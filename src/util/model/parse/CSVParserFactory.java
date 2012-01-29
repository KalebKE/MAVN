/*
 CSVParserFactory -- a class within the Open Queueing Model (OQM).
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
 * Factory class creates a CSV parser. Pass the method the name of the
 * desired parser. If one were to require a large number of parsers,
 * this factory class would be used to access the file-type specific
 * parser implementations.
 *
 */
public class CSVParserFactory implements AbstractParserFactory
{
    /**
     * Factory class creates a CSV parser. Pass the method the name of the
     * desired parser. If one were to require a large number of parsers,
     * this factory class would be used to access the file-type specific
     * parser implementations.
     *
     * @param String
     *            name The file file type.
     * @return ParseReadble A file parser.
     */
    @Override
    public ParserInterface createReader()
    {
            return new CSVParser();
    }
}
