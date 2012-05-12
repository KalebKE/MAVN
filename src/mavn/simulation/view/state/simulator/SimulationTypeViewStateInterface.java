/*
SimulationTypeViewStateInterface -- An interface within the Machine Artificial
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
package mavn.simulation.view.state.simulator;

/**
 * SimulationTypeViewStateInterface maintains all of the View State required for 
 * selecting the type of simulation that will be run.
 * It enables and disables certain UI functionality based on the
 * Simulation Properties State.
 * @author Kaleb
 */
public interface SimulationTypeViewStateInterface
{

    /**
     * Indicate that a Diagnostic Simulation View is desired.
     */
    public void onDiagnosticSimluationView();

    /**
     * Indicate that a Monte Carlo Simulation View is desired.
     */
    public void onMonteCarloSimulationView();

    /**
     * Indicate a Pixel Grid Simulation View is desired.
     */
    public void onPixelGridSimulationView();

    /**
     * Indicate that the Simulation Input View is desired.
     */
    public void onSimulatorInputView();

    /**
     * Indicate that the Simulation Output View is desired.
     */
    public void onSimulatorOutputView();
}
