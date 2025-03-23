package usuario;

public abstract class Usuario implements Bloqueable{

    private String nombre;
    private String contrasena;
    private String correo;
    private final Tipo tipo;
    private boolean bloqueado;

    public Usuario (String nombre, String contrasena, String correo, Tipo tipo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.tipo = tipo;
        this.bloqueado = false;
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

    public Tipo getTipoUsuario() { return tipo;}

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public void setCorreo(String correo) { this.correo = correo;}

    public abstract String toString();

    public void bloquear() {
        this.bloqueado = true;
    }
    public void desbloquear(){
        this.bloqueado = false;
    }

}
