<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="model.Articulo" %>
<%@page import="java.util.List" %>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link rel="stylesheet" href="css/estilo.css">
    <script src="js/compra.js"></script>
    
    <title>compra</title>
  </head>
  <body>
  	<%
  		java.util.List articulos = (java.util.List) request.getAttribute("articulos");
      if(articulos==null){
        out.println("No hay articulos en el catalogo");
      }
  	%>
    
  	<table>
  		<caption></caption>
  		<thead>
  			<tr>
	  			<th>
	  				Referencia
		  		</th>
		  		<th>
		  			Descripcion
		  		</th>
		  		<th>
		  			Unidades
		  		</th>
		  		<th>
		  			Precio
		  		</th>
		  		<th>
		  			Cantidad a reservar
		  		</th>

		  	</tr>
  		</thead>
  		<tbody>
  			
  			<%
		      		int i=0;
					for(i = 0;i<articulos.size();i++){
		  	%>
			<tr id="fila-<%=i+1%>">
	  		<td >
					<p id="referencia-<%=i+1%>"><% out.println(((Articulo)articulos.get(i)).getReferencia()); %></p>
			</td>

			<td >
	       			<p id="descripcion-<%=i+1%>"><% out.println(((Articulo)articulos.get(i)).getDescripcion()); %></p>
			</td>

			<td >
	        		<p id="unidades-<%=i+1%>"><% out.println(((Articulo)articulos.get(i)).getUnidades()); %></p>
			</td>

			<td>
		        	<p id="precio-<%=i+1%>"><% out.println(((Articulo)articulos.get(i)).getPrecio()); %></p>
		  	</td>
		  	<td  >
		  		<input type="number" class="cantidad" id="cantidad-<%=i+1%>" name="number-<%=i+1%>" value="1" placeholder="" max="<%=((Articulo)articulos.get(i)).getUnidades() %>">
		  	</td>
		  	<td>
		  			
		  		<input type="button" class="botones" id="boton-<%=i+1%>" name="<%=i+1%>" value="Anadir al carrito">
		  	</td>
			</tr>
		  	<%
			}
		  	%>

  		</tbody>
  	</table>
  		
  	<div id="carrito" class="hidden">
  		<form action="convertirservlet" method="post" accept-charset="utf-8">
  			
  		
  			<table>
  					<caption>CARRITO</caption>
		<thead>
		  			<tr>
			  			<th>
			  				Referencia
				  		</th>
				  		<th>
				  			Descripcion
				  		</th>
				  		<th>
				  			Unidades
				  		</th>
				  		<th>
				  			Precio Articulo
				  		</th>
				  		<th>
				  			Total
				  		</th>
				  	</tr>
		  		</thead>
		  		<tbody id="cuerpo-tabla">
		  		
	

					<tr>
			  		
					</tr>
  				</table>	
				<div id="controles">
					<input id="reset" type="reset" name="limpiar" value="Limpiar Carrito">
  				<input type="submit" id="hecho" name="hecho" value="Pagar">

  				<div id="error"></div>

        </div>
  				</form>
				
  				
  	</div>	
    	
  	
  </body>
</html>