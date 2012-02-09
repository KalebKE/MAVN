/*
W0State -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

import mavn.view.MavnView;
import util.components.BlinkerButton;

/**
 * Implements the View's state control for the W2 State.
 * This class is an implementation of the applications
 * State Pattern.
 * @author Kaleb
 */
public class W2State implements InputStateInterface
{

    private MavnView view;
    private boolean matrixLoaded;

    /**
     * Initialize the state.
     * @param view the View the class is responsible for.
     */
    public W2State(MavnView view)
    {
        this.view = view;
        matrixLoaded = false;
    }

    /**
     * Inidcate that a W2 Model matrix has been loaded.
     */
    @Override
    public void matrixLoaded()
    {
        // Disable these buttons
        view.getNewW2MatrixButton().setEnabled(false);
        view.getImportMatrixW2Button().setEnabled(false);
        ((BlinkerButton) view.getNewW2MatrixButton()).setBlink(false);
        ((BlinkerButton) view.getImportMatrixW2Button()).setBlink(false);

        // Enable these buttons
        view.getEditW2MatrixButton().setEnabled(true);
        view.getSaveW2MatrixButton().setEnabled(true);
        view.getClearW2MatrixButton().setEnabled(true);
        ((BlinkerButton) view.getEditW2MatrixButton()).setBlink(true);
        ((BlinkerButton) view.getSaveW2MatrixButton()).setBlink(true);
        ((BlinkerButton) view.getClearW2MatrixButton()).setBlink(true);

        matrixLoaded = true;
    }

    /**
     * Indicate that a W2 Model matrix has been unloaded.
     */
    @Override
    public void matrixUnloaded()
    {
        // Enable these buttons
        view.getNewW2MatrixButton().setEnabled(true);
        view.getImportMatrixW2Button().setEnabled(true);
        ((BlinkerButton) view.getNewW2MatrixButton()).setBlink(true);
        ((BlinkerButton) view.getImportMatrixW2Button()).setBlink(true);

        // Disable these buttons
        view.getEditW2MatrixButton().setEnabled(false);
        view.getSaveW2MatrixButton().setEnabled(false);
        view.getClearW2MatrixButton().setEnabled(false);
        ((BlinkerButton) view.getEditW2MatrixButton()).setBlink(false);
        ((BlinkerButton) view.getSaveW2MatrixButton()).setBlink(false);
        ((BlinkerButton) view.getClearW2MatrixButton()).setBlink(false);

        matrixLoaded = false;
    }

    /**
     * Check if the W1 Model matrix has been loaded.
     * @return boolean indicating if the W1 Model matrix has been loaded.
     */
    @Override
    public boolean isMatrixLoaded()
    {
        return matrixLoaded;
    }
}
