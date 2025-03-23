package gestor;
import proyecto.Proyecto;
import java.util.ArrayList;

public final class GestorVista {
    private String iconoExito;
    private String iconoError;

    public GestorVista(String iconoExito, String iconoError) {
        this.iconoExito = iconoExito;
        this.iconoError = iconoError;
    }

    public void muestraProyectos(ArrayList<Proyecto> proyectos){
        for(Proyecto proyecto: proyectos){
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
