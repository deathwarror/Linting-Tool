package TestMain;

/**
 * @Author: Kenneth Hassey
 * @Date: 1/7/2013
 * @Version 1.000
 * Function:
 *      This class is designed to store all the variables that come across in
 *      parsing of the code.  This file stores Variable's name, variableType, and any
 *      edge sensitivities.
 * 
 *      Status: Untested.
 */
import java.util.ArrayList;


public class Variable {
    protected String variableType; //i.e. reg, wire, parameter, localparam
    protected String variableAttribute; //i.e. Operational modes: input, output,
    protected String variableSign; //i.e. Signed, Unsigned, or Unspecified
    protected String vectorParentName;
    protected String name;
    protected ArrayList<String> EdgeSens;
    protected String edgeSensitivity;
    protected int LineNumber;
    protected int arraySize;
    
    //Default Constructor
    Variable()
    {
        name = "";
        variableType = "";
        variableAttribute = "";
        EdgeSens = new ArrayList();
        vectorParentName = "";
        variableSign = "";
        arraySize = 0;
        LineNumber = Parser.currentLineNumber;
    }
    
    //This constructor will most likely be used in conjuntion with the addEdge
    //function because the variable variableType may edge may not be registered at first
    Variable(String name_in, String type_in)
    {
        name = name_in;
        variableType = type_in;
        variableAttribute = "";
        vectorParentName = "";
        variableSign = "";
        arraySize = 0;
        LineNumber = Parser.currentLineNumber;
    }
    
    Variable(String name_in, String type_in, String attributeIn)
    {
        name = name_in;
        variableType = type_in;
        variableAttribute = attributeIn;
        vectorParentName = "";
        variableSign = "";
        arraySize = 0;
        LineNumber = Parser.currentLineNumber;
    }

    Variable(String name_in, String type_in, String attributeIn, String sign_in, int arraySizeIn)
    {
        name = name_in;
        variableType = type_in;
        variableAttribute = attributeIn;
        vectorParentName = "";
        variableSign = sign_in;
        arraySize = arraySizeIn;
        LineNumber = Parser.currentLineNumber;
    }

    //This constructor can be used to allow all three data slots to be filled.
    //Recommended defualt use of 
    //      Variable(String name_in, String type_in)
    //to keep duplicate edges from showing up.
    Variable(String name_in,String type_in, ArrayList<String> Edge)
    {
        name = name_in;
        variableType = type_in;
        EdgeSens = Edge;
        vectorParentName = "";
        variableSign = "";
        arraySize = 0;
        LineNumber = Parser.currentLineNumber;
    }
    
    //returns the Name of the Variable
    public String getName()
    {
        return name;
    }
    
    public String getVectorName()
    {
        return vectorParentName;
    }
    
    
    //returns the Type of variable
    public String getType()
    {
        return variableType;
    }

    public String getAttribute()
    {
        return variableAttribute;
    }

    //returns the Edge Sensitivity
    public ArrayList<String> getEdge()
    {
        return EdgeSens;
    }
    
    
    //Allows the name to be changed.
    public void setName(String Name_in)
    {
        name = Name_in;
    }
    
    //Allows the variableType to be changed
    public void setType(String Type_in)
    {
        variableType = Type_in;
    }
    
    //Allows a new list of edge sensitivity to be placed
    public void setEdge(ArrayList<String> Edge)
    {
        EdgeSens = Edge;
    }
    
    //For adding an edge sensitivity
    public boolean addEdge(String edge)
    {
        for(int i = 0;(i<EdgeSens.size());i++)
        {
            //get integer value of relation between two strings
            int compare = EdgeSens.get(i).compareTo(edge);
            //Look for the edge sensitivity is already in the list
            if(compare==0)
            {
                //return false for same
                return false;
            }
            //if it goes in the index of the current edge
            else if(compare > 0)
            {
                EdgeSens.add(i,edge);
                return true;
            }
            else
            {
                EdgeSens.add(edge);
                return true;
            }
           
        }
        //catch false
        return false;    
    }
    
