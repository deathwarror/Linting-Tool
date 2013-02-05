package TestMain;

/**
 * @author Kenneth Hassey
 * @date: 1/15/2013
 * @version: 1.000
 *
 * 
 * Primary Use of this file will be the selection of a specific parser based on
 * the file type that is sent to the parser.  This file will return a FileStore
 * Object with everything set inside  This will also build the error database
 * for data patterns based on file types;
 */
import java.util.ArrayList;

public class ParserMain {
    private FileStore fs;
    private String FileType;
    private ArrayList<ErrorDatabase> db;
    ParserMain()
    {
        fs = new FileStore();
        FileType = "";
        db = new ArrayList();
        //loadDatabases();
    }
    public void parse(String FileType,ArrayList<String> UnparsedCode)
    {
        //this selects the correct parser for the file type
        int i2 = 0;;
        for(int i = 0; i<db.size();i++)
        {
            if(db.get(i).getFileType().equals("v"))
            {
                i2 = i;
            }
        }
        if(FileType.equals("v"))
        {
            vParser vP = new vParser(FileType,UnparsedCode);
           
            if(i2!=0)
            {
                vP.setErrorDatabase(db.get(i2));    
            }
            vP.start();
            fs = vP.getFileStore();
        }
    }
    public FileStore getParsedFile()
    {
        return fs;
    }
    
}
