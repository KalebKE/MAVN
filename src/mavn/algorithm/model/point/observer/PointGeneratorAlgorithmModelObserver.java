/*
PointGeneratorAlgorithmModelObserver -- A class within the Machine Artificial
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

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;

/**
 * A hook for classes that want to Observe the Point Generator Algorithm
 * Model Subject. Point Generator Algorithm Models know each Point that
 * hit a shape in the image and each Point that missed a shape in the image.
 * It is updated once when the simulation has finished. State is pushed to the
 * Observer by the Subject.
 * @author Kaleb
 */
public interface PointGeneratorAlgorithmModelObserver
{
    /**
     * Hook for clases to Observe updates to the Point Generator Algorithm Model
     * Subject.
     * @param hitPointList the Points that hit shapes in the image.
     * @param missPointList the Points that missed shapes in the image.
     */
    public void updatePointGeneratorAlgorithmModel(ArrayList<Point> hitPointList, ArrayList<Point> missPointList);
}
