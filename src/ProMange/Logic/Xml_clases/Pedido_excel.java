
package ProMange.Logic.Xml_clases;

import ED.LinkedList;
import java.io.File;
import java.io.IOException;
import ProMange.Logic.Maquina;
import ProMange.Logic.PedidoJ;
import ProMange.Logic.ProductoJ;
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

public class Pedido_excel {
    static String archivo_xml_empleados = "basedatosPedidos.xml";
    static String etiqueta_empleados = "Pedido"; // reemplazar por la variable Etiqueta_objeto_almacenar en la funcion de crear_xlm
     
    public static LinkedList ListaProductos_linked (String cadenaProductos){
        
        //Guardamos todas las cadenas separadas por comas  (produc,canti,produc,canti,......)
         String[] ListaProductos = null;
         ListaProductos = cadenaProductos.split(",");   
        
         //Lista donde se guardaran una lista de prodcutos con una lista de cantidades
        LinkedList <LinkedList<String>> ejemploLista = new LinkedList<LinkedList<String>>();
        LinkedList <String> productos = new LinkedList<String>();
        LinkedList cantidad = new LinkedList();
        int j=0;
        
        for (int i = 0; i < (ListaProductos.length/2); i++) {
            String producto =ListaProductos[j];
            String cant =ListaProductos[j+1];
            productos.push(producto, i);
            cantidad.push(cant, i);
            j = j+2;
        }       

        ejemploLista.push(productos, 0);
        ejemploLista.push(cantidad, 1);
        
        return ejemploLista;
     }
    
    public static ArrayList ListaProductos (String cadenaProductos){
        
        //Guardamos todas las cadenas separadas por comas  (produc,canti,produc,canti,......)
         String[] ListaProductos = null;
         ListaProductos = cadenaProductos.split(",");   
        
         //Lista donde se guardaran una lista de prodcutos con una lista de cantidades
        ArrayList <ArrayList<String>> ejemploLista = new ArrayList<ArrayList<String>>();
        ArrayList<String> productos = new ArrayList<String>();
        ArrayList<String> cantidad = new ArrayList<String>();
        int j=0;
        
        for (int i = 0; i < (ListaProductos.length/2); i++) {
            String producto =ListaProductos[j];
            String cant =ListaProductos[j+1];
            productos.add(producto);
            cantidad.add(cant);
            j = j+2;
        }       

        ejemploLista.add(productos);
        ejemploLista.add(cantidad);
        
        return ejemploLista;
     }
     
    public static String ListaProductos_excel (ArrayList productos_guardar){
        
        //Guardamos todas las cadenas separadas por comas  (produc,canti,produc,canti,......) 
   
        ArrayList productos = (ArrayList) productos_guardar.get(0);
        ArrayList cantidad = (ArrayList) productos_guardar.get(1);
        
        String retorno = "";
        
        for (int i = 0; i < productos.size()-1; i++) {
            retorno = retorno + productos.get(i)+","+cantidad.get(i) +",";
        }
        return retorno = retorno +productos.get(productos.size()-1)+","+cantidad.get(cantidad.size()-1);
        
     }
    
    //sfdsdfasdfasdfsdfsa
    
    private static String obtenerNodoValor(String strTag, Element pedido){
        Node nValor = (Node)pedido.getElementsByTagName(strTag).item(0).getFirstChild();
        return nValor.getNodeValue();
    }
      
    public static ArrayList<PedidoJ> obtenerPedidos(){
        ArrayList<PedidoJ> lista_pedidos = new ArrayList<>();
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosPedidos.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //obtiene los nodos con la etiuqeta EmpleadosJ
            NodeList nodosPedido = doc.getElementsByTagName("Pedido");  // linea a cambiar
            
            //Por cada nodo que se obtuvo se obtendran los datos
            //y se guardaran en un objeto tipo eompleadosJ
            
            for (int i = 0; i < nodosPedido.getLength(); i++) {
                Node pedidosJ = nodosPedido.item(i);
                if (pedidosJ.getNodeType() == Node.ELEMENT_NODE) {
                    Element unElemento = (Element) pedidosJ ;
                    PedidoJ objPedido = new PedidoJ();
                        
                    objPedido.setId_pedido(Integer.parseInt(obtenerNodoValor("Id_pedido",unElemento)));
                    objPedido.setFecha_inicio(obtenerNodoValor("Fecha_inicio",unElemento));
                    objPedido.setFecha_entrega(obtenerNodoValor("Fecha_entrega",unElemento));
                    ArrayList productos_pedidos = ListaProductos(obtenerNodoValor("Productos_pedidos",unElemento));
                    objPedido.setProductos(productos_pedidos);
                    
                    lista_pedidos.add(objPedido);
                }
                        
            }
        }catch(ParserConfigurationException parseE){
            JOptionPane.showMessageDialog(null,parseE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(SAXException saxE){
            JOptionPane.showMessageDialog(null,saxE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException ioE){
            JOptionPane.showMessageDialog(null,ioE.getMessage(),""+"Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista_pedidos;
    }
  
    public void agregarPedido(PedidoJ nuevo){
        try{
            //Validar y leer nuestro XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File ("xml_archivos/basedatosPedidos.xml"));
            
            //preparar el archivo para obtener datos
            doc.getDocumentElement().normalize();
            
            //Obtenemos el Nodo padre "basedatosEmpleados"
            Node nodoRaiz = doc.getDocumentElement();
            //Agregamos una nueva etiqueta al documento
            //Primero creamos la etiqueta
            Element nuevaPedido = doc.createElement("Pedido");
            
           //Creamos sus etiquetas hijas y las llenamos
           
            Element nuevoId_pedido = doc.createElement("Id_pedido");
            nuevoId_pedido.setTextContent(String.valueOf(nuevo.getId_pedido()));
            
            Element nuevoFecha_inicio = doc.createElement("Fecha_inicio");
            nuevoFecha_inicio.setTextContent(String.valueOf(nuevo.getFecha_inicio()));
            
            Element nuevoFecha_entrega = doc.createElement("Fecha_entrega");
            nuevoFecha_entrega.setTextContent(String.valueOf(nuevo.getFecha_entrega()));
            
            Element nuevoListaPedidos = doc.createElement("Productos_pedidos");
            nuevoListaPedidos.setTextContent(ListaProductos_excel(nuevo.getProductos())); //
            
            //Agragarmos las etiquetas hijas de la persona
            nuevaPedido.appendChild(nuevoId_pedido);
            nuevaPedido.appendChild(nuevoFecha_inicio);
            nuevaPedido.appendChild(nuevoFecha_entrega);
            nuevaPedido.appendChild(nuevoListaPedidos);
            
            //Relacionamos la persona con el nodo raiz del documento
            nodoRaiz.appendChild(nuevaPedido);
            
            //transformamos esta estructura de datos a un archivo xml
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml_archivos/basedatosPedidos.xml"));
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
