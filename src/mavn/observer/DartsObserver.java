/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.observer;

import java.util.ArrayList;

/**
 *
 * @author Kaleb
 */
public interface DartsObserver
{
    public void updateDartResults(ArrayList<double[][]> hit, ArrayList<double[][]> miss);
}
