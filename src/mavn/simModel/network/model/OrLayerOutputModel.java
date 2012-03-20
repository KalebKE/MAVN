/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.network.model;

import java.util.ArrayList;
import mavn.simModel.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;
import mavn.simModel.network.model.observer.OrLayerOutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 *
 * @author Kaleb
 */
public class OrLayerOutputModel extends OutputModelInterface implements OrLayerAlgorithmModelObserver
{
    /**
     * Initialize the state.
     */
    public OrLayerOutputModel()
    {
        modelResultObservers = new ArrayList<OrLayerOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(OrLayerOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(OrLayerOutputModelObserver o)
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
            OrLayerOutputModelObserver matrixObserver = (OrLayerOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateOrLayerModelResult(matrix);
        }
    }

    @Override
    public void updateOrLayerModelOutput(double[][] orLayerResult)
    {
        this.setModelResult(orLayerResult);
    }
}
