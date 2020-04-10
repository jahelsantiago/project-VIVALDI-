/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.Logic;

import java.util.ArrayList;

/**
 *
 * @author ProBook
 */
public class Empleado {
    private String nombre, turno, telefono , correo;
    private int id;
    

    public Empleado(String nombre, String turno, String id, String correo) {
        this.nombre = nombre;
        this.turno = turno;
        this.telefono  = id;
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public String getTurno() {
        return turno;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }
    
    
}
