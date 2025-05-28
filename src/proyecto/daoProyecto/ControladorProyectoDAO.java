package proyecto.daoProyecto;

import proyecto.Proyecto;
import proyecto.ProyectoVista;
import utilidades.DAOManager;

import java.util.ArrayList;

import static utilidades.FuncionesVarias.*;

public class ControladorProyectoDAO {
    private DAOProyectoSQL modelo;
    private ProyectoVista vista;
    private DAOManager daoManager;
    private ArrayList<Proyecto> listaProyecto;

    public ControladorProyectoDAO(DAOProyectoSQL modelo, ProyectoVista vista, DAOManager daoManager) {
        this.modelo = modelo;
        this.vista = vista;
        this.daoManager = daoManager;
        this.listaProyecto= new ArrayList<>();
    }

    public void insert (Proyecto proyecto){
        if (modelo.insert(proyecto, daoManager)){
            vista.operacionExitosa();
            logBBDD("Inserción", "proyecto");
            listaProyecto.add(proyecto);
        }
        else vista.operacionErronea();
    }

    public void update (Proyecto proyecto){
        if (modelo.update(proyecto, daoManager)){
            vista.operacionExitosa();
            logBBDD("Actualización", "proyecto");
            for (int i = 0; i < listaProyecto.size(); i++) {
                if (listaProyecto.get(i).getId() == proyecto.getId()){
                    listaProyecto.set(i, proyecto);
                    break;
                }
            }
        }
        else vista.operacionErronea();
    }

    public void delete (int idProyecto){
        if (modelo.delete(idProyecto, daoManager)){
            vista.operacionExitosa();
            logBBDD("Eliminación", "proyecto");
            listaProyecto.removeIf(p -> p.getId() == idProyecto);
        }
        else vista.operacionErronea();
    }

    public void read (int idProyecto){
        if (modelo.read(idProyecto, daoManager) != null){
            vista.operacionExitosa();
            logBBDD("Acceso", "proyecto");
        }
        else vista.operacionErronea();
    }

    public void readAll (){
        if (modelo.readAll(daoManager) != null){
            vista.operacionExitosa();
            logBBDD("Acceso", "proyecto");
        }
        else vista.operacionErronea();
    }

    public void cargarProyectos(){
        if (modelo.cargarProyectos(daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void guardarProyectos(ArrayList<Proyecto> listaProyectos){
        if (modelo.guardarProyectos(listaProyectos, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }
}
