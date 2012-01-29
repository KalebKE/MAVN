/*
 ParserFactory -- a class within the Open Queueing Model (OQM).
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

/**
 * This class exists to allow parser implementations to be organized by file type. For instance,
 * sub-classes should be created for ".txt", ".csv", ".pdf", etc... respectivly.
 * Those sub-classes should then organize each implementation of a parser
 * for the file type. This allows for a great deal of flexibility in how
 * you can parse each file type and makes it easy to access each implementation.
 * 
 */
public interface AbstractParserFactory
{
    /**
     * An interface to create create fileReaders. Implementation of fileReader goes here. Instantiate a concrete
     * implementation of ParseReadable here.
     *
     *
     * @param name The name of the file type you want to parse.
     * @return ParseReadable A file reader.
     */
    public ParserInterface createReader();
}
