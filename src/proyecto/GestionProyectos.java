package proyecto;
import inversion.Inversion;
import inversor.Inversor;

import java.io.*;
import java.time.LocalDate;
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

    public void proyectosOrdenadosCantidad() {
        proyectosDeLaPlataforma
                .stream()
                .sorted(((p1, p2) -> p2.getCantidadFinanciada() - p1.getCantidadFinanciada()))
                .forEach(proyecto -> System.out.println(proyecto));
    }

    public void proyectosOrdenadosFecha(){
        proyectosDeLaPlataforma
                .stream()
                .sorted(((p1, p2) -> p2.getCantidadFinanciada() - p1.getCantidadFinanciada()))
                .forEach(proyecto -> System.out.println(proyecto));

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

    public void guardarProyectos(String ruta) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));

            for (Proyecto p : proyectosDeLaPlataforma) {
                bw.write(p.getId() + ";" + p.getNombre() + ";" + p.getDescripcion() + ";" +
                        p.getCantidadNecesaria() + ";" + p.getCantidadFinanciada() + ";" +
                        p.getFechaInicio() + ";" + p.getFechaFin() + ";" + p.getCategoria());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar proyectos.");
            e.printStackTrace();
        }
    }

    // todo modificar estos metodos para que también guarden la información de las recompensas y las inversiones de cada proyecto
    // todo PREGUNTAR A ELADIO SI LA FORMA DE GUARDAR/CARGAR PROYECTOS Y USUARIOS DEBE SER ESTA O CON SERIALIZACION: ObjectInputStream
    public void cargarProyectos(String ruta) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 8) {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String descripcion = partes[2];
                    double cantidadNecesaria = Double.parseDouble(partes[3]);
                    double cantidadFinanciada = Double.parseDouble(partes[4]);
                    LocalDate fechaInicio = LocalDate.parse(partes[5]);
                    LocalDate fechaFin = LocalDate.parse(partes[6]);
                    Categoria categoria = Categoria.valueOf(partes[7]);

                    Proyecto proyecto = new Proyecto(nombre, descripcion, cantidadNecesaria, fechaInicio, fechaFin, categoria);
                    proyecto.setCantidadFinanciada(cantidadFinanciada);

                    proyectosDeLaPlataforma.add(proyecto);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer proyectos.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error en el formato del archivo de proyectos.");
            e.printStackTrace();
        }
    }




}
