/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestMain;

import java.util.ArrayList;
/**
 *
 * @author Hell-o
 */
public class Parser {
    private Block top;
    private ArrayList<Variable> variables;
    private String cleanInput;
    
    Parser(ArrayList<String> original){
        //initializes "Parser" class members
        top = new Block("Top",null);
        variables = new ArrayList();

        //Checks to see if the ArrayList is Empty, and returns an error
        if(original.isEmpty()){
            System.out.println("Error, the file contians no code\n");
        }

        cleanInput = removeComments(original);
        cleanInput = spaceEvenly(cleanInput);

        String bob = getNextPiece();
//        for(bob=""; !bob.equals("module"); bob=getNextPiece());
        checkForNewBlock(top,bob);
        System.out.println("\n"+top.toString());


        /*The outer loop traverses every string line in the file, allowing the 
         * inner loop to split it apart by spaces and check for the presence of
         * a module, and assign it a block, and begin parsing it.
         */
/*        for(int i=0; i != original.size(); i++){
            if(!original.get(i).contains("//")){
                String[] line = original.get(i).split(" ");
                for(int j=0; line[j]!=null; j++){
                    if(line[j].trim().matches("module")){
                        if(parseModule(i,original)){
                            return;
                        }
                    }
                }
            }
        }
  */      System.out.println("\nError, No module detected in code file\n");
    }
    private String removeComments(ArrayList<String> original){
        int i=0,numChars=0;
        String temp="";
        for(i =0; i<original.size(); i++){
            temp += original.get(i);
            temp += " \n";
        }
        System.out.println("\nBefore:\n");
        System.out.print(temp);
        numChars = temp.length();
        char[] clean = temp.toCharArray();
        for(i=0; i<numChars; i++){
            switch(clean[i]){
                case '/':
                    if(clean[i+1] == '/'){
                        for(;clean[i]!='\n';i++)
                            clean[i]=' ';
                    }else if(clean[i+1] == '*'){
                        for(; !(clean[i]=='*' && clean[i+1]=='/') && i+1<numChars; i++)
                            clean[i]=' ';
                        clean[i]=' '; clean[i+1]=' ';
                        i++;
                    }
                    break;
                case '\n':
                    clean[i]=' ';
                    break;
                case '\r':
                    clean[i]=' ';
                    break;
                case ',':
                    clean[i]=' ';
                    break;
                default:
                    break;
            }
        }
        System.out.println("\nAfter:\n");
        System.out.print(String.copyValueOf(clean));
        return String.copyValueOf(clean);
    }
    private String spaceEvenly(String original){
        String spacer = original;
        spacer = spaceElement("(",spacer);
        spacer = spaceElement(")",spacer);
        spacer = spaceElement("[",spacer);
        spacer = spaceElement("]",spacer);
        spacer = spaceElement("@",spacer);
        spacer = spaceElement(",",spacer);
        spacer = spaceElement("`",spacer);
        spacer = spaceElement(";",spacer);

        System.out.println("\nEvenly Spaced\n"+spacer+"\n");
        return spacer;
    }
    private String spaceElement(String delim, String original){
        System.out.println("Looking for "+delim+"\n");
        original.indexOf(delim);
        String splits = original;
        String output = "";
        int delimStart = 0; int delimEnd=0;
        delimEnd = original.indexOf(delim, delimStart);
        while(delimEnd != -1){
            output = output + original.substring(delimStart,delimEnd).trim()
                    + " " + delim + " " ;
            delimStart = delimEnd+delim.length();
            delimEnd = original.indexOf(delim, delimStart);
        }
        output += original.substring(delimStart, original.length());

        return output;
    }

    public void checkForNewBlock(Block current, String nextWord){
        String piece = nextWord;
        if( piece.equals("module") ){
            Module.parseModule(current,this);
        }else if( piece.equals("`")){
            parseCompilerDerective(current);
        }else if( piece.equals("always")){
            System.out.println("NO, not an always block!!\n");
            //Always.parseAlways(current,this);
        }
    }
    public String getNextPiece(){
        String[] piece = cleanInput.split(" ",2);
        if(piece.length != 1)
            cleanInput = piece[1];
        while( piece[0].equals("")){
            piece = cleanInput.split(" ",2);
            cleanInput = piece[1];
        }
        return piece[0];
    }

    public void parseCompilerDerective(Block current) {
        String piece = getNextPiece();
        while(!pieceIsKeyword(piece)){
            
            piece = getNextPiece();
        }
        checkForNewBlock(current, piece);
    }
    public boolean pieceIsKeyword(String piece){
        if( piece.equals("module"))
            return true;
        else if(piece.equals("`"))
            return true;
        else if(piece.equals("input"))
            return true;
        else if(piece.equals("output"))
            return true;
        else if(piece.equals("parameter"))
            return true;
        else if(piece.equals("reg"))
            return true;
        else if(piece.equals("wire"))
            return true;
        else if(piece.equals("always"))
            return true;
        else if(piece.equals("if"))
            return true;
        else if(piece.equals("else"))
            return true;
        else if(piece.equals("end"))
            return true;
        else if(piece.equals("endmodule"))
            return true;
        else{
            return false;
        }
    }
}
