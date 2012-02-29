/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.result.model;

import java.util.ArrayList;
import mavn.simModel.result.model.observer.ModelResultModelObserver;

/**
 *
 * @author Kaleb
 */
public class ResultModel extends ModelResultInterface
{

    /**
     * Initialize the state.
     */
    public ResultModel()
    {
        modelResultObservers = new ArrayList<ModelResultModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(ModelResultModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(ModelResultModelObserver o)
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
            ModelResultModelObserver matrixObserver = (ModelResultModelObserver) modelResultObservers.get(i);
            matrixObserver.updateModelResult(matrix);
        }
    }
}
