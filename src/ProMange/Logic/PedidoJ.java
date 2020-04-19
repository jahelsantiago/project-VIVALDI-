
package ProMange.Logic;

import ED.LinkedList;
import java.util.Date;

public class PedidoJ {
    int id_pedido;
    Date fecha_inicio,fecha_entrega;
    LinkedList productos; //revisar como crear la lista y leerla

    public PedidoJ(int id_pedido, Date fecha_inicio, Date fecha_entrega, LinkedList productos) {
        this.id_pedido = id_pedido;
        this.fecha_inicio = fecha_inicio;
        this.fecha_entrega = fecha_entrega;
        this.productos = productos;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public LinkedList getProductos() {
        return productos;
    }

    public void setProductos(LinkedList productos) {
        this.productos = productos;
    }
    
    
}
