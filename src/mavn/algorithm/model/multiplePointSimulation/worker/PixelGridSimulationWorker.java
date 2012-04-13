/*
PixelGridSimulationWorker -- A class within the Machine Artificial
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
package mavn.algorithm.model.multiplePointSimulation.worker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import mavn.globals.Globals;
import mavn.algorithm.model.math.CalcTheta;
import mavn.algorithm.model.math.MatrixMultiply;
import mavn.algorithm.model.math.SHL;
import mavn.algorithm.model.multiplePointSimulation.state.PixelGridPointOutputModelState;
import mavn.algorithm.model.multiplePointSimulation.state.MultiPointSimulationInputModelState;
import mavn.algorithm.model.state.output.NetworkLayerOutputModelStateAbstract;
import mavn.algorithm.model.point.Point;
import mavn.algorithm.model.timer.Timer;
import mavn.util.math.FindMax;

/**
 * PixelSimulationWorker is a part of a Command Pattern that spawns off
 * a new thread to perform the simulation's calculations. This prevents the
 * GUI from hanging while the simulation runs and significantly improves
 * performance. A Swing Worker is used to spawn the new thread. Note that each
 * instance is intended to be used only once. Every simulation will require
 * a new instance of the class.
 * @author Kaleb
 */
public class PixelGridSimulationWorker extends SwingWorker
{

    private MultiPointSimulationInputModelState inputModelState;
    private NetworkLayerOutputModelStateAbstract outputLayerModelState;
    private PixelGridPointOutputModelState outputModelResultState;
    // Objects for debugging and Exception outputs
    private DecimalFormat decimalFormater;
    private String decimalFormat;
    private String results;
    private int bounds;
    private int resolution;
    private int numPoints;
    private Timer timer;

    /**
     * Initialize a new PixelGridSimulationWorker.
     * @param inputModelState the State of the simulations Input Models.
     * @param outputLayerModelState the Output State for the Network Layers.
     * @param outputModelResultState the Output State for the Simulation.
     */
    public PixelGridSimulationWorker(
            MultiPointSimulationInputModelState inputModelState,
            NetworkLayerOutputModelStateAbstract outputLayerModelState,
            PixelGridPointOutputModelState outputModelResultState)
    {
        this.inputModelState = inputModelState;
        this.outputLayerModelState = outputLayerModelState;
        this.outputModelResultState = outputModelResultState;

        // For debug and Exception
        results = "";
        decimalFormat = ("0.0000");
        decimalFormater = new DecimalFormat(decimalFormat);

        timer = new Timer();
    }

