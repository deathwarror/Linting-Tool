/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

import java.util.ArrayList;
/**
 * This class is to be used for conditional statements used in "if" and "else if",
 */

public class ConditionStatement extends AssignmentStatement {
    ArrayList<Variable> conditionVars;
    ConditionStatement(String rawText, Block blockSource){
        assignmentText = rawText;
        conditionVars = new ArrayList();
        LineNumber = Parser.currentLineNumber;
        parent = blockSource;
        
        identifyConditionVariables();
    }
    
    private void identifyConditionVariables(){
        String preserve = assignmentText;
        String temp="";
        Variable var;
        for(temp=super.getNextPiece();!temp.equals("##END_OF_STATEMENT");temp=super.getNextPiece()){
            if( parent.findVariableInParentBlockHierarchy(temp) != null){
                conditionVars.add(parent.findVariableInParentBlockHierarchy(temp));
            }else {
                conditionVars.addAll(parent.findVectorNameInParentBlockHierarchy(temp));
            }
        }
        assignmentText = preserve;
    }

    @Override
    public String toString(){
        return assignmentText + "";
    }

}
