/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.persistencia;

/**
 *
 * @author Jere
 */
public class NoExisteEntidadException extends Exception {

    public NoExisteEntidadException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public NoExisteEntidadException(String mensaje) {
        super(mensaje);
    }
}
