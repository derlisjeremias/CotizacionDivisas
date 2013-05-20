/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.conector;

import javax.swing.event.TableModelListener;
import laboratoriouno.cotizaciondivisas.modelo.Usuario;

/**
 *
 * @author Jere
 */
public class TablaUsuarios implements javax.swing.table.TableModel{
    protected String[] nombresColumnas;
    protected Object[][] filasColumnasTabla;
    
    public TablaUsuarios(){
    this.nombresColumnas = new String[]{"Nombre", "Cant monedas"};
    this.filasColumnasTabla = new Object[0][2];
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
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
