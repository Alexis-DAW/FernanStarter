package proyecto;

import utilidades.DAOManager;

import java.util.ArrayList;

public interface DAORecompensa {

    boolean insert(Recompensa recompensa, DAOManager daoManager);
    boolean update(Recompensa recompensa, DAOManager daoManager);
    boolean delete(int idRecompensa, DAOManager daoManager);
    Recompensa read(int idRecompensa, DAOManager daoManager);
    ArrayList<Recompensa> readAll(DAOManager daoManager);
}
