
package ED;


public interface LinearList <J> {
    boolean isEmpty();
    int size();
    J get(int index);
    int indexOf(J objeto);
    void add(int index, J objeto_agregar);
    String toString();
    
}
