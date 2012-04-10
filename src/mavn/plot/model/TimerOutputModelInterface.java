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
public abstract class TimerOutputModelInterface
{
    protected boolean timerOutputReady;
    protected ArrayList modelOutputObservers;
    protected ArrayList<Point> timer;


    public TimerOutputModelInterface()
    {
        timerOutputReady = false;
    }

     /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyTimerOutputModelObservers();

    public ArrayList<Point> getTimer()
    {
        return timer;
    }

    public boolean isTimerReady()
    {
        return timerOutputReady;
    }

    /**
     * Set the matrix.
     * @param matrix
     */
    public void setTimerOutput(ArrayList<Point> timer)
    {
        setTimerOutputReady(true);
        this.timer = timer;
        notifyTimerOutputModelObservers();
    }

    public void setTimerOutputReady(boolean timerOutputReady)
    {
        this.timerOutputReady = timerOutputReady;
    }
}
