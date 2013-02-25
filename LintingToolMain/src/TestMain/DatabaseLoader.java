package TestMain;

/*
 * @author: Kenenth Hassey
 * @date: 1/23/2013
 * @Version: 1.000;
 * Function: Reads in the database from the git hub url
 * Status: Tested Will need to be tested again
 */
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DatabaseLoader {
    //contains the base url leading to the database directory
    private String baseURL = "https://raw.github.com/deathwarror/Linting-Tool/"
            + "master/Database/";
    //contains the name of the files in url format
    private String FileTypeURLExt ="File%20Types.txt";
    private String DefFileNameExt = "Error%20List.txt";
    private BufferedReader in;
    private InputStreamReader inStream;
    URL databaseURL;
    //trys running the input streams
    DatabaseLoader()
    {
        try{
        databaseURL = new URL(baseURL);
        inStream = new InputStreamReader(databaseURL.openStream());
        in = new BufferedReader(inStream);
        }
        catch(Exception e)
        {
            System.out.println("Database Url Streams failed to open.");
        }
    }
    //trys reading in the database
    public ArrayList<ErrorDatabase> load()
    {
        ArrayList<String> FileTypes = new ArrayList();
        FileTypes = getFileTypes();
        ArrayList<ErrorDatabase> ed = new ArrayList();

        for(int i = 0; i <FileTypes.size();i++)
        {
            ed.add(getErrors(FileTypes.get(i)));
        }
        for(int i = 0; i< ed.size();i++)
        {
            ed.get(i).toString();
        }
        return ed;
    }

    //gets the list of file types that the database contains
    private ArrayList<String> getFileTypes()
    {
        String fileLine = "";
        ArrayList<String> FileTypes = new ArrayList();

        try{
            URL FileTypeURL = new URL((baseURL+FileTypeURLExt));
            inStream = new InputStreamReader(FileTypeURL.openStream());
            in = new BufferedReader(inStream);
        }
        catch(Exception e)
        {
            System.out.println("Failed to open database file type list");
        }
        try{
            fileLine = in.readLine();
            while ((fileLine !=null))
            {
                //ignore lines with the ignore characters in them
                if(!(fileLine.contains("//")))
                {
                    FileTypes.add(fileLine);
                }
                fileLine = in.readLine();
            }
        }
        catch(Exception E)
        {
            System.out.println("Failed to read in the file types from "
                    + "the database");
        }
        return FileTypes;
    }
    private ErrorDatabase getErrors(String type)
    {
        ErrorDatabase ed = new ErrorDatabase(type);
        ArrayList<String> ErrorList = getErrorList(type);
        Error e;

        for(int i = 0;i < ErrorList.size();i++)
        {
            e = new Error();
            String s = "";
            //tries to open an error file
            try{
                databaseURL = new URL(baseURL+type+"/definitions/"+
                        ErrorList.get(i)+".txt");
                //open the error file
                inStream = new InputStreamReader(databaseURL.openStream());
                in = new BufferedReader(inStream);
            }
            catch(Exception E)
            {
                System.out.println("Could not open error file:"+ErrorList.get(i));
            }


            //Tries reading the error file
            try{
                s = in.readLine();

                ArrayList<String> ErrorDef = new ArrayList();
                int errorMSGSelect= 0;
                String ErrorMSG="";
                while(s != null)
                {
                    if(errorMSGSelect == 0)
                    {
                       if(s.contains("ERRMSG"))
                       {
                           errorMSGSelect = 1;
                       }
                       else
                       {
                           ErrorDef.add(s);
                       }

                    }
                    else
                    {
                        ErrorMSG = ErrorMSG+" "+s;
                    }
                    s = in.readLine();
                }
                e.setErrorNum(ErrorList.get(i));
                e.setErrorDef(ErrorDef);
                e.setErrorMsg(ErrorMSG);
            }
            catch(Exception E)
            {
                System.out.println("Something went wrong in reading the file.");
            }
            ed.addError(e);
        }
        return ed;

    }
    //responsible for loading each error file
    private ArrayList<String> getErrorList(String type)
    {
        String s = "";
        ArrayList<String> el = new ArrayList();
        try{
            databaseURL = new URL(baseURL+type+"/definitions/"+
                    DefFileNameExt);
            //open the error file
            inStream = new InputStreamReader(databaseURL.openStream());
            in = new BufferedReader(inStream);
        }
        catch(Exception E)
        {
            System.out.println("Could not open error file:"+type);
        }
        try{
            s = in.readLine();
            while(s!= null)
            {
                if(!(s.contains("//")))
                {
                    el.add(s);
                }
                s = in.readLine();
            }

        }
        catch(Exception E)
        {
            System.out.println("Failure Reading in the file: "+type+" Error List");
        }
        return el;
    }
}
