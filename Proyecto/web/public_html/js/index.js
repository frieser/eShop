window.onload=function() {   
       var cambioEstado=function(){
            if (xmlhttp.readyState == 4){
                if (xmlhttp.status==200){       
                  window.location='compraservlet';
                }else{
                  alert(xmlhttp.responseText);
                }          
            }
      };
        
      var obtener=function(){        
          var parametros = "usuario="+document.getElementById("usuario").value;
          parametros += "&pass="+document.getElementById("pass").value;
          xmlhttp = new XMLHttpRequest();
          obj = document.getElementById("contenedorCajas");
          xmlhttp.open("POST", "loginservlet"); 
          xmlhttp.onreadystatechange = cambioEstado;
          
          //para poder enviar por post la petición es necesario añadir a la cabecera el siguiente content-type
          xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8")
          
          xmlhttp.send(parametros);
      };


      document.getElementById("hecho").onclick=function(){
              obtener();
      };
    };