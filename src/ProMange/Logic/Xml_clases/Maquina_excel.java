

package ProMange.Logic.Xml_clases;

import java.io.File;
import java.io.IOException;
import ProMange.Logic.Maquina;
import java.util.ArrayList;
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

public class Maquina_excel {
    
    //String con las que operar con funciones ubicadas en archivos_gestor
    static String archivo_xml_maquinas = "basedatosMaquina.xml";
    static String etiqueta_maquina = "Maquina"; // reemplazar por la variable Etiqueta_objeto_almacenar en la funcion de crear_xlm
    
     private static String obtenerNodoValor(String strTag, Element maquina){
        Node nValor = (Node)maquina.getElementsByTagName(strTag).item(0).getFirstChild();
        return nValor.getNodeValue();
    }
     
     public ED.ArrayList<Maquina> obtenerMaquinas(){
        ED.ArrayList<Maquina> lista_maquinas = new ED.ArrayList<>();
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosMaquina.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //obtiene los nodos con la etiuqeta EmpleadosJ
            NodeList nodosMaquinas = doc.getElementsByTagName("Maquina");  // linea a cambiar
            
            //Por cada nodo que se obtuvo se obtendran los datos
            //y se guardaran en un objeto tipo eompleadosJ
            
            for (int i = 0; i < nodosMaquinas.getLength(); i++) {
                Node empleadoJ = nodosMaquinas.item(i);
                if (empleadoJ.getNodeType() == Node.ELEMENT_NODE) {
                    Element unElemento = (Element) empleadoJ ;
                    
                    Maquina objMaquina = new Maquina();
                    
                    objMaquina.setSerial(Integer.parseInt(obtenerNodoValor("Serial",unElemento)));
                    if (obtenerNodoValor("Estado",unElemento) == "false") {
                        objMaquina.setEstado(false);
                    }else{
                        objMaquina.setEstado(true);
                    }
                    objMaquina.setOperario(Long.parseLong(obtenerNodoValor("Id_Operario",unElemento)));
                    lista_maquinas.add(lista_maquinas.size(),objMaquina);
                }
                        
            }
        }catch(ParserConfigurationException parseE){
            JOptionPane.showMessageDialog(null,parseE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(SAXException saxE){
            JOptionPane.showMessageDialog(null,saxE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException ioE){
            JOptionPane.showMessageDialog(null,ioE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista_maquinas;
    }
     
     public void agregarMaquina(Maquina nuevo){
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosMaquina.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //Obtenemos el Nodo padre "basedatosEmpleados"
            Node nodoRaiz = doc.getDocumentElement();
            //Agregamos una nueva etiqueta al documento
            //Primero creamos la etiqueta
            Element nuevaMaquina = doc.createElement("Maquina");
            
           //Creamos sus etiquetas hijas y las llenamos
            Element nuevoSerial = doc.createElement("Serial");
            nuevoSerial.setTextContent(String.valueOf(nuevo.getSerial()));
            
            //Estado
            Element nuevoEstado = doc.createElement("Estado"); 
             String estado = null;
            if (nuevo.getEstado()==true) {
                estado="true";
            }else{
                estado = "false";
            }
            nuevoEstado.setTextContent(estado);
            
            Element nuevoOperario = doc.createElement("Id_Operario");
            nuevoOperario.setTextContent(String.valueOf(nuevo.getOperario()));         
            
            //Agragarmos las etiquetas hijas de la persona
            nuevaMaquina.appendChild(nuevoSerial);
            nuevaMaquina.appendChild(nuevoEstado);
            nuevaMaquina.appendChild(nuevoOperario);
            
            //Relacionamos la persona con el nodo raiz del documento
            nodoRaiz.appendChild(nuevaMaquina);
            
            //transformamos esta estructura de datos a un archivo xml
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml_archivos/basedatosMaquina.xml"));
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
