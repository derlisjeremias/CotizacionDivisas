/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas;

/**
 *
 * @author Jere
 */
public class Moneda {

    private String siglas;
    private String cambio;

    public Moneda(String siglas, String cambio) {
        this.siglas = siglas;
        this.cambio = cambio;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    @Override
    public String toString() {
        return this.siglas + " / " + this.cambio;
    }
}
