package proyecto;
import inversion.Inversion;
import inversor.Inversor;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static utilidades.FuncionesFechas.convertirAString;

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
        nuevoLog("Nuevo proyecto", proyecto.getNombre(), LocalDateTime.now());
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
            String nombreProyecto= proyectosDeLaPlataforma.get(indice).getNombre();
            nuevoLog("Eliminación de proyecto", nombreProyecto, LocalDateTime.now());
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
            nuevoLog("Modificación de proyecto", proyecto.getNombre(), LocalDateTime.now());
            return true;
        }
        return false;
    }

    public void mostrarProyectos() {
        for (Proyecto proyecto : proyectosDeLaPlataforma) {
            System.out.println(proyecto);
        }
    }

    public boolean invertirEnProyecto(int posicionProyecto, Inversor inversor){
        Scanner sc = new Scanner(System.in);
        Proyecto proyecto = proyectosDeLaPlataforma.get(posicionProyecto);
        System.out.print("¿Cuánto desea invertir?: ");
        double cantidadInvertida = Double.parseDouble(sc.nextLine());
        Recompensa recompensa = proyecto.obtenerRecompensa(cantidadInvertida);
        Inversion inversion = new Inversion(inversor, proyecto, cantidadInvertida, recompensa);
        if (inversor.getSaldo() < cantidadInvertida){
            System.out.println("Saldo insuficiente.");
            return false;
        }
        boolean invertidoCorrectamente = proyecto.recibirInversion(inversion);
        if (invertidoCorrectamente){
            nuevoLog("Nueva inversión", inversor.getNombre(), LocalDateTime.now());
            inversor.invertir(inversion);
            return true;
        }
        return false;
    }

    //Esta función registra cada vez que se crea, elimina o se invierte un proyecto
    public void nuevoLog(String tipoLog, String nombre, LocalDateTime fecha) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("ficheros/log.txt"));
            bw.write(tipoLog + " " + nombre + ", " + convertirAString(LocalDateTime.now()) + "\n");
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
