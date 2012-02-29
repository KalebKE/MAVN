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
package mavn.simModel.algorithm.model;

import mavn.simModel.algorithm.network.OutputLayerInterface;
import mavn.simModel.algorithm.network.AndLayerInterface;
import mavn.simModel.algorithm.network.OrLayerInterface;
import mavn.simModel.algorithm.model.dart.DartModelInterface;
import java.text.DecimalFormat;
import java.util.ArrayList;
import mavn.simModel.algorithm.observer.ImageRatioAlgorithmObserver;
import mavn.simModel.algorithm.observer.ShapesRatioAlgorithmObserver;
import mavn.simModel.input.controller.ModelInputFileInterface;
import mavn.dartGun.DartGunInterface;
import mavn.globals.Globals;
import mavn.simModel.algorithm.CalcTheta;
import mavn.simModel.algorithm.MatrixMultiply;
import mavn.simModel.algorithm.SHL;
import mavn.simModel.algorithm.network.ImageRatioInterface;
import mavn.simModel.algorithm.network.ShapesRatioInterface;
import mavn.simModel.algorithm.observer.AndLayerAlgorithmObserver;
import mavn.simModel.algorithm.observer.DartsAlgorithmObserver;
import mavn.simModel.algorithm.observer.OrLayerAlgorithmObserver;
import mavn.simModel.algorithm.observer.OutputLayerAlgorithmObserver;
import mavn.simModel.algorithm.observer.ModelResultAlgorithmObserver;
import mavn.util.math.FindMax;

/**
 * A implementation of MavnAlgorithmModelInterface and DartModelInterface
 * that runs a full simulation. A full simulation consists of firing many
 * random darts in a Monte Carlo type simulation. The number of hits (darts that
 * hit a shape in the image) versus the number of misses (darts that hit nothing)
 * are recorded and made availble to observers using an Observer pattern.
 * @author Kaleb
 */
