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
public class TestCode {
    private Code c;
    private Error e;
    private Variable v;
    private Scanner sc;
    
    
    TestCode()
    {
        sc = new Scanner(System.in);
        e = new Error();
    }
    TestCode(Variable V,Error E)
    {
        sc = new Scanner(System.in);
        e = E;
        v = V;
        c = new Code();
    }
    TestCode(Error E, Variable V)
    {
        this(V,E);
    }
    public void start()
    {
        System.out.println("Code Tester:\n");
        for(int i = 0;i>-1;)
        {
        System.out.println("-1\t quit\n"
                + "1\tBuild Code\n"
                + "2\tSet Code Original\n"
                + "3\tSet Code Parsed\n"
                + "4\tSet Line Number\n"
                + "5\tSet Specific Type\n"
                + "6\tSet Error in List\n"
                + "7\tAdd Variable\n"
                + "8\tAdd Error\n"
                + "7\tPrint Code");
        i = sc.nextInt();
        switch(i){
            case 1:
                buildCode();
                break;
            case 2:
                setCodeOriginal();
                break;
            case 3:
                setCodeParsed();
                break;
            case 4:
                setLineNumber();
                break;
            case 5:
                setSpecType();
                break;
            case 6:
                setErrorList();
                break;
            case 7:
                addVar();
                break;
            case 8:
                addError();
                break;
            case 9:
                printCode();
        }
        }
    }
    public void buildCode()
    {
        String or="";
        String pa="";
        String read="";
        ArrayList<String> ed = new ArrayList();
        System.out.println("Enter Original Code");
        while(or.equals(""))
        {
            or = sc.nextLine();
        }
        
        System.out.println("Enter Parsed Code");
        while(pa.equals(""))
        {
            pa = sc.nextLine();
        }
        read = "";
        System.out.println("Enter Specific Type");
        while(read.equals(""))
        {
            read = sc.nextLine();
        }
        c = new Code(or,pa,read);
    }
    private void setCodeOriginal()
    {
        System.out.println("Enter Original Code");
        String Read="";
        while(Read.equals(""))
        {
            Read = sc.nextLine();
        }
        c.setOriginal(Read);
        System.out.println("Error original code Set to "+c.getOriginal());
    }
    private void setCodeParsed()
    {
        
        System.out.println("Enter Parsed Code");
        String Read="";
        while(Read.equals(""))
        {
            Read = sc.nextLine();
        }
        c.setParsed(Read);
        System.out.println("Parsed Code Set to \n\t"+c.getParsed());
    }
    private void setLineNumber()
    {
        System.out.println("Enter a Line Number");
        c.setLineNumber(sc.nextInt());
    }
    private void setSpecType()
    {
         String read = "";
         System.out.println("Enter definition line");
         while(read.equals(""))
         {
             read = sc.nextLine();
         }
         c.setSpecificType(read);
         System.out.println("Specific Type Set to "+c.getSpecificType());
    }
    private void setErrorList()
    {
        ArrayList<Error> el = new ArrayList();
        el.add(e);
        c.setErrors(el);
        c.toString();
    }
    private void addVar()
    {
        Variable v2 = v;
        v2.setName("New Name");
        System.out.println("Variable Added:  "+c.addVariable(v2));
    }
    private void addError()
    {
        Error e2 = e;
        e2.setErrorNum("New Error");
        System.out.println("Error Added:  "+c.addError(e2));
    }
    private void printCode()
    {
        c.toString();
    }
    public Code getCode()
    {
        return c;
    }
}
