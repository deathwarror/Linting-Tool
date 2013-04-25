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
public class Error04IdentifyOperatorsInSensList {
     //This function identifies any operators in the sensitivity list
    public static ArrayList<Error> getErrors(Parser parser)
    {
        ArrayList<Error> ErrorList = new ArrayList();
        String errorOutput = "No Error";
        
        ArrayList<Block> blocks = parser.getBlockList();
        String AlwaysStatement;
        Block currentBlock;
        String CharactersIdentified;
        //Go through the list of blocks
        for(int i = 0;i<blocks.size();i++)
        {
            //get the current block
            currentBlock = blocks.get(i);
            
            //see if the current block is an always block
            if(currentBlock.getClass() == Always.class)
            {
                CharactersIdentified = "";
                AlwaysStatement = ((Always)currentBlock).getAlwaysStatement();
                int j = AlwaysStatement.indexOf("(");
                int k = AlwaysStatement.lastIndexOf(")");
                if(j>0&&k>0)
                {
                    AlwaysStatement = AlwaysStatement.substring(j,k);
                }
                if(AlwaysStatement.contains("="))
                {
                    CharactersIdentified += "="; 
                }
                if(AlwaysStatement.contains("|"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "|"; 
                    else
                    CharactersIdentified += ", |"; 
                }
                if(AlwaysStatement.contains("&"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "&"; 
                    else
                    CharactersIdentified += ", &"; 
                }
                if(AlwaysStatement.contains("*"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "*"; 
                    else
                    CharactersIdentified += ", *"; 
                }
                if(AlwaysStatement.contains("/"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "/"; 
                    else
                    CharactersIdentified += ", /"; 
                }
                if(AlwaysStatement.contains("+"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "+"; 
                    else
                    CharactersIdentified += ", +"; 
                }
                if(AlwaysStatement.contains("-"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "-"; 
                    else
                    CharactersIdentified += ", -"; 
                }
                if(AlwaysStatement.contains("%"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "%"; 
                    else
                    CharactersIdentified += ", %"; 
                }
                if(AlwaysStatement.contains("!"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "!"; 
                    else
                    CharactersIdentified += ", "; 
                }
                if(AlwaysStatement.contains("<"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "<"; 
                    else
                    CharactersIdentified += ", <"; 
                }
                if(AlwaysStatement.contains(">"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += ">"; 
                    else
                    CharactersIdentified += ", >"; 
                }
                if(AlwaysStatement.contains("^"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "^"; 
                    else
                    CharactersIdentified += ", ^"; 
                }
                if(AlwaysStatement.contains("~"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "~"; 
                    else
                    CharactersIdentified += ", ~"; 
                }
                if(AlwaysStatement.contains("?"))
                {
                    if(CharactersIdentified.equals(""))
                    CharactersIdentified += "?"; 
                    else
                    CharactersIdentified += ", ?"; 
                }
                
                if(!(CharactersIdentified.equals("")))
                {
                    errorOutput = "Error: Detected Mathametical or Logical operator in the Sensitivity List:\n";
                    errorOutput += "\tin always on line "+currentBlock.LineNumber+": \n\t Character List: "+CharactersIdentified;
                    errorOutput += "\n\tLine "+currentBlock.LineNumber+":\t"+((Always)currentBlock).getAlwaysStatement()+";\n";
                                    
                    //put the error message into the error list
                    Error e = new Error();
                    e.setErrorMsg(errorOutput);
                    e.setErrorNum("04");
                    e.addLineNumber(currentBlock.LineNumber);
                    ErrorList.add(e);
                    System.out.println(errorOutput);
                    
                }
                
            }
        }
        
        
        if(errorOutput.equals("No Error"))
        {
            System.out.println("No Operators In Sensitivity List!\n");
            return ErrorList;
        }
        else
        {
            return ErrorList;
        }
    }
    
}
