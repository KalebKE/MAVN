/*
OutputStateInterface -- an interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.state;

import java.util.ArrayList;

/**
 * Defines an output framework to manage the applications state using a State Pattern.
 * Implementations are concerned with maintaining the output states of the
 * simluation. Implementations make the application more intuitive to work with.
 * @author Kaleb
 */
public interface OutputStateInterface
{

    /**
     * Get the number and points of darts that hit shapes during the simulation.
     * @return the number of hits.
     */
    public ArrayList<double[][]> getHit();

    /**
     * Get the number of and points of darts that missed shapes during the simulation.
     * @return
     */
    public ArrayList<double[][]> getMiss();

    /**
     * Indicate if the simulation properties have been loaded.
     * @return boolean indicating if simulation properties are loaded.
     */
    public boolean isPropertiesLoaded();

    /**
     * Indicate if the simulation has been loaded.
     * @return boolean indicating if the simulation has been loaded.
     */
    public boolean isSimulationLoaded();

    /**
     * Indicate if the simulation has been run.
     * @return boolean indicating a run simulation.
     */
    public boolean hasRun();

    /**
     * What state should be enabled when a simulation is loaded.
     */
    public void simulationLoaded();

    /**
     * What state should be enabled when a simulation is unloaded.
     */
    public void simulationUnloaded();

    /**
     * What state should be enabled when the simulation properties are loaded.
     */
    public void propertiesLoaded();

    /**
     * What state should be enabled when the simulation properties are unloaded.
     */
    public void propertiesUnloaded();

    /**
     * What state should be enabled when the simulation has been run.
     */
    public void run();

    /**
     * Set the number and points of darts that hit shapes during the simulation.
     * @param hit
     */
    public void setHit(ArrayList<double[][]> hit);

    /**
     * Set the number and points of darts that missed shapes during the simulation.
     * @param miss
     */
    public void setMiss(ArrayList<double[][]> miss);
}
