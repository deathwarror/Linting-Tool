
package TestMain;

import java.util.ArrayList;

/**@author Kenneth Hassey
 *@Version 1.000
 * @date: 3/8/2013
 * Function:
 *      Responsible for searching for errors in the files
 */
public class ErrorSearcher {
    ArrayList<Error> ErrorList;
    ErrorSearcher()
    {
      ErrorList = new ArrayList();  
    }
    public ArrayList<Error> Start(Parser parser)
    {
        ClearErrorList();
        identifyMultiplyDrivenSignals(parser);
        return ErrorList;
    }
    private void ClearErrorList()
    {
        ErrorList = new ArrayList();
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
                        //Should retrieve all assignment statements in the current
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
            Error e = new Error();
            e.setErrorMsg(errorOutput);
            e.setErrorNum("01");
            ErrorList.add(e);
            System.out.println(errorOutput);
            return errorOutput;
        }else{
            System.out.println("No Multiply Driven Signals Detected!\n");
            return "No Multiply Driven Signals Detected!\n";
        }
    }

}
