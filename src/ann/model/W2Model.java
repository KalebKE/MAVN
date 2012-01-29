/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ann.model;

import java.util.ArrayList;
import ann.observer.W2Observer;

/**
 * All updates to the W2 Matrix should be made through W2Model using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class W2Model extends MatrixModelInterface
{
    public W2Model()
    {
        matrixObservers = new ArrayList<W2Observer>();
    }

    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < matrixObservers.size(); i++)
        {
            W2Observer matrixObserver = (W2Observer) matrixObservers.get(i);
            matrixObserver.updateW2Matrix(matrix);
        }
    }
}
