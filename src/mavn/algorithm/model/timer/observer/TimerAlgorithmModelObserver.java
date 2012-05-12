/*
TimerAlgorithmModelObserver -- An interface within the Machine Artificial Vision
Network (Machine Artificial Vision Network).
Copyright (C) 2012, Kaleb Kircher.

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
package mavn.algorithm.model.timer.observer;

import java.util.ArrayList;
import mavn.algorithm.model.point.Point;

/**
 * A hook for classes that want to Observe updates to the Timer
 * Algorithm Model Subject. Timer Algorithm Model's know
 * how long it took pair of inputs to be processed by the simulation.
 * It is updated once when the simulation has finished running. State is pushed
 * to the Observers by the Subject.
 * @author Kaleb
 */
public interface TimerAlgorithmModelObserver
{
    /**
     * Hook for classes to Observe updates to the Timer Algorithm Model
     * Subject.
     * @param timerPoints the time Points from each pair of inputs.
     */
    public void updateTimerModel(ArrayList<Point> timerPoints);
}
