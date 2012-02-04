/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrixWizard.controller;

import matrixWizard.model.MatrixWizardModelInterface;
import matrixWizard.view.CreateMatrixWizardFrame;

/**
 *
 * @author Kaleb
 */
public class NewMatrixWizardController implements MatrixWizardControllerInterface
{
    private MatrixWizardModelInterface model;
    private CreateMatrixWizardFrame view;

    public NewMatrixWizardController(MatrixWizardModelInterface model)
    {
        this.model = model;
    }

    @Override
    public void getMatrixWizard()
    {
        view = new CreateMatrixWizardFrame(model);
    }

}
