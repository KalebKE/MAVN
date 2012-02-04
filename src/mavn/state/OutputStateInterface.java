/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mavn.state;

/**
 *
 * @author Kaleb
 */
public interface OutputStateInterface 
{
    public boolean isPropertiesLoaded();
    public boolean isSimulationLoaded();
    public boolean hasRun();
    public void simulationLoaded();
    public void simulationUnloaded();
    public void propertiesLoaded();
    public void propertiesUnloaded();
    public void run();
}
