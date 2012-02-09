/*
InputController -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
import file.controller.FileController;
import file.controller.FileControllerInterface;
import file.model.OpenCSVFileModel;
import file.model.OpenFileModelInterface;
import file.model.SaveCSVFileModel;
import file.model.SaveFileModelInterface;
import file.observer.FileObserver;
import matrixWizard.controller.EditMatrixWizardController;
import matrixWizard.controller.NewMatrixWizardController;
import matrixWizard.controller.MatrixWizardControllerInterface;
import matrixWizard.model.MatrixWizardModel;
import matrixWizard.model.MatrixWizardModelInterface;
import matrixWizard.observer.MatrixWizardObserver;
import mavn.math.model.MavnAlgorithmModelInterface;

/**
 * A special Controller that manages all of the input related to the files
 * that the application works with. It essentially concerned with observing
 * classes that import data from external files and classes that create their own data locally.
 * When an observer is notified of a change, the class provides implementations to foward
 * the data to the rest of the application. 
 *
 * @author Kaleb
 */
public class InputController implements FileObserver, InputControllerInterface, MatrixWizardObserver
{

    private FileControllerInterface fileController;
    private OpenFileModelInterface openFileModel;
    private SaveFileModelInterface saveFileModel;
    private InputModelInterface model;
    private MavnAlgorithmModelInterface mavn;
    private MatrixWizardControllerInterface newMatrixWizardController;
    private MatrixWizardControllerInterface editMatrixWizardControler;
    private MatrixWizardModelInterface matrixWizardModel;
    private boolean matrixSet;
    private double[][] matrix;

    /**
     * Initialize the class.
     * @param model the model that will be updated when new data is available.
     */
    public InputController(InputModelInterface model)
    {
        this.model = model;
        openFileModel = new OpenCSVFileModel();
        openFileModel.registerFileObserver(this);
        saveFileModel = new SaveCSVFileModel();
        fileController = new FileController(openFileModel, saveFileModel);
        matrixWizardModel = new MatrixWizardModel();
        matrixWizardModel.registerMatrixWizardObserver(this);
        newMatrixWizardController = new NewMatrixWizardController(matrixWizardModel);
    }

    @Override
    public void importMatrix()
    {
        fileController.getOpenFileChooser();
    }

    @Override
    public void newMatrix()
    {
        newMatrixWizardController.getMatrixWizard();
    }

    @Override
    public void updateMatrix(double[][] matrix)
    {
        this.matrix = matrix;
        model.setMatrix(matrix);
        this.setMatrixSet(true);
    }

    @Override
    public void editMatrix()
    {
        if (this.isMatrixSet())
        {
            editMatrixWizardControler = new EditMatrixWizardController(matrixWizardModel, this.getMatrix());
            editMatrixWizardControler.getMatrixWizard();
        }
    }

    @Override
    public OpenFileModelInterface getOpenFileModel()
    {
        return openFileModel;
    }

    @Override
    public void saveMatrix()
    {
        if (this.isMatrixSet())
        {
            fileController.getSaveFileChooser(this.getMatrix());
        }
    }

    @Override
    public void setMatrix(double[][] matrix)
    {
        model.setMatrix(matrix);
    }

    @Override
    public double[][] getMatrix()
    {
        return matrix;
    }

    @Override
    public boolean isMatrixSet()
    {
        return matrixSet;
    }

    @Override
    public void setMatrixSet(boolean matrixSet)
    {
        this.matrixSet = matrixSet;
    }
}
