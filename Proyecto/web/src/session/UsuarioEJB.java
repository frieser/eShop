package session;

import java.util.List;

import javax.ejb.Remote;

import model.Usuario;

@Remote
public interface UsuarioEJB {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Usuario persistUsuario(Usuario usuario);

    Usuario mergeUsuario(Usuario usuario);

    void removeUsuario(Usuario usuario);

    List<Usuario> getUsuarioFindAll();
    
    Usuario getUsuarioFindByLogin(String login);
}
