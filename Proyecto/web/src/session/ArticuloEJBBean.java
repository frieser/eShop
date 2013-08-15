package session;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Articulo;

@Stateless(name = "ArticuloEJB", mappedName = "Proyecto-web-ArticuloEJB")
@Remote
public class ArticuloEJBBean implements ArticuloEJB {
    @PersistenceContext(unitName="web")
    private EntityManager em;

    public ArticuloEJBBean() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public Articulo persistArticulo(Articulo articulo) {
        em.persist(articulo);
        return articulo;
    }

    public Articulo mergeArticulo(Articulo articulo) {
        return em.merge(articulo);
    }

    public void removeArticulo(Articulo articulo) {
        articulo = em.find(Articulo.class, articulo.getReferencia());
        em.remove(articulo);
    }

    /** <code>select o from Articulo o</code> */
    public List<Articulo> getArticuloFindAll() {
        return em.createNamedQuery("Articulo.findAll").getResultList();
    }
    
    public Articulo getArticuloFindByReferencia(String referencia) {
      return (Articulo)em.createNamedQuery("Articulo.findByReferencia").setParameter("referencia", referencia).getSingleResult();
    }
}
