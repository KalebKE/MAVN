/*
UniformMutliPointOutputModelState --
A class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.algorithm.model.multiplePointSimulation.state;

import java.awt.Point;
import java.util.ArrayList;
import mavn.simModel.algorithm.model.multiplePointSimulation.UniformMultiPointSimulation;
import simulyn.algorithm.model.state.AlgorithmModelStateInterface;

/**
 * AlrogithmModelStateInterfaces are State Managers that use a State Pattern
 * to help Algorithm Model's manage their State and push that State to the
 * Algorithm Model's Subject Obeservers.
 * Some State Managers keep track of when to push State on their own and some
 * need to be notified by their Model. In the latter case, the State Manager
 * still does validation to ensure the Model State has been set.
 * UniformMutliPointOutputModelState is the State Manager for the Points
 * that have been fired at the shapes in the image. It keeps track of
 * how many Points hit shapes and how many Points misses shapes. When all
 * of the required State has been set it notifies the simulation to
 * update the Point Generator Subject Observers.
 * @author Kaleb
 */
public class UniformMutliPointOutputModelState implements AlgorithmModelStateInterface
{
    // Booleans used to keep track of what State has been set.
    private boolean hitResultReady;
    private boolean missResultReady;
    private boolean shapesRatioReady;
    private boolean imageRatioReady;
    // Algorithm Dart Output State
    private ArrayList<Point> hit;
    private ArrayList<Point> miss;
    // Primitive used to put set bounds on the image
    // so the Dart Gun only produces points within the bounds.
    private double bounds;
    // Algorithm Result Output State
    private double[][] shapesRatio;
    private double[][] imageRatio;
    // The Algorithm Model this class manages State for.
    private UniformMultiPointSimulation model;

    /**
     * Initialize a new State Manager for to manage the Ouput Model State for a
     * MAVN Multiple Point Simulation.
     * @param model the MAVNMultiplePointModel that the class will manage state for.
     */
    public UniformMutliPointOutputModelState(UniformMultiPointSimulation model)
    {
        this.model = model;
    }

    /**
     * Return the bounds of the image currently loaded into the Input Model.
     * @return the bounds of the image
     */
    public double getBounds()
    {
        return bounds;
    }

    /**
     * Return the coordinates of the points that hit shapes within the image.
     * @return coordinates of the points that hit shapes within the image
     */
    public ArrayList<Point> getHit()
    {
        return hit;
    }

    /**
     * Return the number of points that hit shapes within the images divided by
     * the number of points fired at the image.
     * @return the ratio pointHits/totalPoints.
     */
    public double[][] getImageRatio()
    {
        return imageRatio;
    }

    /**
     * Return the coordinates of points that missed shapes within the image.
     * @return coordinates of points that missed shapes within the image.
     */
    public ArrayList<Point> getMiss()
    {
        return miss;
    }

    /**
     * Return the ratio of points that hit individual shapes within the image
     * versus the total number of points fired at the image.
     * @return ratio of points that hit individual shapes within the image
     * versus the total number of points fired at the image.
     */
    public double[][] getShapesRatio()
    {
        return shapesRatio;
    }

    /**
     * Set the bounds of the image currently loaded into the Input Model.
     * @param bounds the bounds of the image.
     */
    public void setBounds(double bounds)
    {
        this.bounds = bounds;
    }

    /**
     * Set the coordinates of points that hit shapes within the image.
     * @param hit coordinates of points that hit shapes within the image.
     */
    public void setHit(ArrayList<Point> hit)
    {
        this.hit = hit;
    }

    /**
     * Indicate that all of the coordinates of points that hit shapes within
     * the image have been loaded into a collection and that collection is
     * ready to be pushed to Observers.
     * @param hitResultReady coordinates of points that hit shapes within
     * the image have been loaded into a collection 
     */
    public void setHitResultReady(boolean hitResultReady)
    {
        this.hitResultReady = hitResultReady;
    }

    /**
     * Set the ratio of points that hit shapes within the image versus the
     * number of points fired at the image.
     * @param imageRatio ratio of points that hit shapes within the image versus the
     * number of points fired at the image.
     */
    public void setImageRatio(double[][] imageRatio)
    {
        this.imageRatio = imageRatio;
    }

    /**
     * Indicate that the ratio of points that hit shapes within the image versus
     * the number of points fired at the image has been determined and is
     * ready to be pushed to Observers.
     * @param imageRatioReady ratio of points that hit shapes within the image versus
     * the number of points fired at the image
     */
    public void setImageRatioReady(boolean imageRatioReady)
    {
        this.imageRatioReady = imageRatioReady;
    }

    /**
     * Set the coordinates of points that missed shapes within the image.
     * @param miss coordinates of points that missed shapes within the image.
     */
    public void setMiss(ArrayList<Point> miss)
    {
        this.miss = miss;
    }

    /**
     * Indicate that the coordinates of points that missed shapes within the image
     * have been been determined and the State is ready to push to Observers.
     * @param missResultReady coordinates of points that missed shapes within the image
     * have been been determined.
     */
    public void setMissResultReady(boolean missResultReady)
    {
        this.missResultReady = missResultReady;
    }

    /**
     * Set the ratio of points that hit each individual shape within the image
     * versus the total number of points fired at the image.
     * @param shapesRatio ratio of points that hit each individual shape within the image
     * versus the total number of points fired at the image.
     */
    public void setShapesRatio(double[][] shapesRatio)
    {
        this.shapesRatio = shapesRatio;
    }

    /**
     * Indicate that the
     * @param shapesRatioReady ratio of points that hit each individual shape within the image
     * versus the total number of points fired at the image has been determined
     * and the State is ready to be pushed to Observers.
     */
    public void setShapesRatioReady(boolean shapesRatioReady)
    {
        this.shapesRatioReady = shapesRatioReady;
    }

    /**
     * Indicate that the State has changed and check if the State should
     * be pushed to the Observers.
     */
    @Override
    public void stateChanged()
    {
        if (this.hitResultReady && this.imageRatioReady && this.missResultReady
                && this.shapesRatioReady)
        {
            model.notifyPointGeneratorModelObservers();
            model.notifyImageRatioModelObservers();
            model.notifyShapesRatioModelObservers();
            resetState();
        }
    }

    /**
     * Indicate that the State has been pushed to the Observers and
     * new State needs to be set.
     */
    @Override
    public void resetState()
    {
        this.hitResultReady = false;
        this.imageRatioReady = false;
        this.missResultReady = false;
        this.shapesRatioReady = false;
    }
}
