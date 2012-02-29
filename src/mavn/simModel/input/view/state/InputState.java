/*
TargetState -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.input.view.state;

import mavn.view.input.ModelInputPanelAbstract;
import util.components.BlinkerButton;

/**
 * Implements the View's state control for the Target Model.
 * This class is an implementation of the applications
 * State Pattern.
 * @author Kaleb
 */
public class InputState implements InputStateInterface
{
    private ModelInputPanelAbstract view;
    private boolean matrixLoaded;

    /**
     * Initialize the state.
     * @param view the View the class is responsible for.
     */
    public InputState(ModelInputPanelAbstract view)
    {
        this.view = view;
        matrixLoaded = false;
    }

    /**
     * Indicate that the Target Model matrix has been loaded.
     */
    @Override
    public void inputModelReady(boolean ready)
    {
        if (ready)
        {
            view.getImportModelButton().setEnabled(false);
            ((BlinkerButton) view.getImportModelButton()).setBlink(false);

            // Enable these buttons
            view.getSaveModelButton().setEnabled(true);
            ((BlinkerButton) view.getSaveModelButton()).setBlink(true);

            matrixLoaded = true;
        }
        if (!ready)
        {
            // Disable these buttons
           view.getImportModelButton().setEnabled(true);
            ((BlinkerButton) view.getImportModelButton()).setBlink(true);

            // Enable these buttons
            view.getSaveModelButton().setEnabled(false);
            ((BlinkerButton) view.getSaveModelButton()).setBlink(false);

            matrixLoaded = false;
        }
    }

    /**
     * Check to see if the Target Model matrix has been loaded.
     * @return boolean indicating the Target Model matrix has been loaded.
     */
    @Override
    public boolean isInputModelReady()
    {
        return matrixLoaded;
    }
}
