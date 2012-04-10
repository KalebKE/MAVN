/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.plot.model;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;

/**
 *
 * @author Kaleb
 */
public abstract class PointOutputModelInterface
{
    protected boolean pointsOutputReady;
    protected ArrayList modeOutputObservers;
    protected ArrayList<Point> hit;
    protected ArrayList<Point> miss;

    public PointOutputModelInterface()
    {
        pointsOutputReady = false;
    }

     /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyPointsObservers();

    public ArrayList<Point> getHit()
    {
        return hit;
    }

    public ArrayList<Point> getMiss()
    {
        return miss;
    }

    public boolean isPointsReady()
    {
        return pointsOutputReady;
    }

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

    public void setPointsOutputReady(boolean pointsOutputReady)
    {
        this.pointsOutputReady = pointsOutputReady;
    }
}
