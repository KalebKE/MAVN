/*
InputControllerInterface -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

import file.controller.FileControllerInterface;

/**
 * InputControllerInterface implementations are concerned with managing
 * importing data, creating data locally and editing local data. Any class
 * that needs to manage application data should use an implementation of InputControllerInterface. 
 * @author Kaleb
 */
public interface InputControllerInterface
{

    /**
     * Implementation of the classes that allow data to be edited.
     */
    public void editMatrix();

    public FileControllerInterface getFileController();

    /**
     * Implementation of the classes that allow the matrix to be
     * accessed by the rest of the application.
     * @return the matrix.
     */
    public double[][] getModel();

    /**
     * Indicate if the matrix has been set.
     * @return boolean indicating if the matrix has been set.
     */
    public boolean isModelSet();

    /**
     * Implementation of the classes that will import data.
     */
    public void importModel();

    /**
     * Implementation of the classes that allow new data to be created locally.
     */
    public void newModel();

    /**
     * Implementatoin of the classes that allow input data to be saved externally.
     */
    public void saveModel();

    /**
     * Implementation of setting the matrix.
     * @param matrix the matrix to be set.
     */
    public void setModel(double[][] matrix);

    /**
     * Inidcate that a matrix has been set.
     * @param matrixSet boolean indicating if a matrix has been set.
     */
    public void setModelSet(boolean matrixSet);
}
