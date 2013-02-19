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
    protected Variable LHS;
    protected ArrayList<Variable> RHSvars;
    protected String assignmentText;
    AssignmentStatement(){
        assignmentText = "";
    }
    AssignmentStatement(String rawText){
        assignmentText = rawText;
    }


    @Override
    public String toString(){
        return assignmentText + ";\n";
    }
}
