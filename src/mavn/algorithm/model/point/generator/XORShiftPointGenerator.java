/*
XORShiftPointGenerator -- a class within the Machine Artificial Vision
Network(Machine Artificial Vision Network)
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
import org.uncommons.maths.random.XORShiftRNG;

/**
 * A XORShiftPointGenerator implementation that uses XOR Shift RNG to generate points.
 * A Java implementation of the very fast PRNG described by George Marsaglia.
 * It has a period of about 2^160, which although much shorter than the Mersenne
 * Twister's, is still significantly longer than that of java.util.Random.
 * This is the RNG to use when performance is the primary concern. It can be
 * up to twice as fast as the Mersenne Twister.
 * @author Kaleb
 */
public class XORShiftPointGenerator implements PointGeneratorInterface
{

    private XORShiftRNG xOrRng;

    public XORShiftPointGenerator()
    {
        xOrRng = new XORShiftRNG();
    }

    /**
     * Generate a random Point. Imagine throwing a dart at a
     * two dimensional plane with X and Y axis. This method
     * produces the point where the dart landed by generating
     * two random numbers (X and Y) using the XORShiftRNG.
     * @param bounds the maximum X and Y axis points.
     * @return the X and Y Point where the dart landed.
     */
    @Override
    public Point generatePoint(double bounds)
    {
        Point point = new Point();

        // X: A random double no larger than 5 (the outer bounds of the image)
        // Y: A random double no larger than 5 (the outer bounds of the image)
        point.setLocation(xOrRng.nextDouble() * bounds, xOrRng.nextDouble() * bounds);

        return point;
    }
}
