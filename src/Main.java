import java.util.Scanner;

import administrador.Administrador;
import gestor.Gestor;

import proyecto.Proyecto;
import proyecto.ProyectoControlador;
import proyecto.ProyectoVista;
import inversor.Inversor;
import proyecto.GestionProyectos;
import usuario.*;
import static utilidades.Funciones.*;
import static utilidades.FuncionesCorreos.autentificacionDeUsuario;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        GestionUsuarios listaUsuarios = new GestionUsuarios(); //Modelo
        UsuarioVista vistaUsuarios = new UsuarioVista("✅","❌"); //Vista
        UsuarioControlador controladorUsuario = new UsuarioControlador(listaUsuarios, vistaUsuarios);

        GestionProyectos proyectosDeLaPlataforma = new GestionProyectos(); //Modelo
        ProyectoVista vistaDeProyectos = new ProyectoVista("✅","❌"); //Vista
        ProyectoControlador controladorProyecto = new ProyectoControlador(proyectosDeLaPlataforma, vistaDeProyectos);


        // Una instancia de cada tipo de usuario para hacer pruebas sin tener que crear el usuario cada vez
        Administrador adminPruebas = new Administrador("admin", "admin", "admin@gmail.com");
        Gestor gestorPruebas = new Gestor ("gestor", "gestor", "gestor@gmail.com");
        Inversor inversorPruebas = new Inversor ("inversor", "inversor", "inversor@gmail.com");
        listaUsuarios.agregarUsuario(adminPruebas);
        listaUsuarios.agregarUsuario(gestorPruebas);
        listaUsuarios.agregarUsuario(inversorPruebas);

        int opcion;
        do {
            System.out.println("Bienvenido ༼ つ ◕_◕ ༽つ");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Mostrar listaUsuarios actuales (para pruebas internas)");
            System.out.println("4. Salir del programa");
            opcion = Integer.parseInt(s.nextLine());
            if (opcion == 1){
                System.out.println("REGISTRO DE USUARIOS");
                int entrada;
                do{
                    System.out.println("Seleccione el tipo de usuario");
                    System.out.println("1. Inversor");
                    System.out.println("2. Gestor");
                    entrada= Integer.parseInt(s.nextLine());
                }while (entrada!=1 && entrada!=2);
                controladorUsuario.agregarUsuario(datosUsuario(opcion));
            }
            if (opcion == 2){
                System.out.println("INICIO DE SESIÓN");
                Usuario usuario;
                do {
                    System.out.println("» Introduzca su nombre de usuario: ");
                    usuario = controladorUsuario.devuelveUsuario(s.nextLine());
                } while (usuario == null);

                String contrasenaUsuario = usuario.getContrasena();
                String contrasenaIntroducida;
                int intentos=3;
                do {
                    System.out.println("» Introduzca su contraseña: ");
                    System.out.println(intentos + " intentos restantes.");
                    contrasenaIntroducida = s.nextLine();
                    intentos--;
                    if (intentos==0) usuario.bloquear();
                } while (!contrasenaIntroducida.equalsIgnoreCase(contrasenaUsuario) && intentos>0);

                //Autentiicación doble factor, todavía no la he probado.
                autentificacionDeUsuario();

                switch (controladorUsuario.getTipoDeUsuario(usuario)){
                    case ADMINISTRADOR -> {
                        Administrador administrador = (Administrador) usuario;
                        do{
                            System.out.println("MENÚ - " + "ADMINISTRADOR ");
                            System.out.println("1. Panel de control");
                            System.out.println("2. Proyectos");
                            System.out.println("3. Configuración");
                            System.out.println("4. Cerrar sesión");
                            opcion = Integer.parseInt(s.next());
                            if (opcion == 1){
                                System.out.println("PANEL DE CONTROL");
                                System.out.println("Listado de todos los usuarios");
                                controladorUsuario.muestraUsuarios();
                                System.out.println("=====================================================================");
                                System.out.println("Introduzca un nombre de usuario para bloquearlo/desbloquearlo");
                                controladorUsuario.cambiarEstadoUsuario(s.nextLine());
                            }
                            if (opcion == 2){
                                System.out.println("TODOS LOS PROYECTOS");
                                int entrada, indice;
                                do {
                                    System.out.println("1. Crear Proyectos");
                                    System.out.println("2. Consultar proyectos");
                                    System.out.println("3. Modificar proyectos");
                                    System.out.println("4. Eliminar proyectos");
                                    System.out.println("5. Salir");
                                    entrada = Integer.parseInt(s.nextLine());
                                    switch (entrada) {
                                        case 1:
                                            controladorProyecto.agregarProyecto(datosProyecto());
                                            break;
                                        case 2:
                                            controladorProyecto.mostrarProyectos();
                                            break;
                                        case 3:
                                            controladorProyecto.mostrarProyectos();
                                            System.out.println("Introduzca la ID del proyecto a modificar");
                                            indice = Integer.parseInt(s.nextLine());
                                            controladorProyecto.modificarProyecto(indice, datosProyecto());
                                            break;
                                        case 4:
                                            System.out.println("Introduzca la ID del proyecto a eliminar");
                                            indice = Integer.parseInt(s.nextLine());
                                            controladorProyecto.eliminarProyecto(indice);
                                        case 5:
                                            System.out.println("Salir.");
                                            break;
                                        default:
                                            System.out.println("Por, favor, introduzca una opción correcta.");
                                            break;
                                    }
                                } while (entrada != 5);

                            }
                            if (opcion == 3) configuracion(administrador);
                        }while (opcion !=4);
                    }
                    case GESTOR -> {
                        Gestor gestor = (Gestor) usuario;
                        String nombreUsuario= gestor.getNombre();
                        do{
                            System.out.println("MENÚ - " + "Gestor " + nombreUsuario);
                            System.out.println("1. Configuración");
                            System.out.println("2. Mis proyectos");
                            System.out.println("3. Cerrar sesión");
                            opcion= Integer.parseInt(s.nextLine());
                            if (opcion == 1) configuracion(gestor);;
                            if (opcion == 2){
                                System.out.println("MIS PROYECTOS");
                                Proyecto nuevoProyecto;
                                int entrada, indice;
                                do {
                                    System.out.println("1. Crear Proyectos");
                                    System.out.println("2. Consultar proyectos");
                                    System.out.println("3. Modificar proyectos");
                                    System.out.println("4. Eliminar proyectos");
                                    System.out.println("5. Salir");
                                    entrada = Integer.parseInt(s.nextLine());
                                    switch (entrada) {
                                        case 1:
                                            nuevoProyecto = datosProyecto();
                                            controladorProyecto.agregarProyecto(nuevoProyecto);
                                            controladorUsuario.agregarProyectoGestor(nuevoProyecto, nombreUsuario);
                                            break;
                                        case 2:
                                            controladorUsuario.mostrarProyectosGestor(nombreUsuario);
                                            break;
                                        case 3:
                                            controladorUsuario.mostrarProyectosGestor(nombreUsuario);
                                            System.out.println("Introduzca la ID del proyecto que desea modificar");
                                            indice = Integer.parseInt(s.nextLine());
                                            nuevoProyecto= datosProyecto();
                                            controladorUsuario.modificarProyectoGestor(nuevoProyecto, indice, nombreUsuario);
                                            break;
                                        case 4:
                                            System.out.println("Introduzca la ID del proyecto a eliminar");
                                            indice = Integer.parseInt(s.nextLine());
                                            controladorUsuario.eliminarProyectoGestor(indice, nombreUsuario);
                                        case 5:
                                            System.out.println("Salir.");
                                            break;
                                        default:
                                            System.out.println("Por, favor, introduzca una opción correcta.");
                                            break;
                                    }
                                } while (entrada != 5);
                            }
                        }while (opcion !=3);
                    }
                }
            }
            if (opcion == 3) controladorUsuario.muestraUsuarios();
        }while (opcion != 4);

        System.out.println("SALIENDO...");
    }
}
