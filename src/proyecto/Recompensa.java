package proyecto;

import java.io.Serializable;

public final class Recompensa implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int contadorId;
    private int id;
    private String descripcion;
    private double cantidadMinima; // (para obtener la recompensa)

    public Recompensa(String descripcion, double cantidadMinima) {
        this.id = ++contadorId;
        this.descripcion = descripcion;
        this.cantidadMinima = cantidadMinima;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCantidadMinima() {
        return cantidadMinima;
    }

    public static void setContadorId(int nuevoContador) {
        contadorId = nuevoContador;
    }

    public String toString(){
        return "ID: " + id + ", descripción: " + descripcion + ", cantidad mínima para obtener: " + cantidadMinima;
    }

    public boolean esSuficienteParaObtener(double cantidadInvertida) {
        return cantidadInvertida >= cantidadMinima;
    }


}
