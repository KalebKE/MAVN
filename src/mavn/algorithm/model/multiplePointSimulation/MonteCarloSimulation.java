/*
MonteCarloSimulation -- A class within the Machine Artificial Vision
Network(Machine Artificial Vision Network).
Copyright (C) 2012, Kaleb Kircher, Dennis Steele.

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package mavn.algorithm.model.multiplePointSimulation;

import mavn.algorithm.model.network.OutputLayerAlgorithmModelInterface;
import mavn.algorithm.model.network.AndLayerAlgorithmModelInterface;
import mavn.algorithm.model.network.OrLayerAlgorithmModelInterface;
import mavn.algorithm.model.point.PointAlgorithmModelInterface;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import mavn.algorithm.model.point.observer.ImageRatioAlgorithmModelObserver;
import mavn.algorithm.model.point.observer.ShapesRatioAlgorithmModelObserver;
import mavn.algorithm.model.point.generator.PointGeneratorInterface;
import mavn.algorithm.model.state.output.NetworkLayerOutputModelStateAbstract;
import mavn.algorithm.model.multiplePointSimulation.state.MonteCarloPointOutputModelState;
import mavn.algorithm.model.point.ImageRatioAlgorithmModelInterface;
import mavn.algorithm.model.point.ShapesRatioAlgorithmModelInterface;
import mavn.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;
import mavn.algorithm.model.point.observer.PointGeneratorAlgorithmModelObserver;
import mavn.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;
import mavn.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;
import mavn.algorithm.model.multiplePointSimulation.observer.MultiplePointAlgorithmModelObserver;
import mavn.algorithm.model.multiplePointSimulation.state.MultiPointSimulationInputModelState;
import mavn.algorithm.model.multiplePointSimulation.state.MonteCarloNetworkOutputModelState;
import mavn.algorithm.model.multiplePointSimulation.worker.MonteCarloSimulationWorker;
import mavn.algorithm.model.point.PointHitAlgorithmModelInterface;
import mavn.algorithm.model.point.PointMissAlgorithmModelInterface;
import mavn.algorithm.model.point.observer.PointHitAlgorithmModelObserver;
import mavn.algorithm.model.point.observer.PointMissAlgorithmModelObserver;
import mavn.algorithm.model.timer.TimerAlgorithmModelInterface;
import mavn.algorithm.model.timer.observer.TimerAlgorithmModelObserver;
import mavn.input.model.observer.ThetaInputModelObserver;
import mavn.input.model.observer.W0InputModelObserver;
import mavn.input.model.observer.W1InputModelObserver;
import mavn.input.model.observer.W2InputModelObserver;
import simulyn.algorithm.model.AlgorithmModelInterface;

/**
 * A implementation of AlgorithmModelInterface that runs a Monte Carlo
 * Pattern Recognition Simulation. 
 * The simulation generates random Points that are fired at the image which is
 * represented by the Input Model.
 * The number of hits (Points that hit a shape in the image) versus the number
 * of misses (Points that hit nothing) are recorded and made availble to Monte
 * Carlo Simulation Observers using an Observer pattern that pushes State.
 * @author Kaleb
 */
