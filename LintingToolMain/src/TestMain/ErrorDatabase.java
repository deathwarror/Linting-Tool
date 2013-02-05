package TestMain;

/**
 * @author: Kenneth Hassey
 * @date: 1/15/2013
 * @Version: 1.000;
 *
 * Function:
 *      ErrorDatabase Stores a list of errors based on the type of file they are
 *      meant for.  The file Type that the database pertains to is stored 
 *      and the list of errors pertaining to said file type is stored
 */
import java.util.ArrayList;

public class ErrorDatabase {
    private ArrayList<Error> ErrorData;
    private String FileType;
    
    //construnctors
    ErrorDatabase()
    {
        ErrorData = new ArrayList();
        FileType = "";
    }
    ErrorDatabase(String Type)
    {
        this();
        FileType = Type;
    }
    ErrorDatabase(String Type, ArrayList<Error> Ed)
    {
        FileType = Type;
        ErrorData = Ed;
    }
    
    ErrorDatabase(ArrayList<Error> Ed, String Type)
    {
        this(Type,Ed);
    }
    
    //returns what this database pertains to
    public String getFileType()
    {
        return FileType;
    }
    
    
    //allows setting of the file type
    public void setFileType(String Type)
    {
        FileType = Type;
    }
    
    //Prints out the Error Database Object
    public String toString()
    {
        System.out.println("File Type: "+FileType);
        for(int i = 0; i<ErrorData.size();i++)
        {
            ErrorData.get(i).toString();
        }
        return "Successful";
    }
    
    //returns the error list for the search function.
    public ArrayList<Error> getErrorList()
    {
        return ErrorData;
    }
    
    //allows Setting of the error Lists
    public void setErrorList(ArrayList<Error> ed)
    {
        ErrorData = ed;
    }
    
    //adds an error to the error list
    public boolean addError(Error e)
    {
        for(int i = 0; i<ErrorData.size();i++)
        {
            //if the error is already in the list
            if(ErrorData.get(i).compareTo(e))
            {
                return false;
            }
        }
        ErrorData.add(e);
        return true;
    }
    
}
