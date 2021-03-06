/*
AndLayerAlgorithmModelInterface --
A class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.algorithm.model.network;

import mavn.algorithm.model.network.observer.AndLayerAlgorithmModelObserver;

/**
 * An interface for Subject classes that manage And Layer Network Model State.
 * Each Node within the AND Layer of the MAVN Network either goes high, 1,
 * or stays low, -1, for each set of inputs.
 * @author Kaleb
 */
public interface AndLayerAlgorithmModelInterface
{
    /**
     * Notify all AND Layer Algorithm Model Observers.
     */
    public void notifyAndLayerObservers();

    /**
     * Register a AndLayerAlgorithmModelObserver.
     * @param o the AndLayerAlgorithmModelObserver
     */
    public void registerObserver(AndLayerAlgorithmModelObserver o);

    /**
     * Remove a AndLayerAlgorithmModelObserver
     * @param o the AndLayerAlgorithmModelObserver
     */
    public void removeObserver(AndLayerAlgorithmModelObserver o);
}
