package ProMange.Logic.Xml_clases;
import java.io.File;
import java.io.IOException;
import ProMange.Logic.Maquina;
import ProMange.Logic.OrdenJ;
import ProMange.Logic.PedidoJ;
import ProMange.Logic.ProductoJ;
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

public class OrdenJ_excel {
    
    //String con las que operar con funciones ubicadas en archivos_gestor
    static String archivo_xml_productos = "basedatosOrdenes.xml";
    static String etiqueta_productos = "Orden"; // reemplazar por la variable Etiqueta_objeto_almacenar en la funcion de crear_xlm
    
    private static String obtenerNodoValor(String strTag, Element orden){
        Node nValor = (Node)orden.getElementsByTagName(strTag).item(0).getFirstChild();
        return nValor.getNodeValue();
    }
    
    public ED.ArrayList<OrdenJ> obtenerOrden(){
        ED.ArrayList<OrdenJ> lista_Orden = new ED.ArrayList<>();
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosOrdenes.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //obtiene los nodos con la etiuqeta EmpleadosJ
            NodeList nodosOrden = doc.getElementsByTagName("Orden");  // linea a cambiar
            
            //Por cada nodo que se obtuvo se obtendran los datos
            //y se guardaran en un objeto tipo eompleadosJ
            
            for (int i = 0; i < nodosOrden.getLength(); i++) {
                Node OrdenJ = nodosOrden.item(i);
                if (OrdenJ.getNodeType() == Node.ELEMENT_NODE) {
                    Element unElemento = (Element) OrdenJ ;
                    OrdenJ objOrden = new OrdenJ();

                    objOrden.setReferencia_producto(obtenerNodoValor("Referencia_producto",unElemento));
                    objOrden.setFecha_inicio(obtenerNodoValor("Fecha_inicio",unElemento));
                    objOrden.setEstado(obtenerNodoValor("Estado",unElemento));
                    objOrden.setTiempo_elaboracion(Integer.parseInt(obtenerNodoValor("Tiempo_de_elaboracion",unElemento)));                    
                    objOrden.setCantidad(Integer.parseInt(obtenerNodoValor("Cantidad",unElemento)));                    
                    
                    lista_Orden.add(lista_Orden.size(),objOrden);
                }
                        
            }
        }catch(ParserConfigurationException parseE){
            JOptionPane.showMessageDialog(null,parseE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(SAXException saxE){
            JOptionPane.showMessageDialog(null,saxE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException ioE){
            JOptionPane.showMessageDialog(null,ioE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista_Orden;
    }
    
    public void agregarOrden(OrdenJ nuevo){
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosOrdenes.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //Obtenemos el Nodo padre "basedatosEmpleados"
            Node nodoRaiz = doc.getDocumentElement();
            //Agregamos una nueva etiqueta al documento
            //Primero creamos la etiqueta
            Element nuevoProducto = doc.createElement("Orden");
            
           //Creamos sus etiquetas hijas y las llenamos
           
            Element nuevaReferencia = doc.createElement("Referencia_producto");
            nuevaReferencia.setTextContent(String.valueOf(nuevo.getReferencia_producto()));
 
            Element nuevoNombre = doc.createElement("Fecha_inicio");
            nuevoNombre.setTextContent(String.valueOf(nuevo.getFecha_inicio()));
            
            Element nuevoCategoria = doc.createElement("Cantidad");
            nuevoCategoria.setTextContent(String.valueOf(nuevo.getCantidad()));  
            
            Element nuevoTiempoElaboracion = doc.createElement("Tiempo_de_elaboracion");
            nuevoTiempoElaboracion.setTextContent(String.valueOf(nuevo.getTiempo_elaboracion())); 
            
            Element nuevoEstado = doc.createElement("Estado");
            nuevoEstado.setTextContent(String.valueOf(nuevo.getEstado())); 
            
            

            
            //Agragarmos las etiquetas hijas de la persona
            nuevoProducto.appendChild(nuevaReferencia);
            nuevoProducto.appendChild(nuevoNombre);
            nuevoProducto.appendChild(nuevoCategoria);
            nuevoProducto.appendChild(nuevoTiempoElaboracion);
            nuevoProducto.appendChild(nuevoEstado);

            
            //Relacionamos la persona con el nodo raiz del documento
            nodoRaiz.appendChild(nuevoProducto);
            
            //transformamos esta estructura de datos a un archivo xml
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml_archivos/basedatosOrdenes.xml"));
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
