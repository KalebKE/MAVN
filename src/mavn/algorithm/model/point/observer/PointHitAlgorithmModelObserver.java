/*
PointHitAlgorithmModelObserver -- A class within the Machine Artificial
Vision Network(Machine Artificial Vision Network).
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
package mavn.algorithm.model.point.observer;

import mavn.algorithm.model.point.Point;

/**
 * A hook for classes that want to Observe the Point Hit Algorithm
 * Model Subject. Point Hit Algorithm Models know each Point that
 * hit a shape in the image.
 * It is updated whenever a Point hits a shape in the image. State is pushed to the
 * Observer by the Subject.
 * @author Kaleb
 */
public interface PointHitAlgorithmModelObserver
{

    /**
     * Hook for clases to Observe updates to the Point Hit Algorithm Model
     * Subject.
     * @param pointHit the Point that hit a shape in the image.
     */
    public void updatePointHitAlgorithmModel(Point pointHit);
}
