package administrador;

import usuario.Usuario;
import utilidades.DAOManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAdministradorSQL implements DAOUsuario {
    @Override
    public boolean insert(Usuario usuario, DAOManager daoManager) {
        String sql = "INSERT INTO usuario VALUES ('"
                + usuario.getNombre() + "','"
                + usuario.getContrasena() + "','"
                + usuario.getCorreo() + "', 'ADMINISTRADOR');";
        return daoManager.ejecutaSentencia(sql);
    }
    @Override
    public boolean update(Usuario usuario, DAOManager daoManager) {
        String sql = "UPDATE usuario SET nombre = '" + usuario.getNombre()
                + "', contrasena = '" + usuario.getContrasena()
                + "' WHERE correo = " + usuario.getCorreo() + " AND tipo = 'ADMINISTRADOR';";
        return daoManager.ejecutaSentencia(sql);
    }
    @Override
    public boolean delete(String correo, DAOManager daoManager) {
        String sql = "DELETE FROM usuario WHERE correo = '" + correo + "' AND tipo = 'ADMINISTRADOR';";
        return daoManager.ejecutaSentencia(sql);
    }
    @Override
    public Administrador read(String correo, DAOManager daoManager) {
        String sql = "SELECT * FROM usuario WHERE correo = '" + correo + "' AND tipo = 'ADMINISTRADOR';";
        try (Statement stmt = daoManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                Administrador administrador = new Administrador(
                        rs.getString("nombre"),
                        rs.getString("contrasena"), correo);

                return administrador;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<Usuario> readAll(DAOManager daoManager) {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE tipo = 'ADMINISTRADOR';";
        try (Statement stmt = daoManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Administrador administrador = new Administrador(
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("correo"));

                lista.add(administrador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