    /**
     * Run the simulation.
     * @return null
     * @throws Exception
     */
    @Override
    protected Object doInBackground() throws Exception
    {
        outputModelResultState.setBounds(FindMax.getMaxValue(this.inputModelState.getTheta()));
        resolution = Globals.RESOLUTION;
        bounds = (int) this.outputModelResultState.getBounds();

        MatrixMultiply matrixMath = new MatrixMultiply();
        SHL shl = new SHL();
        CalcTheta theta = new CalcTheta();

        // Used to count the number of hits on a individual shape in the image
        int[][] shapeHits = new int[this.inputModelState.getW1().length][1];

        // Used to calculate the ratio of hits to misses on a individual shape in the image
        outputModelResultState.setShapesRatio(new double[this.inputModelState.getW1().length][1]);

        // Used to count the number of hits in a spot where two or more images overlap
        int imageHits = 0;

        // Used to calculate the ratio of hits to misses in a spot where two or more images overlap
        outputModelResultState.setImageRatio(new double[1][1]);

        //
        outputModelResultState.setHit(new ArrayList<Point>());
        outputModelResultState.setMiss(new ArrayList<Point>());

        Point[] pointArray = this.getPoints();

        // For loop to fire the darts. There are two different Subjects
        // that are being Observed by other classes within this loop.
        // The first Subject is the Network Layer Output Subject that updates its
        // Observers every time a new dart is fired. It knows what nodes went high
        // and what nodes stayed low after every dart. It keeps track of each
        // new dart being fired and updates the Observers automatically.
        // The second Subject is the Network Result Output Subject that updates
        // its Observers one time after all of the darts have been fired. It knows
        // the locations of all the darts that were fired, what darts hit and
        // missed shapes, and the ratios of hits/misses in each shape within
        // the image and the image itself. It needs to be told when its state
        // has been changed and the State pushed to the Observers.
        for (int i = 0; i < pointArray.length; i++)
        {
            try
            {
                timer.start();
                // Convert that point into a double array so the algorithm can work with it.
                double[][] points =
                {
                    {
                        pointArray[i].getX()
                    },
                    {
                        pointArray[i].getY()
                    }
                };

                // SHL[(W2*P)+Theta2]
                double[][] matrix0 = matrixMath.multiply(this.inputModelState.getW2(), points);
                double[][] matrix1 = matrixMath.addMatrix(matrix0, this.inputModelState.getTheta());
                // Update the Ouput Layer Model State Manager.
                // The State Manager keeps track of all the Network Layer's
                // State during the simulation. It updates the Observers
                // automatically.
                outputLayerModelState.setAndLayerOutput(shl.calculate(matrix1));

                // SHL[(W1*P)+Theta1]
                double[][] matrix3 = matrixMath.multiply(this.inputModelState.getW1(), outputLayerModelState.getAndLayerOutput());
                double[][] theta1 = theta.calculateAnd(this.inputModelState.getW1());
                double[][] matrix4 = matrixMath.addMatrix(matrix3, theta1);
                // Update the Ouput Layer Model State Manager.
                // The State Manager keeps track of all the Network Layer's
                // State during the simulation. It updates the Observers
                // automatically.
                outputLayerModelState.setOrLayerOutput(shl.calculate(matrix4));

                // SHL[(W0*P)+Theta0]
                double[][] theta2 = theta.calculateOr(this.inputModelState.getW0());
                double[][] matrix6 = matrixMath.multiply(this.inputModelState.getW0(), outputLayerModelState.getOrLayerOutput());
                double[][] matrix7 = matrixMath.addMatrix(matrix6, theta2);
                // Update the Ouput Layer Model State Manager.
                // The State Manager keeps track of all the Network Layer's
                // State during the simulation. It updates the Observers
                // automatically.
                outputLayerModelState.setOutputLayerResult(shl.calculate(matrix7));

                timer.stop();

                if (i == 0)
                {
                    outputModelResultState.getTimerPoints().add(new Point(i, timer.getElapsedTimeSecs()));
                } else
                {
                    outputModelResultState.getTimerPoints().add(new Point(i, timer.getElapsedTimeSecs() + outputModelResultState.getTimerPoints().get(i - 1).getY()));
                }

                // Check for unique hits and tally them
                for (int k = 0; k < outputLayerModelState.getOrLayerOutput().length; k++)
                {
                    for (int l = 0; l < outputLayerModelState.getOrLayerOutput()[k].length; l++)
                    {
                        // If the result is a 1, then hit! Increment the hits.
                        if (outputLayerModelState.getOrLayerOutput()[k][l] == 1)
                        {
                            shapeHits[k][l]++;
                        }
                    }
                }

                // Check for shared hits and tally them
                for (int k = 0; k < outputLayerModelState.getOutputLayerOutput().length; k++)
                {
                    for (int l = 0; l < outputLayerModelState.getOutputLayerOutput()[k].length; l++)
                    {
                        // If the result is a 1, then hit! Increment the hits.
                        if (outputLayerModelState.getOutputLayerOutput()[k][l] == 1)
                        {
                            imageHits++;
                        }
                    }
                }

                // Keep track of what points hit shapes in the image.
                if (outputLayerModelState.getOutputLayerOutput()[0][0] == 1)
                {
                    outputModelResultState.getHit().add(pointArray[i]);
                    outputModelResultState.setHitResultReady(true);
                    outputModelResultState.stateChanged();
                }
                // Keep track of what points missed shapes in the image.
                if (outputLayerModelState.getOutputLayerOutput()[0][0] == -1)
                {
                    outputModelResultState.getMiss().add(pointArray[i]);
                    outputModelResultState.setMissResultReady(true);
                    outputModelResultState.stateChanged();
                }

                if (false)
                {
                    results = "";
                    // Describe the input layer model
                    appendResults("Input Layer:");
                    appendResults(this.inputModelState.getW2(), "W2: Shape Vector Directions");
                    appendResults(inputModelState.getTheta(), "Theta2: Shape Vector Boundaries");
                    appendResults(points, "Targets: Initial Node Inputs (x,y cooridnates)");

                    appendResults("Input Layer Calculations: SHL[(W2*Targets)+Theta2]");

                    // Describe the results of SHL[(W2*P)+Theta2]
                    appendResults(matrix0, "W2*Targets =");
                    appendResults(matrix1, "(W2*Targets) + Theta2 =");
                    appendResults(outputLayerModelState.getAndLayerOutput(), "SHL[(W2*Target) + Theta2 =]");

                    // Describe ANDing Layer
                    appendResults("ANDing Layer");
                    appendResults(inputModelState.getW1(), "W1: Image Vectors");

                    // Describe the results of SHL[(W1*P)+Theta1]
                    appendResults(theta1, "Theta1 (ANDing):");
                    appendResults(matrix3, "W1*SHL[(W2*Target)+Theta2]:");
                    appendResults(matrix4, "(W1*SHL[(W2*Target)+Theta2])+Theta1");
                    appendResults(matrix4, "SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]");

                    // Describe ORing layer
                    appendResults("Output Layer");
                    appendResults(inputModelState.getW0(), "W0:");

                    appendResults(theta2, "Theta0 (ORing):");
                    appendResults(matrix6, "(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]");
                    appendResults(matrix7, "(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]+Theta0");
                    appendResults(outputLayerModelState.getOutputLayerOutput(), "SHL[(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]+Theta0]");

                    if (outputLayerModelState.getOutputLayerOutput()[0][0] == 1)
                    {
                        appendResults("Hit!");
                    }
                    if (outputLayerModelState.getOutputLayerOutput()[0][0] != 1)
                    {
                        appendResults("Miss!");
                    }

                    System.out.println(results);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        double darts = (double)pointArray.length;

        double[][] temp = new double[shapeHits.length][];

        // Calculate the ratio of unique hits
        for (int k = 0; k < shapeHits.length; k++)
        {
            temp[k] = new double[shapeHits[k].length];
            for (int l = 0; l < shapeHits[k].length; l++)
            {
                temp[k][l] = shapeHits[k][l] / darts;
            }
        }

        outputModelResultState.setShapesRatio(temp);

        // Calculate the ratio of shared hits
        outputModelResultState.getImageRatio()[0][0] = imageHits / darts;

        // Update the Output Model Results
        outputModelResultState.setHitResultReady(true);
        outputModelResultState.setMissResultReady(true);
        outputModelResultState.setImageRatioReady(true);
        outputModelResultState.setShapesRatioReady(true);
        outputModelResultState.setTimerPointsReady(true);
        outputModelResultState.stateChanged();

        return null;
    }

    private void appendResults(String message)
    {
        results += "\n" + message;
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

    public Point[] getPoints()
    {
        int xPoints = bounds * resolution;
        int yPoints = bounds * resolution;
        numPoints = (xPoints * yPoints);
        Point[] points = new Point[numPoints];

        for (int i = 0; i < numPoints; i++)
        {
            points[i] = new Point();
        }

        double increment = ((double) 1) / resolution;

        double x = 0;
        double y = 0;
        int count = 0;
        for (int i = 0; i < points.length; i++)
        {
            points[i].setLocation(x, y);

            if (((bounds * resolution) - 1) == count)
            {
                x = x + increment;
                y = 0;
                count = -1;
            } else
            {
                    y = y + increment;    
            }
            count++;
        }
        return points;
    }
}
