/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

/**
 *
 * @author Hell-o
 */
public class Wire extends Variable{
    Wire(String name_in)
    {
        name = name_in;
        variableType = "wire";
        variableAttribute = "";
    }
    Wire(String name_in, String attributeIn)
    {
        name = name_in;
        variableType = "wire";
        variableAttribute = attributeIn;
    }
    @Override
    public String toString(){
        return variableAttribute + " " + variableType + " " + name;
    }

}
