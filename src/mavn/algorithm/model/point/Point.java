/*
Point -- A interface within the Machine Artificial Vision
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
package mavn.algorithm.model.point;

/**
 * An implementation of Point that works with primitive double values. Note that this
 * class exists because Java Point won't work with primitive double values
 * and will round all of the values off to the nearest whole number. That is
 * essentially a giant fail for what we would expect from a Point, so we have
 * MAVN Point to replace Java Point in the simulations.
 * @author Kaleb
 */
public class Point
{

    private double x;
    private double y;

    /**
     * Initialize a new Point. This is an Overloaded constructor.
     * @param x the X coordinate.
     * @param y the Y coordinate.
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Initialize a new Point. 
     */
    public Point()
    {
        super();
    }

    /**
     * Get the X coordinate.
     * @return the x coordinate.
     */
    public double getX()
    {
        return x;
    }

    /**
     * Get the Y cooordinate.
     * @return the Y cooordinate.
     */
    public double getY()
    {
        return y;
    }

    /**
     * Set the location of the Point.
     * @param x the X coordinate.
     * @param y the Y coordinate.
     */
    public void setLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
}
