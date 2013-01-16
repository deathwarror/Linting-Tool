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
public class TestVariable {
    private Scanner sc;
    private Variable v, v2;
    TestVariable()
    {
        sc = new Scanner(System.in);
        v = new Variable();
        v2 = new Variable();
    }
    public void start()
    {
        System.out.println("Error Tester:\n");
        for(int i = 0;i>-1;)
        {
        System.out.println("-1\t quit\n"
                + "1\tBuild Variable\n"
                + "2\tSet Variable Name\n"
                + "3\tSet Variable Type\n"
                + "4\tSet Variable Edges to pos\n"
                + "5\tDuplicate Variable\n"
                + "6\tCompare Variable \n"
                + "7\tCompare Variable all fields\n"
                + "8\tAdd Edge Neg Sens to Variable\n"
                + "9\tPrint Primary Stored Error\n");
        i = sc.nextInt();
        switch(i){
            case 1:
                buildVariable();
                break;
            case 2:
                setVN();
                break;
            case 3:
                setVT();
                break;
            case 4:
                setEdge();
                break;
            case 5:
                dupV();
                break;
            case 6:
                compareV();
                break;
            case 7:
                compareVAll();
                break;
            case 8:
                addNegEdge();
                break;
            case 9:
                PrintVar();
                break;
            default:
                break;
        }
        }
    }
    public Variable Default()
    {
        String vn = "Name";
        String Vt = "Reg";
        ArrayList<String> el = new ArrayList();
        el.add("NegEdge");
        el.add("PosEdge");
        return new Variable(vn,Vt,el);
    }
    public void buildVariable()
    {
        String vn="";
        String vt="";
        String read="";
        ArrayList<String> ed = new ArrayList();
        System.out.println("Enter Variable Name");
        while(vn.equals(""))
        {
            vn = sc.nextLine();
        }
        
        System.out.println("Enter Variable Type");
        while(vt.equals(""))
        {
            vt = sc.nextLine();
        }
        for(int i = 0;i>-1; i = sc.nextInt())
        {
            read = "";
            System.out.println("Enter Edge Sensitivity");
            while(read.equals(""))
            {
                read = sc.nextLine();
            }
            ed.add(read);
            System.out.println("-1\t quit\n"
                    + "1\tContinue\n");
        }
        v = new Variable(vn,vt,ed);
    }
    private void setVN()
    {
        System.out.println("Enter Variable Name");
        String Read="";
        while(Read.equals(""))
        {
            Read = sc.nextLine();
        }
        v.setName(Read);
        System.out.println("Variable Name Set to "+v.getName());
    }
    private void setVT()
    {
        
        System.out.println("Enter Variable Type");
        String Read="";
        while(Read.equals(""))
        {
            Read = sc.nextLine();
        }
        v.setType(Read);
        System.out.println("Variable Type Set to \n\t"+v.getType());
    }
    private void setEdge()
    {
        ArrayList<String> el = new ArrayList();
        el.add("PosEdge");
        v.setEdge(el);
    }
    private void dupV()
    {
        v2 = new Variable(v.getName(),v.getType(),v.getEdge());
        System.out.println("Variable Duplicated\n\n");
        System.out.println("New Variable:\n");
        v2.toString();
    }
    private void compareV()
    {
        System.out.println("Variable Compare = "+v.compareTo(v2));
    }
    private void compareVAll()
    {
        System.out.println("Variable Compare All: "+v.compareToAll(v2));
    }
    private void addNegEdge()
    {
        System.out.println("Add NegEdge: "+ v.addEdge("NegEdge"));
    }
    private void PrintVar()
    {
        v.toString();
    }
    public Variable getVariable()
    {
        return v;
    }
}

