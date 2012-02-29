/*
Globals -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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
package mavn.globals;

/**
 * Global for the application. This class is usually used for defining
 * the locations of individual objects in their interface collections. 
 * @author Kaleb
 */
public class Globals
{
    
    public static int NUM_DARTS = 1000;
    public static double SEED = 0;

    public final static int TARGET_CONTROLLER = 0;
    public final static int THETA_CONTROLLER = 1;
    public final static int W0_CONTROLLER = 2;
    public final static int W1_CONTROLLER = 3;
    public final static int W2_CONTROLLER = 4;

    public final static int TARGET_MODEL = 0;
    public final static int THETA_MODEL = 1;
    public final static int W0_MODEL = 2;
    public final static int W1_MODEL = 3;
    public final static int W2_MODEL = 4;


    public final static int TARGET_PANEL = 0;
    public final static int THETA_PANEL = 1;
    public final static int W0_PANEL = 2;
    public final static int W1_PANEL = 3;
    public final static int W2_PANEL = 4;
    

    public final static int AND_LAYER_MODEL = 0;
    public final static int OR_LAYER_MODEL = 1;
    public final static int OUTPUT_LAYER_MODEL = 2;
    public final static int SIMULATION_RESULT = 3;
    public final static int SHAPES_RATIO_RESULT = 4;
    public final static int IMAGE_RATIO_RESULT = 5;

    public final static int DART_RESULT_MODEL = 0;

    public final static int TARGET_STATE = 0;
    public final static int THETA_STATE = 1;
    public final static int W0_STATE = 2;
    public final static int W1_STATE = 3;
    public final static int W2_STATE = 4;

    public final static int RESULTS_CONTROLLER = 0;
    public final static int RESULTS_STATE = 0;

    public final static int CELLULAR_AUTOMATON_DARTGUN = 0;
    public final static int CMWC4096_DARTGUN = 1;
    public final static int JAVA_RNG_DARTGUN = 2;
    public final static int MT_DARTGUN = 3;
    public final static int XOR_DARTGUN = 4;

    public final static int SINGLE_POINT_ALGORITHM = 0;
    public final static int CELLULAR_AUTOMATON_ALGORITHM = 1;
    public final static int CMWC4096_ALGORITHM = 2;
    public final static int JAVA_RNG_ALGORITHM = 3;
    public final static int MT_ALGORITHM = 4;
    public final static int XOR_ALGORITHM = 5;
}
