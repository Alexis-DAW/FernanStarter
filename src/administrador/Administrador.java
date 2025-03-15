package administrador;

import usuario.Usuario;

public final class Administrador extends Usuario {

    public Administrador(String nombre, String contrasena, String correo){
        super(nombre, contrasena, correo);
    }

    public String toString(){
        return "Administrador " + super.getNombre();
    }

    /* todo metodo para bloquear usuarios y otro para desbloquearlos
    public void bloquearUsuario(Usuario usuario) {
    }
    */

}
