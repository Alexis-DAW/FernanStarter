import java.util.Scanner;

import gestor.Gestor;
import usuario.*;
import static utilidades.Funciones.*;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        GestionUsuarios usuarios = new GestionUsuarios(100);

        int opcion;
        do {
            System.out.println("Bienvenido ༼ つ ◕_◕ ༽つ");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Mostrar usuarios actuales (para pruebas internas)");
            opcion = Integer.parseInt(s.nextLine());
            if (opcion == 1 && usuarios.getNumUsuarios() == 0 || opcion == 2)
                registroUsuarios(usuarios);
            if (opcion == 3) System.out.println(usuarios);
        } while (opcion != 1);

        System.out.println("INICIO DE SESIÓN");

        Usuario usuario;
        do {
            System.out.println("Introduzca su nombre de usuario: ");
            String nombreUsuario = s.nextLine();
            usuario = usuarios.devuelveUsuario(nombreUsuario);
        } while (usuario == null);

        String contrasenaUsuario = usuario.getContrasena();
        String contrasenaIntroducida;
        int intentos=3;
        do {
            if (intentos==0) {
                usuario.bloquear();
                break;
            }
            System.out.println("Por favor, introduzca su contraseña");
            System.out.println(intentos + " intentos restantes.");
            contrasenaIntroducida = s.nextLine();
            intentos--;
        } while (contrasenaIntroducida.equalsIgnoreCase(contrasenaUsuario));

        switch (usuario.getTipoUsuario()){
            case GESTOR -> apartadoGestor(usuario);
//          case INVERSOR ->
//          case ADMINISTRADOR ->
        }
    }

    public static void apartadoGestor(Usuario usuario) {
        Scanner s = new Scanner(System.in);
        Gestor gestor = (Gestor) usuario;
        int opcion;
        do{
            System.out.println("MENÚ - " + "Gestor " + usuario.getNombre());
            System.out.println("1. Mis Proyectos");
            System.out.println("2. Configuración");
            System.out.println("3. Cerrar sesión");
            opcion= Integer.parseInt(s.nextLine());
            if (opcion == 1) misProyectos(gestor);
            if (opcion == 2) configuracion(gestor);
        }while (opcion !=3);
    }

    public static void misProyectos(Gestor gestor) {
        Scanner s = new Scanner(System.in);

        System.out.println("MIS PROYECTOS");
        int opcion;
        do {
            System.out.println("1. Crear Proyectos");
            System.out.println("2. Consultar proyectos");
            System.out.println("3. Modificar proyectos");
            System.out.println("4. Salir");
            opcion = Integer.parseInt(s.nextLine());
            switch (opcion) {
                case 1:
                    gestor.agregarProyecto(datosProyecto());
                    break;
                case 2:
                    gestor.mostrarProyectos();
                    break;
                case 3:
                    System.out.println("Introduzca la ID del proyecto a eliminar");
                    gestor.modificarProyecto(s.nextInt(), datosProyecto());
                    break;
                case 4:
                    System.out.println("Salir.");
                    return;
                default:
                    System.out.println("Por, favor, introduzca una opción correcta.");
                    break;
            }
        } while (opcion != 3);
    }

    public static void configuracion(Usuario usuario) {
        Scanner s = new Scanner(System.in);
        System.out.println("CONFIGURACION");
        int opcion;
        do {
            System.out.println("1. Cambiar usuario");
            System.out.println("2. Cambiar contraseña");
            System.out.println("3. Modificar correo electrónico");
            System.out.println("3. Guardar cambios");
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
        }while (opcion!=3);
    }
}
