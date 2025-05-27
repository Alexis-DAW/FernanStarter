package proyecto.daoRecompensa;

import proyecto.ProyectoVista;
import proyecto.Recompensa;
import utilidades.DAOManager;

import static utilidades.FuncionesVarias.logBBDD;

public class ControladorRecompensaDAO {

    private DAORecompensaSQL modelo;
    private ProyectoVista vista;
    private DAOManager daoManager;

    public ControladorRecompensaDAO (DAORecompensaSQL modelo, ProyectoVista vista, DAOManager daoManager){
        this.modelo= modelo;
        this.vista= vista;
        this.daoManager= daoManager;
    }

    public void insert(Recompensa recompensa){
        if(modelo.insert(recompensa, daoManager)){
            vista.operacionExitosa();
            logBBDD("Inserción", "recompensa");
        }
        else vista.operacionErronea();
    }

    public void delete(int idRecompensa){
        if(modelo.delete(idRecompensa, daoManager)){
            vista.operacionExitosa();
            logBBDD("Eliminación", "recompensa");
        }
        else vista.operacionErronea();
    }

    public void update(Recompensa recompensa){
        if(modelo.update(recompensa, daoManager)){
            vista.operacionExitosa();
            logBBDD("Actualización", "recompensa");
        }
        else vista.operacionErronea();
    }

    public void read(int idRecompensa, DAOManager daoManager){
        if(modelo.read(idRecompensa, daoManager)!= null){
            vista.operacionExitosa();
            logBBDD("Acceso", "recompensa");
        }
        else vista.operacionErronea();
    }

    public void readAll(){
        if(modelo.readAll(daoManager)!= null){
            vista.operacionExitosa();
            logBBDD("Acceso", "recompensa");
        }
        else vista.operacionErronea();
    }
}
