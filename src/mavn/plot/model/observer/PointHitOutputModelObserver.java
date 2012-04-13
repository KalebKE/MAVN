/*
PointHitOutputModel-- a class within the Machine Artificial Vision Network
(Machine Artificial Vision Network).
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
package mavn.plot.model.observer;

import mavn.algorithm.model.point.Point;

/**
 * Point Hit Output Model Observers are classes that want to Observer the
 * Algorithm Model Subject and be updated when a Point hits a shape in the image
 * in real-time. This differers from Point Ouput Model Observers in that the
 * Point Output Model Observers update both the Hit and the Miss Point when the
 * simulation has finished running. Point Hit Ouput Model Observers only
 * update one Hit Point at a time while the simulation is running.
 * @author Kaleb
 */
public interface PointHitOutputModelObserver
{
    /**
     * Update the Point that hit a shape within the image.
     * @param point the Point that hit a shape within the image.
     */
    public void updatePointHit(Point point);
}
