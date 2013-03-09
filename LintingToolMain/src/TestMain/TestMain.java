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
package TestMain;

//general import and export that are commonly used.
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;


//used for the gui components
import javax.swing.*;
import java.awt.*;

    



public class TestMain {
    public static void main(String[] args) {
        TestMenu lt = new TestMenu(); 
        }
}
class TestMenu
{
        Scanner sc;
        
        TestMenu()
        {
            sc = new Scanner(System.in);

            Parser parserTest;
            ArrayList<String> stringArray;// = new ArrayList();


            FileRead fileReaderTest = new FileRead();
            fileReaderTest.newFile();
//            stringArray.add( fileReaderTest.toString() );
            stringArray = fileReaderTest.getCode();
            parserTest = new Parser(stringArray);

            ErrorSearcher error = new ErrorSearcher();
            error.identifyMultiplyDrivenSignals(parserTest);

//            start();
        }
        private void start()
        {
            for(int select = 0;select > -1;)
            {
                System.out.println("-1\t quit\n"
                        + "1\tRun Class Tester");
                select = sc.nextInt();
                //System.out.println(select);
                if(select == 1)
                {
                    ClassTestMenu();
                }
            }
        }
        private void ClassTestMenu()
        {
            TestVariable V = new TestVariable();
            TestError E = new TestError();
            TestFileReader fr = new TestFileReader();
            TestCode C;
            for(int i = 0; i > -1;)
            {
                System.out.println("-1\t quit \n"
                        + "1\tFile Reader Tester\n"
                        + "2\tError Class Tester\n"
                        + "3\tVariable Class Tester\n"
                        + "4\tCode Class Tester");
                i = sc.nextInt();
                switch(i)
                {
                    case 1:
                        fr.start();
                        break;
                    case 2:
                        E.start();
                        break;
                    case 3:
                        V.start();
                        break;
                    case 4:
                        C = new TestCode(V.Default(),E.Default());
                        C.start();
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                        break;
                }
            }
            
        }
}

    
   