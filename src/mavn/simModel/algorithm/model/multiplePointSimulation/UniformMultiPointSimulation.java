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
import mavn.simModel.algorithm.model.point.PointModelInterface;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import mavn.simModel.algorithm.model.point.observer.ImageRatioAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.observer.ShapesRatioAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.generator.PointGeneratorInterface;
import mavn.simModel.algorithm.model.state.output.NetworkLayerOutputModelStateAbstract;
import mavn.simModel.algorithm.model.multiplePointSimulation.state.UniformMutliPointOutputModelState;
import mavn.simModel.algorithm.model.point.ImageRatioAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.ShapesRatioAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.point.observer.PointGeneratorModelObserver;
import mavn.simModel.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;
import mavn.simModel.algorithm.model.multiplePointSimulation.observer.MultiplePointAlgorithmModelObserver;
import mavn.simModel.algorithm.model.multiplePointSimulation.state.UniformMultiPointInputModelState;
import mavn.simModel.algorithm.model.multiplePointSimulation.worker.UniformMultiPointSimulationWorker;
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
public class UniformMultiPointSimulation implements AlgorithmModelInterface,
        MultiplePointModelInterface, PointModelInterface, AndLayerAlgorithmModelInterface,
        OrLayerAlgorithmModelInterface, OutputLayerAlgorithmModelInterface, ShapesRatioAlgorithmModelInterface,
        ImageRatioAlgorithmModelInterface, ThetaModelObserver,
        W0ModelObserver, W1ModelObserver, W2ModelObserver
{
    // Implements seven Subject interfaces for an Observer Pattern.

    private ArrayList<MultiplePointAlgorithmModelObserver> resultObservers;
    private ArrayList<PointGeneratorModelObserver> dartObservers;
    private ArrayList<AndLayerAlgorithmModelObserver> andLayerObservers;
    private ArrayList<OrLayerAlgorithmModelObserver> orLayerObservers;
    private ArrayList<OutputLayerAlgorithmModelObserver> outputLayerObservers;
    private ArrayList<ShapesRatioAlgorithmModelObserver> shapesRatioObservers;
    private ArrayList<ImageRatioAlgorithmModelObserver> imageRatioObservers;
    // Algorithm Network State
    private NetworkLayerOutputModelStateAbstract outputLayerModelState;
    // The Point Simulator for Simulation.
    private PointGeneratorInterface pointGenerator;
    // SwingWorker to execute the simluation
    private SwingWorker simulation;
    // Uniform Multiple Point Algorithm Specific State
    private UniformMultiPointInputModelState inputModelState;
    private UniformMutliPointOutputModelState outputModelResultState;

    /**
     * Initialize a new UniformMultiPointSimulation. This class will run a full
     * simulation based on user inputs by firing multiple Points at the image
     * and returning the results.
     * @param pointGenerator the PointGeneratorInterface implementation that will
     * be used to create the Points to be fired at the shapes in the image.
     */
    public UniformMultiPointSimulation(PointGeneratorInterface pointGenerator)
    {
        this.pointGenerator = pointGenerator;

        andLayerObservers = new ArrayList<AndLayerAlgorithmModelObserver>();
        dartObservers = new ArrayList<PointGeneratorModelObserver>();
        imageRatioObservers = new ArrayList<ImageRatioAlgorithmModelObserver>();
        orLayerObservers = new ArrayList<OrLayerAlgorithmModelObserver>();
        outputLayerObservers = new ArrayList<OutputLayerAlgorithmModelObserver>();
        resultObservers = new ArrayList<MultiplePointAlgorithmModelObserver>();
        shapesRatioObservers = new ArrayList<ShapesRatioAlgorithmModelObserver>();
    }

    /**
     * Execute the UniformMultiPointSimulation.
     */
    @Override
    public void execute()
    {
        if (inputModelState.isInputModelReady())
        {
            simulation = new UniformMultiPointSimulationWorker(
                    pointGenerator,
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
            PointGeneratorModelObserver dartObserver = (PointGeneratorModelObserver) dartObservers.get(i);
            dartObserver.updateDartResults(outputModelResultState.getHit(), outputModelResultState.getMiss());
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
    public void registerObserver(PointGeneratorModelObserver o)
    {
        dartObservers.add(o);
    }

    /**
     * Remove a DartsObserver.
     * @param o the DartsObserver.
     */
    @Override
    public void removeObserver(PointGeneratorModelObserver o)
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
}

