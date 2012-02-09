/*
MavenMultiplePointModel -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
Copyright (C) 2012, Kaleb Kircher, Dennis Steele.

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
package mavn.math.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import mavn.controller.InputControllerInterface;
import mavn.dartGun.DartGunInterface;
import mavn.globals.Globals;
import mavn.math.algorithm.CalcTheta;
import mavn.math.algorithm.MatrixMultiply;
import mavn.math.algorithm.SHL;
import mavn.observer.DartsObserver;
import mavn.observer.ResultsObserver;
import mavn.util.math.FindMax;

/**
 * A implementation of MavnAlgorithmModelInterface and DartModelInterface
 * that runs a full simulation. A full simulation consists of firing many
 * random darts in a Monte Carlo type simulation. The number of hits (darts that
 * hit a shape in the image) versus the number of misses (darts that hit nothing)
 * are recorded and made availble to observers using an Observer pattern.
 * @author Kaleb
 */
public class MavnMultiplePointModel implements MavnAlgorithmModelInterface, DartModelInterface
{
    private ArrayList<ResultsObserver> resultObservers;
    private ArrayList<DartsObserver> dartObservers;
    private ArrayList<double[][]> hit;
    private ArrayList<double[][]> miss;
    private DartGunInterface dartGun;
    private ArrayList<InputControllerInterface> controllers;
    private DecimalFormat decimalFormater;
    private String decimalFormat;
    private String results;
    private double bounds;

    /**
     * Initialize a new MavnMultiplePointModel. This class will run a full
     * simulation based on user inputs by firing multiple darts at the image
     * and returning the results.
     * @param controlView the View so the class can pull state
     * @param dartGun the DartGun that will be used to generate the darts
     */
    public MavnMultiplePointModel(ArrayList<InputControllerInterface> controllers, DartGunInterface dartGun)
    {
        this.controllers = controllers;
        this.dartGun = dartGun;
        resultObservers = new ArrayList<ResultsObserver>();
        dartObservers = new ArrayList<DartsObserver>();
        results = "";

        // *This can be overridden by the User Preferances panel!*
        decimalFormat = ("0.0000");

        // Creates a new DecimalFormatter for the text fields so we can
        // can control how many decimal places get printed.
        decimalFormater = new DecimalFormat(decimalFormat);
    }

