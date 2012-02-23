/*
ThetaState -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.state;

import mavn.view.ModelView;
import util.components.BlinkerButton;

/**
 * Implements the View's state control for the Theta Model.
 * This class is an implementation of the applications
 * State Pattern.
 * @author Kaleb
 */
public class ThetaState implements InputStateInterface
{

    private ModelView view;
    private boolean matrixLoaded;

    /**
     * Initialize the state.
     * @param view the View this class is reponsible for.
     */
    public ThetaState(ModelView view)
    {
        this.view = view;
        matrixLoaded = false;
    }

    /**
     * Indicate a Theata Model matrix has been loaded.
     */
    @Override
    public void inputModelLoaded()
    {
        // Disable these buttons
        view.getNewThetaMatrixButton().setEnabled(false);
        view.getImportThetaMatrixButton().setEnabled(false);
        ((BlinkerButton) view.getNewThetaMatrixButton()).setBlink(false);
        ((BlinkerButton) view.getImportThetaMatrixButton()).setBlink(false);

        // Enable these buttons
        view.getEditThetaMatrixButton().setEnabled(true);
        view.getSaveThetaMatrixButton().setEnabled(true);
        view.getClearThetaMatrixButton().setEnabled(true);
        ((BlinkerButton) view.getEditThetaMatrixButton()).setBlink(true);
        ((BlinkerButton) view.getSaveThetaMatrixButton()).setBlink(true);
        ((BlinkerButton) view.getClearThetaMatrixButton()).setBlink(true);

        matrixLoaded = true;
    }

    /**
     * Indicate a Theta Model matrix has been unloaded.
     */
    @Override
    public void inputModelUnloaded()
    {
        // Disable these buttons
        view.getNewThetaMatrixButton().setEnabled(true);
        view.getImportThetaMatrixButton().setEnabled(true);
        ((BlinkerButton) view.getNewThetaMatrixButton()).setBlink(true);
        ((BlinkerButton) view.getImportThetaMatrixButton()).setBlink(true);

        // Enable these buttons
        view.getEditThetaMatrixButton().setEnabled(false);
        view.getSaveThetaMatrixButton().setEnabled(false);
        view.getClearThetaMatrixButton().setEnabled(false);
        ((BlinkerButton) view.getEditThetaMatrixButton()).setBlink(false);
        ((BlinkerButton) view.getSaveThetaMatrixButton()).setBlink(false);
        ((BlinkerButton) view.getClearThetaMatrixButton()).setBlink(false);

        matrixLoaded = false;
    }

    /**
     * Check if a Theta Model matrix has been loaded.
     * @return boolean indicating the Theta Model matrix has been loaded.
     */
    @Override
    public boolean isInputModelLoaded()
    {
        return matrixLoaded;
    }
}
