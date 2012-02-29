/*
InputController -- a class within PatternRecognitionSimulationNetwork.
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
package mavn.simModel.input.controller;

import java.util.Iterator;
import mavn.simModel.input.model.ModelInputInterface;
import file.open.controller.OpenFileController;
import file.open.controller.OpenFileControllerInterface;
import file.open.observer.OpenFileObserver;
import file.save.controller.SaveFileControllerInterface;
import file.save.controller.SaveSpreadsheetFileController;

/**
 * A ModelInputFileController (FMIC) is an implementation of the
 * ModelInputFileInterface that is intended to work with the
 * FileManagerFramework (FMF). FMF uses an Observer Pattern to
 * allow observers to be notified when a new file has been opened, read
 * and parsed into an Iterator collection. FMIC observes the FMF and
 * asks FMF for a FileController that delivers a GUI to the user so
 * they can point the application to the desired file.
 *
 * This class is designed as a Controller in a Model-View-Controller
 * architecture. Each instance is intended to control and interface
 * one, and only one, instance of a ModelInput and an ViewInput, respectively.
 * See the ModelInputFileIterface documentation for more information.
 * @see ModelInputFileIterface
 * @author Kaleb
 */
public class ModelInputFileController implements OpenFileObserver,
        ModelInputFileInterface
{
  
    // Instance of the ModelInput that will be used to
    // store and manage the data read from the external file.
    protected ModelInputInterface modelInput;
    // Use lazy instantiation
    protected OpenFileControllerInterface openFileController;
    // Use lazy instantiation
    protected SaveFileControllerInterface saveFileController;

    /**
     * Initialize the class.
     * @param model the model that will be updated when new data is available.
     */
    public ModelInputFileController(ModelInputInterface modelInput)
    {
        this.modelInput = modelInput;
    }

    @Override
    public ModelInputInterface getModelInput()
    {
        return modelInput;
    }

    /**
     * Import a new InputModel from an external file.
     */
    @Override
    public void importModelInput()
    {
        openFileController = new OpenFileController(this);
    }



    /**
     * FileManagerFramework observer hook. This method is called when a
     * new input model is available. 
     * @param itrtr
     */
    @Override
    public void updateModel(Iterator itrtr)
    {
        double[][] data = null;

        while (itrtr.hasNext())
        {
            data = ((double[][]) itrtr.next());
        }

        modelInput.setModelInput(data);
    }

    @Override
    public void exportModelInput()
    {
        System.out.println(modelInput.getModelInput().length);
        saveFileController =
                new SaveSpreadsheetFileController(modelInput.getModelInput());
    }
}
