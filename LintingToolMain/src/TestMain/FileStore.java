/**
 * @Author: Kenneth Hassey
 * @Date: 1/7/2013
 * @Version: 1.022
 *  Function:
 *      This File is responsible for the container of what is in the file.  This
 *      class is responsible for keeping data about the file and holds the 
 *      parsed and original code.  It is also responsible for output of parsed
 *      code and errors for each line of code.
 *  Status: Tested/Working.
 */
package TestMain;
import java.util.ArrayList;



public class FileStore {
    //holds all the lines of code
    private ArrayList<Code> codeLines;
    //holds list of variables
    private ArrayList<Variable> variables;
    //holds the name of the file
    private String FileName;
    //Holds the type of file
    private String FileType;
    //hold number of lines that have comments
    private int commentCount;
    //holds total lines of code
    private int linesOfCode;
    //contains the list of modules inside the file
    private ArrayList<String> moduleList;
    
    FileStore()
    {
        codeLines = new ArrayList();
        variables = new ArrayList();
        FileName = FileType = "";
        moduleList = new ArrayList();
        linesOfCode = commentCount = 0;
    }
    //used for the filename given from FileRead Function
    FileStore(String FileInfo)
    {
        this();
        FileName = FileInfo.substring(0,FileInfo.indexOf(".")-1);
        FileType = FileInfo.substring(FileInfo.indexOf("."),FileInfo.length());
    }
    //used for handeling of independent Strings for file name and type
    FileStore(String Filename, String Filetype)
    {
        this();
        FileName = Filename;
        FileType = Filetype;
    }
    FileStore(String FileInfo, ArrayList<Code> code, ArrayList<Variable> var)
    {
        this(FileInfo);
        codeLines = code;
        variables = var;
    }
    FileStore(String Filename, String Filetype, ArrayList<Code> code, ArrayList<Variable> var)
    {
        this(Filename,Filetype);
        codeLines = code;
        variables = var;
    }
    
    //returns the file name stored in the file store
    public String getFileName()
    {
        return FileName;
    }
    //returns the file type stored in the file store
    public String getFileType()
    {
        return FileType;
    }
    //returns the number of comments in the code
    public int getCommentCount()
    {
        return commentCount;
    }
    //returns the number of lines that contain code
    public int getLinesOfCode()
    {
        return linesOfCode;
    }
    //returns the code contained in the file
    public ArrayList<Code> getCode()
    {
        return codeLines;
    }
    //returns the variables contained in the file
    public ArrayList<Variable> getVariables()
    {
        return variables;
    }
    //returns the list of modules in the file
    public ArrayList<String> getModuleList()
    {
        return moduleList;
    }
    //returns the number of comments
    public int getCommentDensity()
    {
        //if there were any lines that contained comments
        if(commentCount!=0)
        {
            return linesOfCode/commentCount;
        }
        //else return no comments detected
        else
        {
            return 0;
        }
    }
    
    //Print out all the information about the structure
    public String toString()
    {
        System.out.println(FileName + "."+FileType +":\n\nVariables:");
        for(int i =0; i <variables.size();i++)
        {
            variables.get(i).toString();
        }
        System.out.println("\n\nCode:");
        for(int i = 0; i<codeLines.size();i++)
        {
            codeLines.get(i).toString();
        }
        System.out.println("\n\nModules contained Within:");
        for(int i = 0; i<moduleList.size();i++)
        {
            System.out.println(moduleList.get(i));
        }
        return "Successful";
    }
    //allows setting of the file name
    public void setFileName(String Fn)
    {
        FileName = Fn;
    }
    //allows setting of the file type
    public void setFileType(String Ft)
    {
        FileType = Ft;
    }
    //allows setting of the comment count
    public void setCommentCount(int cc)
    {
        commentCount = cc;
    }
    //allows setting for the number of line that have code
    public void setLinesOfCode(int loc)
    {
        linesOfCode = loc;
    }
    //allows the setting of the code
    public void setCode(ArrayList<Code> c)
    {
        codeLines = c;
    }
    //set variables list that are in the file
    public void setVariable(ArrayList<Variable> v)
    {
        variables = v;
    }
    //set the modules that are contained in the file
    public void setModuleList(ArrayList<String> ml)
    {
        moduleList = ml;
    }
    
    //allows adding of a variable list to the file header
    public boolean addVariable(Variable V)
    {
        int i;
        for(i = 0; i < variables.size();i++)
        {
            if (variables.get(i).compareTo(V))
            {
                return false;
            }
        }
        variables.add(V);
        return true;
    }
    //allows adding of the module list
    private boolean addModule(String ModName)
    {
        int i;
        for(i = 0; i< moduleList.size();i++)
        {
            if(ModName.equals(moduleList.get(i)))
            {
                return false;
            }
        }
        if(i == moduleList.size())
        {
            moduleList.add(ModName);
            return true;
        }
        return false;
    }
    public FileStore copy()
    {
        FileStore fs = new FileStore(FileName,FileType);
        ArrayList<Variable> vars = new ArrayList();
        ArrayList<String> mods = new ArrayList();
        ArrayList<Code> code = new ArrayList();
        //duplicates the code list
        for(int i =0; i<codeLines.size();i++)
        {
            code.add(codeLines.get(i).copy());
        }
        //duplicates the variable list
        for(int i =0; i<variables.size();i++)
        {
            vars.add(variables.get(i).copy());
        }
        
        //duplicates the module list
        for(int i =0; i<moduleList.size();i++)
        {
            mods.add(moduleList.get(i));
        }
        //sets all non set values in new object
        fs.setLinesOfCode(linesOfCode);
        fs.setCommentCount(commentCount);
        fs.setCode(code);
        fs.setVariable(vars);
        fs.setModuleList(mods);
        
        return fs;
    }
}
