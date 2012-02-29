/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.result.model;

import java.util.ArrayList;
import mavn.simModel.result.model.observer.DartsModelObserver;

/**
 *
 * @author Kaleb
 */
public class DartResult extends DartResultInterface
{
      /**
     * Initialize the state.
     */
    public DartResult()
    {
        modelResultObservers = new ArrayList<DartsModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(DartsModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(DartsModelObserver o)
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
            DartsModelObserver matrixObserver = (DartsModelObserver) modelResultObservers.get(i);
            matrixObserver.updateDartResults(hit,miss);
        }
    }

    
}
