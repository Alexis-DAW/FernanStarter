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

    public void bloquearUsuario(String nombreUsuario){
        if(modelo.bloquearUsuario(nombreUsuario)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void desbloquearUsuario(String nombreUsuario){
        if(modelo.desbloquearUsuario(nombreUsuario)) vista.operacionExitosa();
        else vista.operacionErronea();
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


}
