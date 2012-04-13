/*
SinglePointLayerOutputModelState --
An interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.algorithm.model.singlePointSimulation.state;

import mavn.algorithm.model.state.output.NetworkLayerOutputModelStateAbstract;
import mavn.algorithm.model.singlePointSimulation.DiagnosticSimulation;

/**
 * This class acts as a State Manager for the Network Layer Models within
 * the Single Point Model Simulation.
 * SinglePointLayerOutputModelState manages the State of the Network Layers
 * in the Single Point Model Simulation. A MAVN Network has four layers. They are
 * the Input Layer, AND Layer, OR Layer and Output Layer.
 * Since different MAVN Simulations may need to have their Network Layer State
 * managed differently, NetworkLayerOutputModelStateAbstract provides
 * most of the key implementation and allows child implementations to decide
 * how to deal with it.
 * @author Kaleb
 */
public class SinglePointLayerOutputModelState extends NetworkLayerOutputModelStateAbstract
{

    private DiagnosticSimulation model;

    /**
     * Initialize a new Single Point Output Model State Manager.
     * @param model the Single Point Model that this class will
     * manage the Output State for. 
     */
    public SinglePointLayerOutputModelState(DiagnosticSimulation model)
    {
        this.model = model;
    }

    /**
     * When the State changes, call stateChanged() to see if all of the
     * Network Layer Models have been set. If they have been set,
     * notify the Model Subjects to update their Observers and
     * reset the State for the next set of inputs.
     */
    @Override
    public void stateChanged()
    {
        if (this.andLayerOutputReady && this.orLayerOutputReady && this.outputLayerOutputReady)
        {
            model.notifyAndLayerObservers();
            model.notifyOrLayerObservers();
            model.notifyOutputLayerObservers();
            model.notifySinglePointModelObserver();

            resetState();
        }
    }

    /**
     * Reset the State.
     */
    @Override
    public void resetState()
    {
        this.andLayerOutputReady = false;
        this.orLayerOutputReady = false;
        this.outputLayerOutputReady = false;
    }
}
