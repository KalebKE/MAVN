/*
Controller -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.controller;

import mavn.model.InputModelInterface;
import mavn.view.ControlFrame;
import file.controller.FileController;
import file.controller.FileControllerInterface;
import file.model.CSVFileModel;
import file.model.FileModelInterface;
import file.observer.FileObserver;
import matrixWizard.controller.EditMatrixWizardController;
import matrixWizard.controller.NewMatrixWizardController;
import matrixWizard.controller.MatrixWizardControllerInterface;
import matrixWizard.model.MatrixWizardModel;
import matrixWizard.model.MatrixWizardModelInterface;
import matrixWizard.observer.MatrixWizardObserver;
import mavn.math.model.MavnAlgorithmModelInterface;

/**
 *
 * @author Kaleb
 */
public class InputController implements FileObserver, InputControllerInterface, MatrixWizardObserver
{
    private ControlFrame mainView;
    private FileControllerInterface fileController;
    private FileModelInterface fileModel;
    private InputModelInterface model;
    private MavnAlgorithmModelInterface mavn;
    private MatrixWizardControllerInterface newMatrixWizardController;
    private MatrixWizardControllerInterface editMatrixWizardControler;
    private MatrixWizardModelInterface matrixWizardModel;

    public InputController(ControlFrame mainView, InputModelInterface model)
    {
        this.mainView = mainView;
        this.model = model;
        fileModel = new CSVFileModel();
        fileModel.registerFileObserver(this);
        fileController = new FileController(fileModel);
        matrixWizardModel = new MatrixWizardModel();
        matrixWizardModel.registerMatrixWizardObserver(this);
        newMatrixWizardController = new NewMatrixWizardController(matrixWizardModel);
    }

    @Override
    public void importMatrix()
    {
        fileController.getFileChooser();
    }

    @Override
    public void newMatrix()
    {
        newMatrixWizardController.getMatrixWizard();
    }

    @Override
    public void updateMatrix(double[][] matrix)
    {
        model.setMatrix(matrix);
    }

    @Override
    public void editMatrix(double[][] matrix)
    {
        editMatrixWizardControler = new EditMatrixWizardController(matrixWizardModel, matrix);
        editMatrixWizardControler.getMatrixWizard();
    }
}
