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
public class IfElse extends Block {
    private ConditionStatement condition;
    /*"ifElseBlockOrder" preserves the structureal order of statements and
     * subBlocks in and always block.
     * 0 - indicates an assignment statement,
     * 1- indicates the presence of another subBlock
     */
    private ArrayList<Integer> ifElseBlockOrder;
    private int ifElseType; //0="if", 1="else if", 2="else"

    IfElse(Block comesFrom, String name){
        BlockType = "";
        BlockName = name;
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();
        ifElseBlockOrder = new ArrayList();

        parent = comesFrom;
    }

    public static IfElse parseIf(Block current, Parser parser){
        IfElse ie = new IfElse(current,"");
        current.addSubBlock(ie);
        String temp = "";
        String statementText = "";
        int ifConditionStatementBreak=0;
        temp = parser.getNextPiece(); // temp should get a "(" back
        //This loop parses the condition statement at the beginning of the if block
        for(ifConditionStatementBreak=1, temp=parser.getNextPiece(), 
                statementText=""; ifConditionStatementBreak!=-1; ){
            if(temp.equals("(")){
                ifConditionStatementBreak++;
                statementText += temp+ " ";
                temp = parser.getNextPiece();
            }else if(temp.equals(")")){
                ifConditionStatementBreak--;
                if(ifConditionStatementBreak!=0){
                    statementText += temp+ " ";
                    temp = parser.getNextPiece();
                }else {
                    ifConditionStatementBreak = -1;
                }
            }else {
                statementText += temp+ " ";
                temp = parser.getNextPiece();
            }
        }
        ie.condition = new ConditionStatement(statementText);

        temp = parser.getNextPiece();
        // if there will be multiple sublocks or assginments
        if(temp.equals("begin")){
            for(temp=parser.getNextPiece(); !temp.equals("end"); temp=parser.getNextPiece() ){
                if(parser.pieceIsKeyword(temp)){
                    parser.checkForNewBlock(ie, temp);
                    ie.ifElseBlockOrder.add(new Integer(1));
                }else {
                    for(statementText=""; !temp.equals(";"); temp = parser.getNextPiece()){
                        statementText+=temp;
                    }
                    ie.addAssignment(new AssignmentStatement(statementText));
                    ie.ifElseBlockOrder.add(new Integer(0));
                }
            }
        }else {
            if(parser.pieceIsKeyword(temp)){
                parser.checkForNewBlock(ie, temp);
                ie.ifElseBlockOrder.add(new Integer(1));
            }else {
                for(statementText=""; !temp.equals(";"); temp = parser.getNextPiece()){
                    statementText+=temp;
                }
                ie.addAssignment(new AssignmentStatement(statementText));
                ie.ifElseBlockOrder.add(new Integer(0));
            }
        }

        return ie;
    }
    public static String parseElse(Block current, Parser parser){
        String temp = parser.getNextPiece();
        String statementText = "";
        //if this "else" statement is really and "else if" then parse it like
        // an "if" statment, othewise parse it as an "else"
        if(temp.equals("if")){
            IfElse ie = parseIf(current, parser);
            ie.setIfElseType(1);
        }else if(temp.equals("begin")){
            IfElse ie = new IfElse(current, "");
            current.addSubBlock(ie);
            ie.setIfElseType(2);
            for(temp=parser.getNextPiece(); !temp.equals("end"); temp=parser.getNextPiece() ){
                if(parser.pieceIsKeyword(temp)){
                    parser.checkForNewBlock(ie, temp);
                    ie.ifElseBlockOrder.add(new Integer(1));
                }else {
                    for(statementText=""; !temp.equals(";"); temp = parser.getNextPiece()){
                        statementText+=temp;
                    }
                    ie.addAssignment(new AssignmentStatement(statementText));
                    ie.ifElseBlockOrder.add(new Integer(0));
                }
            }
        }else {
            IfElse ie = new IfElse(current, "");
            current.addSubBlock(ie);
            ie.setIfElseType(2);
            if(parser.pieceIsKeyword(temp)){
                parser.checkForNewBlock(ie, temp);
                ie.ifElseBlockOrder.add(new Integer(1));
            }else {
                for(statementText=""; !temp.equals(";"); temp = parser.getNextPiece()){
                    statementText+=temp;
                }
                ie.addAssignment(new AssignmentStatement(statementText));
                ie.ifElseBlockOrder.add(new Integer(0));
            }
        }


        return temp;
    }
    public void setIfElseType(int type){
        this.ifElseType = type;
    }
    public int getIfElseType(){
        return this.ifElseType;
    }
    @Override
    public String toString(){
        String temp = "";
        int i=0; int subBlockCount=0; int assignmentStatementCount=0;
        if(this.ifElseType == 0){
            temp += "if("; 
            temp += this.condition.toString();
            temp += ") begin\n";
            for(i=0, subBlockCount=0, assignmentStatementCount=0;
                i< this.ifElseBlockOrder.size(); i++){
                if(ifElseBlockOrder.get(i) == 1){
                    temp += this.subBlocks.get(subBlockCount).toString();
                    subBlockCount++;
                }else {
                    temp += this.assignments.get(assignmentStatementCount);
                    assignmentStatementCount++;
                }
            }
            temp += "end //ends if()\n";
        }else if(this.ifElseType==1){
            temp += "else if("; temp += this.condition.toString(); temp += ") begin\n";
            for(i=0, subBlockCount=0, assignmentStatementCount=0;
                i< this.ifElseBlockOrder.size(); i++){
                if(ifElseBlockOrder.get(i) == 1){
                    temp += this.subBlocks.get(subBlockCount).toString();
                    subBlockCount++;
                }else {
                    temp += this.assignments.get(assignmentStatementCount);
                    assignmentStatementCount++;
                }
            }
            temp += "end //ends else if()\n";
        }else {
            temp += "else begin\n";
            for(i=0, subBlockCount=0, assignmentStatementCount=0;
                i< this.ifElseBlockOrder.size(); i++){
                if(ifElseBlockOrder.get(i) == 1){
                    temp += this.subBlocks.get(subBlockCount).toString();
                    subBlockCount++;
                }else {
                    temp += this.assignments.get(assignmentStatementCount);
                    assignmentStatementCount++;
                }
            }
            temp += "end //ends else\n";
        }

        return temp;
    }
}
