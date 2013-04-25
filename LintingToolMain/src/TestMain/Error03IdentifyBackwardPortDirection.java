/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

import java.util.ArrayList;

/**
 *
 * @author Deathwarror
 */
public class Error03IdentifyBackwardPortDirection {
    //This function Detects if there is an improper used port either on the left
    //or on the right hand side of an assignment statement
    public static ArrayList<Error> getErrors(Parser parser)
    {
        ArrayList<Error> ErrorList = new ArrayList();
        String errorOutput = "No Error";
        int lineFlagged;
        
        ArrayList<Block> blocks = parser.getBlockList();
        ArrayList<AssignmentStatement> statements;
        ArrayList<Variable> LHSVList;
        ArrayList<Variable> RHSVList;
        Variable inputVar;
        Variable outputVar;
        Block currentBlock;
       
        //Go through the list of blocks
        for(int i = 0;i<blocks.size();i++)
        {
            //get the current block
            currentBlock = blocks.get(i);
            
            //see if the current block is an always block
            if(currentBlock.getClass() == Always.class)
            {
                //get all the statements
                statements = currentBlock.getAllAssignmentStatements();
                for(int j = 0; j < statements.size(); j++)
                {
                    lineFlagged = 0;
                    //Checks Left hand side for an input
                    LHSVList = statements.get(j).getLHSvars();
                    for(int k = 0; k< LHSVList.size();k++)
                    {
                        inputVar = LHSVList.get(k);
                        if(inputVar.variableAttribute.equals("input") &&lineFlagged ==0)
                        {
                           errorOutput = "Error: Detected input being assigned:\n";
                           errorOutput += "\tin always on line "+currentBlock.LineNumber+": \n\t Variable: "+LHSVList.get(k).name;
                           errorOutput += "\n\tLine "+statements.get(j).LineNumber+":\t"+statements.get(j).assignmentText+";\n";
                                    
                           //put the error message into the error list
                           Error e = new Error();
                           e.setErrorMsg(errorOutput);
                           e.addLineNumber(statements.get(j).LineNumber);
                           e.setErrorNum("03");
                           ErrorList.add(e);
                           System.out.println(errorOutput);
                                    
                           //label the input side  as already been flagged so that multiply errors arent listed
                           lineFlagged = 1;
                        }
                    }
                    
                    lineFlagged = 0;
                    //Look For an output being used in a calculation or right side of assignment
                    RHSVList = statements.get(j).getRHSvars();
                    for(int k = 0; k < RHSVList.size();k++)
                    {
                        outputVar = RHSVList.get(k);
                        if(outputVar.variableAttribute.equals("output") &&lineFlagged ==0)
                        {
                           errorOutput = "Error: Detected output being being used in Right Side of equation:\n";
                           errorOutput += "\tin always on line "+currentBlock.LineNumber+": \n\t Variable: "+RHSVList.get(k).name;
                           errorOutput += "\n\tLine "+statements.get(j).LineNumber+":\t"+statements.get(j).assignmentText+";\n";
                                    
                           //put the error message into the error list
                           Error e = new Error();
                           e.setErrorMsg(errorOutput);
                           e.setErrorNum("03");
                           e.addLineNumber(statements.get(j).LineNumber);
                           ErrorList.add(e);
                           System.out.println(errorOutput);
                                    
                           //label the operation side as already been flagged so that multiply errors arent listed
                           lineFlagged = 1;
                        }
                    }
                }
            }
        }
        
        
        if(errorOutput.equals("No Error"))
        {
            System.out.println("No Improper used ports!\n");
            return ErrorList;
        }
        else
        {
            return ErrorList;
        }
    }
}
