package business;

import java.net.URL;

import java.util.List;

import javax.naming.Context;

import javax.naming.InitialContext;

import model.Usuario;

import session.UsuarioEJB;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import servlet.CompraServlet;

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
            URL url = Loader.getResource("log4j.properties");
            PropertyConfigurator.configure(url);
            Context ctx = new InitialContext();
            UsuarioEJB local = (UsuarioEJB)ctx.lookup("Proyecto-web-UsuarioEJB#session.UsuarioEJB");
            usuario=local.getUsuarioFindByLogin(nombre);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }        
        
        if (usuario!=null) {            
          Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"Leemos usuario de autenticacion");
          System.out.println(usuario.getCodUsuario());
          System.out.println(usuario.getLogin());
          System.out.println(usuario.getPass());
          if(clave.equals(usuario.getPass())){
            id=usuario.getCodUsuario();
            Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"Coincide usuario y pass");
          }else {
            id=-1;
          }
        }else{
          id=-1;
        }
           
        return id;
      }
}
