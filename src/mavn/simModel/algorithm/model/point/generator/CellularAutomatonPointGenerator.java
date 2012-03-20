/*
CellularAutomatonRngDartGun -- a class within the Machine Artificial Vision
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

import java.awt.Point;
import org.uncommons.maths.random.CellularAutomatonRNG;

/**
 * A DartGunInterface implementation that uses Cellular Automaton RNG to generate points.
 * A Java port of Tony Pasqualoni's fast Cellular Automaton RNG.
 * It uses a 256-cell automaton to generate random values.
 * @author Kaleb
 */
public class CellularAutomatonPointGenerator implements PointGeneratorInterface
{

    private CellularAutomatonRNG caRng;

    /**
     * Initialize the state.
     */
    public CellularAutomatonPointGenerator()
    {
        caRng = new CellularAutomatonRNG();
    }

    /**
     * Fire a random dart. Imagine throwing a dart at a
     * two dimensional plane with X and Y axis. This method
     * produces the point where the dart landed by generating
     * two random numbers using the CellularAutomatonRNG.
     * @param bounds the maximum X and Y axis points.
     * @return the X and Y points where the dart landed
     */
    @Override
    public Point fireDart(double bounds)
    {
        Point point = new Point();

        // X: A random double no larger than 5 (the outer bounds of the image)
        // Y: A random double no larger than 5 (the outer bounds of the image)
        point.setLocation(caRng.nextDouble() * bounds, caRng.nextDouble() * bounds);

        return point;
    }
}
