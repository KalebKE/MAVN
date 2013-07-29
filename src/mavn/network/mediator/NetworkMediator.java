/*
NetworkMediator -- a class within the Machine Artificial Vision Network
(Machine Artificial Vision Network).
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
package mavn.network.mediator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import mavn.network.mediator.state.NetworkMediatorModelState;
import mavn.network.mediator.state.NetworkMediatorViewStateInterface;
import mavn.network.model.AndLayerOutputModel;
import mavn.network.model.OrLayerOutputModel;
import mavn.network.model.OutputLayerOutputModel;
import mavn.network.model.observer.AndLayerOutputModelObserver;
import mavn.network.model.observer.OrLayerOutputModelObserver;
import mavn.network.model.observer.OutputLayerOutputModelObserver;
import mavn.network.view.MavnNetworkRenderer;
import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.model.OutputModelInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 * ModelViewMediatorInterface implementations are used to completely decouple
 * the Model from the View using a Model-View-Mediator (MVM) architecture. This
 * pattern is also known as a Model-View-Presenter using a Passive View strategy.
 * It uses a single method to push new State to the View, updateUI(). 
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
 * NetworkMediator is a implementation that provides a coupling between
 * MAVN's Output Models and Output View's. 
 * It manages a Network UI View that is designed to allow the user
 * to view a simulation network rendering once the Input Model has been loaded into the simluation.
 * It provides the logic for the View's Actions and renders the Model Results with a
 * Network Rendering UI backed by the JUNG library within the View.
 * @author Kaleb
 */
public class NetworkMediator implements AndLayerOutputModelObserver,
        NetworkMediatorInterface, OutputViewMediatorInterface,
        OrLayerOutputModelObserver, OutputLayerOutputModelObserver
{
    // Model Algorithm Model
    private MavnNetworkRenderer networkView;
    private MediatorStateInterface networkModelState;
    private NetworkMediatorViewStateInterface networkViewState;
    // Model Result Models
    private OutputModelInterface andLayerOutputModel;
    private OutputModelInterface orLayerOutputModel;
    private OutputModelInterface outputLayerOutputModel;

    /**
     * Initialize the Network Mediator.
     * @param andLayerOutputModel
     * @param orLayerOutputModel
     * @param outputLayerModelResult
     */
    public NetworkMediator(
            OutputModelInterface andLayerOutputModel,
            OutputModelInterface orLayerOutputModel,
            OutputModelInterface outputLayerOutputModel)
    {

        // local instance of the result model
        this.andLayerOutputModel = andLayerOutputModel;
        // this class should observe changes to the result model
        ((AndLayerOutputModel) this.andLayerOutputModel).registerObserver(this);

        // local instance of the result model
        this.orLayerOutputModel = orLayerOutputModel;
        // this class should observe changes to the result model
        ((OrLayerOutputModel) this.orLayerOutputModel).registerObserver(this);

        // local instance of the result model
        this.outputLayerOutputModel = outputLayerOutputModel;
        // this class should observe changes to the result model
        ((OutputLayerOutputModel) this.outputLayerOutputModel).registerObserver(this);

        networkView = new MavnNetworkRenderer();
        networkModelState = new NetworkMediatorModelState(this);
    }

    /**
     * Animate the network.
     * @param animate boolean indicating if the network should be animated.
     */
    @Override
    public void animateNetwork(boolean animate)
    {
        ((NetworkMediatorModelState) networkModelState).setAnimated(animate);
        networkViewState.setAnimated(animate);
    }

    /**
     * Get the network's Model State.
     * @return the State of the network's Model.
     */
    public NetworkMediatorModelState getNetworkModelState()
    {
        return (NetworkMediatorModelState) networkModelState;
    }

    /**
     * Get the network View.
     * @return the network View.
     */
    @Override
    public JPanel getView()
    {
        return networkView;
    }

    /**
     * Check if the network is animated.
     * @return boolean indicating if the network is animated.
     */
    @Override
    public boolean isAnimateNetwork()
    {
        return ((NetworkMediatorModelState) networkModelState).isAnimated();
    }

    /**
     * Reset the network.
     */
    @Override
    public void resetNetwork()
    {
        this.setNetwork(((NetworkMediatorModelState) networkModelState).getW2(),
                ((NetworkMediatorModelState) networkModelState).getW1(),
                ((NetworkMediatorModelState) networkModelState).getW0());
    }

    /**
     * Set the network.
     * @param w2 the W2 Input Model.
     * @param w1 the W1 Input Model.
     * @param w0 the W0 Input Model.
     */
    @Override
    public void setNetwork(double[][] w2, double[][] w1, double[][] w0)
    {
        ((NetworkMediatorModelState) networkModelState).setW2(w2);
        ((NetworkMediatorModelState) networkModelState).setW1(w1);
        ((NetworkMediatorModelState) networkModelState).setW0(w0);

        networkView.setNetwork(w2, w1, w0);
    }

    /**
     * Set the network's View State.
     * @param networkViewState the State of the network View.
     */
    public void setNetworkViewState(NetworkMediatorViewStateInterface networkViewState)
    {
        this.networkViewState = networkViewState;
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
        ((NetworkMediatorModelState) networkModelState).setAndLayerOutput(andLayerResult);
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
        ((NetworkMediatorModelState) networkModelState).setOrLayerOutput(orLayerResult);
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
        ((NetworkMediatorModelState) networkModelState).setOutputLayerOutput(outputLayerResult);
    }

    /**
     * Called when the Mediator has new View State ready to be pushed to the View.
     */
    @Override
    public void updateUI()
    {
        // Thread.sleep() is called to pause the animation so it can be
        // seen by the human eye.

        if (((NetworkMediatorModelState) networkModelState).isAnimated())
        {
            try
            {
                Thread.sleep(20);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(NetworkMediator.class.getName()).log(Level.SEVERE, null, ex);
            }

            networkView.fireNodes(((NetworkMediatorModelState) networkModelState).getNodeOutput());
            networkView.repaint();
        }
    }
}
