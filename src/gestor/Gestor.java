package gestor;
import proyecto.Proyecto;
import usuario.Usuario;

public final class Gestor extends Usuario {
    private Proyecto [] proyectos;
    private int numProyectos;

    public Gestor(String nombre, String contrasena, String correo) {
        super(nombre, contrasena, correo);
        this.proyectos = new Proyecto[3];
        this.numProyectos = 0;
    }

    public String toString() {
        return "Gestor " + super.getNombre() + " con " + numProyectos + " proyectos.";
    }

    public void agregarProyecto(Proyecto proyecto) {
        if (numProyectos < proyectos.length) {
            proyectos[numProyectos++] = proyecto;
        } else {
            System.out.println("Capacidad de proyectos alcanzada. Expandiendo array...");
            ampliarArrayProyectos();
            agregarProyecto(proyecto); // Se llama a sí misma pero esta vez el array tiene capacidad para el proyecto nuevo
        }
    }
    private void ampliarArrayProyectos() {
        Proyecto[] nuevoArray = new Proyecto[proyectos.length * 2];
        for (int i = 0; i < proyectos.length; i++) {
            nuevoArray[i] = proyectos[i];
        }
        proyectos = nuevoArray;
    }

    public boolean eliminarProyecto(int idProyecto) {
        for (int i = 0; i < numProyectos; i++) {
            if (proyectos[i].getId() == idProyecto) {

                for (int j = i; j < numProyectos - 1; j++) {
                    proyectos[j] = proyectos[j + 1];
                }
                proyectos[numProyectos - 1] = null;
                numProyectos--;
                System.out.println("Proyecto eliminado.");
                return true;
            }
        }
        System.out.println("Proyecto no encontrado.");
        return false;
    }

    public Proyecto buscarProyecto(int idProyecto) {
        for (int i = 0; i < numProyectos; i++) {
            if (proyectos[i].getId() == idProyecto) {
                return proyectos[i];
            }
        }
        return null;
    }


}
