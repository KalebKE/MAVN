/*
SinglePointAlgorithmModelObserver --
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
package mavn.algorithm.model.singlePointSimulation.observer;

/**
 * A hook for classes that want to Observe updates to the Single Point Algorithm
 * Model's State. Single Point Algorithm Model's return a 1 if the Point hit the
 * image and a -1 if the point missed the image. 
 * @author Kaleb
 */
public interface SinglePointAlgorithmModelObserver
{

    /**
     * A hook for classes that want to Observe updates to the Single Point Algorithm
     * Model's State.
     * @param results the Single Point Model State.
     */
    public void updateSinglePointModelOutput(double[][] modelOutput);
}
