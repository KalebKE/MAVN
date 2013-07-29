/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.simulation.mediator;

import mavn.algorithm.model.point.Point;
import mavn.output.view.layout.ModelOutputInfographic;
import mavn.plot.model.PointHitOutputModel;
import mavn.plot.model.PointHitOutputModelInterface;
import mavn.plot.model.PointMissOutputModel;
import mavn.plot.model.PointMissOutputModelInterface;
import mavn.plot.model.observer.PointHitOutputModelObserver;
import mavn.plot.model.observer.PointMissOutputModelObserver;
import simulyn.output.view.mediator.OutputViewMediatorInterface;

/**
 *
 * @author Kaleb
 */
public class InfographicMediator implements OutputViewMediatorInterface, PointHitOutputModelObserver,
        PointMissOutputModelObserver
{

    private PointHitOutputModelInterface pointHitOutputModel;
    private PointMissOutputModelInterface pointMissOutputModel;
    
    private double hit = 0;
    private double miss = 0;
    private int points = 0;
    private double ratio = 0;
    private ModelOutputInfographic outputInfographic;

    public InfographicMediator(PointHitOutputModelInterface pointHitOutputModel, PointMissOutputModelInterface pointMissOutputModel)
    {
        this.pointHitOutputModel = pointHitOutputModel;
        this.pointMissOutputModel = pointMissOutputModel;
        
        outputInfographic = new ModelOutputInfographic();

        // this class should observe changes to the result model
        ((PointHitOutputModel) this.pointHitOutputModel).registerObserver((PointHitOutputModelObserver) this);
        // this class should observe changes to the result model
        ((PointMissOutputModel) this.pointMissOutputModel).registerObserver((PointMissOutputModelObserver) this);
    }

    @Override
    public void updatePointHit(Point point)
    {
        hit++;
        points++;
        updateRatio();
    }

    @Override
    public void updatePointMiss(Point point)
    {
        miss++;
        points++;
        updateRatio();
    }

    private void updateRatio()
    {
        ratio = hit / miss;

        outputInfographic.setPointsValue(points);
        outputInfographic.setHitValue(hit);
        outputInfographic.setMissValue(miss);
        outputInfographic.setRatioValue(ratio);
    }

    @Override
    public void updateUI()
    {
    }

    public ModelOutputInfographic getOutputInfographic()
    {
        return outputInfographic;
    }
    
    
}
