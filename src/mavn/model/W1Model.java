/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.model;

import java.util.ArrayList;
import mavn.observer.W1Observer;

/**
 * All updates to the W2 Matrix should be made through W2Model using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class W1Model extends InputModelInterface
{

    public W1Model()
    {
        matrixObservers = new ArrayList<W1Observer>();
    }

    public void registerObserver(W1Observer o)
    {
        matrixObservers.add(o);
    }

    public void removeObserver(W1Observer o)
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
            W1Observer matrixObserver = (W1Observer) matrixObservers.get(i);
            matrixObserver.updateW1Matrix(matrix);
        }
    }
}
