package usuario;
import proyecto.Proyecto;

public class UsuarioControlador {
    GestionUsuarios modelo;
    UsuarioVista vista;

    public UsuarioControlador(GestionUsuarios modelo, UsuarioVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void muestraUsuarios(){
        vista.mostrarUsuarios(modelo.getUsuarios());

    }

    public void agregarUsuario(Usuario usuario){
        if (modelo.agregarUsuario(usuario)) vista.operacionExitosa();
        vista.operacionErronea();
    }

    public Usuario devuelveUsuario(String nombreUsuario){
        Usuario usuario= modelo.devuelveUsuario(nombreUsuario);
        if (usuario != null) {
            vista.operacionExitosa();
            return usuario;
        }
        vista.operacionErronea();
        return null;
    }

    public Tipo getTipoDeUsuario(Usuario usuario) {
        Tipo tipoDeUsuario= modelo.getTipoDeUsuario(usuario);
        if (tipoDeUsuario != null) {
            vista.operacionExitosa();
            return tipoDeUsuario;
        }
        vista.operacionErronea();
        return null;
    }

    public void cambiarEstadoUsuario(String nombreUsuario){
        if(modelo.estaBloqueado(nombreUsuario)) {
            modelo.desbloquearUsuario(nombreUsuario);
            vista.operacionExitosa();
        }else{
            modelo.bloquearUsuario(nombreUsuario);
            vista.operacionErronea();
        }
        vista.operacionExitosa();
    }

    public void agregarProyectoGestor(Proyecto proyecto, String nombreUsuario){
        if (modelo.agregarProyectoGestor(proyecto, nombreUsuario)) vista.operacionExitosa();
        else vista.operacionErronea();
    }
    public void eliminarProyectoGestor(int idProyecto, String nombreUsuario){
        if (modelo.eliminarProyectoGestor(idProyecto, nombreUsuario)) vista.operacionExitosa();
        else vista.operacionErronea();
    }
    public void modificarProyectoGestor(Proyecto proyecto,int idProyecto, String nombreUsuario){
        if (modelo.modificarProyectoGestor(proyecto, idProyecto, nombreUsuario)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void mostrarProyectosGestor(String nombreUsuario){
        vista.mostrarProyectos(modelo.getProyectos(nombreUsuario));
    }
}
