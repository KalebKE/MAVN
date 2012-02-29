/*
W0Observer -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.input.model.observer;

/**
 * W0Observer provides a common interface for all classes that need to
 * be notified of updates to the simulations Theta dat to hook into.
 * @author Kaleb
 */
public interface W0ModelObserver
{

    /**
     * The hook for classes that need to observe updates to the
     * simulations W0 data.
     * @param matrix the matrix containing the data.
     */
    public void updateW0ModelInput(double[][] modelInput);
}
