
package ProMange.Logic;

import java.util.Date;

public class EmpleadoJ extends PersonaJ{
    
    boolean estado;
    int maquina;

    public EmpleadoJ() {
        this.nombre = null; 
        this.apellido = null;
        this.fecha_nacimiento = null;
        this.id = 0;
        this.estado = false;
        this.maquina =0;     
    }
    
    
    
    public EmpleadoJ(String nombre, String apellido, Date fecha_nacimiento, long id) {
        super(nombre, apellido, fecha_nacimiento, id);
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
