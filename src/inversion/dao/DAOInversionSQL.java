package inversion.dao;

import inversion.Inversion;
import inversor.Inversor;
import proyecto.Proyecto;
import proyecto.Recompensa;
import proyecto.daoProyecto.DAOProyectoSQL;
import proyecto.daoRecompensa.DAORecompensaSQL;
import usuario.dao.DAOUsuarioSQL;
import utilidades.DAOManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOInversionSQL implements DAOInversion {
    @Override
    public boolean insert(Inversion inversion, DAOManager daoManager){
        String sql= "INSERT INTO inversion (correo_inversor, id_proyecto, cantidad, fecha, id_recompensa) VALUES ('"
                + inversion.getCorreoInversor() + "','"
                + inversion.getIDProyecto() + "','"
                + inversion.getCantidad() + "','"
                + inversion.getFecha() + "','"
                + inversion.getIDRecompensa() + "');";
        return daoManager.ejecutaSentencia(sql);
    }
    @Override
    public boolean delete(int idInversion, DAOManager daoManager){
        String sql= "DELETE FROM inversion WHERE id = "+ idInversion + ";";
        return daoManager.ejecutaSentencia(sql);
    }
    @Override
    public boolean update(Inversion inversion, DAOManager daoManager){
        String sql= "UPDATE inversion SET correo_inversor = '"
                + inversion.getCorreoInversor() + "', id_proyecto = "
                + inversion.getIDProyecto() + ", cantidad = "
                + inversion.getCantidad() + ", fecha = '"
                + inversion.getFecha() + "', id_recompensa= "
                + inversion.getIDRecompensa() + " WHERE id = " + inversion.getID() + ";";
        return daoManager.ejecutaSentencia(sql);
    }
    @Override
    public Inversion read(int idInversion, DAOManager daoManager) {
        String sql = "SELECT * FROM entradas WHERE id = "+ idInversion + ";";
        try(Statement stmt = daoManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            if(rs.next()) {
                return datosInversion (rs, daoManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<Inversion> readAll(DAOManager daoManager) {
        String sql = "SELECT * FROM inversion;";
        ArrayList<Inversion> lista = new ArrayList<>();
        try (Statement stmt = daoManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                Inversion inversion = datosInversion(rs, daoManager);
                lista.add(inversion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Inversion datosInversion(ResultSet rs, DAOManager daoManager) throws SQLException{
        String correo_inversor= rs.getString("correo_inversor");
        int id_proyecto= rs.getInt("id_proyecto");
        double cantidad= rs.getDouble("cantidad");
        int id_recompensa= rs.getInt("id_recompensa");

        //Vamos a instanciar objetos de las clases inversor, proyecto y recompensa
        //Para ello, utilizamos el metodo read con sus respectivas claves primarias
        DAOUsuarioSQL daoUsuario= new DAOUsuarioSQL();
        DAOProyectoSQL daoProyecto= new DAOProyectoSQL();
        DAORecompensaSQL daoRecompensa= new DAORecompensaSQL();

        Inversor inversor= (Inversor) daoUsuario.read(correo_inversor, daoManager);
        Proyecto proyecto= daoProyecto.read(id_proyecto,daoManager);
        Recompensa recompensa= daoRecompensa.read(id_recompensa, daoManager);

        return new Inversion(inversor, proyecto, cantidad, recompensa);
    }
}
