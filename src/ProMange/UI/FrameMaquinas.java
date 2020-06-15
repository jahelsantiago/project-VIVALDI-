/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.UI;

import ED.ArrayList;
import ED.MatrizDinamica;
import ED.Pila;
import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.Maquina;
import ProMange.Logic.OrdenJ;
import ProMange.Logic.ProductoJ;

import ProMange.Logic.Xml_clases.EmpleadoJ_excel;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_xml;

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

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FrameMaquinas extends javax.swing.JFrame {    
    String ID;
    String Nombre = "";
    String apellido ="";
    String maquina ="";
    String fecha_nacimiento;
    String estado;
    String disponibilidad;
    
    Pila<ArrayList<EmpleadoJ>> pila = new Pila<>();
    EmpleadoJ_excel empleado_excel = new EmpleadoJ_excel();    
    ED.ArrayList<EmpleadoJ> arr_empleado = new ArrayList<>();
    ED.ArrayList<Maquina> arr_maquinas = new ArrayList<>();
    MatrizDinamica mat_maquinas;
    int i,j;
    
    

    long time_start,time_end;

    
    public FrameMaquinas(){
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("../Images/medium_40px.png")).getImage());
        this.setLocationRelativeTo(null);
        try{
            arr_empleado=leerFichero();
        }catch(Exception nullPoinerException){
            arr_empleado = new ED.ArrayList<>();
        }        
        try{
            mat_maquinas = leerFicheroMaquinas();
        }catch (Exception e) {
            System.err.println("eerror en frame pedidos al optener maquinas");
            mat_maquinas =  new MatrizDinamica(2,7);
        }
        try{
            arr_maquinas = leerFicheroarrMaquinas();
        }catch (Exception e) {
            System.err.println("eerror en frame pedidos al optener arr maquinas");
            crear_arreglo_matriz();
        }
        mouse_listen();
        mouse_listen_maquinas();
        mostrar_matriz();
        mostrar_matriz_maquinas();
        mostrar_arr_maquinas();
    }
    
    private void crear_arreglo_matriz(){
        this.arr_maquinas = new ArrayList<>();
        for(int i = 0; i<this.mat_maquinas.columnasMax;i++){
            Maquina temp = new Maquina(i, "", true, 0);
            arr_maquinas.add(i, temp);
        }
    }
    
    private void mostrar_matriz_maquinas(){
        // inicializamos el titulo
        String[] titulos = new String[this.mat_maquinas.columnasMax];
        for (int k = 0; k < titulos.length; k++) {
            titulos[k] = "Maq " + Integer.toString(k);
        }
        // inicializamos la matriz
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            mat_maquinas.datos,
            titulos
        ));                
    }
                            
    private static ED.ArrayList<EmpleadoJ> leerFichero() throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/empleados");
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        ED.ArrayList<EmpleadoJ> usuario = (ED.ArrayList<EmpleadoJ>) s.readObject();
        s.close();
        return usuario;
    }
    
    private static void escribirFishero(ED.ArrayList<EmpleadoJ> usuario) throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/empleados");
        FileOutputStream f =new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(usuario);
        s.close();
    }
    
    private static MatrizDinamica leerFicheroMaquinas() throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/MAT_maquinas_empleados");
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        MatrizDinamica usuario = (MatrizDinamica) s.readObject();
        s.close();
        return usuario;
        
    }   
    
    private static void escribirFisheroMaquinas(MatrizDinamica usuario) throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/MAT_maquinas_empleados");
        FileOutputStream f =new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(usuario);
        s.close();
    }
    
    private static ArrayList<Maquina> leerFicheroarrMaquinas() throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/arr_maquinas_empleados");
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        ArrayList<Maquina>  usuario = (ArrayList<Maquina> ) s.readObject();
        s.close();
        return usuario;        
    }   
    
    private static void escribirFisheroarrMaquinas(ArrayList<Maquina>  usuario) throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/arr_maquinas_empleados");
        FileOutputStream f =new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(usuario);
        s.close();
    }
    
    private void mostrar_matriz(){
        String matris[][] = new String[arr_empleado.size()][5];
        int i=0;
        int j=0;
        while(i<arr_empleado.size()){
            matris[i][0] = Long.toString(((EmpleadoJ)arr_empleado.get(i)).getId());
            matris[i][1] = ((EmpleadoJ)arr_empleado.get(i)).getNombre()+" "+((EmpleadoJ)arr_empleado.get(i)).getApellido();            
            matris[i][2] = Integer.toString(((EmpleadoJ)arr_empleado.get(i)).getMaquina());

            //Estado
            if (((EmpleadoJ)arr_empleado.get(i)).getDispo() == true) {
                matris[i][3] = "Disponible";
            }else{
                matris[i][3] = "No disponible";
            }      
            i++;                        
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "ID", "Nombre", "Maquina","Disponibilidad"
            }
        ));                
    }
    private void mostrar_arr_maquinas(){
        String matris[][] = new String[arr_maquinas.size()][4];
        for(int i = 0; i<arr_maquinas.size();i++){
            matris[i][0] = Integer.toString(((Maquina)arr_maquinas.get(i)).getSerial()); // serial maquina
            matris[i][1] = Long.toString(((Maquina)arr_maquinas.get(i)).getOperario()); //id operario
            matris[i][2] = ((Maquina)arr_maquinas.get(i)).getNombre(); // nombre operario           
            if (((Maquina)arr_maquinas.get(i)).getEstado()== true) {
                matris[i][3] = "Disponible";
            }else{
                matris[i][3] = "No disponible";
            }  
            
        }
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "Mauina", "ID operario", "Nombre", "Disponibilidad"
            }
        ));                
    }
    
    private void matriz_resultado_busqueda(ED.ArrayList resultado){
        if (resultado.size() != 0) {
            String matris[][] = new String[resultado.size()][5];
        for(int i = 0; i<resultado.size();i++){
            matris[i][0] = Long.toString(((EmpleadoJ)resultado.get(i)).getId());
            matris[i][1] = ((EmpleadoJ)resultado.get(i)).getNombre();
            matris[i][2] = ((EmpleadoJ)resultado.get(i)).getApellido();
            matris[i][3] = Integer.toString(((EmpleadoJ)resultado.get(i)).getMaquina());
            matris[i][4] = ((EmpleadoJ)resultado.get(i)).getFecha_nacimiento();                                                              
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "Maquina", "Operario", "Nombre", "Estado"
            }
        ));
        }else{
            JOptionPane.showMessageDialog(null, "Ningun empleados posee el Id ingresado", "No encontrado", JOptionPane.DEFAULT_OPTION);
        }                
    }
    
    private void mouse_listen(){
        jTable1.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();
            
            public void mouseClicked(MouseEvent e){
                i = jTable1.getSelectedRow();
                
                ID = (jTable1.getValueAt(i, 0)).toString();
                                
                Nombre = (jTable1.getValueAt(i, 1)).toString();                
                                                
                maquina = (jTable1.getValueAt(i, 2)).toString();                
                                  
                estado = (jTable1.getValueAt(i, 3)).toString();                
                                
                System.out.println(i);
            }
        });
    }
    
     private void mouse_listen_maquinas(){
        jTable3.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();
            
            public void mouseClicked(MouseEvent e){
                j = jTable3.getSelectedRow();                                                                
                System.err.println(j);
            }
        });
         
    }
    
    private void clean(){
        
    }
      
   private boolean esNumero(String a){
       try {
            Long.parseLong(a);
            return true;
	} catch (NumberFormatException exNum){
             JOptionPane.showMessageDialog(null, "Porfavor ingresar solo numeros en el campo", "Error de tipeo", JOptionPane.DEFAULT_OPTION);
             return false;
        }
   }
   
   //Busqueda 1, versatil pero no rapida

    private ED.ArrayList buscar_empleados(){
        
        
        ED.ArrayList retorno = new ED.ArrayList();
        if(esNumero(jTextFieldID.getText())){
            int h=0;
            for (int j = 0; j < arr_empleado.size(); j++) {
                EmpleadoJ mostrar = (EmpleadoJ) arr_empleado.get(j);
                if (jTextFieldID.getText().length() <= Long.toString(mostrar.getId()).length()) {
                    String Id_emp_ac = Long.toString(mostrar.getId()).substring(0, jTextFieldID.getText().length());
                    if (jTextFieldID.getText().equals(Id_emp_ac)) {
                        retorno.add(h,mostrar);
                        h++;
                    }
                }
            }
            return retorno;
        }else{
            JOptionPane.showMessageDialog(null, "Porfavor ingresar solo numeros en el campo", "Error en la busqueda", JOptionPane.WARNING_MESSAGE);
             return retorno;
        }
        
 
    }
    


