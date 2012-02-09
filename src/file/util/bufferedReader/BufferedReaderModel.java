/*
 BufferedReaderModel -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network).
 Copyright (C) 2012, Kaleb Kircher

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

package file.util.bufferedReader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A convenience class that creates a BufferedReader from an external file. This class
 * can then be used to read the file by other classes. This is concrete implementation of the
 * BufferedReaderModelInterface.
 * 
 */
public class BufferedReaderModel implements BufferedReaderModelInterface
{
    // File to be opened and read by a BufferedReader.
    private File file;
    // BufferedInputStream to make things fast.
    private BufferedInputStream bis;
    // DataInputStream to read the data.
    private DataInputStream dis;
    // BufferedReader to read each line, also fast.
    private BufferedReader data;
    // FileInputStream to open the file.
    private FileInputStream fis;
    // String storing the location of the file.
    private String fileLocation;
    // Boolean indicating if the file location has been defined.
    private boolean fileLocationDefined = false;

    /**
     * Constructor defines the location of the file that will be opened and read. Indicates
     * that the file location has been defined.
     *
     * @param fileLocation
     *            String containing the location of the file.
     */
    public BufferedReaderModel(String fileLocation)
    {
        super();
        this.fileLocation = fileLocation;
        fileLocationDefined = true;
    }

    /*
     * Closes the BufferedInputStream and DataInputStream. This implementation
     * is not part of the interface and should remain private.
     */
    private void destroyReader()
    {
        // try to close the buffered data stream
        try
        {
            // close the BufferedInputStream
            bis.close();
            // close the DataInputStream
            dis.close();
            // close the BufferedInputReader
            data.close();
        } // fail
        catch (IOException e)
        {
            System.out.println("Failed to close file streams for the company names!");
            e.printStackTrace();
        }

    }

    /*
     * Initializes the BufferedReader. This implementation is not part of the
     * interface is should remain private.
     */
    private BufferedReader initReader()
    {
        file = new File(fileLocation);

        // try to open a file stream
        try
        {
            fis = new FileInputStream(file);
        } // fail to find file
        catch (FileNotFoundException e)
        {
            System.out.println("No such file found!");
            e.printStackTrace();
        }

        // BufferedInputStream for fast reading.
        bis = new BufferedInputStream(fis);

        // Data stream for the file
        dis = new DataInputStream(bis);

        // Buffered reader for the input stream
        return data = new BufferedReader(new InputStreamReader(bis));
    }

    /**
     * Overridden Readable method to close the BufferedInputStream and
     * DataInputStream. Part of the interface and should be accessible to other
     * classes.
     */
    @Override
    public void closeReader()
    {
        // Call to implementation.
        destroyReader();
    }

    /**
     * Overridden Readable implementation. Returns the location of the current
     * file.
     */
    @Override
    public String getFileLocation()
    {
        return this.fileLocation;
    }

    /**
     * Overridden Readable implementation. Returns the boolean indicating
     * if the file location has been defined.
     */
    @Override
    public boolean isFileLocationDefined()
    {
        return fileLocationDefined;
    }

    /**
     * Overridden Readable method implementation to open the File,
     * BufferedInputStream and DataInputStream. Part of the interface and should
     * be accessible to other classes.
     *
     * @return BufferedReader containing the current file.
     */
    @Override
    public BufferedReader openReader()
    {
        // Check to make sure a file location has been defined.
        if (fileLocationDefined)
        {
            // Call to implementation.
            return initReader();
        }
        // Fail to define file location.
        else
        {
            System.out.println("No file location has been defined!");
            return null;
        }
    }
}
