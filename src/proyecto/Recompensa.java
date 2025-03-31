package proyecto;

public final class Recompensa {
    private int contadorId;
    private int id;
    private String descripcion;
    private double cantidadMinima; // (para obtener la recompensa)

    public Recompensa(String descripcion, double cantidadMinima) {
        this.id = contadorId++;
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

    public String toString(){
        return "ID: " + id + ", descripción: " + descripcion + ", cantidad mínima para obtener: " + cantidadMinima;
    }

    public boolean esSuficienteParaObtener(double cantidadInvertida) {
        return cantidadInvertida >= cantidadMinima;
    }


}
