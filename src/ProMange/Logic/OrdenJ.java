
package ProMange.Logic;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import javax.swing.JOptionPane;

public class OrdenJ <J> implements Serializable, Comparable<OrdenJ >  {
    public String referencia_producto;
    public  String referencia_orden;
    public  String estado;
    public  int tiempo_elaboracion;
    public  int cantidad;
    public Date fecha_inicio;

    public OrdenJ(String referencia_producto, int tiempo_elaboracion, int cantidad_inventario) {
        this.referencia_orden = "-1";
        this.referencia_producto = referencia_producto;
        this.tiempo_elaboracion = tiempo_elaboracion;
        this.cantidad = cantidad_inventario;
        this.estado = "pendiente";
        this.setFecha_inicio("0/00/0000");
    }
    
    public OrdenJ(String referencia_producto, int tiempo_elaboracion, int cantidad_inventario, String referencia_orden, String fecha_entrega) {
        this.referencia_orden = referencia_orden;
        this.referencia_producto = referencia_producto;
        this.tiempo_elaboracion = tiempo_elaboracion;
        this.cantidad = cantidad_inventario;
        this.estado = "pendiente";
        this.setFecha_inicio(fecha_entrega);
    }
    
    
    
    public OrdenJ(){
        this.referencia_producto = "ninguna";
        this.estado = "incompleto";
        this.tiempo_elaboracion = 0;
        this.cantidad = 0;
        this.setFecha_inicio("0/00/0000");
    }

    public String getReferencia_orden() {
        return this.referencia_orden;
    }

    public void setReferencia_orden(String referencia_orden) {
        this.referencia_orden = referencia_orden;
    }
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getReferencia_producto() {
        return referencia_producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    
    
    public String getFecha_inicio() { //Cambie para que retornara un tipo String
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha_inicio);
    }

    public void setFecha_inicio (String fecha_inicio) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate = null;
            try {
                fechaDate = formato.parse((String) fecha_inicio);
            } 
            catch (ParseException ex) 
            {
                JOptionPane.showMessageDialog(null,"La fecha de incicio no se ha ingresado exitosamente",""+"Error", JOptionPane.WARNING_MESSAGE);
            }
            this.fecha_inicio = fechaDate;
    }
    



    public void setReferencia_producto(String referencia_producto) {
        this.referencia_producto = referencia_producto;
    }

    

    

    public int getTiempo_elaboracion() {
        return tiempo_elaboracion;
    }

    public void setTiempo_elaboracion(int tiempo_elaboracion) {
        this.tiempo_elaboracion = tiempo_elaboracion;
    }    
    
    
    
    @Override
    public int compareTo (OrdenJ a){ 

        if(Integer.valueOf(this.referencia_orden) > Integer.valueOf(a.getReferencia_orden())){
            return 1;
        } else if (Integer.valueOf(this.referencia_orden) < Integer.valueOf(a.getReferencia_orden())){
            return -1;
        }else{
            return 0;
        }
    }
    
    public boolean contains(String referencia, ED.ArbolAVL b){
        OrdenJ a = new OrdenJ();
        a.setReferencia_orden(referencia);
        if (b.contains(a)) {
            return true;
        }else{
            return false;
        } 
    }
    
    
}
