package administrador;
import usuario.Tipo;
import usuario.Usuario;

import java.io.Serializable;

public final class Administrador extends Usuario implements Serializable {

    public Administrador(String nombre, String contrasena, String correo){
        super(nombre, contrasena, correo, Tipo.ADMINISTRADOR);
    }

    public String toString(){
        return "Administrador " + super.getNombre();
    }

}
