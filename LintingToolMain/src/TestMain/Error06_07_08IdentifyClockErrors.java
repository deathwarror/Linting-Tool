//detects any always blocks that a for flip flops and searches to see if they
//are missing any reset variables.

package TestMain;
import java.util.ArrayList;
/**
 *
 * @author Deathwarror
 */
public class Error06_07_08IdentifyClockErrors {
    public static ArrayList<Error> getErrors(Parser parser)
    {
        ArrayList<Error> ErrorList = new ArrayList();
        
        String errorOutput = "No Error";
        
        ArrayList<Block> blocks = parser.getBlockList();
        ArrayList<Block> AlwaysSubBlocks;
        Variable currentVar;
        Block currentBlock;
        ConditionStatement currentStatement;
        ArrayList<Variable> Clocks = new ArrayList(); 
        ArrayList<Variable> SensList;
        
        
        
        for(int i = 0; i < blocks.size(); i++)
        {
            currentBlock = blocks.get(i);
            if(currentBlock.getClass() == Always.class)
            {
                if((((Always)currentBlock).BlockType).equals("edgeSensitive"))
                {
                    //Get the Sensitivity list for the flip flop type always block
                    SensList = (((Always)currentBlock).getSensitivityList());
                    
                    //if there is only one item in the senselist
                    if(SensList.size() == 1)
                    {
                        Error e = new Error();
                        e.setErrorNum("06");
                        errorOutput = "Error: No Reset or clock Detected in Flip Flop type always block:\n";
                        errorOutput += "\tin always on line :"+currentBlock.LineNumber;
                        e.addLineNumber(currentBlock.LineNumber);
                        e.setErrorMsg(errorOutput);
                        System.out.println(errorOutput + "\n");
                        ErrorList.add(e);
                    }
                    
                    //if there more than one item the sensitivity list
                    else if(SensList.size()>1)
                    {
                        AlwaysSubBlocks=currentBlock.subBlocks;
                        for(int j = 0; j < AlwaysSubBlocks.size(); j++)
                        {
                            if(AlwaysSubBlocks.get(j).getClass() == IfElse.class)
                            {
                                currentStatement = ((IfElse)(AlwaysSubBlocks.get(j))).getConditionStatement();
                                if(currentStatement != null)
                                {
                                    ArrayList<Variable> vl =currentStatement.conditionVars;
                                    for(int k = 0; k <vl.size(); k++)
                                    {
                                        for(int l = 0; l <SensList.size(); l++)
                                        {
                                            currentVar = vl.get(k);
                                            if(currentVar.compareTo(SensList.get(l)))
                                            {
                                                SensList.remove(l);
                                                l = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                        //if all signals were used in the sensitivity list
                        if(SensList.isEmpty())
                        {
                            Error e = new Error();
                            e.setErrorNum("07");
                            errorOutput = "Error: No Clock Detected or clock is used in always block:\n";
                            errorOutput += "\tin always on line : "+currentBlock.LineNumber;  
                            e.addLineNumber(currentBlock.LineNumber);
                            e.setErrorMsg(errorOutput);
                            System.out.println(errorOutput + "\n");
                            ErrorList.add(e);
                        }
                        
                        //if there was more than one unused signal in the sensitivity list
                        else if(SensList.size()>1)
                        {
                            Error e = new Error();
                            e.setErrorNum("07");
                            errorOutput = "Error: Multiple Clocks or unused signals detected in always block:\n";
                            errorOutput += "\tin always on line : "+currentBlock.LineNumber;  
                            e.addLineNumber(currentBlock.LineNumber);
                            e.setErrorMsg(errorOutput);
                            System.out.println(errorOutput + "\n");
                            ErrorList.add(e);
                        }
                        //if there is only one variable that isnt used in the sensitivity list then assumes its the clock
                        else
                        {
                            Clocks.add(SensList.get(0));
                        }
                    }
                }
            }
        }
        
        
        //compares all clock detected variables and if a variable doesnt match
        //flags multiple clocks detected
        for(int i = 0; i<Clocks.size(); i++)
        {
            currentVar = Clocks.get(0);
            //if a clock doesnt match
            if(currentVar.compareTo(Clocks.get(i))==false)
            {
                Error e = new Error();
                e.setErrorNum("08");
                errorOutput = "Error: Multible Clocks detected in module\n";
                errorOutput += "\tFirst Clock: "+currentVar.getName()+"\n\tSecond Clock: "+Clocks.get(i).getName();
                e.setErrorMsg(errorOutput);
                System.out.println(errorOutput + "\n");
                ErrorList.add(e);               
            }
        }
        if(errorOutput.equals("No Error"))
        {
            System.out.println("No Problems with clock or reset detected in ClockErrors\n");
        }
        return ErrorList;
    }
}
