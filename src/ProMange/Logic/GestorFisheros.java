/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ProBook
 */
public class GestorFisheros implements Serializable{
    
    public static void escribirFisheroEmpleados( ArrayList<Empleado> arr_empleado) throws IOException, ClassNotFoundException {
		File file=new File("Arreglo_empleados");
		FileOutputStream f =new FileOutputStream(file);
		ObjectOutputStream s = new ObjectOutputStream(f);
		s.writeObject(arr_empleado);
		s.close();
	}
	
	public static ArrayList<Empleado> leerFicheroEmpleados() throws IOException, ClassNotFoundException {
		File file=new File("Arreglo_empleados");
		FileInputStream f = new FileInputStream(file);
                ObjectInputStream s = new ObjectInputStream(f);
        	ArrayList<Empleado> arr_empleado = (ArrayList<Empleado>) s.readObject();
	        s.close();
		return arr_empleado;
	}
    public static void escribirFisheroInventario( ArrayList<Producto> arr_Producto) throws IOException, ClassNotFoundException {
		File file=new File("Arreglo_inventario");
		FileOutputStream f =new FileOutputStream(file);
		ObjectOutputStream s = new ObjectOutputStream(f);
		s.writeObject(arr_Producto);
		s.close();
	}
	
	public static ArrayList<Producto> leerFicheroInventario() throws IOException, ClassNotFoundException {
		File file=new File("Arreglo_inventario");
		FileInputStream f = new FileInputStream(file);
                ObjectInputStream s = new ObjectInputStream(f);
        	ArrayList<Producto> arr_Producto = (ArrayList<Producto>) s.readObject();
	        s.close();
		return arr_Producto;
	}
        
       public static void escribirFisheroUsuario( Usuario usr) throws IOException, ClassNotFoundException {
		File file=new File("usuario");
		FileOutputStream f =new FileOutputStream(file);
		ObjectOutputStream s = new ObjectOutputStream(f);
		s.writeObject(usr);
		s.close();
	}
	
	public static Usuario  leerFicheroUsuario() throws IOException, ClassNotFoundException {
		File file=new File("usuario");
		FileInputStream f = new FileInputStream(file);
                ObjectInputStream s = new ObjectInputStream(f);
        	Usuario usr = (Usuario) s.readObject();
	        s.close();
		return usr;
	}
        
        
    
}
