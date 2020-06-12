/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ED;

import ProMange.Logic.OrdenJ;

/**
 *
 * @author ProBook
 */
public class MatrizDinamica {
    public String[][] datos;
    public int columnas;
    public int filas;    
    public int filasMax;
    public int columnasMax;    
    public int[] profundidad_filas;
    public int[] prioridad;

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

            String referencia = ((OrdenJ)arr.get(min_col)).getReferencia_orden();
            int prioridad = ((OrdenJ)arr.get(min_col)).getTiempo_elaboracion();        
            this.columnAppend(min_col,referencia , prioridad );
        }

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
        
        for(int i = 0; i<this.columnasMax;i++){
            System.out.print(this.prioridad[i]);
            System.out.print(" ");
        }
        
    }

    
    
    public void imprimirMatriz(){
            for(int i= 0;i<filasMax;i++){
                for (int j = 0; j < columnasMax; j++) {
                    System.out.print(this.datos[i][j]);
                }
                System.out.println("");
                
            }          
    }
}
    
    
    

