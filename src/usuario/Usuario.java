package usuario;
public abstract class Usuario {

    private String nombre;
    private String contrasena;
    private String correo;

    public Usuario (String nombre, String contrasena, String correo){
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public abstract String toString();

}
