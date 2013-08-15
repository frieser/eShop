
window.onload=function() {
	botones=document.getElementsByClassName("botones");

    
	document.getElementById("reset").onclick=function(){
    	document.getElementById("cuerpo-tabla").innerHTML="";
      document.getElementById("carrito").style.visibility="hidden";
      for (var i =0;  i<botones.length;i++) {
       botones[i].style.visibility="visible";
      }
      
  };

	for (var i =0;  i<botones.length;i++) {
		botones[i].onclick=function(){
				this.style.visibility="hidden";
				document.getElementById("carrito").style.visibility="visible";
				obj=document.getElementById("carrito");
				obj	.setAttribute("class", "");
				referencia=document.getElementById("referencia-"+this.name);
				descripcion=document.getElementById("descripcion-"+this.name);
				unidades=document.getElementById("cantidad-"+this.name);
				precio=document.getElementById("precio-"+this.name);
				total=parseInt(unidades.value)*parseInt(precio.innerHTML);

				sitio=document.getElementById("cuerpo-tabla");

				html1="<td><input type='text' readonly='readonly' name='referencia-pedido"+this.name+"' value='"+referencia.innerHTML+"' ></td>";
				html2="<td><input type='text' readonly='readonly' name='descripcion-pedido"+this.name+"' value='"+descripcion.innerHTML+"' ></td>";


				html3="<td><input type='text'  readonly='readonly' name='unidades-pedido"+this.name+"' value='"+unidades.value+"' ></td>";
				html4="<td><input type='text' readonly='readonly' name='precio-pedido"+this.name+"' value='"+ precio.innerHTML+"'></td>";
				html5="<td><input type='text'  readonly='readonly'  name='total-pedido"+this.name+"' value='"+total+"' ></td>";			


				sitio.innerHTML=sitio.innerHTML+"<tr>"+html1+html2+html3+html4+html5+"</tr>";
    
		};
	};
  
 };
