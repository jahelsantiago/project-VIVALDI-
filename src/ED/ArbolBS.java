
package ED;

public class ArbolBS <J extends Comparable <?super J>>  {
    
    NodoAVL raiz;
    
    //Constructor
    
    public ArbolBS(){
        raiz = null;
    }
    
    // Insertar
    
    public void insert(J x){
        raiz = insert(x, raiz);
    }
    
    private NodoAVL<J> insert( J x, NodoAVL<J> nd )
    {
        if( nd == null )
            return new NodoAVL<>( x, null, null );
        
        int compareResult = x.compareTo( nd.data );
            
        if( compareResult < 0 )
            nd.left = insert( x, nd.left );
        else if( compareResult > 0 )
            nd.right = insert( x, nd.right );
        else
            ;  // Si es un duplicado no hacer nada, aunque igual
               // se puede configurar despues para guardarlo 
        return nd;
    }
    

    //remover
    
    public void remove(J x){
        raiz = remove(x, raiz);
    }
    
    private NodoAVL<J> remove(J x, NodoAVL<J> nd){
        if (nd == null) {
            return nd;  //No hacemos nada porque  el arbol esta vacio
        }
        
        int comp = x.compareTo( nd.data );
        
        if(comp < 0){
            nd.left = remove(x, nd.left);
        }
        else if(comp > 0){
            nd.right = remove(x, nd.right);
        }
        else if(nd.left != null && nd.right != null){ // En caso de que el nodo a eliminar posea 2 hijos
           nd.data = findMin(nd.right).data;
           nd.right = remove(nd.data, nd.right);
           //En este caso debemos reemplazar el valor del nodo a elminar
           //Por el valor  mas pequeño debajo de su hijo derecho
           //El cual seria el valor proximo mayor seguiente a el
        }
        else if(nd.left != null){ 
        // Caso en que el nodo a eliminar solamente posea un hijo a la izquierda
        // Este tomara sera reemplazado por su hijo izquierda
            nd = nd.left;
        }else{
            // Encaso de que solo sea una hoja pues entonces este
            // sera igual a su hijo derecho o sea a null y ya se reemplazo
            nd = nd.right;
        }         
        return nd; // retornamos el nodo eliminado
    }
    
    // Encontar minimo
    
    public J findMin(){
        if (isEmpty()) {
            System.out.println("Arbol vacio");
        }
        return (J) findMin(raiz).data;
        
    }
    
    private NodoAVL<J> findMin(NodoAVL<J> nd){
        
        if (nd == null && nd == raiz) {
            return null;
        }
        
        while (nd.left != null) {
            nd = nd.left;
        }
        
        return nd;
    }
    
    // Encontar maximo
    
    public J findMax(){
        if (isEmpty()) {
            System.out.println("Arbol vacio");
        }
            return (J) findMax(raiz).data;
        
    }
    
    private NodoAVL<J> findMax (NodoAVL<J> nd){
        if (nd == null && nd == raiz) {
            return null;
        }
        
        while (nd.right != null) {
            nd = nd.right;
        }
        
        return nd;
        
    }
    
    // Continete el elmento que busco
    
    public boolean contains(J x){
        return contains(x, raiz);
    }
    
    private boolean contains(J x, NodoAVL<J> nd ){
        
        if (nd == null) {
            return false;
        }
        
        int comp = x.compareTo(nd.data);
        
        //Busca dependiendo si el dato en el nodo es mayor o menor a x
        
        if (comp < 0) {
            return contains(x, nd.left); 
        }
        else if(comp > 0){
            return contains(x, nd.right);
        }
        else {
            return true; 
        }
    }
    
    // Esta vacio?
    
    public boolean isEmpty(){
        if (raiz == null) {
            return true;
        }else{
            return false;
        }
    }
    
    // Vaciar el arbol
    
    public void makeEmpty(){
        raiz = null;
    }
    
    
    //imprimir arbol con InOrder
    
    private void printTree_in( NodoAVL<J> t )
    {
        if( t != null )
        {
            printTree_in( t.left );
            System.out.println( t.data );
            printTree_in( t.right );
        }
    }
    
    public void printTree_in( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree_in( raiz );
    }

}
