/*
NetworkMediatorViewState -- A class within the Machine Artificial Vision
Network(Machine Artificial Vision Network).
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
package mavn.network.mediator.state;

import mavn.simulation.view.controlBar.ControlBar;
import mavn.simulation.view.SimControlView;

/**
 * NetworkMediatorViewState manages all of the View State required to manage the
 * graphical representation of the simulations network.
 * @author Kaleb
 */
public class NetworkMediatorViewState implements NetworkMediatorViewStateInterface
{

    private boolean animated;
    private ControlBar outputViewBar;
    private ControlBar inputViewBar;
    private SimControlView view;

    /**
     * Initialize a new NetworkMediatorViewState.
     * @param outputViewBar the Simulation Control Bar for the Output View
     * @param inputViewBar  the Simulation Control Bar for the Input View
     * @param view the Simulation Control View.
     */
    public NetworkMediatorViewState(ControlBar outputViewBar,
            ControlBar inputViewBar, SimControlView view)
    {
        this.outputViewBar = outputViewBar;
        this.inputViewBar = inputViewBar;
        this.view = view;
    }

    /**
     * Check if the network should be animated.
     * @return boolean indicating if the network should be animated.
     */
    @Override
    public boolean isAnimated()
    {
        return animated;
    }

    /**
     * Set if the network should be Animated.
     * @param animate boolean indicating if the network should be animated.
     */
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
}
