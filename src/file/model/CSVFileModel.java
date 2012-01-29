/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file.model;

import ann.util.IteratorToDoubleArray;
import ann.util.StringToDouble;
import file.observer.FileObserver;
import util.model.parse.CSVParserFactory;
import util.model.parse.FileParser;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Kaleb
 */
public class CSVFileModel implements FileModelInterface
{
    private ArrayList<FileObserver> observers;
    private double[][] array;

    public CSVFileModel()
    {
        observers = new ArrayList<FileObserver>();
    }

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

    @Override
    public void registerFileObserver(FileObserver o)
    {
        observers.add(o);
    }

    @Override
    public void removeFileObserver(FileObserver o)
    {
        int i = observers.indexOf(o);
        if (i >= 0)
        {
            observers.remove(i);
        }
    }

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
