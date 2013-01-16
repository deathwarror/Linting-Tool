package TestMain;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deathwarror
 */
 //used to fully test and run the file reader class
class TestFileReader{
        private FileRead fr;
        private Scanner sc;
        private ArrayList<FileStore> files;
        
        
        
        TestFileReader()
        {
            
            sc = new Scanner(System.in);
            fr = new FileRead();
        }
    public void start()
    {
        ArrayList<String> code = new ArrayList();
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
                    System.out.println(fr.getFileName());
                    break;
                case 3:
                    code = fr.getCode();
                    for (int j = 0;j<code.size();j++)
                    {
                        System.out.println(code.get(j));
                    }
                    break;
                case 4:
                    fr.toString();
                    break;
                default: break;
            } 
        }
    }
}
    
    


