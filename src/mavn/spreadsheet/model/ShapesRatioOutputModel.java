/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.spreadsheet.model;

import java.util.ArrayList;
import mavn.algorithm.model.point.observer.ShapesRatioAlgorithmModelObserver;
import mavn.spreadsheet.model.observer.ShapesRatioOutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 *
 * @author Kaleb
 */
public class ShapesRatioOutputModel extends OutputModelInterface implements ShapesRatioAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public ShapesRatioOutputModel()
    {
        modelResultObservers = new ArrayList<ShapesRatioOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(ShapesRatioOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(ShapesRatioOutputModelObserver o)
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
            ShapesRatioOutputModelObserver matrixObserver = (ShapesRatioOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateShapesRatioModelResult(matrix);
        }
    }

    @Override
    public void updateShapesRatioModelResult(double[][] shapesRatio)
    {
        this.setModelResult(shapesRatio);
    }
}
