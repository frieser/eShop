package session;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Usuario;

@Stateless(name = "UsuarioEJB", mappedName = "Proyecto-web-UsuarioEJB")
@Remote
public class UsuarioEJBBean implements UsuarioEJB {
    @PersistenceContext(unitName="web")
    private EntityManager em;

    public UsuarioEJBBean() {
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

    public Usuario persistUsuario(Usuario usuario) {
        em.persist(usuario);
        return usuario;
    }

    public Usuario mergeUsuario(Usuario usuario) {
        return em.merge(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        usuario = em.find(Usuario.class, usuario.getCodUsuario());
        em.remove(usuario);
    }

    /** <code>select o from Usuario o</code> */
    public List<Usuario> getUsuarioFindAll() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }
    
  public Usuario getUsuarioFindByLogin(String login) {
      return (Usuario)em.createNamedQuery("Usuario.findByLogin").setParameter("login", login).getSingleResult();
  }
}
