
package ProMange.Logic;

import ED.ArrayList;
import ED.MyArrayList;

public class Maquina {
    int serial;
    boolean estado;
    long operario;
    ArrayList<OrdenJ> ordenes;

    public ArrayList<OrdenJ> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(ArrayList<OrdenJ> ordenes) {
        this.ordenes = ordenes;
    }
        
    public Maquina(int serial, boolean estado, long operario) {
        this.serial = serial;
        this.estado = estado;
        this.operario = operario;
    }

    public Maquina() {
        this.serial = 0;
        this.estado = false;
        this.operario = 0;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
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
    
    
}
