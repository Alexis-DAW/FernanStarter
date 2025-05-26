package proyecto;

import utilidades.DAOManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAORecompensaSQL implements DAORecompensa {

    public boolean insert(Recompensa recompensa, DAOManager daoManager) {
        String sql = "INSERT INTO entradas (nombre) VALUES ('"
                + recompensa.getId() + "','"
                + recompensa.getDescripcion() + "','"
                + recompensa.getCantidadMinima() + "');";
        return daoManager.ejecutaSentencia(sql);
    }

    public boolean update(Recompensa recompensa, DAOManager daoManager) {
        String sql = "UPDATE entradas SET descripcion= '"
                + recompensa.getDescripcion() + ", cantidadMinima= "
                + recompensa.getCantidadMinima() + " WHERE id= " + recompensa.getId();
        return daoManager.ejecutaSentencia(sql);
    }

    public boolean delete(int idRecompensa, DAOManager daoManager) {
        String sql = "DELETE FROM entradas WHERE id = "+ idRecompensa + ";";
        return daoManager.ejecutaSentencia(sql);
    }

    public Recompensa read(int idRecompensa, DAOManager daoManager) {
        String sql = "SELECT * FROM entradas WHERE id = "+ idRecompensa + ";";
        try{
            Statement stmt = daoManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                // Si existe una entrada con ese id creo el objeto Entrada a partir de los datos de la tabla entradas
                Recompensa recompensa = new Recompensa(
                        rs.getString("descripcion"),
                        rs.getDouble("cantidadMinima"));
                return recompensa;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Recompensa> readAll(DAOManager daoManager) {
        String sql = "SELECT * FROM entradas;";
        ArrayList<Recompensa> recompensas = new ArrayList<>();
        try {
            Statement stmt = daoManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Recompensa recompensa = new Recompensa(
                        rs.getString("descripcion"),
                        rs.getDouble("cantidadMinima"));
                recompensas.add(recompensa);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recompensas;
    }
}
