
package ED;


public class Pila <J>{
    private int size;
    private Node top;

    public Pila(){
        size = 0;
        top = null;
    }
    
    
    public void push(J valor){
        Node nuevo = new Node();
        nuevo.setData(valor);
        nuevo.setNext(top);
        top = nuevo;
        size++;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public J pop(){
        if(isEmpty()){
            return null;
        }else{
            Node res = new Node();
            res = top;
            top = res.getNext();
            size--;
            return (J) res.getData();
        }
    }
    
    public J peek(){
        if(isEmpty()){
            return null;
        }else{
            return (J) top.getData();
        }
    }
    
    
    
}
