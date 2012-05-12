/*
DiagnosticSimulationWorker -- A class within the Machine Artificial Vision
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
package mavn.algorithm.model.singlePointSimulation.worker;

import java.text.DecimalFormat;
import javax.swing.SwingWorker;
import mavn.algorithm.model.math.CalcTheta;
import mavn.algorithm.model.math.MatrixMath;
import mavn.algorithm.model.math.SHL;
import mavn.algorithm.model.state.input.InputModelState;
import mavn.algorithm.model.singlePointSimulation.state.SinglePointLayerOutputModelState;

/**
 * A special SwingWorker that performs the calculations for the
 * Single Point Model Simulations Algorithm. This can be very time
 * consuming and we don't want the GUI to hang, so the SwingWorker
 * spawns its own thread and runs the calculations in the background.
 * @author Kaleb
 */
public class DiagnosticSimulationWorker extends SwingWorker
{

    private boolean debug = false;
    private InputModelState inputModelState;
    private SinglePointLayerOutputModelState outputModelState;
    // Objects for debugging and Exception outputs
    private DecimalFormat decimalFormater;
    private String decimalFormat;
    private String results;

    /**
     * Initialize a new instance of a DiagnosticSimulationWorker. Note that this
     * class extends SwingWorker which is intended to be used *once* per
     * instance.
     * @param inputModelState the Input Model State for the simulation.
     * @param outputModelState the Output Model State for the simulation.
     */
    public DiagnosticSimulationWorker(InputModelState inputModelState, SinglePointLayerOutputModelState outputModelState)
    {
        this.inputModelState = inputModelState;
        this.outputModelState = outputModelState;
        // For debug and Exception
        results = "";
        decimalFormat = ("0.0000");
        decimalFormater = new DecimalFormat(decimalFormat);
    }

    @Override
    protected Object doInBackground() throws Exception
    {
        MatrixMath matrix = new MatrixMath();
        SHL shl = new SHL();
        CalcTheta theta = new CalcTheta();

        double[][] w2 = inputModelState.getW2();

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
        double[][] matrix0 = matrix.multiply(inputModelState.getW2(), inputModelState.getTarget());
        double[][] matrix1 = matrix.addMatrix(matrix0, inputModelState.getTheta());
        outputModelState.setAndLayerOutput(shl.calculate(matrix1));

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
        double[][] matrix3 = matrix.multiply(inputModelState.getW1(), outputModelState.getAndLayerOutput());
        double[][] theta1 = theta.calculateAnd(inputModelState.getW1());
        double[][] matrix4 = matrix.addMatrix(matrix3, theta1);
        outputModelState.setOrLayerOutput(shl.calculate(matrix4));

        // SHL[(W0*P)+Theta0]
        double[][] theta2 = theta.calculateOr(inputModelState.getW0());
        double[][] matrix6 = matrix.multiply(inputModelState.getW0(), outputModelState.getOrLayerOutput());
        double[][] matrix7 = matrix.addMatrix(matrix6, theta2);
        outputModelState.setOutputLayerResult(shl.calculate(matrix7));

        if (debug)
        {
            results = "";
            // Describe the input layer model
            appendResults("Input Layer:");
            appendResults(w2, "W2: Shape Vector Directions");
            appendResults(inputModelState.getTheta(), "Theta2: Shape Vector Boundaries");
            appendResults(inputModelState.getTarget(), "Targets: Initial Node Inputs (x,y cooridnates)");

            appendResults("Input Layer Calculations: SHL[(W2*Targets)+Theta2]");

            // Describe the results of SHL[(W2*P)+Theta2]
            appendResults(matrix0, "W2*Targets =");
            appendResults(matrix1, "(W2*Targets) + Theta2 =");
            appendResults(outputModelState.getAndLayerOutput(), "SHL[(W2*Target) + Theta2 =]");

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
            appendResults(outputModelState.getOutputLayerOutput(), "SHL[(W0*SHL[(W1*SHL[(W2*Target)+Theta2])+Theta1]+Theta0]");

            if (outputModelState.getOutputLayerOutput()[0][0] == 1)
            {
                appendResults("Hit!");
            }
            if (outputModelState.getOutputLayerOutput()[0][0] != 1)
            {
                appendResults("Miss!");
            }
        }

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
}
