/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ann.model;

import java.util.ArrayList;
import ann.observer.W2Observer;

/**
 * An abstract class interface where all of the key implementation is shared by
 * the child classes. Child classes can add special functionality if needed.
 * @author Kaleb
 */
public abstract class MatrixModelInterface
{
    protected ArrayList matrixObservers;
    protected double[][] matrix;

    public abstract void notifyObservers();
 
    public void setMatrix(double[][] matrix)
    {
        this.matrix = matrix;
        notifyObservers();
    }

    public void registerObserver(W2Observer o)
    {
        matrixObservers.add(o);
    }

    public void removeObserver(W2Observer o)
    {
        int i = matrixObservers.indexOf(o);
        if (i >= 0)
        {
            matrixObservers.remove(i);
        }
    }
}
