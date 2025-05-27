package inversor;

import usuario.UsuarioVista;
import utilidades.DAOManager;

public class ControladorInversorDAO {

    private DAOInversorSQL modelo;
    private UsuarioVista vista;
    private DAOManager daoManager;

    public void insert(Inversor inversor, DAOManager daoManager){
        if(modelo.insert(inversor, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void delete(String correo, DAOManager daoManager){
        if(modelo.delete(correo, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void update(Inversor inversor, DAOManager daoManager){
        if(modelo.update(inversor, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void read(String correo, DAOManager daoManager){
        if(modelo.read(correo, daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void readAll(Inversor inversor, DAOManager daoManager){
        if(modelo.readAll(daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }
}
