package model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Carrito.findAll", query = "select o from Carrito o")
})
public class Carrito implements Serializable {
    @Column(name="COD_USUARIO")
    private Long codUsuario;
    private Timestamp fecha;
    @Id
    @Column(name="NUM_CARRITO", nullable = false)
    private Long numCarrito;

    public Carrito() {
    }

    public Carrito(Long codUsuario, Timestamp fecha, Long numCarrito) {
        this.codUsuario = codUsuario;
        this.fecha = fecha;
        this.numCarrito = numCarrito;
    }

    public Long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Long getNumCarrito() {
        return numCarrito;
    }

    public void setNumCarrito(Long numCarrito) {
        this.numCarrito = numCarrito;
    }
}
