package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Articulo;

import session.ArticuloEJB;
import session.UsuarioEJB;

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
      } catch (NamingException e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
      }  
      request.setAttribute("articulos", articulos);
          
      RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/jsp/compra.jsp");
      dispatcher.forward(request, response);
    }
}
