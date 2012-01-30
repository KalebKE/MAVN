/*
 FileController -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package file.controller;

import file.model.FileModelInterface;
import file.view.FileChooserView;

/**
 * An implementation of FileControllerInterface that will help load a file
 * into the application.
 * FileController is a Model class and is part of a MVC architecture. It uses
 * an Observer pattern to notify observers when a new file has been loaded.
 * FileModelInterface implementations define how a file should be parsed
 * and loaded by the Model. An Implementation of FileModelInterface needs to be
 * intitaized, registered with the observer,and then passed into the contructor.
 *
 * @author Kaleb
 */
public class FileController implements FileControllerInterface
{

    private FileModelInterface fileModel;

    /**
     * Initialize the state by passing in an implemenation
     * of FileModelInterface. The implementation tells the Model how to parse
     * the file.
     * @param fileModel
     */
    public FileController(FileModelInterface fileModel)
    {
        this.fileModel = fileModel;
    }

    /**
     * Get a file navigator that selects a file.
     */
    @Override
    public void getFileChooser()
    {
        // Create a file chooser
        final FileChooserView fc = new FileChooserView(this);

        int returnVal = fc.showOpenDialog(fc);
    }

    /**
     * Point the application to the path of the file that defines the Model.
     * @param path
     */
    @Override
    public void setModel(String path)
    {
        fileModel.setModel(path);
    }
}
