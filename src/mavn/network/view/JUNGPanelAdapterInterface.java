/*
JUNGPanelAdapterInterface -- an interface within the Machine Artificial Vision
Networkm (Machine Artificial Vision Network).
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
package mavn.network.view;

/**
 * An interface that defines the required methods to adapt the MAVN Network
 * into a network that can be rendered by JUNG's Graphical Network library.
 * @author Kaleb
 */
public interface JUNGPanelAdapterInterface
{
    public void fireNodes(double[] result);
    public void setNetwork(double[][] w2, double[][] w1, double[][] w0);
}
