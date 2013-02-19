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
public class SubCase extends Block{
    private ConditionStatement condition;
    /*"caseBlockOrder" preserves the structureal order of statements and
     * subBlocks in and always block.
     * 0 - indicates an assignment statement,
     * 1- indicates the presence of another subBlock
     */
    private ArrayList<Integer> subCaseBlockOrder;

    SubCase(Block comesFrom, String name){
        BlockType = "";
        BlockName = name;
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();
        subCaseBlockOrder = new ArrayList();

        parent = comesFrom;
    }

    public static String parseSubCase(Block current, Parser parser, String firstTemp){
        SubCase sub = new SubCase(current,"");
        current.addSubBlock(sub);
        String temp = firstTemp;
        String statementText = "";
        int subCaseConditionStatementBreak=0;
//        temp = parser.getNextPiece(); // temp should get a "(" back
        //This loop parses the condition statement at the beginning of the case block
        for(subCaseConditionStatementBreak=0, 
        statementText=""; subCaseConditionStatementBreak!=-1; ){
            if(temp.equals(":")){
                if(subCaseConditionStatementBreak == 0){
                    subCaseConditionStatementBreak = -1;
                }else {
                    statementText += temp+ " ";
                    temp = parser.getNextPiece();
                }
            }else if(temp.equals("[")){
                subCaseConditionStatementBreak++;
                statementText += temp+ " ";
                temp = parser.getNextPiece();
            }else if(temp.equals("]")){
                subCaseConditionStatementBreak--;
                statementText += temp+ " ";
                temp = parser.getNextPiece();
            }else {
                statementText += temp+ " ";
                temp = parser.getNextPiece();
            }
        }
        sub.condition = new ConditionStatement(statementText);

        temp = parser.getNextPiece();
        // if there will be multiple sublocks or assginments
        if(temp.equals("begin")){
            for(temp=parser.getNextPiece(); !temp.equals("end"); temp=parser.getNextPiece() ){
                if(parser.pieceIsKeyword(temp)){
                    parser.checkForNewBlock(sub, temp);
                    sub.subCaseBlockOrder.add(new Integer(1));
                }else {
                    for(statementText=""; !temp.equals(";"); temp = parser.getNextPiece()){
                        statementText+=temp;
                    }
                    sub.addAssignment(new AssignmentStatement(statementText));
                    sub.subCaseBlockOrder.add(new Integer(0));
                }
            }
        }else {
            if(parser.pieceIsKeyword(temp)){
                parser.checkForNewBlock(sub, temp);
                sub.subCaseBlockOrder.add(new Integer(1));
            }else {
                for(statementText=""; !temp.equals(";"); temp = parser.getNextPiece()){
                    statementText+=temp;
                }
                sub.addAssignment(new AssignmentStatement(statementText));
                sub.subCaseBlockOrder.add(new Integer(0));
            }
        }

        return temp;
    }
    @Override
    public String toString(){
        String temp="";
        int i=0; int subBlockCount=0; int assignmentStatementCount=0;
        temp += condition.toString()+": begin\n";

        for(i=0, subBlockCount=0, assignmentStatementCount=0;
            i< this.subCaseBlockOrder.size(); i++){
            if(subCaseBlockOrder.get(i) == 1){
                temp += this.subBlocks.get(subBlockCount).toString();
                subBlockCount++;
            }else {
                temp += this.assignments.get(assignmentStatementCount);
                assignmentStatementCount++;
            }
        }
        
        temp += "end\n";
        return temp;
    }
}
