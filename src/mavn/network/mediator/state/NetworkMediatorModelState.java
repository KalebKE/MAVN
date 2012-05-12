/*
NetworkMediatorModelState -- A class within the Machine Artificial Vision
Network(Machine Artificial Vision Network).
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
package mavn.network.mediator.state;

import java.util.ArrayList;
import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 * NetworkMediatorModelState manages all of the Model State required to render the
 * graphical representation of the simulations network. 
 * @author Kaleb
 */
public class NetworkMediatorModelState implements MediatorStateInterface
{

    private boolean animated;
    private boolean andLayerUpdated;
    private boolean orLayerUpdated;
    private boolean outputLayerUpdated;
    private double[] andLayerOutput;
    private double[] orLayerOutput;
    private double[] outputLayerOutput;
    private double[][] w0;
    private double[][] w1;
    private double[][] w2;
    private OutputViewMediatorInterface mediator;

    /**
     * Initialize a new instance of NetworkMediatorModelState.
     * @param mediator the Network Mediator that Network State will be managed
     * for. 
     */
    public NetworkMediatorModelState(OutputViewMediatorInterface mediator)
    {
        this.mediator = mediator;
        andLayerUpdated = false;
        orLayerUpdated = false;
        outputLayerUpdated = false;

        w0 = new double[0][0];
        w1 = new double[0][0];
        w2 = new double[0][0];
    }

    /**
     * Get the simulation network And Layer Output.
     * @return the output from the And Layer. 
     */
    public double[] getAndLayerOutput()
    {
        return andLayerOutput;
    }

    /**
     * Get the Node output from the entire network.
     * @return a double[] containing the output
     * from the entire network indexed numerically.
     */
    public double[] getNodeOutput()
    {
        ArrayList<Double> list = new ArrayList<Double>();

        list.add((double) 1);
        list.add((double) 1);
        for (int i = 0; i < andLayerOutput.length; i++)
        {
            list.add(andLayerOutput[i]);
        }

        for (int i = 0; i < orLayerOutput.length; i++)
        {
            list.add(orLayerOutput[i]);
        }

        for (int i = 0; i < outputLayerOutput.length; i++)
        {
            list.add(outputLayerOutput[i]);
        }

        double[] nodeResults = new double[list.size()];

        for (int i = 0; i < nodeResults.length; i++)
        {
            nodeResults[i] = (Double) list.get(i);
        }

        return nodeResults;
    }

    /**
     * Get the simulation network's Or Layer Output.
     * @return the output from the Or Layer.
     */
    public double[] getOrLayerOutput()
    {
        return orLayerOutput;
    }

    /**
     * Get the simulation network's Output Layer Output.
     * @return the output from the Output Layer.
     */
    public double[] getOutputLayerOutput()
    {
        return outputLayerOutput;
    }

    /**
     * Get the double[][] representing the W0 Input Model.
     * @return the W0 Input Model.
     */
    public double[][] getW0()
    {
        return w0;
    }

    /**
     * Get the double[][] representing the W1 Input Model.
     * @return the W1 Input Model.
     */
    public double[][] getW1()
    {
        return w1;
    }

    /**
     * Get the double[][] representing the W2 Input Model.
     * @return the W2 Input Model.
     */
    public double[][] getW2()
    {
        return w2;
    }

    /**
     * Check if the graphical network rendering should be animated while
     * the simulation runs.
     * @return boolean indicating if the network should be animated.
     */
    public boolean isAnimated()
    {
        return animated;
    }

    /**
     * Indicate if the graphical network representation should be animated
     * during the simulation.
     * @param animated boolean indicating if the network should be animated.
     */
    public void setAnimated(boolean animated)
    {
        this.animated = animated;
    }

    /**
     * Set the And Layer Output.
     * @param andLayerOutput the And Layer Output
     */
    public void setAndLayerOutput(double[] andLayerOutput)
    {
        this.andLayerOutput = andLayerOutput;
        andLayerUpdated = true;
        stateChanged();
    }

    /**
     * Set the Or Layer Output.
     * @param orLayerOutput the Or Layer Output.
     */
    public void setOrLayerOutput(double[] orLayerOutput)
    {
        this.orLayerOutput = orLayerOutput;
        orLayerUpdated = true;
        stateChanged();
    }

    /**
     * Set the Output Layer Output.
     * @param outputLayerOutput the Output Layer Output.
     */
    public void setOutputLayerOutput(double[] outputLayerOutput)
    {
        this.outputLayerOutput = outputLayerOutput;
        outputLayerUpdated = true;
        stateChanged();
    }

    /**
     * Set the W0 Input Model.
     * @param w0 the W0 Input Model.
     */
    public void setW0(double[][] w0)
    {
        this.w0 = w0;
    }

    /**
     * Set the W1 Input Model.
     * @param w1 the W1 Input Model.
     */
    public void setW1(double[][] w1)
    {
        this.w1 = w1;
    }

    /**
     * Set the W2 Input Model.
     * @param w2 the W2 Input Model.
     */
    public void setW2(double[][] w2)
    {
        this.w2 = w2;
    }

    /**
     * Indicate that the State has been changed and should be verified before
     * being pushed to the Network View via the Network Mediator.
     */
    @Override
    public void stateChanged()
    {
        if (this.andLayerUpdated && this.orLayerUpdated && this.outputLayerUpdated)
        {
            mediator.updateUI();
            resetState();
        }
    }

    /**
     * Reset the State.
     */
    @Override
    public void resetState()
    {
        this.andLayerUpdated = false;
        this.orLayerUpdated = false;
        this.outputLayerUpdated = false;
    }
}
