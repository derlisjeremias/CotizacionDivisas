/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.conector;

import java.util.List;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import laboratoriouno.cotizaciondivisas.modelo.*;

/**
 *
 * @author Jere
 */
public class ComboBoxDivisas implements javax.swing.ComboBoxModel {

   private AdministracionDivisasUsuarios modeloApp;
    // private CapturaRemotaDivisas servidorDatos = new CapturaRemotaDivisasGoogleFinance();
    private Moneda monedaSeleccionada;
    private List<Moneda> listaMonedas;

    public ComboBoxDivisas(AdministracionDivisasUsuarios app) {
        this.modeloApp = app;
        this.listaMonedas = this.modeloApp.getMonedas();
        this.monedaSeleccionada = listaMonedas.get(0);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.monedaSeleccionada = (Moneda) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return this.monedaSeleccionada;
    }

    @Override
    public int getSize() {
        return this.listaMonedas.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaMonedas.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }
}
