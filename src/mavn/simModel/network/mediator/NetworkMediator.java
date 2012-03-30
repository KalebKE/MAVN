/*
ResultNetworkMediator -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
Copyright (C) 2012, Kaleb Kircher.

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
package mavn.simModel.network.mediator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import mavn.simModel.algorithm.properties.view.state.SimulationPropertiesStateInterface;
import mavn.simModel.network.mediator.state.NetworkMediatorState;
import mavn.simModel.network.model.AndLayerOutputModel;
import mavn.simModel.network.model.OrLayerOutputModel;
import mavn.simModel.network.model.OutputLayerOutputModel;
import mavn.simModel.network.model.observer.AndLayerOutputModelObserver;
import mavn.simModel.network.model.observer.OrLayerOutputModelObserver;
import mavn.simModel.network.model.observer.OutputLayerOutputModelObserver;
import mavn.simModel.output.view.layoutPanel.ModelOutputDefaultLayoutView;
import mavn.simModel.network.view.MavnNetworkRenderer;
import mavn.simModel.output.view.state.OutputViewStateInterface;
import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.model.OutputModelInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 * ModelViewMediatorInterface implementations are used to completely decouple
 * the Model from the View using a Model-View-Mediator (MVM) architecture. This
 * pattern is also known as a Model-View-Presenter using a Passive View strategy.
 *
 * Mediators differ from Controllers in how they couple the View to the Model.
 * With a Controller, the View requests Actions to be taken upon the Model via
 * the Controller and the Model will then update the View with the new State.
 * With a Mediator, the View requests Actions to be taken upon the Model via the
 * Mediator and the Model will update the Mediator with the new State. The Mediator
 * will then provide the View with the new State.
 *
 * An MVM is used to manage the Views for the Network UI, Plot UI, and Result UI.
 * This is because we want the UI's to be completely decoupled from any form
 * of a model or controller, mediator, presenter, etc... We want the UI's to be as
 * decoupled and as portable as possible so they can be reused in many ways.
 * But we don't know how the UI's might be used in the future. This is why
 * the MVM is used.
 *
 * ResultNetworkMediator is a implementation of the Simulyn Framework that
 * provides a coupling between MAVN's Result Models, Input Models, and Result Views.
 * It manages a Network UI View that is designed to allow the user
 * to view a simulation network rendering once the Input Model has been loaded into the simluation.
 * It provides the logic for the View's Actions and renders the Model Results with a
 * Network Rendering UI backed by the JUNG library within the View.
 * @author Kaleb
 */