public class MonteCarloSimulation implements AlgorithmModelInterface,
        MultiplePointSimulationInterface, PointAlgorithmModelInterface, AndLayerAlgorithmModelInterface,
        OrLayerAlgorithmModelInterface, OutputLayerAlgorithmModelInterface,
        ShapesRatioAlgorithmModelInterface, PointHitAlgorithmModelInterface,
        PointMissAlgorithmModelInterface, ImageRatioAlgorithmModelInterface,
        TimerAlgorithmModelInterface, ThetaInputModelObserver,
        W0InputModelObserver, W1InputModelObserver, W2InputModelObserver
{
    // Implements seven Subject interfaces for an Observer Pattern.

    private ArrayList<MultiplePointAlgorithmModelObserver> resultObservers;
    private ArrayList<PointGeneratorAlgorithmModelObserver> pointObservers;
    private ArrayList<AndLayerAlgorithmModelObserver> andLayerObservers;
    private ArrayList<OrLayerAlgorithmModelObserver> orLayerObservers;
    private ArrayList<OutputLayerAlgorithmModelObserver> outputLayerObservers;
    private ArrayList<ShapesRatioAlgorithmModelObserver> shapesRatioObservers;
    private ArrayList<ImageRatioAlgorithmModelObserver> imageRatioObservers;
    private ArrayList<PointHitAlgorithmModelObserver> pointHitObservers;
    private ArrayList<PointMissAlgorithmModelObserver> pointMissObservers;
    private ArrayList<TimerAlgorithmModelObserver> timerObservers;
    // Algorithm Network State
    private NetworkLayerOutputModelStateAbstract outputLayerModelState;
    // The Point Simulator for Simulation.
    private PointGeneratorInterface pointGenerator;
    // SwingWorker to execute the simluation
    private SwingWorker simulation;
    // Uniform Multiple Point Algorithm Specific State
    private MultiPointSimulationInputModelState inputModelState;
    private MonteCarloPointOutputModelState outputModelResultState;

    /**
     * Initialize a new Monte Carlo Simulation. This class will run a full
     * simulation based on user inputs by firing multiple Points at the image
     * and returning the results.
     * @param pointGenerator the PointGeneratorInterface implementation that will
     * be used to create the Points to be fired at the shapes in the image.
     */
    public MonteCarloSimulation(PointGeneratorInterface pointGenerator)
    {
        this.pointGenerator = pointGenerator;

        // Initialize the Observers
        andLayerObservers = new ArrayList<AndLayerAlgorithmModelObserver>();
        pointObservers = new ArrayList<PointGeneratorAlgorithmModelObserver>();
        imageRatioObservers = new ArrayList<ImageRatioAlgorithmModelObserver>();
        orLayerObservers = new ArrayList<OrLayerAlgorithmModelObserver>();
        outputLayerObservers = new ArrayList<OutputLayerAlgorithmModelObserver>();
        resultObservers = new ArrayList<MultiplePointAlgorithmModelObserver>();
        shapesRatioObservers = new ArrayList<ShapesRatioAlgorithmModelObserver>();
        pointHitObservers = new ArrayList<PointHitAlgorithmModelObserver>();
        pointMissObservers = new ArrayList<PointMissAlgorithmModelObserver>();
        timerObservers = new ArrayList<TimerAlgorithmModelObserver>();

        // Initialize the State Managers.
        inputModelState = new MultiPointSimulationInputModelState();
        outputModelResultState = new MonteCarloPointOutputModelState(this);
        outputLayerModelState = new MonteCarloNetworkOutputModelState(this);
    }

    /**
     * Execute the Monte Carlo Simulation.
     */
    @Override
    public void execute()
    {
        if (inputModelState.isInputModelReady())
        {

            // A Command Pattern is implemented with a Swing Worker
            // to spawn off a new thread to actually run the simulations
            // calcuations. This keeps the GUI from hanging while the
            // simulation runs and signifcantly improves performance.
            simulation = new MonteCarloSimulationWorker(
                    pointGenerator,
                    inputModelState,
                    outputLayerModelState,
                    outputModelResultState);

            simulation.execute();
        }
    }

    /**
     * Notify all Point Generator Alogirhtm Model Observers.
     */
    @Override
    public void notifyPointGeneratorModelObservers()
    {
        for (int i = 0; i < pointObservers.size(); i++)
        {
            PointGeneratorAlgorithmModelObserver dartObserver = (PointGeneratorAlgorithmModelObserver) pointObservers.get(i);
            dartObserver.updatePoints(outputModelResultState.getHitPointList(), outputModelResultState.getMissPointList());
        }
    }

    /**
     * Notify all Shapes Point Ratio Algorithm Model Observers. 
     */
    @Override
    public void notifyShapesRatioModelObservers()
    {
        for (int i = 0; i < shapesRatioObservers.size(); i++)
        {
            ShapesRatioAlgorithmModelObserver resultObserver = (ShapesRatioAlgorithmModelObserver) shapesRatioObservers.get(i);
            resultObserver.updateShapesRatioModelResult(outputModelResultState.getShapesPointRatio());
        }
    }

    /**
     * Notify all Image Point Ratio Algorithm Model Observers.
     */
    @Override
    public void notifyImageRatioModelObservers()
    {
        for (int i = 0; i < imageRatioObservers.size(); i++)
        {
            ImageRatioAlgorithmModelObserver resultObserver = (ImageRatioAlgorithmModelObserver) imageRatioObservers.get(i);
            resultObserver.updateImageRatioModelResult(outputModelResultState.getImagePointRatio());
        }
    }

    /**
     * Notify all Multiple Point Simulation Algorithm Model Observer.
     */
    @Override
    public void notifyMultiplePointModelObserver()
    {
        for (int i = 0; i < resultObservers.size(); i++)
        {
            MultiplePointAlgorithmModelObserver resultObserver = (MultiplePointAlgorithmModelObserver) resultObservers.get(i);
            resultObserver.updateMultiplePointModelResult(outputLayerModelState.getOutputLayerOutput());
        }
    }

    /**
     * Notify all AND Layer Network Observers.
     */
    @Override
    public void notifyAndLayerObservers()
    {
        for (int i = 0; i < andLayerObservers.size(); i++)
        {
            AndLayerAlgorithmModelObserver observer = (AndLayerAlgorithmModelObserver) andLayerObservers.get(i);
            observer.updateAndLayerModelOutput(outputLayerModelState.getAndLayerOutput());
        }
    }

    /**
     * Notify all OR Layer Network Observers.
     */
    @Override
    public void notifyOrLayerObservers()
    {
        for (int i = 0; i < orLayerObservers.size(); i++)
        {
            OrLayerAlgorithmModelObserver observer = (OrLayerAlgorithmModelObserver) orLayerObservers.get(i);
            observer.updateOrLayerModelOutput(outputLayerModelState.getOrLayerOutput());
        }
    }

    /**
     * Notify all Output Layer Network Observers.
     */
    @Override
    public void notifyOutputLayerObservers()
    {
        for (int i = 0; i < outputLayerObservers.size(); i++)
        {
            OutputLayerAlgorithmModelObserver observer = (OutputLayerAlgorithmModelObserver) outputLayerObservers.get(i);
            observer.updateOutputLayerModelOutput(outputLayerModelState.getOutputLayerOutput());
        }
    }

    /**
     * Notify all Point Hit Model Observers. Note that this method should be used
     * to update classes of Points that hit a shape in the image in real time.
     */
    @Override
    public void notifyPointHitModelObservers()
    {
        for (int i = 0; i < pointHitObservers.size(); i++)
        {
            PointHitAlgorithmModelObserver observer = (PointHitAlgorithmModelObserver) pointHitObservers.get(i);
            observer.updatePointHit(outputModelResultState.getHitPointList().get(outputModelResultState.getHitPointList().size() - 1));
        }
    }

    /**
     * Notify all Point Miss Model Observers. Note that this method should be used
     * to update classes of Points that missed a shape in the image in real time.
     */
    @Override
    public void notifyPointMissModelObservers()
    {
        for (int i = 0; i < pointMissObservers.size(); i++)
        {
            PointMissAlgorithmModelObserver observer = (PointMissAlgorithmModelObserver) pointMissObservers.get(i);
            observer.updatePointMiss(outputModelResultState.getMissPointList().get(outputModelResultState.getMissPointList().size() - 1));
        }
    }

    /**
     * Push Timer Model State to Observers.
     */
    @Override
    public void notifyTimerModelObservers()
    {
        for (int i = 0; i < timerObservers.size(); i++)
        {
            TimerAlgorithmModelObserver observer = (TimerAlgorithmModelObserver) timerObservers.get(i);
            observer.updateTimerModel(outputModelResultState.getTimerPoints());
        }
    }

    /**
     * Register an AND Layer Algorithm Model Observer.
     * @param o the AndLayerAlgorithmModelObserver will be notified by the subject
     * when new State is available. 
     */
    @Override
    public void registerObserver(AndLayerAlgorithmModelObserver o)
    {
        andLayerObservers.add(o);
    }

    /**
     * Register an Image Ratio Algorithm Model Observer.
     * @param o the ImageRatioAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void registerObserver(ImageRatioAlgorithmModelObserver o)
    {
        imageRatioObservers.add(o);
    }

    /**
     * Register a new MultiplePointAlgorithmModelObserver Observer.
     * @param o the observer.
     */
    @Override
    public void registerObserver(MultiplePointAlgorithmModelObserver o)
    {
        resultObservers.add(o);
    }

    /**
     * Register an AND Layer Algorithm Model Observer.
     * @param o the OrLayerAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void registerObserver(OrLayerAlgorithmModelObserver o)
    {
        orLayerObservers.add(o);
    }

    /**
     * Register an Output Layer Algorithm Model Observer.
     * @param o the OutputLayerAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void registerObserver(OutputLayerAlgorithmModelObserver o)
    {
        outputLayerObservers.add(o);
    }

    /**
     * Register a new PointGeneratorAlgorithmModelObserver.
     * @param o the PointGeneratorAlgorithmModelObserver.
     */
    @Override
    public void registerObserver(PointGeneratorAlgorithmModelObserver o)
    {
        pointObservers.add(o);
    }

    /**
     * Register the PointHitAlgorithmModelObserver. Point Hit Observers
     * will be notified of Points that hit shapes in the image in real time.
     * @param o the PointHitAlgorithmModelObserver
     */
    @Override
    public void registerObserver(PointHitAlgorithmModelObserver o)
    {
        pointHitObservers.add(o);
    }

    /**
     * Register the PointMissAlgorithmModelObserver. Point Miss Observers
     * will be updated of Points that missed shapes in real time.
     * @param o the PointMissAlgorithmModelObserver
     */
    @Override
    public void registerObserver(PointMissAlgorithmModelObserver o)
    {
        pointMissObservers.add(o);
    }

    /**
     * Register an Shapes Ratio Algorithm Model Observer.
     * @param o the ShapesRatioAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void registerObserver(ShapesRatioAlgorithmModelObserver o)
    {
        shapesRatioObservers.add(o);
    }

    @Override
    public void registerObserver(TimerAlgorithmModelObserver o)
    {
        timerObservers.add(o);
    }

    /**
     * Remove an AND Layer Algorithm Model Observer.
     * @param o the AndLayerAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void removeObserver(AndLayerAlgorithmModelObserver o)
    {
        int i = andLayerObservers.indexOf(o);
        if (i >= 0)
        {
            andLayerObservers.remove(o);
        }
    }

    /**
     * Remove an Image Ratio Algorithm Model Observer.
     * @param o the ImageRatioAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void removeObserver(ImageRatioAlgorithmModelObserver o)
    {
        int i = imageRatioObservers.indexOf(o);
        if (i >= 0)
        {
            imageRatioObservers.remove(o);
        }
    }

    /**
     * Remove a MultiplePointAlgorithmModelObserver Observer.
     * @param o the observer.
     */
    @Override
    public void removeObserver(MultiplePointAlgorithmModelObserver o)
    {
        int i = resultObservers.indexOf(o);
        if (i >= 0)
        {
            resultObservers.remove(o);
        }
    }

    /**
     * Remove an OR Layer Algorithm Model Observer.
     * @param o the OrLayerAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void removeObserver(OrLayerAlgorithmModelObserver o)
    {
        int i = orLayerObservers.indexOf(o);
        if (i >= 0)
        {
            orLayerObservers.remove(o);
        }
    }

    /**
     * Remove an Output Layer Algorithm Model Observer.
     * @param o the OutputLayerAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void removeObserver(OutputLayerAlgorithmModelObserver o)
    {
        int i = outputLayerObservers.indexOf(o);
        if (i >= 0)
        {
            outputLayerObservers.remove(o);
        }
    }

    /**
     * Remove a PointGeneratorAlgorithmModelObserver.
     * @param o the PointGeneratorAlgorithmModelObserver.
     */
    @Override
    public void removeObserver(PointGeneratorAlgorithmModelObserver o)
    {
        int i = pointObservers.indexOf(o);
        if (i >= 0)
        {
            pointObservers.remove(o);
        }
    }

    /**
     * Remove the PointHitAlgorithmModelObserver.
     * @param o the PointHitAlgorithmModelObserver.
     */
    @Override
    public void removeObserver(PointHitAlgorithmModelObserver o)
    {
        int i = pointHitObservers.indexOf(o);
        if (i >= 0)
        {
            pointHitObservers.remove(o);
        }
    }

    /**
     * Remove the PointMissAlgorithmModelObserver.
     * @param o the PointMissAlgorithmModelObserver.
     */
    @Override
    public void removeObserver(PointMissAlgorithmModelObserver o)
    {
        int i = pointMissObservers.indexOf(o);
        if (i >= 0)
        {
            pointMissObservers.remove(o);
        }
    }

    /**
     * Remove an Shapes Ratio Algorithm Model Observer.
     * @param o the ShapesRatioAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void removeObserver(ShapesRatioAlgorithmModelObserver o)
    {
        int i = shapesRatioObservers.indexOf(o);
        if (i >= 0)
        {
            shapesRatioObservers.remove(o);
        }
    }

    /**
     * Remove the TimerAlgorithmModelObserver.
     * @param o the TimerAlgorithmModelObserver.
     */
    @Override
    public void removeObserver(TimerAlgorithmModelObserver o)
    {
        int i = timerObservers.indexOf(o);
        if (i >= 0)
        {
            timerObservers.remove(o);
        }
    }

    /**
     * Set the Point Generator. The Point Generator creates the random
     * points for the simulation.
     * @param pointGenerator the Point Generator.
     */
    public void setPointGenerator(PointGeneratorInterface pointGenerator)
    {
        this.pointGenerator = pointGenerator;
    }

    /**
     * Observer Hook for Theta Input Model Subject.
     * @param modelInput the new Theta Input Model State.
     */
    @Override
    public void updateThetaModelInput(double[][] modelInput)
    {
        this.inputModelState.setTheta(modelInput);
    }

    /**
     * Observer Hook for W0 Input Model Subject.
     * @param modelInput the new W0 Input Model State.
     */
    @Override
    public void updateW0ModelInput(double[][] modelInput)
    {
        this.inputModelState.setW0(modelInput);
    }

    /**
     * Observer Hook for W1 Input Model Subject.
     * @param modelInput the new W1 Input Model State.
     */
    @Override
    public void updateW1ModelInput(double[][] modelInput)
    {
        this.inputModelState.setW1(modelInput);
    }

    /**
     * Observer Hook for W2 Input Model Subject.
     * @param modelInput the new W2 Input Model State.
     */
    @Override
    public void updateW2ModelInput(double[][] modelInput)
    {
        this.inputModelState.setW2(modelInput);
    }
}

