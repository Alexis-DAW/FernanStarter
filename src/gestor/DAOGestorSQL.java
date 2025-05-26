package gestor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilidades.DAOManager;

public class DAOGestorSQL {

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


}
