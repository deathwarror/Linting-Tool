/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestMain;

import java.util.ArrayList;

/**
 *
 * @author cloud9
 */
public class Error16IdentifyLatches {
    public static ArrayList<Error> getErrors(Parser parser)
    {
        ArrayList<Error> errorList = new ArrayList();
        ArrayList<Block> alwaysBlocks = parser.getBlockList();
        ArrayList<Variable> LHSvars;
        //This loop collects all of the level-sensitive always blocks
        //(all of the combinatorial logic blocks)
        for(int i=0; i< alwaysBlocks.size(); i++){
            if( alwaysBlocks.get(i).getClass() != Always.class  
                    || !alwaysBlocks.get(i).BlockType.equals("levelSensitive")){
                alwaysBlocks.remove(i);
                i--;
            }
        }
        
        for(int i=0; i< alwaysBlocks.size(); i++){
            ArrayList<Error> errors = new ArrayList();
            ArrayList<AssignmentStatement> assignments = alwaysBlocks.get(i).getAllAssignmentStatements();
            LHSvars = new ArrayList();
            for(int j=0;j<assignments.size();j++){
                LHSvars.addAll( assignments.get(j).LHSvars );
            }
            for(int j=0;j<LHSvars.size();j++){
                errorList.addAll( checkSubBlocks(LHSvars.get(j),alwaysBlocks.get(i)));
            }
        }
        
        return errorList;
    }
    private static ArrayList<Error> checkSubBlocks(Variable var, Block block){
        
        
        return null;
    }
    private static Error error16(Variable var, Block block){
        return new Error();
    }
}
