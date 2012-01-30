/*
 IteratorToDoubleArray -- a class within the Open Queueing Model (OQM).
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

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorToDoubleArray
{
    public IteratorToDoubleArray()
    {
        super();
    }

    public double[] reversal(Iterator iterator)
    {
        ArrayList<Double> list = new ArrayList<Double>();

        while (iterator.hasNext())
        {
            list.add((Double) iterator.next());
        }

        double[] array = new double[list.size()];

        int count = 0;
        for (double x : list)
        {
            array[count] = x;
            count++;
        }

        return array;
    }
}
