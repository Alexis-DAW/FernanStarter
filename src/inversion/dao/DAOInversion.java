package inversion.dao;

import inversion.Inversion;
import utilidades.DAOManager;

import java.util.ArrayList;

public interface DAOInversion {

    boolean insert(Inversion inversion, DAOManager daoManager);
    boolean delete(int idInversion, DAOManager daoManager);
    boolean update(Inversion inversion, DAOManager daoManager);
    Inversion read(int idInversion, DAOManager daoManager);
    ArrayList<Inversion> readAll(DAOManager daoManager);
}
