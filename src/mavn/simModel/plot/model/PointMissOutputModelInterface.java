/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.simModel.plot.model;

import java.util.ArrayList;
import mavn.simModel.algorithm.model.point.Point;

/**
 *
 * @author Kaleb
 */
public abstract class PointMissOutputModelInterface
{
    protected boolean pointMissReady;
    protected ArrayList modelOutputObservers;
    protected Point miss;

    public PointMissOutputModelInterface()
    {
        pointMissReady = false;
    }

     /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyPointMissObservers();

    public Point getHit()
    {
        return miss;
    }

    public boolean isPointMissReady()
    {
        return pointMissReady;
    }

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

    public void setPointMissReady(boolean pointMissReady)
    {
        this.pointMissReady = pointMissReady;
    }
}
