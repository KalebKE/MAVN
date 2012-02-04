/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.model;

import java.util.ArrayList;
import mavn.observer.W0Observer;

/**
 * All updates to the W2 Matrix should be made through W2Model using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class W0Model extends InputModelInterface
{

    public W0Model()
    {
        matrixObservers = new ArrayList<W0Observer>();
    }

    public void removeObserver(W0Observer o)
    {
        int i = matrixObservers.indexOf(o);
        if (i >= 0)
        {
            matrixObservers.remove(i);
        }
    }

    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < matrixObservers.size(); i++)
        {
            W0Observer matrixObserver = (W0Observer) matrixObservers.get(i);
            matrixObserver.updateW0Matrix(matrix);
        }
    }

    public void registerObserver(W0Observer o)
    {
        matrixObservers.add(o);
    }
}
