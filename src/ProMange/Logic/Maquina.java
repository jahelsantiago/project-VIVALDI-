
package ProMange.Logic;

import ED.ArrayList;
import ED.MyArrayList;
import java.io.Serializable;

public class Maquina implements Comparable<Maquina>, Serializable {
    int serial;
    boolean estado;
    long operario;
    String nombre;
    ArrayList<OrdenJ> ordenes;

    public ArrayList<OrdenJ> getOrdenes() {
        return ordenes;
    }
    public String getNombre() {
        return nombre;
    }

        
    public void setOrdenes(ArrayList<OrdenJ> ordenes) {
        this.ordenes = ordenes;
    }
        
    public Maquina(int serial, boolean estado, long operario) {
        this.serial = serial;
        this.estado = estado;
        this.operario = operario;
    }
    
    public Maquina(int serial, String nombre, boolean estado, long operario) {
        this.serial = serial;
        this.estado = estado;
        this.operario = operario;
        this.nombre = nombre;
    }

    public Maquina() {
        this.serial = 0;
        this.estado = false;
        this.operario = 0;
    }
    
    public Maquina(ED.ArbolAVL<Maquina> Maquinas){
        if (Maquinas.isEmpty()){
            Maquina nueva = new Maquina();
            nueva.setEstado(true);
            nueva.setSerial(1);
            Maquinas.insert(nueva);
        }else{
            Maquina sm = Maquinas.findMax();
            int mayor = sm.getSerial();
            Maquina nueva = new Maquina();
            nueva.setEstado(true);
            nueva.setSerial(1+ mayor);
            Maquinas.insert(nueva);
        }
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
    public void setNombre(String serial) {
        this.nombre = serial;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public long getOperario() {
        return operario;
    }

    public void setOperario(long operario) {
        this.operario = operario;
    }        
    // implementando arbol AVL
    
    @Override
    public int compareTo (Maquina b){ 
        if(this.serial > b.serial){
            return 1;
        } else if (this.serial < b.serial){
            return -1;
        }else{
            return 0;
        }
    }
    
    public boolean contains(int ser,ED.ArbolAVL<Maquina> b){
        Maquina a = new Maquina();
        a.setSerial(ser);
        if (b.contains(a)) {
            return true;
        }else{
            return false;
        } 
    }    
}
