import java.util.Scanner;

import administrador.Administrador;
import gestor.Gestor;

import proyecto.ProyectoControlador;
import proyecto.ProyectoVista;
import inversor.Inversor;
import proyecto.GestionProyectos;
import usuario.*;
import static utilidades.Funciones.*;
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
            if (opcion == 3) controladorUsuario.muestraUsuarios();
            if (opcion == 1){
                System.out.println("REGISTRO DE USUARIOS");
                do{
                    System.out.println("Seleccione el tipo de usuario");
                    System.out.println("1. Inversor");
                    System.out.println("2. Gestor");
                    opcion= Integer.parseInt(s.nextLine());
                }while (opcion!=1 && opcion!=2);
                listaUsuarios.agregarUsuario(datosUsuario(opcion));
            }else{
                System.out.println("INICIO DE SESIÓN");
                Usuario usuario;
                do {
                    System.out.println("Introduzca su nombre de usuario: ");
                    String nombreUsuario = s.nextLine();
                    usuario = listaUsuarios.devuelveUsuario(nombreUsuario);
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
                } while (!contrasenaIntroducida.equalsIgnoreCase(contrasenaUsuario));

                switch (usuario.getTipoUsuario()){
                    case ADMINISTRADOR -> {
                        Administrador administrador = (Administrador) usuario;
                        do{
                            System.out.println("MENÚ - " + "Administrador " + usuario.getNombre());
                            System.out.println("1. Panel de control");
                            System.out.println("2. Proyectos"); //todo apartado para proyectosDeLaPlataforma, que trabaja con la lista de todos los proyectos creados.
                            System.out.println("3. Configuración");
                            System.out.println("4. Cerrar sesión");
                            opcion = Integer.parseInt(s.next());
                            if (opcion == 1) panelDeControl();
                            if (opcion == 2);
                            if (opcion == 3) configuracion(administrador);
                        }while (opcion !=4);
                    }
                    case GESTOR -> {
                        Gestor gestor = (Gestor) usuario;
                        do{
                            System.out.println("MENÚ - " + "Gestor " + usuario.getNombre());
                            System.out.println("1. Configuración");
                            System.out.println("2. Mis proyectos");
                            System.out.println("3. Cerrar sesión");
                            opcion= Integer.parseInt(s.nextLine());
                            if (opcion == 1) configuracion(gestor);;
                            if (opcion == 2){
                                System.out.println("MIS PROYECTOS");
                                int indice;
                                do {
                                    System.out.println("1. Crear Proyectos");
                                    System.out.println("2. Consultar proyectos");
                                    System.out.println("3. Modificar proyectos");
                                    System.out.println("4. Eliminar proyectos");
                                    System.out.println("5. Salir");
                                    opcion = Integer.parseInt(s.nextLine());
                                    switch (opcion) {
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
                                            gestor.modificarProyecto(indice, datosProyecto());
                                            break;
                                        case 4:
                                            System.out.println("Introduzca la ID del proyecto a modificar");
                                            indice = Integer.parseInt(s.nextLine());
                                            controladorProyecto.eliminarProyecto(indice);
                                        case 5:
                                            System.out.println("Salir.");
                                            return;
                                        default:
                                            System.out.println("Por, favor, introduzca una opción correcta.");
                                            break;
                                    }
                                } while (opcion != 3);
                            }
                        }while (opcion !=3);
                    }
                }
            }
        }while (opcion != 4);
    }
}
