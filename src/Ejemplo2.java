
import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.ProductoJ;
import ProMange.Logic.Supervisor;
import ProMange.Logic.Xml_clases.EmpleadoJ_excel;
import ProMange.Logic.Xml_clases.ProductoJ_excel;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_carpeta;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_xml;
import clases_guardado.empleado_serial;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ProBook
 */
public class Ejemplo2 implements Serializable{
    
    public static void escribirFishero(empleado_serial usuario) throws IOException, ClassNotFoundException {
		File file=new File("xml_archivos/empleados");
		FileOutputStream f =new FileOutputStream(file);
		ObjectOutputStream s = new ObjectOutputStream(f);
		s.writeObject(usuario);
		s.close();
	}
    
    public static empleado_serial leerFichero() throws IOException, ClassNotFoundException {
		File file=new File("xml_archivos/empleados");
		FileInputStream f = new FileInputStream(file);
                ObjectInputStream s = new ObjectInputStream(f);
        	empleado_serial usuario = (empleado_serial) s.readObject();
	        s.close();
		return usuario;
	}
    
    
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
         int i = 5;
         long time_start,time_end;
         empleado_serial emp;
         if(false){
             time_start = System.currentTimeMillis();
            ProductoJ_excel productos_excel = new ProductoJ_excel();    
            ArrayList<ProductoJ> arr_productos = productos_excel.obtenerProductos();
            empleado_serial empa= new empleado_serial(arr_productos);
             time_end = System.currentTimeMillis();
            System.out.println("the task edit has taken "+ ( time_end - time_start ) +" milliseconds");
         }
         
         //escribir .jav
        if(false){
            time_start = System.currentTimeMillis();
            escribirFishero(emp);
            time_end = System.currentTimeMillis();
            System.out.println("the task edit has taken "+ ( time_end - time_start ) +" milliseconds");
         }
         // leer .jav
         if(true){
            time_start = System.currentTimeMillis();
            empleado_serial gat = leerFichero();
            time_end = System.currentTimeMillis();
            System.out.println("the task edit has taken "+ ( time_end - time_start ) +" milliseconds");
         }
         
         
         //escribir xml
         if(false){
            time_start = System.currentTimeMillis();
            crear_xml("ProductoJ","basedatosProductos.xml");
            arr_productos.remove(i);
            for (int j = 0; j<arr_productos.size();j++) {
                productos_excel.agregarProducto(arr_productos.get(i));
            }        
            time_end = System.currentTimeMillis();
            System.out.println("the task edit has taken "+ ( time_end - time_start ) +" milliseconds");
         }
        
       
    }
        
    
       public static void imprimir_empleados(){
        EmpleadoJ_excel a = new EmpleadoJ_excel();

        ArrayList b  = a.obtenerEmpleados();
        EmpleadoJ mostrar = new EmpleadoJ();
        
        for (int i = 0; i < b.size(); i++) {
            
            mostrar = (EmpleadoJ) b.get(i);
            
            System.out.println("Nombre " + mostrar.getNombre());
            System.out.println("Apellido " + mostrar.getApellido());
            System.out.println("Fecha nacimineto " + mostrar.getFecha_nacimiento());
            System.out.println("Estado " + mostrar.getEstado());
            System.out.println("Maquina " + mostrar.getMaquina());
            System.out.println("Id " + mostrar.getId());
            System.out.println("");
        }
    }
    
    private static void a(){
        crear_carpeta();
        //crear_xml("EmpleadoJ","basedatosEmpleados.xml");
        EmpleadoJ_excel empleado_excel = new EmpleadoJ_excel();
        
        EmpleadoJ b = new EmpleadoJ("ja", "ime","1900/24/03", 0, true);
        EmpleadoJ c = new EmpleadoJ("ka", "ime","1900/24/03", 0, true);
        EmpleadoJ d = new EmpleadoJ("la", "ime","1900/24/03", 0, true);
        
        
        empleado_excel.agregarEmpleado(b);
        empleado_excel.agregarEmpleado(c);
    }
}
