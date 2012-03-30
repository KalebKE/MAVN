/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simModel.algorithm.model.timer.observer;

import java.util.ArrayList;
import mavn.simModel.algorithm.model.point.Point;

/**
 *
 * @author Kaleb
 */
public interface TimerAlgorithmModelObserver
{
    public void updateTimerModel(ArrayList<Point> timerPoints);
}
