
package ED;

public class LinkedList <J>{
    int size;
    Node head;
    
    public LinkedList(){
        this.head=null;
        this.size=0;
    }
    
    public boolean revIndex(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index +"es mayor al tamaño de la lista o es menor a 0" );
        }else{
            return true;
        }
    }
    
    public boolean isEmpty(){
        return size==0;
    }
    
    public void push(J dato,int index){
        
        Node actual = new Node();
        actual = head;
        Node nuevo = new Node();
        nuevo.setData(dato);
        if (index == 0) {
           nuevo.setNext(head);
           head = nuevo;
           size++;
        }else if(head == null){
            head = nuevo;
            size++;
        } else{
            for (int i = 1; i < index; i++) {    
            actual = actual.getNext();
            }
            nuevo.setNext(actual.getNext());
            actual.setNext(nuevo);
            size++;
        }
    }
    
    public J peek(int index){
        
        revIndex(index);
        
        Node actual = new Node();
        actual = head;
        if (index == 0) {
            return (J) actual.getData();
        }
        for (int i = 1; i <= index; i++) {    
            actual = actual.getNext();
        }
        return (J) actual.getData();
    }
    
    public J pop(int index){
        
        revIndex(index);
        
        Node actual = new Node();
        Node retorno = new Node();
        actual = head;
        if (index == 0 && (head != null)) {  
            retorno = head;
            head=head.getNext();
            size--;
            return (J) retorno.getData();    
        }else if(head == null){
            return null;
        }else{
            for (int i = 1; i < index; i++) {    // int i = 0; i < index - 1
            actual = actual.getNext();
            }
            retorno = actual.getNext();
            actual.setNext(actual.getNext().getNext());
            size--;
            return (J) retorno.getData();
        }
    }
    
    public int getSize(){
        return size;
    }
    
    public int indexOf (J elemento){
        Node actual = new Node();
        actual = head;
        int index = -1;
        for (int i = 0; i < size-1; i++) { 
            if (elemento.equals(actual.getData())) {
                index = i;
                break;
            }else{
                actual = actual.getNext();
            } 
        }
        return index;
    }
    
}
