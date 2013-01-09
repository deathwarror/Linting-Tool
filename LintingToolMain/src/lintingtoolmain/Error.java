package lintingtoolmain;

/** @Author: Kenneth Hassey
 * @Date: 1/7/2013
 * @Version: 1.000
 * Function
 *      Contains all information about an error, Defintion, Indetification 
 *      Number, and Message Given to the user
 * Status: Untested
 */

import java.util.ArrayList;

public class Error {
    //Stores the Errors Message
    private String ErrorMsg;
    //Stores the error Number;
    private String ErrorNumber;
    //Stores the Definition of the Error.
    private ArrayList<String> ErrorDef;
    
    Error()
    {
        ErrorMsg = "";
        ErrorNumber = "";
        ErrorDef = new ArrayList();
    }
    
    Error(String ErrorNum, String ErrorMsg_in, ArrayList<String> ErrorDefinition)
    {
        ErrorNumber = ErrorNum;
        ErrorMsg = ErrorMsg_in;
        ErrorDef = ErrorDefinition;
    }
    
    
    //Returns the Error Number;
    public String getErrorNum()
    {
        return ErrorNumber;
    }
    
    //Returns the ErrorMsg
    public String getErrorMsg()
    {
        return ErrorMsg;
    }
    
    //Returns the Error Defintion in ArrayList Form
    public ArrayList<String> getErrorDef()
    {
        return ErrorDef;
    }
    
    //@Override the default to string statement
    //used for testing, Displays all information in the object
    public String toString()
    {
      System.out.println(ErrorNumber+":\n"+ErrorMsg+"\n");
      for(int i = 0; i< ErrorDef.size();i++)
      {
          System.out.println("\t"+ErrorDef.get(i));
      }
      return "Successful";
    }
    
    //allows the error number to be set afterwards
    public void setErrorNum(String e)
    {
        ErrorNumber = e;
    }
    //allows the error number to be set afterwards
    public void setErrorDef(ArrayList<String> e)
    {
        ErrorDef= e;
    }
    //allows the error number to be set afterwards
    public void setErrorMsg(String msg)
    {
        ErrorMsg = msg;
    }
    
    
    //Used to keep duplicate errors from being placed in the array.
    public boolean compareTo(Error e)
    {
        //checks to see if the error number and msg are the same
        if((e.getErrorNum().equals(ErrorNumber))&&(e.getErrorMsg().equals(ErrorMsg)))
        {
            
            ArrayList<String> edef = e.getErrorDef();
            
            //checks to see if the definition size is the same
            if(edef.size() == ErrorDef.size())
            {
                int i;
                
                //used to compare each line of the definition is the same
                for(i = 0; i< ErrorDef.size();i++)
                {
                    
                  //if any line is not equal to the other return they are not 
                  //equal
                  if(!(edef.get(i).equals(ErrorDef.get(i))))
                  {
                      return false;
                  }
                  
                }
                //return true if all tests have been passed.
                return true;
            }
        }
        //if it failed the first test return false
        return false;
    }
}
