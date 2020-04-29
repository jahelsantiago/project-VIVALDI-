
import ED.ArrayList;
import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.ProductoJ;
import ProMange.Logic.Xml_clases.ProductoJ_excel;
import clases_guardado.empleado_serial;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ProBook
 */
public class test {
    
    public static void escribirFishero(ArrayList<ProductoJ> usuario) throws IOException, ClassNotFoundException {
            File file=new File("xml_archivos/productos");
            FileOutputStream f =new FileOutputStream(file);
            ObjectOutputStream s = new ObjectOutputStream(f);
            s.writeObject(usuario);
            s.close();
    }
    
    public static ArrayList<ProductoJ> leerFichero() throws IOException, ClassNotFoundException {
		File file=new File("xml_archivos/productos");
		FileInputStream f = new FileInputStream(file);
                ObjectInputStream s = new ObjectInputStream(f);
        	ArrayList<ProductoJ> usuario = (ArrayList<ProductoJ>) s.readObject();
	        s.close();
		return usuario;
	}
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> arr = new ArrayList();
        arr.add(0, "gato");
        arr.add(0, "perro");
        arr.add(arr.size(), "araña");
        arr.get(0);
        System.err.println(arr.size());
        System.err.println(arr.get(2));
        
        
        arr.set(2, "chino");
        System.err.println(arr.get(2));
        
        
          long time_start,time_end;
          ArrayList<ProductoJ> arr_productos = new ArrayList<>();
          ProductoJ_excel productos_excel = new ProductoJ_excel();  
          
          
          //leer xml
          time_start = System.currentTimeMillis();
          arr_productos = productos_excel.obtenerProductos();          
          time_end = System.currentTimeMillis();
          System.out.println("the task edit has taken "+ ( time_end - time_start ) +" milliseconds");
//          
//escribir 
          time_start = System.currentTimeMillis();
            escribirFishero(arr_productos);
            time_end = System.currentTimeMillis();
            System.out.println("the task edit has taken "+ ( time_end - time_start ) +" milliseconds");
            

//leer 
//           time_start = System.currentTimeMillis();
//            arr_productos = leerFichero();
//            time_end = System.currentTimeMillis();
//            System.out.println("the task edit has taken "+ ( time_end - time_start ) +" milliseconds");
//            
//test
//            arr_productos.get(6).getClass();
//            ProductoJ prod = (ProductoJ)arr_productos.get(9);
//            System.out.println(prod.getCategoria()); 
          
          
    }
    

    
}
