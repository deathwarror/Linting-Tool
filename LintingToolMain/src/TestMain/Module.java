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
public class Module extends Block{

    protected ArrayList<Variable> preParameters;
    protected ArrayList<Variable> parameters;
    protected ArrayList<Variable> portVariables;

    Module(){
        BlockType = "";
        BlockName = "";
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();
        preParameters = new ArrayList();
        parameters = new ArrayList();
        portVariables = new ArrayList();
    }
    Module(String type, Block comesFrom){
        BlockType = type;
        BlockName = "";
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();
        parent = comesFrom;
        preParameters = new ArrayList();
        parameters = new ArrayList();
        portVariables = new ArrayList();
    }
    Module(String type, Block comesFrom, String name){
        BlockType = type;
        BlockName = name;
        vars = new ArrayList();
        subBlocks = new ArrayList();
        assignments = new ArrayList();
        parent = comesFrom;
        preParameters = new ArrayList();
        parameters = new ArrayList();
        portVariables = new ArrayList();
    }
    void addVariableToModule(Variable newVar){
        vars.add(newVar);
//        sortVariables();
    }

    void addVariableToModulePortList(Variable newVar){
        portVariables.add(newVar);
        addVariableToModule(newVar);
    }

    public Variable findVariableInModule(String searchName){
        for(int i=0; i<vars.size(); i++){
            if(vars.get(i).name.equals(searchName)){
                return vars.get(i);
            }
        }
        return null;
    }

