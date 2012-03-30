/*
MtRngDartGun -- a class within the Machine Artificial Vision
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
package mavn.simModel.algorithm.model.point.generator;


import mavn.simModel.algorithm.model.point.Point;
import org.uncommons.maths.random.MersenneTwisterRNG;

/**
 * A DartGunInterface implementation that uses MersenneTwisterRNG to generate points.
 *
 * Java port of the fast and reliable Mersenne Twister RNG originally developed
 * by Makoto Matsumoto and Takuji Nishimura. It is faster than java.util.Random,
 * does not have the same statistical flaws as that RNG and also has a long
 * period (219937). The Mersenne Twister is an excellent general purpose RNG.
 * @author Kaleb Kircher
 */
public class MersenneTwisterPointGenerator implements PointGeneratorInterface
{

    private MersenneTwisterRNG mtRng;

    /**
     * Initialize the state.
     */
    public MersenneTwisterPointGenerator()
    {
        mtRng = new MersenneTwisterRNG();
    }

    /**
     * Fire a random dart. Imagine throwing a dart at a
     * two dimensional plane with X and Y axis. This method
     * produces the point where the dart landed by generating
     * two random numbers using the MersenneTwisterRNG.
     * @param bounds the maximum X and Y axis points.
     * @return the X and Y points where the dart landed
     */
    @Override
    public Point fireDart(double bounds)
    {
        Point point = new Point();

        // X: A random double no larger than 5 (the outer bounds of the image)
        // Y: A random double no larger than 5 (the outer bounds of the image)
        point.setLocation(mtRng.nextDouble() * bounds, mtRng.nextDouble() * bounds);

        return point;
    }
}
