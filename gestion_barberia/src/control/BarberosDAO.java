package control;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
    
    public int totalBarberos() {
    int total = 0;
    String sql = "SELECT COUNT(*) FROM barberos"; 

    try (
        Connection con = Conexion.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        if (rs.next()) {
            total = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("Error total barberos: " + e.getMessage());
    }
    return total;
}
    
    public int totalServicios() {
    int total = 0;
    String sql = "SELECT COUNT(*) FROM servicios"; 

    try (
        Connection con = Conexion.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        if (rs.next()) {
            total = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("Error total servicios: " + e.getMessage());
    }
    return total;
}
    
    public int totalCitasHoy() {
    int total = 0;
    String sql = "SELECT COUNT(*) FROM citas WHERE fecha = date('now', 'localtime')"; 

    try (
        Connection con = Conexion.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        if (rs.next()) {
            total = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("Error total citas hoy: " + e.getMessage());
    }
    return total;
}
    
    public java.util.List<Object[]> listarProximasCitas() {
        java.util.List<Object[]> lista = new java.util.ArrayList<>();

        String sql = "SELECT c.nombre AS cliente, b.nombre AS barbero, ci.hora, ci.estado "
                + "FROM citas ci "
                + "INNER JOIN clientes c ON ci.id_cliente = c.id_cliente "
                + "INNER JOIN barberos b ON ci.id_barbero = b.id_barbero "
                + "WHERE ci.fecha = date('now', 'localtime') "
                + "ORDER BY ci.hora ASC";

        try (
                Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                Object[] fila = new Object[4];
                fila[0] = rs.getString("cliente");
                fila[1] = rs.getString("barbero");
                fila[2] = rs.getString("hora");
                fila[3] = rs.getString("estado");

                lista.add(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar próximas citas en DAO: " + e.getMessage());
        }

        return lista;
    }
    
    public boolean actualizarPassword(String username,
            String passwordActual,
            String passwordNueva) {

        String verificar
                = "SELECT * FROM usuarios WHERE username=? AND password=?";

        String actualizar
                = "UPDATE usuarios SET password=? WHERE username=?";

        try (
                Connection con = Conexion.conectar();) {

            PreparedStatement psVerificar
                    = con.prepareStatement(verificar);

            psVerificar.setString(1, username);
            psVerificar.setString(2, passwordActual);

            ResultSet rs = psVerificar.executeQuery();

            if (!rs.next()) {
                return false;
            }

            PreparedStatement psActualizar
                    = con.prepareStatement(actualizar);

            psActualizar.setString(1, passwordNueva);
            psActualizar.setString(2, username);

            return psActualizar.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error actualizar contraseña: "
                    + e.getMessage());

            return false;
        }
    }
}