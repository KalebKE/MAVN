/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mavn.util;

/**
 *
 * @author Kaleb
 */
public class FindMax
{
    public static double getMaxValue(double[][] numbers)
    {
        double maxValue = numbers[0][0];
        for (int i = 0; i < numbers.length; i++)
        {
            for (int j = 0; j < numbers.length; j++)
            {
                if (numbers[i][j] > maxValue)
                {
                    maxValue = numbers[i][j];
                }
            }
        }
        return maxValue;
    }
}