/*
SimulationPropertiesStateInterface -- an interface within the Machine Artificial
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
package mavn.algorithm.properties.view.state;

import javax.swing.SpinnerNumberModel;

/**
 * An interface for the Simulation Properties State. Simulation Properties
 * State manages all of the State related to running a simulation. 
 * Implementations define what State should be enabled for the application under
 * specific conditions.
 * @author Kaleb
 */
public interface SimulationPropertiesStateInterface
{

    /**
     * Get the Spinner Model for the Pixel Grid Simulation.
     * @return a Spinner Model containing the desired resolution for the
     * Pixel Grid Simulation.
     */
    public SpinnerNumberModel getPixelGridSpinnerModel();

    /**
     * Get the Spinner Model for the Monte Carlo Simulation.
     * @return SpinnerNumberModel representing the number of Points to be fired
     * during the simulation.
     */
    public SpinnerNumberModel getMonteCarloSpinnerModel();

    /**
     * Get the Monte Carlo Simulation Seed Model.
     * @return SpinnerNumberModel representing the random number seed that should
     * be used during the simulation.
     */
    public SpinnerNumberModel getSeedModel();

    /**
     * Check if CA RNG is desired.
     * @return boolean indicating CA RNG is desired.
     */
    public boolean isCaRng();

    /**
     * Check if CMWC RNG is desired.
     * @return boolean indicating CMWC RNG  is desired.
     */
    public boolean isCmwcRng();

    /**
     * Check to see if the Diagnostic Simulation is enabled.
     * @return boolean indicating if a single dart target is desired.
     */
    public boolean isDiagnosticSimulation();

    /**
     * Check to see if the Monte Carlo Simulation is enabled.
     * @return boolean indicating if the Monte Carlo Simulation is enabled.
     */
    public boolean isMonteCarloSimulation();

    /**
     * Check if Mt Rng is desired.
     * @return boolean indicating Mt Rng is desired.
     */
    public boolean isMtRng();

    /**
     * Check to see if the Pixel Grid Simulation is enabled.
     * @return boolean indicating if the Pixel Grid Simulation is enabled.
     */
    public boolean isPixelGridSimulation();

    /**
     * Check if java.util.random RNG is desired.
     * @return boolean indicating java.util.random RNG is desired.
     */
    public boolean isRandomRng();

    /**
     * Check if a random seed is desired.
     * @return boolean indicating if a random seed is desired.
     */
    public boolean isRandomSeed();

    /**
     * Check if XORRng is desired.
     * @return boolean indicating XORRng is desired.
     */
    public boolean isXORRng();

    /**
     * Use the CA RNG.
     */
    public void onCaRng();

    /**
     * Indicate that mtRNG is desired.
     */
    public void onMtRng();

    /**
     * Use the CMWC RNG.
     */
    public void onCmwcRng();

    /**
     * Indicate that the Diagnostic Simulation should be enabled.
     */
    public void onDiagnosticSimulation();

    /**
     * Indicate that the Monte Carolo Simulation should be enabled.
     */
    public void onMonteCarloSimulation();

    /**
     * Indicate that the Pixel Grid Simulation should be enabled.
     */
    public void onPixelGridSimulation();

    /**
     * Indicate that the RNG should use a specific seed.
     */
    public void onSpecifiedRngSeed();

    /**
     * Indicate that the RNG should use a random seed.
     */
    public void onRandomRngSeed();

    /**
     * Inidcate that XORSHIFT RNG should be used.
     */
    public void onXORRng();
    /**
     * Indicate that java.util.random RNG is desired.
     */
    public void randomRng();
}
