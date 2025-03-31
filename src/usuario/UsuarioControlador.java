package usuario;
import inversor.Inversor;
import proyecto.Proyecto;

import java.util.ArrayList;
import java.util.HashMap;

public class UsuarioControlador {
    GestionUsuarios modelo;
    UsuarioVista vista;

    public UsuarioControlador(GestionUsuarios modelo, UsuarioVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void muestraUsuarios(){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>(modelo.getUsuarios().values());
        vista.mostrarUsuarios(listaUsuarios);
    }

    public void agregarUsuario(Usuario usuario){
        if (modelo.agregarUsuario(usuario)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public Usuario devuelveUsuario(String nombreUsuario){
        Usuario usuario = modelo.devuelveUsuario(nombreUsuario);
        if (usuario != null) {
            vista.operacionExitosa();
            return usuario;
        }
        vista.operacionErronea();
        return null;
    }

    public String getContrasena (Usuario usuario){
        String contrasena= modelo.getContrasena(usuario);
        if (contrasena != null) {
            vista.operacionExitosa();
            return contrasena;
        }
        vista.operacionErronea();
        return null;
    }

    public Tipo getTipoDeUsuario(Usuario usuario) {
        Tipo tipoDeUsuario = modelo.getTipoDeUsuario(usuario);
        if (tipoDeUsuario != null) {
            vista.operacionExitosa();
            return tipoDeUsuario;
        }
        vista.operacionErronea();
        return null;
    }

    public void bloquearUsuario(Usuario usuario){
        modelo.bloquearUsuario(usuario);
    }

    public void cambiarEstadoUsuario(Usuario usuario){
        if(modelo.estaBloqueado(usuario)) {
            modelo.desbloquearUsuario(usuario);
            vista.operacionExitosa();
        }else{
            modelo.bloquearUsuario(usuario);
            vista.operacionErronea();
        }
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

    public void recargarSaldo (double cantidad, Inversor inversor){
        boolean recargadoCorrectamente = modelo.recargarSaldo(cantidad, inversor);
        if (recargadoCorrectamente) vista.recargadoConExito(cantidad, inversor.getSaldo());
        else vista.recargadoSinExito(inversor.getSaldo());
    }

}
