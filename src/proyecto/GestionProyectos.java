package proyecto;
import inversion.Inversion;
import inversor.Inversor;
import usuario.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static utilidades.FuncionesFechas.convertirAString;

public final class GestionProyectos implements Serializable {
    private static final long serialVersionUID = 1L;
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
            BufferedWriter bw = new BufferedWriter(new FileWriter("ficheros/log.txt", true));
            bw.write(tipoLog + " " + nombre + ", " + convertirAString(LocalDateTime.now()));
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. Archivo no encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Excepción de entrada/salida");
            e.printStackTrace();
        }
    }

    public boolean guardarProyectos(String ruta) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(proyectosDeLaPlataforma);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cargarProyectos(String ruta) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            proyectosDeLaPlataforma = (ArrayList<Proyecto>) ois.readObject();

            // El ID es un atributo estático y lo tenemos que recuperar de forma manual:
            int maxId = 0;
            for (Proyecto p : proyectosDeLaPlataforma) {
                if (p.getId() > maxId) maxId = p.getId();
            }
            Proyecto.setContadorProyectos(maxId + 1);

            // Restaurar los ID de recompensas
            maxId = 0;
            for (Proyecto p : proyectosDeLaPlataforma) {
                for (Recompensa r : p.getRecompensas()) {
                    if (r != null && r.getId() > maxId) maxId = r.getId();
                }
            }
            Recompensa.setContadorId(maxId);

            return true;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }




}
