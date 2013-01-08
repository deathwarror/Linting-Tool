package lintingtoolmain;

/** @Author: Kenneth Hassey
 *  @Date: 1/7/2013
 *  @Version: 1.000
 *  Function:
 *      Contains all the code from a parsed file and is used to store in coming
 *      blocks of code for multiple depth.  This function is responsible for
 *      all original information and parsed information of the file.
 * 
 *  Status: Untested/Incomplete
 * 
 */

import java.util.ArrayList;
public class Code extends FileStore{
    private String original;
    private String parsed;
    
    private ArrayList<Error> error;
    private String SpecificType;
    private ArrayList<Code> code;
    private ArrayList<Variable> variables;
    
    
    Code()
    {
        original = parsed = SpecificType = null;
        error = null;
        code = null;
        variables = null;
    }
    Code(String Orig,String Parse, String type)
    {
        original = Orig;
        parsed = Parse;
        SpecificType = type;
    }
    
    //if Parsing is done and error is Hard Coded, for the error to be added.
    Code(String Orig,String Parse, String type, Error e)
    {
        this(Orig,Parse,type);
        this.add(e);
    }
    private boolean add(Error e)
    {
        for(int i = 0; i<error.size(); i++)
        {
            if(error.get(i).compareTo(e))
            {
                return false;
            }
        }
        error.add(e);
        return true;
    }
}
