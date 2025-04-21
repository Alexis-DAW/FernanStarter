package usuario;

import inversion.Inversion;
import proyecto.Proyecto;

import java.util.ArrayList;

public class UsuarioVista {
    private String iconoExito;
    private String iconoError;

    public UsuarioVista(String iconoExito, String iconoError) {
        this.iconoExito = iconoExito;
        this.iconoError = iconoError;
    }

    public void mostrarUsuarios(ArrayList<Usuario> usuarios){
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public void mostrarEstadoUsuarios(ArrayList<Usuario> usuarios){
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre()
                    + (usuario.estaBloqueado() ? iconoError : iconoExito));
        }
    }

    public void mostrarProyectos(ArrayList<Proyecto> proyectos){
        for (Proyecto proyecto : proyectos){
            System.out.println(proyecto);
        }
    }

    public void operacionExitosa(){
        System.out.println(iconoExito + " Se ha completado la operación con éxito");
    }

    public void operacionErronea(){
        System.out.println(iconoError + " No se ha completado la operación");
    }

    public void recargadoConExito(double cantidad, double saldo){
        System.out.println(iconoExito + " se recargó su cartera con " +cantidad+ ". Ahora tiene un total de "+saldo+"€");
    }

    public void recargadoSinExito(double saldo){
        System.out.println(iconoError + " no se puede recargar con una cantidad inferior a 0. Su saldo sigue siendo de "+saldo+"€");
    }

    public void bloqueado(){
        System.out.println(iconoError + " Este usuario se encuentra bloqueado " );
    }

    public void mostrarInversiones(ArrayList<Inversion> inversiones){
        for (Inversion inversion : inversiones){
            System.out.println(inversion);
        }
    }

    public void guardadoCorrectamente (){
        System.out.println(iconoExito + " usuarios guardados correctamente.");
    }

    public void guardadoIncorrectamente (){
        System.out.println(iconoError + " error al guardar los usuarios.");
    }

    public void cargadoCorrectamente (){
        System.out.println(iconoExito + " usuarios cargados correctamente.");
    }

    public void cargadoIncorrectamente (){
        System.out.println(iconoError + " error al cargar los usuarios.");
    }

}
