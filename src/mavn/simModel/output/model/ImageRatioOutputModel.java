/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.model;

import java.util.ArrayList;
import mavn.simModel.algorithm.model.point.ImageRatioAlgorithmModelInterface;
import mavn.simModel.algorithm.model.point.observer.ImageRatioAlgorithmModelObserver;
import mavn.simModel.output.model.observer.ImageRatioOutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 *
 * @author Kaleb
 */
public class ImageRatioOutputModel extends OutputModelInterface implements ImageRatioAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public ImageRatioOutputModel()
    {
        modelResultObservers = new ArrayList<ImageRatioOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(ImageRatioOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(ImageRatioOutputModelObserver o)
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
            ImageRatioOutputModelObserver matrixObserver = (ImageRatioOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateImageRatioModelResult(matrix);
        }
    }

    @Override
    public void updateImageRatioModelResult(double[][] imageRatio)
    {
        this.setModelResult(imageRatio);
    }
}
