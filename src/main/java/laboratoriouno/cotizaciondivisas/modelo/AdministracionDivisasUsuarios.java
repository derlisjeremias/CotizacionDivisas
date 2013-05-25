/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import laboratoriouno.cotizaciondivisas.persistencia.CtrlPersistenciaMoneda;
import laboratoriouno.cotizaciondivisas.persistencia.CtrlPersistenciaUsuario;
import laboratoriouno.cotizaciondivisas.persistencia.ManejoPersistencia;
import laboratoriouno.cotizaciondivisas.persistencia.NoExisteEntidadException;

/**
 *
 * @author Jere
 */
public class AdministracionDivisasUsuarios {

    private ManejoPersistencia persistencia;
    private List<Moneda> monedas;
    private List<MonedaCotizacion> cotizacionMonedas;
    private List<Usuario> usuarios;
    private Usuario usuarioActivo = null;
    private CapturaRemotaDivisas informacionDivisas = new CapturaRemotaDivisasOpenExchange();

    public AdministracionDivisasUsuarios() {
        this.monedas = this.informacionDivisas.obtenerDatosMonedas();
        this.cotizacionMonedas = this.informacionDivisas.obtenerCotizacionesMonedas();
        this.persistencia = new ManejoPersistencia();
        this.cargarUsuarios();

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

    private void cargarUsuarios() {
        CtrlPersistenciaUsuario cpu = this.persistencia.getCtrlPersUsuario();
        this.usuarios = cpu.encontrarEntidadesUsuario();
    }

    private void persistirUsuario(Usuario u) {
        CtrlPersistenciaUsuario cpu = this.persistencia.getCtrlPersUsuario();
        cpu.crear(u);
    }

    private void destruirUsuario(Usuario u) {
        try {
            CtrlPersistenciaUsuario cpu = this.persistencia.getCtrlPersUsuario();
            cpu.destruir(u.getId());
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(AdministracionDivisasUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void destruirMonedaDeUsuario(Moneda m) {

        if (m.getUsuario().getId() == this.usuarioActivo.getId()) {
            try {
                CtrlPersistenciaMoneda cpm = this.persistencia.getCtrlPersMoneda();
                cpm.destruir(m.getId());
            } catch (NoExisteEntidadException ex) {
                Logger.getLogger(AdministracionDivisasUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            this.persistirUsuario(u);
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

    public void modificarNombreUsuario(Usuario u, String n) {
        try {
            CtrlPersistenciaUsuario cpu = this.persistencia.getCtrlPersUsuario();
            u.setNombre(n);
            cpu.editar(u);
        } catch (Exception ex) {
            Logger.getLogger(AdministracionDivisasUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarClaveUsuario(Usuario u, String c) {
        try {
            CtrlPersistenciaUsuario cpu = this.persistencia.getCtrlPersUsuario();
            u.modificarClaveAcceso(c);
            cpu.editar(u);
        } catch (Exception ex) {
            Logger.getLogger(AdministracionDivisasUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void eliminarUsuario(Usuario u) {
        if (this.usuarios.contains(u)) {
            this.usuarios.remove(u);
            this.destruirUsuario(u);
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
        if (this.sesionIniciada()) {
            try {
                Moneda nuevaMoneda = new Moneda();
                nuevaMoneda.setSiglas(m.getSiglas());
                nuevaMoneda.setDescripcion(m.getDescripcion());
                nuevaMoneda.setUsuario(this.usuarioActivo);
                this.usuarioActivo.agregarMoneda(nuevaMoneda);

                CtrlPersistenciaMoneda cpm = this.persistencia.getCtrlPersMoneda();
                cpm.crear(nuevaMoneda);
            } catch (Exception ex) {
                Logger.getLogger(AdministracionDivisasUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void eliminarMonedaDeUsuario(String siglas) {
        if (sesionIniciada()) {
            int largo = this.usuarioActivo.getMisMonedas().size() - 1;
            for (int i = largo; i >= 0; i--) {
                Moneda m = this.usuarioActivo.getMisMonedas().get(i);
                if (siglas.equals(m.getSiglas())) {
                    this.eliminarMonedaDeUsuario(m);
                    break;
                }
            }

        }
    }

    public void eliminarMonedaDeUsuario(Moneda m) {
        if (sesionIniciada()) {
            this.usuarioActivo.eliminarMoneda(m);
            this.destruirMonedaDeUsuario(m);
        }
    }

    public void actualizarCotizaciones() {
        this.cotizacionMonedas = this.informacionDivisas.obtenerCotizacionesMonedas();
    }
}
