/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.observer;

/**
 * W2Observer provides a common interface for all classes that need to
 * be notified of new matricies to hook into.
 * @author Kaleb
 */
public interface W1Observer
{
    public void updateW1Matrix(double[][] matrix);
}