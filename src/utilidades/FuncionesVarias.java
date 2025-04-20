package utilidades;
import java.time.LocalDate;
import java.util.Scanner;

import static utilidades.FuncionesCadenas.*;
import static utilidades.FuncionesFechas.*;

import proyecto.Categoria;
import proyecto.Proyecto;
import proyecto.Recompensa;
import usuario.*;
import gestor.*;
import inversor.*;

public final class FuncionesVarias {

    //Apartado de configuración de usuarios (es igual para todos)
    public static void configuracion(Usuario usuario) {
        Scanner s = new Scanner(System.in);
        System.out.println("CONFIGURACION");
        int opcion;
        do {
            System.out.println("1. Cambiar usuario");
            System.out.println("2. Cambiar contraseña");
            System.out.println("3. Modificar correo electrónico");
            System.out.println("4. Guardar cambios");
            opcion = Integer.parseInt(s.nextLine());
            switch (opcion) {
                case 1:
                    System.out.println("Usuario " + usuario.getNombre());
                    System.out.println("Introduzca su nuevo nombre de usuario: ");
                    usuario.setNombre(s.nextLine());
                    System.out.println("Nombre de usuario modificado-> " + usuario.getNombre());
                    break;
                case 2:
                    System.out.println("Contraseña actual ->" + usuario.getContrasena());
                    System.out.println("Introduzca su nueva contraseña: ");
                    usuario.setContrasena(s.nextLine());
                    System.out.println("Nueva contraseña -> " + usuario.getContrasena());
                    break;
                case 3:
                    System.out.println("Correo electrónico -> " + usuario.getCorreo());
                    System.out.println("Introduzca su nuevo correo electrónico: ");
                    usuario.setCorreo(s.nextLine());
                    System.out.println("Nuevo correo electrónico -> " + usuario.getCorreo());
                    break;
                case 4:
                    System.out.println("Cambios guardados");
                    break;
                default:
                    System.out.println("Por favor, introduzca una opción válida.");
                    break;
            }
        }while (opcion!=4);
    }

    //Crea una nueva contraseña, comprobando que esta cumpla con los requisitos de seguridad
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

    //Crea un usuario, gestor o inversor, según lo que se haya elegido en el programa principal
    public static Usuario datosUsuario(int opcion){
        Scanner s = new Scanner(System.in);
        System.out.println("Introduzca su correo electrónico");
        String correo = s.nextLine();
        System.out.println("Introduzca su nombre de usuario");
        String nombreUsuario= s.nextLine();

        String contrasena = crearcontrasena();

        if (opcion==1){
            return new Inversor(nombreUsuario, contrasena, correo);
        }else{
            return new Gestor(nombreUsuario, contrasena, correo);
        }
    }

   //Esta función devuelve un proyecto, se usa para agregar un proyecto nuevo o para modificar uno existente
    public static Proyecto datosProyecto(){
        Scanner s = new Scanner(System.in);
        String titulo, descripcion;
        do{
            System.out.print("Introduzca el título del proyecto: ");
            titulo= s.nextLine();
        }while(!comprobarLongitud(titulo));

        do{
            System.out.print("Introduzca la descripción del proyecto: ");
            descripcion = s.nextLine();
        }while(!comprobarLongitud(descripcion));

        System.out.print("Escoja una de las siguientes categorías: ");
        System.out.println("-> Arte");
        System.out.println("-> Tecnología");
        System.out.println("-> Cine");
        System.out.println("-> Música");
        System.out.println("-> Juegos");
        System.out.println("-> Comida");
        System.out.println("-> Moda");
        Categoria categoriaElegida= Categoria.valueOf(s.nextLine().toUpperCase());

        System.out.print("Introduzca la cantidad de inversión necesaria para el proyecto: ");
        double cantidad= Double.parseDouble(s.nextLine());

        System.out.print("Introduzca la fecha de apertura para recibir inversiones (dd/MM/yyy): ");
        LocalDate fechaInicio= convertirAFecha(s.nextLine());
        System.out.print("Introduzca la fecha de cierre (dd/MM/yyy): ");
        LocalDate fechaCierre= convertirAFecha(s.nextLine());

        Proyecto nuevoProyecto= new Proyecto(titulo, descripcion, cantidad, fechaInicio, fechaCierre, categoriaElegida);

        for (int i = 1; i <= 3; i++) {
            System.out.print("Descripción de recompensa nº" + i + ": ");
            String descripcionRecompensa = s.nextLine();
            System.out.print("Cantidad mínima para obtener la recompensa nº" + i + ": ");
            double cantidadMinimaRecompensa = Double.parseDouble(s.nextLine());
            Recompensa recompensa = new Recompensa(descripcionRecompensa, cantidadMinimaRecompensa);
            nuevoProyecto.agregarRecompensa(recompensa);
        }

        return nuevoProyecto;
    }

    public static void muestraGrafico(int[] cantidad, int[] cantidadAportada, int proyecto) {
        float grafico = (float) (cantidadAportada[proyecto] * 100) / cantidad[proyecto];
        System.out.println("Gráfica de financiación");
        System.out.print(grafico + "% \u2192 ");
        for (int i = 0; i <= grafico; i++) {
            System.out.print("\u001B[36m\u275A");
            if (i == 100) break;
        }
    }
}
