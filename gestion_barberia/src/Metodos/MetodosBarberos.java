/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;


import control.BarberosDAO;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Barbero;
/**
 *
 * @author jeanf
 */
public class MetodosBarberos {
    BarberosDAO dao = new BarberosDAO();

    public void llenarTabla(JTable tabla, String busqueda) {
        List<Barbero> lista = dao.listar(busqueda);
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); 
        
        for (Barbero b : lista) {
            Object[] fila = {
                b.getIdBarbero(), 
                b.getNombre(), 
                b.getComision(), 
                b.getEstado()
            };
            modelo.addRow(fila);
        }
    }

    public void registrarBarbero(String nombre, double comision, String estado) {
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre es obligatorio");
            return;
        }
        
        Barbero b = new Barbero();
        b.setNombre(nombre);
        b.setComision(comision);
        b.setEstado(estado);

        int resultado = dao.insertar(b); 
        if (resultado > 0) {
            JOptionPane.showMessageDialog(null, "¡Barbero registrado con éxito!");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar barbero.");
        }
    }


    public void actualizarBarbero(int id, String nombre, double comision, String estado) {
        Barbero b = new Barbero();
        b.setIdBarbero(id);
        b.setNombre(nombre);
        b.setComision(comision);
        b.setEstado(estado);

        int resultado = dao.actualizar(b);
        if (resultado > 0) {
            JOptionPane.showMessageDialog(null, "¡Barbero actualizado!");
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar.");
        }
    }
    public void eliminarBarbero(int id) {
        int confirmacion = JOptionPane.showConfirmDialog(null, 
            "¿Estás seguro de eliminar este barbero?", "Confirmar", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            int resultado = dao.eliminar(id);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Barbero eliminado.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo eliminar.");
            }
        }
    }

    public void limpiarFormulario(JTextField id, JTextField nom, JTextField com, JComboBox est) {
        id.setText("");
        nom.setText("");
        com.setText("");
        if (est.getItemCount() > 0) {
            est.setSelectedIndex(0);
        }
    }
    
    public int totalClientes() {

    return dao.totalClientes();

}
}
