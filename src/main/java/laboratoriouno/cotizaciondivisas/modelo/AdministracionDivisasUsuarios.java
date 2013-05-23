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
public class AdministracionDivisasUsuarios {

    private List<Moneda> monedas = new ArrayList();
    private List<MonedaCotizacion> cotizacionMonedas = new ArrayList();
    private List<Usuario> usuarios = new ArrayList();
    private Usuario usuarioActivo = null;
    private CapturaRemotaDivisas informacionDivisas = new CapturaRemotaDivisasOpenExchange();

    public AdministracionDivisasUsuarios() {
        this.monedas = this.informacionDivisas.obtenerDatosMonedas();
        this.cotizacionMonedas = this.informacionDivisas.obtenerCotizacionesMonedas();
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public List<MonedaCotizacion> getCotizacionMonedas() {
        return cotizacionMonedas;
    }

    public Moneda obtenerUnaMoneda(String sigla) {
        Moneda objetivo = null;
        boolean encontrada = false;
        int i = 0;
        while (i < this.monedas.size() || !encontrada) {
            if (this.monedas.get(i).getSiglas().equals(sigla)) {
                objetivo = this.monedas.get(i);
                encontrada = true;
            }
            i++;
        }
        return objetivo;
    }

    public MonedaCotizacion obtenerUnaCotizacion(String sigla) {
        Moneda m = this.obtenerUnaMoneda(sigla);
        MonedaCotizacion mc = this.obtenerUnaCotizacion(m);
        return mc;
    }

    public MonedaCotizacion obtenerUnaCotizacion(Moneda m) {
        MonedaCotizacion objetivo = null;
        boolean encontrada = false;
        int i = 0;
        while (i < this.cotizacionMonedas.size() || !encontrada) {
            if (this.cotizacionMonedas.get(i).getSiglas().equals(m.getSiglas())) {
                objetivo = this.cotizacionMonedas.get(i);
                encontrada = true;
            }
            i++;
        }
        return objetivo;
    }

    public void agregarUsuario(Usuario u) {
        boolean existeNombreUsuario = false;
        int i = 0;
        while (!existeNombreUsuario && i < this.usuarios.size()) {
            if (this.usuarios.get(i).getNombre().equals(u.getNombre())) {
                existeNombreUsuario = true;
            }
            i++;
        }


        if (!existeNombreUsuario && !this.usuarios.contains(u)) {
            this.usuarios.add(u);
        }
    }

    public boolean sesionIniciada() {
        return (this.usuarioActivo != null);
    }

    public boolean iniciarSesion(Usuario u, char[] clave) {
        boolean existeUsuario = existeUsuarioConMombre(u.getNombre());
        if (existeUsuario && !sesionIniciada() && u.validarClave(clave)) {
            this.usuarioActivo = u;
            return true;
        }
        return false;
    }

    public void cerrarSesion() {
        this.usuarioActivo = null;
    }

    public void eliminarUsuario(Usuario u) {
        if (this.usuarios.contains(u)) {
            this.usuarios.remove(u);
        }
    }

    public List<Usuario> getUsuarios() {
        return this.usuarios;
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
            return new Usuario("");
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
