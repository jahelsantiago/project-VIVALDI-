package ProMange.UI;



import ED.MatrizDinamica;
import ED.MinHeap;
import ProMange.Logic.OrdenJ;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;


public class FramePruebas extends javax.swing.JFrame {
    ED.ArrayList<OrdenJ> arr_ordenes = new ED.ArrayList<>();  
    MatrizDinamica mat_maquinas;
    Hashtable contenedor;
    MinHeap minheap;
    
    public FramePruebas() {
        initComponents();
        this.setLocationRelativeTo(null);
         try {
            arr_ordenes = leerFicherOrdenes();
        } catch (Exception e) {
            System.err.println("eerror en frame pedidos al optener ordenes");
            arr_ordenes = new ED.ArrayList<>();
        }
        
        try {
            mat_maquinas = leerFicheroMaquinas();
        } catch (Exception e) {
            System.err.println("eerror en frame pedidos al optener maquinas");
            mat_maquinas =  new MatrizDinamica(this.arr_ordenes.size(), 6);
        }

        crear_hash();

        
        mat_maquinas.imprimirMatriz();
        
        
        
                   
                   

    }
    
    private static MatrizDinamica leerFicheroMaquinas() throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/MAT_maquinas");
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        MatrizDinamica usuario = (MatrizDinamica) s.readObject();
        s.close();
        return usuario;
        
    }        
    private static ED.ArrayList<OrdenJ> leerFicherOrdenes() throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/ordenes");
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        ED.ArrayList<OrdenJ> usuario = (ED.ArrayList<OrdenJ>) s.readObject();
        s.close();
        return usuario;
    }

    private OrdenJ buscar(ED.ArrayList arr, String ref){
        for(int i = 0; i<arr.size(); i++){
            if((((OrdenJ)arr.get(i)).getReferencia_orden()).equals(ref)){
                return ((OrdenJ)arr.get(i));
            }
        }
        return new OrdenJ();
    }
    
    private void crear_hash(){
        contenedor=new Hashtable();
        for (OrdenJ arr_ordene : arr_ordenes) {
            contenedor.put(arr_ordene.referencia_orden, arr_ordene);                    
        }
    }
    

            


    public void paint(Graphics g){
        super.paint(g);
        int px = 50;
        int py = 50;
        int inicioX = 2*px;
        int inicioY = 3*py;
        int x_panel = 1150;
        int y_panle = 700;
        g.drawRect(inicioX+20,inicioY-10,1000,550);
        int i = 0;
        int px_x = inicioX/2;
        int py_y = inicioY;
        for (int j = inicioX+20; j < x_panel; j+=px) {
            g.drawLine(j, inicioY-10, j, y_panle-10);
        }
        
        int j = 0;
        int w;
        while(i < this.mat_maquinas.profundidad_filas.length){
            px_x = inicioX/2;
            g.setColor(Color.GRAY);
            g.fillRect(px_x, py_y, px, py);
            g.setColor(Color.white);
            g.fillRect(px_x+5, py_y+5, px-10, py-10);
            g.setColor(Color.BLACK);
            g.drawString("M "+Integer.toString(i), px_x+20, py_y+20);
            px_x = 20+inicioX;
            
            j = 0;
            //System.out.println(this.mat_maquinas.profundidad_filas[i]);            
            while(j<this.mat_maquinas.profundidad_filas[i]){
                OrdenJ temp = (OrdenJ)(contenedor.get(mat_maquinas.datos[j][i]));
                w = temp.tiempo_elaboracion/60;
                g.fillRect(px_x, py_y, w*px, py);
                g.setColor(Color.WHITE);
                g.setColor(Color.getHSBColor((int) (Math.random() * 50) + 1, (int) (Math.random() * 50) + 1, (int) (Math.random() * 50) + 1));
                g.fillRect(px_x+5, py_y+5,(w*px)-10, py-10);
                g.setColor(Color.BLACK);
                g.drawString(temp.referencia_orden, px_x+20, py_y+20);
                px_x += w*px;
                px_x += 5;
                j+=1;                    
            }
            py_y += 75;            
            i++;
        }
        g.setColor(Color.BLUE);         
    }  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/close_window_25px.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 40, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 40));
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1200, -1));
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 50, -1, 650));
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 650));
        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 690, 1200, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel3.setText("PROCESOS DE MAQUINARIA");
        jPanel7.add(jLabel3);

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1180, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 380, 20));

        jButton1.setText("Volver A Procesos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 80, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FrameProcesos c = new FrameProcesos();
        this.setVisible(false);
        c.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
    
}
