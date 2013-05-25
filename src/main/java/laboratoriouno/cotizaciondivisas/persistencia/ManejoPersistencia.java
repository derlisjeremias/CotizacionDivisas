/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import laboratoriouno.cotizaciondivisas.modelo.Moneda;
import laboratoriouno.cotizaciondivisas.modelo.Usuario;

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

    public void verificarDatos() {
        EntityManager em = this.ctrlPersUsuario.getEntityManager();

        String see = "select u from Usuario as u";
        TypedQuery<Usuario> query = em.createQuery(see, Usuario.class);

        List<Usuario> lista = query.getResultList();
        for (Usuario u : lista) {
            System.out.println(u.toString());
            for (Moneda m : u.getMisMonedas()) {
                System.out.println("        id "+m.getId() + m.toString());
            }
        }

    }
}
