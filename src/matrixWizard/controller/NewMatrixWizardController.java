/*
NewMatrixWizardController -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
import matrixWizard.view.CreateMatrixWizardFrame;
import mavn.controller.InputController;
import mavn.controller.InputControllerInterface;

/**
 * An implementation of MatrixWizardControllerInterface that allows
 * users to create a new matrix. 
 * @author Kaleb
 */
public class NewMatrixWizardController implements MatrixWizardControllerInterface
{
    private MatrixWizardModelInterface matrixWizardModel;

    /**
     * Initialize the NewMatrixWizardController. The class will allow
     * the user to build a new matrix an update the Model with the matrix.
     * @param model the model that will be updated with the new matrix
     */
    public NewMatrixWizardController(InputControllerInterface controller)
    {
        matrixWizardModel = new MatrixWizardModel();
        matrixWizardModel.registerMatrixWizardObserver((InputController)controller);
    }

    @Override
    public void getMatrixWizard()
    {
        CreateMatrixWizardFrame view = new CreateMatrixWizardFrame(matrixWizardModel);
    }

    @Override
    public void getMatrixWizard(double[][] matrix)
    {
        throw new UnsupportedOperationException("This action is not supported!");
    }

}
