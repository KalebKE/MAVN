/*
TimerAlgorithmModelInterface -- An Interface within the Machine Artificial
Vision Network(Machine Artificial Vision Network).
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
package mavn.algorithm.model.timer;

import mavn.algorithm.model.timer.observer.TimerAlgorithmModelObserver;

/**
 * An interface for classes that create Timer Points for MAVN Multiple Point Simulations.
 * Points are represented by a Java Point containing an X and Y coordinate
 * for a two dimensional plane. These Timer Points determine how long it took
 * each pair of inputs to run through the simulation's algorithm.
 *
 * TimerAlgorithmModelInterface is intended to be a Subject in an Observer pattern.
 * Observers can implement TimerInputModelObserver to hook into
 * TimerAlgorithmModelInterface implementations. They will the be notified when
 * new State is availble.
 * @author Kaleb
 */
public interface TimerAlgorithmModelInterface
{

    /**
     * Notify observers.
     */
    public void notifyTimerModelObservers();

    /**
     * Register a PointGeneratorModelObserver.
     * @param o the PointGeneratorModelObserver
     */
    public void registerObserver(TimerAlgorithmModelObserver o);

    /**
     * Remove a PointGeneratorModelObserver
     * @param o the PointGeneratorModelObserver
     */
    public void removeObserver(TimerAlgorithmModelObserver o);
}
