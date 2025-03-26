package inversion;

import inversor.Inversor;
import proyecto.Proyecto;
import proyecto.Recompensa;

import java.time.LocalDate;

public final class Inversion {
    private Inversor inversor;
    private Proyecto proyecto;
    private double cantidad;
    private LocalDate fecha;
    private Recompensa recompensa;

    public Inversion(Inversor inversor, Proyecto proyecto, double cantidad, Recompensa recompensa) {
        this.inversor = inversor;
        this.proyecto = proyecto;
        this.cantidad = cantidad;
        this.fecha = LocalDate.now();
        this.recompensa = recompensa;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String toString(){
        return  " → Inversor: " + inversor.getNombre() +
                " → Proyecto: " + proyecto.getNombre() +
                " | Cantidad: " + cantidad +
                " | Fecha: " + fecha;
    }
}
