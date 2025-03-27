package usuario;

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
}
