/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas;

import java.util.List;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Jere
 */
public class ModeloComboBoxDivisas implements javax.swing.ComboBoxModel {

    private ServidorJSon sj = new ServidorJSon();
    private DatosMoneda itemSeleccionado;
    private static List<DatosMoneda> lista = null;

    public ModeloComboBoxDivisas() {
        if (lista == null) {
            lista = sj.getArrayDatosMoneda();
        }
        itemSeleccionado = lista.get(0);
    }

    public void actualizarListaMonedas() {
        lista = sj.getArrayDatosMoneda();
    }

    public void setSelectedItem(Object anItem) {
        this.itemSeleccionado = (DatosMoneda) anItem;
    }

    public Object getSelectedItem() {
        return itemSeleccionado;
    }

    public int getSize() {
        return lista.size();
    }

    public Object getElementAt(int index) {
        return lista.get(index);
    }

    public void addListDataListener(ListDataListener l) {
    }

    public void removeListDataListener(ListDataListener l) {
    }
}
