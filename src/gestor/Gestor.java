package gestor;
import proyecto.Proyecto;
import usuario.Tipo;
import usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;

public final class Gestor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Proyecto> proyectos;

    public Gestor(String nombre, String contrasena, String correo) {
        super(nombre, contrasena, correo, Tipo.GESTOR);
        this.proyectos = new ArrayList<>();
    }

    public String toString() {
        return "Gestor " + super.getNombre() +
                " con " + proyectos.size() + " proyectos.";
    }

    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    /**
     * Agrega un nuevo proyecto a la lista
     *
     * @param proyecto que se va a añadir
     */
    public void agregarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }

    /**
     * Elimina un proyecto existente de la lista
     *
     * @param idProyecto identifica a los proyectos
     * @return true si se ha podido eliminar dicho proyecto
     */
    public boolean eliminarProyecto(int idProyecto) {
        for (Proyecto proyecto : proyectos){
            if (proyecto.getId() == idProyecto){
                proyectos.remove(proyecto);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un proyecto existente de la lista
     *
     * @param idProyecto identifica a los proyectos
     * @return posición del proyecto con dicha ID
     * @return -1 si no se ha encontrado la ID
     */
    public int buscarProyecto(int idProyecto) {
        for (int i=0 ; i<proyectos.size(); i++){
            if (proyectos.get(i).getId() == idProyecto) return i;
        }
        return -1;
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
            proyectos.set(posicion, proyecto);
            return true;
        }
        return false;
    }

    public void mostrarProyectos() {
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto);
        }
    }

}
