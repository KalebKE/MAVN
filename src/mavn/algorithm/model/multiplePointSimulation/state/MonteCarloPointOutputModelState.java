/*
MonteCarloPointOutputModelState -- A class within the Machine Artificial
Vision Network(Machine Artificial Vision Network).
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
package mavn.algorithm.model.multiplePointSimulation.state;

import java.util.ArrayList;
import mavn.algorithm.model.multiplePointSimulation.MonteCarloSimulation;
import mavn.algorithm.model.point.Point;
import simulyn.algorithm.model.state.AlgorithmModelStateInterface;

/**
 * MonteCarloPointOutputModelState are State Managers that use a State Pattern
 * to help Algorithm Model's manage their State and push that State to the
 * Algorithm Model's Subject Obeservers.
 *
 * Some State Managers keep track of when to push State on their own and some
 * need to be notified by their Model. In the latter case, the State Manager
 * still does validation to ensure the Model State has been set.
 * 
 * MonteCarloPointOutputModelState is the State Manager for the Points
 * that have been fired at the shapes in the image. It keeps track of
 * how many Points hit shapes and how many Points misses shapes. When all
 * of the required State has been set it notifies the simulation to
 * update the Point Generator Subject Observers.
 * @author Kaleb
 */
public class MonteCarloPointOutputModelState implements AlgorithmModelStateInterface
{
    // Booleans used to keep track of what State has been set.

    private boolean hitResultReady;
    private boolean missResultReady;
    private boolean shapesPointRatioReady;
    private boolean imagePointRatioReady;
    private boolean timerPointsReady;

    // Algorithm Dart Output State
    private ArrayList<Point> hitPointList;
    private ArrayList<Point> missPointList;
    private ArrayList<Point> timerPointList;

    // Primitive used to put set bounds on the image
    // so the Dart Gun only produces points within the bounds.
    private double imageBounds;
    // Algorithm Result Output State
    private double[][] shapesPointRatio;
    private double[][] imagePointRatio;
    // The Algorithm Model this class manages State for.
    private MonteCarloSimulation model;

    /**
     * Initialize a new State Manager for to manage the Ouput Model State for a
     * Monte Carlo Simulation.
     * @param model the Monte Carlo Simulation that the class will manage state for.
     */
    public MonteCarloPointOutputModelState(MonteCarloSimulation model)
    {
        this.model = model;
        hitResultReady = false;
        missResultReady = false;
        timerPointsReady = false;

        hitPointList = new ArrayList<Point>();
        missPointList = new ArrayList<Point>();
        timerPointList = new ArrayList<Point>();
    }

    /**
     * Return the bounds of the image currently loaded into the Input Model.
     * @return the bounds of the image
     */
    public double getImageBounds()
    {
        return imageBounds;
    }

    /**
     * Return the coordinates of the Points that hit shapes within the image.
     * @return coordinates of the Points that hit shapes within the image
     */
    public ArrayList<Point> getHitPointList()
    {
        return hitPointList;
    }

    /**
     * Return the number of Points that hit shapes within the images divided by
     * the number of Points fired at the image.
     * @return the ratio PointHits/TotalPoints.
     */
    public double[][] getImagePointRatio()
    {
        return imagePointRatio;
    }

    /**
     * Return the coordinates of Points that missed shapes within the image.
     * @return coordinates of Points that missed shapes within the image.
     */
    public ArrayList<Point> getMissPointList()
    {
        return missPointList;
    }

    /**
     * Return the ratio of Points that hit individual shapes within the image
     * versus the total number of Points fired at the image.
     * @return ratio of Points that hit individual shapes within the image
     * versus the total number of Points fired at the image.
     */
    public double[][] getShapesPointRatio()
    {
        return shapesPointRatio;
    }

    /**
     * Return the Timer Points. Timer Points indicate how long each input
     * took to run through the network.
     * @return ArrayList<Point> containing Timer Points.
     */
    public ArrayList<Point> getTimerPoints()
    {
        return timerPointList;
    }

