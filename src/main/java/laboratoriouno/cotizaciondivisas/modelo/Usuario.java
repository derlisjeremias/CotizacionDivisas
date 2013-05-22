/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jere
 */
public class Usuario {

    private String nombre;
    private String claveAcceso;
    private List<Moneda> misMonedas = new ArrayList();

    public Usuario(String unNombre) {
        this.nombre = unNombre;
        this.claveAcceso = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean setClaveAcceso(String claveAcceso) {
        if (this.claveAcceso == null) {
            this.claveAcceso = claveAcceso;
            return true;
        }
        return false;
    }

    public boolean tieneNombre() {
        return !this.nombre.isEmpty();
    }

    public boolean validarClave(char[] claveEntrante) {
        char[] clave;
        clave = this.claveAcceso.trim().toCharArray();

        if (Arrays.equals(claveEntrante, clave)) {
            return true;
        }

        Arrays.fill(claveEntrante, ' ');
        return false;
    }

    public boolean modificarClaveAcceso(String claveAcceso, String nuevaClaveAcceso) {
        if (this.claveAcceso.equals(claveAcceso)) {
            this.claveAcceso = nuevaClaveAcceso;
            return true;
        }
        return false;
    }

    public List<Moneda> getMisMonedas() {
        return misMonedas;
    }

    public void setMisMonedas(List<Moneda> misMonedas) {
        this.misMonedas = misMonedas;
    }

    public void agregarMoneda(Moneda unaMoneda) {
        this.misMonedas.add(unaMoneda);
    }

    public void eliminarMoneda(Moneda unaMoneda) {
        this.misMonedas.remove(unaMoneda);
    }

    @Override
    public String toString() {
        return "El usuario " + nombre + " tiene " + misMonedas.size() + " monedas.";
    }
}
