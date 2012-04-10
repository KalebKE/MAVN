/*
AndLayerAlgorithmModelObserver --
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
package mavn.algorithm.model.network.observer;

/**
 * A hook for classes that want to observe updates to the And Layer Algorithm
 * Model's State during a simulation. For each set of inputs, the And Layer
 * Network Nodes either go high, 1, or stay low, -1. 
 * @author Kaleb
 */
public interface AndLayerAlgorithmModelObserver
{

    /**
     * Hook for classes to Observe Subject updates to the And Layer Algorithm
     * Model's State
     * @param results the output of the And Layer Network Nodes updated one per
     * input set.
     */
    public void updateAndLayerModelOutput(double[][] andLayerOutput);
}
