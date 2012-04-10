/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.spreadsheet.model;

import java.util.ArrayList;
import mavn.algorithm.model.multiplePointSimulation.observer.MultiplePointAlgorithmModelObserver;
import mavn.algorithm.model.singlePointSimulation.observer.SinglePointAlgorithmModelObserver;
import mavn.spreadsheet.model.observer.OutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 *
 * @author Kaleb
 */
public class SimulationOutputModel extends OutputModelInterface implements
        MultiplePointAlgorithmModelObserver, SinglePointAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public SimulationOutputModel()
    {
        modelResultObservers = new ArrayList<OutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(OutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(OutputModelObserver o)
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
        try
        {
            for (int i = 0; i < modelResultObservers.size(); i++)
            {
                OutputModelObserver matrixObserver = (OutputModelObserver) modelResultObservers.get(i);
                matrixObserver.updateModelResult(matrix);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMultiplePointModelResult(double[][] modelResult)
    {
        this.setModelResult(modelResult);
    }

    @Override
    public void updateSinglePointModelOutput(double[][] modelResult)
    {
        this.setModelResult(modelResult);
    }
}
