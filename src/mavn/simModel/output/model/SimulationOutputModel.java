/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.model;

import java.util.ArrayList;
import mavn.simModel.algorithm.model.multiplePointSimulation.MultiplePointModelInterface;
import mavn.simModel.algorithm.model.singlePointSimulation.SinglePointModelInterface;
import mavn.simModel.algorithm.model.multiplePointSimulation.observer.MultiplePointAlgorithmModelObserver;
import mavn.simModel.algorithm.model.singlePointSimulation.observer.SinglePointAlgorithmModelObserver;
import mavn.simModel.output.model.observer.OutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 *
 * @author Kaleb
 */
public class SimulationOutputModel extends OutputModelInterface implements MultiplePointAlgorithmModelObserver, SinglePointAlgorithmModelObserver
{

    /**
     * Initialize the state.
     */
    public SimulationOutputModel(MultiplePointModelInterface multiModel, SinglePointModelInterface singleModel)
    {
        singleModel.registerObserver(this);
        multiModel.registerObserver(this);
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
        for (int i = 0; i < modelResultObservers.size(); i++)
        {
            OutputModelObserver matrixObserver = (OutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateModelResult(matrix);
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