public class MavnMultiplePointModel implements AlgorithmModelInterface,
        DartModelInterface, AndLayerInterface, OrLayerInterface,
        OutputLayerInterface, ShapesRatioInterface, ImageRatioInterface
{

    private ArrayList<ModelResultAlgorithmObserver> resultObservers;
    private ArrayList<DartsAlgorithmObserver> dartObservers;
    private ArrayList<AndLayerAlgorithmObserver> andLayerObservers;
    private ArrayList<OrLayerAlgorithmObserver> orLayerObservers;
    private ArrayList<OutputLayerAlgorithmObserver> outputLayerObservers;
    private ArrayList<ShapesRatioAlgorithmObserver> shapesRatioObservers;
    private ArrayList<ImageRatioAlgorithmObserver> imageRatioObservers;
    private ArrayList<double[][]> hit;
    private ArrayList<double[][]> miss;
    private double[][] andLayerResult;
    private double[][] orLayerResult;
    private double[][] outputLayerResult;
    private double[][] shapesRatio;
    private DartGunInterface dartGun;
    private ArrayList<ModelInputFileInterface> controllers;
    private DecimalFormat decimalFormater;
    private String decimalFormat;
    private String results;
    private double bounds;
    private double[][] imageRatio;

    /**
     * Initialize a new MavnMultiplePointModel. This class will run a full
     * simulation based on user inputs by firing multiple darts at the image
     * and returning the results.
     * @param controlView the View so the class can pull state
     * @param dartGun the DartGun that will be used to generate the darts
     */
    public MavnMultiplePointModel(ArrayList<ModelInputFileInterface> controllers, DartGunInterface dartGun)
    {
        this.controllers = controllers;
        this.dartGun = dartGun;
        resultObservers = new ArrayList<ModelResultAlgorithmObserver>();
        dartObservers = new ArrayList<DartsAlgorithmObserver>();
        andLayerObservers = new ArrayList<AndLayerAlgorithmObserver>();
        orLayerObservers = new ArrayList<OrLayerAlgorithmObserver>();
        outputLayerObservers = new ArrayList<OutputLayerAlgorithmObserver>();
        shapesRatioObservers = new ArrayList<ShapesRatioAlgorithmObserver>();
        imageRatioObservers = new ArrayList<ImageRatioAlgorithmObserver>();
        results = "";

        // *This can be overridden by the User Preferances panel!*
        decimalFormat = ("0.0000");

        // Creates a new DecimalFormatter for the text fields so we can
        // can control how many decimal places get printed.
        decimalFormater = new DecimalFormat(decimalFormat);

        andLayerResult = null;
        orLayerResult = null;
        outputLayerResult = null;
    }

    /**
     * Calculate the results of the simulation.
     */
    @Override
    public void calculate()
    {
        bounds = FindMax.getMaxValue(this.controllers.get(Globals.THETA_CONTROLLER).getModelInput().getModelInput());
        MatrixMultiply matrixMath = new MatrixMultiply();
        SHL shl = new SHL();
        CalcTheta theta = new CalcTheta();

        // Used to count the number of hits on a individual shape in the image
        int[][] uniqueHits = new int[this.controllers.get(Globals.W1_CONTROLLER).getModelInput().getModelInput().length][1];

        // Used to calculate the ratio of hits to misses on a individual shape in the image
        shapesRatio = new double[this.controllers.get(Globals.W1_CONTROLLER).getModelInput().getModelInput().length][1];

        // Used to count the number of hits in a spot where two or more images overlap
        int imageHits = 0;

        // Used to calculate the ratio of hits to misses in a spot where two or more images overlap
        imageRatio = new double[1][1];

        // The number of darts fired at the image
        double darts = Globals.NUM_DARTS;

        hit = new ArrayList<double[][]>();
        miss = new ArrayList<double[][]>();



        // For loop to fire the darts
        for (int i = 0; i < darts; i++)
        {
            try
            {
                double[][] points = dartGun.fireDart(bounds);

                // SHL[(W2*P)+Theta2]
                double[][] matrix0 = matrixMath.multiply(this.controllers.get(Globals.W2_CONTROLLER).getModelInput().getModelInput(), points);
                double[][] matrix1 = matrixMath.addMatrix(matrix0, this.controllers.get(Globals.THETA_CONTROLLER).getModelInput().getModelInput());
                andLayerResult = shl.calculate(matrix1);

                // SHL[(W1*P)+Theta1]
                double[][] matrix3 = matrixMath.multiply(this.controllers.get(Globals.W1_CONTROLLER).getModelInput().getModelInput(), andLayerResult);
                double[][] theta1 = theta.calculateAnd(this.controllers.get(Globals.W1_CONTROLLER).getModelInput().getModelInput());
                double[][] matrix4 = matrixMath.addMatrix(matrix3, theta1);
                orLayerResult = shl.calculate(matrix4);

                // Check for unique hits and tally them
                for (int k = 0; k < orLayerResult.length; k++)
                {
                    for (int l = 0; l < orLayerResult[k].length; l++)
                    {
                        if (orLayerResult[k][l] == 1)
                        {
                            uniqueHits[k][l]++;
                        }
                    }
                }

                // SHL[(W0*P)+Theta0]
                double[][] theta2 = theta.calculateOr(this.controllers.get(Globals.W0_CONTROLLER).getModelInput().getModelInput());
                double[][] matrix6 = matrixMath.multiply(this.controllers.get(Globals.W0_CONTROLLER).getModelInput().getModelInput(), orLayerResult);
                double[][] matrix7 = matrixMath.addMatrix(matrix6, theta2);
                outputLayerResult = shl.calculate(matrix7);

                // Check for shared hits and tally them
                for (int k = 0; k < outputLayerResult.length; k++)
                {
                    for (int l = 0; l < outputLayerResult[k].length; l++)
                    {
                        if (outputLayerResult[k][l] == 1)
                        {
                            imageHits++;
                        }
                    }
                }

                if (outputLayerResult[0][0] == 1)
                {
                    hit.add(points);
                }
                if (outputLayerResult[0][0] != 1)
                {
                    miss.add(points);
                }
                notifyObservers();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        // Calculate the ratio of unique hits
        for (int k = 0; k < uniqueHits.length; k++)
        {
            for (int l = 0; l < uniqueHits[k].length; l++)
            {
                shapesRatio[k][l] = uniqueHits[k][l] / darts;
            }
        }

        // Calculate the ratio of shared hits
        imageRatio[0][0] = imageHits / darts;

        appendResults(shapesRatio, "Shape (hits/darts):");

        appendResults(imageRatio, "Image ((sum: shape hits)/darts):");

        notifyResultObservers();
    }

    /**
     * Register a new Results Observer.
     * @param o the observer.
     */
    @Override
    public void registerObserver(ModelResultAlgorithmObserver o)
    {
        resultObservers.add(o);
    }

    /**
     * Remove a Results Observer.
     * @param o the observer.
     */
    @Override
    public void removeObserver(ModelResultAlgorithmObserver o)
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
    public void registerObserver(DartsAlgorithmObserver o)
    {
        dartObservers.add(o);
    }

    /**
     * Remove a DartsObserver.
     * @param o the DartsObserver.
     */
    @Override
    public void removeObserver(DartsAlgorithmObserver o)
    {
        int i = dartObservers.indexOf(o);
        if (i >= 0)
        {
            dartObservers.remove(o);
        }
    }

    @Override
    public void registerObserver(AndLayerAlgorithmObserver o)
    {
        andLayerObservers.add(o);
    }

    @Override
    public void removeObserver(AndLayerAlgorithmObserver o)
    {
        int i = andLayerObservers.indexOf(o);
        if (i >= 0)
        {
            andLayerObservers.remove(o);
        }
    }

    @Override
    public void registerObserver(OrLayerAlgorithmObserver o)
    {
        orLayerObservers.add(o);
    }

    @Override
    public void removeObserver(OrLayerAlgorithmObserver o)
    {
        int i = orLayerObservers.indexOf(o);
        if (i >= 0)
        {
            orLayerObservers.remove(o);
        }
    }

    @Override
    public void registerObserver(OutputLayerAlgorithmObserver o)
    {
        outputLayerObservers.add(o);
    }

    @Override
    public void removeObserver(OutputLayerAlgorithmObserver o)
    {
        int i = outputLayerObservers.indexOf(o);
        if (i >= 0)
        {
            outputLayerObservers.remove(o);
        }
    }

    @Override
    public void registerObserver(ShapesRatioAlgorithmObserver o)
    {
        shapesRatioObservers.add(o);
    }

    @Override
    public void removeObserver(ShapesRatioAlgorithmObserver o)
    {
        int i = shapesRatioObservers.indexOf(o);
        if (i >= 0)
        {
            shapesRatioObservers.remove(o);
        }
    }

    @Override
    public void registerObserver(ImageRatioAlgorithmObserver o)
    {
        imageRatioObservers.add(o);
    }

    @Override
    public void removeObserver(ImageRatioAlgorithmObserver o)
    {
        int i = imageRatioObservers.indexOf(o);
        if (i >= 0)
        {
            imageRatioObservers.remove(o);
        }
    }

    @Override
    public void notifyResultObservers()
    {
        for (int i = 0; i < dartObservers.size(); i++)
        {
            DartsAlgorithmObserver dartObserver = (DartsAlgorithmObserver) dartObservers.get(i);
            dartObserver.updateDartResults(hit, miss);
        }

        for (int i = 0; i < shapesRatioObservers.size(); i++)
        {
            ShapesRatioAlgorithmObserver resultObserver = (ShapesRatioAlgorithmObserver) shapesRatioObservers.get(i);
            resultObserver.updateShapesRatioModelResult(shapesRatio);
        }
        for (int i = 0; i < imageRatioObservers.size(); i++)
        {
            ImageRatioAlgorithmObserver resultObserver = (ImageRatioAlgorithmObserver) imageRatioObservers.get(i);
            resultObserver.updateImageRatioModelResult(imageRatio);
        }
        for (int i = 0; i < resultObservers.size(); i++)
        {
            ModelResultAlgorithmObserver resultObserver = (ModelResultAlgorithmObserver) resultObservers.get(i);
            resultObserver.updateModelResult(outputLayerResult);
        }
    }

    /**
     * Notify all Observers.
     */
    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < andLayerObservers.size(); i++)
        {
            AndLayerAlgorithmObserver observer = (AndLayerAlgorithmObserver) andLayerObservers.get(i);
            observer.updateAndLayerModelResult(andLayerResult);
        }
        for (int i = 0; i < orLayerObservers.size(); i++)
        {
            OrLayerAlgorithmObserver observer = (OrLayerAlgorithmObserver) orLayerObservers.get(i);
            observer.updateOrLayerModelResult(orLayerResult);
        }
        for (int i = 0; i < outputLayerObservers.size(); i++)
        {
            OutputLayerAlgorithmObserver observer = (OutputLayerAlgorithmObserver) outputLayerObservers.get(i);
            observer.updateOutputLayerModelResult(outputLayerResult);
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

