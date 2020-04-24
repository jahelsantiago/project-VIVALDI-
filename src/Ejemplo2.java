
import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.Xml_clases.EmpleadoJ_excel;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_carpeta;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_xml;
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
public class Ejemplo2 {
    public static void main(String[] args) {
        //crear_xml("EmpleadoJ","basedatosEmpleados.xml");

       
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
