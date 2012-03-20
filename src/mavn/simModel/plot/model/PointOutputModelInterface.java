/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.simModel.plot.model;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Kaleb
 */
public abstract class PointOutputModelInterface
{
    protected boolean dartResultReady;
    protected ArrayList modelResultObservers;
    protected ArrayList<Point> hit;
    protected ArrayList<Point> miss;

    public PointOutputModelInterface()
    {
        dartResultReady = false;
    }

     /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyObservers();

    public ArrayList<Point> getHit()
    {
        return hit;
    }

    public ArrayList<Point> getMiss()
    {
        return miss;
    }

    public boolean isDartResultReady()
    {
        return dartResultReady;
    }

    /**
     * Set the matrix.
     * @param matrix
     */
    public void setDartResult(ArrayList<Point> hit, ArrayList<Point> miss)
    {
        setDartResultReady(true);
        this.hit = hit;
        this.miss = miss;
        notifyObservers();
    }

    public void setDartResultReady(boolean dartResultReady)
    {
        this.dartResultReady = dartResultReady;
    }
}
