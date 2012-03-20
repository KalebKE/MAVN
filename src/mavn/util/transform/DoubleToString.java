/*
DoubleToString -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.util.transform;

/**
 * A class containing methods to convert collections of doubles to collections
 * of strings.
 * @author Kaleb Kircher
 */
public class DoubleToString
{
    /**
     * Convert a double array of doubles to a double array of Strings.
     * @param matrix double array containing doubles.
     * @return double array containing Strings.
     */
    public String doubleToString(double[][] matrix)
    {
      
        String string = "";

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                string += Double.toString(matrix[i][j]) + ",";
            }

            string += "\n";
        }

        return string;
    }
}
