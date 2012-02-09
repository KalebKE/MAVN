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
package mavn.state;

import mavn.view.MavnView;
import util.components.BlinkerButton;

/**
 * Implements the View's state control for the Target Model.
 * This class is an implementation of the applications
 * State Pattern.
 * @author Kaleb
 */
public class TargetState implements InputStateInterface
{

    private MavnView view;
    private boolean matrixLoaded;

    /**
     * Initialize the state.
     * @param view the View the class is responsible for.
     */
    public TargetState(MavnView view)
    {
        this.view = view;
        matrixLoaded = false;
    }

    /**
     * Indicate that the Target Model matrix has been loaded.
     */
    @Override
    public void matrixLoaded()
    {
        // Disable these buttons
        view.getNewTargetMatrixButton().setEnabled(false);
        view.getImportTargetMatrixButton().setEnabled(false);
        ((BlinkerButton) view.getNewTargetMatrixButton()).setBlink(false);
        ((BlinkerButton) view.getImportTargetMatrixButton()).setBlink(false);

        // Enable these buttons
        view.getEditTargetMatrixButton().setEnabled(true);
        view.getSaveTargetMatrixButton().setEnabled(true);
        view.getClearTargetMatrixButton().setEnabled(true);
        ((BlinkerButton) view.getEditTargetMatrixButton()).setBlink(true);
        ((BlinkerButton) view.getSaveTargetMatrixButton()).setBlink(true);
        ((BlinkerButton) view.getClearTargetMatrixButton()).setBlink(true);

        matrixLoaded = true;
    }

    /**
     * Indicate that Target Model matrix has been unloaded.
     */
    @Override
    public void matrixUnloaded()
    {
        // Disable these buttons
        view.getNewTargetMatrixButton().setEnabled(true);
        view.getImportTargetMatrixButton().setEnabled(true);
        ((BlinkerButton) view.getNewTargetMatrixButton()).setBlink(true);
        ((BlinkerButton) view.getImportTargetMatrixButton()).setBlink(true);

        // Enable these buttons
        view.getEditTargetMatrixButton().setEnabled(false);
        view.getSaveTargetMatrixButton().setEnabled(false);
        view.getClearTargetMatrixButton().setEnabled(false);
        ((BlinkerButton) view.getEditTargetMatrixButton()).setBlink(false);
        ((BlinkerButton) view.getSaveTargetMatrixButton()).setBlink(false);
        ((BlinkerButton) view.getClearTargetMatrixButton()).setBlink(false);

        matrixLoaded = false;
    }

    /**
     * Check to see if the Target Model matrix has been loaded.
     * @return boolean indicating the Target Model matrix has been loaded.
     */
    @Override
    public boolean isMatrixLoaded()
    {
        return matrixLoaded;
    }
}
