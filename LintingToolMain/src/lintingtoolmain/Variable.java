package lintingtoolmain;

/**
 * @Author: Kenneth Hassey
 * @Date: 1/7/2013
 * @Version 1.000
 * Function:
 *      This class is designed to store all the variables that come across in
 *      parsing of the code.  This file stores Variable's name, type, and any
 *      edge sensitivities.
 * 
 *      Add Function Untested.
 */
import java.util.ArrayList;


public class Variable {
    private String type;
    private String name;
    private ArrayList<String> EdgeSens;
    
    
    //Default Constructor
    Variable()
    {
        name = null;
        type = null;
        EdgeSens = null;
    }
    
    //This constructor will most likely be used in conjuntion with the addEdge
    //function because the variable type may edge may not be registered at first
    Variable(String name_in, String type_in)
    {
        name = name_in;
        type = type_in;
    }
    
    //This constructor can be used to allow all three data slots to be filled.
    //Recommended defualt use of 
    //      Variable(String name_in, String type_in)
    //to keep duplicate edges from showing up.
    Variable(String name_in,String type_in, ArrayList<String> Edge)
    {
        name = name_in;
        type = type_in;
        EdgeSens = Edge;
    }
    
    //returns the Name of the Variable
    public String getName()
    {
        return name;
    }
    
    
    //returns the Type of variable
    public String getType()
    {
        return type;
    }
    
    //returns the Edge Sensitivity
    public ArrayList<String> getEdge()
    {
        return EdgeSens;
    }
    
    //Convert all data in the object to be outputted.
    public String toString()
    {
        System.out.println("Name: "+name + " \t\tType: "+type+"\nEdges");
        for(int i = 0; i <EdgeSens.size();i++)
        {
            System.out.println("\t"+EdgeSens.get(i));
        }
        return "Successful";
    }
    
    //Allows the name to be changed.
    public void setName(String Name_in)
    {
        name = Name_in;
    }
    
    //Allows the type to be changed
    public void setType(String Type_in)
    {
        type = Type_in;
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
            //Look for the edge sensitivity is already in the list
            if(EdgeSens.get(i).equals(edge))
            {
                //return false that it was not added to the list
                return false;
            }
        }
        EdgeSens.add(edge);
        //Return that is was added to the list.
        return true;
        
    }
}
