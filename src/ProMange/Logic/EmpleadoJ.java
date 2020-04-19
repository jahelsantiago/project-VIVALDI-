
package ProMange.Logic;

import java.util.Date;
import org.w3c.dom.Element;

public class EmpleadoJ extends UsuarioJ{
    
    boolean estado;
    int maquina;

    public EmpleadoJ() {
        this.nombre = null; 
        this.apellido = null;
        this.fecha_nacimiento = null;
        this.id = 0;
        this.password = null;
        this.estado = false;
        this.maquina =0;
                
    }
    
    
    
    public EmpleadoJ(String nombre, String apellido, Date fecha_nacimiento, long id, String password) {
        super(nombre, apellido, fecha_nacimiento, id,password);
       this. estado = false;
       this.maquina = 0;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getMaquina() {
        return maquina;
    }

    public void setMaquina(int maquina) {
        this.maquina = maquina;
    }
    
    /////////////////////////////////////////


  
    
    
    
}
