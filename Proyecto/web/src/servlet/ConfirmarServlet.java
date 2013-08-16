package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;

import java.sql.Timestamp;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Articulo;
import model.Carrito;
import model.Pedido;

import session.CarritoEJB;
import model.Usuario;

import org.apache.log4j.Level;

import session.ArticuloEJB;
import session.PedidoEJB;
import session.UsuarioEJB;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

public class ConfirmarServlet extends HttpServlet {
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
      List<Articulo> articulos;
      int i=0;
      
      Usuario usuario =  (Usuario) sesion.getAttribute("usuario");
      Random rand= new Random();
      long numcarrito= rand.nextInt(10000);
      
      
      Carrito carrito=new Carrito();
      carrito.setNumCarrito(numcarrito);
      carrito.setCodUsuario(usuario.getCodUsuario());
      
      Calendar calendar = Calendar.getInstance();
      java.util.Date now = calendar.getTime();
      carrito.setFecha(new Timestamp(now.getTime()));
      
      
      Context ctx;
        try {
          ctx = new InitialContext();
          URL url = Loader.getResource("log4j.properties");
          PropertyConfigurator.configure(url);
          CarritoEJB carritoSession = (CarritoEJB)ctx.lookup("Proyecto-web-CarritoEJB#session.CarritoEJB");
          carritoSession.persistCarrito(carrito);

        } catch (NamingException e) {
          Logger.getLogger(CompraServlet.class.getName()).log(Level.FATAL,"Operacion solicitada no realizada"+e.getMessage());
          e.printStackTrace();
        }
      
      Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"SERVLET confirmar");
      
      articulos =  (ArrayList<Articulo>) sesion.getAttribute("articulos");
      
      if(articulos==null){
        Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"Esta vacio");
      }
      
      for(i = 0;i<articulos.size();i++){
        System.out.println(articulos.get(i).getReferencia());
        System.out.println(articulos.get(i).getDescripcion());
        System.out.println(articulos.get(i).getPrecio());
        System.out.println(articulos.get(i).getUnidades());
            try {
                ctx = new InitialContext();
              PedidoEJB pedidoSession = (PedidoEJB)ctx.lookup("Proyecto-web-PedidoEJB#session.PedidoEJB");
              ArticuloEJB articuloSession = (ArticuloEJB)ctx.lookup("Proyecto-web-ArticuloEJB#session.ArticuloEJB");
                
                Pedido pedido=new Pedido();
                pedido.setNumCarrito(carrito.getNumCarrito());
                pedido.setReferencia(articulos.get(i).getReferencia());
                pedido.setNumUnidades(articulos.get(i).getUnidades());
                pedidoSession.persistPedido(pedido);
                
                Articulo articulo;
                articulo=articuloSession.getArticuloFindByReferencia(articulos.get(i).getReferencia());
                articulo.setUnidades(articulo.getUnidades()-articulos.get(i).getUnidades());
                articuloSession.mergeArticulo(articulo);
              
            } catch (NamingException e) {
              e.printStackTrace();
              System.out.println(e.getMessage());
            }
           
       
      }
      request.setAttribute("articulos", "");
      Logger.getLogger(CompraServlet.class.getName()).log(Level.INFO,"Accedemos a compraservlet");
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/compraservlet");
      rd.forward(request, response);
    }
}
