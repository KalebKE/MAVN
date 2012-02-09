/*
EditMatrixWizard -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

package matrixWizard.controller;

import matrixWizard.model.MatrixWizardModelInterface;
import matrixWizard.view.MatrixTemplateFrame;
import mavn.util.math.Transpose;

/**
 * An MatrixWizardControllerInterface implementation for editing matricies. The
 * EditMatrixWizardController class gets the matrix from the Model, allows the user
 * to edit the matrix with a graphical template, and then updates the Model with
 * the new matrix when the user is finished. 
 * @author Kaleb Kircher
 */
public class EditMatrixWizardController implements MatrixWizardControllerInterface
{
    private MatrixWizardModelInterface model;
    private MatrixTemplateFrame matrixTemplate;
    private double[][] matrix;

    /**
     * Initialize the Controller for the MatrixWizard.
     * @param model the model that will be updated with the new matrix
     * @param matrix the matrix that will be updated
     */
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
