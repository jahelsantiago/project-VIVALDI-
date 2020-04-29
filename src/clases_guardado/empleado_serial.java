/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases_guardado;

import ProMange.Logic.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ProBook
 */
public class empleado_serial implements Serializable{
    private ED.ArrayList<ProductoJ> arr;

    public empleado_serial(ED.ArrayList<ProductoJ> arr) {
        this.arr = arr;
    }

    public void setArr(ED.ArrayList<ProductoJ> arr) {
        this.arr = arr;
    }

    public ED.ArrayList<ProductoJ> getArr() {
        return arr;
    }
    
}
