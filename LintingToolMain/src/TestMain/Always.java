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
    
    private String statementText;
    private ArrayList<Integer> alwaysBlockOrder;

    Always(Block comesFrom, String name){
        BlockType = "";
        BlockName = name;
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();
        sensitivityList = new ArrayList();
        alwaysBlockOrder = new ArrayList();
        statementText = "";
        
        parent = comesFrom;
        LineNumber = Parser.currentLineNumber;
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
            for(; !temp.equals("end") && !temp.equals("##END_OF_MODULE_CODE"); temp=parser.getNextPiece()){
                //will need to handle: if, switch, assignments,
                //Syntax error if there are: nested always,
                if(!parser.pieceIsKeyword(temp) && parser.checkTaskOrFunctionName(temp)==0){
                    for(; !temp.equals(";") && !temp.equals("##END_OF_MODULE_CODE"); temp = parser.getNextPiece()){
                        statementText += temp+" ";
                    }
                    always.addAssignment(new AssignmentStatement(statementText,always));
                    always.alwaysBlockOrder.add(new Integer(0));
                    statementText = "";
                }
                else if(parser.checkTaskOrFunctionName(temp)!=0){
                    String taskCallText = "";
                    ArrayList<String> taskCallElements = new ArrayList();
                    for(; !temp.equals(";") && 
                            !temp.equals("##END_OF_MODULE_CODE"); 
                            temp=parser.getNextPiece()){
                        taskCallText += temp+" ";
                        taskCallElements.add(temp);
                    }
                    always.addVariable(new TaskCall(taskCallText,taskCallElements));
                    always.alwaysBlockOrder.add(new Integer(2));
                }
                else{
                    parser.checkForNewBlock(always, temp);
                    if(!temp.equals("$#"))
                        always.alwaysBlockOrder.add(new Integer(1));
                }
            }
        }else{
            if(temp.equals("$#")){
                parser.checkForNewBlock(always, temp);
                temp = parser.getNextPiece();
            }            
            if(!parser.pieceIsKeyword(temp)){
                if(!parser.pieceIsKeyword(temp) && parser.checkTaskOrFunctionName(temp)==0){
                    for(; !temp.equals(";") && !temp.equals("##END_OF_MODULE_CODE"); temp = parser.getNextPiece()){
                        statementText += temp+" ";
                    }
                    always.addAssignment(new AssignmentStatement(statementText,always));
                    always.alwaysBlockOrder.add(new Integer(0));
                    statementText = "";
                }
                else if(parser.checkTaskOrFunctionName(temp)!=0){
                    String taskCallText = "";
                    ArrayList<String> taskCallElements = new ArrayList();
                    for(; !temp.equals(";") && 
                            !temp.equals("##END_OF_MODULE_CODE"); 
                            temp=parser.getNextPiece()){
                        taskCallText += temp+" ";
                        taskCallElements.add(temp);
                    }
                    always.addVariable(new TaskCall(taskCallText,taskCallElements));
                    always.alwaysBlockOrder.add(new Integer(2));
                }
                else{
                    always.alwaysBlockOrder.add(new Integer(1));
                }
            }
        }

        return temp;
    }
    private void parseAlwaysHead(Parser parser){
        statementText = "always";
        String temp = parser.getNextPiece(); //temp will equal "@"
        statementText += (" "+temp);
        Variable tempVar; Reg tempReg; Wire tempWire;
        temp = parser.getNextPiece(); //temp will equal "(" or "*"
        statementText += (" "+temp);
        if(temp.equals("*")){ //if the sensitivities are not explicitely specified
            sensitivityList.add(new Variable("*",""));
            BlockType = "levelSensitive";
        }else { //if temp == "("
            temp = parser.getNextPiece(); //temp will equal the first item of interrest
            statementText += (" "+temp);
            for(; !temp.equals(")") && !temp.equals("##END_OF_MODULE_CODE"); 
                    temp = parser.getNextPiece(), statementText+=(" "+temp) ){
                if(temp.equals("$#")){
                    parser.updateLineNumber();
                }else if(temp.equals("*")){
                    sensitivityList.add(new Variable("*",""));
                    BlockType = "levelSensitive";
                }
                else if(temp.equals("posedge")){
                    if(!(BlockType.equals("") || BlockType.equals("edgeSensitive")) ){
                        addErrorMixedSensitivity(parser);
//                        return;
                    }
                    BlockType = "edgeSensitive";
                    temp = parser.getNextPiece(); statementText+=(" "+temp);
                    tempVar = this.findVariableInParentBlockHierarchy(temp);
                    if(tempVar != null){
                        if(tempVar.arraySize > 1){ // if the variable in question is an array
                            addErrorVectorArrayInSensList(temp, parser);
                        }
                        else if(true){//(tempVar.setEdgeSensitivity("posedge")){
                            sensitivityList.add(tempVar);
                        }else{
                            System.out.println("Error: Variable "+temp+" is already " +
                                    "driven on "+tempVar.getEdgeSensitivity());
                        }
                    }else if( !this.findVectorNameInParentBlockHierarchy(temp).isEmpty() ){ //if the variable is a vector
                        addErrorVectorArrayInSensList(temp, parser);
                    }
                    else{
                        System.out.println("Error: Variable "+temp+" in sensitivity list not defined!");
                    }

                }
                else if(temp.equals("negedge")){
                    if(!(BlockType.equals("") || BlockType.equals("edgeSensitive")) ){
                        addErrorMixedSensitivity(parser);
//                        return;
                    }
                    BlockType = "edgeSensitive";
                    temp = parser.getNextPiece(); statementText+=(" "+temp);
                    tempVar = this.findVariableInParentBlockHierarchy(temp);
                    if(tempVar != null){
                        if(tempVar.arraySize > 1){ // if the variable in question is an array
                            addErrorVectorArrayInSensList(temp, parser);
                        }
                        else if(true){//(tempVar.setEdgeSensitivity("negedge")){
                            sensitivityList.add(tempVar);
                        }else{
                            System.out.println("Error: Variable "+temp+" is already " +
                                    "driven on "+tempVar.getEdgeSensitivity());
                        }
                    }else if( !this.findVectorNameInParentBlockHierarchy(temp).isEmpty() ){ //if the variable is a vector
                        addErrorVectorArrayInSensList(temp, parser);
                    }
                    else{
                        System.out.println("Error: Variable "+temp+" in sensitivity list not defined!");
                    }

                }
                else if(temp.equals("or") || temp.equals(","));
                else{
                    if(!(BlockType.equals("") || BlockType.equals("levelSensitive")) ){
                        addErrorMixedSensitivity(parser);
//                        return;
                    }
                    BlockType = "levelSensitive";
                    tempVar = this.findVariableInParentBlockHierarchy(temp);
                    if(tempVar != null){
                        if(tempVar.arraySize > 1){ // if the variable in question is an array
                            addErrorVectorArrayInSensList(temp, parser);
                        }else{
                            sensitivityList.add(tempVar);
                        }
                    }else if( !this.findVectorNameInParentBlockHierarchy(temp).isEmpty() ){ //if the variable is a vector
                        addErrorVectorArrayInSensList(temp, parser);
                    }
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
        if(index< alwaysBlockOrder.size() && index>=0){
            return this.alwaysBlockOrder.get(index);
        }else{
            return this.alwaysBlockOrder.get(0);
        }
    }
    public String getAlwaysStatement(){
        return statementText;
    }
    public ArrayList<Variable> getSensitivityList()
    {
        return sensitivityList;
    }
    private void addErrorVectorArrayInSensList(String varName, Parser parser){
        Error e = new Error();
        e.setErrorNum("10");
        String errorOutput = "Error: Vector or Array Series in Always Block:\n";
        errorOutput += "\tin always on line : "+this.LineNumber+"\n\tVariable Name: ";
        errorOutput += varName;
        System.out.println(errorOutput+"\n");
        e.setErrorMsg(errorOutput);
        parser.addErrorToParserErrorList(e);
    }
    private void addErrorMixedSensitivity(Parser parser){
        Error e = new Error();
        e.setErrorNum("11");
        String errorOutput = "Error: Mixing of Level and Edge Sensitivity detected in Always Block:\n";
        errorOutput += "\tin always on line : "+this.LineNumber+"\n";
        System.out.println(errorOutput);
        e.setErrorMsg(errorOutput);
        parser.addErrorToParserErrorList(e);
    }
    @Override
    public String toString(){
        int i = 0; int subBlockCount=0; int assignmentStatementCount=0; int taskCallCount=0;
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
        temp += " )"; temp += "begin \\\\LINE: "+LineNumber+" Statement Text: "+statementText+"\n";
        for(i=0, subBlockCount=0, assignmentStatementCount=0; i<alwaysBlockOrder.size() ; i++){
           if(alwaysBlockOrder.get(i) == 0){
               temp+= assignments.get(assignmentStatementCount).toString();
               assignmentStatementCount++;
           }else if(alwaysBlockOrder.get(i) == 1){
               temp+= subBlocks.get(subBlockCount).toString();
               subBlockCount++;
           }else if(alwaysBlockOrder.get(i) == 2){
               temp+= vars.get(taskCallCount);
               taskCallCount++;
           }
        }
        temp += "end //end always\n";

        return temp;
    }

}
