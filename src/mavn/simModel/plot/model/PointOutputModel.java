/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.plot.model;

import java.awt.Point;
import java.util.ArrayList;
import mavn.simModel.algorithm.model.point.observer.PointGeneratorModelObserver;
import mavn.simModel.plot.model.observer.PointOutputModelObserver;

/**
 *
 * @author Kaleb
 */
public class PointOutputModel extends PointOutputModelInterface implements PointGeneratorModelObserver
{

    /**
     * Initialize the state.
     */
    public PointOutputModel()
    {
        modelResultObservers = new ArrayList<PointOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(PointOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(PointOutputModelObserver o)
    {
        int i = modelResultObservers.indexOf(o);
        if (i >= 0)
        {
            modelResultObservers.remove(i);
        }
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < modelResultObservers.size(); i++)
        {
            PointOutputModelObserver matrixObserver = (PointOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateDartResults(hit, miss);
        }
    }

    @Override
    public void updateDartResults(ArrayList<Point> hit, ArrayList<Point> miss)
    {
        this.setDartResult(hit, miss);
    }
}
