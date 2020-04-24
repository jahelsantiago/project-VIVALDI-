/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.UI;


import ProMange.Logic.Supervisor;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_xml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProBook
 */
public class FrameSignup extends javax.swing.JFrame {

        
    public FrameSignup() {
        initComponents();
        
        
    }
    
    

    


	
	public static Supervisor leerFichero() throws IOException, ClassNotFoundException {
		File file=new File("DatosAppNoBorrar");
		FileInputStream f = new FileInputStream(file);
                ObjectInputStream s = new ObjectInputStream(f);
        	Supervisor usuario = (Supervisor) s.readObject();
	        s.close();
		return usuario;
	}
        
        public static void escribirFishero(Supervisor usuario) throws IOException, ClassNotFoundException {
		File file=new File("DatosAppNoBorrar");
		FileOutputStream f =new FileOutputStream(file);
		ObjectOutputStream s = new ObjectOutputStream(f);
		s.writeObject(usuario);
		s.close();
	}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPasswordField1 = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jTextNameEmail = new javax.swing.JTextField();
        jButtonSingUp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(33, 33, 35));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(53, 113, 117));
        jLabel1.setText("Confirm Pasword");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, -1, -1));

        jTextName.setBackground(new java.awt.Color(33, 33, 35));
        jTextName.setForeground(new java.awt.Color(255, 255, 255));
        jTextName.setBorder(null);
        jTextName.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 260, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 260, -1));

        jPasswordField1.setBackground(new java.awt.Color(33, 33, 35));
        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setBorder(null);
        jPasswordField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 260, 30));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 260, 10));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 260, 10));

        jLabel2.setForeground(new java.awt.Color(53, 113, 117));
        jLabel2.setText("Name");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jTextNameEmail.setBackground(new java.awt.Color(33, 33, 35));
        jTextNameEmail.setForeground(new java.awt.Color(255, 255, 255));
        jTextNameEmail.setBorder(null);
        jTextNameEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextNameEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 260, 30));

        jButtonSingUp.setBackground(new java.awt.Color(124, 85, 227));
        jButtonSingUp.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButtonSingUp.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSingUp.setText("Registrese");
        jButtonSingUp.setBorder(null);
        jButtonSingUp.setBorderPainted(false);
        jButtonSingUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSingUpActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSingUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 260, 40));

        jLabel3.setForeground(new java.awt.Color(53, 113, 117));
        jLabel3.setText("Email");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        jLabel4.setForeground(new java.awt.Color(53, 113, 117));
        jLabel4.setText("Pasword");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        jPasswordField2.setBackground(new java.awt.Color(33, 33, 35));
        jPasswordField2.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField2.setBorder(null);
        jPasswordField2.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 260, 30));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 260, 10));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 550, 560));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("High Tower Text", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Factory Manager");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 395, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSingUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSingUpActionPerformed
        String user = this.jTextName.getText();
        String correo = this.jTextNameEmail.getText();
        String pasword = this.jPasswordField1.getText();
        String pasword2 = this.jPasswordField2.getText();
        
        
        if(pasword == pasword2){
            try {
                Supervisor sup = new Supervisor(user, correo, pasword);
                escribirFishero(sup);
            } catch (IOException ex) {
                Logger.getLogger(FrameSignup.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrameSignup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonSingUpActionPerformed

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
            java.util.logging.Logger.getLogger(FrameSignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameSignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameSignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameSignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameSignup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSingUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextName;
    private javax.swing.JTextField jTextNameEmail;
    // End of variables declaration//GEN-END:variables
}
