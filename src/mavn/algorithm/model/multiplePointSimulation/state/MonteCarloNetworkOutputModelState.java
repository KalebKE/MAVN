/*
MonteCarloNetworkOutputModelState -- A class within the Machine Artificial
Vision Network(Machine Artificial Vision Network).
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
package mavn.algorithm.model.multiplePointSimulation.state;

import mavn.algorithm.model.state.output.NetworkLayerOutputModelStateAbstract;
import mavn.algorithm.model.multiplePointSimulation.MonteCarloSimulation;

/**
 * MonteCarloNetworkOutputModelState manages the State of the Network Layers
 * in the Monte Carlo Simulations. A MAVN Network has four layers. They are
 * the Input Layer, AND Layer, OR Layer and Output Layer. Observers of Network Layer Subjects
 * within the Monte Carlo Simulations only need to be notified once for
 * every input after the output has been determined. This class contains the
 * logic to update the Network Layer Subjects only once per input and only
 * after all the network layer results have been determined. 
 * @author Kaleb
 */
public class MonteCarloNetworkOutputModelState extends NetworkLayerOutputModelStateAbstract
{

    private MonteCarloSimulation simulation;

    /**
     * Initialize a new MonteCarloNetworkOutputModelState.
     * @param model the Monte Carlo Simulation the State should be managed for.
     */
    public MonteCarloNetworkOutputModelState(MonteCarloSimulation simulation)
    {
        this.simulation = simulation;
    }

    /**
     * Indicate that the State has changed and verify that all of the State
     * has been set before notifying the Observers.
     */
    @Override
    public void stateChanged()
    {
        if (this.andLayerOutputReady && this.orLayerOutputReady && this.outputLayerOutputReady)
        {
            simulation.notifyAndLayerObservers();
            simulation.notifyOrLayerObservers();
            simulation.notifyOutputLayerObservers();
            resetState();
        }
    }

    @Override
    public void resetState()
    {
        this.andLayerOutputReady = false;
        this.orLayerOutputReady = false;
        this.outputLayerOutputReady = false;
    }
}
