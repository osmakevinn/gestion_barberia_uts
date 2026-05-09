/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Barbero;
/**
 *
 * @author jeanf
 */
public class BarberosDAO {
   Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Barbero> listar(String buscar) {
        List<Barbero> datos = new ArrayList<>();
        // Buscamos por nombre usando LIKE para el buscador en tiempo real
        String sql = "SELECT * FROM barberos WHERE nombre LIKE ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + buscar + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Barbero b = new Barbero();
                b.setIdBarbero(rs.getInt("id_barbero")); // Nombre real en SQLite
                b.setNombre(rs.getString("nombre"));
                b.setComision(rs.getDouble("comision"));
                b.setEstado(rs.getString("estado"));
                datos.add(b);
            }
        } catch (SQLException e) {
            System.out.println("Error listar: " + e.getMessage());
        }
        return datos;
    }

    public int insertar(Barbero b) {
        String sql = "INSERT INTO barberos (nombre, comision, estado) VALUES (?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, b.getNombre());
            ps.setDouble(2, b.getComision());
            ps.setString(3, b.getEstado());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insertar: " + e.getMessage());
            return 0;
        }
    }

    public int actualizar(Barbero b) {
        // Aquí usamos id_barbero con guion bajo
        String sql = "UPDATE barberos SET nombre=?, comision=?, estado=? WHERE id_barbero=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, b.getNombre());
            ps.setDouble(2, b.getComision());
            ps.setString(3, b.getEstado());
            ps.setInt(4, b.getIdBarbero());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return 0;
        }
    }

    public int eliminar(int id) {
        // Aquí también corregimos a id_barbero
        String sql = "DELETE FROM barberos WHERE id_barbero = ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return 0;
        }
    } 
}
