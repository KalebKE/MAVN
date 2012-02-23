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
import file.observer.FileObserver;
import matrixWizard.controller.EditMatrixWizardController;
import matrixWizard.controller.NewMatrixWizardController;
import matrixWizard.controller.MatrixWizardControllerInterface;
import matrixWizard.observer.MatrixWizardObserver;

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
    private InputModelInterface model;
    private MatrixWizardControllerInterface newMatrixWizardController;
    private MatrixWizardControllerInterface editMatrixWizardControler;
    private boolean matrixSet;
    private double[][] matrix;

    /**
     * Initialize the class.
     * @param model the model that will be updated when new data is available.
     */
    public InputController(InputModelInterface model)
    {
        this.model = model;
        fileController = new FileController();
        newMatrixWizardController = new NewMatrixWizardController(this);
        editMatrixWizardControler = new EditMatrixWizardController(this);
    }

    @Override
    public void editMatrix()
    {
        if (this.isModelSet())
        {
            editMatrixWizardControler.getMatrixWizard(this.getModel());
        }
    }

    @Override
    public FileControllerInterface getFileController()
    {
        return fileController;
    }

    @Override
    public double[][] getModel()
    {
        return matrix;
    }

    @Override
    public boolean isModelSet()
    {
        return matrixSet;
    }

    @Override
    public void importModel()
    {
        fileController.getOpenFileChooser(this);
    }

    @Override
    public void newModel()
    {
        newMatrixWizardController.getMatrixWizard();
    }

    @Override
    public void saveModel()
    {
        if (this.isModelSet())
        {
            fileController.getSaveFileChooser(this.getModel());
        }
    }

    @Override
    public void setModel(double[][] matrix)
    {
        model.setModel(matrix);
    }

    @Override
    public void setModelSet(boolean matrixSet)
    {
        this.matrixSet = matrixSet;
    }

    @Override
    public void updateModel(double[][] matrix)
    {
        this.matrix = matrix;
        model.setModel(matrix);
        this.setModelSet(true);
    }
}
