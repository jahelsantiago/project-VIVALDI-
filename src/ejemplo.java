
import ED.LinkedList;
import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.Xml_clases.EmpleadoJ_excel;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_carpeta;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_xml;
import static ProMange.Logic.Xml_clases.archivos_gestor.eliminar_carpeta;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.ArrayList;


public class ejemplo {
    
    static String archivo_xml_maquinas = "basedatosMaquina.xml";
    static String etiqueta_empleados = "Maquina"; // reemplazar por la variable Etiqueta_objeto_almacenar en la funcion de crear_xlm

    public static void main(String[] args) {
       /*crear_carpeta();
       crear_xml("EmpleadoJ","basedatosEmpleados.xml");
       ED.ArrayList arreglo_empleados = new ED.ArrayList();             
       EmpleadoJ_excel a1 = new EmpleadoJ_excel();
       
       EmpleadoJ b = new EmpleadoJ();
       EmpleadoJ c = new EmpleadoJ();
       EmpleadoJ d = new EmpleadoJ();
       a1.agregarEmpleado(b);
       a1.agregarEmpleado(c);
       a1.agregarEmpleado(d);
       arreglo_empleados =  a1.obtenerEmpleados();  
       System.out.println(arreglo_empleados.get(2).toString());
       //eliminar_carpeta("basedatosEmpleados.xml");

       //crear_carpeta();
       crear_xml("Pedido","basedatosPedidos.xml");*/
      
    }
    
}
