/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jere
 */
public class ActualizadorInformacionMonedas {

    public List<CotizacionMoneda> recuperarDatosTabla(javax.swing.JTable tabla) {
        int i = 0;
        CotizacionMoneda cm;
        List<CotizacionMoneda> lista = new ArrayList<CotizacionMoneda>();
        while (i < tabla.getRowCount()) {
            String siglas = (String) tabla.getValueAt(i, 0);
            String cambio = (String) tabla.getValueAt(i, 1);
            cm = new CotizacionMoneda(siglas, cambio);
            lista.add(cm);
        }
    return lista;
    }
}
