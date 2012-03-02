/*
FindMax -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.util.math;

/**
 * A class that contains methods to find the maximum value contained
 * in a collection.
 * @author Kaleb
 */
public class FindMax
{
    /**
     * Find the max value in a double array containing doubles.
     * @param numbers the double array
     * @return  the maximum value in the array
     */
    public static double getMaxValue(double[][] numbers)
    {
        double maxValue = numbers[0][0];
        for (int i = 0; i < numbers.length; i++)
        {
            for (int j = 0; j < numbers[i].length; j++)
            {
                if (numbers[i][j] > maxValue)
                {
                    maxValue = Math.abs(numbers[i][j]);
                }
            }
        }
        return maxValue;
    }
}
