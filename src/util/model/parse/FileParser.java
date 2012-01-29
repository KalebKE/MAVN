/*
 FileParser -- a class within the Open Queueing Model (OQM).
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

import util.bufferedReader.BufferedReaderModel;
import util.bufferedReader.BufferedReaderModelInterface;
import java.io.BufferedReader;
import java.util.Iterator;

/**
 * A flexible class for parsing through almost any type of data file.
 * 
 */
public class FileParser
{
	// Buffered read to read the data out one line at a time
	private BufferedReader data;

	// Convenience class use to open files.
	private BufferedReaderModelInterface fileOpener;

	private Iterator list;

	private Iterator[] lists;

	// A abstract file reader that defines methods to parses a file.
	private ParserInterface parseModel;

	// A String to store the current file location.
	private String fileLocation;


        /*
         * Constructor takes an abstract ParseReadable object and
         * associates it with a concrete implemtation of a file parser.
         * This allows any implementation of a file parser to be used with
         * this class as long as it uses the ParseReadable interface.
         */
	public FileParser(AbstractParserFactory parserFactory)
	{
		this.parseModel = parserFactory.createReader();
	}

	/*
	 * Gets the csv file that will be parsed. All files will be accessed in the
	 * same way, so this method is implemented here. It can be accessed by the
	 * child classes so code can be reused.
	 * 
	 * @param fileName the name of the csv file to be parsed.
	 */
	private BufferedReader getFile(String fileLocation)
	{
		// Buffered reader for the input stream
		fileOpener = new BufferedReaderModel(fileLocation);
		return data = fileOpener.openReader();
	}

	/**
	 * Returns a String containing the current file location.
	 * 
	 * @return String containing the file location.
	 */
	public String getFileLocation()
	{
		return fileLocation;
	}

	/**
	 * Returns a Iterator[] containing the data parsed from the file.
	 * 
	 * @param fileName
	 *            The location of the file to be parsed.
	 * @return A Iterator[] containing the data parsed from the file.
	 */
	public Iterator[] parseFile(String fileName)
	{
		// get the file
		lists = parseModel.readLinesCollection(getFile(fileName));

		fileOpener.closeReader();

		return lists;
	}
}
