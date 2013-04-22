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
public class Error05IdentifyAssignmentVsSensType {
     public static ArrayList<Error> getErrors(Parser parser)
    {
        ArrayList<Error> ErrorList = new ArrayList();
        String errorOutput = "NE";
        
        ArrayList<Block> blocks = parser.getBlockList();
        ArrayList<AssignmentStatement> statements;
        Block currentBlock;
        String currentStatement;
        String SensType;
        //Go through the list of blocks
        for(int i = 0;i<blocks.size();i++)
        {
            //get the current block
            currentBlock = blocks.get(i);
            
            //see if the current block is an always block
            if(currentBlock.getClass() == Always.class)
            {
                int error = 0;
                SensType = ((Always)currentBlock).BlockType;
                statements = currentBlock.getAllAssignmentStatements();
                
                //check for blocking in a flip flop always
                for(int j = 0; j <statements.size(); j++)
                {
                    currentStatement = statements.get(j).assignmentText;
                    if(!(currentStatement.contains("<")))
                    {
                        if(SensType.equals("edgeSensitive"))
                        {
                           error = 1;
                        }
                    }
                    
                    
                    //check for nonblocking in a combination logic always
                    else if(currentStatement.contains("<"))
                    {
                        int index = currentStatement.indexOf("<");
                        if(currentStatement.charAt(index+2)== '=')
                        {
                            //if the block is combinational
                            if((SensType.equals("levelSensitive")))
                            {
                                error = 2;
                            }
                        }
                    }
                    
                    //if a blocking statement in a flip flop was detected
                    if(error == 1);
                    {
                        errorOutput = "Error: Detected a flip flop with a Blocking Statement:\n";
                        errorOutput += "\tin always on line "+currentBlock.LineNumber+":";
                        errorOutput += "\n\tLine "+statements.get(j).LineNumber+":\t"+statements.get(j).assignmentText+";\n";
                    }
                    //if a nonblocking in a combination block was detected
                    if(error == 2)
                    {
                        errorOutput = "Error: Detected a Combinational Always Block with a NonBlocking Statement:\n";
                        errorOutput += "\tin always on line "+currentBlock.LineNumber+":";
                        errorOutput += "\n\tLine "+statements.get(j).LineNumber+":\t"+statements.get(j).assignmentText+";\n";
                    }
                    
                    //if an error was detected
                    if(error!= 0)
                    {
                           //put the error message into the error list
                           Error e = new Error();
                           e.setErrorMsg(errorOutput);
                           e.setErrorNum("05");
                           ErrorList.add(e);
                    }
                }
            }
        }
        
        
        if(errorOutput.equals("NE"))
        {
            System.out.println("No improper use of blocking and non blocking detected!\n");
            return ErrorList;
        }
        else
        {
            return ErrorList;
        }
    }   
}
