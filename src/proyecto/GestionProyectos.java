package proyecto;
import java.util.ArrayList;

public final class GestionProyectos {
    private ArrayList<Proyecto> listaProyectos;

    public GestionProyectos() {
        this.listaProyectos = new ArrayList<>();
    }

    /**
     * Agrega un nuevo proyecto a la lista
     *
     * @param proyecto que se va a añadir
     */
    public void agregarProyecto(Proyecto proyecto) {
        listaProyectos.add(proyecto);
    }

    /**
     * Busca un proyecto existente de la lista
     *
     * @param idProyecto identifica a los proyectos
     * @return posición del proyecto con dicha ID
     * @return -1 si no se ha encontrado la ID
     */
    public int buscarProyecto(int idProyecto) {
        for (int i=0 ; i<listaProyectos.size(); i++){
            if (listaProyectos.get(i).getId() == idProyecto) return i;
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
            listaProyectos.remove(indice);
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
            listaProyectos.set(posicion, proyecto);
            return true;
        }
        return false;
    }

    public void mostrarProyectos() {
        for (Proyecto proyecto : listaProyectos) {
            System.out.println(proyecto);
        }
    }
}
