/**
 * @Author: Kenneth Hassey
 * @Date:1/7/2013
 * @Version: 1.000
 * Function:
 *      This file will be responsible for calling and running all functions and
 *      to open, parse files, load database, test rules, and check comments. 
 * 
 *      Coders Note: This file will be the one to replace with the GUI Later on.
 *                   .toString() function will need to be re written for all
 *                   class functions.
 */
package lintingtoolmain;

//general import and export that are commonly used.
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;


//used for the gui components
import javax.swing.*;
import java.awt.*;

public class LintingToolMain {
    public static void main(String[] args) {
        LintingTool lt = new LintingTool(); 
        }
}
    



class LintingTool{
        private FileRead fr;
        private Scanner sc;
        private ArrayList<FileStore> files;
        
        
        
        LintingTool()
        {
            
            sc = new Scanner(System.in);
            fr = new FileRead();
            start();
            
        }
        private void start()
        {
            for(int select = 0;select > -1;)
            {
                System.out.println("-1\t quit\n"
                        + "1\tRun File Reader");
                select = sc.nextInt();
                System.out.println(select);
                scannerSelect(select);
            }
        }
    public void scannerSelect(int s)
    {
        //System.out.println("scannerSelect");
        if(s == 1)
        {
            FileReaderCall();
        }
    }
    public void FileReaderCall()
    {
        //System.out.println("FileReaderCall");
        for(int i = 0; i >-1;i = i)
        {
            System.out.println("\n\n-1\tQuit\n"
                    + "1\tRead New File\n"
                    + "2\tGet File Name\n"
                    + "3\tGet File Code\n"
                    + "4\tPrint File Information\n");
            i = sc.nextInt();
            switch(i){
                case 1:
                    fr.newFile();
                    break;
                case 2:
                    fr.getFileName();
                    break;
                case 3:
                    fr.getCode();
                    break;
                case 4:
                    fr.toString();
                    break;
                default: break;
            } 
        }
    }
}
    
    

