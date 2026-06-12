package Metodos;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Mclientes {

    public Mclientes() {
        // No se abre conexión aquí.
    }

    public void registrarCliente(String cedula, String nombre, String telefono, String email) {
        String sql = "INSERT INTO clientes (cedula, nombre, telefono, email) "
                + "VALUES (?, ?, ?, ?)";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, cedula);
            ps.setString(2, nombre);
            ps.setString(3, telefono);
            ps.setString(4, email);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: " + e.getMessage());
        }
    }

    public void actualizarCliente(int idCliente, String cedula, String nombre, String telefono, String email) {
        String sql = "UPDATE clientes SET "
                + "cedula = ?, "
                + "nombre = ?, "
                + "telefono = ?, "
                + "email = ? "
                + "WHERE id_cliente = ?";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, cedula);
            ps.setString(2, nombre);
            ps.setString(3, telefono);
            ps.setString(4, email);
            ps.setInt(5, idCliente);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, idCliente);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    public void llenarTabla(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Cédula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Email");

        String sql = "SELECT id_cliente, cedula, nombre, telefono, email "
                + "FROM clientes "
                + "ORDER BY nombre";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Object[] fila = new Object[5];

                fila[0] = rs.getInt("id_cliente");
                fila[1] = rs.getString("cedula");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("telefono");
                fila[4] = rs.getString("email");

                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (SQLException e) {
            System.out.println("Error al llenar tabla de clientes: " + e.getMessage());
        }
    }
}