package session;

import java.util.List;

import javax.ejb.Remote;

import model.Pedido;

@Remote
public interface PedidoEJB {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Pedido persistPedido(Pedido pedido);

    Pedido mergePedido(Pedido pedido);

    void removePedido(Pedido pedido);

    List<Pedido> getPedidoFindAll();
}
