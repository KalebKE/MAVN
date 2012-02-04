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
public class ThetaState implements InputStateInterface
{
    private ControlFrame view;
    private boolean matrixLoaded;

    public ThetaState(ControlFrame view)
    {
        this.view = view;
        matrixLoaded = false;
    }
    
    @Override
    public void matrixLoaded()
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

    @Override
    public void matrixUnloaded()
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

    @Override
    public boolean isMatrixLoaded()
    {
        return matrixLoaded;
    }
}
