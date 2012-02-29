/*
OutputControllerInterface -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.result.controller;

/**
 * An OutputControllerInterface is essentailly concerned with the implementation
 * of access to the MavnAlgorithm and then saving that data to an external file.
 * @author Kaleb Kircher
 */
public interface ModelResultControllerInterface
{

    /**
     * Implementation of the MAVN Algorithm. Implementations
     * should access the classes concerned with initializing
     * the mathmatical models and algorithms that will execute the
     * simulation.
     * Example:
     * {
     * algorithms.get(SIMULATION_IMPLEMENTATION).calculate();
     * }
     */
    public void runSimulation();

    /**
     * Implementations should initialize the classes
     * that will save the results data to an external file. 
     * This can be accomplished by accessing a file controller
     * and requesting a Save File Chooser (usually a JFileChooser)
     * and passing it the results. 
     * Example:
     * {
     * fileController.getSaveFileChooser(results);
     * }
     */
    public void saveResults();
}
