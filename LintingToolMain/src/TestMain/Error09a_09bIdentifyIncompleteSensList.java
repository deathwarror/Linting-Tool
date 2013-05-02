package TestMain;

import java.util.ArrayList;

//Finds any incomplete sensitivity lists
/**
 *
 * @author Deathwarror
 */
public class Error09a_09bIdentifyIncompleteSensList {
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
        ArrayList<Variable> OrigSensList;
        ArrayList<Variable> UsedVars;
        
        
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
                    OrigSensList = new ArrayList();
                    OrigSensList.addAll(SensList);
                    UsedVars = new ArrayList();
                    //if there at least one item the sensitivity list
                    if(SensList.size()>0)
                    {
                        
                        statements=currentBlock.getAllAssignmentStatements();
                        for(int j = 0; j < SensList.size() && !statements.isEmpty(); j++)
                        {
                            currentStatement = statements.get(j);
                            ArrayList<Variable> vl;
                            vl= currentStatement.RHSvars;
                            
                            for(int k = 0; k <vl.size(); k++)
                            {
                                currentVar = vl.get(k);
                                
                                
                                //used to identify listed signals
                                for(int l = 0; l <SensList.size(); l++)
                                {
                                    if(currentVar.compareTo(SensList.get(l)))
                                    {
                                        SensList.remove(l);
                                        l--;
                                    }
                                } 
                                
                                
                                
                                //used to identify unlisted signals
                                if(UsedVars.isEmpty())
                                {
                                    UsedVars.add(currentVar);
                                }
                                for(int l = 0; l <UsedVars.size();l++)
                                {
                                    if(!(currentVar.compareTo((UsedVars.get(l)))))
                                    {
                                        UsedVars.add(currentVar);
                                    }
                                }
                            }
                        }  
                    }
                    
                    
                    //compare the used list with the original sens list
                    for(int varCount = 0;varCount <(OrigSensList.size());varCount++)
                        {
                            for(int variables = 0;variables<UsedVars.size();variables++)
                            {
                                //go though the original sens list
                                currentVar = UsedVars.get(variables);
                                if(currentVar.compareTo(OrigSensList.get(varCount)))
                                {
                                    //remove any matching
                                    UsedVars.remove(variables);
                                    variables--;
                                }
                            }
                            
                        }
                    //if there is a used signal not listed in the sensitivity list
                    if(UsedVars.size() > 0)
                    {
                        Error e = new Error();
                        e.setErrorNum("09b");
                        errorOutput = "Used signal not in sensitivity list:\n";
                        errorOutput += "\tin always on line : "+currentBlock.LineNumber +"\n\tUsed Variable(s) not listed: ";
                        e.addLineNumber(currentBlock.LineNumber);
                        //add the unused Vars
                        for(int varCount = 0;varCount <(UsedVars.size()-1);varCount++)
                        {
                            errorOutput+= UsedVars.get(varCount).getName()+", ";
                        }
                        errorOutput+=UsedVars.get(UsedVars.size()-1).getName();
                        System.out.println(errorOutput+"\n");
                        e.setErrorMsg(errorOutput);
                        ErrorList.add(e);
                    }
                    
                    
                    //if all signals weren't used in the sensitivity list
                    if(SensList.size()>0)
                    {
                        Error e = new Error();
                        e.setErrorNum("09a");
                        errorOutput = "Unused signal in sensitivity list:\n";
                        errorOutput += "\tin always on line : "+currentBlock.LineNumber +"\n\tUnused Variable(s): ";
                        e.addLineNumber(currentBlock.LineNumber);
                        for(int varCount = 0;varCount <(SensList.size()-1);varCount++)
                        {
                            errorOutput+= SensList.get(varCount).getName()+", ";
                        }
                        errorOutput+=SensList.get(SensList.size()-1).getName();
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
