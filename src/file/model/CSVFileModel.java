/*
 CSVFileModel -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package file.model;

import mavn.util.IteratorToDoubleArray;
import mavn.util.StringToDouble;
import file.observer.FileObserver;
import util.model.parse.CSVParserFactory;
import util.model.parse.FileParser;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of FileModelInterface. CSVFileModel knows how to
 * parse CSV files into the Model so it can be used by the rest of the
 * application. It implements an Observer pattern so any observers will
 * be notified and updated with a new Model whenever a new CSV file is parsed.
 * @author Kaleb
 */
public class CSVFileModel implements FileModelInterface
{
    private ArrayList<FileObserver> observers;
    private double[][] array;

    /**
     * Initialize a new instance of CSVFileModel. Generally, you would want to
     * call registerFileObserver(FileObserver o) to register at least one observer
     * and then call setModel(String path) to update the Model and any observers.
     */
    public CSVFileModel()
    {
        observers = new ArrayList<FileObserver>();
    }

    /**
     * Set the path to the file that defines the new Model.
     * @param path to the file containing the model data
     */
    @Override
    public void setModel(String path)
    {
        Iterator[] iterator = new FileParser(new CSVParserFactory()).parseFile(path);

        IteratorToDoubleArray conversion = new IteratorToDoubleArray();

         array = new double[iterator.length][];

        int count = 0;
        for (Iterator i : iterator)
        {
            Iterator doubleIterator = new StringToDouble().parseString(i);
            array[count] = conversion.reversal(doubleIterator);
            count++;
        }

        notifyFileObservers();
    }

    /**
     * Register a new observer.
     * @param o
     */
    @Override
    public void registerFileObserver(FileObserver o)
    {
        observers.add(o);
    }

    /**
     * Remove an observer.
     * @param o
     */
    @Override
    public void removeFileObserver(FileObserver o)
    {
        int i = observers.indexOf(o);
        if (i >= 0)
        {
            observers.remove(i);
        }
    }

    /**
     * Notify all observers that there has been a change in the model.
     */
    @Override
    public void notifyFileObservers()
    {
        for(int i = 0; i < observers.size(); i++)
        {
            FileObserver observer = (FileObserver)observers.get(i);
            observer.updateMatrix(array);
        }
    }
}
