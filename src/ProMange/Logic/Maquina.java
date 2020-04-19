
package ProMange.Logic;

public class Maquina {
    int serial;
    boolean estado;
    int operario;

    public Maquina(int serial, boolean estado, int operario) {
        this.serial = serial;
        this.estado = estado;
        this.operario = operario;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getOperario() {
        return operario;
    }

    public void setOperario(int operario) {
        this.operario = operario;
    }
    
    
}
