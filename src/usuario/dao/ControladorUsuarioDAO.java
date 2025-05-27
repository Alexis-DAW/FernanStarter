package usuario.dao;

import usuario.Usuario;
import usuario.UsuarioVista;
import utilidades.DAOManager;

import java.util.ArrayList;

import static utilidades.FuncionesVarias.logBBDD;

public class ControladorUsuarioDAO {

    private DAOUsuarioSQL modelo;
    private UsuarioVista vista;
    private DAOManager daoManager;

    public ControladorUsuarioDAO(DAOUsuarioSQL modelo, UsuarioVista vista, DAOManager daoManager){
        this.modelo= modelo;
        this.vista= vista;
        this.daoManager= daoManager;
    }

    public void insert(Usuario usuario){
        if(modelo.insert(usuario, daoManager)){
            vista.operacionExitosa();
            logBBDD("Inserción", "usuario");
        }
        else vista.operacionErronea();
    }

    public void delete(String correo){
        if(modelo.delete(correo, daoManager)){
            vista.operacionExitosa();
            logBBDD("Eliminación", "usuario");
        }
        else vista.operacionErronea();
    }

    public void update(Usuario usuario){
        if(modelo.update(usuario, daoManager)){
            vista.operacionExitosa();
            logBBDD("Actualización","usuario" );
        }
        else vista.operacionErronea();
    }

    public void read(String correo){
        if(modelo.read(correo, daoManager)!= null){
            vista.operacionExitosa();
            logBBDD("Acceso", "usuario");
        }
        else vista.operacionErronea();
    }

    public void readAll(){
        if(modelo.readAll(daoManager)!= null){
            vista.operacionExitosa();
            logBBDD("Acceso", "usuario");
        }
        else vista.operacionErronea();
    }

    public void cargarUsuarios(){
        if (modelo.cargarUsuarios(daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void guardarUsuarios(ArrayList<Usuario> listaUsuarios){
        if (modelo.guardarUsuarios(listaUsuarios, daoManager)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

}
