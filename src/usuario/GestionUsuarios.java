package usuario;
import gestor.Gestor;
import proyecto.Proyecto;

import java.util.ArrayList;

public final class GestionUsuarios{
    private ArrayList<Usuario> usuarios;

    public GestionUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuarios() { return usuarios; }

    public boolean agregarUsuario(Usuario usuario) {
        return usuarios.add(usuario);
    }

    public int buscarUsuario(String nombreUsuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombre().equals(nombreUsuario)) {
                return i;
            }
        }
        return -1;
    }

    public boolean eliminarUsuario(String nombreUsuario) {
        int posicion = buscarUsuario(nombreUsuario);
        if (posicion != -1) {
            usuarios.remove(posicion);
            return true;
        }
        return false;
    }

    public Usuario devuelveUsuario(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean bloquearUsuario(String nombreUsuario) {
        int posicion = buscarUsuario(nombreUsuario);
        if (posicion != -1) {
            usuarios.get(posicion).bloquear();
            return true;
        }
        return false;
    }

    public boolean desbloquearUsuario(String nombreUsuario) {
        int posicion = buscarUsuario(nombreUsuario);
        if (posicion != -1) {
            usuarios.get(posicion).desbloquear();
            return true;
        }
        return false;
    }

    public boolean agregarProyectoGestor(Proyecto proyecto, String nombreUsuario){
        Gestor usuarioGestor= (Gestor) devuelveUsuario(nombreUsuario);
        usuarioGestor.agregarProyecto(proyecto);
        return true;
    }

    public boolean eliminarProyectoGestor(int idProyecto, String nombreUsuario){
        Gestor usuarioGestor= (Gestor) devuelveUsuario(nombreUsuario);
        if(usuarioGestor.eliminarProyecto(idProyecto)) return true;
        return false;
    }

    public boolean modificarProyectoGestor(Proyecto proyecto, int idProyecto, String nombreUsuario){
        Gestor usuarioGestor= (Gestor) devuelveUsuario(nombreUsuario);
        if(usuarioGestor.modificarProyecto(idProyecto, proyecto)) return true;
        return false;
    }

}
