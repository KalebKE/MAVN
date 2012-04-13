/*
MavenSinglePointModel --
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
package mavn.algorithm.model.singlePointSimulation;

import mavn.algorithm.model.network.OutputLayerAlgorithmModelInterface;
import mavn.algorithm.model.network.AndLayerAlgorithmModelInterface;
import mavn.algorithm.model.network.OrLayerAlgorithmModelInterface;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import mavn.algorithm.model.state.input.InputModelState;
import mavn.algorithm.model.singlePointSimulation.state.SinglePointLayerOutputModelState;
import mavn.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;
import mavn.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;
import mavn.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;
import mavn.algorithm.model.singlePointSimulation.observer.SinglePointAlgorithmModelObserver;
import mavn.algorithm.model.singlePointSimulation.worker.DiagnosticSimulationWorker;
import mavn.input.model.observer.TargetInputModelObserver;
import mavn.input.model.observer.ThetaInputModelObserver;
import mavn.input.model.observer.W0InputModelObserver;
import mavn.input.model.observer.W1InputModelObserver;
import mavn.input.model.observer.W2InputModelObserver;
import simulyn.algorithm.model.AlgorithmModelInterface;

/**
 * A MAVN Single Point Model is intended to be used as testing or educational
 * tool. It fires a single Point defined by the user in the Target InputModel
 * at the image. While this is powerful tool to ensure that your simulation's model
 * is working correctly and a good way of demonstrating how the MAVN Network
 * works, it is not practical for pattern recognition which require a large
 * number of Points to be fired at the image.
 *
 * @author Kaleb
 */
