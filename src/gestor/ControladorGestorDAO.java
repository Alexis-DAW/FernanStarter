package gestor;

import usuario.UsuarioVista;
import utilidades.DAOManager;

public class ControladorGestorDAO {

    private DAOGestorSQL modelo;
    private UsuarioVista vista;
    private DAOManager daoManager;

    public void insert(Gestor gestor, DAOManager daoManager){
        if(modelo.insert(gestor, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void delete(String correo, DAOManager daoManager){
        if(modelo.delete(correo, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void update(Gestor gestor, DAOManager daoManager){
        if(modelo.update(gestor, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void read(String correo, DAOManager daoManager){
        if(modelo.read(correo, daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void readAll(Gestor gestor, DAOManager daoManager){
        if(modelo.readAll(daoManager)!= null) vista.operacionExitosa();
        else vista.operacionErronea();
    }
}
