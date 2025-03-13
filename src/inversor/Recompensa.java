package inversor;
public final class Recompensa {

    private String nombre;
    private String descripcion;
    private float precio;

    public Recompensa(String nombre, String descripcion, float precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String toString(){
        return "RECOMPENSA"+ "\nNombre -> " + nombre + "\nDescripcion -> "
                + descripcion + "\nPrecio -> " + precio;
    }

}
