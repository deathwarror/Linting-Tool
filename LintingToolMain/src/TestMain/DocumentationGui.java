package TestMain;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultEditorKit;
import sun.security.krb5.internal.crypto.Des;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deathwarror
 */
public class DocumentationGui extends javax.swing.JDialog {

    private Dimension dim;
    private int ScreenHeight;
    private int ScreenWidth;
    
    /**
     * Creates new form DocumentationGui
     */
    public DocumentationGui(java.awt.Frame parent, boolean modal,String ErrorNumber ,String DURL,String DType,String DDType) {
        super(parent, modal);
        initComponents();
        
        ErrorDocumentationLoader EDL = new ErrorDocumentationLoader(DURL,DType);
        ErrorDocumentationLoader EDL2 = new ErrorDocumentationLoader(DURL,DDType);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        dim = toolkit.getScreenSize();
        String Description= EDL.getURL(ErrorNumber);
        //try loading the html version of the description
        try{
              Description = (EDL.getURL(ErrorNumber));
             try{
                DocumentationField.setPage((String)Description);
             }
             catch(Exception E)
             {
                 Description = EDL2.getURL(ErrorNumber);
                 DocumentationField.setPage(new URL(Description));
             }
             DocumentationField.setEditable(false);
             ErrorName.setText("Error "+ErrorNumber+":");
             ScreenHeight = (int)(dim.height*.8);
             ScreenWidth = (int)(dim.width*.8);
             this.setSize(new Dimension(ScreenWidth,ScreenHeight));
             this.setLocation((int)((dim.width-ScreenWidth)/2),(int)((dim.height-ScreenHeight)/2));
             this.setSize(new Dimension((int)(ScreenWidth*.9-200),(int)(ScreenHeight*.9-30)));
             this.setVisible(true);
        }
        //try loading the txt version of he description
        catch(Exception e)
        {
            e.printStackTrace();
            Description = EDL2.load(ErrorNumber);
             JOptionPane.showMessageDialog(new JFrame(), Description,("Error: "+ ErrorNumber),JOptionPane.INFORMATION_MESSAGE );
             this.dispose();
        }
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CloseButton = new javax.swing.JButton();
        JSP = new javax.swing.JScrollPane();
        DocumentationField = new javax.swing.JEditorPane();
        ErrorName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        CloseButton.setText("OK");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        JSP.setViewportView(DocumentationField);

        ErrorName.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(CloseButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ErrorName)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(ErrorName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JSP, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CloseButton)
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_CloseButtonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButton;
    private javax.swing.JEditorPane DocumentationField;
    private javax.swing.JLabel ErrorName;
    private javax.swing.JScrollPane JSP;
    // End of variables declaration//GEN-END:variables
}
