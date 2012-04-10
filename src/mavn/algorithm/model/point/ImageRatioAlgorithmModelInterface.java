/*
ImageRatioAlgorithmModelInterface --
A interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

import mavn.algorithm.model.point.observer.ImageRatioAlgorithmModelObserver;

/**
 * An interface for classes that use MAVN Multiple Point Simulations to
 * produce a large number of Points to be fired at the shapes within an image and
 * then calculate the ratio of Points that hit shapes versus Points that missed
 * shapes.
 * @author Kaleb
 */
public interface ImageRatioAlgorithmModelInterface
{

    /**
     * Register a ImageRatioAlgorithmModelObserver.
     * @param o the ImageRatioAlgorithmModelObserver
     */
    public void registerObserver(ImageRatioAlgorithmModelObserver o);

    /**
     * Remove a ImageRatioAlgorithmModelObserver
     * @param o the ImageRatioAlgorithmModelObserver
     */
    public void removeObserver(ImageRatioAlgorithmModelObserver o);

    /**
     * Notify Image Ratio Algorithm Model Observer.
     */
    public void notifyImageRatioModelObservers();
}
