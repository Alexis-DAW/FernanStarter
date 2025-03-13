import java.util.Scanner;
import static utilidades.Funciones.registroUsuarios;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int opcion;
        do{
            System.out.println("Bienvenido ༼ つ ◕_◕ ༽つ");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            opcion = Integer.parseInt(s.nextLine());
            if (opcion == 2) registroUsuarios();
        }while (opcion!=1);
    }
}