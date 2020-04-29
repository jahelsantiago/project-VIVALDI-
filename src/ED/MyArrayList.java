
package ED;

import java.util.Iterator;






public class MyArrayList<J> implements LinearList,Iterable<J> {
    
    //atributos
    protected J[] arr_objetos;
    protected int size;
    
    //Constructores
    public MyArrayList(int capacidad_inicial){
        if (capacidad_inicial<1) {
            throw new IllegalArgumentException("La capacidad inicial debe ser mayor o igual a 1");
        }
        arr_objetos = (J[]) new Object[capacidad_inicial];
        size=0;
    }
    
    public MyArrayList(){
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
    

    public J remove(int index) {
         checkIndex(index);
         
         J elemento_removido = arr_objetos[index];
         for (int i = index+1; i < size; i++) {
            arr_objetos[i-1] = arr_objetos[i];
         }
         arr_objetos[--size] = null;
         return elemento_removido;
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
    public int indexOf(Object objeto) {
             for (int i = 0; i < size; i++) {
             if (arr_objetos[i].equals(objeto)) {
                 return i;
             }
        }
         return -1;
    }
}
