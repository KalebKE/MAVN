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
public class W1State implements InputStateInterface
{

    private ControlFrame view;
    private boolean matrixLoaded;

    public W1State(ControlFrame view)
    {
        this.view = view;
        matrixLoaded = false;
    }

    @Override
    public void matrixLoaded()
    {
        // Disable these buttons
        view.getNewW1MatrixButton().setEnabled(false);
        view.getImportW1MatrixButton().setEnabled(false);
        ((BlinkerButton) view.getNewW1MatrixButton()).setBlink(false);
        ((BlinkerButton) view.getImportW1MatrixButton()).setBlink(false);

        // Enable these buttons
        view.getEditW1MatrixButton().setEnabled(true);
        view.getSaveW1MatrixButton().setEnabled(true);
        view.getClearW1MatrixButton().setEnabled(true);
        ((BlinkerButton) view.getEditW1MatrixButton()).setBlink(true);
        ((BlinkerButton) view.getSaveW1MatrixButton()).setBlink(true);
        ((BlinkerButton) view.getClearW1MatrixButton()).setBlink(true);

        matrixLoaded = true;
    }

    @Override
    public void matrixUnloaded()
    {
        // Disable these buttons
        view.getNewW1MatrixButton().setEnabled(true);
        view.getImportW1MatrixButton().setEnabled(true);
        ((BlinkerButton) view.getNewW1MatrixButton()).setBlink(true);
        ((BlinkerButton) view.getImportW1MatrixButton()).setBlink(true);

        // Enable these buttons
        view.getEditW1MatrixButton().setEnabled(false);
        view.getSaveW1MatrixButton().setEnabled(false);
        view.getClearW1MatrixButton().setEnabled(false);
        ((BlinkerButton) view.getEditW1MatrixButton()).setBlink(false);
        ((BlinkerButton) view.getSaveW1MatrixButton()).setBlink(false);
        ((BlinkerButton) view.getClearW1MatrixButton()).setBlink(false);

        matrixLoaded = false;
    }

    @Override
    public boolean isMatrixLoaded()
    {
        return matrixLoaded;
    }
}
