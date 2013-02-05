package TestMain;

import java.util.Scanner;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deathwarror
 */
public class TestError {
    
    private Error e,e2;
    private Scanner sc;
    
    
    TestError()
    {
        sc = new Scanner(System.in);
        e = new Error();
    }
    public Error Default()
    {
        String en = "10";
        String em = "Error Msg";
        ArrayList<String> ed = new ArrayList();
        ed.add("Module");
        ed.add("endModule");
        return new Error(en,em,ed);
    }
    public void start()
    {
        System.out.println("Error Tester:\n");
        for(int i = 0;i>-1;)
        {
        System.out.println("-1\t quit\n"
                + "1\tBuild Error\n"
                + "2\tSet Error Number\n"
                + "3\tSet Error Message\n"
                + "4\tDuplicate Error\n"
                + "5\tCompare Errors\n"
                + "6\tPrint Primary Stored Error\n");
        i = sc.nextInt();
        switch(i){
            case 1:
                buildError();
                break;
            case 2:
                setEN();
                break;
            case 3:
                setEM();
                break;
            case 4:
                dupE();
                break;
            case 5:
                compareE();
                break;
            case 6:
                PrintError();
                break;
            default:
                break;
        }
        }
    }
    public void buildError()
    {
        String en="";
        String em="";
        String read="";
        ArrayList<String> ed = new ArrayList();
        System.out.println("Enter Error Number");
        while(en.equals(""))
        {
            en = sc.nextLine();
        }
        
        System.out.println("Enter Error Message");
        while(em.equals(""))
        {
            em = sc.nextLine();
        }
        for(int i = 0;i>-1; i = sc.nextInt())
        {
            read = "";
            System.out.println("Enter definition line");
            while(read.equals(""))
            {
                read = sc.nextLine();
            }
            ed.add(read);
            System.out.println("-1\t quit\n"
                    + "1\tContinue\n");
        }
        e = new Error(en,em,ed);
    }
    private void setEN()
    {
        System.out.println("Enter Error Number");
        String Read="";
        while(Read.equals(""))
        {
            Read = sc.nextLine();
        }
        e.setErrorNum(Read);
        System.out.println("Error Number Set to "+e.getErrorNum());
    }
    private void setEM()
    {
        
        System.out.println("Enter Error Message");
        String Read="";
        while(Read.equals(""))
        {
            Read = sc.nextLine();
        }
        e.setErrorMsg(Read);
        System.out.println("Error Message Set to \n\t"+e.getErrorMsg());
    }
    private void dupE()
    {
        e2 = new Error(e.getErrorNum(),e.getErrorMsg(),e.getErrorDef());
        System.out.println("Error Duplicated\n\n");
        System.out.println("New Error:\n");
        e2.toString();
    }
    public void compareE()
    {
        System.out.println("Error Compare = "+e.compareTo(e2));
    }
    public void PrintError()
    {
        e.toString();
    }
    
    public Error getError()
    {
        return e;
    }
}
