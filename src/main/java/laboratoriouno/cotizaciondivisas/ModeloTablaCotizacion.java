/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas;

import javax.swing.event.TableModelListener;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Jere
 */
public class ModeloTablaCotizacion implements javax.swing.table.TableModel {

    public static final int INDICE_MONEDA = 0;
    public static final int INDICE_CAMBIO = 1;
    protected String[] nombreColumnas;
    protected Object[] datos;

    public ModeloTablaCotizacion() {
        this.nombreColumnas = new String[]{"Moneda", "Cambio"};
        this.datos = new Object[0];
        //this.datos[0]=new DatosMoneda("ARS","Pesos");
    }

    public int getRowCount() {
        return datos.length;
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
        DatosMoneda aux = (DatosMoneda) (datos[rowIndex]);
        /*if (columnIndex == INDICE_MONEDA) {

         return aux.getSiglas();
         }
         if (columnIndex == INDICE_CAMBIO) {

         return aux.getDescripcion();
         }

         throw new IllegalArgumentException("unknown column identifier: " + columnIndex);*/
        switch (columnIndex) {
            case INDICE_MONEDA:
                return aux.getSiglas();
            case INDICE_CAMBIO:
                return aux.getDescripcion();
            default:
                return new Object();
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        DatosMoneda aux = (DatosMoneda) (datos[rowIndex]);
        /*if (columnIndex == INDICE_MONEDA) {

         aux.setSiglas((String) aValue);
         }
         if (columnIndex == INDICE_CAMBIO) {

         aux.setDescripcion((String) aValue);
         }

         throw new UnsupportedOperationException("unknown column identifier: " + columnIndex);*/
        switch (columnIndex) {
            case INDICE_MONEDA:
                aux.setSiglas((String) aValue);
                break;
            case INDICE_CAMBIO:
                aux.setDescripcion((String) aValue);
                break;
            default:
                System.out.println("invalid index");
        }
    }
    public void addRow() {
        int r = this.getRowCount();
        r++;
        Object[] d = new Object[r];
        for (int i=0;i<=this.getRowCount();i++){
            d[i]=this.datos[i];
        }
        this.datos = d; 
    }
    public void addTableModelListener(TableModelListener l) {
    }

    public void removeTableModelListener(TableModelListener l) {
    }


}
