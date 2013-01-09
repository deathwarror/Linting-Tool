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
 *      Status: Untested.
 */
import java.util.ArrayList;


public class Variable {
    private String type;
    private String name;
    private ArrayList<String> EdgeSens;
    
    
    //Default Constructor
    Variable()
    {
        name = "";
        type = "";
        EdgeSens = new ArrayList();
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
            if(type.equals(V.getType()))
            {
                int i;
                //if the edge sensitivity arent the same Length
                if(EdgeSens.size()!=V.getEdge().size())
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
    
    //compares two variable's name and type for a lower level compare
    public boolean compareTo(Variable V)
    {
        if(name.equals(V.getName()));
        {
            if(type.equals(V.getType()))
            {
                return true;
            }
        }
        return false;
    }
}
