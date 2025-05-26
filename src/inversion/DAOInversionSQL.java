package inversion;

import inversor.Inversor;
import proyecto.Proyecto;
import proyecto.Recompensa;
import administrador.DAOManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOInversionSQL implements DAOInversion {

    public boolean insert(Inversion inversion, DAOManager daoManager){
        String sql= "INSERT INTO inversion VALUES ('"
                + inversion.getCorreoInversor() + "','"
                + inversion.getIDProyecto() + "','"
                + inversion.getCantidad() + "','"
                + inversion.getFecha() + "','"
                + inversion.getIDRecompensa() + "');";
        return daoManager.ejecutaSentencia(sql);
    }

    public boolean delete(int idInversion, DAOManager daoManager){
        String sql= "DELETE FROM inversion WHERE id= "+ idInversion + ";";
        return daoManager.ejecutaSentencia(sql);
    }

    public boolean update(Inversion inversion, DAOManager daoManager){
        String sql= "UPDATE inversion SET correo_inversor= '"
                + inversion.getCorreoInversor() + "', id_proyecto= "
                + inversion.getIDProyecto() + ", cantidad= "
                + inversion.getCantidad() + ", fecha= '"
                + inversion.getFecha() + "', id_recompensa= "
                + inversion.getIDRecompensa() + " WHERE id= " + inversion.getID() + ";";
        return daoManager.ejecutaSentencia(sql);
    }

    public Inversion read(int idInversion, DAOManager daoManager) {
        String sql = "SELECT * FROM entradas WHERE id = "+ idInversion + ";";
        try{
            Statement stmt = daoManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                return datosInversion (rs, daoManager);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Inversion> readAll(DAOManager daoManager) {
        String sql = "SELECT * FROM inversion;";
        ArrayList<Inversion> lista = new ArrayList<>();
        try {
            Statement stmt = daoManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Inversion inversion = datosInversion(rs, daoManager);
                lista.add(inversion);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Inversion datosInversion(ResultSet rs, DAOManager daoManager) throws SQLException {
        String correo_inversor= rs.getString("correo_inversor");
        int id_proyecto= rs.getInt("id_proyecto");
        double cantidad= rs.getDouble("cantidad");
        int id_recompensa= rs.getInt("id_recompensa");
        Inversor inversor= daoManager.getDAOInversor().read(correo_inversor, daoManager);
        Proyecto proyecto= daoManager.getDAOProyecto().read(id_proyecto,daoManager);
        Recompensa recompensa= daoManager.getDAORecompensa().read(id_recompensa, daoManager);
        return new Inversion(inversor, proyecto, cantidad,recompensa);
    }
}
