/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas;

/**
 *
 * @author Jere
 */
public class DatosMoneda {

    private String siglas;
    private String descripcion;

    public DatosMoneda(String siglas, String descripcion) {
        this.siglas = siglas;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return siglas + " / " + descripcion;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
