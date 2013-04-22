/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

import java.util.ArrayList;
/**
 * This Class is to be used for assignments associated with type WIRE
 */

public class ContinuousAssignment extends AssignmentStatement {
    ContinuousAssignment(String rawText, Block blockSource){
        assignmentText = rawText;
        LHSvars = new ArrayList();
        RHSvars = new ArrayList();
        parent = blockSource;
        LineNumber = Parser.currentLineNumber;

        identifyLHSvariables();
        identifyRHSvariables();
    }

    @Override
    protected void identifyLHSvariables(){
        String preserve = assignmentText;
        String temp = super.getNextPiece();
        Variable tempVar=null;
        for(; !temp.equals("="); temp=super.getNextPiece()){
            if( !super.expressionPiece(temp) ){
                tempVar = parent.findVariableInParentBlockHierarchy(temp);
                if(tempVar != null){
                    LHSvars.add(tempVar);
                }
            }
        }
        assignmentText = preserve;
    }

    @Override
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
    @Override
    public String toString(){
        String text = "";
        text += "assign " + assignmentText + ";\\\\ ";
        text +=  "LHSvars: "+LHSvars.toString();
        text +=  ",   RHSvars: "+RHSvars.toString()+" LINE: "+LineNumber+"\n";
        return text;
    }

}
