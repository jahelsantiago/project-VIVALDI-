
import ED.LinkedList;
import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.Xml_clases.EmpleadoJ_excel;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_carpeta;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_xml;
import static ProMange.Logic.Xml_clases.archivos_gestor.eliminar_carpeta;


public class ejemplo {

    public static void main(String[] args) {
       crear_carpeta();
       crear_xml("EmpleadoJ","basedatosEmpleados.xml");
       LinkedList a = new LinkedList();
       EmpleadoJ_excel a1 = new EmpleadoJ_excel();
       EmpleadoJ b = new EmpleadoJ();
       a1.agregarEmpleado(b);
       a =  a1.obtenerEmpleados();  
       System.out.println(a.peek(0).getClass());
       eliminar_carpeta("basedatosEmpleados.xml");
    }
    
}
