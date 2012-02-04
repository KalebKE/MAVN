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
public class W0State implements InputStateInterface
{
    private ControlFrame view;
    private boolean matrixLoaded;

    public W0State(ControlFrame view)
    {
        this.view = view;
        matrixLoaded = false;
    }
    
    @Override
    public void matrixLoaded()
    {
        // Disable these buttons
        view.getNewW0MatrixButton().setEnabled(false);
        view.getImportW0MatrixButton().setEnabled(false);
        ((BlinkerButton) view.getNewW0MatrixButton()).setBlink(false);
        ((BlinkerButton) view.getImportW0MatrixButton()).setBlink(false);

        // Enable these buttons
        view.getEditW0MatrixButton().setEnabled(true);
        view.getSaveW0MatrixButton().setEnabled(true);
        view.getClearW0MatrixButton().setEnabled(true);
        ((BlinkerButton) view.getEditW0MatrixButton()).setBlink(true);
        ((BlinkerButton) view.getSaveW0MatrixButton()).setBlink(true);
        ((BlinkerButton) view.getClearW0MatrixButton()).setBlink(true);

        matrixLoaded = true;
    }

    @Override
    public void matrixUnloaded()
    {
         // Disable these buttons
        view.getNewW0MatrixButton().setEnabled(true);
        view.getImportW0MatrixButton().setEnabled(true);
        ((BlinkerButton) view.getNewW0MatrixButton()).setBlink(true);
        ((BlinkerButton) view.getImportW0MatrixButton()).setBlink(true);

        // Enable these buttons
        view.getEditW0MatrixButton().setEnabled(false);
        view.getSaveW0MatrixButton().setEnabled(false);
        view.getClearW0MatrixButton().setEnabled(false);
        ((BlinkerButton) view.getEditW0MatrixButton()).setBlink(false);
        ((BlinkerButton) view.getSaveW0MatrixButton()).setBlink(false);
        ((BlinkerButton) view.getClearW0MatrixButton()).setBlink(false);

        matrixLoaded = false;
    }

    @Override
    public boolean isMatrixLoaded()
    {
        return matrixLoaded;
    }
}
