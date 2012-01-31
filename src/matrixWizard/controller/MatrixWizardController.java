/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrixWizard.controller;

import javax.swing.SpinnerNumberModel;
import matrixWizard.model.MatrixWizardModelInterface;
import matrixWizard.view.CreateMatrixWizardFrame;

/**
 *
 * @author Kaleb
 */
public class MatrixWizardController implements MatrixWizardControllerInterface
{
    private MatrixWizardModelInterface model;
    private CreateMatrixWizardFrame view;

    public MatrixWizardController(MatrixWizardModelInterface model)
    {
        this.model = model;
    }

    @Override
    public void getMatrixWizard(SpinnerNumberModel spinnerModel)
    {
        view = new CreateMatrixWizardFrame(model, spinnerModel);
    }

}
