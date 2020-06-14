//Codigo de ayuda obtenido de https://balusoft.wordpress.com/2011/07/12/usar-un-archivo-xml-como-una-base-de-datos-java-parte-12/

package ProMange.Logic.Xml_clases;

import java.io.File;
import java.io.IOException;
import ED.LinkedList;
import ProMange.Logic.EmpleadoJ;
//import java.util.ArrayList;
import ED.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.swing.JOptionPane;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class EmpleadoJ_excel <J> {
    
    //String con las que operar con funciones ubicadas en archivos_gestor
    static String archivo_xml_empleados = "basedatosEmpleados.xml";
    static String etiqueta_empleados = "EmpleadoJ"; // reemplazar por la variable Etiqueta_objeto_almacenar en la funcion de crear_xlm
    
    private static String obtenerNodoValor(String strTag, Element empleadoJ){
        Node nValor = (Node)empleadoJ.getElementsByTagName(strTag).item(0).getFirstChild();
        return nValor.getNodeValue();
    }
    
    public LinkedList obtenerEmpleadosLinked(){
        LinkedList<EmpleadoJ> lista_empleados = new LinkedList<>();
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosEmpleados.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //obtiene los nodos con la etiuqeta EmpleadosJ
            NodeList nodosEmpleados = doc.getElementsByTagName("EmpleadoJ");  // linea a cambiar
            
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
                    
                    if (obtenerNodoValor("Estado",unElemento) == "false") {
                        objEmpleadoJ.setEstado(false);
                    }else{
                        objEmpleadoJ.setEstado(true);
                    }
                    
                    if (obtenerNodoValor("Disponibilidad",unElemento) == "false") {
                        objEmpleadoJ.setDispo(false);
                    }else{
                        objEmpleadoJ.setDispo(true);
                    }
                    
                    objEmpleadoJ.setMaquina(Integer.parseInt(obtenerNodoValor("Maquina",unElemento)));
                    objEmpleadoJ.setId(Long.parseLong(obtenerNodoValor("Id",unElemento)));
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
    
    public ED.ArrayList<EmpleadoJ> obtenerEmpleados(){
        ED.ArrayList<EmpleadoJ> lista_empleados = new ED.ArrayList<>();
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosEmpleados.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //obtiene los nodos con la etiuqeta EmpleadosJ
            NodeList nodosEmpleados = doc.getElementsByTagName("EmpleadoJ");  // linea a cambiar
            
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
                    if (obtenerNodoValor("Estado",unElemento) == "false") {
                        objEmpleadoJ.setEstado(false);
                    }else{
                        objEmpleadoJ.setEstado(true);
                    }
                    
                    if (obtenerNodoValor("Disponibilidad",unElemento) == "false") {
                        objEmpleadoJ.setDispo(false);
                    }else{
                        objEmpleadoJ.setDispo(true);
                    }
                    
                    objEmpleadoJ.setMaquina(Integer.parseInt(obtenerNodoValor("Maquina",unElemento)));
                    objEmpleadoJ.setId(Long.parseLong(obtenerNodoValor("Id",unElemento)));
                    lista_empleados.add(lista_empleados.size(),objEmpleadoJ);
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
    
    public void agregarEmpleado(EmpleadoJ nuevo){
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosEmpleados.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //Obtenemos el Nodo padre "basedatosEmpleados"
            Node nodoRaiz = doc.getDocumentElement();
            //Agregamos una nueva etiqueta al documento
            //Primero creamos la etiqueta
            Element nuevoEmp = doc.createElement("EmpleadoJ");
            
           //Creamos sus etiquetas hijas y las llenamos
            Element nuevoNombre = doc.createElement("Nombre");
            nuevoNombre.setTextContent(nuevo.getNombre());
            
            Element nuevoApellido = doc.createElement("Apellido");
            nuevoApellido.setTextContent(nuevo.getApellido());
            
            Element nuevaFecha_nacimiento = doc.createElement("Fecha_nacimiento");
            nuevaFecha_nacimiento.setTextContent(nuevo.getFecha_nacimiento());
            
            //Estado
            Element nuevoEstado = doc.createElement("Estado"); 
             String estado = null;
            if (nuevo.getEstado()==true) {
                estado="true";
            }else{
                estado = "false";
            }
            nuevoEstado.setTextContent(estado);
            
            //Disponibilidad
            Element nuevoDispo = doc.createElement("Disponibilidad"); 
             String dispo = null;
            if (nuevo.getEstado()==true) {
                dispo="true";
            }else{
                dispo = "false";
            }
            nuevoDispo.setTextContent(dispo);
            
            Element nuevaMaquina = doc.createElement("Maquina");
            nuevaMaquina.setTextContent(String.valueOf(nuevo.getMaquina()));
            
            Element nuevoId = doc.createElement("Id");
            nuevoId.setTextContent(String.valueOf(nuevo.getId()));         
            
            //Agragarmos las etiquetas hijas de la persona
            nuevoEmp.appendChild(nuevoNombre);
            nuevoEmp.appendChild(nuevoApellido);
            nuevoEmp.appendChild(nuevaFecha_nacimiento);
            nuevoEmp.appendChild(nuevoEstado);
            nuevoEmp.appendChild(nuevaMaquina);
            nuevoEmp.appendChild(nuevoId);
            
            //Relacionamos la persona con el nodo raiz del documento
            nodoRaiz.appendChild(nuevoEmp);
            
            //transformamos esta estructura de datos a un archivo xml
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml_archivos/basedatosEmpleados.xml"));
            transformer.transform(source,result);
            
        }catch(ParserConfigurationException parseE){
            JOptionPane.showMessageDialog(null,parseE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(SAXException saxE){
            JOptionPane.showMessageDialog(null,saxE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException ioE){
            JOptionPane.showMessageDialog(null,ioE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(TransformerConfigurationException transE){
            JOptionPane.showMessageDialog(null,transE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(TransformerException transformE){
            JOptionPane.showMessageDialog(null,transformE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}