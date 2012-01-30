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
public class W0Model extends MatrixModelInterface
{
    public W0Model()
    {
        matrixObservers = new ArrayList<W0Observer>();
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
}
