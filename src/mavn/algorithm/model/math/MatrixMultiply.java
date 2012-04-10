/*
MatrixMultiply -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.algorithm.model.math;

/**
 * Multiply two matrices. Java implementation from
 * Tom Christiansen & Nathan Torkington, Perl Cookbook version.
 */
public class MatrixMultiply
{
    /* Matrix-multiply two arrays together.
     * The arrays MUST be rectangular.
     * @param m1
     * @param m2
     * @return
     * @author Tom Christiansen & Nathan Torkington, Perl Cookbook version.
     */
    public double[][] multiply(double[][] m1, double[][] m2)
    {
        int m1rows = m1.length;
        int m1cols = m1[0].length;
        int m2rows = m2.length;
        int m2cols = m2[0].length;
        if (m1cols != m2rows)
        {
            throw new IllegalArgumentException("matrices don't match: " + m1cols + " != " + m2rows);
        }

        double[][] result = new double[m1rows][m2cols];

        // multiply
        for (int i = 0; i < m1rows; i++)
        {
            for (int j = 0; j < m2cols; j++)
            {
                for (int k = 0; k < m1cols; k++)
                {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return result;
    }

    /**
     * Add two rectangular matrices of integers and
     * return a matrix with their sum.
     * @param m1
     * @param m2
     * @return
     */
    public double[][] addMatrix(double[][] m1, double[][] m2)
    {

        double[][] result = new double[m1.length][m1[0].length];

        for (int row = 0; row < m1.length; row++)
        {
            for (int col = 0; col < m1[row].length; col++)
            {
                result[row][col] = m1[row][col] + m2[row][col];
            }
        }

        return result;
    }

    /**
     * Matrix print.
     * @param a 
     */
    public void mprint(double[][] a)
    {
        int rows = a.length;
        int cols = a[0].length;
        System.out.println("array[" + rows + "][" + cols + "] = {");
        for (int i = 0; i < rows; i++)
        {
            System.out.print("{");
            for (int j = 0; j < cols; j++)
            {
                System.out.print(" " + a[i][j] + ",");
            }
            System.out.println("},");
        }
        System.out.println(":;");
    }
}

