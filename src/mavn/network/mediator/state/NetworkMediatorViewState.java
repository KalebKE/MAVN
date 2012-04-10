/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.network.mediator.state;

import mavn.simulation.view.controlBar.ControlBar;
import mavn.simulation.view.SimControlView;

/**
 *
 * @author Kaleb
 */
public class NetworkMediatorViewState implements NetworkMediatorViewStateInterface
{

    private boolean animated;
    private ControlBar outputViewBar;
    private ControlBar inputViewBar;
    private SimControlView view;

    public NetworkMediatorViewState(ControlBar outputViewBar, ControlBar inputViewBar, SimControlView view)
    {
        this.outputViewBar = outputViewBar;
        this.inputViewBar = inputViewBar;
        this.view = view;
    }
    
    @Override
    public void setAnimated(boolean animate)
    {
        this.animated = animate;
        if (animate)
        {
            view.getAnimateNetworkMenuItem().setSelected(true);
            view.getRenderNetworkMenuItem().setSelected(false);

            outputViewBar.getAnimateSimulationButton().getModel().setPressed(true);
            outputViewBar.getAnimateSimulationButton().getModel().setSelected(true);

            inputViewBar.getAnimateSimulationButton().getModel().setPressed(true);
            inputViewBar.getAnimateSimulationButton().getModel().setSelected(true);

            outputViewBar.getRenderSimulationButton().getModel().setPressed(false);
            outputViewBar.getRenderSimulationButton().getModel().setSelected(false);

            inputViewBar.getRenderSimulationButton().getModel().setPressed(false);
            inputViewBar.getRenderSimulationButton().getModel().setSelected(false);
        }
        if (!animate)
        {
            view.getAnimateNetworkMenuItem().setSelected(false);
            view.getRenderNetworkMenuItem().setSelected(true);

            outputViewBar.getRenderSimulationButton().getModel().setPressed(true);
            outputViewBar.getRenderSimulationButton().getModel().setSelected(true);

            inputViewBar.getRenderSimulationButton().getModel().setPressed(true);
            inputViewBar.getRenderSimulationButton().getModel().setSelected(true);

            outputViewBar.getAnimateSimulationButton().getModel().setPressed(false);
            outputViewBar.getAnimateSimulationButton().getModel().setSelected(false);

            inputViewBar.getAnimateSimulationButton().getModel().setPressed(false);
            inputViewBar.getAnimateSimulationButton().getModel().setSelected(false);
        }
    }

    @Override
    public boolean isAnimated()
    {
        return animated;
    }
}
