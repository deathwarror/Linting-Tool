/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

/**
 * This class is to be used for conditional statements used in "if" and "else if",
 */

public class ConditionStatement extends AssignmentStatement {
    ConditionStatement(String rawText){
        assignmentText = rawText;
    }

    @Override
    public String toString(){
        return assignmentText + "";
    }

}
