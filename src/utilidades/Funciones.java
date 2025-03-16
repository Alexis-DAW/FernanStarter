package utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static utilidades.FuncionesCadenas.*;

import proyecto.Categoria;
import proyecto.Proyecto;
import usuario.*;
import gestor.*;
import inversor.*;

public final class Funciones {
    public static void registroUsuarios(GestionUsuarios usuarios){
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

    public static String crearcontrasena(){
        Scanner s = new Scanner(System.in);
        String contrasena;
        do{
            System.out.println("Introduzca una contraseña");
            System.out.println("Debe contener: ");
            System.out.println("  -> Mínimo 8 carácteres");
            System.out.println("  -> Mayúsculas, minúsculas");
            System.out.println("  -> Carácteres especiales");
            contrasena= s.nextLine();
        }while(!fortalezaContrasena(contrasena));

        String contrasena2;
        do{
            System.out.println("Vuelva ha introducir su contraseña");
            contrasena2= s.nextLine();
        }while(!compararContrasenas(contrasena, contrasena));

        return contrasena;
    }

    public static Usuario datosUsuario(int opcion){
        Scanner s = new Scanner(System.in);
        System.out.println("Introduzca su correo electrónico");
        String correo = s.nextLine();
        System.out.println("Introduzca su nombre de usuario");
        String nombreUsuario= s.nextLine();

        String contrasena = crearcontrasena();

        if (opcion==1){
            return new Inversor(nombreUsuario, correo, contrasena);
        }else{
            return new Gestor(nombreUsuario, correo, contrasena);
        }
    }

    public static Proyecto datosProyecto(){
        Scanner s = new Scanner(System.in);

        System.out.print("Introduzca el nombre del proyecto: ");
        String nombre= s.nextLine();

        System.out.println("Introduzca la descripción del proyecto: ");
        String descripcion = s.nextLine();

        System.out.print("Escoja una de las siguientes categorías");
        System.out.println("Arte");
        System.out.println("Tecnología");
        System.out.println("Cine");
        System.out.println("Música");
        System.out.println("Juegos");
        System.out.println("Comida");
        System.out.println("Moda");
        Categoria categoria= Categoria.valueOf(s.nextLine().toUpperCase());

        System.out.print("Introduzca la cantidad de inversión necesaria: ");
        double cantidadNecesaria= Double.parseDouble(s.nextLine());

        DateTimeFormatter formato= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Introduzca la fecha de apertura para recibir inversiones (dd/MM/yyy)");
        LocalDate fechaInicio= LocalDate.parse(s.nextLine(), formato);
        System.out.println("Introduzca la fecha de cierre (dd/MM/yyy)");
        LocalDate fechaCierre= LocalDate.parse(s.nextLine(), formato);

        Proyecto nuevoProyecto= new Proyecto(nombre, descripcion, cantidadNecesaria, fechaInicio, fechaCierre, categoria);

        System.out.println("¿Cuantas recompensas desea añadir al proyecto?");
        nuevoProyecto.setNumRecompensas(s.nextInt());

        return nuevoProyecto;
    }
}
