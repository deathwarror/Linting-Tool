/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package TestMain;
import java.util.ArrayList;
/**
 *
 * @author Deathwarror
 */
public class SimpleFileStore {
    private String Name;
    private ArrayList<String> Code;
    private ArrayList<Error> ErrorList;
    SimpleFileStore()
    {
        Name = "";
        Code = new ArrayList();
        ErrorList = new ArrayList();
        
    }
    public void setName(String name)
    {
        Name = name;
    }
    public String getName()
    {
        return Name;
    }
    public void setCode(ArrayList<String> code)
    {
        Code = code;
    }
    public ArrayList<String> getCode()
    {
        return Code;
    }
    public ArrayList<Error> getErrorList()
    {
        return ErrorList;
    }
    public void setErrorList(ArrayList<Error> el)
    {
        ErrorList = el;
    }
    public String toString()
    {
        return Name;
    }
    
}
