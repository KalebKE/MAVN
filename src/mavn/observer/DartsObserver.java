/*
DartsObserver -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.observer;

import java.util.ArrayList;

/**
 * A hook for classes that want to be notified of when the results of the
 * dart simulation have changed.
 * @author Kaleb
 */
public interface DartsObserver
{
    /**
     * Hook for observers to be notified of dart simulations.
     * @param hit the number of dart hits
     * @param miss the number of dart misses
     */
    public void updateDartResults(ArrayList<double[][]> hit, ArrayList<double[][]> miss);
}
