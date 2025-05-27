package proyecto.daoProyecto;

import proyecto.Proyecto;
import proyecto.ProyectoVista;
import utilidades.DAOManager;

public class ControladorProyectoDAO {
    private DAOProyectoSQL modelo;
    private ProyectoVista vista;
    private DAOManager daoManager;

    public ControladorProyectoDAO(DAOProyectoSQL modelo, ProyectoVista vista, DAOManager daoManager) {
        this.modelo = modelo;
        this.vista = vista;
        this.daoManager = daoManager;
    }

    public void insert (Proyecto proyecto){
        if (modelo.insert(proyecto, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void update (Proyecto proyecto){
        if (modelo.update(proyecto, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void delete (int idProyecto){
        if (modelo.delete(idProyecto, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void read (int idProyecto){
        if (modelo.read(idProyecto, daoManager) != null) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void readAll (){
        if (modelo.readAll(daoManager) != null) vista.operacionExitosa();
        else vista.operacionErronea();
    }


}
