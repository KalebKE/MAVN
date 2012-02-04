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
public class W2State implements InputStateInterface
{
    private ControlFrame view;
    private boolean matrixLoaded;

    public W2State(ControlFrame view)
    {
        this.view = view;
        matrixLoaded = false;
    }
    
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

    @Override
    public boolean isMatrixLoaded()
    {
        return matrixLoaded;
    }
}
