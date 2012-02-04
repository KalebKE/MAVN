/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.math.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import mavn.math.algorithm.CalcTheta;
import mavn.math.algorithm.Matrix;
import mavn.math.algorithm.SHL;
import mavn.observer.ResultsObserver;
import mavn.view.ControlFrame;

/**
 *
 * @author Kaleb
 */
public class MavnSinglePointModel implements MavnAlgorithmModelInterface
{
    private ArrayList<ResultsObserver> observers;
    private ControlFrame view;
    private DecimalFormat decimalFormater;
    private String decimalFormat;
    private String results;

    public MavnSinglePointModel(ControlFrame view)
    {
        this.view = view;
        observers = new ArrayList<ResultsObserver>();
        results = "";

        // Set the desired decimal format here.
        // *This can be overridden by the User Preferances panel!*
        decimalFormat = ("0.0000");

        // Creates a new DecimalFormatter for the text fields so we can
        // can control how many decimal places get printed.
        decimalFormater = new DecimalFormat(decimalFormat);
    }

    @Override
    public void calculate()
    {
        Matrix matrix = new Matrix();
        SHL shl = new SHL();
        CalcTheta theta = new CalcTheta();

        // Describe the input layer model
        appendResults("Input Layer:");
        appendResults(view.getCurrentMatrixW2(), "W2: Shape Vector Directions");
        appendResults(view.getCurrentMatrixTheta(), "Theta2: Shape Vector Boundaries");
        appendResults(view.getCurrentMatrixTarget(), "Targets: Initial Node Inputs (x,y cooridnates)");
        
        appendResults("Input Layer Calculations: SHL[(W2*Targets)+Theta2]");
        // SHL[(W2*P)+Theta2]
        // An Image contaians multiple Shapes definined by four vectors,
        // represented by nodes, and their respective spaces.
        // This equation calculates the results of target and the input layer.
        // The target consists of two cooridnates (generally denoted as X and Y)
        // from a cartesian two-space plane. W2 (the vector directions) is multiplied
        // by the x,y coordinates and the result is then added to Theta2 (the vector boundaries).
        // The symetrical hard limit (SHL) of the result is taken (SHL = if(result => 0 = 1), else -1).
        // The result of the SHL[(W2*P)+Theta2] indicates what nodes (representing vectors) in the input
        // layer activated (1) or statyed deactived (-1). An activated node means that a hit
        // occured within the vector space defined by that node.
        // The input layer needs to see BOTH the X and Y nodes go active indicating that a "hit" has occured within
        // the image space described by the vectors.Usually, four nodes need to
        // be activated to indicate a 'hit'. This is calculated by the ANDing layer. 
        double[][] matrix0 = matrix.multiply(view.getCurrentMatrixW2(), view.getCurrentMatrixTarget());
        double[][] matrix1 = matrix.addMatrix(matrix0, view.getCurrentMatrixTheta());
        double[][] matrix2 = shl.calculate(matrix1);

        // Describe the results of SHL[(W2*P)+Theta2]
        appendResults(matrix0, "W2*Targets =");
        appendResults(matrix1, "(W2*Targets) + Theta2 =");
        appendResults(matrix2, "SHL[(W2*Target) + Theta2 =]");

        // Describe ANDing Layer
        appendResults("ANDing Layer");
        appendResults(view.getCurrentMatrixW1(), "W1: Image Vectors");

        // SHL[(W1*P)+Theta1]
        // Calculates the result of the ANDing layer.
        // Each row of the W1 matrix descibes an shape within the image that is defined on a two-space
        // cartesian plane by vectors (usually four vectors to box in the image).
        // The W1 colums indicate what vectors (representing nodes) are defining
        // what shapes within the image. A 1 in the column of a row indicates
        // the node represents a vector defining the shape, a 0 indicates the
        // node is not representing a vector defining the shape.  To do this, W1 is multiplied by the results
        // of SHL[(W2*P)+Theta2] and then added to Theta1. Theta1 is a special
        // ANDing function that is dynamically calculated to ensure all the nodes,
        // representing vectors, that define the image went high.
        // Iff all four nodes go high for a image, the ANDing node goes high
        // indicating a "hit" within the vectors defining the image. Otherwise,
        // if one or more nodes does not go high, the ANDing node will stay low.
        double[][] matrix3 = matrix.multiply(view.getCurrentMatrixW1(), matrix2);
        double[][] theta1 = theta.calculateAnd(view.getCurrentMatrixW1());
        double[][] matrix4 = matrix.addMatrix(matrix3, theta1);
        double[][] matrix5 = shl.calculate(matrix4);

        // Describe the results of SHL[(W1*P)+Theta1]
        appendResults(theta1, "Theta1 (ANDing):");
        appendResults(matrix3, "W1*SHL[(W2*Target)+Theta2]:");
        appendResults(matrix4, "(W1*SHL[(W2*Target)+Theta2])+Theta1");    
        appendResults(matrix4, "SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]");

        // Describe ORing layer
        appendResults("Output Layer");
        appendResults(view.getCurrentMatrixW0(), "W0:");

        // SHL[(W0*P)+Theta0]
        double[][] theta2 = theta.calculateOr(view.getCurrentMatrixW0());
        double[][] matrix6 = matrix.multiply(view.getCurrentMatrixW0(), matrix5);
        double[][] matrix7 = matrix.addMatrix(matrix6, theta2);
        double[][] matrix8 = shl.calculate(matrix7);
        
        appendResults(theta2, "Theta0 (ORing):");
        appendResults(matrix6, "(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]");
        appendResults(matrix7, "(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]+Theta0");
        appendResults(matrix8, "SHL[(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]+Theta0]");

        if (matrix8[0][0] == 1)
        {
            appendResults("Hit!");
        }
        if (matrix8[0][0] != 1)
        {
            appendResults("Miss!");
        }

        notifyObservers();
    }

    @Override
    public void registerObserver(ResultsObserver o)
    {
        observers.add(o);
    }

    @Override
    public void removeObserver(ResultsObserver o)
    {
        int i = observers.indexOf(o);
        if (i >= 0)
        {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < observers.size(); i++)
        {
            ResultsObserver observer = (ResultsObserver) observers.get(i);
            observer.updateResultsMatrix(results);
        }
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
}

