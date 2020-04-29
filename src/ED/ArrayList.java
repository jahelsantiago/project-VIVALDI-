package ED;

import java.io.Serializable;
import java.util.Iterator;




public class ArrayList<J> implements LinearList,Iterable<J>,Serializable {
    
    //atributos
    protected J[] arr_objetos;
    protected int size;
    protected int len;
    
    //Constructores
    public ArrayList(int capacidad_inicial){
        if (capacidad_inicial<1) {
            throw new IllegalArgumentException("La capacidad inicial debe ser mayor o igual a 1");
        }
        arr_objetos = (J[]) new Object[capacidad_inicial];
        size=0;
    }
    
    public ArrayList(){
        this(10);
    }
    
    
    @Override
    public boolean isEmpty() {
       return size==0;
    }

    @Override
    public int size() {
        return size;
    }
    
    void checkIndex (int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("El index "+index+" no se encuentra en la lista de tamaño "+size);
        }
    }

    @Override
    public Object get(int index) {
         checkIndex(index);
         return arr_objetos[index];
    }

    public int indexOf(J elemento_a_encontrar) {
         for (int i = 0; i < size; i++) {
             if (arr_objetos[i].equals(elemento_a_encontrar)) {
                 return i;
             }
        }
         return -1;
    }
    
    public J remove(int index) {
         checkIndex(index);
         
         J elemento_removido = arr_objetos[index];
         for (int i = index+1; i < size; i++) {
            arr_objetos[i-1] = arr_objetos[i];
         }
         arr_objetos[--size] = null;
         return elemento_removido;
    }
    
    public void set(int index, Object objeto_agregar){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("El index "+index+" no se encuentra en la lista de tamaño "+size);
        }
        this.arr_objetos[index] = (J) objeto_agregar;
    }

    @Override
    public void add(int index, Object objeto_agregar) {
        //miramos si el index se encuentra dentro de la lista
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("El index "+index+" no se encuentra en la lista de tamaño "+size);
        }
        // miramos si tenemos el espacio para agregarlo
        if (size == arr_objetos.length) {
            J[] viejo = arr_objetos;
            arr_objetos = (J[]) new Object[2*size];
            //Copiamos la lista viejita al nuevo arr_objetos, desde la posicion 0 de la lista viejita comenzando tamibne desde la posicion 
            // 0 del arr_objetos en su totalidad
            System.arraycopy(viejo, 0,arr_objetos,0,size);
        }
        
        for (int i = size-1; i >= index; i--) {
            arr_objetos[i+1] = arr_objetos[i];
        }
        arr_objetos[index] = (J) objeto_agregar;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        
        for (J x : this ) {
            s.append(x.toString() + ", ");
        }
        if (size>0) {
            s.setLength(s.length() - 2);
        }
        s.append("]");
        return new String(s);
    }


    @Override
    public Iterator<J> iterator() {
       return new MyArrayListIterator<J>(this);
    }

    @Override
    public int indexOf(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
}