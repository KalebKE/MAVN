/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.plot.model;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;
import mavn.algorithm.model.point.observer.PointMissAlgorithmModelObserver;
import mavn.plot.model.observer.PointMissOutputModelObserver;

/**
 *
 * @author Kaleb
 */
public class PointMissOutputModel extends PointMissOutputModelInterface implements
        PointMissAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public PointMissOutputModel()
    {
        modelOutputObservers = new ArrayList<PointMissOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(PointMissOutputModelObserver o)
    {
        modelOutputObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(PointMissOutputModelObserver o)
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
    public void notifyPointMissObservers()
    {
        for (int i = 0; i < modelOutputObservers.size(); i++)
        {
            PointMissOutputModelObserver matrixObserver = (PointMissOutputModelObserver) modelOutputObservers.get(i);
            matrixObserver.updatePointMiss(miss);
        }
    }

    @Override
    public void updatePointMiss(Point point)
    {
        this.setPointMiss(point);
    }
}
