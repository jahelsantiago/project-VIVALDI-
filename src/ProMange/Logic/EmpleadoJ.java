
package ProMange.Logic;

import java.io.Serializable;
import java.util.Date;

public class EmpleadoJ extends PersonaJ implements Serializable, Comparable<EmpleadoJ> {
    
    boolean estado,dispo;
    int maquina;

    public EmpleadoJ() {
       this.setNombre("Ninguno");
       this.setApellido("Ninguno");
       this.setEstado(false);
       this.setDispo(false);
       this.setFecha_nacimiento("0/00/0000");
       this.setId(0);
       this.setMaquina(0);  
    }
    
    
    
    public EmpleadoJ(String nombre, String apellido, String fecha_nacimiento, long id, boolean estado, boolean dispo) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setEstado(estado);
        this.setDispo(dispo);
        this.setFecha_nacimiento(fecha_nacimiento);
        this.setId(id);
        this.setMaquina(0);
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

    public boolean getDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }
     
    /////////////////////////////////////////

    @Override
    public String toString() {
        return "EmpleadoJ{" + "estado=" + estado + ", maquina=" + maquina + '}';
    }
      
    // implementando arbol AVL
    
    @Override
    public int compareTo (EmpleadoJ a){  
        
        if(this.id > a.getId()){
            return 1;
        } else if (this.id < a.getId()){
            return -1;
        }else{
            return 0;
        }
    }
    
    public boolean contains(int id,ED.ArbolAVL<EmpleadoJ> b){
        EmpleadoJ a = new EmpleadoJ();
        a.setId(id);
        if (b.contains(a)) {
            return true;
        }else{
            return false;
        } 
    }
}
