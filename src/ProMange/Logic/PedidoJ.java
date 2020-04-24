
package ProMange.Logic;

import ED.LinkedList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class PedidoJ <J> {
    int id_pedido,cantidad;
    Date fecha_inicio,fecha_entrega;
    ArrayList productos; //revisar como crear la lista y leerla

    public PedidoJ(int id_pedido, Date fecha_inicio, Date fecha_entrega, ArrayList  productos, int cantidad) {
        this.id_pedido = id_pedido;
        this.fecha_inicio = fecha_inicio;
        this.fecha_entrega = fecha_entrega;
        this.productos = productos;
        this.cantidad = cantidad;
        
    }

    public PedidoJ() {
        
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getFecha_inicio() { //Cambie para que retornara un tipo String
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha_inicio);
    }

    public void setFecha_inicio (J fecha_inicio) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate = null;
            try {
                fechaDate = formato.parse((String) fecha_inicio);
            } 
            catch (ParseException ex) 
            {
                JOptionPane.showMessageDialog(null,"La fecha de incicio no se ha ingresado exitosamente",""+"Error", JOptionPane.WARNING_MESSAGE);
            }
            this.fecha_inicio = fechaDate;
    }

    public String getFecha_entrega() { //Cambie para que retornara un tipo String
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha_entrega);
    }

    public void setFecha_entrega (J fecha_entrega) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate = null;
            try {
                fechaDate = formato.parse((String) fecha_entrega);
            } 
            catch (ParseException ex) 
            {
                JOptionPane.showMessageDialog(null,"La fecha de incicio no se ha ingresado exitosamente",""+"Error", JOptionPane.WARNING_MESSAGE);
            }
            this.fecha_entrega = fechaDate;
    }

    public ArrayList  getProductos() {
        return productos;
    }

    public void setProductos(ArrayList  productos) {
        this.productos = productos;
    }

}
