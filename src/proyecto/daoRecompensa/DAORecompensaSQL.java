package proyecto.daoRecompensa;

import proyecto.Recompensa;
import utilidades.DAOManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAORecompensaSQL implements DAORecompensa {
    @Override
    public boolean insert(Recompensa recompensa, DAOManager daoManager) {
        String sql = "INSERT INTO recompensa (descripcion, cantidadMinima) VALUES ('"
                + recompensa.getDescripcion() + "','"
                + recompensa.getCantidadMinima() + "');";
        return daoManager.ejecutaSentencia(sql);
    }
    @Override
    public boolean update(Recompensa recompensa, DAOManager daoManager) {
        String sql = "UPDATE recompensa SET descripcion = '"
                + recompensa.getDescripcion() + "', cantidadMinima = "
                + recompensa.getCantidadMinima() + " WHERE id = "
                + recompensa.getId();
        return daoManager.ejecutaSentencia(sql);
    }
    @Override
    public boolean delete(int idRecompensa, DAOManager daoManager) {
        String sql = "DELETE FROM recompensa WHERE id = "+ idRecompensa + ";";
        return daoManager.ejecutaSentencia(sql);
    }
    @Override
    public Recompensa read(int idRecompensa, DAOManager daoManager) {
        String sql = "SELECT * FROM recompensa WHERE id = "+ idRecompensa + ";";
        try(Statement stmt = daoManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            if(rs.next()) {
                Recompensa recompensa = new Recompensa(
                        rs.getString("descripcion"),
                        rs.getDouble("cantidadMinima"));
                return recompensa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<Recompensa> readAll(DAOManager daoManager) {
        String sql = "SELECT * FROM recompensa;";
        ArrayList<Recompensa> recompensas = new ArrayList<>();
        try (Statement stmt = daoManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                Recompensa recompensa = new Recompensa(
                        rs.getString("descripcion"),
                        rs.getDouble("cantidadMinima"));
                recompensas.add(recompensa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recompensas;
    }
}
