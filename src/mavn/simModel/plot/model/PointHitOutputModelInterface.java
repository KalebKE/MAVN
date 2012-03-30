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
public abstract class PointHitOutputModelInterface
{
    protected boolean pointHitReady;
    protected ArrayList modelOutputObservers;
    protected Point hit;

    public PointHitOutputModelInterface()
    {
        pointHitReady = false;
    }

     /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyPointHitObservers();

    public Point getHit()
    {
        return hit;
    }

    public boolean isPointHitReady()
    {
        return pointHitReady;
    }

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

    public void setPointHitReady(boolean pointHitReady)
    {
        this.pointHitReady = pointHitReady;
    }
}
