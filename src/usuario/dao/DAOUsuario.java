package usuario.dao;

import usuario.Usuario;
import utilidades.DAOManager;

import java.util.ArrayList;

public interface DAOUsuario {
    boolean insert(Usuario usuario, DAOManager daoManager);
    boolean update(Usuario usuario, DAOManager daoManager);
    boolean delete(String correo, DAOManager daoManager);
    Usuario read(String correo, DAOManager daoManager);
    ArrayList<Usuario> readAll(DAOManager daoManager);
}
