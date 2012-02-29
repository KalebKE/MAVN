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
package mavn.simModel.algorithm.model;

import mavn.simModel.algorithm.network.OutputLayerInterface;
import mavn.simModel.algorithm.network.AndLayerInterface;
import mavn.simModel.algorithm.network.OrLayerInterface;
import java.text.DecimalFormat;
import java.util.ArrayList;
import mavn.simModel.input.controller.ModelInputFileInterface;
import mavn.globals.Globals;
import mavn.simModel.algorithm.CalcTheta;
import mavn.simModel.algorithm.MatrixMultiply;
import mavn.simModel.algorithm.SHL;
import mavn.simModel.algorithm.observer.AndLayerAlgorithmObserver;
import mavn.simModel.algorithm.observer.OrLayerAlgorithmObserver;
import mavn.simModel.algorithm.observer.OutputLayerAlgorithmObserver;
import mavn.simModel.algorithm.observer.ModelResultAlgorithmObserver;

/**
 *
 * @author Kaleb
 */
public class MavnSinglePointModel implements AlgorithmModelInterface, AndLayerInterface, OrLayerInterface, OutputLayerInterface
{
    private ArrayList<ModelResultAlgorithmObserver> resultObservers;
    private ArrayList<AndLayerAlgorithmObserver> andLayerObservers;
    private ArrayList<OrLayerAlgorithmObserver> orLayerObservers;
    private ArrayList<OutputLayerAlgorithmObserver> outputLayerObservers;
    private ArrayList<ModelInputFileInterface> controllers;
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
    public MavnSinglePointModel(ArrayList<ModelInputFileInterface> controllers)
    {

        this.controllers = controllers;
        resultObservers = new ArrayList<ModelResultAlgorithmObserver>();
        andLayerObservers = new ArrayList<AndLayerAlgorithmObserver>();
        orLayerObservers = new ArrayList<OrLayerAlgorithmObserver>();
        outputLayerObservers = new ArrayList<OutputLayerAlgorithmObserver>();
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

        double[][] w2 = this.controllers.get(Globals.W2_CONTROLLER).getModelInput().getModelInput();
        // Describe the input layer model
        appendResults("Input Layer:");
        appendResults(w2, "W2: Shape Vector Directions");
        appendResults(this.controllers.get(Globals.THETA_CONTROLLER).getModelInput().getModelInput(), "Theta2: Shape Vector Boundaries");
        appendResults(this.controllers.get(Globals.TARGET_CONTROLLER).getModelInput().getModelInput(), "Targets: Initial Node Inputs (x,y cooridnates)");

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
        double[][] matrix0 = matrix.multiply(this.controllers.get(Globals.W2_CONTROLLER).getModelInput().getModelInput(), this.controllers.get(Globals.TARGET_CONTROLLER).getModelInput().getModelInput());
        double[][] matrix1 = matrix.addMatrix(matrix0, this.controllers.get(Globals.THETA_CONTROLLER).getModelInput().getModelInput());
        andLayerResult = shl.calculate(matrix1);

        // Describe the results of SHL[(W2*P)+Theta2]
        appendResults(matrix0, "W2*Targets =");
        appendResults(matrix1, "(W2*Targets) + Theta2 =");
        appendResults(andLayerResult, "SHL[(W2*Target) + Theta2 =]");

        // Describe ANDing Layer
        appendResults("ANDing Layer");
        appendResults(this.controllers.get(Globals.W1_CONTROLLER).getModelInput().getModelInput(), "W1: Image Vectors");

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
        double[][] matrix3 = matrix.multiply(this.controllers.get(Globals.W1_CONTROLLER).getModelInput().getModelInput(), andLayerResult);
        double[][] theta1 = theta.calculateAnd(this.controllers.get(Globals.W1_CONTROLLER).getModelInput().getModelInput());
        double[][] matrix4 = matrix.addMatrix(matrix3, theta1);
        orLayerResult = shl.calculate(matrix4);

        // Describe the results of SHL[(W1*P)+Theta1]
        appendResults(theta1, "Theta1 (ANDing):");
        appendResults(matrix3, "W1*SHL[(W2*Target)+Theta2]:");
        appendResults(matrix4, "(W1*SHL[(W2*Target)+Theta2])+Theta1");
        appendResults(matrix4, "SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]");

        // Describe ORing layer
        appendResults("Output Layer");
        appendResults(this.controllers.get(Globals.W0_CONTROLLER).getModelInput().getModelInput(), "W0:");

        // SHL[(W0*P)+Theta0]
        double[][] theta2 = theta.calculateOr(this.controllers.get(Globals.W0_CONTROLLER).getModelInput().getModelInput());
        double[][] matrix6 = matrix.multiply(this.controllers.get(Globals.W0_CONTROLLER).getModelInput().getModelInput(), orLayerResult);
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
    public void registerObserver(ModelResultAlgorithmObserver o)
    {
        resultObservers.add(o);
    }

    /**
     * Remove a ResultsObserver.
     * @param o the ResultsObserver.
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

    /**
     * Notify all Observers.
     */
    @Override
    public void notifyResultObservers()
    {
        for (int i = 0; i < resultObservers.size(); i++)
        {
            ModelResultAlgorithmObserver observer = (ModelResultAlgorithmObserver) resultObservers.get(i);
            observer.updateModelResult(outputLayerResult);
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

