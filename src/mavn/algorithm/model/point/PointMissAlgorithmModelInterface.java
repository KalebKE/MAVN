/*
PointModelInterface -- 
An Interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.algorithm.model.point;

import mavn.algorithm.model.point.observer.PointMissAlgorithmModelObserver;

/**
 * An interface for classes that create Points for MAVN Multiple Point Simulations.
 * Points are represented by a Java Point containing an X and Y coordinate
 * for a two dimensional plane. These Points are fired at an image containing
 * shapes on the two dimensional plane. The Points are created by a Random Number
 * Generator and are intended to be used in a Monte Carlo type simulation
 * where many random Points are fired at the image and the ratio of points
 * that hit shapes to darts that misses shapes is determined.
 *
 * DartModelInterface is intended to be a Subject in an Observer pattern.
 * Observers can implement PointGeneratorModelObserver to hook into
 * PointModelInterface implementations to be notified when new State is availble.
 *
 * This interface should be implemented by any class that uses PointGeneraterInterface's
 * and needs to push the State to Observers.
 * @author Kaleb
 */
public interface PointMissAlgorithmModelInterface
{
    /**
     * Notify observers.
     */
    public void notifyPointMissModelObservers();

    /**
     * Register a PointGeneratorModelObserver.
     * @param o the PointGeneratorModelObserver
     */
    public void registerObserver(PointMissAlgorithmModelObserver o);

    /**
     * Remove a PointGeneratorModelObserver
     * @param o the PointGeneratorModelObserver
     */
    public void removeObserver(PointMissAlgorithmModelObserver o);
}
