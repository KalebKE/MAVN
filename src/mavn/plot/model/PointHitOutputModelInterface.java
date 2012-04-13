/*
PointHitOutputModelInterface -- an interface within the Machine Artificial Vision Network
(Machine Artificial Vision Network).
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
package mavn.plot.model;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;

/**
 * Point Hit Output Model Interface stores the Output Model State from Points that hit
 * shapes within the image during the simulation. It is both a Observer of
 * the simulation's Algorithm Model and a Subject with its own Observers,
 * typically Output Mediators who render the State and then push it to the View.
 * @author Kaleb
 */
public abstract class PointHitOutputModelInterface
{

    protected boolean pointHitReady;
    protected ArrayList modelOutputObservers;
    protected Point hit;

    /**
     * Initialize a new Point Hit Output Model Interface.
     */
    public PointHitOutputModelInterface()
    {
        pointHitReady = false;
    }

    /**
     * Get the Point that hit a shape during the simulation.
     * @return the Point that hit a shape.
     */
    public Point getHit()
    {
        return hit;
    }

    /**
     * Check if the Point is ready to be used by the Observers.
     * @return
     */
    public boolean isPointHitReady()
    {
        return pointHitReady;
    }

    /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyPointHitObservers();

    /**
     * Set the matrix.
     * @param matrix
     */
    public void setPointHit(Point hit)
    {
        setPointHitReady(true);
        this.hit = hit;
        notifyPointHitObservers();
    }

    /**
     * Indicate that the Point is ready to be used by the Observers.
     * @param pointHitReady
     */
    public void setPointHitReady(boolean pointHitReady)
    {
        this.pointHitReady = pointHitReady;
    }
}
