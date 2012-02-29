/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.result.model;

import java.util.ArrayList;
import mavn.simModel.result.model.observer.AndLayerModelObserver;

/**
 *
 * @author Kaleb
 */
public class AndLayerModel extends ModelResultInterface
{
      /**
     * Initialize the state.
     */
    public AndLayerModel()
    {
        modelResultObservers = new ArrayList<AndLayerModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(AndLayerModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(AndLayerModelObserver o)
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
            AndLayerModelObserver matrixObserver = (AndLayerModelObserver) modelResultObservers.get(i);
            matrixObserver.updateAndLayerModelResult(matrix);
        }
    }

    
}
