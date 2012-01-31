/*
Controller -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.controller;

import mavn.model.MatrixModelInterface;
import mavn.view.ControlFrame;
import file.observer.FileObserver;
import mavn.math.MavnAlgorithmModelInterface;
import mavn.math.MavnSinglePointModel;

/**
 *
 * @author Kaleb
 */
public class OutputController implements FileObserver, OutputControllerInterface
{
    private ControlFrame mainView;
    private MatrixModelInterface model;
    private MavnAlgorithmModelInterface mavn;

    public OutputController(ControlFrame mainView, MatrixModelInterface model)
    {
        this.mainView = mainView;
        this.model = model;
    }

    @Override
    public void runMavnAlgorithm()
    {
        mavn = new MavnSinglePointModel(mainView);
        mavn.registerObserver(mainView);
        mavn.calculate();
    }

    @Override
    public void updateMatrix(double[][] matrix)
    {
        model.setMatrix(matrix);
    }
}
