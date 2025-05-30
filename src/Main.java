import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

import administrador.Administrador;
import inversion.dao.DAOInversionSQL;
import proyecto.daoProyecto.ControladorProyectoDAO;
import proyecto.daoProyecto.DAOProyectoSQL;
import proyecto.daoRecompensa.DAORecompensaSQL;
import usuario.dao.ControladorUsuarioDAO;
import usuario.dao.DAOUsuarioSQL;
import utilidades.DAOManager;
import gestor.Gestor;

import proyecto.Proyecto;
import proyecto.ProyectoControlador;
import proyecto.ProyectoVista;
import inversor.Inversor;
import proyecto.GestionProyectos;
import usuario.*;

import static utilidades.FuncionesFechas.convertirAString;
import static utilidades.FuncionesVarias.*;
import static utilidades.FuncionesCorreos.autentificacionDeUsuario;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        DAOManager daoManager = DAOManager.getSinglentonInstance();
        try {
            daoManager.open();
            // Obtenemos la conexión para pasársela a los DAOs
            Connection connection = daoManager.getConnection();

            //Creación de las vistas
            UsuarioVista vistaUsuarios = new UsuarioVista("🟢","🔴");
            ProyectoVista vistaDeProyectos = new ProyectoVista("🟢","🔴");

            //Creación de los modelos
            DAOUsuarioSQL daoUsuario = new DAOUsuarioSQL();
            DAOProyectoSQL daoProyecto = new DAOProyectoSQL();
            DAORecompensaSQL daoRecompensa = new DAORecompensaSQL();
            DAOInversionSQL daoInversion = new DAOInversionSQL();

            //Creación de los controladores
            ControladorProyectoDAO controladorProyectoDAO = new ControladorProyectoDAO(daoProyecto, vistaDeProyectos, daoManager);
            ControladorUsuarioDAO controladorUsuarioDAO= new ControladorUsuarioDAO(daoUsuario, vistaUsuarios, daoManager);
            controladorUsuarioDAO.cargarUsuarios();

            //Modelos y controladores para persistencia en ficheros
            GestionUsuarios listaUsuarios = new GestionUsuarios();
            UsuarioControlador controladorUsuario = new UsuarioControlador(listaUsuarios, vistaUsuarios);
            GestionProyectos proyectosDeLaPlataforma = new GestionProyectos();
            ProyectoControlador controladorProyecto = new ProyectoControlador(proyectosDeLaPlataforma, vistaDeProyectos);

            //Creamos nuestro archivo properties
            Properties properties = new Properties();
            //Cargamos la ruta en donde se guardarán los datos.
            properties.load(new FileReader("configuracion/setup.properties"));

            //Ruta de usuarios BBDD
            properties.setProperty("rutaUsuarios","ficheros/usuariosBBDD.txt");
            //Ruta de proyectos BBDD
            properties.setProperty("rutaProyectos","ficheros/proyectosBBDD.txt");
            //Ruta de los logs
            properties.setProperty("rutaLogs", "ficheros/log.txt");

            //Guardamos las rutas que acabamos de definir en nuestro archivo properties
            properties.store(new FileWriter("./configuracion/setup.properties"), "Configuracion del programa");

            //Por último, cargamos los usuarios y proyectos de la BBDD
            controladorUsuarioDAO.cargarUsuarios();
            controladorProyectoDAO.cargarProyectos();


            int opcion;
            do {
                System.out.println("Bienvenido ༼ つ ◕_◕ ༽つ");
                System.out.println("1. Registrarse");
                System.out.println("2. Iniciar sesión");
                System.out.println("3. Acceder como invitado");
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
                    controladorUsuarioDAO.insert(nuevoUsuario);
//                    controladorUsuario.guardarUsuarios(properties.getProperty("rutaUsuarios"));
                }

                if (opcion == 2) {
                    System.out.println("INICIO DE SESIÓN");
                    Usuario usuario;
                    do {
                        System.out.println("» Introduzca su nombre de usuario: ");
                        usuario = controladorUsuarioDAO.getUsuarioPorNombre(s.nextLine());
                    } while (usuario == null);

                    String contrasenaUsuario = controladorUsuarioDAO.getContrasena(usuario);
                    String contrasenaIntroducida;
                    int intentos = 3;
                    do {
                        System.out.println("» Introduzca su contraseña: ");
                        System.out.println(intentos + " intentos restantes.");
                        contrasenaIntroducida = s.nextLine();

                        if (contrasenaIntroducida.equals(contrasenaUsuario)) {
                            System.out.println("Contraseña correcta. ¡Bienvenido!");
                            //Mostramos el último inicio de sesión de un usuario
                            System.out.println("Último inicio de sesión: " + properties.getProperty(usuario.getNombre()));
                            //Guardamos el nuevo inicio de sesión en la plataforma en nuestro archivo properties.
                            properties.setProperty(usuario.getNombre(), convertirAString(LocalDateTime.now()));
                            properties.store(new FileWriter("./configuracion/setup.properties"), "Ultimos inicios de sesion");
                            controladorUsuario.inicioSesion(usuario);
                        }else{
                            System.out.println("Contraseña incorrecta");
                            intentos--;
                        }

                    } while (intentos > 0 && !contrasenaIntroducida.equals(contrasenaUsuario));

                    if (intentos == 0) controladorUsuario.bloquearUsuario(usuario);
                    controladorUsuario.guardarUsuarios("ficheros/usuarios.txt"); // cuando un usuario quede bloqueado, se guarda

                    switch (usuario.getTipoUsuario()) {
                        case ADMINISTRADOR -> {
                            Administrador administrador = (Administrador) usuario;
                            int opcionAdmin;
                            do {
                                System.out.println("MENÚ - ADMINISTRADOR");
                                System.out.println("1. Panel de control");
                                System.out.println("2. Proyectos");
                                System.out.println("3. Configuración de usuario");
                                System.out.println("4. Configuración del programa");
                                System.out.println("5. Últimas conexiones");
                                System.out.println("6. Cerrar sesión");
                                opcionAdmin = Integer.parseInt(s.nextLine());

                                if (opcionAdmin == 1) {
                                    System.out.println("PANEL DE CONTROL");
                                    controladorUsuario.muestraEstadoUsuarios();
                                    String nombreUsuario;
                                    do{
                                        System.out.println("Introduzca un nombre de usuario para bloquearlo/desbloquearlo");
                                        nombreUsuario= s.nextLine();
                                    }while (controladorUsuarioDAO.getUsuarioPorNombre(nombreUsuario) == null);

                                    controladorUsuarioDAO.cambiarEstadoUsuario(nombreUsuario);

//                                    controladorUsuario.cambiarEstadoUsuario(nombreUsuario);
//                                    controladorUsuario.guardarUsuarios("ficheros/usuarios.txt");
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
                                            case 1 -> {
                                                Proyecto proyecto= datosProyecto(usuario);
                                                controladorProyecto.agregarProyecto(proyecto);
                                                controladorProyectoDAO.insert(proyecto);
                                                controladorProyecto.guardarProyectos("ficheros/proyectos.txt");
                                            }
                                            case 2 -> controladorProyectoDAO.mostrarTodos();
                                            case 3 -> {
                                                controladorProyecto.mostrarProyectos();
                                                System.out.println("Introduzca la ID del proyecto a modificar");
                                                int indice = Integer.parseInt(s.nextLine());
                                                Proyecto proyecto= datosProyecto(usuario);
                                                controladorProyecto.modificarProyecto(indice,proyecto );
                                                controladorProyectoDAO.update(proyecto);
                                                controladorProyecto.guardarProyectos("ficheros/proyectos.txt");
                                            }
                                            case 4 -> {
                                                System.out.println("Introduzca la ID del proyecto a eliminar");
                                                int indice = Integer.parseInt(s.nextLine());
                                                controladorProyecto.eliminarProyecto(indice);
                                                controladorProyectoDAO.delete(indice);
                                                controladorProyecto.guardarProyectos("ficheros/proyectos.txt");
                                            }
                                        }
                                    } while (entrada != 5);
                                } else if (opcionAdmin == 3) {
                                    configuracion(administrador);
                                }else if (opcionAdmin == 4){
                                    System.out.println("CONFIGURACIÓN DEL PROGRAMA");
                                    System.out.println("Modo invitado » " + (properties.getProperty("invitado")));
                                    System.out.println("Ruta de usuarios » " + properties.getProperty("rutaUsuarios"));
                                    System.out.println("Ruta de proyectos » " + properties.getProperty("rutaProyectos"));
                                    System.out.println("Ruta de logs » " + properties.getProperty("rutaLogs"));
                                }else if(opcionAdmin == 5){
                                    ultimasConexiones();
                                }
                            } while (opcionAdmin != 6);
                            System.out.println("Cerrando la sesión...");
                            controladorUsuario.cierreSesion(administrador);
                        }

                        case GESTOR -> {
                            if(controladorUsuario.estaBloqueado(usuario)){
                                break;
                            }
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
                                                Proyecto nuevoProyecto = datosProyecto(gestor);
                                                controladorProyectoDAO.insert(nuevoProyecto);
                                                controladorProyecto.agregarProyecto(nuevoProyecto);
                                                controladorUsuario.agregarProyectoGestor(nuevoProyecto, gestor.getNombre());
                                                controladorProyecto.guardarProyectos("ficheros/proyectos.txt");
                                            }
                                            case 2 -> controladorUsuario.mostrarProyectosGestor(gestor.getNombre());
                                            case 3 -> {
                                                controladorUsuario.mostrarProyectosGestor(gestor.getNombre());
                                                System.out.println("Introduzca la ID del proyecto a modificar");
                                                int indice = Integer.parseInt(s.nextLine());
                                                Proyecto proyecto= datosProyecto(gestor);
                                                controladorProyectoDAO.update(proyecto);
                                                controladorUsuario.modificarProyectoGestor(proyecto, indice, gestor.getNombre());
                                                controladorProyecto.guardarProyectos("ficheros/proyectos.txt");
                                            }
                                            case 4 -> {
                                                System.out.println("Introduzca la ID del proyecto a eliminar");
                                                int indice = Integer.parseInt(s.nextLine());
                                                controladorProyectoDAO.delete(indice);
                                                controladorUsuario.eliminarProyectoGestor(indice, gestor.getNombre());
                                                controladorProyecto.guardarProyectos("ficheros/proyectos.txt");
                                            }
                                        }
                                    } while (entrada != 5);
                                }
                            } while (opcionGestor != 3);
                            System.out.println("Cerrando la sesión...");
                            controladorUsuario.cierreSesion(gestor);

                        }
                        case INVERSOR -> {
                            if(controladorUsuario.estaBloqueado(usuario)){
                                break;
                            }
                            Inversor inversor = (Inversor) usuario;
                            int opcionInversor;
                            do {
                                System.out.println("MENÚ - INVERSOR " + inversor.getNombre());
                                System.out.println("1. Configuración");
                                System.out.println("2. Ver proyectos");
                                System.out.println("3. Invertir en proyecto");
                                System.out.println("4. Ver inversiones");
                                System.out.println("5. Cartera digital");
                                System.out.println("6. Cerrar sesión");
                                opcionInversor = Integer.parseInt(s.nextLine());

                                if (opcionInversor == 1) configuracion(inversor);
                                if (opcionInversor == 2) controladorProyecto.mostrarProyectos();
                                if (opcionInversor == 3){
                                    System.out.print("Introduce el ID del proyecto donde sea invertir: ");
                                    int idProyecto = Integer.parseInt(s.nextLine());
                                    controladorProyecto.invertirEnProyecto(idProyecto, inversor);
                                    controladorProyecto.guardarProyectos("ficheros/proyectos.txt");
                                }
                                if (opcionInversor == 4){
                                    controladorUsuario.mostrarInversiones(inversor);

                                }
                                if (opcionInversor == 5){
                                    int opcionCartera;
                                    do {
                                        System.out.println("CARTERA DIGITAL \n" +
                                                "1. Ver saldo \n" +
                                                "2. Recargar saldo\n" +
                                                "3. Salir");
                                        opcionCartera = Integer.parseInt(s.nextLine());
                                        switch (opcionCartera){
                                            case 1 -> System.out.println("Saldo actual: " + inversor.getSaldo());
                                            case 2 -> {
                                                System.out.print("Cantidad: ");
                                                double cantidad = Double.parseDouble(s.nextLine());
                                                controladorUsuario.recargarSaldo(cantidad, inversor);
                                            }
                                        }
                                    }while (opcionCartera != 3);
                                }
                            } while (opcionInversor != 6);
                            System.out.println("Cerrando la sesión...");
                            controladorUsuario.cierreSesion(inversor);
                        }
                    }
                }
                if (opcion == 3 && properties.getProperty("invitado").equals("activado")){
                    System.out.println("CONSULTA DE PROYECTOS");
                    controladorProyecto.mostrarProyectos();
                }else{
                    System.out.println("Actualmente no se puede acceder como invitado a la plataforma");
                }
            } while (opcion != 4);
//        controladorUsuario.guardarUsuarios("ficheros/usuarios.txt");
//        controladorProyecto.guardarProyectos("ficheros/proyectos.txt");
            System.out.println("SALIENDO...");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try{
                daoManager.close();
            }catch (Exception e) {
                System.out.println("Error al cerrar la conexión: ");
                e.printStackTrace();
            }
        }
    }

}
