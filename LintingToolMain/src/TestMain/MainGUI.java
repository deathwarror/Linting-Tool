/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestMain;

import com.sun.corba.se.impl.util.Version;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Deathwarror
 */
public class MainGUI extends javax.swing.JFrame {

    private String ErrorLineColor = "F78181";
    private String CommentColor = "04B404";
    private String KeywordColor = "0404B4";
    private String QuoteColor = "FFD006";
    private String DocumentationURL = "https://linting-tool.googlecode.com/git/Database/v/Documentation/";
    private String DocumentationType = ".html";
    private String DefaultDocType = ".txt";
    private DefaultListModel FileListModel;
    private DefaultListModel ErrorListModel;
    private DefaultListModel CodeListModel;
    private int ScreenHeight;
    private int ScreenWidth;
    private Dimension dim;
    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();

        FileListModel = new DefaultListModel();
        ErrorListModel = new DefaultListModel();
        CodeListModel = new DefaultListModel();
        FileListBox.setModel(FileListModel);
        ErrorListBox.setModel(ErrorListModel);
        CodeListBox.setModel(CodeListModel);
        RemoveButton.setEnabled(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        dim = toolkit.getScreenSize();
        ScreenHeight = (int)(dim.height*.8);
        ScreenWidth = (int)(dim.width*.8);
        VersionNumber.setText(("Version: "+version.version));
        this.setSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setLocation((int)((dim.width-ScreenWidth)/2),(int)((dim.height-ScreenHeight)/2));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileListPane = new javax.swing.JScrollPane();
        FileListBox = new javax.swing.JList();
        AddButton = new javax.swing.JButton();
        RemoveButton = new javax.swing.JButton();
        FileListTitle = new javax.swing.JLabel();
        CodeListTitle = new javax.swing.JLabel();
        ErrorMsgTitle = new javax.swing.JLabel();
        ErrorListPane = new javax.swing.JScrollPane();
        ErrorListBox = new javax.swing.JList();
        CodeListPane = new javax.swing.JScrollPane();
        CodeListBox = new javax.swing.JList();
        VersionNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Linting Tool");
        setBounds(new java.awt.Rectangle(0, 0, 800, 600));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        FileListBox.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                FileListBoxValueChanged(evt);
            }
        });
        FileListPane.setViewportView(FileListBox);

        AddButton.setText("Load File");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        RemoveButton.setText("Remove File");
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });

        FileListTitle.setText("Files Opened");

        CodeListTitle.setText("File data");

        ErrorMsgTitle.setText("Error Messages");

        ErrorListBox.setToolTipText("Right Click For More Information about the Error (Requires Internet Connection)");
        ErrorListBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ErrorListBoxMouseClicked(evt);
            }
        });
        ErrorListBox.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ErrorListBoxValueChanged(evt);
            }
        });
        ErrorListPane.setViewportView(ErrorListBox);

        CodeListPane.setViewportView(CodeListBox);

        VersionNumber.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ErrorListPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FileListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FileListTitle)
                            .addComponent(ErrorMsgTitle)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RemoveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CodeListTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(VersionNumber))
                            .addComponent(CodeListPane))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FileListTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CodeListTitle)
                    .addComponent(VersionNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FileListPane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddButton)
                            .addComponent(RemoveButton)))
                    .addComponent(CodeListPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ErrorMsgTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ErrorListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        // TODO add your handling code here:
        SimpleFileStore newFile = new SimpleFileStore();
        FileRead FR = new FileRead();
        Parser parser;
        ErrorSearcher ES = new ErrorSearcher();
        FR.newFile();
        if(!FR.getFileName().equals(""))
        {
            newFile.setCode(FR.getCode());
            newFile.setName(FR.getFileName());
            System.out.println("enter");
            parser = new Parser(newFile.getCode());
            System.out.println("exit parser");
            newFile.setErrorList(ES.Start(parser));
            System.out.println("exit Error list");
            FileListModel.addElement(newFile);
            FileListBox.setSelectedIndex(FileListModel.size()-1);
            FileListBox.ensureIndexIsVisible(FileListModel.size()-1);
        }
        else
        {
            JOptionPane.showMessageDialog(new JFrame(), "Error the File Failed to Read");
        }

    }//GEN-LAST:event_AddButtonActionPerformed

    private void FileListBoxValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_FileListBoxValueChanged
        // TODO add your handling code here:
        if(evt.getValueIsAdjusting()==false)
        {
            int index = FileListBox.getSelectedIndex();
            SimpleFileStore WorkingStore;
            ArrayList<String> Code;
            ArrayList<Error> Errors;
            String FileCode;
            
            
            //Clear The ErrorList and the Code List here
            ErrorListModel.clear();
            CodeListModel.clear();
            if(index == -1)
            {
                RemoveButton.setEnabled(false);
            }
            else
            {
                WorkingStore = (SimpleFileStore) FileListModel.get(index);
                Code = WorkingStore.getCode();
                Code = HighlightKeyWords(Code);
                Errors = WorkingStore.getErrorList();
                RemoveButton.setEnabled(true);
                for(int i = 0; i<Code.size();i++)
                {
                    FileCode = AdjustLine(i+1,Code.get(i));
                    CodeListModel.addElement("<html>"+FileCode+"</html>");
                }
                
                for(int i = 0; i<Errors.size();i++)
                {
                    Error e = Errors.get(i);
                    if(!e.getLineNumbers().isEmpty()){
                        String Temp = convertToHTML(e.getLineNumbers().get(0)+":  Error "+e.getErrorNum()+": "+e.getErrorMsg()+"\n\n");
                        ErrorListModel.addElement("<html>"+Temp+"</html>");
                    }else {
                        String Temp = convertToHTML("Error "+e.getErrorNum()+":  "+e.getErrorMsg()+"\n\n");
                        ErrorListModel.addElement("<html>"+Temp+"</html>");
                    }
                }
                if(Errors.isEmpty())
                {
                    ErrorListModel.addElement("No Errors Detected");
                }
            }
        }
    }//GEN-LAST:event_FileListBoxValueChanged



    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveButtonActionPerformed
        int index = FileListBox.getSelectedIndex();
        if(index != -1)
        {
             String FileName = ((SimpleFileStore) FileListModel.get(index)).getName();
             FileListModel.remove(index);
        }

        int size = FileListModel.getSize();
        if(size == 0)
        {
            RemoveButton.setEnabled(false);
        }
        else
        {
            if(index == FileListModel.getSize())
            {
                index--;
            }
            FileListBox.setSelectedIndex(index);
            FileListBox.ensureIndexIsVisible(index);
        }

    }//GEN-LAST:event_RemoveButtonActionPerformed

    private void ErrorListBoxValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ErrorListBoxValueChanged
        // TODO add your handling code here:
        if(evt.getValueIsAdjusting()==false)
        {
            int index = ErrorListBox.getSelectedIndex();
            int FileIndex = FileListBox.getSelectedIndex();
            SimpleFileStore WorkingStore;
            Error e;
            ArrayList<Error> EL;
            ArrayList<Integer> ErrorLines;
            ArrayList<String> Code;
            String FileCode;
            //if nothing is selected
            
            if(index == -1)
            {
            }
            else
            {
                
              WorkingStore = (SimpleFileStore) FileListModel.get(FileIndex);
              EL = WorkingStore.getErrorList();
              Code = WorkingStore.getCode();
              Code = HighlightKeyWords(Code);
              
              if(EL.size() >0)
              {
                    //if Something was selected 
                    CodeListModel.clear();
                    
                    e = EL.get(index);
                    ErrorLines = e.getLineNumbers();
                    sortLineNumbers(ErrorLines);
                    //fill the cell with 
                    int j = 0;

                    //rebuilds the HTML code so that the error lines are highlighted
                     for(int i = 0; i<Code.size();i++)
                    {
                          FileCode = AdjustLine(i+1,Code.get(i));
                          if(j < ErrorLines.size())
                          {
                              if(ErrorLines.get(j).intValue()-1 == i)
                              {
                                  FileCode = "<body bgcolor="+ErrorLineColor+"\">"+FileCode;
                                  j++;
                              }
                          }
                          CodeListModel.addElement("<html>"+FileCode+"</html>");
                    }
              }
            }
        }
    }//GEN-LAST:event_ErrorListBoxValueChanged

    private void ErrorListBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ErrorListBoxMouseClicked
        //if the right mouse button was used
        if(evt.getButton() ==java.awt.event.MouseEvent.BUTTON3)
        {
            ErrorDocumentationLoader EDL = new ErrorDocumentationLoader(DocumentationURL,DocumentationType);
            ErrorDocumentationLoader EDL2 = new ErrorDocumentationLoader(DocumentationURL,DefaultDocType);
            String Description;
            SimpleFileStore WorkingStore;
            ArrayList<Error> EL;
            String ErrorNumber;
            JEditorPane ErrorD;
            JScrollPane JSP;
            
            int FileIndex = FileListBox.getSelectedIndex();
            int index = ErrorListBox.getSelectedIndex();
            if(index>=0)
            {
                WorkingStore = (SimpleFileStore) FileListModel.get(FileIndex);
                EL = WorkingStore.getErrorList();
  
                if(EL.size()>0)
                {
                    ErrorNumber = EL.get(index).getErrorNum();
                    //try using the gui based load method
                    
                        DocumentationGui dialog = new DocumentationGui(new javax.swing.JFrame(), true, ErrorNumber,DocumentationURL,DocumentationType,DefaultDocType);
                        
                    
                }
            }
            
        }
    }//GEN-LAST:event_ErrorListBoxMouseClicked


    
    //converts the arraylist of numbers to an integer array
    public static void sortLineNumbers(ArrayList<Integer> arr){
        int i=0, j=0;
        Integer temp = new Integer(0);
        for(i=0; i<arr.size(); i++){
            for(j=0; j<arr.size()-(i+1); j++){
                if(arr.get(j).compareTo(arr.get(j+1)) == 1){
                    temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
                else if(arr.get(j).compareTo(arr.get(j+1)) == 0){
                    arr.remove(j);
                    i--; j--;
                }
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }
    private String AdjustLine(int i, String text){
        String Temp = i+":&#8202;";
        String inte = ""+i;
        for(int j = 0; j<(4-inte.length());j++)
        {
            Temp= Temp+"&nbsp;&nbsp;";
        }
        return Temp+convertToHTML(text);
    }
    private ArrayList<String> HighlightKeyWords(ArrayList<String> TempAL){
        ArrayList<String> newLines = new ArrayList();
        String temp1, temp2;
        int startIndex=0,endIndex = 0;
        boolean blockComment = false;
        for(int i = 0; i < TempAL.size();i++)
        {
            temp1 = "";
            temp2 = " "+TempAL.get(i)+" ";
            
            temp2 = replaceAll(temp2);
            
            boolean inQuote = false;
            char[] arr = temp2.toCharArray();
            String line = "", line2 = "";
            for(int k=1; k<arr.length; k++){
                if(arr[k]=='\"' && arr[k-1]!='\\' && !inQuote){
                    line += line2; line2="";
                    line += "<font color=#"+QuoteColor+">\"";
                    inQuote = true;
                }
                else if(arr[k]=='\"' && arr[k-1]!='\\' && inQuote){
                    line2 = removePreviousColors(line2);
                    line += line2; line2 = "";
                    line += "\"</font>";
                    inQuote = false;
                }
                else{
                    line2 += arr[k];
                }
            }
            
            line += line2;
            temp2 = line;
            
            if(blockComment && !temp2.contains("*/")){
                temp2 = removePreviousColors(temp2);
                temp2 = "<font color=#"+CommentColor+">"+temp2;
                temp2 += "</font>";
            }
            else if(temp2.contains("/*") && !temp2.contains("*/"))
            {
                startIndex = temp2.indexOf("/*");
                temp1 = temp2.substring(0,startIndex);
                temp1 = replaceAll(temp1);
                temp2 = removePreviousColors(temp2.substring(startIndex));
                temp2 = temp1+temp2;
                temp2 = temp2.replace("/*","<font color=#"+CommentColor+">/*");
                temp2 = temp2 + "</font>";
                blockComment = true;
            }
            else if(temp2.contains("/*") && temp2.contains("*/"))
            {
                temp2 = removePreviousColors(temp2);
                int comNum = 0;
                boolean lineCom = false;
                arr = temp2.toCharArray();
                line = "";
                for(int j=1; j<arr.length; j++){
                    if(arr[j]=='*' && arr[j-1]=='/' && !lineCom){
                        if(line.length()>0){
                            line = line.substring(0, line.length()-1);
                        }
                        line = replaceAll(line);
                        line += "<font color=#"+CommentColor+">/*";
                        comNum++;
                    }
                    else if(arr[j]=='/' && arr[j-1]=='*' && !lineCom){
                        line += "/</font>";
                        comNum--;
                        lineCom = false;
                    }
                    else if(arr[j]=='/' && arr[j-1]=='/'){
                        if(line.length()>0){
                            line = line.substring(0, line.length()-1);
                        }
                        line = replaceAll(line);
                        line += "<font color=#"+CommentColor+">//";
                    }
                    else{
                        line += arr[j];
                    }
                }
                
                if(lineCom){
                    line  += "</font>/";
                }
                else if(comNum > 0){
                    line  += "</font>/";
                    blockComment = true;
                }
                temp2 = line;
            }
            else if(temp2.contains("*/"))
            {
                
                startIndex = temp2.indexOf("*/");
                temp1 = temp2.substring(startIndex);
                temp1 = replaceAll(temp1);
                temp2 = removePreviousColors(temp2.substring(0,startIndex))+temp1;
                temp2 = temp2.replace("*/","*/</font>");
                temp2 = "<font color=#"+CommentColor+">"+ temp2;
                blockComment = false;
            }
            else if(temp2.contains("//"))
            {
                startIndex = temp2.indexOf("//");
                temp1 = temp2.substring(0,startIndex);
                temp1 = replaceAll(temp1);
                temp2 = temp1+removePreviousColors(temp2.substring(startIndex));
                temp2 = temp2.replace("//","<font color=#"+CommentColor+">//");
                temp2 = temp2+"</font>";
            }
            
            newLines.add(temp2);
        }
        return newLines;
    }
    private String replaceAll(String S){
        //module
        S = S.replaceAll(" module "," <font color=#"+KeywordColor+">module</font> ");
        S = S.replaceAll(";module ",";<font color=#"+KeywordColor+">module</font> ");
        S = S.replaceAll("module ","<font color=#"+KeywordColor+">module</font> ");
        //input
        S = S.replaceAll(" input "," <font color=#"+KeywordColor+">input</font> ");
        S = S.replaceAll(",input ",",<font color=#"+KeywordColor+">input</font> ");
        S = S.replaceAll(";input ",";<font color=#"+KeywordColor+">input</font> ");
        S = S.replaceAll("\\(input ","\\(<font color=#"+KeywordColor+">input</font> ");
        //inout
        S = S.replaceAll(" inout "," <font color=#"+KeywordColor+">inout</font> ");
        S = S.replaceAll(",inout ",",<font color=#"+KeywordColor+">inout</font> ");
        S = S.replaceAll(";inout ",";<font color=#"+KeywordColor+">inout</font> ");
        S = S.replaceAll("\\(inout ","\\(<font color=#"+KeywordColor+">inout</font> ");
        //output
        S = S.replaceAll(" output "," <font color=#"+KeywordColor+">output</font> ");
        S = S.replaceAll(",output ",",<font color=#"+KeywordColor+">output</font> ");
        S = S.replaceAll(";output ",";<font color=#"+KeywordColor+">output</font> ");
        S = S.replaceAll("\\(output ","\\(<font color=#"+KeywordColor+">output</font> ");
        //parameter
        S = S.replaceAll(" parameter "," <font color=#"+KeywordColor+">parameter</font> ");
        S = S.replaceAll(";parameter ",";<font color=#"+KeywordColor+">parameter</font> ");
        S = S.replaceAll(" parameter="," <font color=#"+KeywordColor+">parameter</font>=");
        S = S.replaceAll(";parameter=",";<font color=#"+KeywordColor+">parameter</font>=");
        //localparameter
        S = S.replaceAll(" localparam "," <font color=#"+KeywordColor+">localparam</font> ");
        S = S.replaceAll(";localparam ",";<font color=#"+KeywordColor+">localparam</font> ");
        S = S.replaceAll(" localparam="," <font color=#"+KeywordColor+">localparam</font>=");
        S = S.replaceAll(";localparam=",";<font color=#"+KeywordColor+">localparam</font>=");
        //register 
        S = S.replaceAll(" reg ", " <font color=#"+KeywordColor+">reg</font> ");
        S = S.replaceAll(";reg ", ";<font color=#"+KeywordColor+">reg</font> ");
        //wire
        S = S.replaceAll(" wire ", " <font color=#"+KeywordColor+">wire</font> ");
        S = S.replaceAll(";wire ", ",<font color=#"+KeywordColor+">wire</font> ");
        //always
        S = S.replaceAll(" always "," <font color=#"+KeywordColor+">always</font> ");
        S = S.replaceAll(";always ",";<font color=#"+KeywordColor+">always</font> ");
        S = S.replaceAll(" always@"," <font color=#"+KeywordColor+">always</font>@");
        S = S.replaceAll(";always@",";<font color=#"+KeywordColor+">always</font>@");
        //if
        S = S.replaceAll(" if "," <font color=#"+KeywordColor+">if</font> ");
        S = S.replaceAll(";if ",";<font color=#"+KeywordColor+">if</font> ");
        S = S.replaceAll(" if\\("," <font color=#"+KeywordColor+">if</font>\\(");
        S = S.replaceAll(";if\\(",";<font color=#"+KeywordColor+">if</font>\\(");
        //else
        S = S.replaceAll(";else ",";<font color=#"+KeywordColor+">else</font> ");
        S = S.replaceAll(" else "," <font color=#"+KeywordColor+">else</font> ");
        //end
        S = S.replaceAll(";end ",";<font color=#"+KeywordColor+">end</font> ");
        S = S.replaceAll(";end;",";<font color=#"+KeywordColor+">end</font>;");
        S = S.replaceAll("\\)end ","\\)<font color=#"+KeywordColor+">end</font> ");
        S = S.replaceAll("\\)end;","\\)<font color=#"+KeywordColor+">end</font>;");
        S = S.replaceAll(" end;"," <font color=#"+KeywordColor+">end</font>;");
        S = S.replaceAll(" end "," <font color=#"+KeywordColor+">end</font> ");
        S = S.replaceAll(";end\\(",";<font color=#"+KeywordColor+">end</font>\\(");
        S = S.replaceAll(" end\\("," <font color=#"+KeywordColor+">end</font>\\(");
        S = S.replaceAll("end"," <font color=#"+KeywordColor+">end</font>");
        //begin
        S = S.replaceAll(";begin ",";<font color=#"+KeywordColor+">begin</font> ");
        S = S.replaceAll(";begin;",";<font color=#"+KeywordColor+">begin</font>;");
        S = S.replaceAll(" begin;"," <font color=#"+KeywordColor+">begin</font>;");
        S = S.replaceAll(" begin "," <font color=#"+KeywordColor+">begin</font> ");
        S = S.replaceAll("\\)begin ","\\)<font color=#"+KeywordColor+">begin</font> ");
        S = S.replaceAll("\\)begin;","\\)<font color=#"+KeywordColor+">begin</font>;");
        S = S.replaceAll(";begin\\(",";<font color=#"+KeywordColor+">begin</font>\\(");
        S = S.replaceAll(" begin\\("," <font color=#"+KeywordColor+">begin</font>\\(");
        //endmodule
        S = S.replaceAll(";endmodule ",";<font color=#"+KeywordColor+">endmodule</font> ");
        S = S.replaceAll(" endmodule "," <font color=#"+KeywordColor+">endmodule</font> ");
        S = S.replaceAll("endmodule"," <font color=#"+KeywordColor+">endmodule</font> ");
        //case
        S = S.replaceAll(";case ",";<font color=#"+KeywordColor+">case</font> ");
        S = S.replaceAll(" case "," <font color=#"+KeywordColor+">case</font> ");
        S = S.replaceAll(";case\\(",";<font color=#"+KeywordColor+">case</font>\\(");
        S = S.replaceAll(" case\\(",";<font color=#"+KeywordColor+">case</font>\\(");
        
        //endcase
        S = S.replaceAll(";endcase ",";<font color=#"+KeywordColor+">endcase</font> ");
        S = S.replaceAll(";endcase;",";<font color=#"+KeywordColor+">endcase</font>;");
        S = S.replaceAll(" endcase;"," <font color=#"+KeywordColor+">endcase</font>;");
        S = S.replaceAll(" endcase "," <font color=#"+KeywordColor+">endcase</font> ");
        S = S.replaceAll(";endcase\\(",";<font color=#"+KeywordColor+">endcase</font>\\(");
        S = S.replaceAll(" endcase\\("," <font color=#"+KeywordColor+">endcase</font>\\(");
        S = S.replaceAll("\\)endcase ","\\)<font color=#"+KeywordColor+">endcase</font> ");
        S = S.replaceAll("\\)endcase;","\\)<font color=#"+KeywordColor+">endcase</font>;");
        //default
        S = S.replaceAll(";default ",";<font color=#"+KeywordColor+">default</font> ");
        S = S.replaceAll(" default "," <font color=#"+KeywordColor+">default</font> ");
        //forever
        S = S.replaceAll(";forever ",";<font color=#"+KeywordColor+">forever</font> ");
        S = S.replaceAll(" forever "," <font color=#"+KeywordColor+">forever</font> ");
        //task
        S = S.replaceAll(";task ",";<font color=#"+KeywordColor+">task</font> ");
        S = S.replaceAll(" task "," <font color=#"+KeywordColor+">task</font> ");
        S = S.replaceAll(";task\\(",";<font color=#"+KeywordColor+">task</font>\\(");
        S = S.replaceAll(" task\\("," <font color=#"+KeywordColor+">task</font>\\(");
        //function
        S = S.replaceAll(";function ",";<font color=#"+KeywordColor+">function</font> ");
        S = S.replaceAll(" function "," <font color=#"+KeywordColor+">function</font> ");
        S = S.replaceAll(";function\\(",";<font color=#"+KeywordColor+">function</font>\\(");
        S = S.replaceAll(" function\\("," <font color=#"+KeywordColor+">function</font>\\(");
        //while
        S = S.replaceAll(";while ",";<font color=#"+KeywordColor+">while</font> ");
        S = S.replaceAll(" while "," <font color=#"+KeywordColor+">while</font> ");
        S = S.replaceAll(";while\\(",";<font color=#"+KeywordColor+">while</font>\\(");
        S = S.replaceAll(" while\\("," <font color=#"+KeywordColor+">while</font>\\(");
        //for
        S = S.replaceAll(";for ",";<font color=#"+KeywordColor+">for</font> ");
        S = S.replaceAll(" for "," <font color=#"+KeywordColor+">for</font> ");
        S = S.replaceAll(";for\\(",";<font color=#"+KeywordColor+">for</font>\\(");
        S = S.replaceAll(" for\\("," <font color=#"+KeywordColor+">for</font>\\(");
        //repeat
        S = S.replaceAll(";repeat ",";<font color=#"+KeywordColor+">repeat</font> ");
        S = S.replaceAll(" repeat "," <font color=#"+KeywordColor+">repeat</font> ");
        S = S.replaceAll(";repeat\\(",";<font color=#"+KeywordColor+">repeat</font>\\(");
        S = S.replaceAll(" repeat\\("," <font color=#"+KeywordColor+">repeat</font>\\(");
        //initial
        S = S.replaceAll(";initial ",";<font color=#"+KeywordColor+">initial</font> ");
        S = S.replaceAll(" initial "," <font color=#"+KeywordColor+">initial</font> ");
        S = S.replaceAll(";initial\\(",";<font color=#"+KeywordColor+">initial</font>\\(");
        S = S.replaceAll(" initial\\("," <font color=#"+KeywordColor+">initial</font>\\(");
        return S;
    }
    private String removePreviousColors(String S){
        String s = S;
        String ss = "";
        int i, last;
        boolean dont = false;
        for(i=s.indexOf("<font"),last=0; i!=-1;i=s.indexOf("<font")){
            ss += s.substring(last, i);
            if(!dont){
                i = s.indexOf(">")+1;
                dont = true;
            }else {
                i = s.indexOf(">")+1;
                s = s.substring(i, s.length());
                i = s.indexOf(">")+1;
                dont = false;
            }
            s = s.substring(i, s.length());
        }
        ss += s.substring(0, s.length());
        s = ss; ss= "";
        for(i=s.indexOf("</font>"),last=0; i!=-1;last=0,i=s.indexOf("</font>")){
            ss += s.substring(last, i);
            i = s.indexOf(">")+1;
            s = s.substring(i, s.length());
        }
        ss += s.substring(0, s.length());
        return ss;
    }
    
    private String convertToHTML(String S){
        String Temp = S;
        Temp = Temp.replaceAll("\n","<br>");
        Temp = Temp.replaceAll("\t","&nbsp;&nbsp;&nbsp;&nbsp;");
        Temp = Temp.replaceAll(" ","&nbsp;");
        return Temp;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JList CodeListBox;
    private javax.swing.JScrollPane CodeListPane;
    private javax.swing.JLabel CodeListTitle;
    private javax.swing.JList ErrorListBox;
    private javax.swing.JScrollPane ErrorListPane;
    private javax.swing.JLabel ErrorMsgTitle;
    private javax.swing.JList FileListBox;
    private javax.swing.JScrollPane FileListPane;
    private javax.swing.JLabel FileListTitle;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JLabel VersionNumber;
    // End of variables declaration//GEN-END:variables


}
