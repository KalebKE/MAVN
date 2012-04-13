/*
TimerOutputModel-- a class within the Machine Artificial Vision Network
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
import mavn.algorithm.model.timer.observer.TimerAlgorithmModelObserver;
import mavn.plot.model.observer.PointMissOutputModelObserver;
import mavn.plot.model.observer.TimerOutputModelObserver;

/**
 * The Timer Output Model stores the Timer Points that represent how long it took
 * the simulation to process each pair of network inputs. 
 * @author Kaleb
 */
public class TimerOutputModel extends TimerOutputModelInterface implements
        TimerAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public TimerOutputModel()
    {
        modelOutputObservers = new ArrayList<PointMissOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(TimerOutputModelObserver o)
    {
        modelOutputObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(TimerOutputModelObserver o)
    {
        int i = modelOutputObservers.indexOf(o);
        if (i >= 0)
        {
            modelOutputObservers.remove(i);
        }
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyTimerOutputModelObservers()
    {
        for (int i = 0; i < modelOutputObservers.size(); i++)
        {
            TimerOutputModelObserver matrixObserver = (TimerOutputModelObserver) modelOutputObservers.get(i);
            matrixObserver.updateTimerOutput(timer);
        }
    }

    /**
     * Hook to Observe the Algorithm Model Subject.
     * @param point the Timer Points from the simulation.
     */
    @Override
    public void updateTimerModel(ArrayList<Point> timerPoints)
    {
        this.setTimerOutput(timerPoints);
    }
}
