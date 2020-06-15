/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ED;

import ProMange.Logic.OrdenJ;
import java.io.Serializable;

/**
 *
 * @author ProBook
 */
public class MatrizDinamica implements Serializable{
    public String[][] datos;
    public int columnas;
    public int filas;    
    public int filasMax;
    public int columnasMax;    
    public int[] profundidad_filas;
    public int[] prioridad;
    public MinHeap minheap;

    public MatrizDinamica(int filasMax, int columnasMax) {
        this.filasMax = filasMax;        
        this.columnasMax = columnasMax;        
        this.datos = crearMatriz(filasMax, columnasMax);  
        this.profundidad_filas = new int[columnasMax];
        this.prioridad = new int[columnasMax];
        for (int i = 0; i < columnasMax; i++) {
            this.profundidad_filas[i] = 0;
            this.prioridad[i] = 0;
            
        }        
    }
    
// para auto asignar
    public void autoAsignar(ED.ArrayList<OrdenJ> arr){        
        for(int j = 0; j<arr.size;j++){
            int min = Integer.MAX_VALUE;
            int min_col=0;
            for (int i = 0; i < this.columnasMax; i++) {
                if(prioridad[i] < min){
                    min = prioridad[i];
                    min_col = i;
                }
            }
            if(((OrdenJ)arr.get(j)).getEstado().equalsIgnoreCase("Produccion")){
            continue;
            }
            String referencia = ((OrdenJ)arr.get(j)).getReferencia_orden();
            int prioridad = ((OrdenJ)arr.get(j)).getTiempo_elaboracion();        
            this.columnAppend(min_col,referencia , prioridad);
            ((OrdenJ)arr.get(j)).setEstado("Produccion");
        }
        
    }
    
    public MatrizDinamica(int filasMax, int columnasMax,ED.ArrayList<OrdenJ> arr){        
        // inicializa de forma normal la matriz
        this.filasMax = filasMax;        
        this.columnasMax = columnasMax;        
        this.datos = crearMatriz(filasMax, columnasMax);  
        this.profundidad_filas = new int[columnasMax];
        this.prioridad = new int[columnasMax];
        for (int i = 0; i < columnasMax; i++) {
            this.profundidad_filas[i] = 0;
            this.prioridad[i] = 0;            
        }        
        // asi
        for(int j = 0; j<arr.size;j++){
            int min = Integer.MAX_VALUE;
            int min_col=0;
            for (int i = 0; i < this.columnasMax; i++) {
                if(prioridad[i] < min){
                    min = prioridad[i];
                    min_col = i;
                }
            }
            String referencia = ((OrdenJ)arr.get(j)).getReferencia_orden();
            int prioridad = ((OrdenJ)arr.get(j)).getTiempo_elaboracion();        
            this.columnAppend(min_col,referencia , prioridad );
            ((OrdenJ)arr.get(j)).setEstado("Produccion");
        }
    }
    
    public void eliminar(int row,int column){
        if(this.datos[row][column].equals("")){
            return;
        }
        while(!this.datos[row][column].equals("")){            
            this.datos[row][column] = this.datos[row+1][column];
            row += 1;            
        }
        this.profundidad_filas[column] -= 1;        
    }
    
    public String[][] crearMatriz(int filas,int columnas){
        String[][] matriz = new String[filas][columnas];
        for(int i= 0;i<filas;i++){
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = "";
            }                    
        }
        return matriz;
    }
        
        
    public void columnAppend(int columna, String str, int prioridad){
        // si el arreglo esta lleno creamos uno nuevo y pasamos la info ahi
        this.datos[profundidad_filas[columna]][columna] = str; 
        this.profundidad_filas[columna] += 1;
        this.prioridad[columna] += prioridad;
        
        if(this.profundidad_filas[columna] > this.filas){
            this.filas = this.profundidad_filas[columna];
        }
        
        if(this.profundidad_filas[columna] == this.filasMax){
            this.filasMax = filasMax * 2;
            String[][] aux = crearMatriz(this.filasMax, this.columnasMax);
            for(int i = 0;i<filas;i++){
                for (int j = 0; j < columnasMax; j++) {
                    aux[i][j] = this.datos[i][j];
                }                    
            }
            this.datos = aux;
        }                             
    }
    
    public void insertarColumna(){
        this.columnasMax += 1;
        String[][] aux = new String[filasMax][columnasMax];
        for (int i = 0; i < filasMax; i++) {
            for (int j = 0; j < columnasMax-1; j++) {
                aux[i][j] = datos[i][j];
            }
        }
        datos = aux;
        for (int i = 0; i < filasMax; i++) {
            datos[i][columnasMax-1] = "";
        }
    }

    
    
    
    public void imprimirMatriz(){
            for(int i= 0;i<filasMax;i++){
                for (int j = 0; j < columnasMax; j++) {
                    System.out.print(this.datos[i][j]);
                    System.out.print(" ");
                    
                }
                System.out.println("");
                
            }          
    }
    
//    public static void main(String[] args) {
//        MatrizDinamica m = new MatrizDinamica(4, 2);
//        m.insertarColumna();
//        m.imprimirMatriz();
//    }
}
    
    
    

