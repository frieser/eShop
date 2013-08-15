package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

import session.ArticuloEJB;
import session.PedidoEJB;
import session.UsuarioEJB;

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
          CarritoEJB carritoSession = (CarritoEJB)ctx.lookup("Proyecto-web-CarritoEJB#session.CarritoEJB");
          carritoSession.persistCarrito(carrito);
          //cbdh.insertarCarrito(carrito);
        } catch (NamingException e) {
          e.printStackTrace();
          System.out.println(e.getMessage());
        }
      
      System.out.println("SERVLET CONFIRMAR");
      
      articulos =  (ArrayList<Articulo>) sesion.getAttribute("articulos");
      
      if(articulos==null){
        System.out.println("Esta vacio");
      }
      
      for(i = 0;i<articulos.size();i++){
        System.out.println("ASDASDASDASDASHDASIDKDHASLÑDJKH");
        System.out.println(articulos.get(i).getReferencia());
        System.out.println(articulos.get(i).getDescripcion());
        System.out.println(articulos.get(i).getPrecio());
        System.out.println(articulos.get(i).getUnidades());
            try {
                ctx = new InitialContext();
              PedidoEJB pedidoSession = (PedidoEJB)ctx.lookup("Proyecto-web-PedidoEJBB#session.PedidoEJB");
              ArticuloEJB articuloSession = (ArticuloEJB)ctx.lookup("Proyecto-web-ArticuloEJB#session.ArticuloEJB");
                
                Pedido pedido=new Pedido();
                pedido.setNumCarrito(carrito.getNumCarrito());
                pedido.setReferencia(articulos.get(i).getReferencia());
                pedido.setNumUnidades(articulos.get(i).getUnidades());
                pedidoSession.persistPedido(pedido);
              //pdbh.insertarPedido(numcarrito, (articulos.get(i)).getReferencia(), Integer.parseInt((articulos.get(i)).getUnidades()));
                
                Articulo articulo;
                articulo=articuloSession.getArticuloFindByReferencia(articulos.get(i).getReferencia());
                articulo.setUnidades(articulo.getUnidades()-articulos.get(i).getUnidades());
                articuloSession.mergeArticulo(articulo);
              //adbh.modificarStockArticulo(((Articulo)articulos.get(i)).getReferencia(),Integer.parseInt(((Articulo)articulos.get(i)).getUnidades()));
              
            } catch (NamingException e) {
              e.printStackTrace();
              System.out.println(e.getMessage());
            }
           
       
      }
      request.setAttribute("articulos", "");
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/compraservlet");
      rd.forward(request, response);
    }
}
