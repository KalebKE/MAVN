/*
ShapesRatioAlgorithmModelInterface --
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
package mavn.algorithm.model.point;

import mavn.algorithm.model.point.observer.ShapesRatioAlgorithmModelObserver;

/**
 * An interface MAVN Simulations that are Subjects for Shape Ratio Algorithm
 * Model's. Shape Ratio Algorithm Model's keep track of the number of Points
 * that hit each shape in the image during the simulation. It then calculates the
 * ratio of Points that hit each shapes versus the total number of Points fired
 * at the image.
 * @author Kaleb
 */
public interface ShapesRatioAlgorithmModelInterface
{

    /**
     * Register a ShapesRatioAlgorithmModelInterface.
     * @param o the ShapesRatioAlgorithmModelInterface
     */
    public void registerObserver(ShapesRatioAlgorithmModelObserver o);

    /**
     * Remove a ShapesRatioAlgorithmModelInterface
     * @param o the ShapesRatioAlgorithmModelInterface
     */
    public void removeObserver(ShapesRatioAlgorithmModelObserver o);

    /**
     * Notify the Shapes Ratio Algorithm Model Interface.
     */
    public void notifyShapesRatioModelObservers();
}
