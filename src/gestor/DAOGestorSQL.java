package gestor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import administrador.DAOUsuario;
import usuario.Usuario;
import administrador.DAOManager;

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

    // todo -> implementar el método readAll para Gestor
}
