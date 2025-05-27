package gestor;

import java.sql.*;
import java.util.ArrayList;

import administrador.DAOUsuario;
import usuario.Usuario;
import utilidades.DAOManager;

public class DAOGestorSQL implements DAOUsuario {
    @Override
    public boolean insert(Usuario usuario, DAOManager daoManager) {
        String sql = "INSERT INTO usuario VALUES ('"
                + usuario.getNombre() + "','"
                + usuario.getContrasena() + "','"
                + usuario.getCorreo() + "', 'GESTOR');";
        return daoManager.ejecutaSentencia(sql);
    }

    @Override
    public boolean update(Usuario usuario, DAOManager daoManager) {
        String sql = "UPDATE usuario SET nombre = '" + usuario.getNombre()
                + "', contrasena = '" + usuario.getContrasena()
                + "' WHERE correo = '" + usuario.getCorreo() + "' AND tipo = 'GESTOR';";
        return daoManager.ejecutaSentencia(sql);
    }

    @Override
    public boolean delete(String correo, DAOManager daoManager) {
        String sql = "DELETE FROM usuario WHERE correo = '" + correo + "' AND tipo = 'GESTOR';";
        return daoManager.ejecutaSentencia(sql);
    }

    @Override
    public Gestor read(String correo, DAOManager daoManager) {
        String sql = "SELECT nombre, contrasena FROM usuario WHERE correo = ? AND tipo = 'GESTOR'";

        try (Connection conn = daoManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String contrasena = rs.getString("contrasena");

                    return new Gestor(nombre, contrasena, correo);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al leer el gestor con correo '" + correo + "': " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> readAll(DAOManager daoManager) {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE tipo = 'GESTOR';";
        try (Statement stmt = daoManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Gestor gestor = new Gestor(
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("correo"));

                lista.add(gestor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
