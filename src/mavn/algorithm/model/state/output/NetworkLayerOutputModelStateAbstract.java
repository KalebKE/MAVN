/*
NetworkLayerOutputModelStateAbstract --
A abstract class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.algorithm.model.state.output;

import simulyn.algorithm.model.state.AlgorithmModelStateInterface;

/**
 * NetworkLayerOutputModelStateAbstract manages the State of the Network Layers
 * in the Multiple Point Simulations. A MAVN Network has four layers. They are
 * the Input Layer, AND Layer, OR Layer and Output Layer.
 * Since different MAVN Simulations may need to have their Network Layer State
 * managed differently, NetworkLayerOutputModelStateAbstract provides
 * most of the key implementation and allows child implementations to decide
 * how to deal with it.
 * @author Kaleb
 */
public abstract class NetworkLayerOutputModelStateAbstract implements AlgorithmModelStateInterface
{

    protected boolean andLayerOutputReady;
    protected boolean orLayerOutputReady;
    protected boolean outputLayerOutputReady;
    
    // Algorithm Output State
    protected double[][] andLayerOutput;
    protected double[][] orLayerOutput;
    protected double[][] outputLayerOutput;

    /**
     * Get the output from the AND Layer within the Network for the
     * current set of inputs.
     * @return the AND Layer Output State.
     */
    public double[][] getAndLayerOutput()
    {
        return andLayerOutput;
    }

    /**
     * Get the output from the OR Layer within the Network for the current
     * set of inputs.
     * @return the OR Layer Output State.
     */
    public double[][] getOrLayerOutput()
    {
        return orLayerOutput;
    }

    /**
     * Get the output from the Output Layer within the Network for the current
     * set of inputs.
     * @return the Output Layer Output State.
     */
    public double[][] getOutputLayerOutput()
    {
        return outputLayerOutput;
    }

    /**
     * Set the local copy of the AND Layer Output for the current set of inputs.
     * @param andLayerOutput the AND Layer Output State.
     */
    public void setAndLayerOutput(double[][] andLayerOutput)
    {
        this.andLayerOutput = andLayerOutput;
        this.andLayerOutputReady = true;
        stateChanged();
    }

    /**
     * Set the local copy of the OR Layer Output for the current set of inputs.
     * @param orLayerOutput the Or Layer Output State.
     */
    public void setOrLayerOutput(double[][] orLayerOutput)
    {
        this.orLayerOutput = orLayerOutput;
        this.orLayerOutputReady = true;
        stateChanged();
    }

    /**
     * Set the local compy of the Output Layer Output for the current set of
     * inputs.
     * @param outputLayerOutput the Output Layer Output State.
     */
    public void setOutputLayerResult(double[][] outputLayerOutput)
    {
        this.outputLayerOutput = outputLayerOutput;
        this.outputLayerOutputReady = true;
        stateChanged();
    }
}