public class NetworkMediator implements OutputViewMediatorInterface, NetworkRendererInterface,
        AndLayerOutputModelObserver, OrLayerOutputModelObserver, OutputLayerOutputModelObserver
{
    // Model Algorithm Model

    private MediatorStateInterface multiPointNetworkState;
    private MavnNetworkRenderer view;
    // Model Result Models
    private OutputModelInterface andLayerModelResult;
    private OutputModelInterface orLayerModelResult;
    private OutputModelInterface outputLayerModelResult;
    private OutputViewStateInterface modelResultState;
    private SimulationPropertiesStateInterface simulationPropertiesState;

    /**
     * Initialize the state.
     * @param mainView the View that will be updated with the output
     * @param dartGunIterator the DartGunInterface collection 
     */
    public NetworkMediator(
            OutputModelInterface andLayerModelResult,
            OutputModelInterface orLayerModelResult,
            OutputModelInterface outputLayerModelResult,
            SimulationPropertiesStateInterface simulationPropertiesState)
    {

        this.simulationPropertiesState = simulationPropertiesState;
        // local instance of the result model
        this.andLayerModelResult = andLayerModelResult;
        // this class should observe changes to the result model
        ((AndLayerOutputModel) this.andLayerModelResult).registerObserver(this);

        // local instance of the result model
        this.orLayerModelResult = orLayerModelResult;
        // this class should observe changes to the result model
        ((OrLayerOutputModel) this.orLayerModelResult).registerObserver(this);

        // local instance of the result model
        this.outputLayerModelResult = outputLayerModelResult;
        // this class should observe changes to the result model
        ((OutputLayerOutputModel) this.outputLayerModelResult).registerObserver(this);

        view = new MavnNetworkRenderer();
        multiPointNetworkState = new NetworkMediatorState(this);
    }

    @Override
    public JPanel getView()
    {
        return view;
    }

    public void setModelResultState(OutputViewStateInterface modelResultState)
    {
        this.modelResultState = modelResultState;
    }

    @Override
    public void setNetwork(double[][] w2, double[][] w1, double[][] w0)
    {
        view.setNetwork(w2, w1, w0);
    }

    /**
     * Hook to Observe the AND Layer Model Result Subject. The State of the
     * Model Result describes what Nodes went high and what Nodes stayed
     * low in the AND Layer of the simulations Network Model.
     * @param matrix describes the state of the AND Layer Nodes
     */
    @Override
    public void updateAndLayerModelResult(double[][] matrix)
    {
        double[] andLayerResult = new double[matrix.length];
        for (int i = 0; i < andLayerResult.length; i++)
        {
            andLayerResult[i] = matrix[i][0];
        }
        // Save the result of the AND Layer Node's State to a local State Pattern
        // that will decide when all of the State that the View requires has been
        // initialized and set. At that point, the State Pattern will call
        // updateUI() and push the entire Network's State to the View. The
        // State Pattern will then reset for the next Network State.
        ((NetworkMediatorState) multiPointNetworkState).setAndLayerOutput(andLayerResult);
    }

    /**
     * Hook to Observe the OR Layer Model Result Subject. The State of the
     * Model Result describes what Nodes went high and what Nodes stayed
     * low in the OR Layer of the simulations Network Model.
     * @param matrix describes the state of the OR Layer Nodes
     */
    @Override
    public void updateOrLayerModelResult(double[][] matrix)
    {
        double[] orLayerResult = new double[matrix.length];
        for (int i = 0; i < orLayerResult.length; i++)
        {
            orLayerResult[i] = matrix[i][0];
        }
        // Save the result of the OR Layer Node's State to a local State Pattern
        // that will decide when all of the State that the View requires has been
        // initialized and set. At that point, the State Pattern will call
        // updateUI() and push the entire Network's State to the View. The
        // State Pattern will then reset for the next Network State.
        ((NetworkMediatorState) multiPointNetworkState).setOrLayerOutput(orLayerResult);
    }

    /**
     * Hook to Observe the OUTPUT Layer Model Result Subject. The State of the
     * Model Result describes what Nodes went high and what Nodes stayed
     * low in the OUTPUT Layer of the simulations Network Model.
     * @param matrix describes the state of the OUTPUT Layer Nodes
     */
    @Override
    public void updateOutputLayerModelResult(double[][] matrix)
    {
        double[] outputLayerResult = new double[matrix.length];
        for (int i = 0; i < outputLayerResult.length; i++)
        {
            outputLayerResult[i] = matrix[i][0];
        }
        // Save the result of the OUTPUT Layer Node's State to a local State Pattern
        // that will decide when all of the State that the View requires has been
        // initialized and set. At that point, the State Pattern will call
        // updateUI() and push the entire Network's State to the View. The
        // State Pattern will then reset for the next Network State.
        ((NetworkMediatorState) multiPointNetworkState).setOutputLayerOutput(outputLayerResult);
    }

    /**
     * Called when the Mediator has new State ready to be pushed to the View.
     */
    @Override
    public void updateUI()
    {
        // Thread.sleep() is called to pause the animation so it can be
        // seen by the human eye.

        if (this.modelResultState.isAnimated() || simulationPropertiesState.isTargetModel())
        {
            try
            {
                Thread.sleep(20);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ModelOutputDefaultLayoutView.class.getName()).log(Level.SEVERE, null, ex);
            }

            view.fireNodes(((NetworkMediatorState) multiPointNetworkState).getNodeOutput());
            view.repaint();
        }
    }
}
