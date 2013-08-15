package business;

import java.util.List;

import javax.naming.Context;

import javax.naming.InitialContext;

import model.Usuario;

import session.UsuarioEJB;

public class Autentificacion {
    public Autentificacion() {
        super();
    }
    
      public long comprobar(String nombre, String clave){
        Usuario usuario = null;
        String nclave = null;
        List <Usuario> usuarios;
        long id;
        
        try {
            Context ctx = new InitialContext();
            UsuarioEJB local = (UsuarioEJB)ctx.lookup("Proyecto-web-UsuarioEJB#session.UsuarioEJB");
            usuario=local.getUsuarioFindByLogin(nombre);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }        
        
        if (usuario!=null) {            
          System.out.println("USUSUARIOOOOO");
          System.out.println(usuario.getCodUsuario());
          System.out.println(usuario.getLogin());
          System.out.println(usuario.getPass());
          if(clave.equals(usuario.getPass())){
            id=usuario.getCodUsuario();
          }else {
            id=-1;
          }
        }else{
          id=-1;
        }
           
        return id;
      }
}
