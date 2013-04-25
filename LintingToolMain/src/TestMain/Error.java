package TestMain;

/** @Author: Kenneth Hassey
 * @Date: 1/7/2013
 * @Version: 1.000
 * Function
 *      Contains all information about an error, Definition, Identification
 *      Number, and Message Given to the user
 * Status: UPDATE
 * Updated by Danny on 2/24/2013
 */

import java.util.ArrayList;

public class Error {
    //Stores the Errors Message
    private String ErrorMsg;
    //Stores the error Number;
    private String ErrorNumber;
    
    private ArrayList<Integer> LineNumbers;

    Error()
    {
        ErrorMsg = "";
        ErrorNumber = "";
        LineNumbers = new ArrayList();
    }

    Error(String ErrorNum, String ErrorMsg_in)
    {
        ErrorNumber = ErrorNum;
        ErrorMsg = ErrorMsg_in;
    }

    public String setLineNumbers(ArrayList<Integer> NewLns)
    {
        LineNumbers = NewLns;
        return "Successful";
    }
    public String addLineNumber(int i)
    {
        LineNumbers.add(i);
        return "Successful";
    }
    public ArrayList<Integer> getLineNumbers()
    {
        return LineNumbers;
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


    //@Override the default to string statement
    //used for testing, Displays all information in the object
    public String toString()
    {
      System.out.println("Error Number "+ ErrorNumber+":\n"
              +"Error Message: "+ErrorMsg+"\nError Definition:");
      return "Successful";
    }

    //allows the error number to be set afterwards
    public void setErrorNum(String e)
    {
        ErrorNumber = e;
    }
    //allows the error message to be set afterwards
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
                //return true if all tests have been passed.
                return true;
        }
        //if it failed the first test return false
        return false;
    }
}
