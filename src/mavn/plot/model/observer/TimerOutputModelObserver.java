/*
TimerOutputModelObserver -- a class within the Machine Artificial Vision
Network(Machine Artificial Vision Network)Copyright (C) 2012, Kaleb Kircher.

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package mavn.plot.model.observer;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;

/**
 * Timer Output Model Observer's want to be notified of Timer Points
 * that represent how long each pair of Input took to be processed by the
 * simulations network. 
 * @author Kaleb
 */
public interface TimerOutputModelObserver
{

    /**
     * Hook for observers to be notified of the Timer Points from the
     * simulation.
     * @param timerPoint the Timer Points from the simulation.
     */
    public void updateTimerOutput(ArrayList<Point> timerPoint);
}
