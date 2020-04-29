package ED;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;




public class MyArrayListIterator <J> implements Iterator<J>{
    private ArrayList<J> list;
    private int nextIndex;
    
    public MyArrayListIterator(ArrayList<J> lista){
        list = lista;
        nextIndex =0;
    }
    
    public boolean hasNext(){
        return nextIndex < list.size;
    }
    
    public J next(){
        if (nextIndex < list.size) {
            return list.arr_objetos[nextIndex++];
        }else{
            throw new NoSuchElementException("No hay un elemento siguiente");
        }
    }
    
    public void remove(){
        throw new UnsupportedOperationException("No se puede remover un elemento con esta clase");
    }

    
}