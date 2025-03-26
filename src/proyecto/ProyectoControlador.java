package proyecto;

public final class ProyectoControlador {

    private GestionProyectos modelo1;
    private ProyectoVista vista;

    public ProyectoControlador(GestionProyectos proyectosDeLaPlataforma,ProyectoVista vista) {
        this.modelo1 = proyectosDeLaPlataforma;
        this.vista = vista;

    }

    public void mostrarProyectos(){
        vista.muestraProyectos(modelo1.getProyectos());
    }

    public void agregarProyecto(Proyecto proyecto){
        modelo1.agregarProyecto(proyecto);
        vista.operacionExitosa();
    }

    public void eliminarProyecto(int idProyecto){
        if (modelo1.eliminarProyecto(idProyecto))vista.operacionExitosa();
        else vista.operacionErronea();

    }

    public void modificarProyecto(int idProyecto, Proyecto proyecto){
        if (modelo1.modificarProyecto(idProyecto, proyecto))vista.operacionExitosa();
        else vista.operacionErronea();

    }
}
