/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.simModel.result.model;

import java.util.ArrayList;

/**
 *
 * @author Kaleb
 */
public abstract class DartResultInterface
{
    protected boolean dartResultReady;
    protected ArrayList modelResultObservers;
    protected ArrayList<double[][]> hit;
    protected ArrayList<double[][]> miss;

    public DartResultInterface()
    {
        dartResultReady = false;
    }


     /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyObservers();

    public ArrayList<double[][]> getHit()
    {
        return hit;
    }

    public ArrayList<double[][]> getMiss()
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
    public void setDartResult(ArrayList<double[][]> hit, ArrayList<double[][]> miss)
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
