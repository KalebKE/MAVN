/*
IteratorToDoubleArray -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A special class to convert iterator collections to array collections of doubles.
 * @author Kaleb
 */
public class IteratorToDoubleArray
{

    /**
     * Initialize the state.
     */
    public IteratorToDoubleArray()
    {
        super();
    }

    /**
     * Transfer the contents of the Iterator into an array of doubles.
     * @param iterator the iterator containing the data.
     * @return the array of doubles.
     */
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
