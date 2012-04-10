/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.spreadsheet.mediator.state;

import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class SSMediatorOutputModelState implements MediatorStateInterface
{

    private OutputViewMediatorInterface controller;
    private boolean imageRatioModelUpdated;
    private boolean shapeRatioModelUpdated;

    public SSMediatorOutputModelState(OutputViewMediatorInterface controller)
    {
        this.controller = controller;
        resetState();
    }

    public boolean isImageRatioModelUpdated()
    {
        return imageRatioModelUpdated;
    }

    public boolean isShapeRatioModelUpdated()
    {
        return shapeRatioModelUpdated;
    }

    public void setImageRatioModelUpdated(boolean imageRatioModelUpdated)
    {
        this.imageRatioModelUpdated = imageRatioModelUpdated;
        this.stateChanged();
    }

    public void setShapeRatioModelUpdated(boolean shapeRatioModelUpdated)
    {
        this.shapeRatioModelUpdated = shapeRatioModelUpdated;
        this.stateChanged();
    }

    @Override
    public void stateChanged()
    {
        if (this.imageRatioModelUpdated && this.shapeRatioModelUpdated)
        {  
                controller.updateUI();
                resetState();
        }
    }

    @Override
    public void resetState()
    {
        this.imageRatioModelUpdated = false;
        this.shapeRatioModelUpdated = false;
    }
}
