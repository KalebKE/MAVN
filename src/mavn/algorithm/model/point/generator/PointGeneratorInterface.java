/*
PointGeneratorInterface -- An interface within the Machine Artificial Vision
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

package mavn.algorithm.model.point.generator;

import mavn.algorithm.model.point.Point;


/**
 * Implementations of PointGeneratorInterface define how the random Points for the
 * Multiple Point Simulations are generated.
 * @author Kaleb Kircher
 */
public interface PointGeneratorInterface
{
    /**
     * Implement the random Point generator.
     * @param bounds the X and Y axis bounds.
     * @return X and Y axis Point where the dart landed.
     */
    public Point generatePoint(double bounds);
}
