/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.Logic;

/**
 *
 * @author ProBook
 */
public class Producto {
    private String referencia;
    private String Nombre;
    private String cantidad;
    private int cantidad_int;

    public Producto(String referencia, String Nombre, String cantidad) {
        this.referencia = referencia;
        this.Nombre = Nombre;
        this.cantidad = cantidad;
        this.cantidad_int= Integer.parseInt(cantidad);
    }

    public String getReferencia() {
        return referencia;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCantidad() {
        return cantidad;
    }
    
    public int getCantidadInt(){
        return cantidad_int;
    }

    
    
    
}
