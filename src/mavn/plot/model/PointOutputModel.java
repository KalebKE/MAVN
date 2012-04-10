/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.plot.model;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;
import mavn.algorithm.model.point.observer.PointGeneratorAlgorithmModelObserver;
import mavn.plot.model.observer.PointOutputModelObserver;

/**
 *
 * @author Kaleb
 */
public class PointOutputModel extends PointOutputModelInterface implements
        PointGeneratorAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public PointOutputModel()
    {
        modeOutputObservers = new ArrayList<PointOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(PointOutputModelObserver o)
    {
        modeOutputObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(PointOutputModelObserver o)
    {
        int i = modeOutputObservers.indexOf(o);
        if (i >= 0)
        {
            modeOutputObservers.remove(i);
        }
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyPointsObservers()
    {
        for (int i = 0; i < modeOutputObservers.size(); i++)
        {
            PointOutputModelObserver matrixObserver = (PointOutputModelObserver) modeOutputObservers.get(i);
            matrixObserver.updatePoints(hit, miss);
        }
    }

    @Override
    public void updatePoints(ArrayList<Point> hit, ArrayList<Point> miss)
    {
        this.setPointsOutput(hit, miss);
    }
}