//Busqueda 2
    
    private ED.ArbolAVL<EmpleadoJ> arbol_emp(ED.ArrayList a){
         ED.ArbolAVL<EmpleadoJ> nue = new ED.ArbolAVL<EmpleadoJ>();
         for (int j = 0; j < a.size(); j++) {
             nue.insert((EmpleadoJ) a.get(j));
         }
         return nue;
     }
    
    
    
    private EmpleadoJ buscar_empleado_2(ED.ArbolAVL a){
        EmpleadoJ b = new EmpleadoJ();
        EmpleadoJ c = new EmpleadoJ();
        b.setId(Long.parseLong(this.jTextFieldID.getText()));
        if (a.contains(b)) {
            c = (EmpleadoJ) a.encontrar(b);
            return c;         
        }else{
            c = null;
            return c;
        } 
     }
    
    private void matriz_resultado_busqueda2(EmpleadoJ a){
        if (a != null) {
            String matris[][] = new String[1][7];
        
            matris[0][0] = Long.toString(a.getId());
            matris[0][1] = a.getNombre();
            matris[0][2] = a.getApellido();
            matris[0][3] = Integer.toString(a.getMaquina());
            matris[0][4] = a.getFecha_nacimiento();
            
            //Estado
            if (a.getEstado() == true) {
                matris[0][5] = "Activo";
            }else{
                matris[0][5] = "Despedido";
            }
            
            //Disponibilidad
            if (a.getDispo() == true) {
            matris[0][6] = "Disponible";
            }else{
            matris[0][6] = "No disponible";
            }
        
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "ID", "Nombre", "Apellido", "Maquina","Fecha de nacimiento","Estado","Disponibilidad"
            }
        ));
        }else{
            JOptionPane.showMessageDialog(null, "Ningun empleados posee el Id ingresado", "No encontrado", JOptionPane.DEFAULT_OPTION);
        }
                
    }
    

    
