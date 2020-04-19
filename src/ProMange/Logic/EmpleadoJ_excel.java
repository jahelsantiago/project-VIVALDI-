
//Codigo de ayuda obtenido de https://balusoft.wordpress.com/2011/07/12/usar-un-archivo-xml-como-una-base-de-datos-java-parte-12/

package ProMange.Logic;

import java.io.File;
import java.io.IOException;
import ED.LinkedList;
import ProManageJ.UI.EmpleadosJ;
import ProMange.Logic.EmpleadoJ;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.swing.JOptionPane;


public class EmpleadoJ_excel <J> {
    
    private static String obtenerNodoValor(String strTag, Element empleadoJ){
        Node nValor = (Node)empleadoJ.getElementsByTagName(strTag).item(0).getFirstChild();
        return nValor.getNodeValue();
    }
    
    public LinkedList obtenerEmpleados(){
        LinkedList<EmpleadoJ> lista_empleados = new LinkedList<>();
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xmlsrc/basedatosEmpleados.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //obtiene los nodos con la etiuqeta EmpleadosJ
            NodeList nodosEmpleados = doc.getElementsByTagName("EmpleadosJ");  // linea a cambiar
            
            //Por cada nodo que se obtuvo se obtendran los datos
            //y se guardaran en un objeto tipo eompleadosJ
            
            for (int i = 0; i < nodosEmpleados.getLength(); i++) {
                Node empleadoJ = nodosEmpleados.item(i);
                if (empleadoJ.getNodeType() == Node.ELEMENT_NODE) {
                    Element unElemento = (Element) empleadoJ ;
                    
                    EmpleadoJ objEmpleadoJ = new EmpleadoJ();
                    objEmpleadoJ.setNombre(obtenerNodoValor("Nombre",unElemento));
                    objEmpleadoJ.setApellido(obtenerNodoValor("Apellido",unElemento));
                    objEmpleadoJ.setFecha_nacimiento((J)obtenerNodoValor("Fecha_nacimiento",unElemento));
                    objEmpleadoJ.setPassword(obtenerNodoValor("Password",unElemento));
                    if (obtenerNodoValor("Estado",unElemento) == "false") {
                        objEmpleadoJ.setEstado(false);
                    }else{
                        objEmpleadoJ.setEstado(true);
                    }
                    objEmpleadoJ.setMaquina(Integer.parseInt(obtenerNodoValor("Maquina",unElemento)));
                    lista_empleados.push(objEmpleadoJ, i);
                }
                        
            }
        }catch(ParserConfigurationException parseE){
            JOptionPane.showMessageDialog(null,parseE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(SAXException saxE){
            JOptionPane.showMessageDialog(null,saxE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException ioE){
            JOptionPane.showMessageDialog(null,ioE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista_empleados;
    }
}
