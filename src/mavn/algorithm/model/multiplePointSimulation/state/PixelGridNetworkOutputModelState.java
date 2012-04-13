/*
PixelGridNetworkOutputModelState -- A class within the Machine Artificial
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

import mavn.algorithm.model.multiplePointSimulation.PixelGridSimulation;
import mavn.algorithm.model.state.output.NetworkLayerOutputModelStateAbstract;

/**
 * PixelGridNetworkOutputModelState manages the State of the Network Layers
 * in the Pixel Grid Simulation. A MAVN Network has four layers. They are
 * the Input Layer, AND Layer, OR Layer and Output Layer. Observers of Network Layer Subjects
 * within the Pixel Grid Simulation only need to be notified once for
 * every input after the output has been determined. This class contains the
 * logic to update the Network Layer Subjects only once per input and only
 * after all the network layer results have been determined. 
 * @author Kaleb
 */
public class PixelGridNetworkOutputModelState extends NetworkLayerOutputModelStateAbstract
{

    private PixelGridSimulation simulation;

    /**
     * Initialize a new PixelGridNetworkOutputModelState.
     * @param simulation the PixelGridSimulation the State should be managed for.
     */
    public PixelGridNetworkOutputModelState(PixelGridSimulation simulation)
    {
        this.simulation = simulation;
    }

    /**
     * Indicate that the State has changed.
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
