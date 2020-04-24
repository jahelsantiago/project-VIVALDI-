/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.Logic;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mauri
 */
public class Supervisor extends PersonaJ implements Serializable{
    
    String password;
    String correo;
    String usuario;
    
    public Supervisor(String nombre, String apellido, Date fecha_nacimiento, int id, String password) {
        super(nombre, apellido, fecha_nacimiento, id);
        this.password = password;
    }
    
    public Supervisor(String usuario,  String correo,  String password) {
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    
    public String getCorreo() {
        return correo;
    }
    
    public String getPassword() {
        return password;
    }
    
    
    
}
