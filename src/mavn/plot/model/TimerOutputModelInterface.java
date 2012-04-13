/*
TimerOutputModelInterface -- an interface within the Machine Artificial Vision Network
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
 * The Timer Output Model stores the Timer Points that represent how long it took
 * the simulation to process each pair of network inputs.
 * @author Kaleb
 */
public abstract class TimerOutputModelInterface
{

    protected boolean timerOutputReady;
    protected ArrayList modelOutputObservers;
    protected ArrayList<Point> timer;

    /**
     * Initialize a new Timer Output Model Interface.
     */
    public TimerOutputModelInterface()
    {
        timerOutputReady = false;
    }

    /**
     * Get the Timer Points from the simulation.
     * @return the Timer Points.
     */
    public ArrayList<Point> getTimer()
    {
        return timer;
    }

    /**
     * Check if the Timer Points are ready to be used by Observers.
     * @return boolean indicating if the Timer Points are ready.
     */
    public boolean isTimerReady()
    {
        return timerOutputReady;
    }

    /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyTimerOutputModelObservers();

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

    /**
     * Indicate that the Timer Points are ready to be used by Observers.
     * @param timerOutputReady
     */
    public void setTimerOutputReady(boolean timerOutputReady)
    {
        this.timerOutputReady = timerOutputReady;
    }
}
