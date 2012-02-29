/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.result.model;

import java.util.ArrayList;
import mavn.simModel.result.model.observer.ImageRatioModelObserver;

/**
 *
 * @author Kaleb
 */
public class ImageRatioModel extends ModelResultInterface
{

    /**
     * Initialize the state.
     */
    public ImageRatioModel()
    {
        modelResultObservers = new ArrayList<ImageRatioModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(ImageRatioModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(ImageRatioModelObserver o)
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
            ImageRatioModelObserver matrixObserver = (ImageRatioModelObserver) modelResultObservers.get(i);
            matrixObserver.updateImageRatioModelResult(matrix);
        }
    }
}
