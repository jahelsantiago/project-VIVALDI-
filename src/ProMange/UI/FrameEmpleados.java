/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.UI;

import ED.ArrayList;
import ED.Pila;
import ProMange.Logic.EmpleadoJ;
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


public class FrameEmpleados extends javax.swing.JFrame {
    
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
    
    int i;
    
    
    //test de tiempo
    long time_start,time_end;
//    time_start = System.currentTimeMillis();
//    //funcion va aca
//    time_end = System.currentTimeMillis();
//    System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
    
    
    public FrameEmpleados() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("../Images/medium_40px.png")).getImage());
        this.setLocationRelativeTo(null);
        try{
            arr_empleado=leerFichero();
        }catch(Exception nullPoinerException){
            arr_empleado = new ED.ArrayList<>();
        }
        mouse_listen();
        //leer carpeta y configurar
        //arr_empleados = leer empleados
        mostrar_matriz();
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
    
    
    
    private void mostrar_matriz(){
        String matris[][] = new String[arr_empleado.size()][7];
        for(int i = 0; i<arr_empleado.size();i++){
            matris[i][0] = Long.toString(((EmpleadoJ)arr_empleado.get(i)).getId());
            matris[i][1] = ((EmpleadoJ)arr_empleado.get(i)).getNombre();
            matris[i][2] = ((EmpleadoJ)arr_empleado.get(i)).getApellido();
            matris[i][3] = Integer.toString(((EmpleadoJ)arr_empleado.get(i)).getMaquina());
            matris[i][4] = ((EmpleadoJ)arr_empleado.get(i)).getFecha_nacimiento();  

            //Estado
            if (((EmpleadoJ)arr_empleado.get(i)).getEstado() == true) {
                matris[i][5] = "Activo";
                //Disponibilidad
                if (((EmpleadoJ)arr_empleado.get(i)).getDispo() == true) {
                    matris[i][6] = "Disponible";
                }else{
                    matris[i][6] = "No disponible";
                }
            }else {
                matris[i][5] = "Despedido";
                matris[i][6] = "No disponible";
            }
            
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matris,
            new String [] {
                "ID", "Nombre", "Apellido", "Maquina","Fecha de nacimiento","Estado","Disponibilidad"
            }
        ));
                
    }
    
    private void mouse_listen(){
        jTable1.addMouseListener(new MouseAdapter() {
            DefaultTableModel model = new DefaultTableModel();
            
            public void mouseClicked(MouseEvent e){
                i = jTable1.getSelectedRow();
                
                ID = (jTable1.getValueAt(i, 0)).toString();
                jTextFieldID.setText(ID);
                
                Nombre = (jTable1.getValueAt(i, 1)).toString();
                jTextFieldNombre.setText(Nombre);
                
                apellido = (jTable1.getValueAt(i, 2)).toString();
                jTextFieldApellido.setText(apellido);
                
                maquina = (jTable1.getValueAt(i, 3)).toString();
                jTextFieldMaquina.setText(maquina);
                
                fecha_nacimiento = (jTable1.getValueAt(i, 4)).toString();
                jTextFieldFechaNacimiento.setText(fecha_nacimiento);
                        
                estado = (jTable1.getValueAt(i, 5)).toString();
                jEstado.setSelectedItem(estado);
                
                disponibilidad = (jTable1.getValueAt(i, 6)).toString();
                jDispo.setSelectedItem(disponibilidad);
                
            }
        });
    }
    
    private void clean(){
        this.jTextFieldFechaNacimiento.setText("");
        this.jTextFieldNombre.setText("");
        this.jTextFieldID.setText("");
        this.jTextFieldApellido.setText("");
        this.jTextFieldMaquina.setText("");
        this.jEstado.setSelectedItem("Despedido");
        this.jDispo.setSelectedItem("No disponible");
    }
    
    private void crear_empleado(){
        if(
            this.jTextFieldNombre.getText().isEmpty() 
            || this.jTextFieldID.getText().isEmpty()
            || this.jTextFieldApellido.getText().isEmpty()
            || this.jTextFieldFechaNacimiento.getText().isEmpty()
          )
        {
            JOptionPane.showMessageDialog(null, "Porfavor llenar todos los atributos", "Error de tipeo", JOptionPane.DEFAULT_OPTION);
            return;
        }
        
        // Estado
        boolean estado;
        if (jEstado.getSelectedItem().toString() == "Activo") {
            estado = true;
        }else{
            estado =false;
        }
        
        //Disponibilidad
        boolean dispo;
        if (jDispo.getSelectedItem().toString() == "Disponible") {
            dispo = true;
        }else{
            dispo =false;
        }

        EmpleadoJ empleado=new EmpleadoJ(
                jTextFieldNombre.getText(),
                jTextFieldApellido.getText(),
                jTextFieldFechaNacimiento.getText(),
                //jDateChooser1.getDateFormatString(),
                Long.parseLong(this.jTextFieldID.getText()),
                estado,
                dispo
        );                
        arr_empleado.add(arr_empleado.size(),empleado);                                
    }
    
    private void editar_empleado(){
        
        // Estado
        boolean estado;
        if (jEstado.getSelectedItem().toString() == "Activo") {
            estado = true;
        }else{
            estado =false;
        }
        
        //Disponibilidad
        boolean dispo;
        if (jDispo.getSelectedItem().toString() == "Disponible") {
            dispo = true;
        }else{
            dispo =false;
        }
        
        EmpleadoJ empleado=new EmpleadoJ(
                jTextFieldNombre.getText(),
                jTextFieldApellido.getText(),
                jTextFieldFechaNacimiento.getText(),
                Long.parseLong(this.jTextFieldID.getText()),
                estado,
                dispo
        );                
        arr_empleado.set(i, empleado);                                
    }
    
    private void eliminar_empleado(){               
        arr_empleado.remove(i);                    
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
                "ID", "Nombre", "Apellido", "Maquina","Fecha de nacimiento"
            }
        ));
        }else{
            JOptionPane.showMessageDialog(null, "Ningun empleados posee el Id ingresado", "No encontrado", JOptionPane.DEFAULT_OPTION);
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
        jPanel3 = new javax.swing.JPanel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldApellido = new javax.swing.JTextField();
        jTextFieldFechaNacimiento = new javax.swing.JTextField();
        jButtonEditar = new javax.swing.JButton();
        jButtonCrear = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jTextFieldMaquina = new javax.swing.JTextField();
        jTextFieldID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonEliminar1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jDispo = new javax.swing.JComboBox<>();
        jEstado = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

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
                "ID", "Nombre", "Apellido", "Maquina", "Fecha nacimiento"
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("NOMBRE"));
        jPanel3.add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 290, 60));

        jTextFieldApellido.setBorder(javax.swing.BorderFactory.createTitledBorder("APELLIDO"));
        jPanel3.add(jTextFieldApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 290, 60));

        jTextFieldFechaNacimiento.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHA DE NACIMIENTO"));
        jTextFieldFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaNacimientoActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 290, 60));
        jTextFieldFechaNacimiento.getAccessibleContext().setAccessibleName("");

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

        jTextFieldMaquina.setBorder(javax.swing.BorderFactory.createTitledBorder("MAQUINA"));
        jTextFieldMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMaquinaActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldMaquina, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 290, 60));

        jTextFieldID.setBorder(javax.swing.BorderFactory.createTitledBorder("ID"));
        jTextFieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 230, 60));

        jLabel3.setFont(new java.awt.Font("Poor Richard", 0, 24)); // NOI18N
        jLabel3.setText("DATOS");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 90, 60));

        jButtonEliminar1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/search1.png"))); // NOI18N
        jButtonEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 40, 60));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProMange/Images/icons8_stream_32px.png"))); // NOI18N
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 30, 30));

        jDispo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "No disponible" }));
        jDispo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDispoActionPerformed(evt);
            }
        });
        jPanel3.add(jDispo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 290, 60));
        jDispo.getAccessibleContext().setAccessibleName("DISPONIBILIDAD");
        jDispo.getAccessibleContext().setAccessibleDescription("");

        jEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Despedido" }));
        jEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEstadoActionPerformed(evt);
            }
        });
        jPanel3.add(jEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 290, 60));
        jEstado.getAccessibleContext().setAccessibleName("ESTADO");
        jEstado.getAccessibleContext().setAccessibleDescription("ESTADO");

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 300, 610));

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

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        //pila.push(arr_empleado);
        editar_empleado();
        mostrar_matriz();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        //pila.push(arr_empleado);
        crear_empleado();
        mostrar_matriz();
        clean();
    }//GEN-LAST:event_jButtonCrearActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        //pila.push(arr_empleado);
        //arr_empleado.remove(i);
        eliminar_empleado();
        mostrar_matriz();
        clean();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTextFieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDActionPerformed

    private void jButtonInventario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInventario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInventario1ActionPerformed

    private void jTextFieldFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFechaNacimientoActionPerformed

    private void jTextFieldMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMaquinaActionPerformed

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
            System.out.print("error al guarda");
        } catch (ClassNotFoundException ex) {            
            System.out.print("error al guardad");        
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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        crear_xml("Empleados","basedatosEmpleados.xml");
        for (int j = 0; j < arr_empleado.size(); j++) {
            empleado_excel.agregarEmpleado((EmpleadoJ)arr_empleado.get(j));
        } 
        JOptionPane.showMessageDialog(null, "Datos guardados archivo XML correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        mostrar_matriz();
        clean();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        try {
            this.arr_empleado = leerFichero();
            //JOptionPane.showMessageDialog(null, "Datos guardados con exito en xml", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {            
        } catch (ClassNotFoundException ex) {            
        }
        
        mostrar_matriz();
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jDispoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDispoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDispoActionPerformed

    private void jEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jEstadoActionPerformed


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
            java.util.logging.Logger.getLogger(FrameEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameEmpleados().setVisible(true);
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
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEliminar1;
    private javax.swing.JButton jButtonEmpleados;
    private javax.swing.JButton jButtonInventario;
    private javax.swing.JButton jButtonInventario1;
    private javax.swing.JButton jButtonPedidos;
    private javax.swing.JButton jButtonProcesos;
    private javax.swing.JComboBox<String> jDispo;
    private javax.swing.JComboBox<String> jEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldFechaNacimiento;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldMaquina;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
