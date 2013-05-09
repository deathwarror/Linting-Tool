/**
 * @Author:     Kenneth Hassey
 * @Date:       5/8/2013
 * @Version:    1.000
 * Function:
 *      Looks for vectors in the sensitivity list of always blocks
 *      in edge sensitivity blocks only
 *
 * Status: Tested Working;
 */
package TestMain;

import java.util.ArrayList;

/**
 *
 * @author Deathwarror
 */
public class Error10VectorArrayInSensList {
    public static ArrayList<Error> getErrors(Parser parser)
    {
        ArrayList<Error> ErrorList = new ArrayList();
        
        String errorOutput = "No Error";
        
        ArrayList<Block> blocks = parser.getBlockList();
        String currentVar;
        ArrayList<Variable> Variables = parser.getVariableList();
        ArrayList<String> VectorNames = new ArrayList();
        Block currentBlock;
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
        //go through the list of blocks
        for(int i = 0; i < blocks.size();i++)
        {
            currentBlock = blocks.get(i);
            //if the current block is an always block
            if(currentBlock.getClass() == Always.class)
            {
                currentStatement = ((Always)currentBlock).getAlwaysStatement();
                
                if(currentBlock.BlockType.equals("edgeSensitive"))
                {
                    int ignore = 0;
                    for(int j = 0; j < VectorNames.size();j++)
                    {
                        currentVar = VectorNames.get(j);
                        
                        //if there is a series of vector bits detected
                        if(currentStatement.contains(":")&&ignore == 0)
                        {
                                Error e = new Error();
                                e.setErrorNum("10");
                                errorOutput = "Vector or Array Series in Edge Sensitive Always Block:\n";
                                errorOutput += "\tin always on line : "+currentBlock.LineNumber;
                                e.addLineNumber(currentBlock.LineNumber);
                                System.out.println(errorOutput+"\n");
                                e.setErrorMsg(errorOutput);
                                ErrorList.add(e);
                                ignore =1;
                        }
                        //see if the VectorName is in the sensitivity list
                        else if(currentStatement.contains(" "+currentVar+" ")&& ignore == 0)
                        {
                            int StartIndex = currentStatement.indexOf(" "+currentVar+" ");
                            String tempStr = currentStatement.substring(StartIndex);
                            //if the variable doesn't have a bracket following it.
                            char debugCharIndex=tempStr.charAt(currentVar.length());
                            if(tempStr.charAt(currentVar.length()) != '[')
                            {
                                Error e = new Error();
                                e.setErrorNum("10");
                                errorOutput = "Vector or Array Series in Edge Sensitive Always Block:\n";
                                errorOutput += "\tin always on line : "+currentBlock.LineNumber+"\n\tVariable Name: ";
                                errorOutput += currentVar;
                                e.addLineNumber(currentBlock.LineNumber);
                                System.out.println(errorOutput+"\n");
                                e.setErrorMsg(errorOutput);
                                ErrorList.add(e);
                            }
                        }
                    }
                }//end of level sensitive check
            }
        }
        
        if(errorOutput.equals("No Error"))
        {
            System.out.println("No Errors Detected In VectorArraySensList\n");
        }
        
        return ErrorList;
    }
}
