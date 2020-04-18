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
    private String Tiempo;
    private String cantidad;
    private int cantidad_int;

    public Producto(String referencia, String Nombre, String Tiempo, String cantidad) {
        this.referencia = referencia;
        this.Nombre = Nombre;
        this.Tiempo = Tiempo;
        this.cantidad = cantidad;
        this.cantidad_int = Integer.parseInt(cantidad);
    }

    public String getReferencia() {
        return referencia;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public int getCantidad_int() {
        return cantidad_int;
    }

    

    

    
    
    
}
