package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Articulo;

import org.apache.log4j.Level;

import session.ArticuloEJB;
import session.UsuarioEJB;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;


public class CompraServlet extends HttpServlet {
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
      HttpSession sesion = request.getSession();
      List<Articulo> articulos=null;      
      Context ctx;
      
      try {
        ctx = new InitialContext();
        ArticuloEJB local = (ArticuloEJB)ctx.lookup("Proyecto-web-ArticuloEJB#session.ArticuloEJB");
        articulos=local.getArticuloFindAll();
        URL url = Loader.getResource("log4j.properties");
        PropertyConfigurator.configure(url);
        Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"Accedemos a la base de datos de articulos");
      } catch (NamingException e) {  
         Logger.getLogger(CompraServlet.class.getName()).log(Level.FATAL,"Operacion solicitada no realizada"+e.getMessage());
        e.printStackTrace();
      }  
      request.setAttribute("articulos", articulos);
          
      RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/jsp/compra.jsp");
      Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"Entramos en compra.jsp");
      dispatcher.forward(request, response);
    }
}
