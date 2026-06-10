package control;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Barbero;

public class BarberosDAO {

    public List<Barbero> listar(String buscar) {
        List<Barbero> datos = new ArrayList<>();

        String sql = "SELECT id_barbero, nombre, comision, estado "
                + "FROM barberos "
                + "WHERE nombre LIKE ? "
                + "ORDER BY nombre";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + buscar + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Barbero b = new Barbero();

                    b.setIdBarbero(rs.getInt("id_barbero"));
                    b.setNombre(rs.getString("nombre"));
                    b.setComision(rs.getDouble("comision"));
                    b.setEstado(rs.getString("estado"));

                    datos.add(b);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listar barberos: " + e.getMessage());
        }

        return datos;
    }

    public int insertar(Barbero b) {
        String sql = "INSERT INTO barberos (nombre, comision, estado) VALUES (?, ?, ?)";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, b.getNombre());
            ps.setDouble(2, b.getComision());
            ps.setString(3, b.getEstado());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertar barbero: " + e.getMessage());
            return 0;
        }
    }

    public int actualizar(Barbero b) {
        String sql = "UPDATE barberos SET nombre = ?, comision = ?, estado = ? "
                + "WHERE id_barbero = ?";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, b.getNombre());
            ps.setDouble(2, b.getComision());
            ps.setString(3, b.getEstado());
            ps.setInt(4, b.getIdBarbero());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizar barbero: " + e.getMessage());
            return 0;
        }
    }

    public int eliminar(int id) {
        String sql = "DELETE FROM barberos WHERE id_barbero = ?";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error eliminar barbero: " + e.getMessage());
            return 0;
        }
    }

    public int totalClientes() {
        int total = 0;

        String sql = "SELECT COUNT(*) FROM clientes";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error total clientes: " + e.getMessage());
        }

        return total;
    }
}