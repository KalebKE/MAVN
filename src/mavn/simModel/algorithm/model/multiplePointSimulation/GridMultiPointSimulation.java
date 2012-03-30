/*
UniformMultiPointSimulation --
A class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.algorithm.model.multiplePointSimulation;

import mavn.simModel.algorithm.model.network.OutputLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.AndLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.OrLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.PointAlgorithmModelInterface;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import mavn.simModel.algorithm.model.point.observer.ImageRatioAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.observer.PointHitAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.observer.PointMissAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.observer.ShapesRatioAlgorithmModelObserver;
import mavn.simModel.algorithm.model.state.output.NetworkLayerOutputModelStateAbstract;
import mavn.simModel.algorithm.model.point.ImageRatioAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.ShapesRatioAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.observer.PointGeneratorAlgorithmModelObserver;
import mavn.simModel.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.multiplePointSimulation.observer.MultiplePointAlgorithmModelObserver;
import mavn.simModel.algorithm.model.multiplePointSimulation.state.GridMultiPointLayerOutputModelState;
import mavn.simModel.algorithm.model.multiplePointSimulation.state.GridMutliPointOutputModelState;
import mavn.simModel.algorithm.model.multiplePointSimulation.state.MultiPointInputModelState;
import mavn.simModel.algorithm.model.multiplePointSimulation.worker.GridMultiPointSimulationWorker;
import mavn.simModel.algorithm.model.point.PointHitAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.PointMissAlgorithmModelInterface;
import mavn.simModel.algorithm.model.timer.TimerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.timer.observer.TimerAlgorithmModelObserver;
import mavn.simModel.input.model.observer.ThetaModelObserver;
import mavn.simModel.input.model.observer.W0ModelObserver;
import mavn.simModel.input.model.observer.W1ModelObserver;
import mavn.simModel.input.model.observer.W2ModelObserver;
import simulyn.algorithm.model.AlgorithmModelInterface;

/**
 * A implementation of AlgorithmModelInterface, MultiplePointModelInterface and
 * PointModelInterface that runs a UniformMultiPointSimulation.
 * A UniformMultiPointSimulation consists of a Monte Carlo type simulation that
 * fires many random Points at shapes within an image.
 * The number of hits (darts that hit a shape in the image) versus the number
 * of misses (darts that hit nothing) are recorded and made availble to
 * Observers using an Observer pattern.
 * UniformMultiPointSimulation is a Subject for five Algorithm Model Observers and
 * an Observer of four Input Models Subjects.
 * UniformMultiPointSimulation uses a Command Pattern defined by
 * AlgorithmModelInterface to execute a Swing Work that will actually
 * run the simulation. 
 * @author Kaleb
 */
