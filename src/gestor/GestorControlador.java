package gestor;
import proyecto.GestionProyectos;
import proyecto.Proyecto;

public final class GestorControlador {

    private GestionProyectos proyectosDeLaPlataforma;
    private Gestor modelo;
    private GestorVista vista;

    public GestorControlador(Gestor modelo, GestorVista vista, GestionProyectos proyectosDeLaPlataforma) {
        this.modelo = modelo;
        this.vista = vista;
        this.proyectosDeLaPlataforma = proyectosDeLaPlataforma;
    }

    public void mostrarProyectos(){
        vista.muestraProyectos(modelo.getProyectos());
    }

    public void agregarProyecto(Proyecto proyecto){
        modelo.agregarProyecto(proyecto);
        proyectosDeLaPlataforma.agregarProyecto(proyecto);
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
