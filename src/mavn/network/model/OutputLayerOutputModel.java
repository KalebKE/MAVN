/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.network.model;

import java.util.ArrayList;
import mavn.algorithm.model.network.OutputLayerAlgorithmModelInterface;
import mavn.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;
import mavn.network.model.observer.OutputLayerOutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 *
 * @author Kaleb
 */
public class OutputLayerOutputModel extends OutputModelInterface implements OutputLayerAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public OutputLayerOutputModel()
    {
        modelResultObservers = new ArrayList<OutputLayerOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(OutputLayerOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(OutputLayerOutputModelObserver o)
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
            OutputLayerOutputModelObserver matrixObserver = (OutputLayerOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateOutputLayerModelResult(matrix);
        }
    }

    @Override
    public void updateOutputLayerModelOutput(double[][] outputLayerResult)
    {
        this.setModelResult(outputLayerResult);
    }
}