public class GridMultiPointSimulation implements AlgorithmModelInterface,
        MultiplePointModelInterface, PointAlgorithmModelInterface, AndLayerAlgorithmModelInterface,
        OrLayerAlgorithmModelInterface, OutputLayerAlgorithmModelInterface, ShapesRatioAlgorithmModelInterface,
        PointHitAlgorithmModelInterface, PointMissAlgorithmModelInterface,
        ImageRatioAlgorithmModelInterface, TimerAlgorithmModelInterface, ThetaModelObserver,
        W0ModelObserver, W1ModelObserver, W2ModelObserver
{
    // Implements seven Subject interfaces for an Observer Pattern.

    private ArrayList<MultiplePointAlgorithmModelObserver> resultObservers;
    private ArrayList<PointGeneratorAlgorithmModelObserver> dartObservers;
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
    // SwingWorker to execute the simluation
    private SwingWorker simulation;
    // Uniform Multiple Point Algorithm Specific State
    private MultiPointInputModelState inputModelState;
    private GridMutliPointOutputModelState outputModelResultState;

    /**
     * Initialize a new UniformMultiPointSimulation. This class will run a full
     * simulation based on user inputs by firing multiple Points at the image
     * and returning the results.
     * @param pointGenerator the PointGeneratorInterface implementation that will
     * be used to create the Points to be fired at the shapes in the image.
     */
    public GridMultiPointSimulation()
    {
        andLayerObservers = new ArrayList<AndLayerAlgorithmModelObserver>();
        dartObservers = new ArrayList<PointGeneratorAlgorithmModelObserver>();
        imageRatioObservers = new ArrayList<ImageRatioAlgorithmModelObserver>();
        orLayerObservers = new ArrayList<OrLayerAlgorithmModelObserver>();
        outputLayerObservers = new ArrayList<OutputLayerAlgorithmModelObserver>();
        resultObservers = new ArrayList<MultiplePointAlgorithmModelObserver>();
        shapesRatioObservers = new ArrayList<ShapesRatioAlgorithmModelObserver>();
        pointHitObservers = new ArrayList<PointHitAlgorithmModelObserver>();
        pointMissObservers = new ArrayList<PointMissAlgorithmModelObserver>();
        timerObservers = new ArrayList<TimerAlgorithmModelObserver>();

        inputModelState = new MultiPointInputModelState();
        outputModelResultState = new GridMutliPointOutputModelState(this);
        outputLayerModelState = new GridMultiPointLayerOutputModelState(this);
    }

    /**
     * Execute the UniformMultiPointSimulation.
     */
    @Override
    public void execute()
    {
        if (inputModelState.isInputModelReady())
        {

            simulation = new GridMultiPointSimulationWorker(
                    inputModelState,
                    outputLayerModelState,
                    outputModelResultState);

            simulation.execute();
        }
    }

    /**
     * Notify all Dart Model Result Observers.
     */
    @Override
    public void notifyPointGeneratorModelObservers()
    {
        for (int i = 0; i < dartObservers.size(); i++)
        {
            PointGeneratorAlgorithmModelObserver dartObserver = (PointGeneratorAlgorithmModelObserver) dartObservers.get(i);
            dartObserver.updatePoints(outputModelResultState.getHit(), outputModelResultState.getMiss());
        }
    }

    /**
     * Notify all Shapes Ratio Model Observers.
     */
    @Override
    public void notifyShapesRatioModelObservers()
    {
        for (int i = 0; i < shapesRatioObservers.size(); i++)
        {
            ShapesRatioAlgorithmModelObserver resultObserver = (ShapesRatioAlgorithmModelObserver) shapesRatioObservers.get(i);
            resultObserver.updateShapesRatioModelResult(outputModelResultState.getShapesRatio());
        }
    }

    /**
     * Notify all Image Ratio Model Observers.
     */
    @Override
    public void notifyImageRatioModelObservers()
    {
        for (int i = 0; i < imageRatioObservers.size(); i++)
        {
            ImageRatioAlgorithmModelObserver resultObserver = (ImageRatioAlgorithmModelObserver) imageRatioObservers.get(i);
            resultObserver.updateImageRatioModelResult(outputModelResultState.getImageRatio());
        }
    }

    /**
     * Notify all Multiple Point Model Observer.
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
     * Notify all AND Layer Observers.
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

    @Override
    public void notifyPointMissModelObservers()
    {
        for (int i = 0; i < pointMissObservers.size(); i++)
        {
            PointMissAlgorithmModelObserver observer = (PointMissAlgorithmModelObserver) pointMissObservers.get(i);
            observer.updatePointMiss(outputModelResultState.getMiss().get(outputModelResultState.getMiss().size() - 1));
        }
    }

    /**
     * Notify all OR Layer Observers.
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
     * Notify all Output Layer Observers.
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

    @Override
    public void notifyPointHitModelObservers()
    {
        for (int i = 0; i < pointHitObservers.size(); i++)
        {
            PointHitAlgorithmModelObserver observer = (PointHitAlgorithmModelObserver) pointHitObservers.get(i);
            observer.updatePointHit(outputModelResultState.getHit().get(outputModelResultState.getHit().size() - 1));
        }
    }

    /**
     * Register a new Results Observer.
     * @param o the observer.
     */
    @Override
    public void registerObserver(MultiplePointAlgorithmModelObserver o)
    {
        resultObservers.add(o);
    }

    /**
     * Remove a Results Observer.
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
     * Register a new DartsObserver.
     * @param o the DartsObserver.
     */
    @Override
    public void registerObserver(PointGeneratorAlgorithmModelObserver o)
    {
        dartObservers.add(o);
    }

    /**
     * Remove a DartsObserver.
     * @param o the DartsObserver.
     */
    @Override
    public void removeObserver(PointGeneratorAlgorithmModelObserver o)
    {
        int i = dartObservers.indexOf(o);
        if (i >= 0)
        {
            dartObservers.remove(o);
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
     * Register an Shapes Ratio Algorithm Model Observer.
     * @param o the ShapesRatioAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void registerObserver(ShapesRatioAlgorithmModelObserver o)
    {
        shapesRatioObservers.add(o);
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
     * Register an Image Ratio Algorithm Model Observer.
     * @param o the ImageRatioAlgorithmModelObserver will be notified by the subject
     * when new State is available.
     */
    @Override
    public void registerObserver(ImageRatioAlgorithmModelObserver o)
    {
        imageRatioObservers.add(o);
    }

    @Override
    public void registerObserver(PointHitAlgorithmModelObserver o)
    {
        pointHitObservers.add(o);
    }

    @Override
    public void registerObserver(PointMissAlgorithmModelObserver o)
    {
        pointMissObservers.add(o);
    }

    @Override
    public void removeObserver(PointHitAlgorithmModelObserver o)
    {
        int i = pointHitObservers.indexOf(o);
        if (i >= 0)
        {
            pointHitObservers.remove(o);
        }
    }

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

    @Override
    public void notifyTimerModelObservers()
    {
        for (int i = 0; i < timerObservers.size(); i++)
        {
            TimerAlgorithmModelObserver observer = (TimerAlgorithmModelObserver) timerObservers.get(i);
            observer.updateTimerModel(outputModelResultState.getTimerPoints());
        }
    }

    @Override
    public void registerObserver(TimerAlgorithmModelObserver o)
    {
        timerObservers.add(o);
    }

    @Override
    public void removeObserver(TimerAlgorithmModelObserver o)
    {
        int i = timerObservers.indexOf(o);
        if (i >= 0)
        {
            timerObservers.remove(o);
        }
    }
}

