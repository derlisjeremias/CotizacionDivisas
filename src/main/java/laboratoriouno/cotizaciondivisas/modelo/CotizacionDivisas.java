/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jere
 */
public class CotizacionDivisas {

    private List<Moneda> monedas = new ArrayList();
    private List<CotizacionMoneda> cotizacionMonedas = new ArrayList();
    private List<Usuario> usuarios = new ArrayList();
    private Usuario usuarioActivo = null;
    private CapturaRemotaDivisas informacionDivisas = new CapturaRemotaDivisasGoogleFinance();

    CotizacionDivisas() {
        this.monedas = this.informacionDivisas.obtenerDatosMonedas();
        this.cotizacionMonedas = this.informacionDivisas.obtenerCotizacionesMonedas();
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public List<CotizacionMoneda> getCotizacionMonedas() {
        return cotizacionMonedas;
    }

    public void agregarUsuario(Usuario u) {
        if (!this.usuarios.contains(u)) {
            this.usuarios.add(u);
        }
    }

    public boolean sesionIniciada() {
        return (this.usuarioActivo != null);
    }

    public boolean iniciarSesion(Usuario u) {
        boolean existeUsuario = existeUsuarioConMombre(u.getNombre());
        if (existeUsuario && !sesionIniciada()) {
            this.usuarioActivo = u;
            return true;
        }
        return false;
    }

    public void cerrarSesion(Usuario u) {
        this.usuarioActivo = null;
    }

    public void eliminarUsuario(Usuario u) {
        if (this.usuarios.contains(u)) {
            this.usuarios.remove(u);
        }
    }

    public Usuario obtenerUsuario(String nombre) {
        Usuario uObjetivo = null;
        Iterator itrUsuarios = this.usuarios.iterator();
        boolean encontrado = false;
        while (itrUsuarios.hasNext() && !encontrado) {
            Usuario u = (Usuario) itrUsuarios.next();
            if (u.getNombre().equals(nombre)) {
                uObjetivo = u;
                encontrado = true;
            }
        }
        if (encontrado) {
            return uObjetivo;
        } else {
            return new Usuario("Usuario no hallado");
        }
    }

    public boolean existeUsuarioConMombre(String nombre) {
        Iterator itrUsuarios = this.usuarios.iterator();
        boolean encontrado = false;
        while (itrUsuarios.hasNext() && !encontrado) {
            Usuario u = (Usuario) itrUsuarios.next();
            if (u.getNombre().equals(nombre)) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public void agregarMonedaDeUsuario(Moneda m) {
        if (sesionIniciada()) {
            this.usuarioActivo.agregarMoneda(m);
        }
    }

    public void eliminarMonedaDeUsuario(Moneda m) {
        if (sesionIniciada()) {
            this.usuarioActivo.eliminarMoneda(m);
        }
    }

    public void actualizarCotizaciones() {
        this.cotizacionMonedas = this.informacionDivisas.obtenerCotizacionesMonedas();
    }
}
