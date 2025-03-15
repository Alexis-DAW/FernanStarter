package inversion;

import inversor.Inversor;
import proyecto.Proyecto;
import java.time.LocalDate;

public final class Inversion {
    private Inversor inversor;
    private Proyecto proyecto;
    private double cantidad;
    private LocalDate fecha;

    public Inversion(Inversor inversor, Proyecto proyecto, double cantidad) {
        this.inversor = inversor;
        this.proyecto = proyecto;
        this.cantidad = cantidad;
        this.fecha = LocalDate.now();
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
