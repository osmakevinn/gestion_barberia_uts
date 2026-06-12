package Metodos;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Mcitas {

    public Mcitas() {
        // Ya no abrimos conexión aquí.
    }

    public void actualizarCita(
            int idCita,
            int idCliente,
            int idBarbero,
            int idServicio,
            String fecha,
            String hora,
            String estado
    ) {
        String sql = "UPDATE citas SET "
                + "id_cliente = ?, "
                + "id_barbero = ?, "
                + "id_servicio = ?, "
                + "fecha = ?, "
                + "hora = ?, "
                + "estado = ? "
                + "WHERE id_cita = ?";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, idCliente);
            ps.setInt(2, idBarbero);
            ps.setInt(3, idServicio);
            ps.setString(4, fecha);
            ps.setString(5, hora);
            ps.setString(6, estado);
            ps.setInt(7, idCita);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar cita: " + e.getMessage());
        }
    }

    public void cargarClientes(JComboBox<String> combo) {
        combo.removeAllItems();
        combo.addItem("Seleccione...");

        String sql = "SELECT id_cliente, nombre FROM clientes ORDER BY nombre";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                combo.addItem(rs.getInt("id_cliente") + " - " + rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
        }
    }

    public void cargarBarberos(JComboBox<String> combo) {
        combo.removeAllItems();
        combo.addItem("Seleccione...");

        String sql = "SELECT id_barbero, nombre FROM barberos WHERE estado = 'Activo' ORDER BY nombre";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                combo.addItem(rs.getInt("id_barbero") + " - " + rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar barberos: " + e.getMessage());
        }
    }

    public void cargarServicios(JComboBox<String> combo) {
        combo.removeAllItems();
        combo.addItem("Seleccione...");

        String sql = "SELECT id_servicio, nombre_servicio FROM servicios ORDER BY nombre_servicio";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                combo.addItem(rs.getInt("id_servicio") + " - " + rs.getString("nombre_servicio"));
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar servicios: " + e.getMessage());
        }
    }

    public void registrarCita(
            int idCliente,
            int idBarbero,
            int idServicio,
            String fecha,
            String hora,
            String estado
    ) {
        String sql = "INSERT INTO citas "
                + "(id_cliente, id_barbero, id_servicio, fecha, hora, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, idCliente);
            ps.setInt(2, idBarbero);
            ps.setInt(3, idServicio);
            ps.setString(4, fecha);
            ps.setString(5, hora);
            ps.setString(6, estado);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar cita: " + e.getMessage());
        }
    }

    public void llenarTabla(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("Barbero");
        modelo.addColumn("Servicio");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Estado");

        String sql = "SELECT "
                + "c.id_cita, "
                + "cl.nombre AS cliente, "
                + "b.nombre AS barbero, "
                + "s.nombre_servicio AS servicio, "
                + "c.fecha, "
                + "c.hora, "
                + "c.estado "
                + "FROM citas c "
                + "LEFT JOIN clientes cl ON c.id_cliente = cl.id_cliente "
                + "LEFT JOIN barberos b ON c.id_barbero = b.id_barbero "
                + "LEFT JOIN servicios s ON c.id_servicio = s.id_servicio "
                + "ORDER BY c.fecha DESC, c.hora DESC";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Object[] fila = new Object[7];

                fila[0] = rs.getInt("id_cita");
                fila[1] = rs.getString("cliente");
                fila[2] = rs.getString("barbero");
                fila[3] = rs.getString("servicio");
                fila[4] = rs.getString("fecha");
                fila[5] = rs.getString("hora");
                fila[6] = rs.getString("estado");

                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (SQLException e) {
            System.out.println("Error al llenar tabla de citas: " + e.getMessage());
        }
    }

    public void eliminarCita(int idCita) {
        String sql = "DELETE FROM citas WHERE id_cita = ?";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, idCita);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar cita: " + e.getMessage());
        }
    }
}