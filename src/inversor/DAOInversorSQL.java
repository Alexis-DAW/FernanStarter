package inversor;

import administrador.DAOUsuario;
import usuario.Usuario;
import administrador.DAOManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOInversorSQL implements DAOUsuario {
    public boolean insert(Usuario usuario, DAOManager daoManager) {
        String sql = "INSERT INTO usuario VALUES ('"
                + usuario.getNombre() + "','"
                + usuario.getContrasena() + "','"
                + usuario.getCorreo() + "', 'INVERSOR');";
        return daoManager.ejecutaSentencia(sql);
    }

    public boolean update(Usuario usuario, DAOManager daoManager) {
        String sql = "UPDATE usuario SET nombre = '" + usuario.getNombre()
                + "', contrasena = '" + usuario.getContrasena()
                + "' WHERE correo = '" + usuario.getCorreo() + "';";
        return daoManager.ejecutaSentencia(sql);
    }

    public boolean delete(String correo, DAOManager daoManager) {
        String sql = "DELETE FROM usuario WHERE correo = '" + correo + "';";
        return daoManager.ejecutaSentencia(sql);
    }

    public Inversor read(String correo, DAOManager daoManager) {
        String sql = "SELECT * FROM usuario WHERE correo = '" + correo + "' AND tipo = 'INVERSOR';";
        try (Statement stmt = daoManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                Inversor inversor = new Inversor(
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("correo"));
                return inversor;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Usuario> readAll(DAOManager daoManager) {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario;";
        try (Statement stmt = daoManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Inversor inversor = new Inversor(
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("correo"));
                lista.add(inversor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
