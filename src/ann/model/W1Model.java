/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ann.model;

import java.util.ArrayList;
import ann.observer.W1Observer;

/**
 * All updates to the W2 Matrix should be made through W2Model using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model. 
 * @author Kaleb
 */
public class W1Model extends MatrixModelInterface
{
    public W1Model()
    {
        matrixObservers = new ArrayList<W1Observer>();
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
