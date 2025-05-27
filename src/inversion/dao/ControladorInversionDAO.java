package inversion.dao;
import inversion.Inversion;
import proyecto.ProyectoVista;

import utilidades.DAOManager;

import static utilidades.FuncionesVarias.logBBDD;

public class ControladorInversionDAO {

    private DAOInversionSQL modelo;
    private ProyectoVista vista;

    public void insert(Inversion inversion, DAOManager daoManager){
        if(modelo.insert(inversion, daoManager)){
            vista.operacionExitosa();
            logBBDD("Inserción", "inversion");
        }
        else vista.operacionErronea();
    }

    public void delete(int idInversion, DAOManager daoManager){
        if(modelo.delete(idInversion, daoManager)){
            vista.operacionExitosa();
            logBBDD("Eliminación", "inversion");
        }
        else vista.operacionErronea();
    }

    public void update(Inversion inversion, DAOManager daoManager){
        if(modelo.update(inversion, daoManager)){
            vista.operacionExitosa();
            logBBDD("Actualización", "inversion");
        }
        else vista.operacionErronea();
    }

    public void read(int idInversion, DAOManager daoManager){
        if(modelo.read(idInversion, daoManager)!= null){
            vista.operacionExitosa();
            logBBDD("Acceso", "inversion");
        }
        else vista.operacionErronea();
    }

    public void readAll(DAOManager daoManager){
        if(modelo.readAll(daoManager)!= null){
            vista.operacionExitosa();
            logBBDD("Acceso", "inversion");
        }
        else vista.operacionErronea();
    }

}
