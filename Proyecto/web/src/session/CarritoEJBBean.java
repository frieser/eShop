package session;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Carrito;

@Stateless(name = "CarritoEJB", mappedName = "Proyecto-web-CarritoEJB")
@Remote
public class CarritoEJBBean implements CarritoEJB {
    @PersistenceContext(unitName="web")
    private EntityManager em;

    public CarritoEJBBean() {
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

    public Carrito persistCarrito(Carrito carrito) {
        em.persist(carrito);
        return carrito;
    }

    public Carrito mergeCarrito(Carrito carrito) {
        return em.merge(carrito);
    }

    public void removeCarrito(Carrito carrito) {
        carrito = em.find(Carrito.class, carrito.getNumCarrito());
        em.remove(carrito);
    }

    /** <code>select o from Carrito o</code> */
    public List<Carrito> getCarritoFindAll() {
        return em.createNamedQuery("Carrito.findAll").getResultList();
    }
}
