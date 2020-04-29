/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.UI;

import ED.ArrayList;
import ProMange.Logic.ProductoJ;
import ProMange.Logic.Xml_clases.ProductoJ_excel;
//import ProMange.Logic.GestorFisheros;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FrameInventario extends javax.swing.JFrame {
    
    String referencia;
    String nombre = "";
    String categoria ="";
    String tiempo_elab;
    String  cantidad;    
    
    ProductoJ_excel productos_excel = new ProductoJ_excel();    
    ED.ArrayList<ProductoJ> arr_productos = new ED.ArrayList<>();
    
    int i;
    long time_start,time_end;
    
    
    
    
    public FrameInventario(){
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("../Images/medium_40px.png")).getImage());
        this.setLocationRelativeTo(null);
        
        try {
            arr_productos = leerFichero();
            //leido con exito
        } catch (ClassNotFoundException ex) {            
            System.out.println("error al leer");
        } catch (IOException ex) {            
            System.out.println("error al leer2");
        }
        
        mouse_listen();               
        mostrar_matriz();
    }
    
    private static ArrayList<ProductoJ> leerFichero() throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/productos");
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        ArrayList<ProductoJ> usuario = (ArrayList<ProductoJ>) s.readObject();
        s.close();
        return usuario;
    }
    
    private static void escribirFishero(ArrayList<ProductoJ> usuario) throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/productos");
        FileOutputStream f =new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(usuario);
        s.close();
    }
    
    
    private void mostrar_matriz(){
//      arr_productos = productos_excel.obtenerEmpleados();
        String matris[][] = new String[arr_productos.size()][5];
        for(int i = 0; i<arr_productos.size();i++){
            matris[i][0] = ((ProductoJ)arr_productos.get(i)).getReferencia();
            matris[i][1] = ((ProductoJ)arr_productos.get(i)).getNombre();
            matris[i][2] = ((ProductoJ)arr_productos.get(i)).getCategoria();
            matris[i][3] = Integer.toString(((ProductoJ)arr_productos.get(i)).getTiempo_elaboracion());
            matris[i][4] = Integer.toString(((ProductoJ)arr_productos.get(i)).getCantidad_inventario());                                                              
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "REFERENCIA", "NOMBRE", "CATEGORIA", "TIEMPO ELABORACION","CANTIDAD"
            }
        ));
                
    }
    
    private void mouse_listen(){
        jTable1.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();
            
            public void mouseClicked(MouseEvent e){
                i = jTable1.getSelectedRow();
                
                referencia = (jTable1.getValueAt(i, 0)).toString();
                jTextFieldReferencia.setText(referencia);
                
                nombre = (jTable1.getValueAt(i, 1)).toString();
                jTextFieldNombre.setText(nombre);
                
                categoria = (jTable1.getValueAt(i, 2)).toString();
                jTextFieldCategoria.setText(categoria);
                
                tiempo_elab = (jTable1.getValueAt(i, 3)).toString();
                jTextFieldTiempoElaboracion.setText(tiempo_elab);
                
                cantidad = (jTable1.getValueAt(i, 4)).toString();
                jTextFieldCantidad.setText(cantidad);
                        

                
            }
        });
    }
    
    private void clean(){
        this.jTextFieldCantidad.setText("");
        this.jTextFieldNombre.setText("");
        this.jTextFieldReferencia.setText("");
        this.jTextFieldCategoria.setText("");
        this.jTextFieldTiempoElaboracion.setText("");
    }
    
    private void crear_producto(){
        ProductoJ producto=new ProductoJ(
                jTextFieldReferencia.getText(),
                jTextFieldNombre.getText(),
                jTextFieldCategoria.getText(),
                Integer.parseInt(this.jTextFieldTiempoElaboracion.getText()),
                Integer.parseInt(jTextFieldCantidad.getText())                
        );                
        arr_productos.add(arr_productos.size(),producto);                                
    }
    
    private void editar_producto(){
        ProductoJ producto=new ProductoJ(
                jTextFieldReferencia.getText(),
                jTextFieldNombre.getText(),
                jTextFieldCategoria.getText(),
                Integer.parseInt(this.jTextFieldTiempoElaboracion.getText()),
                Integer.parseInt(jTextFieldCantidad.getText())                
        );                
        arr_productos.set(i, producto);                                
    }
    
    private void eliminar_producto(){               
        arr_productos.remove(i);                                
    }

     private ED.ArrayList buscar_productos(){
        ED.ArrayList retorno = new ED.ArrayList();
        int h = 0;    
            for (int j = 0; j < arr_productos.size(); j++) {
                ProductoJ mostrar = (ProductoJ) arr_productos.get(j);
                if (jTextFieldReferencia.getText().length() <= mostrar.getReferencia().length()) {
                    String produc = mostrar.getReferencia().substring(0, jTextFieldReferencia.getText().length());
                    if (jTextFieldReferencia.getText().toLowerCase().equals(produc.toLowerCase())) {
                        retorno.add(h,mostrar);
                        h++;
                    }
                }
            }
        return retorno;
     }
     
 

     private void mostrar_matriz_resultado(ED.ArrayList resultado){
        if (resultado.size() != 0) {
        String matris[][] = new String[resultado.size()][5];
        for(int i = 0; i<resultado.size();i++){
            matris[i][0] = ((ProductoJ)resultado.get(i)).getReferencia();
            matris[i][1] = ((ProductoJ)resultado.get(i)).getNombre();
            matris[i][2] = ((ProductoJ)resultado.get(i)).getCategoria();
            matris[i][3] = Integer.toString(((ProductoJ)resultado.get(i)).getTiempo_elaboracion());
            matris[i][4] = Integer.toString(((ProductoJ)resultado.get(i)).getCantidad_inventario());                                                              
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "REFERENCIA", "NOMBRE", "CATEGORIA", "TIEMPO ELABORACION","CANTIDAD"
            }
        ));
         }else{
              JOptionPane.showMessageDialog(null, "No se encontro la referencia en el catalogo de productos", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
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
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldCategoria = new javax.swing.JTextField();
        jTextFieldCantidad = new javax.swing.JTextField();
        jButtonEditar = new javax.swing.JButton();
        jButtonCrear = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jTextFieldTiempoElaboracion = new javax.swing.JTextField();
        jTextFieldReferencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonEliminar1 = new javax.swing.JButton();

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
        jButtonPedidos.setBorderPainted(false);
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 630, 600));

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
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 30, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/undo_20px.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 30, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_save_30px_2.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 30, 40));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_microsoft_excel_30px.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 40, 40));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 990, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldNombre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jPanel3.add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 290, 40));

        jTextFieldCategoria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jPanel3.add(jTextFieldCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 290, 40));

        jTextFieldCantidad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jTextFieldCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 290, 50));

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/create_25px.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 570, -1, -1));

        jButtonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/create_order_25px.png"))); // NOI18N
        jButtonCrear.setText("Crear");
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, -1));

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/delete_bin_25px.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, -1, -1));

        jTextFieldTiempoElaboracion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jTextFieldTiempoElaboracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTiempoElaboracionActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldTiempoElaboracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 290, 50));

        jTextFieldReferencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51)));
        jTextFieldReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldReferenciaActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 290, 40));

        jLabel3.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel3.setText("DATOS INVENTARIO");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 230, 60));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel4.setText("CANTIDAD");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel5.setText("REFERENCIA");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel6.setText("NOMBRE");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel7.setText("CATEGORIA");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel8.setText("TEIMPO DE ELABORACION");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, -1));

        jButtonEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/search1.png"))); // NOI18N
        jButtonEliminar1.setText("Buscar productos por referencia");
        jButtonEliminar1.setActionCommand("Buscar productos por referencia");
        jButtonEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 300, 610));

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

    private void jTextFieldCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        time_start = System.currentTimeMillis();
        editar_producto();
        mostrar_matriz();
        time_end = System.currentTimeMillis();
        System.out.println("the task edit has taken "+ ( time_end - time_start ) +" milliseconds");
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        //pila.push(arr_productos);
        //    time_start = System.currentTimeMillis();
