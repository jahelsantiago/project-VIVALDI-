
package ProMange.Logic;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


public class PersonaJ <J> implements Serializable{
    
    String nombre,apellido;
    Date fecha_nacimiento;
    long id;

    public PersonaJ(String nombre, String apellido, Date fecha_nacimiento, long id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id = id;
    }
    
    public PersonaJ() {
        this.nombre = null;
        this.apellido = null;
        this.fecha_nacimiento = null;
        this.id = 0;
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
                JOptionPane.showMessageDialog(null,"La fecha de nacimiento no se ha ingresado exitosamente",""+"Error", JOptionPane.WARNING_MESSAGE);
            }
            this.fecha_nacimiento = fechaDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
 
    
}
