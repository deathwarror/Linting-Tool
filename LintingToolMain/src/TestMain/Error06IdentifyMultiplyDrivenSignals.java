package TestMain;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deathwarror
 */
public class Error06IdentifyMultiplyDrivenSignals {
     public static ArrayList<Error> getErrors(Parser parser){
        ArrayList<Error> ErrorList = new ArrayList();
        String errorOutput="Error: Multiply Driven Signals were detected in the following lines of code:\n";
        ArrayList<String> tempString;
        ArrayList<Variable> vars = parser.getVariableList();
        ArrayList<Block> blocks = parser.getBlockList();
        ArrayList<AssignmentStatement> statements;
        ArrayList<Integer> lineNumbers = new ArrayList();
        ArrayList<Integer> tempLineNumbers = new ArrayList();
        Variable currentVar = null;
        Block currentBlock = null;
        AssignmentStatement currentStatement = null;

        int i=0; int j=0; int k=0;
        // The first loop goes through every register and wire in the module
        for(i=0; i<vars.size(); i++, tempString = new ArrayList(), tempLineNumbers=new ArrayList()){
            currentVar = vars.get(i);
            if(currentVar.getClass() == Reg.class){ //if currentVar is of type reg
                //The second loop goes through every block lookin for each always block
                for(j=0, tempString=new ArrayList(), currentBlock=blocks.get(0);
                j<blocks.size(); j++){
                    currentBlock=blocks.get(j);
                    if(currentBlock.getClass() == Always.class){ //if currentBlock is an always block
                        //Retrieves all assignment statements in the current
                        //always block (and all of its sub-blocks)
                        statements = currentBlock.getAllAssignmentStatements();
                        for(k=0; k<statements.size(); k++){
                            currentStatement=statements.get(k);
                            //This block identifies if the (register) variable
                            //is present in this particular always block, if so
                            //it makes note of it, and then breaks to check for the variable's presence
                            //in the next always block
                            if(currentStatement.getLHSvars().indexOf( currentVar) != -1 ){
                                tempString.add(
                                        " \"always\" block at line "+ currentBlock.getBlockLineNumber() +": "+
                                        currentStatement.assignmentText + "; (line: "+currentStatement.getAssignmentStatementLineNumber() +")\n"
                                        );
                                tempLineNumbers.add(currentStatement.getAssignmentStatementLineNumber());
                                break;
                            }
                        }
                        if(tempString.size() > 1){
                            errorOutput += tempString+ "\n";
                            tempString = new ArrayList();
                            lineNumbers.addAll(tempLineNumbers);
                        }
                    }
                }
            }


            else if( currentVar.getClass() == Wire.class){ //if currentVar is of type wire
                for(j=0, tempString=new ArrayList(); j<blocks.size(); j++, tempString=new ArrayList(), tempLineNumbers=new ArrayList() ){
                    currentBlock=blocks.get(j);
                    if(currentBlock.getClass() == Module.class){
                        //Should retrive all assignment statements in the module
                        //but not in module subBlocks
                        statements = currentBlock.getBlockAssignmentStatements();
                        for(k=0; k<statements.size(); k++){
                            currentStatement=statements.get(k);
                            if(currentStatement.getLHSvars().indexOf( currentVar) != -1 ){
                                tempString.add(
                                        "Continuous Assignment: assign"
                                        +currentStatement.assignmentText 
                                        + "; (line: "+ currentStatement.getAssignmentStatementLineNumber() +")\n"
                                );
                                tempLineNumbers.add(currentStatement.getAssignmentStatementLineNumber());
                            }
                        }
                    }
                    if(tempString.size() > 1){
                        errorOutput += tempString+ "\n";
                        lineNumbers.addAll(tempLineNumbers);
                    }
                }
            }
        }


        if(!errorOutput.equals("Error: Multiply Driven Signals were detected in the following lines of code:\n")){
            Error e = new Error();
            e.setErrorMsg(errorOutput);
            e.setErrorNum("06");
            e.setLineNumbers(lineNumbers);
            ErrorList.add(e);
            System.out.println(errorOutput);
            return ErrorList;
        }else{
            System.out.println("No Multiply Driven Signals Detected!\n");
            return ErrorList;
        }
    }
    
}
