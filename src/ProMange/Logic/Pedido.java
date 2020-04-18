/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.Logic;

import java.util.Date;

/**
 *
 * @author ProBook
 */
public class Pedido {
    private Producto producto;
    private Date fecha;
    
    
    

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Pedido(Producto producto, Date fecha) {
        this.producto = producto;
        this.fecha = fecha;
    }
    
    
}
