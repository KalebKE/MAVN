/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ann.math;

/**
 *
 * @author Kaleb
 */
public class SHL
{
    public double[][] calculate(double[][] matrix)
    {

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (matrix[i][j] >= 0)
                {
                    matrix[i][j] = 1;
                }
                if (matrix[i][j] < 0)
                {
                    matrix[i][j] = -1;
                }
            }
        }

        return matrix;
    }
}
