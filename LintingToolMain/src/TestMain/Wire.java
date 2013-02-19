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
//    private String edgeSensitivity;
    Wire(String name_in)
    {
        name = name_in;
        variableType = "wire";
        variableAttribute = "";
        edgeSensitivity = "";
    }
    Wire(String name_in, String attributeIn)
    {
        name = name_in;
        variableType = "wire";
        variableAttribute = attributeIn;
        edgeSensitivity = "";
    }
    
//     public String getEdgeSensitivity(){
//         return edgeSensitivity;
//     }
//     public boolean setEdgeSensitivity(String sensitive){
//         if(!edgeSensitivity.equals("")){
//             return false;
//         }else if(sensitive.equals("posedge") || sensitive.equals("negedge")){
//             edgeSensitivity = sensitive;
//             return true;
//         }
//         return false;
//     }

     @Override
    public String toString(){
        return variableAttribute + " " + variableType + " " + name;
    }

}
