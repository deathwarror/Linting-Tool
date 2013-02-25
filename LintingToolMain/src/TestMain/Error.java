package TestMain;

/** @Author: Kenneth Hassey
 * @Date: 1/7/2013
 * @Version: 1.000
 * Function
 *      Contains all information about an error, Defintion, Indetification 
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

    public String identifyMultiplyDrivenSignals(Parser parser){
        String errorOutput="Error: Multiply Driven Signals were detected in the following lines of code:\n";
        ArrayList<String> tempString;
        ArrayList<Variable> vars = parser.getVariableList();
        ArrayList<Block> blocks = parser.getBlockList();
        ArrayList<AssignmentStatement> statements;
        Variable currentVar = null;
        Block currentBlock = null;
        AssignmentStatement currentStatement = null;

        int i=0; int j=0; int k=0;
        for(i=0; i<vars.size(); i++, tempString = new ArrayList()){
            currentVar = vars.get(i);
            if(currentVar.getClass() == Reg.class){
                for(j=0, tempString=new ArrayList(), currentBlock=blocks.get(0);
                j<blocks.size(); j++){
                    currentBlock=blocks.get(j);
                    if(currentBlock.getClass() == Always.class){
                        //Should retrive all assignment statements in the current
                        //always block
                        statements = currentBlock.getAllAssignmentStatements();
                        for(k=0; k<statements.size(); k++){
                            currentStatement=statements.get(k);
                            //This block identifies if the (register) variable
                            //is present in this particular always block, if so
                            //it breaks to check for the variable's presence
                            //in the next always block
                            if(currentStatement.getLHSvars().indexOf( currentVar) != -1 ){
                                tempString.add(currentVar.toString()
                                        +" in always number "+j+": "+
                                        currentStatement.assignmentText + ";\n");
                                break;
                            }
                        }
                        if(tempString.size() > 1){
                            errorOutput += tempString+ "\n";
                        }
                    }
                }
            }


            else if( currentVar.getClass() == Wire.class){
                for(j=0, tempString=new ArrayList(); j<blocks.size(); j++, tempString=new ArrayList() ){
                    currentBlock=blocks.get(j);
                    if(currentBlock.getClass() == Module.class){
                        //Should retrive all assignment statements in the module
                        //but not in module subBlocks
                        statements = currentBlock.getBlockAssignmentStatements();
                        for(k=0; k<statements.size(); k++){
                            currentStatement=statements.get(k);
                            if(currentStatement.getLHSvars().indexOf( currentVar) != -1 ){
                                tempString.add(currentStatement.assignmentText + ";\n");
                            }
                        }
                    }
                    if(tempString.size() > 1){
                        errorOutput += tempString+ "\n";
                    }
                }
            }
        }


        if(!errorOutput.equals("Error: Multiply Driven Signals were detected in the following lines of code:\n")){
            System.out.println(errorOutput);
            return errorOutput;
        }else{
            System.out.println("No Multiply Driven Signals Detected!\n");
            return "No Multiply Driven Signals Detected!\n";
        }
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
      System.out.println("Error Number "+ ErrorNumber+":\n"
              +"Error Message: "+ErrorMsg+"\nError Definition:");
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
