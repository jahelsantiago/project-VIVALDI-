
package ProMange.Logic.Xml_clases;
import ED.ArrayList;
import java.io.File;
import java.io.IOException;
import ProMange.Logic.Maquina;
import ProMange.Logic.PedidoJ;
import ProMange.Logic.ProductoJ;
import java.io.Serializable;
//import java.util.ArrayList;

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

public class ProductoJ_excel implements Serializable{
    
    //String con las que operar con funciones ubicadas en archivos_gestor
    static String archivo_xml_productos = "basedatosProductos.xml";
    static String etiqueta_productos = "Producto"; // reemplazar por la variable Etiqueta_objeto_almacenar en la funcion de crear_xlm
    
    private static String obtenerNodoValor(String strTag, Element producto){
        Node nValor = (Node)producto.getElementsByTagName(strTag).item(0).getFirstChild();
        return nValor.getNodeValue();
    }
    
    public ED.ArrayList<ProductoJ> obtenerProductos(){
        ED.ArrayList<ProductoJ> lista_producto = new ED.ArrayList<>();
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosProductos.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //obtiene los nodos con la etiuqeta EmpleadosJ
            NodeList nodosProdcuto = doc.getElementsByTagName("Producto");  // linea a cambiar
            
            //Por cada nodo que se obtuvo se obtendran los datos
            //y se guardaran en un objeto tipo eompleadosJ
            
            for (int i = 0; i < nodosProdcuto.getLength(); i++) {
                Node productoJ = nodosProdcuto.item(i);
                if (productoJ.getNodeType() == Node.ELEMENT_NODE) {
                    Element unElemento = (Element) productoJ ;
                    ProductoJ objProducto = new ProductoJ();

                    objProducto.setReferencia(obtenerNodoValor("Referencia",unElemento));
                    objProducto.setNombre(obtenerNodoValor("Nombre",unElemento));
                    objProducto.setCategoria(obtenerNodoValor("Categoria",unElemento));
                    objProducto.setTiempo_elaboracion(Integer.parseInt(obtenerNodoValor("Tiempo_de_elaboracion",unElemento)));
                    objProducto.setCantidad_inventario(Integer.parseInt(obtenerNodoValor("Cantidad_inventario",unElemento)));
                    
                    lista_producto.add(lista_producto.size(),objProducto);
                }
                        
            }
        }catch(ParserConfigurationException parseE){
            JOptionPane.showMessageDialog(null,parseE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(SAXException saxE){
            JOptionPane.showMessageDialog(null,saxE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException ioE){
            JOptionPane.showMessageDialog(null,ioE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista_producto;
    }
    
    public void agregarProducto(ProductoJ nuevo){
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosProductos.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //Obtenemos el Nodo padre "basedatosEmpleados"
            Node nodoRaiz = doc.getDocumentElement();
            //Agregamos una nueva etiqueta al documento
            //Primero creamos la etiqueta
            Element nuevoProducto = doc.createElement("Producto");
            
           //Creamos sus etiquetas hijas y las llenamos
           
            Element nuevaReferencia = doc.createElement("Referencia");
            nuevaReferencia.setTextContent(String.valueOf(nuevo.getReferencia()));
 
            Element nuevoNombre = doc.createElement("Nombre");
            nuevoNombre.setTextContent(String.valueOf(nuevo.getNombre()));
            
            Element nuevoCategoria = doc.createElement("Categoria");
            nuevoCategoria.setTextContent(String.valueOf(nuevo.getCategoria()));  
            
            Element nuevoTiempoElaboracion = doc.createElement("Tiempo_de_elaboracion");
            nuevoTiempoElaboracion.setTextContent(String.valueOf(nuevo.getTiempo_elaboracion())); 
            
            Element nuevoCantidadInventario = doc.createElement("Cantidad_inventario");
            nuevoCantidadInventario.setTextContent(String.valueOf(nuevo.getCantidad_inventario())); 
            
            //Agragarmos las etiquetas hijas de la persona
            nuevoProducto.appendChild(nuevaReferencia);
            nuevoProducto.appendChild(nuevoNombre);
            nuevoProducto.appendChild(nuevoCategoria);
            nuevoProducto.appendChild(nuevoTiempoElaboracion);
            nuevoProducto.appendChild(nuevoCantidadInventario);
            
            //Relacionamos la persona con el nodo raiz del documento
            nodoRaiz.appendChild(nuevoProducto);
            
            //transformamos esta estructura de datos a un archivo xml
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml_archivos/basedatosProductos.xml"));
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
