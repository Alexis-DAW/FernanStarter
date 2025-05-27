package administrador;

import usuario.UsuarioVista;
import utilidades.DAOManager;

public class ControladorAdministradorDAO {

    private DAOAdministradorSQL modelo;
    private UsuarioVista vista;
    private DAOManager daoManager;

    public void insert(Administrador administrador, DAOManager daoManager){
        if(modelo.insert(administrador, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void delete(String correo, DAOManager daoManager){
        if(modelo.delete(correo, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void update(Administrador administrador, DAOManager daoManager){
        if(modelo.update(administrador, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void read(String correo, DAOManager daoManager){
        if(modelo.read(correo, daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void readAll(Administrador administrador, DAOManager daoManager){
        if(modelo.readAll(daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }
}
