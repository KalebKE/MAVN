/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrixWizard.controller;

import matrixWizard.model.MatrixWizardModelInterface;
import matrixWizard.view.MatrixTemplateFrame;
import mavn.util.Transpose;

/**
 *
 * @author Kaleb
 */
public class EditMatrixWizardController implements MatrixWizardControllerInterface
{
    private MatrixWizardModelInterface model;
    private MatrixTemplateFrame matrixTemplate;
    private double[][] matrix;

    public EditMatrixWizardController(MatrixWizardModelInterface model, double[][] matrix)
    {
        this.model = model;
        this.matrix = matrix;
        Transpose transpose = new Transpose();
        this.matrix = transpose.tranposeMatrix(matrix);
    }

    @Override
    public void getMatrixWizard()
    {
       matrixTemplate = new MatrixTemplateFrame(matrix.length, matrix[0].length, model);
       matrixTemplate.setMatrix(matrix);
    }
}
