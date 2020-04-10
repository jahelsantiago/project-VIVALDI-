/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.UI;

import ProMange.Logic.Empleado;
import java.util.ArrayList;

/**
 *
 * @author ProBook
 */
public class Empleados extends javax.swing.JFrame {

    ArrayList<Empleado> arr_empleado= new ArrayList<>();
    public Empleados() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldTurno = new javax.swing.JTextField();
        jTextFieldCorre = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextFieldTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(76, 40, 130));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("MS Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ManageApp");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 20, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 56, 179, 10));

        jButton1.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Inventario");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 220, 34));

        jButton3.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Empleados");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 153), new java.awt.Color(153, 0, 153), new java.awt.Color(51, 0, 51), new java.awt.Color(51, 0, 51)));
        jButton3.setContentAreaFilled(false);
        jButton3.setSelected(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 220, 34));

        jButton4.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Procesos");
        jButton4.setContentAreaFilled(false);
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 220, 34));

        jButton5.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Pedidos");
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 220, 34));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 510));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(224, 176, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBackground(new java.awt.Color(204, 255, 204));
        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 102, 255), null, new java.awt.Color(204, 51, 255)));
        jTable1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Turno", "Telefono", "correo"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 320, 510));
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, 30));

        jTextField1.setText("Buscar...");
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 290, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 0, 102), new java.awt.Color(51, 0, 51)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Nombre");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 30));

        jLabel4.setText("Turno");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 50, 30));

        jLabel5.setText("correo");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 70, 30));
        jPanel3.add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 220, 30));
        jPanel3.add(jTextFieldTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 220, 30));

        jTextFieldCorre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCorreActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldCorre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 220, 30));

        jButton6.setText("jButton6");
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jButton7.setText("Crear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, -1, -1));

        jTextFieldTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTelefonoActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 220, 30));

        jLabel6.setText("Telefono");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 70, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 330, 260));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 680, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Pedidos c=new Pedidos();
        c.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Empleado empleado=new Empleado(
                jTextFieldNombre.getText(),
                jTextFieldTurno.getText(),
                jTextFieldTelefono.getText(),
                jTextFieldCorre.getText()
        );
        arr_empleado.add(empleado);
        mostrar_matriz();
        this.jTextFieldCorre.setText("");
        this.jTextFieldNombre.setText("");
        this.jTextFieldTelefono.setText("");
        this.jTextFieldTurno.setText("");
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextFieldTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTelefonoActionPerformed

    private void jTextFieldCorreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCorreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCorreActionPerformed

    public void mostrar_matriz(){
        String matris[][] = new String[arr_empleado.size()][4];
        for(int i = 0; i<arr_empleado.size();i++){
            matris[i][0] = arr_empleado.get(i).getNombre();
            matris[i][1] = arr_empleado.get(i).getTurno();
            matris[i][2] = arr_empleado.get(i).getTelefono();
            matris[i][3] = arr_empleado.get(i).getCorreo();
            
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "Nombre", "Turno", "telefono", "correo"
            }
        ));
                
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
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldCorre;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JTextField jTextFieldTurno;
    // End of variables declaration//GEN-END:variables
}