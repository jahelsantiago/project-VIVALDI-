/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.Logic.Xml_clases;

import ED.ArrayList;
import ProMange.Logic.ProductoJ;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author ProBook
 */
public class Gestor_ficheros implements Serializable{
    public static void escribirFisheroProducto(ArrayList<ProductoJ> usuario) throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/empleados");
        FileOutputStream f =new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(usuario);
        s.close();
    }
    
    public static ArrayList<ProductoJ> leerFicheroProducto() throws IOException, ClassNotFoundException {
        File file=new File("xml_archivos/empleados");
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        ArrayList<ProductoJ> usuario = (ArrayList<ProductoJ>) s.readObject();
        s.close();
        return usuario;
    }
    
    
}
