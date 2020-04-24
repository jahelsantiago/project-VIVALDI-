/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.UI;


import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.Maquina;
import ProMange.Logic.OrdenJ;
import ProMange.Logic.ProductoJ;
import ProMange.Logic.Supervisor;
import ProMange.Logic.Xml_clases.EmpleadoJ_excel;
import ProMange.Logic.Xml_clases.Maquina_excel;
import ProMange.Logic.Xml_clases.OrdenJ_excel;
import ProMange.Logic.Xml_clases.ProductoJ_excel;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_carpeta;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_xml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ProBook
 */
public class FrameSignup extends javax.swing.JFrame {

        
    public FrameSignup() {
        initComponents();
        
        
    }
    
    public void inicializarCuenta(){ 
        this.setLocationRelativeTo(null);
        System.err.println("inicializado con exito");
        crear_carpeta();
        
        crear_xml("Maquina","basedatosMaquina.xml");
        crear_xml("EmpleadoJ","basedatosEmpleados.xml");
        crear_xml("PedidoJ","basedatosPedidos.xml");
        crear_xml("ProductoJ","basedatosProductos.xml");
        crear_xml("Orden","basedatosOrdenes.xml");
        
        
        EmpleadoJ_excel empleado_excel = new EmpleadoJ_excel();        
        EmpleadoJ nuevo_empleado = new EmpleadoJ();
        empleado_excel.agregarEmpleado(nuevo_empleado);
        
        Maquina_excel maquina_excel = new Maquina_excel();
        Maquina nueva_maquina = new Maquina();
        maquina_excel.agregarMaquina(nueva_maquina);
        
        OrdenJ_excel orden_excel = new OrdenJ_excel();
        OrdenJ nueva_orden = new OrdenJ();
        orden_excel.agregarOrden(nueva_orden);
        
        ProductoJ_excel producto_excel = new ProductoJ_excel();
        ProductoJ nuevo_producto = new ProductoJ();
        producto_excel.agregarProducto(nuevo_producto);
        
//        Pedido_excel pedido_excel = new Pedido_excel();
//        PedidoJ nuevo_pedido = new PedidoJ();
//        pedido_excel.agregarPedido(nuevo_pedido);
        
            
        
        
        
        
//        FrameSignup l = new FrameSignup();
//        l.setVisible(true); 
//        this.setVisible(false);
            
    }

    


	

        
        public static void escribirFishero(Supervisor usuario) throws IOException, ClassNotFoundException {
		File file=new File("xml_archivos/DatosSupervisor");
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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(53, 113, 117));
        jLabel1.setText("Confirme Contraseña");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        jTextName.setForeground(new java.awt.Color(255, 255, 255));
        jTextName.setBorder(null);
        jTextName.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 260, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 260, -1));

        jPasswordField1.setBorder(null);
        jPasswordField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 260, 30));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 260, 10));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 260, 10));

        jLabel2.setForeground(new java.awt.Color(53, 113, 117));
        jLabel2.setText("Nombre");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jTextNameEmail.setBorder(null);
        jTextNameEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextNameEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNameEmailActionPerformed(evt);
            }
        });
        jPanel2.add(jTextNameEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 260, 30));

        jButtonSingUp.setBackground(new java.awt.Color(102, 102, 102));
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
        jPanel2.add(jButtonSingUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 260, 40));

        jLabel3.setForeground(new java.awt.Color(53, 113, 117));
        jLabel3.setText("Email");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel4.setForeground(new java.awt.Color(53, 113, 117));
        jLabel4.setText("Contraseña");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jPasswordField2.setBorder(null);
        jPasswordField2.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 260, 30));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 260, 10));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("High Tower Text", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("REGISTRO");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/close_window_25px.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 30, 30));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 560));

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
                inicializarCuenta();
            } catch (IOException ex) {
                Logger.getLogger(FrameSignup.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrameSignup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Las contraseñas no concuerdan", "Error de tipeo", JOptionPane.DEFAULT_OPTION);
        }
    }//GEN-LAST:event_jButtonSingUpActionPerformed

    private void jTextNameEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNameEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNameEmailActionPerformed

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
    private javax.swing.JButton jButton1;
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
