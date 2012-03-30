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
    private double[] andLayerOutput;
    private double[] orLayerOutput;
    private double[] outputLayerOutput;

    public NetworkMediatorState(OutputViewMediatorInterface controller)
    {
        this.controller = controller;
        andLayerUpdated = false;
        orLayerUpdated = false;
        outputLayerUpdated = false;
    }

    public double[] getAndLayerOutput()
    {
        return andLayerOutput;
    }

    public double[] getNodeOutput()
    {
        ArrayList<Double> list = new ArrayList<Double>();

        list.add((double) 1);
        list.add((double) 1);
        for (int i = 0; i < andLayerOutput.length; i++)
        {
            list.add(andLayerOutput[i]);
        }

        for (int i = 0; i < orLayerOutput.length; i++)
        {
            list.add(orLayerOutput[i]);
        }

        for (int i = 0; i < outputLayerOutput.length; i++)
        {
            list.add(outputLayerOutput[i]);
        }

        double[] nodeResults = new double[list.size()];

        for (int i = 0; i < nodeResults.length; i++)
        {
            nodeResults[i] = (Double) list.get(i);
        }

        return nodeResults;
    }

    public double[] getOrLayerOutput()
    {
        return orLayerOutput;
    }

    public double[] getOutputLayerOutput()
    {
        return outputLayerOutput;
    }

    public void setAndLayerOutput(double[] andLayerResult)
    {
        this.andLayerOutput = andLayerResult;
        andLayerUpdated = true;
        stateChanged();
    }

    public void setOrLayerOutput(double[] orLayerResult)
    {
        this.orLayerOutput = orLayerResult;
        orLayerUpdated = true;
        stateChanged();
    }

    public void setOutputLayerOutput(double[] outputLayerResult)
    {
        this.outputLayerOutput = outputLayerResult;
        outputLayerUpdated = true;
        stateChanged();
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
