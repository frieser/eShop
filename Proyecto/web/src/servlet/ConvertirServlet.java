package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;

import java.util.ArrayList;
import java.util.Enumeration;

import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Articulo;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

public class ConvertirServlet extends HttpServlet {
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

      List<Articulo> articulos=new ArrayList<Articulo>();
      
      Enumeration e=request.getParameterNames();
      int iteraciones=0;
      int usuarios = 0;
      int aux = 0;
      int i=1;
      boolean enc=false;
              
      URL url = Loader.getResource("log4j.properties");
      PropertyConfigurator.configure(url);
      
      while(e.hasMoreElements() ) {  
          iteraciones++;
          e.nextElement();
      } 
      
      iteraciones=(int)iteraciones/5;
                
      while(!enc)
      {
          if(request.getParameter("referencia-pedido"+i)!=null)  
          {
            Articulo articulo=new Articulo();
            articulo.setReferencia((String)request.getParameter("referencia-pedido"+i));
            articulo.setDescripcion((String)request.getParameter("descripcion-pedido"+i));
            articulo.setUnidades(Long.parseLong(request.getParameter("unidades-pedido"+i)));
            articulo.setPrecio(Long.parseLong((String)request.getParameter("precio-pedido"+i)));
            articulos.add(articulo);
            aux++;
            if(iteraciones==aux)
                enc=true;
          }
          i++;
      }
      
      Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"VOY A LLAMAR A JSP CONFIRMAR");

      request.setAttribute("articulos", articulos);

      RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/jsp/confirmarCarrito.jsp");
      dispatcher.forward(request, response);
    }
}
