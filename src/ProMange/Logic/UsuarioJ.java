
package ProMange.Logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UsuarioJ <J>{
    
    String nombre,apellido;
    Date fecha_nacimiento;
    long id;
    String password;

    public UsuarioJ(String nombre, String apellido, Date fecha_nacimiento, long id, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id = id;
        this.password = password;
    }
    
    public UsuarioJ() {
        this.nombre = null;
        this.apellido = null;
        this.fecha_nacimiento = null;
        this.id = 0;
        this.password = null;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_nacimiento() { //Cambie para que retornara un tipo String
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha_nacimiento);
    }

    public void setFecha_nacimiento(J fecha_nacimiento) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate = null;
            try {
                fechaDate = formato.parse((String) fecha_nacimiento);
            } 
            catch (ParseException ex) 
            {
                System.out.println(ex);
            }
            this.fecha_nacimiento = fechaDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


   
    
    
    
}
