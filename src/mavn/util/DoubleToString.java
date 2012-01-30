/*
DoubleToString -- a class within the Open Queueing Model (OQM).
Copyright (C) 2011  Kircher Engineering, LLC (http://www.kircherEngineering.com)

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
package mavn.util;

/**
 *
 * @author Kaleb @ Kircher Engineering, LLC
 */
public class DoubleToString
{
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
