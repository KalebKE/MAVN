/*
SHL -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.algorithm;

/**
 * Finds the Symetrical Hard Limit of a MAVN Network.
 * @author Kaleb
 */
public class SHL
{
    /**
     * >0 = 1, <0 = -1
     * @param matrix
     * @return
     */
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
