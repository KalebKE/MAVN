/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.state;

import mavn.view.ControlFrame;
import util.components.BlinkerButton;

/**
 *
 * @author Kaleb
 */
public class TargetState implements InputStateInterface
{

    private ControlFrame view;
    private boolean matrixLoaded;

    public TargetState(ControlFrame view)
    {
        this.view = view;
        matrixLoaded = false;
    }

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

    @Override
    public boolean isMatrixLoaded()
    {
        return matrixLoaded;
    }
}
