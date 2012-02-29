/*
CalcTheta -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

/*
 * The AND and OR functions/algorithms were designed and written by Kaleb Kircher
 * specifically for the MAVN application. The functions/algorithms are designed
 * to dynamically adapt to any network. They are licenced under the GPL Versions 2
 * licence. Any use of this code, the functions or the algorithm
 * *must* retain this licence and authors name.
 */

package mavn.simModel.algorithm;

/**
 * CalcTheta is a special class containing two algorithms that are
 * capable of, respectivly, ANDing and ORing the nodes of the MAVN network.
 * @author Kaleb Kircher
 */
public class CalcTheta {

    /**
     * Peforms and ANDing on the network nodes.
     * @param matrix the matrix representing the network nodes
     * @return the result of the ANDing function
     */
    public double[][] calculateAnd(double[][] matrix)
    {
        double[][] result = new double[matrix.length][1];

        // This code uses a function that
        // will dynamically AND the network nodes.
        // To understand the function, understand that
        // each shape is represented by four vectors with
        // a direction. The four vectors all point at the shape,
        // forming vector representation of the shape. Each shape
        // gets a row in a weight matrix with each colum representing a
        // node in the system.
        // If a vector is pointing at a shape, it is represented as a 1 in the row
        // of the weight matrix. A 0 indicates that the vector does not
        // represent that shape.
        // This weight matrix is multiplied by another matrix full of 1's and -1's.
        // These 1's and -1's reprenting what vectors were hit by darts. 1's indicate hits
        // and -1's represent misses. Basically, for a dart to hit a shape, FOUR vectors
        // have to go high in a row.
        // After you multiply the two matricies representing
        // the vectors and what vectors were activated, you will have a 4 in the row
        // of the shape that was hit. The MAVN algorithm uses a symetrical hard limit
        // of >0 = 1, else -1. So, to see if there was a hit, we essentailly use an
        // AND gate that looks for all four vectors to go high.
        // To make the AND function, sum each row of the weight matrix. Usually,
        // this will result in a 4. Take the negative result of that, usually -4,
        // and add it to the result of the weight matrix * active vectors. If there
        // was a hit, all of the vectors will have gone high, usually a 4. So, 4 + -4 = 0.
        // the SHL of 0 is 1, so a hit. Everything else = 0. 
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < result[0].length; j++)
            {
                for (int k = 0; k < matrix[0].length; k++)
                {
                    result[i][j] += matrix[i][k];
                }

                result[i][j] = -(result[i][j]);
            }
        }

        return result;
    }

    public double[][] calculateOr(double[][] matrix)
    {

        double[][] result = new double[matrix.length][1];

        // This works on the same principle as the AND function,
        // but it looks for just ONE of the nodes to go high
        // instead of all of them like the AND function. This
        // can be accomplished by taking the sum of each of the weight matrix
        // row's and subtracting one from the sum. Just one node has to go high
        // for the OR to activate, so when the matrices are multiplied and subtracted
        // from the result of the OR function, if any of the nodes have gone high,
        // the result will be >0. 
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < result[0].length; j++)
            {
                for (int k = 0; k < matrix[0].length; k++)
                {
                    result[i][j] += matrix[i][k];
                }

                result[i][j] = (result[i][j]-1);
            }
        }
        return result;
    }

}
