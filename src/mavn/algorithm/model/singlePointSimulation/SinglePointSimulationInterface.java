/*
SinglePointModelInterface --
An interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
Copyright (C) 2012, Kaleb Kircher, Dennis Steele.

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
package mavn.algorithm.model.singlePointSimulation;

import mavn.algorithm.model.singlePointSimulation.observer.SinglePointAlgorithmModelObserver;

/**
 * A Single Point Model is intended to be used as testing or educational
 * tool. It fires a single Point defined by the user in the Target InputModel
 * at the image. While this is powerful tool to ensure that your simulation's model
 * is working correctly and a good way of demonstrating how the MAVN Network
 * works, it is not practical for pattern recognition which require a large
 * number of Points to be fired at the image.
 * SinglePointModelInterface defines a Subject interface for an Observer Pattern
 * to keep the Algorithm's Model highly decoupled from classes interested it
 * its State.
 * @author Kaleb
 */
public interface SinglePointSimulationInterface
{

    /**
     * Notify Observers that new Single Point Model State is available.
     */
    public void notifySinglePointModelObserver();

    /**
     * Register a Single Point Algorithm Model Observer.
     * @param o the SinglePointAlgorithmModelObserver.
     */
    public void registerObserver(SinglePointAlgorithmModelObserver o);

    /**
     * Remove a Single Point Algorithm Model Observer.
     * @param o the SinglePointAlgorithmModelObserver.
     */
    public void removeObserver(SinglePointAlgorithmModelObserver o);
}
