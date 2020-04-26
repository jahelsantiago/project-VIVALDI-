/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.UI;

import ED.Pila;
import ProMange.Logic.*;
import ProMange.Logic.ProductoJ;
import ProMange.Logic.Xml_clases.EmpleadoJ_excel;
import ProMange.Logic.Xml_clases.OrdenJ_excel;
import ProMange.Logic.Xml_clases.ProductoJ_excel;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_xml;
//import ProMange.Logic.GestorFisheros;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.crypto.AEADBadTagException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FramePedidos extends javax.swing.JFrame {
    
    
    //tabal productos
    String referencia;
    String nombre = "";
    String categoria ="";
    String tiempo_elab;
    String  cantidad;    
    Pila<ArrayList<EmpleadoJ>> pila = new Pila<>();
    ProductoJ_excel productos_excel = new ProductoJ_excel();    
    ArrayList<ProductoJ> arr_productos;
    int i;
    
    //tabla ordenes
    String referencia_orden;
    String tiempo_total;
    String cantidad_total;
    String fecha_inicio;
    String estado;
    OrdenJ_excel ordenes_excel = new OrdenJ_excel();
    ArrayList<OrdenJ> arr_ordenes;
    int j;
    
    
    long time_start,time_end;
    
    
    
    public FramePedidos() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("../Images/medium_40px.png")).getImage());
        this.setLocationRelativeTo(null);
        try {
            arr_productos = productos_excel.obtenerProductos();
        } catch (Exception e) {
            System.err.println("error en productors");
            arr_productos = new ArrayList<>();
        }
        try {
            arr_ordenes = ordenes_excel.obtenerOrden();
        } catch (Exception e) {
            System.err.println("error en ordenes");
            arr_ordenes = new ArrayList<>();
        }
        mouse_listen();
        
        //leer carpeta y configurar
        //arr_empleados = leer empleados
        mostrar_matriz();
        mostrar_matriz_ordenes();
    }
    
    
    
    public void mostrar_matriz(){
//      arr_productos = productos_excel.obtenerEmpleados();
        String matris[][] = new String[arr_productos.size()][5];
        for(int i = 0; i<arr_productos.size();i++){
            matris[i][0] = arr_productos.get(i).getReferencia();
            matris[i][1] = arr_productos.get(i).getNombre();
            matris[i][2] = arr_productos.get(i).getCategoria();
            matris[i][3] = Integer.toString(arr_productos.get(i).getTiempo_elaboracion());
            matris[i][4] = Integer.toString(arr_productos.get(i).getCantidad_inventario());                                                              
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "REFERENCIA", "NOMBRE", "CATEGORIA", "TIEMPO ELABORACION","CANTIDAD"
            }
        ));
                
    }
    public void mostrar_matriz_ordenes(){
//      arr_productos = productos_excel.obtenerEmpleados();
        String matris[][] = new String[arr_ordenes.size()][5];
        for(int i = 0; i<arr_ordenes.size();i++){
            matris[i][0] = arr_ordenes.get(i).getReferencia_producto();
            matris[i][1] = Integer.toString(arr_ordenes.get(i).getTiempo_elaboracion());
            matris[i][2] = Integer.toString(arr_ordenes.get(i).getCantidad());
            matris[i][3] = arr_ordenes.get(i).getFecha_inicio();
            matris[i][4] = arr_ordenes.get(i).getEstado();                                                              
        }
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "REFERENCIA", "TIEMPO TOTAL", "CANTIDAD", "FECHA INICIP","ESTADO"
            }
        ));
                
    }
    
    private void mouse_listen(){
        jTable1.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();
            
            public void mouseClicked(MouseEvent e){
                i = jTable1.getSelectedRow();
                
                referencia = (jTable1.getValueAt(i, 0)).toString();
                                
                nombre = (jTable1.getValueAt(i, 1)).toString();
                
                categoria = (jTable1.getValueAt(i, 2)).toString();                
                
                tiempo_elab = (jTable1.getValueAt(i, 3)).toString();                
                
                cantidad = (jTable1.getValueAt(i, 4)).toString();
                
                        
            }
        });
    }
     private void mouse_listen_ordenes(){
        jTable1.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();
            
            public void mouseClicked(MouseEvent e){
                j = jTable2.getSelectedRow();
                
                referencia_orden = (jTable1.getValueAt(i, 0)).toString();                
                
                tiempo_total = (jTable1.getValueAt(i, 1)).toString();
                
                cantidad_total = (jTable1.getValueAt(i, 2)).toString();
                
                fecha_inicio = (jTable1.getValueAt(i, 3)).toString();                                                        
                
                estado = (jTable1.getValueAt(i, 4)).toString();                                                        
            }
        });
    }
    private void clean(){
        this.jTextFieldCantidad.setText("");
    }
    
    private void crear_Orden(){
        if(
          jTextFieldCantidad.getText().isEmpty()              
          ){
            JOptionPane.showMessageDialog(null, "Porfavor llenar todos los atributos", "Error de tipeo", JOptionPane.DEFAULT_OPTION);
            System.out.print("eraeeraer");
            return;
        }
        OrdenJ orden=new OrdenJ (
                referencia,
                Integer.parseInt(tiempo_elab),
                Integer.parseInt(jTextFieldCantidad.getText())
        );        
        ordenes_excel.agregarOrden(orden);        
        arr_ordenes.add(orden);                                
    }
    
    
    private void eliminar_orden(){       
        crear_xml("OrdenJ","basedatosOrdenes.xml"); 
        arr_ordenes.remove(i);
        for (int i = 0; i<arr_ordenes.size();i++) {
            ordenes_excel.agregarOrden(arr_ordenes.get(i));
        }
                                
    }
    
    private boolean condicion_igualdad(String comp, String busc){
       return comp.substring(0, busc.length()).equalsIgnoreCase(busc);
    }

    public void buscar_inventario(String busc){
        ArrayList<String> arr_pord_com = new ArrayList<>();
        for (int k = 0; k < 10; k++) {
            if(condicion_igualdad(arr_productos.get(i).getNombre(), busc)){
                arr_pord_com.add(arr_productos.get(i).getNombre());
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonInventario = new javax.swing.JButton();
        jButtonEmpleados = new javax.swing.JButton();
        jButtonProcesos = new javax.swing.JButton();
        jButtonPedidos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonInventario1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonCrear = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldBusca = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("MS Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ManageApp");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 20, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 56, 179, 10));

        jButtonInventario.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jButtonInventario.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/choice_25px.png"))); // NOI18N
        jButtonInventario.setText("Inventario");
        jButtonInventario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 153), new java.awt.Color(153, 0, 153), new java.awt.Color(51, 0, 51), new java.awt.Color(51, 0, 51)));
        jButtonInventario.setBorderPainted(false);
        jButtonInventario.setContentAreaFilled(false);
        jButtonInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInventarioActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 220, 34));

        jButtonEmpleados.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jButtonEmpleados.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/worker_25px.png"))); // NOI18N
        jButtonEmpleados.setText("Empleados");
        jButtonEmpleados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 153), new java.awt.Color(153, 0, 153), new java.awt.Color(51, 0, 51), new java.awt.Color(51, 0, 51)));
        jButtonEmpleados.setBorderPainted(false);
        jButtonEmpleados.setContentAreaFilled(false);
        jButtonEmpleados.setSelected(true);
        jButtonEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmpleadosActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 220, 34));

        jButtonProcesos.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jButtonProcesos.setForeground(new java.awt.Color(255, 255, 255));
        jButtonProcesos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/factory_25px.png"))); // NOI18N
        jButtonProcesos.setText("Procesos");
        jButtonProcesos.setContentAreaFilled(false);
        jButtonProcesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcesosActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonProcesos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 220, 34));

        jButtonPedidos.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jButtonPedidos.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/cash_on_delivery_25px.png"))); // NOI18N
        jButtonPedidos.setText("Pedidos");
        jButtonPedidos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 153), new java.awt.Color(153, 0, 153), new java.awt.Color(51, 0, 51), new java.awt.Color(51, 0, 51)));
        jButtonPedidos.setContentAreaFilled(false);
        jButtonPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPedidosActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 220, 34));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/copyright_20px.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 610, -1, -1));

        jButtonInventario1.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jButtonInventario1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInventario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/services_25px.png"))); // NOI18N
        jButtonInventario1.setText("Otros");
        jButtonInventario1.setBorderPainted(false);
        jButtonInventario1.setContentAreaFilled(false);
        jButtonInventario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInventario1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonInventario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 220, 34));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 670));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(224, 176, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 102, 255), null, new java.awt.Color(204, 51, 255)));
        jTable1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Referencia", "Nombre", "Categoria", "Tiempo Elaboracion", "Cantidad"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(124, 85, 227));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 400, 490));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/close_window_25px.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 20, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/undo_20px.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 30, 20));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 990, 40));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldCantidad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jPanel5.add(jTextFieldCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 60, 30));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel4.setText("CANTIDAD");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jButtonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/create_order_25px.png"))); // NOI18N
        jButtonCrear.setText("Crear");
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 100, -1));

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/delete_bin_25px.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 130, 200));

        jLabel3.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel3.setText("DATOS ORDENES");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 230, 60));

        jTable2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 102, 255), null, new java.awt.Color(204, 51, 255)));
        jTable2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Referencia", "Tiempo elaboracon", "Cantidad Ordenada", "Fecha Inicio", "Estado"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable2.setRowHeight(25);
        jTable2.setSelectionBackground(new java.awt.Color(124, 85, 227));
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 390, 560));

        jLabel5.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel5.setText("DATOS INVENTARIO");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 230, 60));

        jTextFieldBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscaActionPerformed(evt);
            }
        });
        jPanel2.add(jTextFieldBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 250, 40));

        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 50, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 980, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmpleadosActionPerformed
        FrameEmpleados i = new FrameEmpleados();
        this.setVisible(false);
        i.setVisible(true);
    }//GEN-LAST:event_jButtonEmpleadosActionPerformed

    private void jButtonInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInventarioActionPerformed
        FrameInventario cv = new FrameInventario();
        cv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonInventarioActionPerformed

    private void jButtonPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPedidosActionPerformed
        FramePedidos c=new FramePedidos();
        c.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonPedidosActionPerformed

    private void jButtonProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcesosActionPerformed
        FrameProcesos p = new FrameProcesos();
        this.setVisible(false);
        p.setVisible(true);
    }//GEN-LAST:event_jButtonProcesosActionPerformed

    private void jButtonInventario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInventario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInventario1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        arr_productos = pila.pop();
//        mostrar_matriz();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        time_start = System.currentTimeMillis();
        crear_Orden();
        mostrar_matriz_ordenes();
        time_end = System.currentTimeMillis();
        System.out.println("the task edit orden has taken "+ ( time_end - time_start ) +" milliseconds");
    }//GEN-LAST:event_jButtonCrearActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        time_start = System.currentTimeMillis();
        eliminar_orden();
        mostrar_matriz_ordenes();
        time_end = System.currentTimeMillis();
        System.out.println("the task eliminar orden has taken "+ ( time_end - time_start ) +" milliseconds");
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTextFieldBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscaActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
                                        
       
    }//GEN-LAST:event_jButtonBuscarActionPerformed


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
            java.util.logging.Logger.getLogger(FramePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEmpleados;
    private javax.swing.JButton jButtonInventario;
    private javax.swing.JButton jButtonInventario1;
    private javax.swing.JButton jButtonPedidos;
    private javax.swing.JButton jButtonProcesos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldBusca;
    private javax.swing.JTextField jTextFieldCantidad;
    // End of variables declaration//GEN-END:variables
}
