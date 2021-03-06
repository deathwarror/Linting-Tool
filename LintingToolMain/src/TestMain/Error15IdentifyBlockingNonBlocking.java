/**
 * @Author:     Kenneth Hassey
 * @Date:       5/8/2013
 * @Version:    1.001
 * Function:
 *      This File is for detection of blocking and non blocking statements used
 *      in the same always block.
 *
 * Status: Tested Working;
 */

package TestMain;

import java.util.ArrayList;

public class Error15IdentifyBlockingNonBlocking {
    public static ArrayList<Error> getErrors(Parser parser)
    {
        ArrayList<Error> ErrorList = new ArrayList();
        
        String errorOutput = "No Error";
        int blockFlagged;
        
        ArrayList<Block> blocks = parser.getBlockList();
        ArrayList<AssignmentStatement> statements;
        Block currentBlock;
        AssignmentStatement currentStatement;
       
        //Go through the list of blocks
        for(int i = 0;i<blocks.size();i++)
        {
            //get the current block
            currentBlock = blocks.get(i);
            blockFlagged = 0;
            //see if the current block is an always block
            if(currentBlock.getClass() == Always.class)
            {
                //get the list of assignment statements in the always block
                statements = currentBlock.getAllAssignmentStatements();
                
                //go through the statements
                for(int j = 0; j<statements.size(); j++)
                {
                    currentStatement = statements.get(j);
                    
                    //see if current statement contains an nonblocking line of code
                    if(currentStatement.assignmentText.contains("< =")&&blockFlagged == 0)
                    {
                        //go though the statements again
                        for(int k = 0;k<statements.size(); k++)
                        {
                            currentStatement = statements.get(k);
                            
                            //Look for an equals sign to see if it is an blocking line
                            if(currentStatement.assignmentText.contains("=")&&blockFlagged == 0)
                            {
                                int index = currentStatement.assignmentText.indexOf("=");
                                
                                //verify that it is a blocking line by making sure there is not a non blocking symbol at the start
                                if (currentStatement.assignmentText.charAt(index-2) != '<'&&blockFlagged == 0)
                                {
                                    
                                    //create the error message for the line
                                    errorOutput = "Detected blocking and non blocking in following lines of code:\n";
                                    errorOutput += "\tin always on line "+currentBlock.LineNumber+": \n";
                                    errorOutput += "\t"+statements.get(j).LineNumber+":\t"+statements.get(j).assignmentText+";\n";
                                    errorOutput += "\t"+statements.get(k).LineNumber+":\t"+statements.get(k).assignmentText+";\n";
                                    
                                    //put the error message into the error list
                                    Error e = new Error();
                                    e.setErrorMsg(errorOutput);
                                    e.setErrorNum("15");
                                    e.addLineNumber(statements.get(j).LineNumber);
                                    e.addLineNumber(statements.get(k).LineNumber);
                                    ErrorList.add(e);
                                    System.out.println(errorOutput);
                                    
                                    //label the block as already been flagged so that multiply errors arent listed
                                    blockFlagged = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        if(errorOutput.equals("No Error"))
        {
            System.out.println("No NonBlocking with Blocking assignments Detected!\n");
            return ErrorList;
        }
        else
        {
            return ErrorList;
        }
    }   
    
}
