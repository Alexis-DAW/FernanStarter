package proyecto.daoProyecto;

import gestor.Gestor;
import proyecto.Categoria;
import proyecto.Proyecto;
import usuario.Usuario;
import usuario.dao.DAOUsuarioSQL;
import utilidades.DAOManager;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAOProyectoSQL implements DAOProyecto {

    private ArrayList<Proyecto> listadoProyectos;
    @Override
    public boolean insert(Proyecto proyecto, DAOManager daoManager) {
        String sql = "INSERT INTO proyecto (nombre, descripcion, cantidad_necesaria, cantidad_financiada, fecha_inicio, fecha_fin, categoria, correo_gestor) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = daoManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, proyecto.getNombre());
            stmt.setString(2, proyecto.getDescripcion());
            stmt.setDouble(3, proyecto.getCantidadNecesaria());
            stmt.setDouble(4, proyecto.getCantidadFinanciada());
            stmt.setDate(5, Date.valueOf(proyecto.getFechaInicio()));
            stmt.setDate(6, Date.valueOf(proyecto.getFechaFin()));
            stmt.setString(7, proyecto.getCategoria().name());
            stmt.setString(8, proyecto.getUsuario().getCorreo());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    System.out.println("Proyecto insertado con ID: " + idGenerado);
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar proyecto: " + e.getMessage());
        }
        return false;
    }
    @Override
    public boolean update(Proyecto proyecto, DAOManager daoManager) {
        String sql = "UPDATE proyecto SET nombre=?, descripcion=?, cantidad_necesaria=?, cantidad_financiada=?, fecha_inicio=?, fecha_fin=?, categoria=? " +
                "WHERE id=?";
        try (PreparedStatement stmt = daoManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, proyecto.getNombre());
            stmt.setString(2, proyecto.getDescripcion());
            stmt.setDouble(3, proyecto.getCantidadNecesaria());
            stmt.setDouble(4, proyecto.getCantidadFinanciada());
            stmt.setDate(5, Date.valueOf(proyecto.getFechaInicio()));
            stmt.setDate(6, Date.valueOf(proyecto.getFechaFin()));
            stmt.setString(7, proyecto.getCategoria().name());
            stmt.setInt(8, proyecto.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar proyecto: " + e.getMessage());
        }
        return false;
    }
    @Override
    public boolean delete(int idProyecto, DAOManager daoManager) {
        String sql = "DELETE FROM proyecto WHERE id=?";
        try (PreparedStatement stmt = daoManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar proyecto: " + e.getMessage());
        }
        return false;
    }
    @Override
    public Proyecto read(int idProyecto, DAOManager daoManager) {
        String sql = "SELECT * FROM proyecto WHERE id=?";
        try (PreparedStatement stmt = daoManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idProyecto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirProyectoDesdeRS(rs, daoManager);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al leer proyecto: " + e.getMessage());
        }
        return null;
    }
    @Override
    public ArrayList<Proyecto> readAll(DAOManager daoManager) {
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM proyecto";
        try (Statement stmt = daoManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                proyectos.add(construirProyectoDesdeRS(rs, daoManager));
            }
        } catch (SQLException e) {
            System.err.println("Error al leer todos los proyectos: " + e.getMessage());
        }
        return proyectos;
    }

    private Proyecto construirProyectoDesdeRS(ResultSet rs, DAOManager daoManager) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String descripcion = rs.getString("descripcion");
        double cantidadNecesaria = rs.getDouble("cantidad_necesaria");
        double cantidadFinanciada = rs.getDouble("cantidad_financiada");
        LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
        LocalDate fechaFin = rs.getDate("fecha_fin").toLocalDate();
        Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

        String correoGestor = rs.getString("correo_gestor");
        DAOUsuarioSQL daoUsuario = new DAOUsuarioSQL();
        Gestor gestor = (Gestor) daoUsuario.read(correoGestor, daoManager);

        if (gestor == null) {
            throw new SQLException("No se encontró el gestor con correo: " + correoGestor);
        }

        Proyecto p = new Proyecto(id, nombre, descripcion, cantidadNecesaria, fechaInicio, fechaFin, categoria, gestor);
        p.setCantidadFinanciada(cantidadFinanciada);
        return p;
    }

    public boolean cargarUsuarios(DAOManager daoManager){
        listadoProyectos= readAll(daoManager);
        if(listadoProyectos!=null) return true;
        else return false;
    }

    public boolean guardarUsuarios(ArrayList<Proyecto> listaProyectos, DAOManager daoManager) {
        for(Proyecto proyecto: listaProyectos){
            insert(proyecto, daoManager);
        }
        return true;
    }
}
