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
public class Always extends Block{
    private ArrayList<Variable> sensitivityList;
    /*"alwaysBlockOrder" preserves the structureal order of statements and 
     * subBlocks in and always block.
     * 0 - indicates an assignment statement,
     * 1- indicates the presence of another subBlock
     */
    private ArrayList<Integer> alwaysBlockOrder;

    Always(Block comesFrom, String name){
        BlockType = "";
        BlockName = name;
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();
        sensitivityList = new ArrayList();
        alwaysBlockOrder = new ArrayList();

        parent = comesFrom;
    }

    public static String parseAlways(Block current, Parser parser){
        Always always = new Always(current,"");
        current.addSubBlock(always);
        String temp = "";
        String statementText = "";
        always.parseAlwaysHead(parser);
        temp = parser.getNextPiece();
        //if there is more than one action in the always block
        if(temp.equals("begin")){
            temp = parser.getNextPiece();
            for(; !temp.equals("end"); temp=parser.getNextPiece()){
                //will need to hangle: if, switch, assignments,
                //Syntax error if there are: nested always,
                if(!parser.pieceIsKeyword(temp)){
                    for(; !temp.equals(";"); temp = parser.getNextPiece()){
                        statementText += temp;
                    }
                    always.addAssignment(new AssignmentStatement(statementText));
                    always.alwaysBlockOrder.add(new Integer(0));
                    statementText = "";
                }else{//need suppprt for adding other subBlocks
                    parser.checkForNewBlock(always, temp);
                    always.alwaysBlockOrder.add(new Integer(1));
                }
            }
        }else{
            if(!parser.pieceIsKeyword(temp)){
                if(!parser.pieceIsKeyword(temp)){
                    for(; !temp.equals(";"); temp = parser.getNextPiece()){
                        statementText += temp;
                    }
                    always.addAssignment(new AssignmentStatement(statementText));
                    always.alwaysBlockOrder.add(new Integer(0));
                    statementText = "";
                }else{//need suppprt for adding other subBlocks
                    always.alwaysBlockOrder.add(new Integer(1));
                }
            }
        }

        return temp;
    }
    private void parseAlwaysHead(Parser parser){
        String temp = parser.getNextPiece(); //temp will equal "@"
        Variable tempVar; Reg tempReg; Wire tempWire;
        temp = parser.getNextPiece(); //temp will equal "(" or "*"
        if(temp.equals("*")){ //if the sensitivities are not explicitely specified

        }else {
            temp = parser.getNextPiece(); //temp will equal the first item of interrest
            for(; !temp.equals(")"); temp = parser.getNextPiece() ){
                if(temp.equals("posedge")){
                    if(!(BlockType.equals("") || BlockType.equals("edgeSensitive")) ){
                        System.out.println("Error: Edge and Level Sensitivities mixed\n");
                        return;
                    }
                    BlockType = "edgeSensitive";
                    temp = parser.getNextPiece();
                    tempVar = this.findVariableInParentBlockHierarchy(temp);
                    if(tempVar != null){
                        if(tempVar.setEdgeSensitivity("posedge")){
                            sensitivityList.add(tempVar);
                        }else{
                            System.out.println("Error: Variable "+temp+" is already " +
                                    "driven on "+tempVar.getEdgeSensitivity());
                        }
                    }
                    else{
                        System.out.println("Error: Variable "+temp+" in sensitivity list not defined!");
                    }

                }
                else if(temp.equals("negedge")){
                    if(!(BlockType.equals("") || BlockType.equals("edgeSensitive")) ){
                        System.out.println("Error: Edge and Level Sensitivities mixed\n");
                        return;
                    }
                    BlockType = "edgeSensitive";
                    temp = parser.getNextPiece();
                    tempVar = this.findVariableInParentBlockHierarchy(temp);
                    if(tempVar != null)
                        if(tempVar.setEdgeSensitivity("negedge")){
                            sensitivityList.add(tempVar);
                        }else{
                            System.out.println("Error: Variable "+temp+" is already " +
                                    "driven on "+tempVar.getEdgeSensitivity());
                        }
                    else{
                        System.out.println("Error: Variable "+temp+" in sensitivity list not defined!");
                    }

                }
                else if(temp.equals("or"));
                else{
                    if(!(BlockType.equals("") || BlockType.equals("levelSensitive")) ){
                        System.out.println("Error: Edge and Level Sensitivities mixed\n");
                        return;
                    }
                    BlockType = "levelSensitive";
                    tempVar = this.findVariableInParentBlockHierarchy(temp);
                    if(tempVar != null)
                        sensitivityList.add(tempVar);
                    else{
                        System.out.println("Error: Variable "+temp+" in sensitivity list not defined!");
                    }
                }
            }
        }
    }
    public void addToAlwaysBlockOrder(int num){
        this.alwaysBlockOrder.add(num);
    }
    public int getFromAlwaysBlockOrder(int index){
        return this.alwaysBlockOrder.get(index);
    }
    @Override
    public String toString(){
        int i = 0; int subBlockCount=0; int assignmentStatementCount=0;
        String temp = "always@( ";
        if(this.BlockType.equals("edgeSensitive") && sensitivityList.size()>0){
            temp+= sensitivityList.get(0).getEdgeSensitivity();
            temp+= " "; temp+= sensitivityList.get(0).name;
            for(i=1; i < sensitivityList.size(); i++){
                temp+=" or";
                temp+= " "; temp+= sensitivityList.get(i).getEdgeSensitivity();
                temp+= " "; temp+= sensitivityList.get(i).name;
            }
        }
        else if(this.BlockType.equals("levelSensitive") && sensitivityList.size()>0){
            temp+= sensitivityList.get(0).name;
            for(i=1; i < sensitivityList.size(); i++){
                temp+=" or";
                temp+= " "; temp+= sensitivityList.get(i).name;
            }
        }
        temp += ")"; temp += "begin\n";
        for(i=0, subBlockCount=0, assignmentStatementCount=0; i<alwaysBlockOrder.size() ; i++){
           if(alwaysBlockOrder.get(i) == 0){
               temp+= assignments.get(assignmentStatementCount).toString();
               assignmentStatementCount++;
           }else if(alwaysBlockOrder.get(i) == 1){
               temp+= subBlocks.get(subBlockCount).toString();
               subBlockCount++;
           }
        }
        temp += "end //end always\n";

        return temp;
    }

}
