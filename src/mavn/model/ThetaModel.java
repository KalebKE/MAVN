/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.model;

import java.util.ArrayList;
import mavn.observer.ThetaObserver;
import mavn.observer.W0Observer;

/**
 * All updates to the W2 Matrix should be made through W2Model using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class ThetaModel extends InputModelInterface
{

    public ThetaModel()
    {
        matrixObservers = new ArrayList<ThetaObserver>();
    }

    public void registerObserver(ThetaObserver o)
    {
        matrixObservers.add(o);
    }

    public void removeObserver(ThetaObserver o)
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
            ThetaObserver matrixObserver = (ThetaObserver) matrixObservers.get(i);
            matrixObserver.updateThetaMatrix(matrix);
        }
    }
}