    /**
     * Set the bounds of the image currently loaded into the Input Model.
     * @param bounds the bounds of the image.
     */
    public void setImageBounds(double bounds)
    {
        this.imageBounds = bounds;
    }

    /**
     * Set the coordinates of points that hit shapes within the image.
     * @param hit coordinates of points that hit shapes within the image.
     */
    public void setHitPointRatio(ArrayList<Point> hit)
    {
        this.hitPointList = hit;
    }

    /**
     * Indicate that all of the coordinates of points that hit shapes within
     * the image have been loaded into a collection and that collection is
     * ready to be pushed to Observers.
     * @param hitResultReady coordinates of points that hit shapes within
     * the image have been loaded into a collection 
     */
    public void setHitPointOutputReady(boolean hitResultReady)
    {
        this.hitResultReady = hitResultReady;
    }

    /**
     * Set the ratio of points that hit shapes within the image versus the
     * number of points fired at the image.
     * @param imagePointRatio ratio of points that hit shapes within the image versus the
     * number of points fired at the image.
     */
    public void setImagePointRatio(double[][] imagePointRatio)
    {
        this.imagePointRatio = imagePointRatio;
    }

    /**
     * Indicate that the ratio of points that hit shapes within the image versus
     * the number of points fired at the image has been determined and is
     * ready to be pushed to Observers.
     * @param imagePointRatioReady ratio of points that hit shapes within the image versus
     * the number of points fired at the image
     */
    public void setImagePointRatioReady(boolean imagePointRatioReady)
    {
        this.imagePointRatioReady = imagePointRatioReady;
    }

    /**
     * Set the coordinates of points that missed shapes within the image.
     * @param miss coordinates of points that missed shapes within the image.
     */
    public void setMissPointList(ArrayList<Point> miss)
    {
        this.missPointList = miss;
    }

    /**
     * Indicate that the coordinates of points that missed shapes within the image
     * have been been determined and the State is ready to push to Observers.
     * @param missResultReady coordinates of points that missed shapes within the image
     * have been been determined.
     */
    public void setMissPointOutputReady(boolean missResultReady)
    {
        this.missResultReady = missResultReady;
    }

    /**
     * Set the ratio of points that hit each individual shape within the image
     * versus the total number of points fired at the image.
     * @param shapesPointRatio ratio of points that hit each individual shape within the image
     * versus the total number of points fired at the image.
     */
    public void setShapesPointRatio(double[][] shapesPointRatio)
    {
        this.shapesPointRatio = shapesPointRatio;
    }

    /**
     * Indicate that the
     * @param shapesPointRatioReady ratio of points that hit each individual shape within the image
     * versus the total number of points fired at the image has been determined
     * and the State is ready to be pushed to Observers.
     */
    public void setShapesPointRatioReady(boolean shapesPointRatioReady)
    {
        this.shapesPointRatioReady = shapesPointRatioReady;
    }

    public void setTimerPoints(ArrayList<Point> timerPoints)
    {
        this.timerPointList = timerPoints;
    }

    public void setTimerPointsReady(boolean timerPointsReady)
    {
        this.timerPointsReady = timerPointsReady;
    }

    /**
     * Indicate that the State has changed and check if the State should
     * be pushed to the Observers.
     */
    @Override
    public void stateChanged()
    {
        if (this.hitResultReady && this.imagePointRatioReady && this.missResultReady
                && this.shapesPointRatioReady && this.timerPointsReady)
        {
            try
            {
                model.notifyPointGeneratorModelObservers();
                model.notifyImageRatioModelObservers();
                model.notifyShapesRatioModelObservers();
                model.notifyTimerModelObservers();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            resetState();
        }
        if (this.hitResultReady)
        {
            model.notifyPointHitModelObservers();
            this.hitResultReady = false;
        }
        if (this.missResultReady)
        {
            model.notifyPointMissModelObservers();
            this.missResultReady = false;
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
        this.imagePointRatioReady = false;
        this.missResultReady = false;
        this.shapesPointRatioReady = false;
        this.timerPointsReady = false;
    }
}