//    private void guardar(){
//        try {
//            GestorFisheros.escribirFisheroEmpleados(arr_empleado);
//        } catch (IOException ex) {
//            System.err.println("error al escribir fishero");
//        } catch (ClassNotFoundException ex) {
//            System.err.println("error al escribir fishero");
//        }
//    }
    
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
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextFieldID = new javax.swing.JTextField();
        jButtonEliminar1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonAñadir = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();

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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Maquina", "Disponibilidad"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(124, 85, 227));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 380, 440));

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

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_upload_file_32px_1.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 40));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_upload_link_document_32px.png"))); // NOI18N
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 40, 40));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 990, 40));

        jTextFieldID.setBorder(javax.swing.BorderFactory.createTitledBorder("ID"));
        jTextFieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDActionPerformed(evt);
            }
        });
        jPanel2.add(jTextFieldID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 340, 60));

        jButtonEliminar1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/search1.png"))); // NOI18N
        jButtonEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 40, 50));

        jLabel3.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel3.setText("EMPLEADOS");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 160, 60));

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
                "M1", "M2", "M3", "M3", "M4"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable2.setRowHeight(25);
        jTable2.setSelectionBackground(new java.awt.Color(124, 85, 227));
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 410, 80));

        jLabel5.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel5.setText("MAQUINAS");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 120, 60));

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_delete_26px.png"))); // NOI18N
        jPanel2.add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 30, 30));

        jButtonAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_add_new_26px.png"))); // NOI18N
        jButtonAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAñadirActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 30, 30));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 410, -1));

        jTable3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 102, 255), null, new java.awt.Color(204, 51, 255)));
        jTable3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Maquina", "Operario", "Nombre", "Estado"
            }
        ));
        jTable3.setFocusable(false);
        jTable3.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable3.setRowHeight(25);
        jTable3.setSelectionBackground(new java.awt.Color(124, 85, 227));
        jTable3.setShowVerticalLines(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 410, 400));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_sign_out_26px.png"))); // NOI18N
        jButton7.setText("Asignar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 980, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmpleadosActionPerformed
        FrameInventario i = new FrameInventario();
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
        FrameMaquinas f = new FrameMaquinas();
        this.setVisible(false);
        f.setVisible(true);
    }//GEN-LAST:event_jButtonInventario1ActionPerformed

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
            escribirFishero(arr_empleado);
            JOptionPane.showMessageDialog(null, "Datos guardados con exito", "Error de tipeo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {           
            System.out.print("error al guardar");
        } catch (ClassNotFoundException ex) {            
            System.out.print("error al guardar");        
        }
        try {
            escribirFisheroarrMaquinas(arr_maquinas);            
        } catch (IOException ex) {           
            System.out.print("error al guardar");
        } catch (ClassNotFoundException ex) {            
            System.out.print("error al guardar");        
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //leemos el xml
        this.arr_empleado = empleado_excel.obtenerEmpleados();
        mostrar_matriz();
        System.out.println("bien");
        JOptionPane.showMessageDialog(null, "Datos del archivo XML leidos con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.arr_empleado = new ED.ArrayList<>();
        mostrar_matriz();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        crear_xml("Empleados","basedatosEmpleados.xml");
        for (int j = 0; j < arr_empleado.size(); j++) {
            empleado_excel.agregarEmpleado((EmpleadoJ)arr_empleado.get(j));
        } 
        JOptionPane.showMessageDialog(null, "Datos guardados archivo XML correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        try {
            this.arr_empleado = leerFichero();
            //JOptionPane.showMessageDialog(null, "Datos guardados con exito en xml", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {            
        } catch (ClassNotFoundException ex) {            
        }
        
        mostrar_matriz();
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextFieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDActionPerformed

    private void jButtonEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar1ActionPerformed
        if(jTextFieldID.getText().equals("")){
            mostrar_matriz();
            return;
        }
        /*ED.ArrayList resultado = buscar_empleados();
        matriz_resultado_busqueda(resultado);*/
        ED.ArrayList emp_actuales = arr_empleado;
        ED.ArbolAVL emp = arbol_emp(emp_actuales);
        matriz_resultado_busqueda2(buscar_empleado_2(emp));
    }//GEN-LAST:event_jButtonEliminar1ActionPerformed

    private void jButtonAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAñadirActionPerformed
        this.mat_maquinas.insertarColumna();
        Maquina temp = new Maquina(arr_maquinas.size(), "", true, 0);
        arr_maquinas.add(arr_maquinas.size(), temp);        
        mostrar_matriz_maquinas();
        mostrar_arr_maquinas();
    }//GEN-LAST:event_jButtonAñadirActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ((Maquina)this.arr_maquinas.get(j)).setNombre(Nombre);
        ((Maquina)this.arr_maquinas.get(j)).setOperario(Long.parseLong(ID));
        ((EmpleadoJ)this.arr_empleado.get(i)).setDispo(false);
        mostrar_arr_maquinas();
        mostrar_matriz();

    }//GEN-LAST:event_jButton7ActionPerformed


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
            java.util.logging.Logger.getLogger(FrameMaquinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMaquinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMaquinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMaquinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrameMaquinas().setVisible(true);
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
    private javax.swing.JButton jButtonAñadir;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextFieldID;
    // End of variables declaration//GEN-END:variables
}
