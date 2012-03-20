/*
ResultsState -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.simModel.output.view.state;

import simulyn.ui.components.BlinkerButton;
import simulyn.ui.components.outputModelPanel.SimulynDefaultOutputView;

/**
 * A class that maintains all of the state required for the simulations output.
 * This class is an implementation of the applications State Pattern.
 * @author Kaleb
 */
public class OuputState implements OutputModelStateInterface
{
    private SimulynDefaultOutputView view;
    private boolean animated;
    private boolean propertiesLoaded;
    private boolean simulationLoaded;
    private boolean resultAvailable;

    /**
     * Initialize the state.
     * @param view the view the class manages the state for.
     */
    public OuputState(SimulynDefaultOutputView view)
    {
        this.view = view;
        propertiesLoaded = false;

        this.propertiesUnloaded();
        this.simulationUnloaded();
    }

    /**
     * Check if the simulation has been run.
     * @return boolean indicating a simulation has been run.
     */
    @Override
    public boolean isResultAvailable()
    {
        return resultAvailable;
    }

    

    /**
     * Check to see if the simulation properties have been loaded.
     * @return boolean indicating loaded simulation properties.
     */
    @Override
    public boolean isPropertiesLoaded()
    {
        return propertiesLoaded;
    }

   

    /**
     * Check to see if a simulation has been loaded.
     * @return boolean indicating a simulation has been loaded.
     */
    @Override
    public boolean isSimulationLoaded()
    {
        return simulationLoaded;
    }

    

    /**
     * Inidcate that the simulation properties have been loaded.
     */
    @Override
    public void propertiesLoaded()
    {
        // Disable these buttons
        view.getRunButton().setEnabled(true);
        ((BlinkerButton) view.getRunButton()).setBlink(true);
        propertiesLoaded = true;
    }

    /**
     * Inidicate that the simulation properties have not been loaded.
     */
    @Override
    public void propertiesUnloaded()
    {
        view.getPropertiesButton().setEnabled(true);
        ((BlinkerButton) view.getPropertiesButton()).setBlink(true);
        // Disable these buttons
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getSaveOutputButton().setEnabled(false);
        ((BlinkerButton) view.getSaveOutputButton()).setBlink(false);
        view.getClearOutputButton().setEnabled(false);
        ((BlinkerButton) view.getClearOutputButton()).setBlink(false);
        propertiesLoaded = false;
        resultAvailable = false;
    }

    /**
     * Indicate that the simulation has been run.
     */
    @Override
    public void resultAvailable()
    {
        // Disable these buttons
        view.getRunButton().setEnabled(true);
        ((BlinkerButton) view.getRunButton()).setBlink(true);
        view.getSaveOutputButton().setEnabled(true);
        ((BlinkerButton) view.getSaveOutputButton()).setBlink(true);
        view.getClearOutputButton().setEnabled(true);
        ((BlinkerButton) view.getClearOutputButton()).setBlink(true);
        resultAvailable = true;
    }

    

    /**
     * Indicate that a simulation has been loaded.
     */
    @Override
    public void simulationLoaded()
    {
        // Disable these buttons
        view.getPropertiesButton().setEnabled(true);
        ((BlinkerButton) view.getPropertiesButton()).setBlink(true);
        view.getLoadModelButton().setEnabled(true);
        ((BlinkerButton) view.getLoadModelButton()).setBlink(false);
    }

    /**
     * Indicate that a simulation has not been loaded.
     */
    @Override
    public void simulationUnloaded()
    {
        // Disable these buttons
        view.getLoadModelButton().setEnabled(true);
        ((BlinkerButton) view.getLoadModelButton()).setBlink(true);
        view.getPropertiesButton().setEnabled(false);
        ((BlinkerButton) view.getPropertiesButton()).setBlink(false);
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getSaveOutputButton().setEnabled(false);
        ((BlinkerButton) view.getSaveOutputButton()).setBlink(false);
        view.getClearOutputButton().setEnabled(false);
        ((BlinkerButton) view.getClearOutputButton()).setBlink(false);
        simulationLoaded = false;
    }

    @Override
    public void animate(boolean animate)
    {
        this.animated = animate;
    }

    @Override
    public boolean isAnimated()
    {
        return animated;
    }
}
