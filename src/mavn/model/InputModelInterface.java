/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.model;

import java.util.ArrayList;

/**
 * An abstract class interface where all of the key implementation is shared by
 * the child classes. Child classes can add special functionality if needed.
 * @author Kaleb
 */
public abstract class InputModelInterface
{
    protected ArrayList matrixObservers;
    protected double[][] matrix;

    public abstract void notifyObservers();
 
    public void setMatrix(double[][] matrix)
    {
        this.matrix = matrix;
        notifyObservers();
    }
}
