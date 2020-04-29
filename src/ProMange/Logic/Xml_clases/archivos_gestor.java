
package ProMange.Logic.Xml_clases;

import java.io.File;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class archivos_gestor {
    
    public static void crear_carpeta(){
        String sCarpAct = System.getProperty("user.dir");
        File directorio = new File(sCarpAct+"//xml_archivos");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null,"La carpera que contiene a los archivos XML ha sido creada satisfactoriamente",""+"Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"La carpera que contiene a los archivos XML No ha sido creada satisfactoriamente",""+"Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    public static void eliminar_carpeta(String archivo_borrar){
        String sCarpAct = System.getProperty("user.dir");
        File archivo1 = new File(sCarpAct+"//xml_archivos"+"//"+archivo_borrar);
        if (archivo1.delete())
            JOptionPane.showMessageDialog(null,"La carpera "+archivo_borrar +" se a borrado exitosamente",""+"Exito", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null,"La carpera "+archivo_borrar +" No se ha eliminado",""+"Error", JOptionPane.WARNING_MESSAGE);
    }
    public static void crear_xml(String Etiqueta_objeto_almacenar,String nombre_archivo){
        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Creo un DOMImplementation
            DOMImplementation implementation = builder.getDOMImplementation();

            // Creo un documento con un elemento raiz
            Document documento = implementation.createDocument(null, Etiqueta_objeto_almacenar, null);
            documento.setXmlVersion("1.0");
            
            
            // Creo los elementos
            Element Inicio = documento.createElement("Inicio");
            
            documento.getDocumentElement().appendChild(Inicio);
            

            // Asocio el source con el Document
            Source source = new DOMSource(documento);
            // Creo el Result, indicado que fichero se va a crear
            Result result = new StreamResult(new File("xml_archivos//"+nombre_archivo));

            // Creo un transformer, se crea el fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
            //JOptionPane.showMessageDialog(null,"La carpera "+nombre_archivo +" se a creado exitosamente",""+"Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (ParserConfigurationException | TransformerException ex) {
            JOptionPane.showMessageDialog(null,"La carpera "+nombre_archivo +" No se a creado",""+"Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
