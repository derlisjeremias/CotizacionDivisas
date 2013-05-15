/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

/**
 *
 * @author Jere
 */
public class CotizacionMoneda extends Moneda {

    private String cotizacion;

    CotizacionMoneda(String unaSigla, String unaDescripcion, String unaCotizacion) {
        super(unaSigla, unaDescripcion);
        this.cotizacion = unaCotizacion;
    }

    public String getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(String cotizacion) {
        this.cotizacion = cotizacion;
    }

    @Override
    public String toString() {
        return super.sigla + " (" + super.descripcion + ") cotiza en u$s " + cotizacion;
    }
}
