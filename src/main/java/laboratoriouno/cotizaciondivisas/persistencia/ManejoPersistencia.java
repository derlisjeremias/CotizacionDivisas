/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jere
 */
public class ManejoPersistencia {

    private EntityManagerFactory emf = null;
    private CtrlPersistenciaUsuario ctrlPersUsuario = null;
    private CtrlPersistenciaMoneda ctrlPersMoneda = null;

    public ManejoPersistencia() {
        this.emf = Persistence.createEntityManagerFactory("CotizacionDivisasUP");
        this.ctrlPersUsuario = new CtrlPersistenciaUsuario(emf);
        this.ctrlPersMoneda = new CtrlPersistenciaMoneda(emf);
    }

    public CtrlPersistenciaUsuario getCtrlPersUsuario() {
        return ctrlPersUsuario;
    }

    public CtrlPersistenciaMoneda getCtrlPersMoneda() {
        return ctrlPersMoneda;
    }
}
