/*
OrLayerAlgorithmModelInterface --
An interface within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

import mavn.algorithm.model.network.observer.OrLayerAlgorithmModelObserver;

/**
 * An interface for MAVN Simulations that are Subjects for OR Layer Algorithm
 * Models. Each Node within the OR Layer of the MAVN Network either goes high, 1,
 * or stays low, -1, for each set of inputs. 
 * @author Kaleb
 */
public interface OrLayerAlgorithmModelInterface
{

    /**
     * Notify Or Layer Algorithm Model Observers.
     */
    public void notifyOrLayerObservers();

    /**
     * Register a OrLayerAlgorithmModelObserver.
     * @param o the OrLayerAlgorithmModelObserver
     */
    public void registerObserver(OrLayerAlgorithmModelObserver o);

    /**
     * Remove a OrLayerAlgorithmModelObserver.
     * @param o the OrLayerAlgorithmModelObserver
     */
    public void removeObserver(OrLayerAlgorithmModelObserver o);
}
