package session;

import java.util.List;

import javax.ejb.Remote;

import model.Articulo;

@Remote
public interface ArticuloEJB {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Articulo persistArticulo(Articulo articulo);

    Articulo mergeArticulo(Articulo articulo);

    void removeArticulo(Articulo articulo);

    List<Articulo> getArticuloFindAll();
}
