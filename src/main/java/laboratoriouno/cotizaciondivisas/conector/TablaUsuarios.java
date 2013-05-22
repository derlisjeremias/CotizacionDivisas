/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.conector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import laboratoriouno.cotizaciondivisas.modelo.AdministracionDivisasUsuarios;
import laboratoriouno.cotizaciondivisas.modelo.Usuario;

/**
 *
 * @author Jere
 */
public class TablaUsuarios implements javax.swing.table.TableModel {

    protected String[] nombresColumnas;
    protected Object[][] filasColumnasTabla;
    protected List<TableModelListener> dependientes;
    protected TableModelEvent evento;

    public TablaUsuarios() {
        this.nombresColumnas = new String[]{"Nombre", "Cantidad de monedas"};
        this.filasColumnasTabla = new Object[0][2];
        this.dependientes = new ArrayList();
        this.evento = new TableModelEvent(this);
    }

    @Override
    public int getRowCount() {
        return this.filasColumnasTabla.length;
    }

    @Override
    public int getColumnCount() {
        return this.nombresColumnas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.nombresColumnas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.nombresColumnas[columnIndex].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.filasColumnasTabla[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Usuario u = (Usuario) aValue;
        this.filasColumnasTabla[rowIndex][0] = u.getNombre();
        this.filasColumnasTabla[rowIndex][1] = u.getMisMonedas().size();
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        this.dependientes.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void tablaModificada() {
        for (TableModelListener tl : this.dependientes) {
            tl.tableChanged(evento);
        }
    }

    public void limpiarTabla() {
        Object[][] nuevaTabla = new Object[0][2];
        this.filasColumnasTabla = nuevaTabla;
        this.tablaModificada();
    }

    public void agregarFila() {
        Object[][] nuevaTabla = new Object[this.getRowCount() + 1][2];
        int i = 0;
        while (i < this.getRowCount()) {
            nuevaTabla[i][0] = this.filasColumnasTabla[i][0];
            nuevaTabla[i][1] = this.filasColumnasTabla[i][1];
            i++;
        }
        this.filasColumnasTabla = nuevaTabla;
        this.tablaModificada();
    }

    public void cargarUsuarios(AdministracionDivisasUsuarios app) {
        this.limpiarTabla();
        List<Usuario> usuarios;
        usuarios = app.getUsuarios();
        for (Usuario u : usuarios) {
            this.agregarFila();
            this.setValueAt(u, this.getRowCount() - 1, 0);
        }
    }
}
