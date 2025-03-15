import usuario.GestionUsuarios;

import java.util.Scanner;
import static utilidades.Funciones.registroUsuarios;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        GestionUsuarios usuarios = new GestionUsuarios(100);

        int opcion;
        do{
            System.out.println("Bienvenido ༼ つ ◕_◕ ༽つ");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Mostrar usuarios actuales (para pruebas internas)");
            opcion = Integer.parseInt(s.nextLine());
            if (opcion == 2) registroUsuarios(usuarios);
            if (opcion == 3) System.out.println(usuarios);
        }while (opcion!=1);
    }
}