package gestor;
import proyecto.Proyecto;

public final class GestorControlador {

    private Gestor modelo;
    private GestorVista vista;

    public GestorControlador(Gestor modelo, GestorVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void mostrarProyectos(){
        vista.muestraProyectos(modelo.getProyectos());
    }

    public void agregarProyecto(Proyecto proyecto){
        modelo.agregarProyecto(proyecto);
        vista.operacionExitosa();
    }

    public void eliminarProyecto(int idProyecto){
        if (modelo.eliminarProyecto(idProyecto))vista.operacionExitosa();
        else vista.operacionErronea();

    }

    public void modificarProyecto(int idProyecto, Proyecto proyecto){
        if (modelo.modificarProyecto(idProyecto, proyecto))vista.operacionExitosa();
        else vista.operacionErronea();

    }
}
