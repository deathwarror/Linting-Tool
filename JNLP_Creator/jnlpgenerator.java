/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Deathwarror
 */
public class jnlpgenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String BaseURL = "https://linting-tool.googlecode.com/git/Compiled/";
        int type = 1;
        
        File WebStart = new File("LintingTool_WebStart.jnlp");
        if(!WebStart.exists())
        {
            try{
                WebStart.createNewFile();
            }
            catch(Exception e)
            {
                System.out.println("File Creation Failed");
            }
        }
        else
        {
            WebStart.delete();
            try{
                WebStart.createNewFile();
            }
            catch(Exception e)
            {
                System.out.println("File Creation Failed");
            }
        }

        try{
            FileOutputStream JWSFile = new FileOutputStream(WebStart, false);
            FileInputStream Template = new FileInputStream("LintingTool_Template.jnlp");
            DataInputStream in = new DataInputStream(Template);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            String s = br.readLine();
            while (s != null)
            {
                String VersionNumber = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                String JavaVersion = System.getProperty("java.version");
                s = s.replace("#$Version_Here", VersionNumber);
                if(type == 1)
                {
                    s = s.replace("#$JAVA_VERSION_HERE", JavaVersion+"+");
                }
                else
                {
                    s = s.replace("#$JAVA_VERSION_HERE", JavaVersion.substring(0,JavaVersion.indexOf("_"))+"+");
                }
                s = s.replace("#$CodeBASE_URL_HERE", BaseURL)+"\n";
                JWSFile.write(s.getBytes());
                JWSFile.write(System.getProperty("line.separator").getBytes());
                s = br.readLine();
            }
            JWSFile.flush();
            JWSFile.close();
        }
        catch(Exception E)
        {
            System.out.println("Failed to open the Template file");
        }
    }
}
