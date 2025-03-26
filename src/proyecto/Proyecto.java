package proyecto;
import inversion.Inversion;

import java.time.LocalDate;

public final class Proyecto {

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
    private Inversion[] inversiones;
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
        this.inversiones = new Inversion[10];
        this.recompensas = new Recompensa[3];
        this.numInversiones = 0;
        this.numRecompensas = 0;
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

    public double getCantidadFinanciada() {
        return cantidadFinanciada;
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

    public void setNumRecompensas(int numRecompensas) {
        this.numRecompensas = numRecompensas;
    }

    @Override
    public String toString() {
        return "PROYECTO " + id + "\nNombre: " + nombre + ", descripción: " + descripcion + ", cantidad necesaria: "
                + cantidadNecesaria + ", cantidad financiada: " + cantidadFinanciada + ", fecha inicio: " + fechaInicio
                + ", fecha fin: " + fechaFin + ", categoría: " + categoria + ", recompensas: " + numRecompensas ;
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

    public void recibirInversion(Inversion inversion) {
        if (numInversiones < inversiones.length) {
            inversiones[numInversiones++] = inversion;
            cantidadFinanciada += inversion.getCantidad();
        } else {
            System.out.println("Capacidad de inversiones alcanzada. Expandiendo array...");
            ampliarArrayInversiones();
            recibirInversion(inversion); // La funcion se llama a sí misma, pero esta vez entrará por el primer if (no entra en bucle)
        }
    }
    private void ampliarArrayInversiones() {
        Inversion[] nuevoArray = new Inversion[inversiones.length * 2];
        for (int i = 0; i < inversiones.length; i++) {
            nuevoArray[i] = inversiones[i];
        }
        inversiones = nuevoArray;
    }


}
