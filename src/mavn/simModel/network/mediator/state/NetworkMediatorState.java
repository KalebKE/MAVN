/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.network.mediator.state;

import java.util.ArrayList;
import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class NetworkMediatorState implements MediatorStateInterface
{

    private OutputViewMediatorInterface controller;
    private boolean andLayerUpdated;
    private boolean orLayerUpdated;
    private boolean outputLayerUpdated;
    private double[] andLayerResult;
    private double[] orLayerResult;
    private double[] outputLayerResult;

    public NetworkMediatorState(OutputViewMediatorInterface controller)
    {
        this.controller = controller;
    }

    public double[] getAndLayerResult()
    {
        return andLayerResult;
    }

    public void setAndLayerResult(double[] andLayerResult)
    {
        this.andLayerResult = andLayerResult;
    }

    public double[] getOrLayerResult()
    {
        return orLayerResult;
    }

    public void setOrLayerResult(double[] orLayerResult)
    {
        this.orLayerResult = orLayerResult;
    }

    public double[] getNodeResults()
    {
        ArrayList<Double> list = new ArrayList<Double>();

        list.add((double) 1);
        list.add((double) 1);
        for (int i = 0; i < andLayerResult.length; i++)
        {
            list.add(andLayerResult[i]);
        }

        for (int i = 0; i < orLayerResult.length; i++)
        {
            list.add(orLayerResult[i]);
        }

        for (int i = 0; i < outputLayerResult.length; i++)
        {
            list.add(outputLayerResult[i]);
        }

        double[] nodeResults = new double[list.size()];

        for (int i = 0; i < nodeResults.length; i++)
        {
            nodeResults[i] = (Double) list.get(i);
        }

        return nodeResults;
    }

    public double[] getOutputLayerResult()
    {
        return outputLayerResult;
    }

    public void setOutputLayerResult(double[] outputLayerResult)
    {
        this.outputLayerResult = outputLayerResult;
    }

    @Override
    public void stateChanged()
    {
        if (this.andLayerUpdated && this.orLayerUpdated && this.outputLayerUpdated)
        {
            controller.updateUI();
            resetState();
        }
    }

    @Override
    public void resetState()
    {
        this.andLayerUpdated = false;
        this.orLayerUpdated = false;
        this.outputLayerUpdated = false;
    }
}
