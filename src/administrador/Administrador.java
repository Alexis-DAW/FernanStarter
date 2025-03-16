package administrador;
import usuario.TipoUsuario;
import usuario.Usuario;

public final class Administrador extends Usuario {

    public Administrador(String nombre, String contrasena, String correo){
        super(nombre, contrasena, correo, TipoUsuario.ADMINISTRADOR);
    }

    public String toString(){
        return "Administrador " + super.getNombre();
    }



    /*  Creo que esto se puede hacer como una funcion normal en el paquete de utilidades
    todo metodo para bloquear usuarios y otro para desbloquearlos;
    public void bloquearUsuario(Usuario usuario) {
    }
    */

}
