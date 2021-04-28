import java.util.*;
import java.io.*;

/**
 * Class which reads data from and writes data to files.
 *
 * @author Vivian Zhang
 * @version ver1.15
 */

public class FileIO
{
    private String fileName;

    /**
     * Default constructor which creates the object of the class FileIO.
     *
     */
    public FileIO()
    {

    }

    /**
     * Non-default constructor which creates the object of the class FileIO.
     *
     * @param filename             Filename used to read data from or write
     *                             data to
     */
    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Display method to display the object of FileIO.
     *
     * @return                      The attributes of FileIO object, 
     *                              as a String.
     */
    public String display()
    {
        return "File name: " + fileName;
    }
    /**
     * Accessor method to get the filename.
     *
     * @return                      The file name, as a String.
     */
    public String getFileName()
    {
        return fileName;
    }

    /** 
    * Method to read from a file and return the contents of the file 
    * as a String.
    *
    * @return               Contents of the file, as a String                                             
    */
    public String readFile()
    {
        String lineContents = "";
        if(fileName.trim().length() > 0)
        {
            try
            {
                FileReader reader = new FileReader(fileName);
                try
                {
                    Scanner fileInput = new Scanner(reader);
                    while (fileInput.hasNextLine())
                    {
                        lineContents += fileInput.nextLine() + "\n";
                    }
                }
                finally
                {
                    try
                    {
                        reader.close();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error in reading from file!"
                                           + " Exiting...");
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Error in reading from file! Exiting...");
            }
        }
        return lineContents;
    }

    /** 
    * Method to create a new file/overwrite existing file of same name
    * and write content to that file.
    *
    * @param        content         Contents of the file, as a String                                             
    */
    public void writeFile(String content)
    {
        try
        {
            FileWriter writer = new FileWriter(fileName);
            try
            {
                writer.append(content);
            }
            finally
            {
                try
                {   
                    writer.close();
                }
                catch (Exception e)
                {
                    System.out.println("Error in writing to file. Exiting!");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in writing file! Exiting!");
        }
    }
    
    /**
     * Mutator method to set the filename.
     *
     * @param           fileName   The file name, as a String.
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
}
