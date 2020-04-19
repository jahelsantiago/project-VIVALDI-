
package ED;


public class Node <J>{
    private Node next;
    private J data;

    public Node() {
        this.next = null;
        this.data = null;
    }
    
    public Node(Node sig, J info) {
        this.next = sig;
        this.data = info;
    }
    
     public Node(Node sig) {
        this.next = sig;
        this.data = null;
    }
     
     public Node(J valor) {
        this.next = null;
        this.data = valor;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public J getData() {
        return data;
    }

    public void setData(J data) {
        this.data = data;
    }

   
}
