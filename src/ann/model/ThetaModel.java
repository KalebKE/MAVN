/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ann.model;

import java.util.ArrayList;
import ann.observer.ThetaObserver;
import ann.observer.W0Observer;

/**
 * All updates to the W2 Matrix should be made through W2Model using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class ThetaModel extends MatrixModelInterface
{
    public ThetaModel()
    {
        matrixObservers = new ArrayList<ThetaObserver>();
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
