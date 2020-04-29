
package ProMange.Logic;

import java.io.Serializable;
import java.util.Date;

public class EmpleadoJ extends PersonaJ implements Serializable{
    
    boolean estado;
    int maquina;

    public EmpleadoJ() {
       this.setNombre("Ninguno");
       this.setApellido("Ninguno");
       this.setEstado(false);
       this.setFecha_nacimiento("0/00/0000");
       this.setId(0);
       this.setMaquina(0);  
    }
    
    
    
    public EmpleadoJ(String nombre, String apellido, String fecha_nacimiento, long id, boolean estado) {
        this.setNombre(nombre);
       this.setApellido(apellido);
       this.setEstado(estado);
       this.setFecha_nacimiento(fecha_nacimiento);
       this.setId(id);
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

    @Override
    public String toString() {
        return "EmpleadoJ{" + "estado=" + estado + ", maquina=" + maquina + '}';
    }
    
   


  
    
    
    
}
