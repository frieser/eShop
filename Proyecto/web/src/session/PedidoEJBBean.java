package session;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Pedido;
import model.PedidoPK;

@Stateless(name = "PedidoEJB", mappedName = "Proyecto-web-PedidoEJB")
@Remote
public class PedidoEJBBean implements PedidoEJB {
    @PersistenceContext(unitName="web")
    private EntityManager em;

    public PedidoEJBBean() {
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

    public Pedido persistPedido(Pedido pedido) {
        em.persist(pedido);
        return pedido;
    }

    public Pedido mergePedido(Pedido pedido) {
        return em.merge(pedido);
    }

    public void removePedido(Pedido pedido) {
        pedido = em.find(Pedido.class, new PedidoPK(pedido.getNumCarrito(), pedido.getReferencia()));
        em.remove(pedido);
    }

    /** <code>select o from Pedido o</code> */
    public List<Pedido> getPedidoFindAll() {
        return em.createNamedQuery("Pedido.findAll").getResultList();
    }
}
