package usuario;
import administrador.Administrador;
import gestor.Gestor;
import inversion.Inversion;
import inversor.Inversor;
import proyecto.Proyecto;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import static usuario.Tipo.*;
import static utilidades.FuncionesFechas.convertirAString;

public final class GestionUsuarios implements Serializable {

    private HashMap<String, Usuario> usuarios;

    public GestionUsuarios() {
        this.usuarios = new HashMap<>();
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean agregarUsuario(Usuario usuario) {
        if (!usuarios.containsKey(usuario.getNombre())) {
            usuarios.put(usuario.getNombre(), usuario);
            return true;
        }
        return false;
    }

    public Usuario devuelveUsuario(String nombreUsuario) {
        return usuarios.get(nombreUsuario);
    }

    public boolean eliminarUsuario(String nombreUsuario) {
        return usuarios.remove(nombreUsuario) != null;
    }

    public Tipo getTipoDeUsuario(String nombreUsuario){
        return usuarios.get(nombreUsuario).getTipoUsuario();
    }

    public Tipo getTipoDeUsuario(Usuario usuario){
        return usuarios.get(usuario.getNombre()).getTipoUsuario();
    }

    public String getContrasena (Usuario usuario){ return usuarios.get(usuario.getNombre()).getContrasena(); }

    public boolean estaBloqueado(Usuario usuario) {
        return usuario != null && usuario.estaBloqueado();
    }

    public void bloquearUsuario(Usuario usuario) {
        usuario.bloquear();
    }

    public void desbloquearUsuario(Usuario usuario) {
        usuario.desbloquear();
    }

    //Esta función nos devuelve el arraylist de proyectos perteneciente a un gestor del Hashmap de usuarios.
    public ArrayList<Proyecto> getProyectos(String nombreGestor){
        Gestor gestor = (Gestor) usuarios.get(nombreGestor);
        return gestor.getProyectos();
    }

    public boolean agregarProyectoGestor(Proyecto proyecto, String nombreUsuario) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario.getTipoUsuario() == GESTOR) {
            Gestor gestor = (Gestor) usuario;
            gestor.agregarProyecto(proyecto);
            return true;
        }
        return false;
    }

    public boolean eliminarProyectoGestor(int idProyecto, String nombreUsuario){
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario.getTipoUsuario() == GESTOR) {
            Gestor gestor = (Gestor) usuario;
            gestor.eliminarProyecto(idProyecto);
            return true;
        }
        return false;
    }

    public boolean modificarProyectoGestor(Proyecto proyecto, int idProyecto, String nombreUsuario){
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario.getTipoUsuario() == GESTOR) {
            Gestor gestor = (Gestor) usuario;
            gestor.modificarProyecto(idProyecto, proyecto);
            return true;
        }
        return false;
    }

    public boolean recargarSaldo (double cantidad, Inversor inversor){
        return inversor.recargarSaldo(cantidad);
    }

    public boolean aumentaInversion(double cantidad, Inversion inversion, Inversor inversor) {
        return inversor.aumentaInversion(cantidad, inversion);
    }

    public boolean disminuyeInversion(double cantidad, Inversion inversion, Inversor inversor) {
        return inversor.disminuyeInversion(cantidad, inversion);
    }

    public ArrayList<Inversion> devuelveInversiones(Inversor inversor) {
        return inversor.getInversiones();
    }

    public void nuevoLog(String tipoLog, String nombre) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("ficheros/log.txt", true));
            bw.write(tipoLog + " " + nombre + ", " + convertirAString(LocalDateTime.now()));
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. Archivo no encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Excepción de entrada/salida");
            e.printStackTrace();
        }
    }

    public void inicioSesion(Usuario usuario){
        nuevoLog("Inicio de sesión de ", usuario.getNombre());
    }

    public void cierreSesion(Usuario usuario){
        nuevoLog("Cierre de sesión de ", usuario.getNombre());
    }

    public boolean guardarUsuarios(String ruta) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(usuarios);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cargarUsuarios(String ruta) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            usuarios = (HashMap<String, Usuario>) ois.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }




}
