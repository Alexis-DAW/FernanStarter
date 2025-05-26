package administrador;

import usuario.Usuario;
import utilidades.DAOManager;

import java.util.ArrayList;

public interface DAOUsuario {
    boolean insert(Usuario usuario, DAOManager daoManager);
    boolean update(Usuario usuario, DAOManager daoManager);
    boolean delete(int idUsuario, DAOManager daoManager);
    Usuario read(int idUsuario, DAOManager daoManager);
    ArrayList<Usuario> readAll(DAOManager daoManager);
}