    public void removeVariableFromModule(String removeName, ArrayList<Variable> rm){
        int i=0;
        for(i=0; !rm.get(i).name.equals(removeName) && i<rm.size(); i++);
        if(i < rm.size()){
            rm.remove(i);
        }
    }
    public void updatePortVariableInModule(String varName, String newType, String newAttribute){
        Variable newVar;
        /* If a variable by that name exists in both the overall variable list
         * "vars" and in the "portVariables" list, then remove it, and add the
         * updated variable.
         */
        if(findVariableInModule(varName) != null){
            if(findVariableInModulePortList(varName) != null){
                removeVariableFromModule(varName, vars);
                removeVariableFromModule(varName, portVariables);
                if(newType.equals("reg")){
                    newVar = new Reg(varName,newAttribute);
                    vars.add(newVar);
                    portVariables.add(newVar);
                }
                else if(newType.equals("wire")){
                    newVar = new Wire(varName,newAttribute);
                    vars.add(newVar);
                    portVariables.add(newVar);
                }
                else{
                    newVar = new Variable(varName,newType,newAttribute);
                    vars.add(newVar);
                    portVariables.add(newVar);
                }
            }
        }
//        sortModuleVariables();
    }
    public void sortModuleVariables(){
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

    public Variable findVariableInModulePortList(String searchName){
        for(int i=0; i<portVariables.size(); i++){
            if(portVariables.get(i).name.equals(searchName)){
                return portVariables.get(i);
            }
        }
        return null;
    }

    public static String parseModule(Block current, Parser parser){
        String temp = parser.getNextPiece();
        String lastType="DEFAULT_NET_TYPE";
        String lastAttribute = "";
        Module module = new Module("module",current, temp);
        module = parseModuleHead(module,parser);
        current.addSubBlock(module);
        Variable tempVar;
        do{
            temp = parser.getNextPiece();
            if(temp.equals("input")){
                lastAttribute = "input";
                temp = parser.getNextPiece();
                /*
                 * module's should not have registers as inputs
                 */
                if(temp.equals("reg")){
                    lastType = "ERROR";
                    temp = parser.getNextPiece();
//                    tempVar = module.findVariableInModule(temp);
//                    if(tempVar != null)
//                    module.addVariableToModule(new Reg(temp,"input"));
                    System.out.println("ERROR: Attempting to declare an input as type register\n");
                }
                else if(temp.equals("wire")){
                    lastType = "wire";
                    temp = parser.getNextPiece();
                    tempVar = module.findVariableInModule(temp);
                    if(tempVar != null){
                        /* This block checks to see if the input wire being
                         * declared is present in the module's port list, if
                         * not it prints out a (compiler) error
                         */
                        if(  tempVar.getType().equals("DEFAULT_NET_TYPE") &&
                                tempVar.getAttribute().equals("")){
                            module.updatePortVariableInModule(temp, "wire", "input");
                        }else{
                            /* This message should print only if the user is
                             * attempting to change a variable that was fully
                             * defined in the port list
                             */
                            System.out.print("ERROR: variable "+temp+" is declared more than once\n");
                        }
                    }else{
                        /* This message should print only if the user is
                         * attempting to add an input or output that is not
                         * already declared in the module's port list
                         */
                        System.out.print("ERROR: Module Inputs should be declared in ports list\n");
                    }
                }
                else{
                    lastType = "";
                    tempVar = module.findVariableInModule(temp);
                    if(tempVar != null){
                        /* This block checks to see if the variable being
                         * declared is present in the module's port list, if
                         * not it prints out a (compiler) error
                         */
                        if(  tempVar.getType().equals("DEFAULT_NET_TYPE") &&
                                tempVar.getAttribute().equals("")){
                            module.updatePortVariableInModule(temp, "", "input");
                        }else{
                            System.out.print("ERROR: Module Inputs should be declare in ports list\n");
                        }
                    }
                }
            }
            else if(temp.equals("output")){
                lastAttribute = "output";
                temp = parser.getNextPiece();
                if(temp.equals("reg")){
                    lastType = "reg";
                    temp = parser.getNextPiece();
                    tempVar = module.findVariableInModule(temp);
                    if(tempVar != null){
                        /* This block checks to see if the output reg being
                         * declared is present in the module's port list, if
                         * not it prints out a (compiler) error
                         */
                        if(  tempVar.getType().equals("DEFAULT_NET_TYPE") &&
                                tempVar.getAttribute().equals("")){
                            module.updatePortVariableInModule(temp, "reg", "output");
                        }else{
                            /* This message should print only if the user is
                             * attempting to change a variable that was fully
                             * defined in the port list
                             */
                            System.out.print("ERROR: variable "+temp+" is declared more than once\n");
                        }
                    }else{
                        /* This message should print only if the user is
                         * attempting to add an input or output that is not
                         * already declared in the module's port list
                         */
                        System.out.print("ERROR: Module Outputs should be declared in ports list\n");
                    }
                }
                else if(temp.equals("wire")){
                    lastType = "wire";
                    temp = parser.getNextPiece();
                    tempVar = module.findVariableInModule(temp);
                    if(tempVar != null){
                        /* This block checks to see if the output wire being
                         * declared is present in the module's port list, if
                         * not it prints out a (compiler) error
                         */
                        if(  tempVar.getType().equals("DEFAULT_NET_TYPE") &&
                                tempVar.getAttribute().equals("")){
                            module.updatePortVariableInModule(temp, "wire", "output");
                        }else{
                            /* This message should print only if the user is
                             * attempting to change a variable that was fully
                             * defined in the port list
                             */
                            System.out.print("ERROR: variable "+temp+" is declared more than once\n");
                        }
                    }else{
                        /* This message should print only if the user is
                         * attempting to add an input or output that is not
                         * already declared in the module's port list
                         */
                        System.out.print("ERROR: Module outputs should be declared in ports list\n");
                    }
                }
                else{
                    lastType = "";
                    tempVar = module.findVariableInModule(temp);
                    if(tempVar != null){
                        /* This block checks to see if the variable being
                         * declared is present in the module's port list, if
                         * not it prints out a (compiler) error
                         */
                        if(  tempVar.getType().equals("DEFAULT_NET_TYPE") &&
                                tempVar.getAttribute().equals("")){
                            module.updatePortVariableInModule(temp, "DEFAULT_NET_TYPE", "output");
                        }else{
                            System.out.print("ERROR: Module Inputs should be declare in ports list\n");
                        }
                    }
                }
            }
            else if(temp.equals("parameter")){}
            else if(temp.equals("reg")){
                lastType = "reg";
                lastAttribute = "";
                temp = parser.getNextPiece();
                if(module.findVariableInModule(temp) == null)
                    module.addVariableToModule(new Reg(temp,""));
                else
                    System.out.println("ERROR: variable "+temp+" is declared more than once\n");
            }
            else if(temp.equals("wire")){
                lastType = "wire";
                lastAttribute = "";
                temp = parser.getNextPiece();
                if(module.findVariableInModule(temp) == null)
                    module.addVariableToModule(new Wire(temp,""));
                else
                    System.out.println("ERROR: variable "+temp+" is declared more than once\n");
            }
            else if(temp.equals("assign")){}
            else if(temp.equals("module")){}
            else{
                /* If the string temp is a keyword then once it is back in the
                 * it is checked to see if it is part of a block, and if so
                 * then it is passed back to the parser to be redispatched
                 * to a parser of another block type
                 */
                if(parser.pieceIsKeyword(temp))
                    parser.checkForNewBlock(module, temp);
                else if(!temp.equals(";")){
                    tempVar = module.findVariableInModulePortList(temp);
                    if(tempVar != null){
                        if(  tempVar.getType().equals("DEFAULT_NET_TYPE") &&
                                tempVar.getAttribute().equals("")){
                            module.updatePortVariableInModule(temp, lastType, lastAttribute);
                        }else{
                            System.out.print("ERROR: Module Input already declared in ports list\n");
                        }
                    }else if(module.findVariableInModule(temp) == null){
                        if(lastType.equals("reg")){
                            module.addVariableToModule(new Reg(temp,""));
                        }else if(lastType.equals("wire")){
                            module.addVariableToModule(new Wire(temp,""));
                        }
                    }else{
                        System.out.println("ERROR: variable "+temp+" is declared more than once\n");
                    }
                }
            }
        }while( !temp.equals("endmodule"));
        return temp;
    }
    private static Module parseModuleHead(Module current, Parser parser){
        Module module = current;
        String piece = parser.getNextPiece();
        String lastType="DEFAULT_NET_TYPE";
        String lastAttribute = "";
        do{
           piece = parser.getNextPiece();
           if(piece.equals("input")){
               lastAttribute = "input";
               lastType = "DEFAULT_NET_TYPE";
               piece = parser.getNextPiece();
               if(piece.equals("reg")){
                   lastType = "ERROR";
                   piece = parser.getNextPiece();
                   module.addVariableToModulePortList(new Reg(piece,"input"));
               }else if(piece.equals("wire")){
                   lastType = "wire";
                   piece = parser.getNextPiece();
                   module.addVariableToModulePortList(new Wire(piece,"input"));
               }
               else{
                   module.addVariableToModulePortList(new Variable(piece,lastType,"input"));
               }
           }
           else if(piece.equals("output")){
               lastAttribute = "output";
               lastType = "DEFAULT_NET_TYPE";
               piece = parser.getNextPiece();
               if(piece.equals("reg")){
                   lastType = "reg";
                   piece = parser.getNextPiece();
                   module.addVariableToModulePortList(new Reg(piece,"output"));
               }else if(piece.equals("wire")){
                   lastType = "wire";
                   piece = parser.getNextPiece();
                   module.addVariableToModulePortList(new Wire(piece,"output"));
               }
               else {
                   module.addVariableToModulePortList(new Variable(piece,lastType,"input"));
               }
           }
           else if(piece.equals("reg")){
               lastType = "ERROR";
               piece = parser.getNextPiece();
               module.addVariableToModulePortList(new Variable(piece,lastType,lastAttribute));
           }
           else if(piece.equals("wire")){
               lastType = "wire";
               piece = parser.getNextPiece();
               module.addVariableToModulePortList(new Wire(piece,lastAttribute));
           }
           else if(!piece.equals(")")){
               module.addVariableToModulePortList(new Variable(piece,lastType,lastAttribute));
           }
        }while(!piece.equals(")"));
        piece = parser.getNextPiece(); //used to remove the final semicolon from the parse string
        return module;
    }

    @Override
    public String toString(){
        int i=0;

        //This section prints out the module Header
        String out = "module "+BlockName+" (\n";
        if(!portVariables.isEmpty()){
            for(i=0; !(portVariables.get(i).getAttribute().equals("input")
                    || portVariables.get(i).getAttribute().equals("output"))
                    && i<portVariables.size(); i++);
            out += portVariables.get(i).toString();
            for(i=i+1; i<portVariables.size(); i++){
                if(portVariables.get(i).getAttribute().equals("input")
                        || portVariables.get(i).getAttribute().equals("output")){
                    out += "\t,\n";
                    out += portVariables.get(i).toString();
                }
            }
        }
        out+="\n);\n";

        //This Section Prints out the Module Body
        for(i=0; i<parameters.size(); i++)
            out += parameters.get(i).toString();
        if(!vars.isEmpty()){
//            for(i=0; (vars.get(i).getAttribute().equals("input")
//                    || vars.get(i).getAttribute().equals("output"))
//                    && i<vars.size(); i++);
//            out += vars.get(i).toString();
            for(i=0; i<vars.size(); i++){
                if(!vars.get(i).getAttribute().equals("input")
                        && !vars.get(i).getAttribute().equals("output")){
                    out += vars.get(i).toString();
                    out += ";\n";
                }
            }
        }
        for(i=0; i< subBlocks.size(); i++){
            out += subBlocks.get(i).toString();
        }
        out+="\nendmodule\n";
        return out;
    }
}
