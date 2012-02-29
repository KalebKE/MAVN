/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.result.model;

import java.util.ArrayList;

/**
 *
 * @author Kaleb
 */
public abstract class ModelResultInterface
{
    protected boolean resultReady;
    protected ArrayList modelResultObservers;
    protected double[][] matrix;

    public ModelResultInterface()
    {
        resultReady = false;
    }

    /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyObservers();

    public double[][] getModelResult()
    {
        return matrix;
    }

    public boolean isResultReady()
    {
        return resultReady;
    }

    /**
     * Set the matrix.
     * @param matrix
     */
    public void setModelResult(double[][] matrix)
    {
        setResultReady(true);
        this.matrix = matrix;
        notifyObservers();
    }

    public void setResultReady(boolean resultReady)
    {
        this.resultReady = resultReady;
    }
}
