/*
 MultiColumnParse -- a class within the Open Queueing Model (OQM).
 Copyright (C) 2011  Kircher Engineering, LLC (http://www.kircherEngineering.com)

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

package util.model.parse;

import java.io.BufferedReader;
import java.util.Iterator;
import ann.util.ArrayList2d;

/**
 * This class exists to define the type of data structure the parser should use.
 * In in the case of a multi-column parser, one would almost certainly want to use
 * a two-dimensional data structure. This class defines such a data structure, but
 * hides the implementation through the use of an Iterator object.
 * If, for instance, one would want to parse a single column of data, another
 * class called SingleColumnParse could be written using a one-dimensional data
 * structure while still hiding the implementation with an Iterator object. Other
 * methods to support a one-dimensional data structures are here, but not implemented.
 * This class is an abstract implementation of a file parser, ParseReadable so
 * it can be used with the programs FileParser class.
 * 
 */
public abstract class ParserInterface
{
    // Data from the file gets stored in here.
    // ArrayList is used because we don't know how long the data file is.
    // Implementation is hidden through use of an interator, but we want child classes to have access
    protected ArrayList2d<String> list;


    /**
     * Concrete implementation of the data parser goes here. It *should* use the
     * createIterator() method to return an Iterator. This method should be used
     * if one were inclined to use a two-dimensional data structure.
     *
     * @param data
     *            BufferedReader containing the file data.
     * @return Iterator, the parsed data.
     */
    public abstract Iterator[] readLinesCollection(BufferedReader data);


    /**
     * Concrete implementation of the iterator goes here. Prevents your
     * underlying implementation of the data structures from being exposed to
     * other classes. This method should be used
     * if one were inclined to use a two-dimensional data structure.
     *
     * @return
     */
    public Iterator[] createIteratorCollection()
    {
        return list.iterator();
    }
}
