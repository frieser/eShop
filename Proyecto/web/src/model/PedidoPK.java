package model;

import java.io.Serializable;

public class PedidoPK implements Serializable {
    private Long numCarrito;
    private String referencia;

    public PedidoPK() {
    }

    public PedidoPK(Long numCarrito, String referencia) {
        this.numCarrito = numCarrito;
        this.referencia = referencia;
    }

    public boolean equals(Object other) {
        if (other instanceof PedidoPK) {
            final PedidoPK otherPedidoPK = (PedidoPK) other;
            final boolean areEqual =
                (otherPedidoPK.numCarrito.equals(numCarrito) && otherPedidoPK.referencia.equals(referencia));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    Long getNumCarrito() {
        return numCarrito;
    }

    void setNumCarrito(Long numCarrito) {
        this.numCarrito = numCarrito;
    }

    String getReferencia() {
        return referencia;
    }

    void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
