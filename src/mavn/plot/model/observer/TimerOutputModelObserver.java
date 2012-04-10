/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.plot.model.observer;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;

/**
 *
 * @author Kaleb
 */
public interface TimerOutputModelObserver
{
    public void updateTimerOutput(ArrayList<Point> timerPoint);
}
