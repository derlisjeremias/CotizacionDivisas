/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.conector;

import java.util.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import laboratoriouno.cotizaciondivisas.modelo.*;

/**
 *
 * @author Jere
 */
public class TablaCotizaciones implements javax.swing.table.TableModel {

    protected String[] nombresColumnas;
    protected Object[][] filasColumnasTabla;
    protected List<TableModelListener> dependientes;
    protected TableModelEvent evento;

    public TablaCotizaciones() {
        this.nombresColumnas = new String[]{"Moneda", "Cotización"};
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
        return this.nombresColumnas.getClass();
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
        MonedaCotizacion cmoneda = (MonedaCotizacion) aValue;
        this.filasColumnasTabla[rowIndex][0] = cmoneda.getSiglas();
        this.filasColumnasTabla[rowIndex][1] = cmoneda.getCotizacion();
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

    public void eliminarUltimaFila() {
        if (this.getRowCount() > 0) {
            Object[][] nuevaTabla = new Object[this.getRowCount() - 1][2];
            int i = 0;
            while (i < this.getRowCount() - 1) {
                nuevaTabla[i][0] = this.filasColumnasTabla[i][0];
                nuevaTabla[i][1] = this.filasColumnasTabla[i][1];
                i++;
            }
            this.filasColumnasTabla = nuevaTabla;
            this.tablaModificada();
        }
    }

    public void limpiarTabla() {
        Object[][] nuevaTabla = new Object[0][2];
        this.filasColumnasTabla = nuevaTabla;
        this.tablaModificada();
    }

    public void cargarMonedasUsuario(Usuario u, AdministracionDivisasUsuarios app) {
        for (Moneda mc : u.getMisMonedas()) {
            this.agregarCotizacion(app.obtenerUnaCotizacion(mc));

        }

    }

    public void agregarCotizacion(MonedaCotizacion mc) {

        this.agregarFila();
        this.setValueAt(mc, this.getRowCount() - 1, 0);
    }
}
