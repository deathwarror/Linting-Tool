package TestMain;

/**
 * @Author:     Kenneth Hassey
 * @Date:       1/7/2013
 * @Version:    1.000
 * Function:
 *      This File is for reading in files given by the user.
 *      Basic Steps are prompt the user for a file and convert it to an
 *      array list of Strings of code to be parsed.
 * 
 * Status: Tested Working;
 */
import javax.swing.JFileChooser;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 *
 */
public class FileRead {
    
    //Variables declared here for all functions to use.
    // JFileChooser is used for a gui file selection so that the user doesn't
    // need to place files in a specific location.
    private JFileChooser fileChooser;
    private File file;
    private ArrayList<String> code;
    private String FileName;
    private String FileType;
    
    
    //this constructor is required to set up and allow the files to be read.
    FileRead()
    {
      //makes the prompt window for the users file selection
      fileChooser = new JFileChooser();
      FileName = "";
      code = new ArrayList();
      //responsible for initial reading and variable writing.
    }
    
    private void getFile()
    {
        int returnVal=fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
        file = fileChooser.getSelectedFile();
        System.out.println("Opening File: "+file.getName());
        }
        FileName = file.getName();
        FileType = file.getName();
        
    }
    
    
    
    //This function is Responsible for the opening of the file and to conver it
    //into strings for return.  This function does not return the 
    //String ArrayList.
    private void readFile()
    {
        code = new ArrayList();
        Scanner inputFile;
        
        //
        try 
        {
           inputFile = new Scanner(file);
           //this block is responsible for converting the file to a arraylist 
           //of strings.
           try{
                while (inputFile.hasNext()) 
                {
                  //adds line of code to the Arraylist.
                  code.add(inputFile.nextLine());
                }
           }
           //catches exception dealing with array out of bounds for file
           //should never trigger.
           catch(Exception e )
           {
               System.out.println("File Has Filed to transfer to ArrayList\n"
                       + "Location: FileReader.readFile()");
           }
        }
        
        //catch an exception most likely dealing with the input file being null
        catch(Exception e)
        {
            System.out.println("File Has Failed to Read\n"
                    + "Location: FileReader.readFile()");
        }
        
    }
    
    //used to get a new file from the user and to convert it to a Arraylist of 
    //Strings.  Call this instead of a New Constructor.
     public void newFile()
    {
        getFile();
        readFile();
    }
     
     
    //Used to return the file name
    public String getFileName()
    {
        return FileName;
    }
    
    //Returns all the code inside the file.
    public ArrayList<String> getCode()
    {
        return code;
    }
    //needed to print out all information about the fileReader
    //this replaced the toString Object.

    public String toString()    
    {
        //@Override
        //prints out the file name
        System.out.println(FileName+":");
        
        //prints out all the code in the files array
        for(int i = 0;i<code.size();i++)
        {
            System.out.println("\t"+code.get(i));
        }
        return ("Successful");
    }
}
