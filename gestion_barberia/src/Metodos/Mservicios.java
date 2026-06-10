package Metodos;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Mservicios {

    public void registrarServicio(String nombreServicio, double precio) {
        String sql = "INSERT INTO servicios (nombre_servicio, precio) VALUES (?, ?)";

        try (
            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, nombreServicio);
            ps.setDouble(2, precio);

            ps.executeUpdate();

            System.out.println("Servicio registrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al registrar servicio: " + e.getMessage());
        }
    }

    public void actualizarServicio(int idServicio, String nombreServicio, double precio) {
        String sql = "UPDATE servicios SET nombre_servicio = ?, precio = ? WHERE id_servicio = ?";

        try (
            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, nombreServicio);
            ps.setDouble(2, precio);
            ps.setInt(3, idServicio);

            ps.executeUpdate();

            System.out.println("Servicio actualizado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar servicio: " + e.getMessage());
        }
    }

    public void eliminarServicio(int idServicio) {
        String sql = "DELETE FROM servicios WHERE id_servicio = ?";

        try (
            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, idServicio);
            ps.executeUpdate();

            System.out.println("Servicio eliminado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al eliminar servicio: " + e.getMessage());
        }
    }

    public void llenarTabla(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");

        String sql = "SELECT id_servicio, nombre_servicio, precio FROM servicios ORDER BY id_servicio DESC";

        try (
            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Object[] fila = new Object[3];

                fila[0] = rs.getInt("id_servicio");
                fila[1] = rs.getString("nombre_servicio");
                fila[2] = rs.getDouble("precio");

                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

            System.out.println("Servicios cargados en tabla: " + modelo.getRowCount());

        } catch (SQLException e) {
            System.out.println("Error al llenar tabla de servicios: " + e.getMessage());
        }
    }
}