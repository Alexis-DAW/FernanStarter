package proyecto;

public final class Recompensa {
    private int id;
    private String descripcion;
    private double cantidadMinima; // (para obtener la recompensa)

    public Recompensa(int id, String descripcion, double cantidadMinima) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidadMinima = this.cantidadMinima;
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

    public String toString(){
        return "ID: " + id + ", descripción: " + descripcion + ", cantidad mínima para obtener: " + cantidadMinima;
    }

    public boolean esSuficienteParaObtener(double cantidadInvertida) {
        return cantidadInvertida >= cantidadMinima;
    }


}
