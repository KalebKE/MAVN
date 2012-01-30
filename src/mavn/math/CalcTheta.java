/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.math;

/**
 *
 * @author Kaleb
 */
public class CalcTheta {

    public double[][] calculateAnd(double[][] matrix)
    {

        double[][] result = new double[matrix.length][1];

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
