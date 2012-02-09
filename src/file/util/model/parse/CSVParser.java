/*
 CSVParser -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network).
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
package file.util.model.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import mavn.util.collections.TwoDimensionalArrayList;

/**
 * A concrete implementation of a file parser; implements MultipColumnParse.
 * This implementation is used to read a two-dimensional linear algebra matrix
 * out of a .csv file and into a two-dimensional data structure. The implementation
 * of the data structure is hidden behind an Iterator object.
 * 
 */
public class CSVParser extends ParserInterface
{
	/**
	 * Call to default constructor.
	 */
	public CSVParser()
	{
		super();

                // Call to the parent class data structure.
                // We use a list because we don't know how big the matrix is.
		super.list = new TwoDimensionalArrayList<String>();
	}

	/**
	 * Reads each line of the csv file into a StringTokenizer and returns
         * the data as a two-dimensional Iterator object.
	 * 
	 * @param data
	 *            BufferedReader containing the file data.
	 * @return Iterator[], the file data.
	 */
	@Override
	public Iterator[] readLinesCollection(BufferedReader data)
	{
		// Try to read the data.
		try
		{
                        // Temporary String used to hold the incoming data.
			String csvString;
			
			// Counter for the ArrayList
			int row = 0;

			// Read each line of the csv file while there is data to read.
			while ((csvString = data.readLine()) != null)
			{
				// Parse the data using a csv file format.
				StringTokenizer tokenizer = new StringTokenizer(csvString, ",");

                                // While the tokenizer has data to read.
				while (tokenizer.hasMoreTokens())
				{
                                        // Get the next token.
					String value = tokenizer.nextToken();
                                        // Add the token to the data structure.
					list.Add(value, row);
				}
                                // Increment the row.
                                row++;
			}		
		}
		// Fail.
		catch (IOException e)
		{
                        // Something went wrong reading the data.
			System.out.println("Failed to read line!");
                        // Print the stack trace.
			e.printStackTrace();
		}

		return createIteratorCollection();
	}
}
