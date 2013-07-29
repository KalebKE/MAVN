package mavn.algorithm.model.math;

import java.util.Arrays;

public class Histogram
{

    public double[] getBinCount(double[] values)
    {
        int numBins = (int) Math.sqrt(values.length);

        double[] binIndex = new double[numBins];

        double[] binRange = getBinRange(values);

        for (int i = 0; i < values.length; i++)
        {
            for (int j = 0; j < binRange.length - 1; j++)
            {
                if (binRange[j] < values[i] && values[i] < binRange[j + 1])
                {
                    binIndex[j]++;
                }
            }
        }

        return binIndex;
    }

    public double[] getBinRange(double[] values)
    {
        double[] data = new double[values.length];

        for (int i = 0; i < data.length; i++)
        {
            data[i] = values[i];
        }

        int numBins = (int) Math.sqrt(values.length);

        Arrays.sort(data);

        double[] x = new double[numBins + 1];

        for (int i = 0; i < x.length; i++)
        {
            x[i] = data[numBins * i];
        }

        return x;
    }

    private double getMinValue(double[] values)
    {
        double min = values[0];

        for (double x : values)
        {
            if (x < min)
            {
                min = x;
            }
        }

        return min;
    }
}