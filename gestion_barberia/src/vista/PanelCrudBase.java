package vista;

public class PanelCrudBase extends javax.swing.JPanel {

    protected int idSeleccionado = -1;

    public PanelCrudBase() {
        super();
    }

    protected void cargarDatosIniciales() {
        // Cada panel lo sobrescribe
    }

    protected void limpiarFormulario() {
        idSeleccionado = -1;
    }

    protected boolean haySeleccion() {
        return idSeleccionado != -1;
    }

    protected int getIdSeleccionado() {
        return idSeleccionado;
    }

    protected void setIdSeleccionado(int id) {
        idSeleccionado = id;
    }
}