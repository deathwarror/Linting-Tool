/**
 * @Author: Kenneth Hassey
 * @Date: 1/7/2013
 * @Version: 1.000
 *  Function:
 *      This File is responsible for the container of what is in the file.  This
 *      class is responsible for keeping data about the file and holds the 
 *      parsed and original code.  It is also responsible for output of parsed
 *      code and errors for each line of code.
 *  Status: Untested/incomplete.
 */
package lintingtoolmain;
import java.util.ArrayList;



public class FileStore {
    private ArrayList<Code> codeLines;
    private ArrayList<Variable> variables;
}
