/*
MavenSinglePointModel -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
import mavn.globals.Globals;
import mavn.math.algorithm.CalcTheta;
import mavn.math.algorithm.MatrixMultiply;
import mavn.math.algorithm.SHL;
import mavn.observer.AndLayerObserver;
import mavn.observer.OrLayerObserver;
import mavn.observer.OutputLayerObserver;
import mavn.observer.ResultsObserver;

/**
 *
 * @author Kaleb
 */
public class MavnSinglePointModel implements AlgorithmModelInterface, AndLayerInterface, OrLayerInterface, OutputLayerInterface
{

    private ArrayList<ResultsObserver> resultObservers;
    private ArrayList<AndLayerObserver> andLayerObservers;
    private ArrayList<OrLayerObserver> orLayerObservers;
    private ArrayList<OutputLayerObserver> outputLayerObservers;
    private ArrayList<InputControllerInterface> controllers;
    private DecimalFormat decimalFormater;
    private String decimalFormat;
    private String results;
    private double[][] andLayerResult;
    private double[][] orLayerResult;
    private double[][] outputLayerResult;

    /**
     * Initialize the class.
     * @param view the View to pull state
     */
    public MavnSinglePointModel(ArrayList<InputControllerInterface> controllers)
    {

        this.controllers = controllers;
        resultObservers = new ArrayList<ResultsObserver>();
        andLayerObservers = new ArrayList<AndLayerObserver>();
        orLayerObservers = new ArrayList<OrLayerObserver>();
        outputLayerObservers = new ArrayList<OutputLayerObserver>();
        results = "";

        // Set the desired decimal format here.
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
        results = "";
        MatrixMultiply matrix = new MatrixMultiply();
        SHL shl = new SHL();
        CalcTheta theta = new CalcTheta();

        double[][] w2 = this.controllers.get(Globals.W2_CONTROLLER).getModel();
        // Describe the input layer model
        appendResults("Input Layer:");
        appendResults(w2, "W2: Shape Vector Directions");
        appendResults(this.controllers.get(Globals.THETA_CONTROLLER).getModel(), "Theta2: Shape Vector Boundaries");
        appendResults(this.controllers.get(Globals.TARGET_CONTROLLER).getModel(), "Targets: Initial Node Inputs (x,y cooridnates)");

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
        double[][] matrix0 = matrix.multiply(this.controllers.get(Globals.W2_CONTROLLER).getModel(), this.controllers.get(Globals.TARGET_CONTROLLER).getModel());
        double[][] matrix1 = matrix.addMatrix(matrix0, this.controllers.get(Globals.THETA_CONTROLLER).getModel());
        andLayerResult = shl.calculate(matrix1);

        // Describe the results of SHL[(W2*P)+Theta2]
        appendResults(matrix0, "W2*Targets =");
        appendResults(matrix1, "(W2*Targets) + Theta2 =");
        appendResults(andLayerResult, "SHL[(W2*Target) + Theta2 =]");

        // Describe ANDing Layer
        appendResults("ANDing Layer");
        appendResults(this.controllers.get(Globals.W1_CONTROLLER).getModel(), "W1: Image Vectors");

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
        double[][] matrix3 = matrix.multiply(this.controllers.get(Globals.W1_CONTROLLER).getModel(), andLayerResult);
        double[][] theta1 = theta.calculateAnd(this.controllers.get(Globals.W1_CONTROLLER).getModel());
        double[][] matrix4 = matrix.addMatrix(matrix3, theta1);
        orLayerResult = shl.calculate(matrix4);

        // Describe the results of SHL[(W1*P)+Theta1]
        appendResults(theta1, "Theta1 (ANDing):");
        appendResults(matrix3, "W1*SHL[(W2*Target)+Theta2]:");
        appendResults(matrix4, "(W1*SHL[(W2*Target)+Theta2])+Theta1");
        appendResults(matrix4, "SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]");

        // Describe ORing layer
        appendResults("Output Layer");
        appendResults(this.controllers.get(Globals.W0_CONTROLLER).getModel(), "W0:");

        // SHL[(W0*P)+Theta0]
        double[][] theta2 = theta.calculateOr(this.controllers.get(Globals.W0_CONTROLLER).getModel());
        double[][] matrix6 = matrix.multiply(this.controllers.get(Globals.W0_CONTROLLER).getModel(), orLayerResult);
        double[][] matrix7 = matrix.addMatrix(matrix6, theta2);
        outputLayerResult = shl.calculate(matrix7);

        appendResults(theta2, "Theta0 (ORing):");
        appendResults(matrix6, "(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]");
        appendResults(matrix7, "(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]+Theta0");
        appendResults(outputLayerResult, "SHL[(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]+Theta0]");

        if (outputLayerResult[0][0] == 1)
        {
            appendResults("Hit!");
        }
        if (outputLayerResult[0][0] != 1)
        {
            appendResults("Miss!");
        }

        notifyObservers();
        notifyResultObservers();
    }

    /**
     * Register a new ResultsObserver.
     * @param o the ResultsObserver.
     */
    @Override
    public void registerObserver(ResultsObserver o)
    {
        resultObservers.add(o);
    }

    /**
     * Remove a ResultsObserver.
     * @param o the ResultsObserver.
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

    @Override
    public void registerObserver(AndLayerObserver o)
    {
        andLayerObservers.add(o);
    }

    @Override
    public void removeObserver(AndLayerObserver o)
    {
        int i = andLayerObservers.indexOf(o);
        if (i >= 0)
        {
            andLayerObservers.remove(o);
        }
    }

    @Override
    public void registerObserver(OrLayerObserver o)
    {
        orLayerObservers.add(o);
    }

    @Override
    public void removeObserver(OrLayerObserver o)
    {
        int i = orLayerObservers.indexOf(o);
        if (i >= 0)
        {
            orLayerObservers.remove(o);
        }
    }

    @Override
    public void registerObserver(OutputLayerObserver o)
    {
        outputLayerObservers.add(o);
    }

    @Override
    public void removeObserver(OutputLayerObserver o)
    {
        int i = outputLayerObservers.indexOf(o);
        if (i >= 0)
        {
            outputLayerObservers.remove(o);
        }
    }

    /**
     * Notify all Observers.
     */
    @Override
    public void notifyResultObservers()
    {
        for (int i = 0; i < resultObservers.size(); i++)
        {
            ResultsObserver observer = (ResultsObserver) resultObservers.get(i);
            observer.updateResultsModel(results);
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
            AndLayerObserver observer = (AndLayerObserver) andLayerObservers.get(i);
            observer.updateAndLayerModel(andLayerResult);
        }
        for (int i = 0; i < orLayerObservers.size(); i++)
        {
            OrLayerObserver observer = (OrLayerObserver) orLayerObservers.get(i);
            observer.updateOrLayerModel(orLayerResult);
        }
        for (int i = 0; i < outputLayerObservers.size(); i++)
        {
            OutputLayerObserver observer = (OutputLayerObserver) outputLayerObservers.get(i);
            observer.updateOutputLayerModel(outputLayerResult);
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

