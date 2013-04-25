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
public class Error11LogicalNotWithVector {
    public static ArrayList<Error> getErrors(Parser parser)
    {
        ArrayList<Error> ErrorList = new ArrayList();
        
        String errorOutput = "No Error";
        
        ArrayList<Block> blocks = parser.getBlockList();
        Block currentBlock;
        String currentVar;
        ArrayList<Variable> Variables = parser.getVariableList();
        ArrayList<String> VectorNames = new ArrayList();
        ArrayList<AssignmentStatement> As;
        String currentStatement; 
        
        for(int i = 0;i<Variables.size();i++)
        {
            //get go through the list and find variables that have a vector parent
            if(!(Variables.get(i).vectorParentName.equals("")))
            {
                int add = 1;
                for(int j = 0; j<VectorNames.size();j++)
                {
                    //if the vector name is in the list flag the add variable to don't add
                    if((VectorNames.get(j).equals(Variables.get(i).vectorParentName)))
                    {
                        add = 0;
                    }
                }
                //if the vector name wasn't found in the list
                if(add == 1)
                {
                    //add it to the list of VectorNames
                    VectorNames.add(Variables.get(i).vectorParentName);
                }
            }
        }
        
        if(blocks.size()>0)
        {
            //get the top block
            currentBlock = blocks.get(0);
            //get all assignment statements in the code
            As= currentBlock.getAllAssignmentStatements();
            for(int j = 0; j < As.size(); j++)
            {
                
                //get the assignment text and add a space for missing end character
                currentStatement = As.get(j).assignmentText+" ";
                for(int k = 0; k<VectorNames.size();k++)
                {
                    
                    //get one of the vector names
                    currentVar = VectorNames.get(k);
                    //if a vector was identified
                    if(currentStatement.contains(" "+currentVar+" "))
                    {
                        //get the position of the vector
                        int index = currentStatement.indexOf(" "+currentVar+" ");
                        if((index-1)>=0)
                        {
                            //get the character before the vector
                            char debugChar = currentStatement.charAt(index -1);
                            //see if it was a logical not
                            if(debugChar =='!')
                            {
                                
                                int StartIndex = currentStatement.indexOf(" "+currentVar+" ");
                                int EndIndex;
                                String tempStr = currentStatement.substring(StartIndex);

                                //if the vector does not have indexing
                                    char debugChar2 = tempStr.charAt(currentVar.length()+2);
                                    String debugString = tempStr.substring(currentVar.length()+2);
                                    if(debugChar2!= '[')
                                    {
                                        Error e = new Error();
                                        e.setErrorNum("11");
                                        errorOutput = "Error: Vector or Array Series is being operated on with the logical not operator:\n";
                                        errorOutput += "\tin always on line : "+As.get(j).LineNumber+"\n\tVariable Name: ";
                                        errorOutput += currentVar;
                                        e.addLineNumber(As.get(j).LineNumber);
                                        System.out.println(errorOutput+"\n");
                                        e.setErrorMsg(errorOutput);
                                        ErrorList.add(e);
                                    }
                                    //if the vector does have indexing
                                    else
                                    {
                                        StartIndex = currentStatement.indexOf("[");
                                        EndIndex = currentStatement.indexOf("]");
                                        tempStr = currentStatement.substring(StartIndex,EndIndex);
                                        //see if the indexing contains an range
                                        if(tempStr.contains(":"))
                                        {
                                           Error e = new Error();
                                            e.setErrorNum("11");
                                            errorOutput = "Error: Vector or Array Series is being operated on with the logical not operator:\n";
                                            errorOutput += "\tin always on line : "+As.get(j).LineNumber+"\n\tVariable Name: ";
                                            errorOutput += currentVar;
                                            e.addLineNumber(As.get(j).LineNumber);
                                            System.out.println(errorOutput+"\n");
                                            e.setErrorMsg(errorOutput);
                                            ErrorList.add(e); 
                                        }
                                    }
                                }
                        }
                    }
                }
            }
        }
        
        if(errorOutput.equals("No Error"))
        {
            System.out.println("No Errors Detected In LogicaNotWithVector\n");
        }
        
        return ErrorList;
    }
}
