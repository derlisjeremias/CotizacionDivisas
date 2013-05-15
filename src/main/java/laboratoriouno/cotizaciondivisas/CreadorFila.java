/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas;

import java.util.List;

/**
 *
 * @author Jere
 */
public class CreadorFila {

    String siglas;
    ServidorJSon sj = new ServidorJSon();
    private static List<CotizacionMoneda> lista = null;

    public CreadorFila(String siglas) {
        if (lista == null) {
            lista = sj.getArrayMonedas();
        }
        this.siglas = siglas;
    }

    public void actualizarListaMonedas() {
        lista = sj.getArrayMonedas();
    }

    public CotizacionMoneda crear() {
        CotizacionMoneda moneda = null;
        int i = 0;
        boolean flag = false;
        while (!flag && i <= lista.size()) {
            CotizacionMoneda aux = (CotizacionMoneda) (lista.get(i));
            if (aux.getSiglas().equals(this.siglas)) {
                flag = true;
                moneda = new CotizacionMoneda(this.siglas, aux.getCambio());
                System.out.println("aux " + aux);
                System.out.println("indice " + this.siglas);
            }
            i++;
        }
        if (flag) {
            return moneda;
        } else {
            return new CotizacionMoneda("error", "error");
        }

    }
}
