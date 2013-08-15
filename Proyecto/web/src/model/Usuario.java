package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Usuario.findAll", query = "select o from Usuario o"),
  @NamedQuery(name = "Usuario.findByLogin", query = "select o from Usuario o WHERE o.login=:login")
})
public class Usuario implements Serializable {
    @Id
    @Column(name="COD_USUARIO", nullable = false)
    private Long codUsuario;
    @Column(length = 8)
    private String login;
    @Column(length = 8)
    private String pass;

    public Usuario() {
    }

    public Usuario(Long codUsuario, String login, String pass) {
        this.codUsuario = codUsuario;
        this.login = login;
        this.pass = pass;
    }

    public Long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
