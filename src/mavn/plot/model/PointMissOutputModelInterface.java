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
 * Point Miss Output Model Interface stores the Output Model State from Points that miss
 * shapes within the image during the simulation. It is both a Observer of
 * the simulation's Algorithm Model and a Subject with its own Observers,
 * typically Output Mediators who render the State and then push it to the View.
 * @author Kaleb
 */
public abstract class PointMissOutputModelInterface
{

    protected boolean pointMissReady;
    protected ArrayList modelOutputObservers;
    protected Point miss;

    /**
     * Initialize a new Point Miss Output Model Interface.
     */
    public PointMissOutputModelInterface()
    {
        pointMissReady = false;
    }

    /**
     * Get the Point that missed a shape in the image.
     * @return the Point that missed a shape in the image.
     */
    public Point getHit()
    {
        return miss;
    }

    /**
     * Check if the Point that missed a shape in the image is ready
     * to be used by the Observers.
     * @return
     */
    public boolean isPointMissReady()
    {
        return pointMissReady;
    }

    /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyPointMissObservers();

    /**
     * Set the matrix.
     * @param matrix
     */
    public void setPointMiss(Point miss)
    {
        setPointMissReady(true);
        this.miss = miss;
        notifyPointMissObservers();
    }

    /**
     * Set the Point that missed a shape in the image.
     * @param pointMissReady
     */
    public void setPointMissReady(boolean pointMissReady)
    {
        this.pointMissReady = pointMissReady;
    }
}
