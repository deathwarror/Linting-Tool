/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

/**
 *
 * @author Hell-o
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
