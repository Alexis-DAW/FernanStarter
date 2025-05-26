package proyecto;

import utilidades.DAOManager;

import java.util.ArrayList;

public class DAOProyectoSQL implements DAOProyecto{
    @Override
    public boolean insert(Proyecto proyecto, DAOManager daoManager) {
        return false;
    }

    @Override
    public boolean update(Proyecto proyecto, DAOManager daoManager) {
        return false;
    }

    @Override
    public boolean delete(int idProyecto, DAOManager daoManager) {
        return false;
    }

    @Override
    public Proyecto read(int idProyecto, DAOManager daoManager) {
        return null;
    }

    @Override
    public ArrayList<Proyecto> readAll(DAOManager daoManager) {
        return null;
    }
}
