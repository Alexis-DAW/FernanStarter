package proyecto;
import java.util.ArrayList;

public final class ProyectoVista {
    private String iconoExito;
    private String iconoError;

    public ProyectoVista(String iconoExito, String iconoError) {
        this.iconoExito = iconoExito;
        this.iconoError = iconoError;
    }

    public void muestraProyectos(ArrayList<Proyecto> proyectos){
        for(Proyecto proyecto: proyectos){
            System.out.println(proyecto);
        }
    }

    public void operacionExitosa(){
        System.out.println(iconoExito + " Se ha completado la operación con éxito.");
    }

    public void operacionErronea(){
        System.out.println(iconoError + " No se ha completado la operación.");
    }

    public void guardadoCorrectamente(){
        System.out.println(iconoExito + " proyectos guardados correctamente.");
    }

    public void guardadoIncorrectamente(){
        System.out.println(iconoError + " error al guardar los proyectos.");
    }

    public void cargadoCorrectamente(){
        System.out.println(iconoExito + " proyectos cargados correctamente.");
    }

    public void cargadoIncorrectamente(){
        System.out.println(iconoError + " error al cargar los proyectos.");
    }


}
