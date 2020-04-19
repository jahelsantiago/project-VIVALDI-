
package ED;

public class Queue <J>{
    private Node head;
    private Node tail;
    private int size;

    public Queue() {
        head = null;
        tail = null;
        size = 0;
    }
    
    
    public Boolean isEmpty(){
        return size ==0;
    }
    
    public Node getHead(){
        return head;
    }
    
    public Node getTail(){
        return tail;
    }
    
    public void push(J valor){
        Node nuevo = new Node();
        nuevo.setData(valor);
        if((head == null)){
            head = nuevo;
            tail = nuevo;
            size = 1;
        }else{
            tail.setNext(nuevo);
            tail = nuevo;
            size++;
        }   
    }
    
    public J peekHead(){
        if(head==null){
            return (J) "No existe";
        }else{
            return (J) head.getData();
        } 
    }
    
    public J peekTail(){
        if(tail==null){
            return (J) "No existe";
        }else{
            return (J) tail.getData();
        } 
    }
    
    public Node pop(){
        Node mostrar = new Node();
        mostrar = head;   
        head = head.getNext();
        size--;
        return mostrar;
    }
    
    public int getSize(){
        return size;
    }
    
}
