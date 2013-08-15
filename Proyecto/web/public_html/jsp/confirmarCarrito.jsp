<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html;charset=windows-1252"%>
<%@page import="model.Articulo" %>
<%@page import="model.Usuario" %>
<%@page import="java.util.List" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link rel="stylesheet" href="css/estilo.css">
       <script src="js/confirmar.js"></script>
    <title>confirmarCarrito</title>
  </head>
  <body>
  <%
    java.util.List articulos = (java.util.List) request.getAttribute("articulos");
    //UsuarioBean usuario= (UsuarioBean)session.getAttribute("usuario");
    if(articulos==null){
        out.println("Esta vacio");
        }
  %>
  
    <h1 align="center">Pantalla de confirmacion de pedido</h1>
    <table border="2" align="center" cellpadding="10%">
    <thead> 
      <tr>
        <th bgcolor="Lime">Referencia</th>
        <th bgcolor="Lime">Descripcion</th>
        <th bgcolor="Lime">Unidades</th>
        <th bgcolor="Lime">Precio</th>
        <th bgcolor="Lime">subtotal articulo</th>
        
      </tr>
    </thead> 
    <tbody>
    <%
		      int i=0;
          long precio = 0;
          long precio2 = 0;
					for(i = 0;i<articulos.size();i++){
    %>
      <tr>
      <td>
          <% out.println(((Articulo)articulos.get(i)).getReferencia()); %>
      </td>
      <td>
	       	<% out.println(((Articulo)articulos.get(i)).getDescripcion()); %>
			</td>

			<td>
	        <% out.println(((Articulo)articulos.get(i)).getUnidades()); %>
			</td>

			<td>
		      <% out.println(((Articulo)articulos.get(i)).getPrecio()); %>
      </td>
      
          
      
         
         <td colspan="3"> <%precio = ((Articulo)articulos.get(i)).getPrecio()*((Articulo)articulos.get(i)).getUnidades(); %><%=precio%></td>
         <%precio2 += ((Articulo)articulos.get(i)).getPrecio()*((Articulo)articulos.get(i)).getUnidades(); %>
      </tr>
      
          <%
            
          	}
            session.setAttribute("articulos", articulos);
            //session.setAttribute("usuario", usuario);
		  	  %>
        <tr>
          <td></td>
         <td></td>
           <td></td>
        <td><b>Total: </b></td>
      
              <td > <b><%=precio2%></b></td>
        </tr>
          
    </tbody>
    </table>
    <br/>
    <br/>
    <div align="center">
      <form id="botones" method="POST" action='confirmaservlet'>
        
        <input type="submit"  align="left" id="confirmar" value="Confirmar pedido" />
        <input type="reset" align="right" id="cancelar" value="Cancelar pedido""/> 
      </form>
    </div>
  </body>
</html>