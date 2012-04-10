/*
JavaRngDartGun -- a class within the Machine Artificial Vision
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
import java.util.Random;

/**
 * A DartGunInterface implementation that uses java.util.random to generate points.
 * It is fast, but fails most tests of randomness. 
 * @author Kaleb Kircher
 */
public class JavaRandomPointGenerator implements PointGeneratorInterface
{

    private Random javaRng;

    /**
     * Initialize the state.
     */
    public JavaRandomPointGenerator()
    {
        javaRng = new Random();
    }

    /**
     * Fire a random dart. Imagine throwing a dart at a
     * two dimensional plane with X and Y axis. This method
     * produces the point where the dart landed by generating
     * two random numbers using the java.util.random RNG.
     * @param bounds the maximum X and Y axis points.
     * @return the X and Y points where the dart landed
     */
    @Override
    public Point fireDart(double bounds)
    {
        Point point = new Point();

        // X: A random double no larger than 5 (the outer bounds of the image)
        // Y: A random double no larger than 5 (the outer bounds of the image)
        point.setLocation(javaRng.nextDouble() * bounds, javaRng.nextDouble() * bounds);

        return point;
    }
}
