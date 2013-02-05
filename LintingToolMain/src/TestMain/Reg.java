/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

/**
 *
 * @author Hell-o
 */
public class Reg extends Variable{
    Reg(String name_in)
    {
        name = name_in;
        variableType = "reg";
        variableAttribute = "";
    }
     Reg(String name_in, String attributeIn)
    {
        name = name_in;
        variableType = "reg";
        variableAttribute = attributeIn;
    }
   @Override
    public String toString(){
        return variableAttribute + " " + variableType + " " + name;
    }
}
