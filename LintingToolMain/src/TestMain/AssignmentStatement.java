/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

import java.util.ArrayList;
/**
 *
 * @author Hell-o
 */
public class AssignmentStatement {
    //LHSvars needs to be an ArrayList in case of the presence of vectors
    protected ArrayList<Variable> LHSvars;
    protected ArrayList<Variable> RHSvars;
    protected String assignmentText;
    Block parent;
    AssignmentStatement(){
        assignmentText = "";
        LHSvars = new ArrayList();
        RHSvars = new ArrayList();
    }
    AssignmentStatement(String rawText){
        assignmentText = rawText;
        LHSvars = new ArrayList();
        RHSvars = new ArrayList();
    }
    AssignmentStatement(String rawText, Block blockSource){
        assignmentText = rawText;
        LHSvars = new ArrayList();
        RHSvars = new ArrayList();
        parent = blockSource;
        
        identifyLHSvariables();
    }

    protected void identifyLHSvariables(){
        String preserve = assignmentText;
        String temp = getNextPiece();
        Variable tempVar=null;
        for(; !temp.equals("="); temp=getNextPiece()){
            if( !expressionPiece(temp) ){
                tempVar = parent.findVariableInParentBlockHierarchy(temp);
                if(tempVar != null){
                    LHSvars.add(tempVar);
                }
            }
        }
        assignmentText = preserve;
    }

    protected String getNextPiece(){
        String[] piece = assignmentText.split(" ",2);
        if(piece.length != 1)
            assignmentText = piece[1];
        while( piece[0].equals("")){
            piece = assignmentText.split(" ",2);
            assignmentText = piece[1];
        }
        return piece[0];
    }

    public ArrayList<Variable> getLHSvars(){
        return LHSvars;
    }

    public ArrayList<Variable> getRHSvars(){
        return RHSvars;
    }

    protected boolean expressionPiece(String piece){
        if(piece.equals("=")){
            return true;
        }else if(piece.equals("!")){
            return true;
        }else if(piece.equals("=")){
            return true;
        }else if(piece.equals("~")){
            return true;
        }else if(piece.equals("&")){
            return true;
        }else if(piece.equals("|")){
            return true;
        }else if(piece.equals("^")){
            return true;
        }else if(piece.equals("%")){
            return true;
        }else if(piece.equals("*")){
            return true;
        }else if(piece.equals("+")){
            return true;
        }else if(piece.equals("-")){
            return true;
        }else if(piece.equals("/")){
            return true;
        }else if(piece.equals("(")){
            return true;
        }else if(piece.equals(")")){
            return true;
        }else if(piece.equals("[")){
            return true;
        }else if(piece.equals("]")){
            return true;
        }else if(piece.equals("<")){
            return true;
        }else if(piece.equals(">")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return assignmentText + ";\n";
    }
}
