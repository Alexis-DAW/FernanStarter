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

            if (opcion == 1) {
                System.out.println("REGISTRO DE USUARIOS");
                int entrada;
                do {
                    System.out.println("Seleccione el tipo de usuario");
                    System.out.println("1. Inversor");
                    System.out.println("2. Gestor");
                    entrada = Integer.parseInt(s.nextLine());
                } while (entrada != 1 && entrada != 2);

                autentificacionDeUsuario();

                Usuario nuevoUsuario = datosUsuario(entrada);
                controladorUsuario.agregarUsuario(nuevoUsuario);
            }

            if (opcion == 2) {
                System.out.println("INICIO DE SESIÓN");
                Usuario usuario;
                do {
                    System.out.println("» Introduzca su nombre de usuario: ");
                    usuario = controladorUsuario.devuelveUsuario(s.nextLine());
                } while (usuario == null);

                String contrasenaUsuario = usuario.getContrasena();
                String contrasenaIntroducida;
                int intentos = 3;

                do {
                    System.out.println("» Introduzca su contraseña: ");
                    System.out.println(intentos + " intentos restantes.");
                    contrasenaIntroducida = s.nextLine();
                    intentos--;

                    if (contrasenaIntroducida.equals(contrasenaUsuario)) {
                        System.out.println("Contraseña correcta. ¡Bienvenido!");
                        break;
                    }

                    if (intentos == 0) {
                        usuario.bloquear();
                        System.out.println("Usuario bloqueado por intentos fallidos.");
                    } else {
                        System.out.println("Contraseña incorrecta. Inténtelo de nuevo.");
                    }
                } while (intentos > 0);

                switch (controladorUsuario.getTipoDeUsuario(usuario)) {
                    case ADMINISTRADOR -> {
                        Administrador administrador = (Administrador) usuario;
                        int opcionAdmin;
                        do {
                            System.out.println("MENÚ - ADMINISTRADOR");
                            System.out.println("1. Panel de control");
                            System.out.println("2. Proyectos");
                            System.out.println("3. Configuración");
                            System.out.println("4. Cerrar sesión");
                            opcionAdmin = Integer.parseInt(s.nextLine());

                            if (opcionAdmin == 1) {
                                System.out.println("PANEL DE CONTROL");
                                controladorUsuario.muestraUsuarios();
                                System.out.println("Introduzca un nombre de usuario para bloquear/desbloquearlo");
                                controladorUsuario.cambiarEstadoUsuario(s.nextLine());
                            } else if (opcionAdmin == 2) {
                                System.out.println("TODOS LOS PROYECTOS");
                                int entrada;
                                do {
                                    System.out.println("1. Crear Proyectos");
                                    System.out.println("2. Consultar proyectos");
                                    System.out.println("3. Modificar proyectos");
                                    System.out.println("4. Eliminar proyectos");
                                    System.out.println("5. Salir");
                                    entrada = Integer.parseInt(s.nextLine());

                                    switch (entrada) {
                                        case 1 -> controladorProyecto.agregarProyecto(datosProyecto());
                                        case 2 -> controladorProyecto.mostrarProyectos();
                                        case 3 -> {
                                            controladorProyecto.mostrarProyectos();
                                            System.out.println("Introduzca la ID del proyecto a modificar");
                                            int indice = Integer.parseInt(s.nextLine());
                                            controladorProyecto.modificarProyecto(indice, datosProyecto());
                                        }
                                        case 4 -> {
                                            System.out.println("Introduzca la ID del proyecto a eliminar");
                                            int indice = Integer.parseInt(s.nextLine());
                                            controladorProyecto.eliminarProyecto(indice);
                                        }
                                    }
                                } while (entrada != 5);
                            } else if (opcionAdmin == 3) {
                                configuracion(administrador);
                            }
                        } while (opcionAdmin != 4);
                    }

                    case GESTOR -> {
                        Gestor gestor = (Gestor) usuario;
                        int opcionGestor;
                        do {
                            System.out.println("MENÚ - GESTOR " + gestor.getNombre());
                            System.out.println("1. Configuración");
                            System.out.println("2. Mis proyectos");
                            System.out.println("3. Cerrar sesión");
                            opcionGestor = Integer.parseInt(s.nextLine());

                            if (opcionGestor == 1) configuracion(gestor);
                            if (opcionGestor == 2) {
                                System.out.println("MIS PROYECTOS");
                                int entrada;
                                do {
                                    System.out.println("1. Crear Proyectos");
                                    System.out.println("2. Consultar proyectos");
                                    System.out.println("3. Modificar proyectos");
                                    System.out.println("4. Eliminar proyectos");
                                    System.out.println("5. Salir");
                                    entrada = Integer.parseInt(s.nextLine());

                                    switch (entrada) {
                                        case 1 -> {
                                            Proyecto nuevoProyecto = datosProyecto();
                                            controladorProyecto.agregarProyecto(nuevoProyecto);
                                            controladorUsuario.agregarProyectoGestor(nuevoProyecto, gestor.getNombre());
                                        }
                                        case 2 -> controladorUsuario.mostrarProyectosGestor(gestor.getNombre());
                                        case 3 -> {
                                            controladorUsuario.mostrarProyectosGestor(gestor.getNombre());
                                            System.out.println("Introduzca la ID del proyecto a modificar");
                                            int indice = Integer.parseInt(s.nextLine());
                                            controladorUsuario.modificarProyectoGestor(datosProyecto(), indice, gestor.getNombre());
                                        }
                                        case 4 -> {
                                            System.out.println("Introduzca la ID del proyecto a eliminar");
                                            int indice = Integer.parseInt(s.nextLine());
                                            controladorUsuario.eliminarProyectoGestor(indice, gestor.getNombre());
                                        }
                                    }
                                } while (entrada != 5);
                            }
                        } while (opcionGestor != 3);
                    }
                    case INVERSOR -> {
                        Inversor inversor = (Inversor) usuario;
                        int opcionInversor;
                        do {
                            System.out.println("MENÚ - INVERSOR " + inversor.getNombre());
                            System.out.println("1. Configuración");
                            System.out.println("2. Ver proyectos");
                            System.out.println("3. Ver inversiones");
                            System.out.println("4. Cartera digital");
                            System.out.println("5. Cerrar sesión");
                            opcionInversor = Integer.parseInt(s.nextLine());

                            if (opcionInversor == 1) configuracion(inversor);
                            if (opcionInversor == 2) {
                                controladorProyecto.mostrarProyectos();

                            }

                        } while (opcionInversor != 5);


                    }

                }
            }

            if (opcion == 3) controladorUsuario.muestraUsuarios();
        } while (opcion != 4);

        System.out.println("SALIENDO...");

    }
}
