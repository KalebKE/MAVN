/*
TwoDimensionalArrayList -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.util.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A special data structure made of two ArrayLists.
 * @author Kaleb
 * @param <Type>
 */
public class TwoDimensionalArrayList<Type>
{

    ArrayList<ArrayList<Type>> list;

    /**
     * Initialize the state.
     */
    public TwoDimensionalArrayList()
    {
        list = new ArrayList<ArrayList<Type>>();
    }

    /**
     * Ensures a minimum capacity of num rows. Note that this does not guarantee
     * that there are that many rows.
     *
     * @param num
     */
    public void ensureCapacity(int num)
    {
        list.ensureCapacity(num);
    }

    /**
     * Ensures that the given row has at least the given capacity. Note that
     * this method will also ensure that getNumRows() >= row
     *
     * @param row
     * @param num
     */
    public void ensureCapacity(int row, int num)
    {
        ensureCapacity(row);
        while (row < getNumRows())
        {
            list.add(new ArrayList<Type>());
        }
        list.get(row).ensureCapacity(num);
    }

    /**
     * Adds an item at the end of the specified row. This will guarantee that at
     * least row rows exist.
     */
    public void Add(Type data, int row)
    {
        ensureCapacity(row);
        while (row >= getNumRows())
        {
            list.add(new ArrayList<Type>());
        }
        list.get(row).add(data);
    }

    public Type get(int row, int col)
    {
        return list.get(row).get(col);
    }

    public void set(int row, int col, Type data)
    {
        list.get(row).set(col, data);
    }

    public void remove(int row, int col)
    {
        list.get(row).remove(col);
    }

    public boolean contains(Type data)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).contains(data))
            {
                return true;
            }
        }
        return false;
    }

    public int getNumRows()
    {
        return list.size();
    }

    public int getNumCols(int row)
    {
        return list.get(row).size();
    }

    public String[][] toArray()
    {
        String[][] data = new String[list.size()][];

        for (int i = 0; i < list.size(); i++)
        {
            data[i] = new String[list.get(i).size()];

            for (int j = 0; j < list.get(i).size(); j++)
            {
                data[i][j] = (String) this.get(i, j);
            }
        }

        return data;
    }

    public Iterator[] iterator()
    {
        Iterator[] iterator = new Iterator[list.size()];

        for (int i = 0; i < iterator.length; i++)
        {
            iterator[i] = list.get(i).iterator();
        }

        return iterator;
    }
}
