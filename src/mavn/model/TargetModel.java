/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.model;

import java.util.ArrayList;
import mavn.observer.TargetObserver;


/**
 * All updates to the W2 Matrix should be made through W2Model using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class TargetModel extends MatrixModelInterface
{
    public TargetModel()
    {
        matrixObservers = new ArrayList<TargetObserver>();
    }

    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < matrixObservers.size(); i++)
        {
            TargetObserver matrixObserver = (TargetObserver) matrixObservers.get(i);
            matrixObserver.updateTargetMatrix(matrix);
        }
    }
}
