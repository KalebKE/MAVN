/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.result.model;

import java.util.ArrayList;
import mavn.simModel.result.model.observer.OutputLayerModelObserver;

/**
 *
 * @author Kaleb
 */
public class OutputLayerModel extends ModelResultInterface
{
      /**
     * Initialize the state.
     */
    public OutputLayerModel()
    {
        modelResultObservers = new ArrayList<OutputLayerModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(OutputLayerModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(OutputLayerModelObserver o)
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
            OutputLayerModelObserver matrixObserver = (OutputLayerModelObserver) modelResultObservers.get(i);
            matrixObserver.updateOutputLayerModelResult(matrix);
        }
    }

    
}
