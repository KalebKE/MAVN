/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.result.model;

import java.util.ArrayList;
import mavn.simModel.algorithm.observer.OrLayerAlgorithmObserver;
import mavn.simModel.result.model.observer.OrLayerModelObserver;

/**
 *
 * @author Kaleb
 */
public class OrLayerModel extends ModelResultInterface
{
      /**
     * Initialize the state.
     */
    public OrLayerModel()
    {
        modelResultObservers = new ArrayList<OrLayerModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(OrLayerModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(OrLayerModelObserver o)
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
            OrLayerModelObserver matrixObserver = (OrLayerModelObserver) modelResultObservers.get(i);
            matrixObserver.updateOrLayerModelResult(matrix);
        }
    }

    
}
