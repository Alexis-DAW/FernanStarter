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

    //Esta función nos devuelve el arraylist de proyectos perteneciente a un gestor de la lista.
    public ArrayList<Proyecto> getProyectos(String nombreUsuario){
        int indice= buscarNombreDeUsuario(nombreUsuario);
        Gestor gestor= (Gestor) usuarios.get(indice);
        return gestor.getProyectos();
    }

    public Tipo getTipoDeUsuario(Usuario usuario){
        int indice= buscarUsuario(usuario);
        return usuarios.get(indice).getTipoUsuario();
    }

    public boolean estaBloqueado(String nombreUsuario){
        int indice= buscarNombreDeUsuario(nombreUsuario);
        return usuarios.get(indice).estaBloqueado();
    }

    public boolean agregarUsuario(Usuario usuario) {
        return usuarios.add(usuario);
    }

    //Devuelve el indice (la posición) donde se encuentra un usuario en el arraylist
    public int buscarUsuario(Usuario usuario){
        return usuarios.indexOf(usuario);
    }

   //Funcion son sobrecarga, devuelve el indice donde se encuentra un nombre de usuario en el arraylist
    public int buscarNombreDeUsuario(String nombreUsuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombre().equals(nombreUsuario)) {
                return i;
            }
        }
        return -1;
    }

    public boolean eliminarUsuario(String nombreUsuario) {
        int indice = buscarNombreDeUsuario(nombreUsuario);
        if (indice!= -1) {
            usuarios.remove(indice);
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
        int indice= buscarNombreDeUsuario(nombreUsuario);
        if (indice != -1) {
            usuarios.get(indice).bloquear();
            return true;
        }
        return false;
    }

    public boolean desbloquearUsuario(String nombreUsuario) {
        int posicion = buscarNombreDeUsuario(nombreUsuario);
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
