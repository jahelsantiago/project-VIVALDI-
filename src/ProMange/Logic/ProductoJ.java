
package ProMange.Logic;

public class ProductoJ {
    String referencia,nombre,categoria;
    int tiempo_elaboracion,cantidad_inventario;

    public ProductoJ(String referencia, String nombre, String categoria, int tiempo_elaboracion, int cantidad_inventario) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.categoria = categoria;
        this.tiempo_elaboracion = tiempo_elaboracion;
        this.cantidad_inventario = cantidad_inventario;
    }

    public ProductoJ() {
        this.referencia = "0";
        this.nombre = "ninguno";
        this.categoria = "ninguno";
        this.tiempo_elaboracion = 0;
        this.cantidad_inventario = 0;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTiempo_elaboracion() {
        return tiempo_elaboracion;
    }

    public void setTiempo_elaboracion(int tiempo_elaboracion) {
        this.tiempo_elaboracion = tiempo_elaboracion;
    }

    public int getCantidad_inventario() {
        return cantidad_inventario;
    }

    public void setCantidad_inventario(int cantidad_inventario) {
        this.cantidad_inventario = cantidad_inventario;
    }
    
    
}
