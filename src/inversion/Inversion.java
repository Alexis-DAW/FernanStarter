package inversion;

import inversor.Inversor;
import proyecto.Proyecto;
import proyecto.Recompensa;

import java.io.Serializable;
import java.time.LocalDate;

public final class Inversion implements Invertible, Serializable {
    private static final long serialVersionUID = 1L;
    private static int contadorId;
    private int id;
    private Inversor inversor;
    private Proyecto proyecto;
    private double cantidad;
    private LocalDate fecha;
    private Recompensa recompensa;

    public Inversion(Inversor inversor, Proyecto proyecto, double cantidad, Recompensa recompensa) {
        this.id = ++contadorId;
        this.inversor = inversor;
        this.proyecto = proyecto;
        this.cantidad = cantidad;
        this.fecha = LocalDate.now();
        this.recompensa = recompensa;
    }


    public int getID(){ return id; }

    public double getCantidad() { return cantidad; }

    public String getCorreoInversor() { return inversor.getCorreo(); }

    public int getIDProyecto() { return proyecto.getId(); }

    public LocalDate getFecha() { return fecha; }

    public int getIDRecompensa() { return recompensa.getId();}

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
