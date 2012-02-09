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

import file.model.OpenFileModelInterface;

/**
 * InputControllerInterface implementations are concerned with managing
 * importing data, creating data locally and editing local data. Any class
 * that needs to manage application data should use an implementation of InputControllerInterface. 
 * @author Kaleb
 */
public interface InputControllerInterface
{

    /**
     * Implementation of the classes that will import data.
     */
    public void importMatrix();

    /**
     * Allow other classes access to the current FileModelInterface
     * that is being used by the application.
     * @return
     */
    public OpenFileModelInterface getOpenFileModel();

    /**
     * Implementation of the classes that allow data to be edited.
     */
    public void editMatrix();

    /**
     * Implementation of the classes that allow new data to be created locally.
     */
    public void newMatrix();

    public void saveMatrix();
   
    public double[][] getMatrix();

    public void setMatrix(double[][] matrix);

    public boolean isMatrixSet();

    public void setMatrixSet(boolean matrixSet);
}
