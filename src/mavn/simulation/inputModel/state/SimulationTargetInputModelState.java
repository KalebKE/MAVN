/*
SimulationInputModelState -- a class within the Machine Artificial Vision Network
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
package mavn.simulation.inputModel.state;

import simulyn.output.mediators.state.MediatorStateInterface;

/**
 * A class that manages the state of the Target Input Model for the
 * simulations Output Mediators when Diagnostic Simulations are performed.
 * @author Kaleb
 */
public class SimulationTargetInputModelState implements MediatorStateInterface
{

    private boolean targetInputModelReady;
    private double[][] targetInputModel;

    /**
     * Initialize the SimulationInputModelState class.
     */
    public SimulationTargetInputModelState()
    {
        // The Target Input Model is not ready by default.
        targetInputModelReady = false;
    }

    /**
     * Get the Target Input Model being used by the simulation
     * for the Output Mediators.
     * @return the Target Input Model
     */
    public double[][] getTargetInputModel()
    {
        return targetInputModel;
    }

    /**
     * Check is the Target Input Model has been set and is
     * ready to be used by simulations Output Mediators.
     * @return the state of the Target Input Model
     */
    public boolean isTargetInputModelReady()
    {
        return targetInputModelReady;
    }

    /**
     * Set the Target Input Model so it can be used by the Output Mediators.
     * @param targetInputModel the Target Input Model that should be used
     * by the Ouput Mediators.
     */
    public void setTargetInputModel(double[][] targetInputModel)
    {
        this.targetInputModel = targetInputModel;
        // Verify the input
        stateChanged();
    }

    /**
     * Used to verify the Target Input Model.
     */
    @Override
    public void stateChanged()
    {
        if (targetInputModel.length > 0)
        {
            targetInputModelReady = true;
        } else
        {
            resetState();
        }
    }

    /**
     * Reset the Target Input Model State.
     */
    @Override
    public void resetState()
    {
        targetInputModelReady = false;
    }
}
