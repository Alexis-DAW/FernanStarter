package proyecto.daoRecompensa;

import proyecto.ProyectoVista;
import proyecto.Recompensa;
import utilidades.DAOManager;

public class ControladorRecompensaDAO {

    private DAORecompensaSQL modelo;
    private ProyectoVista vista;
    private DAOManager daoManager;

    public void insert(Recompensa recompensa, DAOManager daoManager){
        if(modelo.insert(recompensa, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void delete(int idRecompensa, DAOManager daoManager){
        if(modelo.delete(idRecompensa, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void update(Recompensa recompensa, DAOManager daoManager){
        if(modelo.update(recompensa, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void read(int idRecompensa, DAOManager daoManager){
        if(modelo.read(idRecompensa, daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void readAll(Recompensa recompensa, DAOManager daoManager){
        if(modelo.readAll(daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }


}
