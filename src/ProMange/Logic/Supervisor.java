/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProMange.Logic;

import java.util.Date;

/**
 *
 * @author mauri
 */
public class Supervisor extends UsuarioJ {
    
    public Supervisor(String nombre, String apellido, Date fecha_nacimiento, int id, String contraseña) {
        super(nombre, apellido, fecha_nacimiento, id, contraseña);
    }
    
}
