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
    protected int LineNumber;
    AssignmentStatement(){
        assignmentText = "";
        LHSvars = new ArrayList();
        RHSvars = new ArrayList();
        LineNumber = Parser.currentLineNumber;
    }
    AssignmentStatement(String rawText){
        assignmentText = rawText;
        LHSvars = new ArrayList();
        RHSvars = new ArrayList();
        LineNumber = Parser.currentLineNumber;
        
        identifyLHSvariables();
    }
    AssignmentStatement(String rawText, Block blockSource){
        assignmentText = rawText;
        LHSvars = new ArrayList();
        RHSvars = new ArrayList();
        parent = blockSource;
        LineNumber = Parser.currentLineNumber;
        
        identifyLHSvariables();
        identifyRHSvariables();
    }

    protected void identifyLHSvariables(){
        String preserve = assignmentText;
        String temp = getNextPiece();
        Variable tempVar=null;
        for(; !temp.equals("=") && !temp.equals("<"); temp=getNextPiece()){
            if( !expressionPiece(temp) ){
                tempVar = parent.findVariableInParentBlockHierarchy(temp);
                if(tempVar != null){
                    LHSvars.add(tempVar);
                }else if(parent.findVectorNameInParentBlockHierarchy(temp).size()>0){
                    LHSvars.addAll(parent.findVectorNameInParentBlockHierarchy(temp));
                }
                else if(parent.findVectorNameInParentBlockHierarchy(temp).size()!=0){
                    String vecName = temp;
                    String vecStart = "";
                    String vecEnd = "";
                    temp = getNextPiece(); //should equal '['
                    for(temp=getNextPiece(); !temp.equals(":") && !temp.equals("]"); temp=getNextPiece()){
                        vecStart += temp;
                    }
                    int MSB = Integer.parseInt( Parser.parseNumberFromExpression(vecStart+" ") );
                    int LSB = -1;
                    if(temp.equals(":")){
                        for(temp=getNextPiece(); !temp.equals("]"); temp=getNextPiece()){
                            vecEnd += temp;
                        }
                        LSB = Integer.parseInt( Parser.parseNumberFromExpression(vecEnd+" ") );
                    }
                    
                    if(LSB == -1){
                        tempVar = parent.findVariableInParentBlockHierarchy(vecName+MSB);
                        if(tempVar != null){
                            LHSvars.add(tempVar);
                        }
                    }else{
                        if(MSB < LSB){
                            int num = MSB; MSB = LSB; LSB = num;
                        }
                        for(int i=0; MSB-i >= LSB; i++){
                            tempVar = parent.findVariableInParentBlockHierarchy(vecName+(LSB+i));
                            if(tempVar != null){
                                LHSvars.add(tempVar);
                            }
                        }
                    }
                }else{
                    
                }
            }
        }
        assignmentText = preserve;
    }
    protected void identifyRHSvariables(){
        String preserve = assignmentText;
        assignmentText = assignmentText.substring(assignmentText.indexOf("="));
        String temp;
        for(temp=getNextPiece(); !temp.equals("##END_OF_STATEMENT"); temp=getNextPiece()){
                        if( parent.findVariableInParentBlockHierarchy(temp) != null){
                RHSvars.add(parent.findVariableInParentBlockHierarchy(temp));
            }else {
                RHSvars.addAll(parent.findVectorNameInParentBlockHierarchy(temp));
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
            if(piece.length != 1){
                assignmentText = piece[1];
            }else{
                piece[0] = "##END_OF_STATEMENT";
            }
                
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
    
    public int getAssignmentStatementLineNumber(){
        return LineNumber;
    }

    @Override
    public String toString(){
        String text = "";
        text += assignmentText + ";\\\\ ";
        text +=  "LHSvars: "+LHSvars.toString();
        text +=  ",   RHSvars: "+RHSvars.toString()+" LINE: "+LineNumber+"\n";
        return text;
    }
}
