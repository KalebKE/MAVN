/*
 StringToDouble -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
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

package mavn.util.parse;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to convert a String array to an int array.
 * 
 */
public class StringToDouble
{

	/**
	 * Converts a String array to an int array.
	 * 
	 * @param stringArray
	 *            the array of Strings
	 * @return the new int array
	 */
	public StringToDouble()
	{
		super();
	}

	public Iterator parseString(Iterator stringIterator)
	{
		ArrayList<String> stringValues = new ArrayList<String>();
		ArrayList<Double> doubleValues = new ArrayList<Double>();
		
		while(stringIterator.hasNext())
		{
			stringValues.add((String) stringIterator.next());
		}
		
		for (int j = 0; j < stringValues.size(); j++)
		{

			doubleValues.add(Double.parseDouble(stringValues.get(j)));
		}

		return doubleValues.iterator();
	}
}
