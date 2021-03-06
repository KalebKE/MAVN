/*
PlotMediatorViewStateInterface -- an Interface within the Machine Artificial
Vision Network (Machine Artificial Vision Network).
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
package mavn.plot.mediator.state;

/**
 * An interface to manage the two types of Plots used within the simulation.
 *
 * @author Kaleb
 */
public interface PlotMediatorViewStateInterface
{
    /**
     * Implement a Line Plot based on the simulation's Output Models.
     */
    public void onLinePlot();

    /**
     * Implement a Scatter Plot based on the simulation's Output Models.
     */
    public void onScatterPlot();
}
