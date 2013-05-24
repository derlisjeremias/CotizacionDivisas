/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jere
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String claveAcceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Moneda> misMonedas = new ArrayList();

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public boolean setClaveAcceso(String claveAcceso) {
        if (this.claveAcceso == null) {
            this.claveAcceso = claveAcceso;
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

    public void modificarClaveAcceso(String nuevaClaveAcceso) {

        this.claveAcceso = nuevaClaveAcceso;
    }

    public void agregarMoneda(Moneda unaMoneda) {
        this.misMonedas.add(unaMoneda);
        unaMoneda.setUsuario(this);
    }

    public void eliminarMoneda(Moneda unaMoneda) {
        this.misMonedas.remove(unaMoneda);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Usuario: " + nombre + " Cant Monedas:  " + misMonedas.size();
    }
}
