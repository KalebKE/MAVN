/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.network.model;

import java.util.ArrayList;
import mavn.simModel.algorithm.model.network.AndLayerAlgorithmModelInterface;
import mavn.simModel.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;
import mavn.simModel.network.model.observer.AndLayerOutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 *
 * @author Kaleb
 */
public class AndLayerOutputModel extends OutputModelInterface implements AndLayerAlgorithmModelObserver
{
    /**
     * Initialize the state.
     */
    public AndLayerOutputModel()
    {
        modelResultObservers = new ArrayList<AndLayerOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(AndLayerOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(AndLayerOutputModelObserver o)
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
            AndLayerOutputModelObserver matrixObserver = (AndLayerOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateAndLayerModelResult(matrix);
        }
    }

    @Override
    public void updateAndLayerModelOutput(double[][] andLayerResult)
    {
        this.setModelResult(andLayerResult);
    }
}
