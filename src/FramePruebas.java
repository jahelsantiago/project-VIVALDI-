
import ED.HeapSort;
import ProMange.Logic.OrdenJ;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ProBook
 */
public class FramePruebas extends javax.swing.JFrame {

    ED.ArrayList<OrdenJ> arr_ordenes = new ED.ArrayList<>();
    public FramePruebas() {
        initComponents();
//        try {
//            arr_ordenes = leerFicherOrdenes();
//            System.err.println("leido con exito");
//        } catch (Exception e) {
//            System.err.println("eerror en frame pedidos al optener ordenes");
//            arr_ordenes = new ED.ArrayList<>();
//        }
//        
//        HeapSort h = new HeapSort();
//        h.sort(arr_ordenes);
//        for (int i = 0; i < arr_ordenes.size(); i++) {
//            System.err.println(((OrdenJ)arr_ordenes.get(i)).getCantidad());
//        }
       
        
    }

    private static ED.ArrayList<OrdenJ> leerFicherOrdenes() throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/ordenes");
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        ED.ArrayList<OrdenJ> usuario = (ED.ArrayList<OrdenJ>) s.readObject();
        s.close();
        return usuario;
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FramePruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePruebas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    public void paint(Graphics g){
        super.paint(g);
        int px = 50;
        int py = 50;
        int maquinas = 6;
        int inicioX = px;
        int inicioY = py;
        int separacionY = py/2;
        int separacion = 230;
        
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                g.drawRect(px*i+inicioX, px*j+inicioY, 1, 0);
//            }
//            
//        }
        // matriz ejemplo donde estaran las maquinas
        
        
        // pintar maquinas 
        for (int i = 1; i <= maquinas; i++) {
            // pinta el cuadro de las maquinas
            g.drawRect(px, i*py + i*separacionY + py, px, py);
            //pinta el numero de la mauqina
            g.drawString("M"+Integer.toString(i), px + px/2, i*py + i*separacionY + py + separacionY);
            
            
        }
        
                
        g.setColor(Color.BLUE);
        g.drawLine(0, 70, 100, 70);        

    }
}
