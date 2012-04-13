/*
OutputLayerAlgorithmModelInterface --
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

import mavn.algorithm.model.network.observer.OutputLayerAlgorithmModelObserver;

/**
 * An interface for MAVN Simulations that are Subjects for Output Layer Algorithm
 * Model Observers. These Observers are interested in the State of the Output
 * Layer Nodes within the Network for each set of inputs. A node will return
 * 1 if it went high, or -1 if it stayed low for each set of inputs.
 * @author Kaleb
 */
public interface OutputLayerAlgorithmModelInterface
{

    /**
     * Notify Output Layer Algorithm Model Interface.
     */
    public void notifyOutputLayerObservers();

    /**
     * Register a OutputLayerAlgorithmModelInterface.
     * @param o the OutputLayerAlgorithmModelInterface
     */
    public void registerObserver(OutputLayerAlgorithmModelObserver o);

    /**
     * Remove a OutputLayerAlgorithmModelInterface
     * @param o the DartObserver
     */
    public void removeObserver(OutputLayerAlgorithmModelObserver o);
}
