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
            ArrayList<AssignmentStatement> assignments = alwaysBlocks.get(i).getAllAssignmentStatements();
            LHSvars = new ArrayList();
            for(int j=0;j<assignments.size();j++){
                LHSvars.addAll( assignments.get(j).LHSvars );
            }
            ArrayList<Variable>tempVars=LHSvars;
            LHSvars = new ArrayList();
            //This loop makes sure that each unique variable in the always block
            // is only checked once
            for(int j=0; j<tempVars.size();j++){
                if(!LHSvars.contains(tempVars.get(j)) ){
                    LHSvars.add(tempVars.get(j) );
                }
            }
            checkIfStatements(alwaysBlocks.get(i),errorList);
            for(int j=0;j<LHSvars.size();j++){
                ArrayList<Error> blockErrors = new ArrayList();
                checkSubBlocks(LHSvars.get(j),alwaysBlocks.get(i), blockErrors);
                if(!blockErrors.isEmpty()){
                    String text = "\tError: "+
                        "variable ("+LHSvars.get(j).name+") in always block on line ("+alwaysBlocks.get(i).LineNumber+") is a latch,\n"
                            + "\tassignments to "+LHSvars.get(j).name+" are missing from the following conditional blocks:";
                    Error err = new Error("16",text, alwaysBlocks.get(i).LineNumber);
                    errorList.add(err);
                    errorList.addAll( blockErrors);
                }
            }
        }
        
        return errorList;
    }
    private static boolean checkSubBlocks(Variable var, Block block, ArrayList<Error> list){
        ArrayList<AssignmentStatement> tempAss= block.getBlockAssignmentStatements();
        int varPresentInSubBlock=0;
        for(int i=0;i<tempAss.size(); i++){
            if(tempAss.get(i).LHSvars.contains(var)){
                return true;
            }
        }
        for(int i=0; i<block.subBlocks.size(); i++){
            if(checkSubBlocks(var,block.subBlocks.get(i), list)){
                varPresentInSubBlock++;
            }
        }
        if(block.subBlocks.size()==varPresentInSubBlock  && varPresentInSubBlock!=0){
            return true;
        }else if(block.subBlocks.size()==0){
            list.add(error16MissingAssignments(var,block));
        }
        return false;
    }
    private static void checkIfStatements(Block block, ArrayList<Error> list){
        int ifFlag=0;
        for(int i=0; i<block.subBlocks.size(); i++){
            if(block.subBlocks.get(i).getClass().equals(IfElse.class) ){
                IfElse ie = (IfElse)block.subBlocks.get(i);
                if(ie.getIfElseType()==0){//ie==an if statement
                    ifFlag++;
                }else if(ie.getIfElseType()==2){//ie==an else statement
                    ifFlag--;
                }
            }
        }
        if(ifFlag != 0){
            list.add(error16MissingElse(block));
        }
        for(int i=0; i<block.subBlocks.size(); i++){
            checkIfStatements(block.subBlocks.get(i), list);
        }
        
        return;
    }
    private static Error error16MissingAssignments(Variable var, Block block){
        String text = "\tSub-Error:\n"+
                "variable ("+var.name+") not assigned in block on line ("+block.LineNumber+")";
        Error err = new Error("16",text, block.LineNumber);
        return err;
    }
    private static Error error16MissingElse(Block block){
        String text = "\tError: Laches present\n"+
                "\"if\" without corresponding \"else\" in block on line ("+block.LineNumber+")";
        Error err = new Error("16",text, block.LineNumber);
        return err;
    }
}
