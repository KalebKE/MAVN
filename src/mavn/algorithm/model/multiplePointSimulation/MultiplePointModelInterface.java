/*
MavnMultiplePointModelInterface --
a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
 * An interface for classes that manages a Multiple Point Simulation. It differs
 * from Single Point Simulations in that it uses a large number of Points to be
 * fired at the shapes in the image image instead of just a single Point.
 * Finding the ratio of Points that hit shapes in the image compares to the
 * Points that missed shapes in the image is the purpose of the simluation.
 *
 * MavnMultiplePointModelInterface is intended to be a Subject in an Observer pattern.
 * Observers can implement MavnMultiplePointAlgorithmModelObserver and hook into
 * MavnMultiplePointModelInterface to be notified when new State is availble.
 *
 * This interface should be implemented by any class that uses PointGeneraterInterface's
 * and needs to push the State to Observers.
 * @author Kaleb
 */
public interface MultiplePointModelInterface
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
