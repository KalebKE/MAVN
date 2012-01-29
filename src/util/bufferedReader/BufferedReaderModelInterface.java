/*
 Openable -- a class within the Open Queueing Model (OQM).
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
package util.bufferedReader;

import java.io.BufferedReader;

/**
 * This interface is used to open files with data that can read using a
 * BufferedReader. It allows for custom implementations of how a file is opened.
 * 
 */
public interface BufferedReaderModelInterface
{
	/**
	 * The interface to access the BufferedReader for the file that has been
	 * passed into the constructor or set using the setFileLocation(String
	 * fileLocation) method.
	 * 
	 * @return
	 */
	public BufferedReader openReader();

	/**
	 * The interface to close the BufferedReader and associated objects.
	 */
	public void closeReader();

	/**
	 * Getter for the current file location.
	 * 
	 * @return String containing the file location.
	 */
	public String getFileLocation();

	/**
	 * Getter for boolean indicating if the file location has been defined.
	 * 
	 * @return boolean indicating if the file location has been defined.
	 */
	public boolean isFileLocationDefined();
	
}
