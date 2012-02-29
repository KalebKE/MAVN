/*
InputModelInterface -- an interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
Copyright (C) 2012, Kaleb Kircher

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
package mavn.simModel.input.model;

import java.util.ArrayList;

/**
 * An abstract class interface where all of the key implementation is shared by
 * the child classes. Child classes can add special functionality if needed. Input
 * ModelInterface implementations are used to notify observers that new data is available.
 * Views and Models are typical observers. 
 * @author Kaleb
 */
public abstract class ModelInputInterface
{
    // Boolean indicates if a model has been read from an external file
    // and its data is available in an InputModel.

    protected boolean modelReady;
    protected ArrayList modelInputObservers;
    protected double[][] matrix;

    public ModelInputInterface()
    {
        super();

        modelReady = false;
    }

    public double[][] getModelInput()
    {
        return matrix;
    }

    /**
     * Returns a boolean value that indicates if a model has been read from an
     * external file and its data is available in an InputModel.
     * @return
     */
    public boolean isModelInputReady()
    {
        return modelReady;
    }

    /**
     * Notify all Observers that new data is available.
     */
    public abstract void notifyObservers();

    /**
     * Set the matrix.
     * @param matrix
     */
    public void setModelInput(double[][] matrix)
    {
        setModelInputReady(true);
        this.matrix = matrix;
        notifyObservers();
    }

    /**
     * Indicate that a new input model for the simulation has been opened,
     * read and parsed from an external file and is ready to use in an InputModel.
     * @param modelReady
     */
    public void setModelInputReady(boolean modelReady)
    {
        this.modelReady = modelReady;
    }
}
