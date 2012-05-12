/*
W1Model -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.input.model;

import java.util.ArrayList;
import mavn.input.model.observer.W1InputModelObserver;
import simulyn.input.model.InputModelInterface;

/**
 * The W1 Input Model keeps track of the current W1 Input for the simulation.
 * The W1 Input can come from an external file or be defined within the
 * simulation by the user.
 * The W1 Input essentially represents the edge connections between nodes from
 * the AND Layer to the OR Layer. It also required to dynamically produce
 * the ANDing function for the Input Layer Outputs.
 * All updates to the W1 Input Model should be made through W0InputModel using
 * setMatrix(double[][] matrix). This will notify all observers of the change
 * and provide them with the new model.
 * @author Kaleb
 */
public class W1InputModel extends InputModelInterface
{
    public W1InputModel()
    {
        modelInputObservers = new ArrayList<W1InputModelObserver>();
    }

    public void registerObserver(W1InputModelObserver o)
    {
        modelInputObservers.add(o);
    }

    public void removeObserver(W1InputModelObserver o)
    {
        int i = modelInputObservers.indexOf(o);
        if (i >= 0)
        {
            modelInputObservers.remove(i);
        }
    }

    @Override
    public void notifyObservers()
    {
        for (int i = 0; i < modelInputObservers.size(); i++)
        {
            W1InputModelObserver matrixObserver = (W1InputModelObserver) modelInputObservers.get(i);
            matrixObserver.updateW1InputModel(matrix);
        }
    }
}
