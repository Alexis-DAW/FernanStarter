package proyecto;
import java.util.ArrayList;

public final class GestionProyectos {
    private ArrayList<Proyecto> proyectosDeLaPlataforma;

    public GestionProyectos() { this.proyectosDeLaPlataforma = new ArrayList<>(); }

    public ArrayList<Proyecto> getProyectos() { return proyectosDeLaPlataforma; }

    /**
     * Agrega un nuevo proyecto a la lista
     *
     * @param proyecto que se va a añadir
     */
    public void agregarProyecto(Proyecto proyecto) {
        proyectosDeLaPlataforma.add(proyecto);
    }

    /**
     * Busca un proyecto existente de la lista
     *
     * @param idProyecto identifica a los proyectos
     * @return posición del proyecto con dicha ID
     * @return -1 si no se ha encontrado la ID
     */
    public int buscarProyecto(int idProyecto) {
        for (int i = 0; i< proyectosDeLaPlataforma.size(); i++){
            if (proyectosDeLaPlataforma.get(i).getId() == idProyecto) return i;
        }
        return -1;
    }

    /**
     * Elimina un proyecto existente de la lista
     *
     * @param idProyecto identifica a los proyectos
     * @return true si se ha podido eliminar dicho proyecto
     */
    public boolean eliminarProyecto(int idProyecto) {
        int indice= buscarProyecto(idProyecto);
        if (indice != -1) {
            proyectosDeLaPlataforma.remove(indice);
            return true;
        }
        return false;
    }


    /**
     * Modifica un proyecto existente de la lista
     *
     * @param idProyecto identifica a los proyectos
     * @param proyecto el nuevo proyecto que se va a insertar
     * @return true si ha econtrado ese ID de proyecto
     */
    public boolean modificarProyecto(int idProyecto, Proyecto proyecto) {
        int posicion= buscarProyecto(idProyecto);
        if (posicion !=-1){
            proyectosDeLaPlataforma.set(posicion, proyecto);
            return true;
        }
        return false;
    }

    public void mostrarProyectos() {
        for (Proyecto proyecto : proyectosDeLaPlataforma) {
            System.out.println(proyecto);
        }
    }

    public void invertirEnProyecto(int posicionProyecto){
        Proyecto proyecto = proyectosDeLaPlataforma.get(posicionProyecto);
        // QUIZA DEBE DEVOLVER INVERSION, EN EL MAIN COGER LA INVERSION Y GUARDARLO EN EL OBJETO INVERSOR Y EN EL PROYECTO
        // Y HACER LO QUE SEA


    }

}
