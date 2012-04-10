/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.view.state.output;

import mavn.simulation.view.controlBar.ControlBar;
import mavn.simulation.view.SimControlView;

/**
 *
 * @author Kaleb
 */
public class SimulationViewOutputState implements SimulationViewOutputStateInterface
{
    private boolean resultAvailable;
    private ControlBar outputViewBar;
    private ControlBar inputViewBar;
    private SimControlView view;

    public SimulationViewOutputState(ControlBar outputViewBar, ControlBar inputViewBar, SimControlView view)
    {
        this.outputViewBar = outputViewBar;
        this.inputViewBar = inputViewBar;
        this.view = view;
    }

    /**
     * Indicate that the simulation has been run.
     */
    @Override
    public void resultAvailable(boolean available)
    {
        if (available)
        {
            view.getRunSimulationMenuItem().setEnabled(true);
            view.getResetSimulationMenuItem().setEnabled(true);
            view.getSaveModelMenuItem().setEnabled(true);
            view.getClearOutputMenuItem().setEnabled(true);

            // Disable these buttons
            outputViewBar.getRunSimulationButton().setEnabled(true);
            outputViewBar.getSaveModelOutputButton().setEnabled(true);
            outputViewBar.getClearModelOutputButton().setEnabled(true);
            outputViewBar.getResetSimulationButton().setEnabled(true);

            // Disable these buttons
            inputViewBar.getRunSimulationButton().setEnabled(true);
            inputViewBar.getSaveModelOutputButton().setEnabled(true);
            inputViewBar.getClearModelOutputButton().setEnabled(true);
            inputViewBar.getResetSimulationButton().setEnabled(true);

            resultAvailable = true;
        }
        if (!available)
        {
            view.getRunSimulationMenuItem().setEnabled(false);
            view.getResetSimulationMenuItem().setEnabled(false);
            view.getSaveModelMenuItem().setEnabled(false);
            view.getClearOutputMenuItem().setEnabled(false);

            // Disable these buttons
            outputViewBar.getRunSimulationButton().setEnabled(false);
            outputViewBar.getSaveModelOutputButton().setEnabled(false);
            outputViewBar.getClearModelOutputButton().setEnabled(false);

            // Disable these buttons
            inputViewBar.getRunSimulationButton().setEnabled(false);
            inputViewBar.getSaveModelOutputButton().setEnabled(false);
            inputViewBar.getClearModelOutputButton().setEnabled(false);

            resultAvailable = false;
        }
    }

    public boolean isResultAvailable()
    {
        return resultAvailable;
    }
}
