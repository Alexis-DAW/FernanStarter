package inversion;

import inversor.Inversor;
import proyecto.Proyecto;
import proyecto.Recompensa;

import java.io.Serializable;
import java.time.LocalDate;

public final class Inversion implements Invertible, Serializable {
    private static final long serialVersionUID = 1L;
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

    public int getCantidad() {
        return (int) cantidad;
    }

    public Inversor getInversor() { return inversor; }

    public Proyecto getProyecto() { return proyecto; }

    public LocalDate getFecha() { return fecha; }

    public Recompensa getRecompensa() {
        return recompensa;
    }

    public String toString(){
        return  " → Inversor: " + inversor.getNombre() +
                " → Proyecto: " + proyecto.getNombre() +
                " | Cantidad: " + cantidad +
                " | Fecha: " + fecha +
                " | Recompensa: " + recompensa;

    }

    public boolean aumentaInversion(double cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
            return true;
        }
        return false;
    }

    public boolean disminuyeInversion(double cantidad) {
        if (cantidad <= this.cantidad){
            this.cantidad -= cantidad;
            return true;
        }
        return false;
    }
}
