package proyecto;

import inversor.Inversor;

public final class ProyectoControlador {

    private GestionProyectos modelo;
    private ProyectoVista vista;

    public ProyectoControlador(GestionProyectos proyectosDeLaPlataforma,ProyectoVista vista) {
        this.modelo = proyectosDeLaPlataforma;
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

    public void invertirEnProyecto(int idProyecto, Inversor inversor){
        int posicionProyecto = modelo.buscarProyecto(idProyecto);
        if(posicionProyecto != -1){
            boolean invertidoCorrectamente = modelo.invertirEnProyecto(posicionProyecto, inversor);
            if (invertidoCorrectamente){
                System.out.println("Invertido correctamente.");
            } else {
                System.out.println("No se puedo invertir.");
            }
        }


    }



}
