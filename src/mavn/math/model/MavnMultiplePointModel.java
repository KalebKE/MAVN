/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.math.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import mavn.math.algorithm.CalcTheta;
import mavn.math.algorithm.Matrix;
import mavn.math.algorithm.SHL;
import mavn.observer.DartsObserver;
import mavn.observer.ResultsObserver;
import mavn.util.FindMax;
import mavn.view.ControlFrame;
import org.uncommons.maths.random.CMWC4096RNG;
import org.uncommons.maths.random.CellularAutomatonRNG;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.XORShiftRNG;

/**
 *
 * @author Kaleb
 */
public class MavnMultiplePointModel implements MavnAlgorithmModelInterface, DartModelInterface
{

    private ArrayList<ResultsObserver> resultObservers;
    private ArrayList<DartsObserver> dartObservers;
    private ArrayList<double[][]> hit;
    private ArrayList<double[][]> miss;
    private ControlFrame controlView;
    private DecimalFormat decimalFormater;
    private String decimalFormat;
    private String results;
    private Random javaRng;
    private MersenneTwisterRNG mtRng;
    private CMWC4096RNG cmwcRng;
    private CellularAutomatonRNG caRng;
    private XORShiftRNG xOrRng;
    private double bounds;

    public MavnMultiplePointModel(ControlFrame controlView)
    {
        this.controlView = controlView;
        resultObservers = new ArrayList<ResultsObserver>();
        dartObservers = new ArrayList<DartsObserver>();
        results = "";
        javaRng = new Random();
        mtRng = new MersenneTwisterRNG();
        cmwcRng = new CMWC4096RNG();
        caRng = new CellularAutomatonRNG();
        xOrRng = new XORShiftRNG();

        bounds = FindMax.getMaxValue(controlView.getCurrentMatrixTheta());


        // *This can be overridden by the User Preferances panel!*
        decimalFormat = ("0.0000");

        // Creates a new DecimalFormatter for the text fields so we can
        // can control how many decimal places get printed.
        decimalFormater = new DecimalFormat(decimalFormat);
    }

    @Override
    public void calculate()
    {
        Matrix matrixMath = new Matrix();
        SHL shl = new SHL();
        CalcTheta theta = new CalcTheta();

        // Used to count the number of hits on a individual shape in the image
        int[][] uniqueHits = new int[controlView.getCurrentMatrixW1().length][1];

        // Used to calculate the ratio of hits to misses on a individual shape in the image
        double[][] uniqueHitsRatio = new double[controlView.getCurrentMatrixW1().length][1];

        // Used to count the number of hits in a spot where two or more images overlap
        int imageHits = 0;

        // Used to calculate the ratio of hits to misses in a spot where two or more images overlap
        double sharedHitsRatio = 0;

        // The number of darts fired at the image
        double darts = controlView.getNumDarts();


        hit = new ArrayList<double[][]>();
        miss = new ArrayList<double[][]>();

        // For loop to fire the darts
        for (int i = 0; i < darts; i++)
        {
            double[][] points;
            if (controlView.isRandomRng())
            {
                // Create a double array for the random points
                points = new double[][]
                        {
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                javaRng.nextDouble() * bounds
                            },
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                javaRng.nextDouble() * bounds
                            }
                        };
            }
            if (controlView.isMtRng())
            {
                // Create a double array for the random points
                points = new double[][]
                        {
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                mtRng.nextDouble() * bounds
                            },
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                mtRng.nextDouble() * bounds
                            }
                        };
            }
            if (controlView.isCmwcRng())
            {
                // Create a double array for the random points
                points = new double[][]
                        {
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                cmwcRng.nextDouble() * bounds
                            },
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                cmwcRng.nextDouble() * bounds
                            }
                        };
            }
            if (controlView.isCaRng())
            {
                // Create a double array for the random points
                points = new double[][]
                        {
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                caRng.nextDouble() * bounds
                            },
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                caRng.nextDouble() * bounds
                            }
                        };
            }
            if (controlView.isxORRng())
            {
                // Create a double array for the random points
                points = new double[][]
                        {
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                xOrRng.nextDouble() * bounds
                            },
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                xOrRng.nextDouble() * bounds
                            }
                        };
            } else
            {
                // Create a double array for the random points
                points = new double[][]
                        {
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                javaRng.nextDouble() * bounds
                            },
                            {
                                // A random double no larger than 5 (the outer bounds of the image)
                                javaRng.nextDouble() * bounds
                            }
                        };
            }


            // SHL[(W2*P)+Theta2]
            double[][] matrix0 = matrixMath.multiply(controlView.getCurrentMatrixW2(), points);
            double[][] matrix1 = matrixMath.addMatrix(matrix0, controlView.getCurrentMatrixTheta());
            double[][] matrix2 = shl.calculate(matrix1);

            // SHL[(W1*P)+Theta1]
            double[][] matrix3 = matrixMath.multiply(controlView.getCurrentMatrixW1(), matrix2);
            double[][] theta1 = theta.calculateAnd(controlView.getCurrentMatrixW1());
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
            double[][] theta2 = theta.calculateOr(controlView.getCurrentMatrixW0());
            double[][] matrix6 = matrixMath.multiply(controlView.getCurrentMatrixW0(), matrix5);
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

    @Override
    public void registerObserver(ResultsObserver o)
    {
        resultObservers.add(o);
    }

    @Override
    public void removeObserver(ResultsObserver o)
    {
        int i = resultObservers.indexOf(o);
        if (i >= 0)
        {
            resultObservers.remove(o);
        }
    }

    @Override
    public void registerObserver(DartsObserver o)
    {
        dartObservers.add(o);
    }

    @Override
    public void removeObserver(DartsObserver o)
    {
        int i = dartObservers.indexOf(o);
        if (i >= 0)
        {
            dartObservers.remove(o);
        }
    }

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

