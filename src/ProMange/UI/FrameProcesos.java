package ProMange.UI;


import ProMange.UI.*;
import ProMange.Logic.*;
import ProMange.Logic.ProductoJ;
import ProMange.Logic.Xml_clases.OrdenJ_excel;
import ProMange.Logic.Xml_clases.ProductoJ_excel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import ED.ArrayList;
import ED.MatrizDinamica;
import ED.MinHeap;
import ED.Pila;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FrameProcesos extends javax.swing.JFrame {            
    //tabla ordenes    
    String referencia_producto;
    String referencia_orden;
    String tiempo_total;
    String cantidad_total;
    String fecha_inicio;
    String estado;
    OrdenJ_excel ordenes_excel = new OrdenJ_excel();
    ED.ArrayList<OrdenJ> arr_ordenes = new ED.ArrayList<>();
    ED.ArrayList<Maquina> arr_maquinas = new ED.ArrayList<>();
    
    int j ,row ,column;
    
    //tabla maquinas
    MatrizDinamica mat_maquinas;
    MinHeap minheap;
    
    long time_start,time_end;
    int maquinas= 7;

    
    
    public FrameProcesos() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("../Images/medium_40px.png")).getImage());
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
            mat_maquinas =  new MatrizDinamica(this.arr_ordenes.size(), maquinas);
        }
                try{
            arr_maquinas = leerFicheroarrMaquinas();
        }catch (Exception e) {
            System.err.println("eerror en frame pedidos al optener arr maquinas");
            crear_arreglo_matriz();
        }

        
        
        
       mouse_listen_ordenes();
       mouse_listen_maquinas();
       mostrar_matriz_maquinas();
       mostrar_matriz_ordenes();
    }

    
    
    private void mostrar_matriz_maquinas(){
        // inicializamos el titulo
        String[] titulos = new String[maquinas];
        for (int k = 0; k < titulos.length; k++) {
            titulos[k] = "Maq " + Integer.toString(k);
        }
        // inicializamos la matriz
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            mat_maquinas.datos,
            titulos
        ));        
        //mat_maquinas.imprimirMatriz();
    }
    
    private void mostrar_matriz_ordenes(){
        String matris[][] = new String[arr_ordenes.size()][6];
        for(int i = 0; i<arr_ordenes.size();i++){
            matris[i][0] = ((OrdenJ)arr_ordenes.get(i)).getReferencia_orden();
            matris[i][1] = ((OrdenJ)arr_ordenes.get(i)).getReferencia_producto();
            matris[i][2] = Integer.toString(((OrdenJ)arr_ordenes.get(i)).getTiempo_elaboracion());
            matris[i][3] = Integer.toString(((OrdenJ)arr_ordenes.get(i)).getCantidad());
            matris[i][4] = ((OrdenJ)arr_ordenes.get(i)).getFecha_inicio();
            matris[i][5] = ((OrdenJ)arr_ordenes.get(i)).getEstado();                                                              
        }
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "REFERENCIA ORDEN","REFERENCIA PRODUCTO", "TIEMPO TOTAL", "CANTIDAD", "FECHA INICIO","ESTADO"
            }
        ));
                
    }
    

     private void mouse_listen_ordenes(){
        jTable2.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();
            
            public void mouseClicked(MouseEvent e){
                j = jTable2.getSelectedRow();
                                                
                referencia_orden = (jTable2.getValueAt(j, 0)).toString();                
                
                referencia_producto = (jTable2.getValueAt(j, 1)).toString();                
                
                tiempo_total = (jTable2.getValueAt(j, 2)).toString();
                
                cantidad_total = (jTable2.getValueAt(j, 3)).toString();
                
                fecha_inicio = (jTable2.getValueAt(j, 4)).toString();                                                        
                
                estado = (jTable2.getValueAt(j, 5)).toString();
                
                System.err.println(referencia_orden);
            }
        });
         
    }
     private void mouse_listen_maquinas(){         
        jTable1.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();
            
            public void mouseClicked(MouseEvent e){
                row = jTable1.getSelectedRow();
                column = jTable1.getSelectedColumn();
//                System.out.println(row);
//                System.out.println(column);
            }
        });
         
    }
    private void clean(){
        this.jTextFieldNoMaquina.setText("");        
    }
    
        
    private boolean condicion_igualdad(String comp, String busc){
       return comp.substring(0, busc.length()).equalsIgnoreCase(busc);
    }
    
    private static ArrayList<Maquina> leerFicheroarrMaquinas() throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/arr_maquinas_empleados");
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        ArrayList<Maquina>  usuario = (ArrayList<Maquina> ) s.readObject();
        s.close();
        return usuario;        
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
    private static void escribirFisheroMaquinas(MatrizDinamica usuario) throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/MAT_maquinas");
        FileOutputStream f =new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(usuario);
        s.close();
    }
    private static void escribirFisheroOrdenes(ED.ArrayList<OrdenJ> usuario) throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/ordenes");
        FileOutputStream f =new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(usuario);
        s.close();
    }
     
     private ED.ArrayList buscar_ordenes(){
        ED.ArrayList retorno = new ED.ArrayList();
        int h = 0;    
            for (int j = 0; j < arr_ordenes.size(); j++) {
                OrdenJ mostrar = (OrdenJ) arr_ordenes.get(j);
                if (jTextFieldBusca1.getText().length() <= mostrar.getReferencia_producto().length()) {
                    String produc = mostrar.getReferencia_producto().substring(0, jTextFieldBusca1.getText().length());
                    if (jTextFieldBusca1.getText().toLowerCase().equals(produc.toLowerCase())) {
                        retorno.add(h,mostrar);
                        h++;
                    }
                }
            }
        return retorno;
     }
     


    

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
        jPanel5 = new javax.swing.JPanel();
        jTextFieldNoMaquina = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonCrear = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextFieldBusca1 = new javax.swing.JTextField();
        jButtonCrear2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButtonAutoAsignar = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

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
        jButtonProcesos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 0, 204), new java.awt.Color(153, 0, 153), new java.awt.Color(102, 0, 102), new java.awt.Color(102, 0, 102)));
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
        jButtonInventario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_robot_26px_2.png"))); // NOI18N
        jButtonInventario1.setText("Maquinas");
        jButtonInventario1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 0, 204), new java.awt.Color(204, 0, 204), new java.awt.Color(51, 0, 51), new java.awt.Color(51, 0, 51)));
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

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldNoMaquina.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jTextFieldNoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNoMaquinaActionPerformed(evt);
            }
        });
        jPanel5.add(jTextFieldNoMaquina, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 60, 30));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel4.setText("NO. Maquina");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/delete_bin_25px.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jButtonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/create_order_25px.png"))); // NOI18N
        jButtonCrear.setText("Crear");
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 100, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, 130, 170));

        jLabel3.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel3.setText("DATOS ORDENES");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 230, 60));

        jTable2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 102, 255), null, new java.awt.Color(204, 51, 255)));
        jTable2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Orden", "Referencia Producto", "Tiempo elaboracon", "Cantidad Ordenada", "Fecha Inicio", "Estado"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable2.setRowHeight(25);
        jTable2.setSelectionBackground(new java.awt.Color(124, 85, 227));
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 390, 490));

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
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 40, 40));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_delete_bin_30px.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 30, 40));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_upload_link_document_32px.png"))); // NOI18N
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 40, 40));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_upload_file_32px_1.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 40));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 990, 40));

        jTextFieldBusca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBusca1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextFieldBusca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 260, 40));

        jButtonCrear2.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCrear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/search1.png"))); // NOI18N
        jButtonCrear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrear2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonCrear2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 40, 40));

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
                "M1", "M2", "M3", "M3", "M4"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(124, 85, 227));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, 400, 490));

        jLabel5.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel5.setText("MAQUINAS");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 120, 60));

        jButtonAutoAsignar.setText("AutoAsignar");
        jButtonAutoAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAutoAsignarActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonAutoAsignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, -1, -1));

        jButton7.setText("Visulizar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 170, 30));

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_stream_32px.png"))); // NOI18N
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 140, 30, 30));

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
        FramePedidos c = new FramePedidos();
        c.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonPedidosActionPerformed

    private void jButtonProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcesosActionPerformed
        FrameProcesos p = new FrameProcesos();
        this.setVisible(false);
        p.setVisible(true);
    }//GEN-LAST:event_jButtonProcesosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //        arr_productos = pila.pop();
        //        mostrar_matriz();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //guardar arreglo
        try {
            escribirFisheroOrdenes(arr_ordenes);
            escribirFisheroMaquinas(this.mat_maquinas);
            JOptionPane.showMessageDialog(null, "Datos guardados con exito", "Error de tipeo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.out.println("error al guardad arreglo");
        } catch (ClassNotFoundException ex) {
            System.out.println("error al guardad arreglo");            
        }
//        try {
//            escribirFisheroMaquinas(mat_maquinas);
//            JOptionPane.showMessageDialog(null, "Datos guardados con exito", "Error de tipeo", JOptionPane.INFORMATION_MESSAGE);
//        } catch (IOException ex) {
//            System.out.println("error al guardad arreglo");
//        } catch (ClassNotFoundException ex) {
//            System.out.println("error al guardad arreglo");            
//        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        if(this.estado.equalsIgnoreCase("Produccion")){
            JOptionPane.showMessageDialog(null, "La orden ya se encuentra asignada", "Error de tipeo", JOptionPane.INFORMATION_MESSAGE);
        }
        
        this.mat_maquinas.columnAppend(Integer.parseInt(this.jTextFieldNoMaquina.getText()),(String)this.referencia_orden, Integer.parseInt(this.tiempo_total));
        ((OrdenJ)this.arr_ordenes.get(j)).setEstado("Produccion");
        mostrar_matriz_maquinas();
        mostrar_matriz_ordenes();
    }//GEN-LAST:event_jButtonCrearActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
          this.mat_maquinas.eliminar(row, column);
          mostrar_matriz_maquinas();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTextFieldBusca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBusca1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBusca1ActionPerformed

    private void jButtonCrear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrear2ActionPerformed

    }//GEN-LAST:event_jButtonCrear2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        //JOptionPane.showMessageDialog(null, "Datos traidos correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextFieldNoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNoMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNoMaquinaActionPerformed

    private void jButtonAutoAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutoAsignarActionPerformed
          //this. mat_maquinas = new MatrizDinamica(this.arr_ordenes.size(), maquinas, arr_ordenes);
          this.mat_maquinas.autoAsignar(arr_ordenes);
          mostrar_matriz_ordenes();
          mostrar_matriz_maquinas();
    }//GEN-LAST:event_jButtonAutoAsignarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        FramePruebas c = new FramePruebas();
        c.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        mat_maquinas =  new MatrizDinamica(this.arr_ordenes.size(), maquinas);
        mostrar_matriz_maquinas();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButtonInventario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInventario1ActionPerformed
        FrameMaquinas f = new FrameMaquinas();
        this.setVisible(false);
        f.setVisible(true);
    }//GEN-LAST:event_jButtonInventario1ActionPerformed


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
            java.util.logging.Logger.getLogger(FrameProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameProcesos().setVisible(true);
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
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonAutoAsignar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonCrear2;
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
    private javax.swing.JTextField jTextFieldBusca1;
    private javax.swing.JTextField jTextFieldNoMaquina;
    // End of variables declaration//GEN-END:variables
}
