/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.plot.model;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;
import mavn.algorithm.model.point.observer.PointHitAlgorithmModelObserver;
import mavn.plot.model.observer.PointHitOutputModelObserver;

/**
 *
 * @author Kaleb
 */
public class PointHitOutputModel extends PointHitOutputModelInterface implements
        PointHitAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public PointHitOutputModel()
    {
        modelOutputObservers = new ArrayList<PointHitOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(PointHitOutputModelObserver o)
    {
        modelOutputObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(PointHitOutputModelObserver o)
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
    public void notifyPointHitObservers()
    {
        for (int i = 0; i < modelOutputObservers.size(); i++)
        {
            PointHitOutputModelObserver matrixObserver = (PointHitOutputModelObserver) modelOutputObservers.get(i);
            matrixObserver.updatePointHit(hit);
        }
    }

    @Override
    public void updatePointHit(Point point)
    {
        this.setPointHit(point);
    }
}
