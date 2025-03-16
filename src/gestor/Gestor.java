package gestor;
import proyecto.Proyecto;
import usuario.TipoUsuario;
import usuario.Usuario;

public final class Gestor extends Usuario {
    private Proyecto [] proyectos;
    private int numProyectos;

    public Gestor(String nombre, String contrasena, String correo) {
        super(nombre, contrasena, correo, TipoUsuario.GESTOR);
        this.proyectos = new Proyecto[10];
        this.numProyectos = 0;
    }

    public String toString() {
        return "Gestor " + super.getNombre() + " con " + numProyectos + " proyectos.";
    }

    public void agregarProyecto(Proyecto proyecto) {
        if (numProyectos < proyectos.length) {
            proyectos[numProyectos++] = proyecto;
            System.out.println("Proyecto añadido.");
        } else {
            System.out.println("Capacidad de proyectos alcanzada. Expandiendo array...");
            ampliarArrayProyectos();
            agregarProyecto(proyecto); // Se llama a sí misma pero esta vez el array tiene capacidad para el proyecto nuevo
        }
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

    public int buscarProyecto(int idProyecto) {
        for (int i = 0; i < numProyectos; i++) {
            if (proyectos[i].getId() == idProyecto) return i;
        }
        System.out.println("Proyecto no encontrado.");
        return -1;
    }

    public void modificarProyecto(int idProyecto, Proyecto proyecto) {
        int posicion= buscarProyecto(idProyecto);
        proyectos[posicion] = proyecto;
    }

    public void mostrarProyectos() {
        for (int i = 0; i < numProyectos; i++) {
            System.out.println(proyectos[i]);
        }
    }

    private void ampliarArrayProyectos() {
        Proyecto[] nuevoArray = new Proyecto[proyectos.length * 2];
        for (int i = 0; i < proyectos.length; i++) {
            nuevoArray[i] = proyectos[i];
        }
        proyectos = nuevoArray;
    }
}
