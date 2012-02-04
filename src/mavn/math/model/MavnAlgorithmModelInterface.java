/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.math.model;

import mavn.observer.ResultsObserver;

/**
 *
 * @author Kaleb
 */
public interface MavnAlgorithmModelInterface
{
    public void calculate();

    public void registerObserver(ResultsObserver o);

    public void removeObserver(ResultsObserver o);

    public void notifyObservers();
}
