package administrador;
import usuario.Tipo;
import usuario.Usuario;

public final class Administrador extends Usuario {

    public Administrador(String nombre, String contrasena, String correo){
        super(nombre, contrasena, correo, Tipo.ADMINISTRADOR);
    }

    public String toString(){
        return "Administrador " + super.getNombre();
    }

}
