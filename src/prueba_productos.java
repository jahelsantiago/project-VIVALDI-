
import ED.NodoAVL;
import ProMange.Logic.Maquina;
import ProMange.Logic.ProductoJ;
import ProMange.Logic.Xml_clases.ProductoJ_excel;


public class prueba_productos {

    public static void main(String[] args) {
        ED.ArbolAVL a = new ED.ArbolAVL<Maquina>();
        Maquina b = new Maquina();
        b.setSerial(1);
        Maquina c = new Maquina();
        c.setSerial(2);
        Maquina d = new Maquina();
        d.setSerial(3);
        
        System.out.println(b.getSerial());
        System.out.println(c.getSerial());
        System.out.println(d.getSerial());
        
        
        a.insert(b);
        a.insert(c);       
        a.insert(d);
        a.printTree_in();
        
        Maquina m1 = (Maquina) a.getRaiz().getData();
        Maquina m2 = (Maquina) a.getRaiz().getLeft().getData();
        Maquina m3 = (Maquina) a.getRaiz().getRight().getData();
        
        System.out.println(m1.getSerial());
        System.out.println(m2.getSerial());
        System.out.println(m3.getSerial());
    }
    
}
