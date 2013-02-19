package TestMain;

/** @Author: Kenneth Hassey
 *  @Date: 1/7/2013
 *  @Version: 1.000
 *  Function:
 *      Contains all the code from a parsed file and is used to store in coming
 *      blocks of code for multiple depth.  This function is responsible for
 *      all original information and parsed information of the file.
 *      Pattern for storage is Parsed contains the category of type
 *      SpecificType is the Specific Type of the code Line.
 *      For Example: an if block would have Parsed = block and SpecificType
 *      = if
 * 
 * 
 *      Note: No depth added because there may be an issue comparing variables
 *            between two targets in different modules.
 * 
 *  Status: Untested
 * 
 */

import java.util.ArrayList;
public class Code {
    private String original;
    private String parsed;
    
    private ArrayList<Error> error;
    private String SpecificType;
    private ArrayList<Variable> variables;
    private int LineNumber;
    
    //default constructor
    Code()
    {
        original = parsed = SpecificType = "";
        LineNumber = 0;
        error = new ArrayList();
        variables = new ArrayList();
    }
    //constructor for most common adding time
    Code(String Orig,String Parse, String type)
    {
        this();
        original = Orig;
        parsed = Parse;
        SpecificType = type;
    }
    
    //if Parsing is done and error is Hard Coded, for the error to be added.
    Code(String Orig,String Parse, String type, Error e)
    {
        this(Orig,Parse,type);
        //boolean a gets rid of parse warning in compiler
        boolean a;
        a = this.addError(e);
    }
    
    
    public int getLineNumber()
    {
        return LineNumber;
    }
    public void setLineNumber(int ln)
    {
        LineNumber = ln;
    }
    //allows checking for duplicate errors and then adds it
    public boolean addError(Error e)
    {
        //goes through the list
        for(int i = 0; i<error.size(); i++)
        {
            //checks to see if they are the same
            if(error.get(i).compareTo(e))
            {
                return false;
            }
        }
        //adds to the list
        error.add(e);
        return true;
    }
    //checks for duplicate variables and then adds to the list
    public boolean addVariable(Variable V)
    {
        //goes through the list
        for(int i = 0; i<variables.size(); i++)
        {
            //checks to see if they are the same
            if(variables.get(i).compareToAll(V));
            {
                return false;
            }
        }
        //adds to the list
        variables.add(V);
        return true;
    }
    
    //Returns the original line of code
    public String getOriginal()
    {
        return original;
    }
    
    //Returns the gerneral type of the code
    public String getParsed()
    {
        return parsed;
    }
    
    //returns the specificType of the code
    public String getSpecificType()
    {
        return SpecificType;
    }
    
    //returns all errors that is on this line of code
    public ArrayList<Error> getErrors()
    {
        return error;
    }
    
    //Returns the variables that is contained in the code
    public ArrayList<Variable> getVariables()
    {
        return variables;
    }
    
    //prints out all information in the Code data structure
    /**@Overrides objects toString()
     * @return Successful in a String
     */
    public String toString()
    {
        //prints out variables for this code item
        System.out.println("Variables: ");
        for(int i = 0; i < variables.size();i++)
        {
            variables.get(i).toString();
        }
        //prints out the original and parsed code next to each other in table 
        //format
        if(original!=null && parsed!=null)
        {
            //if original is over 30 characters in length
            if(original.length()>30) 
            {
                System.out.println(original.substring(0,29)+" | "+parsed);
            }
            //if original is not over 30 characters in length
            else
            {
                //print original
                System.out.print(original);
                //prints spaces to get to 30 character mark
                for(int i = original.length();i<30;i++)
                {
                    System.out.print(" ");
                }
                //prints dividing line and parsed code
                System.out.println(" | "+parsed);
            }
        }
        
        //prints out the specific type for the code item
        if(SpecificType != null)
        {
            System.out.println("Type: "+SpecificType);
        }
        
        //prints out the errors for this code item.
        System.out.println("Errors: ");
        for(int i = 0; i< error.size();i++)
        {
            error.get(i).toString();
        }

        return "Successful";
    }
    
    //allows setting of the original code
    public void setOriginal(String s)
    {
        original = s;
    }
    //allows setting of the parsed string
    public void setParsed(String s)
    {
        parsed = s;
    }
    
    //allows setting of the specific type string
    public void setSpecificType(String s)
    {
        SpecificType = s;
    }
    
    //allows setting of the error array list
    public void setErrors(ArrayList<Error> e)
    {
        error = e;
    }
    
    //allows setting of variable array list
    public void setVariables(ArrayList<Variable>  V)
    {
        variables = V;
    }
}
