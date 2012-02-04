/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.state;

import mavn.view.ControlFrame;
import util.components.BlinkerButton;

/**
 *
 * @author Kaleb
 */
public class ResultsState implements OutputStateInterface
{

    private ControlFrame view;
    private boolean propertiesLoaded;
    private boolean simulationLoaded;
    private boolean run;

    public ResultsState(ControlFrame view)
    {
        this.view = view;
        propertiesLoaded = false;
    }

    @Override
    public void simulationLoaded()
    {
        // Disable these buttons
        view.getPropertiesButton().setEnabled(true);
        ((BlinkerButton) view.getPropertiesButton()).setBlink(true);
    }

    @Override
    public void simulationUnloaded()
    {
        // Disable these buttons
        view.getPropertiesButton().setEnabled(false);
        ((BlinkerButton) view.getPropertiesButton()).setBlink(false);
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getViewResultsButton().setEnabled(false);
        ((BlinkerButton) view.getViewResultsButton()).setBlink(false);
        view.getSaveResultsButton().setEnabled(false);
        ((BlinkerButton) view.getSaveResultsButton()).setBlink(false);
        view.getClearResultsMatrixButton().setEnabled(false);
        ((BlinkerButton) view.getClearResultsMatrixButton()).setBlink(false);
        simulationLoaded = false;
    }

    @Override
    public void propertiesLoaded()
    {
        // Disable these buttons
        view.getRunButton().setEnabled(true);
        ((BlinkerButton) view.getRunButton()).setBlink(true);
        propertiesLoaded = true;
    }

    @Override
    public void propertiesUnloaded()
    {
        // Disable these buttons
        view.getRunButton().setEnabled(false);
        ((BlinkerButton) view.getRunButton()).setBlink(false);
        view.getSaveResultsButton().setEnabled(false);
        ((BlinkerButton) view.getSaveResultsButton()).setBlink(false);
        view.getClearResultsMatrixButton().setEnabled(false);
        ((BlinkerButton) view.getClearResultsMatrixButton()).setBlink(false);
        propertiesLoaded = false;
        run = false;
    }

    @Override
    public void run()
    {
        // Disable these buttons
        view.getRunButton().setEnabled(true);
        ((BlinkerButton) view.getRunButton()).setBlink(true);
        view.getViewResultsButton().setEnabled(true);
        ((BlinkerButton) view.getViewResultsButton()).setBlink(true);
        view.getSaveResultsButton().setEnabled(true);
        ((BlinkerButton) view.getSaveResultsButton()).setBlink(true);
        view.getClearResultsMatrixButton().setEnabled(true);
        ((BlinkerButton) view.getClearResultsMatrixButton()).setBlink(true);
        run = true;
    }

    @Override
    public boolean isPropertiesLoaded()
    {
        return propertiesLoaded;
    }

    @Override
    public boolean isSimulationLoaded()
    {
        return simulationLoaded;
    }

    @Override
    public boolean hasRun()
    {
        return run;
    }
}
