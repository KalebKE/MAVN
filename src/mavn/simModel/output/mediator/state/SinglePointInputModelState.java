/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.output.mediator.state;

import simulyn.output.mediators.state.MediatorStateInterface;

/**
 *
 * @author Kaleb
 */
public class SinglePointInputModelState implements MediatorStateInterface
{
    private boolean inputModelTargetReady;
    private double[][] inputModelTarget;


    public SinglePointInputModelState()
    {
        inputModelTargetReady = false;
    }

    public boolean isInputModelTargetReady()
    {
        return inputModelTargetReady;
    }

    public double[][] getInputModelTarget()
    {
        return inputModelTarget;
    }

    public void setInputModelTarget(double[][] inputModelTarget)
    {
        this.inputModelTarget = inputModelTarget;
        inputModelTargetReady = true;
    }

    @Override
    public void stateChanged()
    {
        
    }

    @Override
    public void resetState()
    {
        inputModelTargetReady = false;
    }
}
