/*
InputModelState --
A class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.algorithm.model.state.input;

import simulyn.algorithm.model.state.AlgorithmModelStateInterface;

/**
 * AlgorithmModelStateInterfaces can be thought of as State Managers for the
 * AlgorithmModelInterface implementations. It uses a State Pattern to manage
 * the state of AlgorithmModelInterface implementations. If you have a very
 * simple algorithm, implementing this class may not be necessary. However,
 * if you are dealing with a very complex algorithm with a lot of state that is
 * being set, pushed to the Observers and then set again, you will want to
 * implement AlgoirthmModelStateInterface to manage your algorithms state.
 *
 * Input Model State manages the Algorithm Model's local copy of the Input
 * Model State. When all of the required State has been set, the method
 * isInputModelReady() returns true.
 * @author Kaleb
 */
public class InputModelState implements AlgorithmModelStateInterface
{

    private boolean targetUpdated;
    private boolean thetaUpdated;
    private boolean w0Updated;
    private boolean w1Updated;
    private boolean w2Updated;
    private boolean inputModelReady;
    // Algorithm Input State
    private double[][] target;
    private double[][] theta;
    private double[][] w0;
    private double[][] w1;
    private double[][] w2;

    /**
     * Initialize a new instance of Input Model State.
     */
    public InputModelState()
    {
        this.inputModelReady = false;
        this.targetUpdated = false;
        this.thetaUpdated = false;
        this.w0Updated = false;
        this.w1Updated = false;
        this.w2Updated = false;
    }

    /**
     * Get the local copy of the Target Input Model State.
     * @return the local copy of the Target Input Model State.
     */
    public double[][] getTarget()
    {
        return target;
    }

    /**
     * Get the local copy of the Theta Input Model State.
     * @return the local copy of the Theta Input Model State.
     */
    public double[][] getTheta()
    {
        return theta;
    }

    /**
     * Get the local copy of the W0 Input Model State.
     * @return the local copy of the W0 Input Model State.
     */
    public double[][] getW0()
    {
        return w0;
    }

    /**
     * Get the local copy of the W0 Input Model State.
     * @return the local copy of the W0 Input Model State.
     */
    public double[][] getW1()
    {
        return w1;
    }

    /**
     * Get the local copy of the W2 Input Model State.
     * @return the local copy of the W2 Input Model State.
     */
    public double[][] getW2()
    {
        return w2;
    }

    /**
     * Get the state of the Input Model.
     * @return true if all of the Input Model are ready.
     */
    public boolean isInputModelReady()
    {
        return inputModelReady;
    }

    /**
     * Set the local copy of the Target Input Model.
     * @param target the local copy of the Target Input Model
     */
    public void setTarget(double[][] target)
    {
        this.target = target;
        this.targetUpdated = true;
        stateChanged();
    }

    /**
     * Set the local copy of the Theta Input Model.
     * @param theta the local copy of the Theta Input Model
     */
    public void setTheta(double[][] theta)
    {
        this.theta = theta;
        this.thetaUpdated = true;
        stateChanged();
    }

    /**
     * Set the local copy of the W0 Input Model.
     * @param w0 the local copy of the W0 Input Model.
     */
    public void setW0(double[][] w0)
    {
        this.w0 = w0;
        this.w0Updated = true;
        stateChanged();
    }

    /**
     * Set the local copy of the W1 Input Model.
     * @param w1 the local copy of the W1 Input Model.
     */
    public void setW1(double[][] w1)
    {
        this.w1 = w1;
        this.w1Updated = true;
        stateChanged();
    }

    /**
     * Set the local copy of the W2 Input Model.
     * @param w2 the local copy of the W2 Input Model.
     */
    public void setW2(double[][] w2)
    {
        this.w2 = w2;
        this.w2Updated = true;
        stateChanged();
    }

    /**
     * Indicate that the State has changed and check if all of the
     * required Input Models have been set.
     */
    @Override
    public void stateChanged()
    {
        if (this.targetUpdated && this.thetaUpdated && this.w0Updated
                && this.w1Updated && this.w2Updated)
        {
            inputModelReady = true;
        }
    }

    /**
     * Reset the State.
     */
    @Override
    public void resetState()
    {
        this.inputModelReady = false;
        this.targetUpdated = false;
        this.thetaUpdated = false;
        this.w0Updated = false;
        this.w1Updated = false;
        this.w2Updated = false;
    }
}
