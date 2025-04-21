package proyecto;
import inversion.Inversion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import static utilidades.FuncionesFechas.*;

public final class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int contadorProyectos=0;
    private final int id;
    private String nombre;
    private String descripcion;
    private double cantidadNecesaria;
    private double cantidadFinanciada;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Categoria categoria;
    private Recompensa [] recompensas;
    private ArrayList<Inversion> inversiones;
    private int numInversiones;
    private int numRecompensas;

    public Proyecto(String nombre, String descripcion, double cantidadNecesaria, LocalDate fechaInicio,
                    LocalDate fechaFin, Categoria categoria){
        this.id = contadorProyectos++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadNecesaria = cantidadNecesaria;
        this.cantidadFinanciada = 0;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.categoria = categoria;
        this.inversiones = new ArrayList<>();
        this.recompensas = new Recompensa[3];
        this.numInversiones = 0;
        this.numRecompensas = 0;
    }

    static void setContadorProyectos(int contadorProyectos) {
        Proyecto.contadorProyectos = contadorProyectos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    public int getCantidadFinanciada() {
        return (int)cantidadFinanciada;
    }

    //Te devuelve cuantos días hay entre la fecha de inicio de un proyecto y la de fin
    public int getDias(){
        Period periodo = Period.between(fechaInicio, fechaFin);
        return periodo.getDays();
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getNumInversiones() {
        return numInversiones;
    }

    // Este metodo sólo debe usarse desde el metodo cargarProyectos de GestionProyectos
    public void setCantidadFinanciada(double cantidadFinanciada) {
        this.cantidadFinanciada = cantidadFinanciada;
    }

    @Override
    public String toString() {
        return "PROYECTO " + id + "\nNombre: " + nombre + ", descripción: " + descripcion + "\nCantidad necesaria: "
                + cantidadNecesaria + "\nCantidad financiada: " + cantidadFinanciada + "\nFecha inicio: " + fechaInicio
                + ", fecha fin: " + fechaFin + "\nCategoría: " + categoria + "\nRecompensas: " + muestraRecompensas() ;
    }

    private String muestraRecompensas(){
        String muestraRecompensas = "";
        for (Recompensa recompensa : recompensas){
            muestraRecompensas += recompensa.toString() + "\n";
        }
        return muestraRecompensas;
    }

    public void agregarRecompensa(Recompensa recompensa) {
        if (numRecompensas < 3) {
            recompensas[numRecompensas++] = recompensa;
        } else {
            System.out.println("El proyecto ya tiene 3 recompensas.");
        }
    }

    public Recompensa obtenerRecompensa(double cantidadInvertida) {
        Recompensa mejorRecompensa = null;
        for (int i = 0; i < numRecompensas; i++) {
            if (recompensas[i].esSuficienteParaObtener(cantidadInvertida)) {
                if (mejorRecompensa == null || recompensas[i].getCantidadMinima() > mejorRecompensa.getCantidadMinima()) {
                    mejorRecompensa = recompensas[i];
                }
            }
        }
        return mejorRecompensa;
    }

    public boolean recibirInversion(Inversion inversion) {
        boolean esAnterior = esFechaAnterior(fechaFin);
        if (cantidadFinanciada <= cantidadNecesaria && !esAnterior){
            cantidadFinanciada += inversion.getCantidad();
            inversiones.add(inversion);
            return true;
        } else {
            System.out.println("Ya no se puede invertir en este proyecto.");
            return false;
        }
    }


}
