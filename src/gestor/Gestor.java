package gestor;
import usuario.Usuario;

public final class Gestor extends Usuario {

    public Gestor(String nombre, String contrasena, String correo) {
        super(nombre, contrasena, correo);
    }

    public String toString() {
        return "Gestor " + super.getNombre();
    }
}
