
package ED;

import ProMange.Logic.EmpleadoJ;
import ProMange.Logic.EmpleadoJ_excel;


public class Ejemplo_leer_excel {

    public static void main(String[] args) {
        
       EmpleadoJ_excel a = new EmpleadoJ_excel();
        
       EmpleadoJ nuevo = new EmpleadoJ();

        nuevo.setNombre("Ninguno");
        nuevo.setApellido("Ninguno");
        nuevo.setFecha_nacimiento("0/0/0");
        nuevo.setEstado(false);
        nuevo.setMaquina(0);
        nuevo.setId(0);
        
        a.agregarEmpleado(nuevo);
        

        LinkedList<EmpleadoJ> b  = a.obtenerEmpleados();
        
        for (int i = 0; i < b.getSize(); i++) {
            System.out.println("Nombre " + b.peek(i).getNombre());
            System.out.println("Apellido " + b.peek(i).getApellido());
            System.out.println("Fecha nacimineto " + b.peek(i).getFecha_nacimiento());
            System.out.println("Estado " + b.peek(i).getEstado());
            System.out.println("Maquina " + b.peek(i).getMaquina());
            System.out.println("Id " + b.peek(i).getId());
            System.out.println("");
        }
    }
    
}
