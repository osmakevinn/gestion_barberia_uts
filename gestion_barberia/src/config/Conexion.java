/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jeanf
 */
public class Conexion {
    private static final String URL = "jdbc:sqlite:" + System.getProperty("user.dir") + java.io.File.separator + "proyecto_barberia.db";
    public static Connection conectar() {
        Connection con = null;
        try {
          
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return con;
    }
}
