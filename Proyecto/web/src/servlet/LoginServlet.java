package servlet;

import business.Autentificacion;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Usuario;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

public class LoginServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
      doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        Autentificacion aut  =new Autentificacion();
        HttpSession sesion = request.getSession(true);      
        
        URL url = Loader.getResource("log4j.properties");
        PropertyConfigurator.configure(url);
        
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        System.out.println("ENTRA");
        String user = request.getParameter("usuario");
        String pass = request.getParameter("pass"); 
        
        Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"Usuario: "+user);
        Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"Pass: "+pass);
        
        long id=aut.comprobar(user,pass);
        if(id!=-1){  
          Usuario usuario=new Usuario();
          usuario.setCodUsuario(id);
          usuario.setLogin(user);
          sesion.setAttribute("usuario", usuario);
          response.setStatus(200);
          Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"USER y PASS correcto");
        }
        else{
          Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"El usuario o pass erroneo");  
          out.println("El usuario o contraseña son incorrectos");
          response.setStatus(201);          
        }
        
        out.close();
    }
}
