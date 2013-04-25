package TestMain;

import java.util.ArrayList;

//Finds any incomplete sensitivity lists
/**
 *
 * @author Deathwarror
 */
public class Error09IdentifyIncompleteSensList {
    public static ArrayList<Error> getErrors(Parser parser)
    {
        ArrayList<Error> ErrorList = new ArrayList();
        
        String errorOutput = "No Error";
        
        ArrayList<Block> blocks = parser.getBlockList();
        ArrayList<AssignmentStatement> statements;
        Variable currentVar;
        Block currentBlock;
        AssignmentStatement currentStatement; 
        ArrayList<Variable> SensList;
        
        
        //go through all the blocks
        for(int i = 0; i < blocks.size(); i++)
        {
            currentBlock = blocks.get(i);
            if(currentBlock.getClass() == Always.class)
            {
                if((((Always)currentBlock).BlockType).equals("levelSensitive"))
                {
                    //Get the Sensitivity list for the flip flop type always block
                    SensList = (((Always)currentBlock).getSensitivityList());

                    //if there at least one item the sensitivity list
                    if(SensList.size()>0)
                    {
                        
                        statements=currentBlock.getAllAssignmentStatements();
                        for(int j = 0; j < SensList.size(); j++)
                        {
                            currentStatement = statements.get(j);
                            ArrayList<Variable> vl;
                            vl= currentStatement.RHSvars;
                            for(int k = 0; k <vl.size(); k++)
                            {
                                currentVar = vl.get(k);
                                for(int l = 0; l <SensList.size(); l++)
                                {
                                    if(currentVar.compareTo(SensList.get(l)))
                                    {
                                        SensList.remove(l);
                                        l--;
                                    }
                                }
                                 
                            }
                        }  
                    }
                    //if all signals weren't used in the sensitivity list
                    if(SensList.size()>0)
                    {
                        Error e = new Error();
                        e.setErrorNum("07");
                        errorOutput = "Error: No Clock Detected or clock is used in always block:\n";
                        errorOutput += "\tin always on line : "+currentBlock.LineNumber +"\n\tUnused Variable(s): ";
                        e.addLineNumber(currentBlock.LineNumber);
                        for(int varCount = 0;varCount <(SensList.size()-1);varCount++)
                        {
                            errorOutput+= SensList.get(varCount).getName()+", ";
                        }
                        errorOutput+=SensList.get(SensList.size()-1);
                        System.out.println(errorOutput+"\n");
                        e.setErrorMsg(errorOutput);
                        ErrorList.add(e);
                    }
                }
            }
        }
        if(errorOutput.equals("No Error"))
        {
            System.out.println("No Error Detected in Incomplete Sensitivity List\n");
        }
        return ErrorList;
    }
}