    /**
     * Calculate the results of the simulation.
     */
    @Override
    public void calculate()
    {
        bounds = FindMax.getMaxValue(this.controllers.get(Globals.THETA_CONTROLLER).getMatrix());
        MatrixMultiply matrixMath = new MatrixMultiply();
        SHL shl = new SHL();
        CalcTheta theta = new CalcTheta();

        // Used to count the number of hits on a individual shape in the image
        int[][] uniqueHits = new int[this.controllers.get(Globals.W1_CONTROLLER).getMatrix().length][1];

        // Used to calculate the ratio of hits to misses on a individual shape in the image
        double[][] uniqueHitsRatio = new double[this.controllers.get(Globals.W1_CONTROLLER).getMatrix().length][1];

        // Used to count the number of hits in a spot where two or more images overlap
        int imageHits = 0;

        // Used to calculate the ratio of hits to misses in a spot where two or more images overlap
        double sharedHitsRatio = 0;

        // The number of darts fired at the image
        double darts = Globals.NUM_DARTS;

        hit = new ArrayList<double[][]>();
        miss = new ArrayList<double[][]>();

        // For loop to fire the darts
        for (int i = 0; i < darts; i++)
        {
            double[][] points = dartGun.fireDart(bounds);

            // SHL[(W2*P)+Theta2]
            double[][] matrix0 = matrixMath.multiply(this.controllers.get(Globals.W2_CONTROLLER).getMatrix(), points);
            double[][] matrix1 = matrixMath.addMatrix(matrix0, this.controllers.get(Globals.THETA_CONTROLLER).getMatrix());
            double[][] matrix2 = shl.calculate(matrix1);

            // SHL[(W1*P)+Theta1]
            double[][] matrix3 = matrixMath.multiply(this.controllers.get(Globals.W1_CONTROLLER).getMatrix(), matrix2);
            double[][] theta1 = theta.calculateAnd(this.controllers.get(Globals.W1_CONTROLLER).getMatrix());
            double[][] matrix4 = matrixMath.addMatrix(matrix3, theta1);
            double[][] matrix5 = shl.calculate(matrix4);

            // Check for unique hits and tally them
            for (int k = 0; k < matrix5.length; k++)
            {
                for (int l = 0; l < matrix5[k].length; l++)
                {
                    if (matrix5[k][l] == 1)
                    {
                        uniqueHits[k][l]++;
                    }
                }
            }

            // SHL[(W0*P)+Theta0]
            double[][] theta2 = theta.calculateOr(this.controllers.get(Globals.W0_CONTROLLER).getMatrix());
            double[][] matrix6 = matrixMath.multiply(this.controllers.get(Globals.W0_CONTROLLER).getMatrix(), matrix5);
            double[][] matrix7 = matrixMath.addMatrix(matrix6, theta2);
            double[][] matrix8 = shl.calculate(matrix7);

            // Check for shared hits and tally them
            for (int k = 0; k < matrix8.length; k++)
            {
                for (int l = 0; l < matrix8[k].length; l++)
                {
                    if (matrix8[k][l] == 1)
                    {
                        imageHits++;
                    }
                }
            }

            if (matrix8[0][0] == 1)
            {
                hit.add(points);
            }
            if (matrix8[0][0] != 1)
            {
                miss.add(points);
            }
        }

        // Calculate the ratio of unique hits
        for (int k = 0; k < uniqueHits.length; k++)
        {
            for (int l = 0; l < uniqueHits[k].length; l++)
            {
                uniqueHitsRatio[k][l] = uniqueHits[k][l] / darts;
            }
        }

        // Calculate the ratio of shared hits
        sharedHitsRatio = imageHits / darts;

        appendResults(uniqueHitsRatio, "Shape (hits/darts):");

        appendResults(sharedHitsRatio, "Image ((sum: shape hits)/darts):");

        notifyObservers();
    }

    /**
     * Register a new Results Observer.
     * @param o the observer.
     */
    @Override
    public void registerObserver(ResultsObserver o)
    {
        resultObservers.add(o);
    }

    /**
     * Remove a Results Observer.
     * @param o the observer.
     */
    @Override
    public void removeObserver(ResultsObserver o)
    {
        int i = resultObservers.indexOf(o);
        if (i >= 0)
        {
            resultObservers.remove(o);
        }
    }

    /**
     * Register a new DartsObserver.
     * @param o the DartsObserver.
     */
    @Override
    public void registerObserver(DartsObserver o)
    {
        dartObservers.add(o);
    }

    /**
     * Remove a DartsObserver.
     * @param o the DartsObserver.
     */
    @Override
    public void removeObserver(DartsObserver o)
    {
        int i = dartObservers.indexOf(o);
        if (i >= 0)
        {
            dartObservers.remove(o);
        }
    }

    /**
     * Notify all Observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < resultObservers.size(); i++)
        {
            ResultsObserver resultObserver = (ResultsObserver) resultObservers.get(i);
            resultObserver.updateResultsMatrix(results);
        }
        for (int i = 0; i < dartObservers.size(); i++)
        {
            DartsObserver dartObserver = (DartsObserver) dartObservers.get(i);
            dartObserver.updateDartResults(hit, miss);
        }
    }

    private void appendResults(double value, String message)
    {
        results += "\n" + message;
        results += "\n" + value;
    }

    private void appendResults(double[][] matrix, String message)
    {
        results += "\n" + message;

        results += "\n";
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                results += decimalFormater.format(matrix[i][j]) + "\t";
            }

            results += "\n";
        }
    }
}

