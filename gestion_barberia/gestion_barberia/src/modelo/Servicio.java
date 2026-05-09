/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jeanf
 */
public class Servicio {
    private int idServicio;
    private String nombreServicio;
    private double precio;

    public Servicio() {}

    public Servicio(int idServicio, String nombreServicio, double precio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }

    // Getters y Setters
    public int getIdServicio() { return idServicio; }
    public void setIdServicio(int idServicio) { this.idServicio = idServicio; }

    public String getNombreServicio() { return nombreServicio; }
    public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
