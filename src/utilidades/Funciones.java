package utilidades;
import java.util.Scanner;

import static utilidades.FuncionesCadenas.*;
import usuario.*;
import gestor.*;
import inversor.*;

public final class Funciones {
    public static void registroUsuarios(){
        GestionUsuarios usuarios= new GestionUsuarios(10);
        Scanner s = new Scanner(System.in);
        System.out.println("REGISTRO DE USUARIOS");
        int opcion;
        do{
            System.out.println("Seleccione el tipo de usuario");
            System.out.println("1. Inversor");
            System.out.println("2. Gestor");
            opcion= Integer.parseInt(s.nextLine());
        }while (opcion!=1 && opcion!=2);

        usuarios.agregarUsuario(datosUsuario(opcion));
    }

    public static Usuario datosUsuario(int opcion){
        Scanner s = new Scanner(System.in);
        System.out.println("Introduzca su correo electrónico");
        String correo = s.nextLine();
        System.out.println("Introduzca su nombre de usuario");
        String nombreUsuario= s.nextLine();

        String contrasena;
        do{
            System.out.println("Introduzca una contraseña");
            System.out.println("Debe contener: ");
            System.out.println("  ->Mínimo 8 carácteres");
            System.out.println("  ->Mayúsculas, minúsculas");
            System.out.println("  ->Carácteres especiales");
            contrasena= s.nextLine();
        }while(!fortalezaContrasena(contrasena));

        if (opcion==1){
            return new Inversor(nombreUsuario, correo, contrasena);
        }else{
            return new Gestor(nombreUsuario, correo, contrasena);
        }
    }

}
