/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.network.mediator.state;

import java.util.ArrayList;
import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class NetworkMediatorModelState implements MediatorStateInterface
{
    private OutputViewMediatorInterface controller;

    private boolean animated;

    private boolean andLayerUpdated;
    private boolean orLayerUpdated;
    private boolean outputLayerUpdated;

    private double[] andLayerOutput;
    private double[] orLayerOutput;
    private double[] outputLayerOutput;

    private double[][] w0;
    private double[][] w1;
    private double[][] w2;

    public NetworkMediatorModelState(OutputViewMediatorInterface controller)
    {
        this.controller = controller;
        andLayerUpdated = false;
        orLayerUpdated = false;
        outputLayerUpdated = false;

        w0 = new double[0][0];
        w1 = new double[0][0];
        w2 = new double[0][0];
    }

    public boolean isAnimated()
    {
        return animated;
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

    public double[][] getW0()
    {
        return w0;
    }

    public double[][] getW1()
    {
        return w1;
    }

    public double[][] getW2()
    {
        return w2;
    }

    public void setAnimated(boolean animated)
    {
        this.animated = animated;
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

    public void setW0(double[][] w0)
    {
        this.w0 = w0;
    }

    public void setW1(double[][] w1)
    {
        this.w1 = w1;
    }

    public void setW2(double[][] w2)
    {
        this.w2 = w2;
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
