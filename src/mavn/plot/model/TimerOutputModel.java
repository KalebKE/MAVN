/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.plot.model;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;
import mavn.algorithm.model.timer.observer.TimerAlgorithmModelObserver;
import mavn.plot.model.observer.PointMissOutputModelObserver;
import mavn.plot.model.observer.TimerOutputModelObserver;

/**
 *
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

    @Override
    public void updateTimerModel(ArrayList<Point> timerPoints)
    {
        this.setTimerOutput(timerPoints);
    }
}