//    //funcion va aca
//    time_end = System.currentTimeMillis();
//    System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
        time_start = System.currentTimeMillis();
        if(
            jTextFieldNombre.getText().isEmpty() ||
            jTextFieldCategoria.getText().isEmpty() ||
            jTextFieldReferencia.getText().isEmpty() ||
            jTextFieldTiempoElaboracion.getText().isEmpty()                         
          )
        {
            JOptionPane.showMessageDialog(null, "Porfavor llenar todos los atributos", "Error de tipeo", JOptionPane.DEFAULT_OPTION);
            System.out.print("eraeeraer");
            return;
        }
        crear_producto();
        mostrar_matriz();
        clean();
        time_end = System.currentTimeMillis();
        System.out.println("the task create has taken "+ ( time_end - time_start ) +" milliseconds");
    }//GEN-LAST:event_jButtonCrearActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        //pila.push(arr_productos);
        //arr_productos.remove(i);
        time_start = System.currentTimeMillis();

        eliminar_producto();
        mostrar_matriz();
        clean();
        
        time_end = System.currentTimeMillis();
        System.out.println("the task edit has taken "+ ( time_end - time_start ) +" milliseconds");
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTextFieldTiempoElaboracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTiempoElaboracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTiempoElaboracionActionPerformed

    private void jTextFieldReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldReferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldReferenciaActionPerformed

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //guardad el xml
        this.arr_productos = productos_excel.obtenerProductos();
        mostrar_matriz();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //guardar arreglo
        try {
            escribirFishero(arr_productos);
            JOptionPane.showMessageDialog(null, "Datos guardados con exito", "Error de tipeo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.out.println("error al guardad arreglo");
        } catch (ClassNotFoundException ex) {
            System.out.println("error al guardad arreglo");            
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar1ActionPerformed
        ED.ArrayList resultado = buscar_productos();
        mostrar_matriz_resultado(resultado);
    }//GEN-LAST:event_jButtonEliminar1ActionPerformed


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
            java.util.logging.Logger.getLogger(FrameInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new FrameInventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEliminar1;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldCategoria;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldReferencia;
    private javax.swing.JTextField jTextFieldTiempoElaboracion;
    // End of variables declaration//GEN-END:variables
}
