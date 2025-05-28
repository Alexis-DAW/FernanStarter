package usuario.dao;

import administrador.Administrador;
import gestor.Gestor;
import inversor.Inversor;
import usuario.Usuario;
import utilidades.DAOManager;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOUsuarioSQL implements DAOUsuario {

    @Override
    public boolean insert(Usuario usuario, DAOManager daoManager) {
        String sql = "INSERT INTO usuario (nombre, contrasena, correo, tipo) VALUES ('"
                + usuario.getNombre() + "','"
                + usuario.getContrasena() + "','"
                + usuario.getCorreo() + "','"
                + usuario.getTipoUsuario().toString() + "');";
        return daoManager.ejecutaSentencia(sql);
    }

    @Override
    public boolean update(Usuario usuario, DAOManager daoManager) {
        String sql = "UPDATE usuario SET " +
                "nombre = '" + usuario.getNombre() + "', " +
                "contrasena = '" + usuario.getContrasena() + "' " +
                "WHERE correo = '" + usuario.getCorreo() + "';";
        return daoManager.ejecutaSentencia(sql);
    }

    @Override
    public boolean delete(String correo, DAOManager daoManager) {
        String sql = "DELETE FROM usuario WHERE correo = '" + correo + "';";
        return daoManager.ejecutaSentencia(sql);
    }

    @Override
    public Usuario read(String correo, DAOManager daoManager) {
        String sql = "SELECT * FROM usuario WHERE correo = ?";

        try (Connection conn = daoManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario= crearUsuario(rs);
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al leer el usuario con correo '" + correo + "': " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> readAll(DAOManager daoManager) {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Statement stmt = daoManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario= crearUsuario(rs);
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Usuario crearUsuario(ResultSet rs) throws SQLException{

        String nombre= rs.getString("nombre");
        String contrasena= rs.getString("contrasena");
        String correo= rs.getString("correo");
        String tipo= rs.getString("tipo");
        Usuario usuario= switch (tipo) {
            case "GESTOR" -> new Gestor(nombre, contrasena, correo);
            case "INVERSOR" -> new Inversor(nombre, contrasena, correo);
            case "ADMINISTRADOR" -> new Administrador(nombre, contrasena, correo);
            default -> null;
        };
        return usuario;
    }

//    public boolean cargarUsuarios(DAOManager daoManager){
//        listadoUsuarios = readAll(daoManager);
//        if (listadoUsuarios != null && !listadoUsuarios.isEmpty()) {
//            for (Usuario u : listadoUsuarios) {
//                System.out.println("Cargado usuario: " + u.getCorreo());
//            }
//            return true;
//        }
//        return false;
//    }
//
    public boolean guardarUsuarios(ArrayList<Usuario> listaUsuarios, DAOManager daoManager){
        for(Usuario usuario: listaUsuarios){
            insert(usuario, daoManager);
        }
        return true;
    }

    public Usuario buscaPorNombre(String nombre, DAOManager daoManager) {
        String sql = "SELECT * FROM usuario WHERE nombre LIKE ?";

        try (Connection conn = daoManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario= crearUsuario(rs);
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al leer el usuario con nombre '" + nombre + "': " + e.getMessage());
        }
        return null;
    }

    public boolean bloquearUsuario(Usuario usuario, DAOManager daoManager) {
        String sql = "UPDATE usuario SET bloqueado = true WHERE nombre = '" + usuario.getNombre() + "';";
        return daoManager.ejecutaSentencia(sql);
    }

    public boolean desbloquearUsuario(Usuario usuario, DAOManager daoManager) {
        String sql = "UPDATE usuario SET bloqueado = false WHERE nombre = '" + usuario.getNombre() + "';";
        return daoManager.ejecutaSentencia(sql);
    }

}
