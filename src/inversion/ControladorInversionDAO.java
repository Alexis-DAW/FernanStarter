package inversion;
import proyecto.ProyectoVista;

import utilidades.DAOManager;

public class ControladorInversionDAO {

    private DAOInversionSQL modelo;
    private ProyectoVista vista;
    private DAOManager daoManager;

    public void insert(Inversion inversion, DAOManager daoManager){
        if(modelo.insert(inversion, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void delete(int idInversion, DAOManager daoManager){
        if(modelo.delete(idInversion, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void update(Inversion inversion, DAOManager daoManager){
        if(modelo.update(inversion, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void read(int idInversion, DAOManager daoManager){
        if(modelo.read(idInversion, daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void readAll(Inversion inversion, DAOManager daoManager){
        if(modelo.readAll(daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }

}
