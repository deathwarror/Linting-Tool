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
public class Block {
    protected String BlockName;
    protected String BlockType;
    protected ArrayList<Variable> vars;
    protected ArrayList<Block> subBlocks;
    protected ArrayList<AssignmentStatement> assignments;
    protected Block parent;

    Block(){
        BlockType = "";
        BlockName = "";
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();

    }
    Block(String type, Block comesFrom){
        BlockType = type;
        BlockName = "";
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();
        parent = comesFrom;
    }
    Block(String type, Block comesFrom, String name){
        BlockType = type;
        BlockName = name;
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();
        parent = comesFrom;
    }
    public void addVariable(Variable newVariable){
        vars.add(newVariable);
        sortVariables();
    }
    public void addSubBlock(Block newSubBlock){
        subBlocks.add(newSubBlock);
    }
    public void addAssignment(AssignmentStatement newAssignment){
        assignments.add(newAssignment);
    }

    public void sortVariables(){
        int i=0, j=0;
        Variable temp;
        for(i=0; i<vars.size(); i++){
            for(j=0; j<vars.size()-(i+1); j++){
                if(vars.get(j).compareVariable(vars.get(j+1)) == 1){
                    temp = vars.get(j);
                    vars.set(j, vars.get(j+1));
                    vars.set(j+1, temp);
                }
            }
        }
    }
    protected Variable findVariableInParentBlockHierarchy(String varToFind){
        int i = 0;
        Block checker = this;
        do{
            for(i=0; i<checker.vars.size(); i++){
                if(checker.vars.get(i).getName().equals(varToFind))
                    return checker.vars.get(i);
            }
            checker = checker.parent;
        }while(checker != null);
        return null;
    }

    @Override
    public String toString(){
        int i=0;
        String retVar = BlockType + " " + BlockName + " ";

        for(i=0; i<vars.size(); i++){}
        for(i=0; i<subBlocks.size(); i++){
            retVar += subBlocks.get(i).toString();
        }
        for(i=0; i<assignments.size(); i++){}

        return retVar;
    }
}
