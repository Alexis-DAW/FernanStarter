package proyecto.daoProyecto;

import proyecto.Proyecto;
import administrador.DAOManager;

import java.util.ArrayList;

public interface DAOProyecto {
    boolean insert(Proyecto proyecto, DAOManager daoManager);
    boolean update(Proyecto proyecto, DAOManager daoManager);
    boolean delete(int idProyecto, DAOManager daoManager);
    Proyecto read(int idProyecto, DAOManager daoManager);
    ArrayList<Proyecto> readAll(DAOManager daoManager);
}
