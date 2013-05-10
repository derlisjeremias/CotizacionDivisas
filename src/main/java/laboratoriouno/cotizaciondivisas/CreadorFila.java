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
    List<Moneda> lista;

    public CreadorFila(String siglas) {
        ServidorJSon sj = new ServidorJSon();
        List<Moneda> l = sj.getArrayMonedas();
        this.lista = l;
        this.siglas = siglas;
    }

    public Moneda crear() {
        Moneda moneda = null;
        int i = 0;
        boolean flag = false;
        while (!flag && i <= lista.size()) {
            Moneda aux = (Moneda) (lista.get(i));
            if (aux.getSiglas().equals(this.siglas)) {
                flag = true;
                moneda = new Moneda(this.siglas, aux.getCambio());
                System.out.println("aux " + aux);
                System.out.println("indice " + this.siglas);
            }
            i++;
        }
        if (flag) {
            return moneda;
        } else {
            return new Moneda("error", "error");
        }

    }
}