public class DiagnosticSimulation implements AlgorithmModelInterface,
        SinglePointModelInterface,
        AndLayerAlgorithmModelInterface,
        OrLayerAlgorithmModelInterface,
        OutputLayerAlgorithmModelInterface,
        TargetInputModelObserver,
        ThetaInputModelObserver,
        W0InputModelObserver,
        W1InputModelObserver,
        W2InputModelObserver
{
    // Implements four Subject interfaces for an Observer Pattern.

    private ArrayList<SinglePointAlgorithmModelObserver> singlePointModelObserver;
    private ArrayList<AndLayerAlgorithmModelObserver> andLayerObservers;
    private ArrayList<OrLayerAlgorithmModelObserver> orLayerObservers;
    private ArrayList<OutputLayerAlgorithmModelObserver> outputLayerObservers;
    // Algorithm State
    private InputModelState inputModelState;
    private SinglePointLayerOutputModelState outputModelState;
    private SwingWorker simulation;

    /**
     * Initialize the Simulation.
     */
    public DiagnosticSimulation()
    {
        // Initialize the collections to store the Observers
        singlePointModelObserver = new ArrayList<SinglePointAlgorithmModelObserver>();
        andLayerObservers = new ArrayList<AndLayerAlgorithmModelObserver>();
        orLayerObservers = new ArrayList<OrLayerAlgorithmModelObserver>();
        outputLayerObservers = new ArrayList<OutputLayerAlgorithmModelObserver>();
        inputModelState = new InputModelState();
        outputModelState = new SinglePointLayerOutputModelState(this);
    }

    /**
     * Execute the Simulation.
     */
    @Override
    public void execute()
    {
        if (inputModelState.isInputModelReady())
        {
            simulation = new DiagnosticSimulationWorker(inputModelState, outputModelState);
            simulation.execute();
        }
    }

    /**
     * Register a new MavnSinglePointAlgorithmModelObserver.
     * @param o the MavnSinglePointAlgorithmModelObserver.
     */
    @Override
    public void registerObserver(SinglePointAlgorithmModelObserver o)
    {
        singlePointModelObserver.add(o);
    }

    /**
     * Remove a MavnSinglePointAlgorithmModelObserver.
     * @param o the MavnSinglePointAlgorithmModelObserver.
     */
    @Override
    public void removeObserver(SinglePointAlgorithmModelObserver o)
    {
        int i = singlePointModelObserver.indexOf(o);
        if (i >= 0)
        {
            singlePointModelObserver.remove(o);
        }
    }

    /**
     * Register a And Layer Algorithm Model Observer.
     * @param o the AndLayerAlgorithmModelObserver.
     */
    @Override
    public void registerObserver(AndLayerAlgorithmModelObserver o)
    {
        andLayerObservers.add(o);
    }

    /**
     * Remove a And Layer Algorithm Model Observer.
     * @param o the AndLayerAlgorithmModelObserver.
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
     * Register a Or Layer Algorithm Model Observer.
     * @param o the OrLayerAlgorithmModelObserver.
     */
    @Override
    public void registerObserver(OrLayerAlgorithmModelObserver o)
    {
        orLayerObservers.add(o);
    }

    /**
     * Remove a Or Layer Algorithm Model Observer.
     * @param o the OrLayerAlgorithmModelObserver.
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
     * Register a Output Layer Algorithm Model Observer.
     * @param o the OutputLayerAlgorithmModelObserver.
     */
    @Override
    public void registerObserver(OutputLayerAlgorithmModelObserver o)
    {
        outputLayerObservers.add(o);
    }

    /**
     * Remove a Output Layer Algorithm Model Observer.
     * @param o the OutputLayerAlgorithmModelObserver.
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
     * Notify the And Layer Observers that new State is available.
     */
    @Override
    public void notifyAndLayerObservers()
    {
        for (int i = 0; i < andLayerObservers.size(); i++)
        {
            AndLayerAlgorithmModelObserver observer = (AndLayerAlgorithmModelObserver) andLayerObservers.get(i);
            observer.updateAndLayerModelOutput(outputModelState.getAndLayerOutput());
        }
    }

    /**
     * Notify the Single Point Model Observers that new State is available.
     */
    @Override
    public void notifySinglePointModelObserver()
    {
        for (int i = 0; i < singlePointModelObserver.size(); i++)
        {
            SinglePointAlgorithmModelObserver observer = (SinglePointAlgorithmModelObserver) singlePointModelObserver.get(i);
            observer.updateSinglePointModelOutput(outputModelState.getOutputLayerOutput());
        }
    }

    /**
     * Notify Or Layer Observers that new State is available.
     */
    @Override
    public void notifyOrLayerObservers()
    {
        for (int i = 0; i < orLayerObservers.size(); i++)
        {
            OrLayerAlgorithmModelObserver observer = (OrLayerAlgorithmModelObserver) orLayerObservers.get(i);
            observer.updateOrLayerModelOutput(outputModelState.getOrLayerOutput());
        }
    }

    /**
     * Notify Output Layer Observers that new State is available.
     */
    @Override
    public void notifyOutputLayerObservers()
    {
        for (int i = 0; i < outputLayerObservers.size(); i++)
        {
            OutputLayerAlgorithmModelObserver observer = (OutputLayerAlgorithmModelObserver) outputLayerObservers.get(i);
            observer.updateOutputLayerModelOutput(outputModelState.getOutputLayerOutput());
        }
    }

    /**
     * Hook to Observe the Target Input Model Subject for new Input Model State.
     * @param modelInput the Input Model State.
     */
    @Override
    public void updateTargetModelInput(double[][] modelInput)
    {
        this.inputModelState.setTarget(modelInput);
    }

    /**
     * Hook to Observe the Theta Input Model Subject for new Input Model State.
     * @param modelInput the Input Model State.
     */
    @Override
    public void updateThetaModelInput(double[][] modelInput)
    {
        this.inputModelState.setTheta(modelInput);
    }

    /**
     * Hook to Observe the W0 Input Model Subject for new Input Model State.
     * @param modelInput the Input Model State.
     */
    @Override
    public void updateW0ModelInput(double[][] modelInput)
    {
        this.inputModelState.setW0(modelInput);
    }

    /**
     * Hook to Observe the W1 Input Model Subject for new Input Model State.
     * @param modelInput the Input Model State.
     */
    @Override
    public void updateW1ModelInput(double[][] modelInput)
    {
        this.inputModelState.setW1(modelInput);
    }

    /**
     * Hook to Observe the W2 Input Model Subject for new Input Model State.
     * @param modelInput the Input Model State.
     */
    @Override
    public void updateW2ModelInput(double[][] modelInput)
    {
        this.inputModelState.setW2(modelInput);
    }
}

