/*
SSMediatorOutputModelState -- A class within the Machine Artificial Vision
Network(Machine Artificial Vision Network).
Copyright (C) 2012, Kaleb Kircher.

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package mavn.spreadsheet.mediator.state;

import simulyn.output.mediators.state.MediatorStateInterface;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 * SSMediatorOutputModelState manages the State of the Output Model's for the
 * Spreadsheet Mediator.
 * @author Kaleb
 */
public class SSMediatorOutputModelState implements MediatorStateInterface
{

    private OutputViewMediatorInterface controller;
    private boolean imageRatioModelUpdated;
    private boolean shapeRatioModelUpdated;

    /**
     * Initialize a SSMediatorOutputModelState.
     * @param mediator the OutputViewMediatorInterface that the Output Model
     * State will be managed for.
     */
    public SSMediatorOutputModelState(OutputViewMediatorInterface mediator)
    {
        this.controller = mediator;
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
