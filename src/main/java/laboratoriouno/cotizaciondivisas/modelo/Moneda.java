/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

/**
 *
 * @author Jere
 */
public class Moneda {

    private String sigla;
    private String descripcion;

    public Moneda(String unaSigla, String unaDescripcion) {
        this.sigla = unaSigla;
        this.descripcion = unaDescripcion;
    }

    public String getSiglas() {
        return sigla;
    }

    public void setSiglas(String siglas) {
        this.sigla = siglas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return sigla + " / " + descripcion;
    }
}
