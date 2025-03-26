import java.util.Scanner;

import administrador.Administrador;
import gestor.Gestor;
import gestor.GestorControlador;
import gestor.GestorVista;
import proyecto.GestionProyectos;
import usuario.*;
import static utilidades.Funciones.*;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        GestionUsuarios usuarios = new GestionUsuarios();
        GestionProyectos proyectosDeLaPlataforma = new GestionProyectos();
        UsuarioVista vista = new UsuarioVista("✅","❌");
        UsuarioControlador controlador = new UsuarioControlador(usuarios, vista);
        int opcion;
        do {
            System.out.println("Bienvenido ༼ つ ◕_◕ ༽つ");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Mostrar usuarios actuales (para pruebas internas)");
            System.out.println("4. Salir del programa");
            opcion = Integer.parseInt(s.nextLine());
            if (opcion == 1) inicioSesion(usuarios, proyectosDeLaPlataforma);
            if (opcion == 2){
                registroUsuarios(usuarios);
            }
            if (opcion == 3) controlador.muestraUsuarios();
        } while (opcion != 4);
    }

    public static void inicioSesion(GestionUsuarios usuarios, GestionProyectos proyectosDeLaPlataforma) {
        Scanner s = new Scanner(System.in);
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
            case GESTOR -> apartadoGestor(usuario, proyectosDeLaPlataforma);
//            case INVERSOR ->
            case ADMINISTRADOR -> apartadoAdministrador(usuario);
        }
    }

    public static void apartadoAdministrador(Usuario usuario) {
        Scanner s = new Scanner(System.in);
        Administrador administrador = (Administrador) usuario;
        int opcion;
        do{
            System.out.println("MENÚ - " + "Administrador " + usuario.getNombre());
            System.out.println("1. Panel de control");
            System.out.println("2. Proyectos"); // todo apartado de todosMisProyectos, que trabaja con una lista de proyectos globales
            System.out.println("3. Configuración");
            System.out.println("4. Cerrar sesión");
            opcion = Integer.parseInt(s.next());
            if (opcion == 1) panelDeControl();
            if (opcion == 2); //todosMisProyectos(administrador)
            if (opcion == 3) configuracion(administrador);
        }while (opcion !=4);
    }

    public static void apartadoGestor(Usuario usuario, GestionProyectos proyectosDeLaPlataforma) {
        Scanner s = new Scanner(System.in);
        Gestor gestor = (Gestor) usuario;
        int opcion;
        do{
            System.out.println("MENÚ - " + "Gestor " + usuario.getNombre());
            System.out.println("1. Mis Proyectos");
            System.out.println("2. Configuración");
            System.out.println("3. Cerrar sesión");
            opcion= Integer.parseInt(s.nextLine());
            if (opcion == 1) misProyectos(gestor, proyectosDeLaPlataforma);
            if (opcion == 2) configuracion(gestor);
        }while (opcion !=3);
    }

    public static void misProyectos(Gestor gestor, GestionProyectos proyectosDeLaPlataforma) {
        Scanner s = new Scanner(System.in);
        GestorVista vista = new GestorVista("✅","❌");
        GestorControlador controlador = new GestorControlador(gestor, vista, proyectosDeLaPlataforma);

        System.out.println("MIS PROYECTOS");
        int opcion, indice;
        do {
            System.out.println("1. Crear Proyectos");
            System.out.println("2. Consultar proyectos");
            System.out.println("3. Modificar proyectos");
            System.out.println("4. Eliminar proyectos");
            System.out.println("5. Salir");
            opcion = Integer.parseInt(s.nextLine());
            switch (opcion) {
                case 1:
                    controlador.agregarProyecto(datosProyecto());


                    break;
                case 2:
                    controlador.mostrarProyectos();
                    break;
                case 3:
                    controlador.mostrarProyectos();
                    System.out.println("Introduzca la ID del proyecto a modificar");
                    indice = Integer.parseInt(s.nextLine());
                    gestor.modificarProyecto(indice, datosProyecto());
                    break;
                case 4:
                    System.out.println("Introduzca la ID del proyecto a modificar");
                    indice = Integer.parseInt(s.nextLine());
                    controlador.eliminarProyecto(indice);
                case 5:
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

    // Incompleto
    public static void panelDeControl() {
        Scanner s = new Scanner(System.in);
        System.out.println("PANEL DE CONTROL");
        System.out.println("Listado de todos los usuarios");

        System.out.println("=====================================================================");
        System.out.println("Introduzca un nombre de usuario para bloquearlo/desbloquearlo");

    }
}
