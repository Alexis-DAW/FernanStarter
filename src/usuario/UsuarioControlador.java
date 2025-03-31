package usuario;
import inversion.Inversion;
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

    public void muestraEstadoUsuarios(){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>(modelo.getUsuarios().values());
        vista.mostrarEstadoUsuarios(listaUsuarios);
    }

    public void agregarUsuario(Usuario usuario){
        if (modelo.agregarUsuario(usuario)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public Usuario devuelveUsuario(String nombreUsuario){
        Usuario usuario = modelo.devuelveUsuario(nombreUsuario);
        if (usuario != null) {
            return usuario;
        }
        return null;
    }

    public String getContrasena (Usuario usuario){
        String contrasena= modelo.getContrasena(usuario);
        if (contrasena != null) {
            return contrasena;
        }
        vista.operacionErronea();
        return null;
    }

    public Tipo getTipoDeUsuario(Usuario usuario) {
        Tipo tipoDeUsuario = modelo.getTipoDeUsuario(usuario);
        if (tipoDeUsuario != null) {
            return tipoDeUsuario;
        }
        return null;
    }
    public boolean estaBloqueado(Usuario usuario){
        if(modelo.estaBloqueado(usuario)){
            vista.bloqueado();
            return true;
        }
        return false;
    }

    public void bloquearUsuario(Usuario usuario){
        modelo.bloquearUsuario(usuario);
    }

    public void cambiarEstadoUsuario(String nombreUsuario){
        Usuario usuario = modelo.devuelveUsuario(nombreUsuario);
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

    public void aumentaInversion(double cantidad, Inversion inversion, Inversor inversor) {
        boolean aumentadaConExito = modelo.aumentaInversion(cantidad, inversion, inversor);
        if (aumentadaConExito) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void disminuyeInversion(double cantidad, Inversion inversion, Inversor inversor) {
        boolean disminuidaConExito = modelo.disminuyeInversion(cantidad, inversion, inversor);
        if (disminuidaConExito) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void mostrarInversiones(Inversor inversor){
        ArrayList<Inversion> inversiones = modelo.devuelveInversiones(inversor);
        vista.mostrarInversiones(inversiones);
    }

}
