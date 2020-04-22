
import ED.LinkedList;
import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.Xml_clases.EmpleadoJ_excel;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_carpeta;
import static ProMange.Logic.Xml_clases.archivos_gestor.crear_xml;
import static ProMange.Logic.Xml_clases.archivos_gestor.eliminar_carpeta;
import java.util.ArrayList;


public class ejemplo {
    
    static String archivo_xml_maquinas = "basedatosMaquina.xml";
    static String etiqueta_empleados = "Maquina"; // reemplazar por la variable Etiqueta_objeto_almacenar en la funcion de crear_xlm

    public static void main(String[] args) {
       //crear_carpeta();
       crear_xml("Pedido","basedatosPedidos.xml");
    }
    
}
