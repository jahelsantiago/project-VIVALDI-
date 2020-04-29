


import ED.LinkedList;
import ED.Node;
import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.Maquina;
import ProMange.Logic.PedidoJ;
import ProMange.Logic.ProductoJ;
import ProMange.Logic.Xml_clases.EmpleadoJ_excel;
import ProMange.Logic.Xml_clases.Maquina_excel;
import ProMange.Logic.Xml_clases.Pedido_excel;
import ProMange.Logic.Xml_clases.ProductoJ_excel;
import java.util.ArrayList;


public class Ejemplo_leer_excel {
    
    public static void imprimir_empleados(){
        EmpleadoJ_excel a = new EmpleadoJ_excel();

        ED.ArrayList b  = a.obtenerEmpleados();
        EmpleadoJ mostrar = new EmpleadoJ();
        
        for (int i = 0; i < b.size(); i++) {
            
            mostrar = (EmpleadoJ) b.get(i);
            
            System.out.println("Nombre " + mostrar.getNombre());
            System.out.println("Apellido " + mostrar.getApellido());
            System.out.println("Fecha nacimineto " + mostrar.getFecha_nacimiento());
            System.out.println("Estado " + mostrar.getEstado());
            System.out.println("Maquina " + mostrar.getMaquina());
            System.out.println("Id " + mostrar.getId());
            System.out.println("");
        }
    }
    public static void imprimir_maquinas(){
        Maquina_excel b = new Maquina_excel();
       ED.ArrayList c = b.obtenerMaquinas();
       Maquina mostrar = new Maquina();
        for (int i = 0; i < c.size(); i++) {
            mostrar = (Maquina) c.get(i);
            System.out.println(mostrar.getEstado());
            System.out.println(mostrar.getOperario());
            System.out.println(mostrar.getSerial());
        }
    }
    public static void imprimir_Producto(){
       ProductoJ a = new ProductoJ();
       
       ProductoJ_excel b = new ProductoJ_excel();
       b.agregarProducto(a);
       ED.ArrayList c = new ED.ArrayList();
       c = b.obtenerProductos();
       ProductoJ d = new ProductoJ();
       
        for (int i = 0; i < c.size(); i++) {
            System.out.println(d.getNombre());
            System.out.println(d.getReferencia());
            System.out.println(d.getCategoria());
            System.out.println(d.getCantidad_inventario());
            System.out.println(d.getTiempo_elaboracion());
            System.out.println("------------------------------------------------");
        }
    }
    public static LinkedList ListaP (String cadenaProductos){
        
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
            cantidad.push(cant,i);
            j = j+2;
        }       

        ejemploLista.push(productos, 0);
        ejemploLista.push(cantidad, 1);
        
        return ejemploLista;
     }
    
    public static ED.ArrayList ListaProductos (String cadenaProductos){
        
        //Guardamos todas las cadenas separadas por comas  (produc,canti,produc,canti,......)
         String[] ListaProductos = null;
         ListaProductos = cadenaProductos.split(",");   
        
         //Lista donde se guardaran una lista de prodcutos con una lista de cantidades
        ED.ArrayList <ED.ArrayList<String>> ejemploLista = new ED.ArrayList<ED.ArrayList<String>>();
        ED.ArrayList<String> productos = new ED.ArrayList<String>();
        ED.ArrayList<String> cantidad = new ED.ArrayList<String>();
        int j=0;
        
        for (int i = 0; i < (ListaProductos.length/2); i++) {
            String producto =ListaProductos[j];
            String cant =ListaProductos[j+1];
            productos.add(i,producto);
            cantidad.add(i,cant);
            j = j+2;
        }       

        ejemploLista.add(0,productos);
        ejemploLista.add(1,cantidad);
        
        return ejemploLista;
     }
     
    public static String ListaProductos_excel (ED.ArrayList productos_guardar){
        
        //Guardamos todas las cadenas separadas por comas  (produc,canti,produc,canti,......) 
   
        ED.ArrayList productos = (ED.ArrayList) productos_guardar.get(0);
        ED.ArrayList cantidad = (ED.ArrayList) productos_guardar.get(1);
        
        String retorno = "";
        
        for (int i = 0; i < productos.size()-1; i++) {
            retorno = retorno + productos.get(i)+","+cantidad.get(i) +",";
        }
        return retorno = retorno +productos.get(productos.size()-1)+","+cantidad.get(cantidad.size()-1);
        
     }
    
    public static void main(String[] args) {
    
        String pp = "oro,5,plata,8,arroz,9";
        ED.ArrayList general =   ListaProductos(pp);
        ED. ArrayList productos = (ED.ArrayList) general.get(0);
        ED.ArrayList cantidad = (ED.ArrayList) general.get(1);

        PedidoJ nuevo = new PedidoJ();
        nuevo.setProductos(general);
        nuevo.setFecha_entrega("1/12/2004");
        nuevo.setFecha_inicio("1/12/2003");
        nuevo.setId_pedido(1);
        
        Pedido_excel o = new Pedido_excel();
        o.agregarPedido(nuevo);
        ED.ArrayList ped =   o.obtenerPedidos();
        PedidoJ mostrar = new PedidoJ();
        mostrar = (PedidoJ) ped.get(0);
        System.out.println(mostrar.getFecha_inicio());
        
    }
}
