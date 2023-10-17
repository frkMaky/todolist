package datos;

import datos.Conexion;
import dominio.Tarea;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TareaDao {

    private final String SQL_SELECT = "SELECT * FROM tarea";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM tarea WHERE id_tarea = ?";
    private final String SQL_INSERT = "INSERT INTO tarea (titulo, descripcion, estado, fecha_inicio, fecha_fin) VALUES (?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE tarea set titulo=?,descripcion=?,estado=?,fecha_inicio=?,fecha_fin=? WHERE id_tarea=?";
    private final String SQL_DELETE = "DELETE FROM tarea WHERE id_tarea=?";

    public List<Tarea> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Tarea> tareas = new ArrayList<>();

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            Tarea tarea = null;

            while (rs.next()) {
                int idTarea = rs.getInt("id_tarea");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                int estado = rs.getInt("estado"); // 0-PNDT 1-COMPLETA 2-CANCEL
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_fin");

                tarea = new Tarea(idTarea, titulo, descripcion, estado, fechaInicio, fechaFin);
                tareas.add(tarea);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (rs != null) {
                Conexion.close(rs);
            }
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }

        return tareas;
    }

    public Tarea buscar(Tarea tarea) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, tarea.getIdTarea());
            rs = stmt.executeQuery();
            if (rs.next()) {
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstado(rs.getInt("estado")); // 0-PNDT 1-COMPLETA 2-CANCEL
                tarea.setFechaInicio(rs.getDate("fecha_inicio"));
                tarea.setFechaFin(rs.getDate("fecha_fin"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (rs != null) {
                Conexion.close(rs);
            }
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return tarea;
    }

    public int insertar(Tarea tarea) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setInt(3, tarea.getEstado());
            stmt.setDate(4, tarea.getFechaInicio());
            stmt.setDate(5, tarea.getFechaFin());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    public int actualizar(Tarea tarea) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setInt(3, tarea.getEstado());
            stmt.setDate(4, tarea.getFechaInicio());
            stmt.setDate(5, tarea.getFechaFin());
            stmt.setInt(6, tarea.getIdTarea());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    public int eliminar(Tarea tarea) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, tarea.getIdTarea());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

}
