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
    private ArrayList<Variable> variables; // A complete list of all variables
    private ArrayList<Block> Blocks; // A complete list of all Blocks/subBlocks
    private String cleanInput;
    private ArrayList<String> usedPieces;
    private ArrayList<String> freshPieces;
    private int freshPieceIndex;
    public static int currentLineNumber;
    
    public ArrayList<Error> parserErrorList;
    
    Parser(ArrayList<String> original){
        //initializes "Parser" class members
        top = new Block("Top",null);
        variables = new ArrayList();
        usedPieces = new ArrayList();
        freshPieces = new ArrayList();
        Blocks = new ArrayList();
        parserErrorList = new ArrayList();
        freshPieceIndex = -1;
        currentLineNumber=0;
        
        //Checks to see if the ArrayList is Empty, and returns an error
        if(original.isEmpty()){
            System.out.println("Error, the file contians no code\n");
            return;
        }

        cleanInput = removeComments(original);
        cleanInput = spaceEvenly(cleanInput);
        
        makePieceList(freshPieces, cleanInput); 
        removeBlockNames();
        checkForTimingControl();
        if(!parserErrorList.isEmpty()){
            return;
        }
        
        String bob = getNextPiece();
        checkForNewBlock(top,bob); //this gets the first line number,

        bob = getNextPiece();
        checkForNewBlock(top,bob); // this starts actally parsing the module
        System.out.println("\n"+top.toString());

        variables.addAll(top.getAllVariables());
        System.out.println("\nVariables: "+variables.toString());
        Blocks.addAll(top.getAllBlocks());

      }
    private String removeComments(ArrayList<String> original){
        int i=0,numChars=0;
        String temp="";
        for(i =0; i<original.size(); i++){
            temp += " $# " + (i+1) + " ";
            temp += original.get(i);
            temp += " \n";
        }
//        System.out.println("\nBefore:\n");
//        System.out.print(temp);
        numChars = temp.length();
        char[] clean = temp.toCharArray();
        for(i=0; i<numChars; i++){
            switch(clean[i]){
                case '/':
                    if(clean[i+1] == '/'){
                        for(;clean[i]!='\n';i++)
                            clean[i]=' ';
                        clean[i] = ' ';
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
/*                case ',':
                    clean[i]=' ';
                    break;
*/                default:
                    break;
            }
        }
//        System.out.println("\nAfter:\n");
//        System.out.print(String.copyValueOf(clean));
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
        spacer = spaceElement("*",spacer);
        spacer = spaceElement(":",spacer);
        spacer = spaceElement("+",spacer);
        spacer = spaceElement("-",spacer);
        spacer = spaceElement("=",spacer);
        spacer = spaceElement("<",spacer);
        spacer = spaceElement(">",spacer);
        spacer = spaceElement("/",spacer);
        spacer = spaceElement("%",spacer);
        spacer = spaceElement("!",spacer);
        spacer = spaceElement("`",spacer);
        spacer = spaceElement("^",spacer);
        spacer = spaceElement("|",spacer);
        spacer = spaceElement("&",spacer);
        spacer = spaceElement("#",spacer);

//        System.out.println("\nEvenly Spaced\n"+spacer+"\n");
        return removeRadixSpaces(spacer);
    }
    private String removeRadixSpaces(String orig){
        String unspaced = "" + orig.charAt(0) + orig.charAt(1);
        char rad = ' ';
        for(int i = 2; i<orig.length(); i++){
            rad = orig.charAt(i-1);
            if(orig.charAt(i-2)=='\'' && 
                    (rad=='b' || rad=='B' || rad=='d' || rad=='D' || rad=='o' || rad=='O' || rad=='h' || rad=='H') &&
                    (orig.charAt(i)==' ' || orig.charAt(i)=='\n')){
                for(;orig.charAt(i)==' ' || orig.charAt(i)=='\n' || orig.charAt(i)=='$'; i++){
                    if(orig.charAt(i)=='$'){
                        for(i+=3; orig.charAt(i)!=' '; i++);
                    }
                }
            }
            unspaced += orig.charAt(i);
        }
        
//            temp += " $# " + (i+1) + " ";        
        return unspaced;
    }
    private String spaceElement(String delim, String original){
//        System.out.println("Looking for "+delim+"\n");
        original.indexOf(delim);
        String splits = original;
        String output = "";
        int delimStart = 0; int delimEnd=0;
        delimEnd = original.indexOf(delim, delimStart);
        while(delimEnd != -1){
            if(delim.equals("$")){
                if(original.charAt(delimEnd+1)!='#'){
                    output = output + original.substring(delimStart,delimEnd).trim()
                            + " " + delim + " " ;
                    delimStart = delimEnd+delim.length();
                    delimEnd = original.indexOf(delim, delimStart);
                }
            }else{
                output = output + original.substring(delimStart,delimEnd).trim()
                        + " " + delim + " " ;
                delimStart = delimEnd+delim.length();
                delimEnd = original.indexOf(delim, delimStart);
            }
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
            Always.parseAlways(current, this);
        }else if( piece.equals("if")){
            IfElse.parseIf(current, this);
        }else if( piece.equals("else")){
            IfElse.parseElse(current, this);
        }else if( piece.equals("case")){
            Case.parseCase(current, this);
        }else if( piece.equals("$#")){
            updateLineNumber();
        }
    }
    public String getNextPiece(){
        if(freshPieceIndex+1 < freshPieces.size()){
            freshPieceIndex++;
            return freshPieces.get(freshPieceIndex);
        }
        return "##END_OF_MODULE_CODE";
    }
    public void replaceCurrentPieceText(String newText){
        freshPieces.set(freshPieceIndex, newText);
    }
    public void reFRESHpieces(){
        freshPieceIndex = -1;
    }
    private void removeBlockNames(){
        for(int i=1; i<freshPieces.size()-1; i++){
            if(freshPieces.get(i-1).equals("$#")){
                currentLineNumber = Integer.parseInt( freshPieces.get(i));
            }else if(freshPieces.get(i).equals(":") && 
                    (freshPieces.get(i-1).equals("begin") || freshPieces.get(i-1).equals("end"))){
                freshPieces.remove(i+1);
                freshPieces.remove(i);
                String errorText="Error: Named Block Detected \n\ton line "+currentLineNumber;
                System.out.println(errorText);
                addErrorToParserErrorList(new Error("17",errorText) );
            }
        }
    }
    private void checkForTimingControl(){
        for(int i=3; i<freshPieces.size()-1; i++){
            if(freshPieces.get(i-1).equals("$#")){
                currentLineNumber = Integer.parseInt( freshPieces.get(i));
            }else if((freshPieces.get(i).equals("posedge") || freshPieces.get(i).equals("negedge"))
                    && freshPieces.get(i-1).equals("(")
                    && freshPieces.get(i-2).equals("@")
                    && !freshPieces.get(i-3).equals("always") ){
                addTimingControlError();
            }else if(isANumber(freshPieces.get(i)) == 1
                    && freshPieces.get(i-1).equals("#") ){
                addTimingControlError();
            }else if( freshPieces.get(i).equals("wait")){
                addTimingControlError();
            }
        }
    }
    private void addTimingControlError(){
        Error e = new Error();
        e.setErrorNum("2");
        String errorOutput = "Error: Timing Control in Combinatorial Block\n";
        errorOutput += "\ton line : "+currentLineNumber;
        System.out.println(errorOutput+"\n");
        e.setErrorMsg(errorOutput);
        addErrorToParserErrorList(e);
    }
    private static void makePieceList(ArrayList<String> tempFreshPieces, String inputString){
//        while(tempFreshPieces.lastIndexOf("endmodule") == -1){
        while( !inputString.equals("") && !inputString.equals(" ")){
            String[] piece = inputString.split(" ",2);
            if(piece.length != 1)
                inputString = piece[1];
            while( piece[0].equals("")){
                piece = inputString.split(" ",2);
                inputString = piece[1];
            }
            tempFreshPieces.add(piece[0]);
//            System.out.print(piece[0]+" ");
        }
        remergeLineNumbers(tempFreshPieces);
    }
    private static void remergeLineNumbers(ArrayList<String> tempFreshPieces){
        for(int i=1; i< tempFreshPieces.size(); i++){
            if(tempFreshPieces.get(i).equals("#") && tempFreshPieces.get(i-1).equals("$")){
                tempFreshPieces.set(i-1, "$#");
                tempFreshPieces.remove(i);
                i--;
            }
        }
    }
    public String getPreviousPiece(int traceBack){
        if(freshPieceIndex-traceBack >= 0 && freshPieceIndex-traceBack < freshPieces.size()){
            return freshPieces.get(freshPieceIndex - traceBack);
        }else{
            return "";
        }
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
        else if(piece.equals("localparam"))
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
        else if(piece.equals("case"))
            return true;
        else if(piece.equals("endcase"))
            return true;
        else if(piece.equals("default"))
            return true;
        else if(piece.equals("forever"))
            return true;
        else if(piece.equals("$#"))
            return true;
        else{
            return false;
        }
    }
    public ArrayList<Variable> getVariableList(){
        return variables;
    }
    public ArrayList<Block> getBlockList(){
        return Blocks;
    }
    public void setFreshPieceIndex(int newIndex){
        if(newIndex > -2 && newIndex+1 < freshPieces.size()){
            freshPieceIndex = newIndex;
        }
    }
    public int getFreshPieceIndex(){
        return freshPieceIndex;
    }
    public static String parseNumberFromExpression(String expression){
        ArrayList<String> pieces = new ArrayList();
        makePieceList(pieces,expression);
        int num = 0;//Integer.parseInt(expression, 10);
        //Integer.parseInt(expression);
        
        for(int i=0; i<pieces.size(); i++){
            if(pieces.get(i).equals("+")){
                i++;
                //This commented line is the one that will ultimately need to be implemented
                //num += parseNumberFromExpression(module, String expression);
                if( isANumber(pieces.get(i)) == 1 ){
                    num += Integer.parseInt(resolveNumberToDecimalRadix(pieces.get(i)) , 10);
                }
            }
            else if(pieces.get(i).equals("-")){
                i++;
                //This commented line is the one that will ultimately need to be implemented
                //num += parseNumberFromExpression(module, String expression);
                if( isANumber(pieces.get(i)) == 1 ){
                    num -= Integer.parseInt(resolveNumberToDecimalRadix(pieces.get(i)) , 10);
                }
            }
            else { //expand this
                if( isANumber(pieces.get(i)) == 1 ){
                    num += Integer.parseInt(resolveNumberToDecimalRadix(pieces.get(i)) , 10);
                }else if( isANumber(pieces.get(i)) == 2){
                    return resolveNumberToDecimalRadix(pieces.get(i));
                }
            }
        }
        
        return Integer.toString(num);
    }
    public static int parseNumberForParameter(Module module, String expression){
        return 0;
    }
    public static int isANumber(String inputString){
        //returns a 1 if the string is a legal verilog number,
        //returns a 2 if the string is a don't care "x" or high impedence "z"
        //returns a 0 if the string is neither of the two preveious
        String maybe = "";
        for(int i=0; i<inputString.length(); i++){
            if(inputString.charAt(i) !=' '){
                maybe += inputString.charAt(i);
            }
        }
        int quoteLocation = maybe.indexOf("'");
        char test;
        
        //if there is no quote ("'") specifing a radix then check to see if
        // the number is a decimal value, otherwise it is not a number
        if( quoteLocation == -1 ){
            for(int i=0; i< maybe.length(); i++){
                if( maybe.charAt(i)<48 || (maybe.charAt(i)>57 && maybe.charAt(i)!=95 )){
                    return 0;
                }
            }
            return 1;
        }else if( maybe.lastIndexOf("'") != quoteLocation ){
            return 0;
        }else{
            test = maybe.charAt(quoteLocation+1);
            if( test!='b' && test!='B' && test!='o' && test!='O' && test!='x' && test!='X' && test!='d' && test!='D'){
                return 0;
            }
            for(int i=quoteLocation+2; i<maybe.length(); i++){
                test = maybe.charAt(i);
                if( ( test<48 || (test>57 && test<65) || (test>70 && test<97) || test>102) && 
                        (test!='_' && test!='z' && test!='Z' && test!='x' && test!='X') ){
                    return 0;
                }
            }
            for(int i=quoteLocation+2; i<maybe.length(); i++){
                test = maybe.charAt(i);
                if(test=='z' || test=='Z' || test=='x' || test=='X'){
                    return 2;
                }
            }
            return 1;
        }
    }
    public static String resolveNumberToDecimalRadix(String inputString){
        String numText = "";
        //This loop clears out the whitespace allowed in the number
        for(int i=0; i<inputString.length(); i++){
            if(inputString.charAt(i) !=' '){
                numText += inputString.charAt(i);
            }
        }
        
        if( isANumber(numText)==0){
            //this probably should never happen!
            return "NOT_A_NUMBER";
        }else if(isANumber(numText)==2){
            return numText;
        }else{
            int quoteLocation = numText.indexOf("'");
            String num = "";
            
            if(quoteLocation == -1){
                for(int i=0; i<numText.length(); i++){
                    if(numText.charAt(i)!='_'){
                        num += numText.charAt(i);
                    }
                }
                return String.valueOf( Integer.parseInt(num, 10));
            }
            for(int i=quoteLocation+2; i<numText.length(); i++){
                num += numText.charAt(i);
            }
            switch(numText.charAt(quoteLocation+1)){
                    case('b'): return String.valueOf( Integer.parseInt(num, 2));
                    case('B'): return String.valueOf( Integer.parseInt(num, 2));
                    case('o'): return String.valueOf( Integer.parseInt(num, 8));
                    case('O'): return String.valueOf( Integer.parseInt(num, 8));
                    case('d'): return String.valueOf( Integer.parseInt(num, 10));
                    case('D'): return String.valueOf( Integer.parseInt(num, 10));
                    case('h'): return String.valueOf( Integer.parseInt(num, 16));
                    case('H'): return String.valueOf( Integer.parseInt(num, 16));
                    default: return numText;
            }
        }
    }
    public void updateLineNumber(){
        String number = getNextPiece(); //number should now be equal to the line number
        currentLineNumber = Integer.valueOf(number);
    }
    public void setLineNumber(int num){
        currentLineNumber = num;
    }
    public static int getCurrentLineNumber(){
        return currentLineNumber;
    }
    public void addErrorToParserErrorList(Error e){
        parserErrorList.add(e);
    }
    public ArrayList<Error> getParserErrorList(){
        return parserErrorList;
    }
    
    
}
