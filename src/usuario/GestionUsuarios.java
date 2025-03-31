package usuario;
import gestor.Gestor;
import inversor.Inversor;
import proyecto.Proyecto;
import java.util.ArrayList;
import java.util.HashMap;
import static usuario.Tipo.*;

public final class GestionUsuarios{

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

    public boolean estaBloqueado(String nombreUsuario) {
        Usuario usuario = usuarios.get(nombreUsuario);
        return usuario != null && usuario.estaBloqueado();
    }

    public boolean bloquearUsuario(String nombreUsuario) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario != null) {
            usuario.bloquear();
            return true;
        }
        return false;
    }

    public boolean desbloquearUsuario(String nombreUsuario) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario != null) {
            usuario.desbloquear();
            return true;
        }
        return false;
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


}
