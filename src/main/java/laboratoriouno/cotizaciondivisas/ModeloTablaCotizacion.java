/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Jere
 */
public class ModeloTablaCotizacion implements javax.swing.table.TableModel {

    protected String[] nombreColumnas = {"Moneda", "Cotización"};
    protected Object[][] datos;
    protected TableModelListener listener;
    protected TableModelEvent event = new TableModelEvent(this);

    public ModeloTablaCotizacion() {
        this.datos = new Object[0][2];
    }

    public int getRowCount() {
        return this.datos.length;
    }

    public int getColumnCount() {
        return this.nombreColumnas.length;
    }

    public String getColumnName(int columnIndex) {
        return this.nombreColumnas[columnIndex];
    }

    public Class getColumnClass(int columnIndex) {
        return this.nombreColumnas[columnIndex].getClass();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.datos[rowIndex][columnIndex];
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            CotizacionMoneda aux = (CotizacionMoneda) aValue;
            if (columnIndex == 0) {
                this.datos[rowIndex][columnIndex] = aux.getSiglas();
            }
            if (columnIndex == 1) {
                this.datos[rowIndex][columnIndex] = aux.getCambio();
            }
        listener.tableChanged(event);
    }

    public void addRow() {
        int r = this.getRowCount();
        r++;
        Object[][] d = new Object[r][2];
        int i = 0;
        while (i < r-1) {
            d[i][0] = this.datos[i][0];
            d[i][1] = this.datos[i][1];
            i++;
        }
        this.datos = d;
        listener.tableChanged(event);
    }

    public void addTableModelListener(TableModelListener l) {
        listener = l;
    }

    public void removeTableModelListener(TableModelListener l) {
    }
}
