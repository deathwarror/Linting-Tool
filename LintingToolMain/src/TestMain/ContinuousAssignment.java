/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

import java.util.ArrayList;
/**
 * This Class is to be used for assignments assiciated with type WIRE
 */

public class ContinuousAssignment extends AssignmentStatement {
    ContinuousAssignment(String rawText, Block blockSource){
        assignmentText = rawText;
        LHSvars = new ArrayList();
        RHSvars = new ArrayList();
        parent = blockSource;
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

    protected void identifyRHSvariables(){
    }
    @Override
    public String toString(){
        String text = "";
        text += "assign " + assignmentText + ";\\\\ ";
        text +=  "LHSvars: "+LHSvars.toString();
        text +=  ",   RHSvars: "+RHSvars.toString()+"\n";
        return text;
    }

}
