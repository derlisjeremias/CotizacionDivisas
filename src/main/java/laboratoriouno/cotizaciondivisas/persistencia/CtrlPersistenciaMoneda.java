/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.persistencia;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import laboratoriouno.cotizaciondivisas.modelo.Moneda;

/**
 *
 * @author Jere
 */
public class CtrlPersistenciaMoneda {

    private EntityManagerFactory emf = null;

    public CtrlPersistenciaMoneda(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    public void crear(Moneda moneda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(moneda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Moneda moneda) throws NoExisteEntidadException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            moneda = em.merge(moneda);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = moneda.getId();
                if (encontrarMoneda(id) == null) {
                    throw new NoExisteEntidadException("No existe la moneda con id " + id);
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destruir(Long id) throws NoExisteEntidadException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moneda moneda;
            try {
                moneda = em.getReference(Moneda.class, id);
                moneda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("No existe la moneda con id " + id, enfe);
            }
            em.remove(moneda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Moneda> encontrarEntidadesMoneda() {
        return encontrarEntidadesMoneda(true, -1, -1);
    }

    public List<Moneda> encontrarEntidadesMoneda(int maxResultados, int primerResultado) {
        return encontrarEntidadesMoneda(false, maxResultados, primerResultado);
    }

    private List<Moneda> encontrarEntidadesMoneda(boolean total, int maxResultados, int primerResultado) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Moneda.class));
            Query q = em.createQuery(cq);
            if (!total) {
                q.setMaxResults(maxResultados);
                q.setFirstResult(primerResultado);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Moneda encontrarMoneda(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Moneda.class, id);
        } finally {
            em.close();
        }
    }

    public int getCantidadMoneda() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Moneda> rt = cq.from(Moneda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
