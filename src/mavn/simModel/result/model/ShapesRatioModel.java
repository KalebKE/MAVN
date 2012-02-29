/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.result.model;

import java.util.ArrayList;
import mavn.simModel.result.model.observer.ShapesRatioModelObserver;

/**
 *
 * @author Kaleb
 */
public class ShapesRatioModel extends ModelResultInterface
{
      /**
     * Initialize the state.
     */
    public ShapesRatioModel()
    {
        modelResultObservers = new ArrayList<ShapesRatioModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(ShapesRatioModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(ShapesRatioModelObserver o)
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
            ShapesRatioModelObserver matrixObserver = (ShapesRatioModelObserver) modelResultObservers.get(i);
            matrixObserver.updateShapesRatioModelResult(matrix);
        }
    }

    
}
