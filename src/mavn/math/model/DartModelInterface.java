/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.math.model;

import mavn.observer.DartsObserver;

/**
 *
 * @author Kaleb
 */
public interface DartModelInterface
{

    public void registerObserver(DartsObserver o);

    public void removeObserver(DartsObserver o);

    public void notifyObservers();

    ;
}
