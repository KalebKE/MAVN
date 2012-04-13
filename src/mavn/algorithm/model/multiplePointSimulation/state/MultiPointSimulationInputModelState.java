/*
MultiPointInputModelState -- A class within the Machine Artificial Vision
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
package mavn.algorithm.model.multiplePointSimulation.state;

import simulyn.algorithm.model.state.AlgorithmModelStateInterface;

/**
 * AlrogithmModelStateInterfaces are State Managers that use a State Pattern
 * to help Algorithm Model's manage their State and push that State to the
 * Algorithm Model's Subject Obeservers.
 * Some State Managers keep track of when to push State on their own and some
 * need to be notified by their Model. In the latter case, the State Manager
 * still does validation to ensure the Model State has been set.
 *
 * MultiPointSimulationInputModelState manages the State of the Input Model
 * Subjects. It keeps track of what Input Models required for the
 * UniformMultipPointSimulation have been set and which ones have not. When
 * all of the required State has been set, the method isInputModelReady()
 * will return true;
 * @author Kaleb
 */
public class MultiPointSimulationInputModelState implements AlgorithmModelStateInterface
{
    private boolean thetaUpdated;
    private boolean w0Updated;
    private boolean w1Updated;
    private boolean w2Updated;
    private boolean inputModelReady;

    // Algorithm Input State
    private double[][] theta;
    private double[][] w0;
    private double[][] w1;
    private double[][] w2;

    /**
     * Initialize a MultiPointSimulationInputModelState.
     */
    public MultiPointSimulationInputModelState()
    {
        this.inputModelReady = false;
        this.thetaUpdated = false;
        this.w0Updated = false;
        this.w1Updated = false;
        this.w2Updated = false;
    }

    /**
     * Get Theta Input Model.
     * @return the Theta Input Model.
     */
    public double[][] getTheta()
    {
        return theta;
    }

    /**
     * Get the W0 Input Model.
     * @return the W0 Input Model.
     */
    public double[][] getW0()
    {
        return w0;
    }

    /**
     * Get the W1 Input Model.
     * @return the W1 Input Model.
     */
    public double[][] getW1()
    {
        return w1;
    }

    /**
     * Get the W2 Input Model.
     * @return the W2 Input Model.
     */
    public double[][] getW2()
    {
        return w2;
    }

    /**
     * Check if the Simulationj Input Model is ready.
     * @return boolean indicating if the Simulation Input Model is ready.
     */
    public boolean isInputModelReady()
    {
        return inputModelReady;
    }

    /**
     * Set the Theta Input Model.
     * @param theta the Theta Input Model.
     */
    public void setTheta(double[][] theta)
    {
        this.theta = theta;
        this.thetaUpdated = true;
        stateChanged();
    }

    /**
     * Set the W0 Input Model.
     * @param w0 the W0 Input Model.
     */
    public void setW0(double[][] w0)
    {
        this.w0 = w0;
        this.w0Updated = true;
        stateChanged();
    }

    /**
     * Set the W1 Input Model.
     * @param w1 the W1 Input Model.
     */
    public void setW1(double[][] w1)
    {
        this.w1 = w1;
        this.w1Updated = true;
        stateChanged();
    }

    /**
     * Set the W2 Input Model.
     * @param w2 the W2 Input Model.
     */
    public void setW2(double[][] w2)
    {
        this.w2 = w2;
        this.w2Updated = true;
        stateChanged();
    }

    /**
     * Indicate the that State has changed.
     */
    @Override
    public void stateChanged()
    {
        if (this.thetaUpdated && this.w0Updated
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
        this.thetaUpdated = false;
        this.w0Updated = false;
        this.w1Updated = false;
        this.w2Updated = false;
    }
}
