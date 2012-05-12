/*
ImageRatioOutputModel -- A class within the Machine Artificial Vision
Network(Machine Artificial Vision Network).
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
package mavn.spreadsheet.model;

import java.util.ArrayList;
import mavn.algorithm.model.point.observer.ImageRatioAlgorithmModelObserver;
import mavn.spreadsheet.model.observer.ImageRatioOutputModelObserver;
import simulyn.output.model.OutputModelInterface;

/**
 * The Output Model for the simulations Image Ratio. The Image Ratio is the
 * number of Points that landed on shapes within the image divided by the
 * total number of Points that landed on the image. It is both a Subject
 * that is Observed by classes, and a Observer of the Algorithm Model Subject.
 * @author Kaleb
 */
public class ImageRatioOutputModel extends OutputModelInterface implements
        ImageRatioAlgorithmModelObserver
{
    /**
     * Initialize the state.
     */
    public ImageRatioOutputModel()
    {
        modelResultObservers = new ArrayList<ImageRatioOutputModelObserver>();
    }

    /**
     * Register observer.
     * @param o the TargetObserver
     */
    public void registerObserver(ImageRatioOutputModelObserver o)
    {
        modelResultObservers.add(o);
    }

    /**
     * Remove observer.
     * @param o the TargetObserver.
     */
    public void removeObserver(ImageRatioOutputModelObserver o)
    {
        int i = modelResultObservers.indexOf(o);
        if (i >= 0)
        {
            modelResultObservers.remove(i);
        }
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < modelResultObservers.size(); i++)
        {
            ImageRatioOutputModelObserver matrixObserver = (ImageRatioOutputModelObserver) modelResultObservers.get(i);
            matrixObserver.updateImageRatioModelResult(matrix);
        }
    }

    /**
     * The hook to Observe the Image Ratio Algorithm Model Subject.
     * @param imageRatio the number of Points that hit shapes in the
     * image versus the total number of Points that landed on the image.
     */
    @Override
    public void updateImageRatioAlgorithmModel(double[][] imageRatio)
    {
        this.setModelResult(imageRatio);
    }
}
