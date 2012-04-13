/*
MultiplePointModelInterface -- A class within the Machine Artificial Vision
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

package mavn.algorithm.model.multiplePointSimulation;

import mavn.algorithm.model.multiplePointSimulation.observer.MultiplePointAlgorithmModelObserver;

/**
 * An interface for classes that manage a Multiple Point Simulation. It differs
 * from Single Point Simulations in that it uses a large number of Points that are
 * fired at the shapes in the image image instead of only a single Point.
 * Finding the ratio of Points that hit shapes in the image compared to the
 * Points that missed shapes in the image is the general idea of Multiple
 * Point Simulations.
 *
 * MultiplePointSimulationInterface is intended to be a Subject of one or many Observer
 * classes that need to be notified of the simulation's State.
 *
 * @author Kaleb
 */
public interface MultiplePointSimulationInterface
{

    /**
     * Notify Multiple Point Model Observer that new State is available.
     */
    public void notifyMultiplePointModelObserver();

    /**
     * Register an observer.
     * @param o the ResultsObserver
     */
    public void registerObserver(MultiplePointAlgorithmModelObserver o);

    /**
     * Remove an observer.
     * @param o the ResultsObserver
     */
    public void removeObserver(MultiplePointAlgorithmModelObserver o);
}
