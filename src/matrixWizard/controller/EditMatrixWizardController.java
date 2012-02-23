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

import matrixWizard.model.MatrixWizardModel;
import matrixWizard.model.MatrixWizardModelInterface;
import matrixWizard.view.MatrixTemplateFrame;
import mavn.controller.InputController;
import mavn.controller.InputControllerInterface;
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

    private MatrixTemplateFrame matrixTemplate;
    private MatrixWizardModelInterface matrixWizardModel;
    
    /**
     * Initialize the Controller for the MatrixWizard.
     * @param controller the InputControllerIterface that uses this class.
     */
    public EditMatrixWizardController(InputControllerInterface controller)
    {
        matrixWizardModel = new MatrixWizardModel();
        matrixWizardModel.registerMatrixWizardObserver((InputController)controller);
    }

    @Override
    public void getMatrixWizard(double[][] matrix)
    {
        Transpose transpose = new Transpose();
        matrix = transpose.tranposeMatrix(matrix);
        matrixTemplate = new MatrixTemplateFrame(matrix.length, matrix[0].length, matrixWizardModel);
        matrixTemplate.setMatrix(matrix);
    }

    @Override
    public void getMatrixWizard()
    {
        throw new UnsupportedOperationException("This action is not supported by this class.");
    }
}
