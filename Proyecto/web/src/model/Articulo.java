package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Articulo.findAll", query = "select o from Articulo o"),
  @NamedQuery(name = "Articulo.findByReferencia", query = "select o from Articulo o WHERE o.referencia=:referencia")
})
public class Articulo implements Serializable {
    @Column(length = 30)
    private String descripcion;
    private Long precio;
    @Id
    @Column(nullable = false, length = 5)
    private String referencia;
    private Long unidades;

    public Articulo() {
    }

    public Articulo(String descripcion, Long precio, String referencia,
                    Long unidades) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.referencia = referencia;
        this.unidades = unidades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Long getUnidades() {
        return unidades;
    }

    public void setUnidades(Long unidades) {
        this.unidades = unidades;
    }
}