    //compares two variable objects to see if they are the same
    public boolean compareToAll(Variable V)
    {
        //see if the name matches
        if(name.equals(V.getName()))
        {
            //see if types are the same
            if(variableType.equals(V.getType()))
            {
                int i;
                //if the edge sensitivity arent the same Length
                if(EdgeSens.size()!= V.getEdge().size())
                {
                    return false;
                }
                
                //checks each element of edge sensitivity
                for(i = 0;i<EdgeSens.size();i++)
                {
                    if(!(EdgeSens.get(i).equals(V.getEdge().get(i))))
                    {
                        return false;
                    }
                }
                //if they are the same
                if(i== EdgeSens.size())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int compareVariable(Variable b){
        if(this.variableAttribute.equals(b.getAttribute())){
            if(this.variableType.equals(b.getType())){
                return this.name.compareTo(b.getName());
            }else{
                return this.variableType.compareTo(b.getType());
            }
        }else{
            return this.variableAttribute.compareTo(b.getAttribute());
        }
    }

    //compares two variable's name and variableType for a lower level compare
    public boolean compareTo(Variable V)
    {
        if(name.equals(V.getName()))
        {
            if(variableType.equals(V.getType()))
            {
                return true;
            }
        }
        return false;
    }

     public String getEdgeSensitivity(){
         return edgeSensitivity;
     }
     public boolean setEdgeSensitivity(String sensitive){
         if(!edgeSensitivity.equals("")){
             return false;
         }else if(sensitive.equals("posedge") || sensitive.equals("negedge")){
             edgeSensitivity = sensitive;
             return true;
         }
         return false;
     }
     
     //the "params" array list is assumed to come from the module that is currently being worked in
     public static ArrayList createVariablesFromVector(Variable template, String vecStart, String vecEnd){
         ArrayList<Variable> vectorVars = new ArrayList();

         String temp; //parser "freshPiece" string value should currently be set to a "["
         
         String number;
         int MSB=0;
         int LSB=0;
         number = Parser.parseNumberFromExpression(vecStart);
         if(Parser.isANumber(number)==1){
             MSB = Integer.parseInt(number);
         }else{
             MSB = 0;
         }
         number = Parser.parseNumberFromExpression(vecEnd);
         if(Parser.isANumber(number)==1){
            LSB = Integer.parseInt(number);
         }else{
             LSB = 0;
         }
         if(MSB < LSB){
             int num = MSB; MSB = LSB; LSB = num;
         }
         
         for(int i=0; MSB-i >= LSB; i++){
             if(template.getClass() == Reg.class){
                 vectorVars.add(new Reg(template.name+"_"+(LSB+i), template.variableAttribute, template.name, template.variableSign, template.arraySize));
             }else if(template.getClass() == Wire.class){
                 vectorVars.add(new Wire(template.name+"_"+(LSB+i), template.variableAttribute, template.name, template.variableSign, template.arraySize));
             }
         }
         
         return vectorVars;
     }

     public static int parseVariableArraySize(String arrayStart, String arrayEnd){
         String number;
         int MSB=0;
         int LSB=0;
         number = Parser.parseNumberFromExpression(arrayStart);
         if(Parser.isANumber(number)==1){
             MSB = Integer.parseInt(number);
         }else{
             return -1;
         }
         number = Parser.parseNumberFromExpression(arrayEnd);
         if(Parser.isANumber(number)==1){
            LSB = Integer.parseInt(number);
         }else{
             return -1;
         }
         if(MSB==0 && LSB==0)
         if(MSB < LSB){
             int num = MSB; MSB = LSB; LSB = num;
         }
         return MSB+1-LSB;
     }
     
    //Convert all data in the object to be outputted.
    @Override
    public String toString()
    {
//        System.out.println("Name: "+name + " \t\tType: "+variableType+"\nEdges");
//        for(int i = 0; i <EdgeSens.size();i++)
//        {
//            System.out.println("\t"+EdgeSens.get(i));
//        }
        return variableAttribute + " " + variableType + " " + name + " Array Size: " + arraySize + " LINE: " + LineNumber;
    }
}
