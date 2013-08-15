package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Pedido.findAll", query = "select o from Pedido o")
})
@IdClass(PedidoPK.class)
public class Pedido implements Serializable {
    @Id
    @Column(name="NUM_CARRITO", nullable = false)
    private Long numCarrito;
    @Column(name="NUM_UNIDADES")
    private Long numUnidades;
    @Id
    @Column(nullable = false, length = 5)
    private String referencia;

    public Pedido() {
    }

    public Pedido(Long numCarrito, Long numUnidades, String referencia) {
        this.numCarrito = numCarrito;
        this.numUnidades = numUnidades;
        this.referencia = referencia;
    }

    public Long getNumCarrito() {
        return numCarrito;
    }

    public void setNumCarrito(Long numCarrito) {
        this.numCarrito = numCarrito;
    }

    public Long getNumUnidades() {
        return numUnidades;
    }

    public void setNumUnidades(Long numUnidades) {
        this.numUnidades = numUnidades;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
