/*
PointOutputModelInterface -- an interface within the Machine Artificial Vision Network
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
 * Point Output Model Interfaces are responsible for storing the entire Point State
 * after a simulation has run.
 * @author Kaleb
 */
public abstract class PointOutputModelInterface
{

    protected boolean pointsOutputReady;
    protected ArrayList modeOutputObservers;
    protected ArrayList<Point> hit;
    protected ArrayList<Point> miss;

    /**
     * Initialize a new Point Output Model Interface.
     */
    public PointOutputModelInterface()
    {
        pointsOutputReady = false;
    }

    /**
     * Get the Points that hit shapes within the image.
     * @return Points that hit shapes.
     */
    public ArrayList<Point> getHit()
    {
        return hit;
    }

    /**
     * Get the Points that missed shapes within the image.
     * @return Points that missed shapes.
     */
    public ArrayList<Point> getMiss()
    {
        return miss;
    }

    /**
     * Check if the Points have been set and are ready for Observers to use. 
     * @return
     */
    public boolean isPointsReady()
    {
        return pointsOutputReady;
    }

    /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyPointsObservers();

    /**
     * Set the matrix.
     * @param matrix
     */
    public void setPointsOutput(ArrayList<Point> hit, ArrayList<Point> miss)
    {
        setPointsOutputReady(true);
        this.hit = hit;
        this.miss = miss;
        notifyPointsObservers();
    }

    /**
     * Inidicate that the Points are ready to be used by Observers. 
     * @param pointsOutputReady
     */
    public void setPointsOutputReady(boolean pointsOutputReady)
    {
        this.pointsOutputReady = pointsOutputReady;
    }
}
