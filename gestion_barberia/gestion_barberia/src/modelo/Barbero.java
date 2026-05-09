/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jeanf
 */
public class Barbero {
    private int idBarbero;
    private String nombre;
    private double comision;
    private String estado;

    public Barbero() {}

    public Barbero(int idBarbero, String nombre, double comision, String estado) {
        this.idBarbero = idBarbero;
        this.nombre = nombre;
        this.comision = comision;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdBarbero() { return idBarbero; }
    public void setIdBarbero(int idBarbero) { this.idBarbero = idBarbero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getComision() { return comision; }
    public void setComision(double comision) { this.comision = comision; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
