
package ED;

public class NodoAVL <J> {
    int alt;
    J data;
    NodoAVL<J> left;
    NodoAVL<J> right;

    public NodoAVL(J data) {
        this.alt = 0;
        this.data = data;
        this.left = null;
        this.right = null;
    }
    
    public NodoAVL(J data, NodoAVL<J> izq, NodoAVL<J> der) {
        this.alt = 0;
        this.data = data;
        this.left = izq;
        this.right = der;
    }
    
}
