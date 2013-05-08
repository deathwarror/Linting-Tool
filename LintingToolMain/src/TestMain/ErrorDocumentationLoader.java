/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class ErrorDocumentationLoader {

    //contains the base url leading to the database directory
    private String baseURL = "https://raw.github.com/deathwarror/Linting-Tool/"
            + "master/Database/v/Documentation/";
    private String Format;
    private BufferedReader in;
    private InputStreamReader inStream;
    URL DocumentationURL;
    //trys running the input streams
    ErrorDocumentationLoader(String burl,String format)
    {
        baseURL = burl;
        Format = format;
        try{
            DocumentationURL = new URL(baseURL);
            inStream = new InputStreamReader(DocumentationURL.openStream());
            in = new BufferedReader(inStream);
        }
        catch(Exception e)
        {
            System.out.println("Documentation Url Streams failed to open.");
        }
    }
    public String getURL(String Number)
    {
        return baseURL+Number+Format;
    }
    //trys reading in the database
    public String load(String Number)
    {
        String ed = "";
        String ReadLine;
        try{
            DocumentationURL = new URL(baseURL+Number+Format);
            //open the error file
            inStream = new InputStreamReader(DocumentationURL.openStream());
            in = new BufferedReader(inStream);
        }
        catch(Exception E)
        {
            return "Could not get the Documentation for error "
                    + Number + ":\nThe page failed to load\n"
                    + "\tThis could be caused by no internet connection or \n"
                    + "\tthe webpage that holds the information is down.\n"
                    + "Documentation Web Address: \n\t"+baseURL+Number+Format+"\n";
        }
        try{
            ReadLine = in.readLine();
            while(ReadLine!= null)
            {
                ed +=ReadLine+"\n";
                ReadLine = in.readLine();
            }

        }
        catch(Exception E)
        {
            return "Something Went wrong while reading the file of error "
                    + Number +":\n The reading of the file was unsuccessful.\n"
                    + "\tThis could be caused by an unstable internet connection.\n"
                    + "Documentation Web Address: \n\t"+baseURL+Number+Format+"\n";
        }
        return ed;
    }
}
