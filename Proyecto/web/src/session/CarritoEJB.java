package session;

import java.util.List;

import javax.ejb.Remote;

import model.Carrito;

@Remote
public interface CarritoEJB {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Carrito persistCarrito(Carrito carrito);

    Carrito mergeCarrito(Carrito carrito);

    void removeCarrito(Carrito carrito);

    List<Carrito> getCarritoFindAll();
}
