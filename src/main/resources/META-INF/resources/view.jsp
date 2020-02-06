<%@ include file="init.jsp" %>

<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/css/banner.css"%>'>
<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/css/general.css"%>'>
<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/css/form.css"%>'>
<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/css/modal.css"%>'>


 <portlet:resourceURL id="getRutas" var="getRutasURL" ></portlet:resourceURL>
<portlet:resourceURL id="getCapacidadDisponibilidad" var="getCapacidadDisponibilidadURL" ></portlet:resourceURL>
<portlet:resourceURL id="getParadas" var="getParadasURL" ></portlet:resourceURL>
<portlet:resourceURL id="getParadasSolicitud" var="getParadasSolicitudURL" ></portlet:resourceURL>

     <portlet:actionURL name="procesaSolicitud" var="procesaSolicitudURL" /> 

 
<body class="container">
 	<section class="row justify-content-center">
 		<article class="col-10 col-sm-10 col-md-10 col-lg-10 col-xl-10">
 			<%@ include file="jsp/body/rutas.jsp" %> 
 		</article>
 	</section>
</body>

	<script>
			  	
				function getCapacidadDisponibilidad(rutaId){
					
					var getCapacidadDisponibilidadURL = "${getCapacidadDisponibilidadURL}";
					$.ajax({
							type        : "POST",
							url         : getCapacidadDisponibilidadURL,
							cache       : false,
							async       : false,
							dataType    : "json",	
							data        : {
								"<portlet:namespace />rutaId" : rutaId
							}
						})
						.done(function(response) {
							 document.getElementById('capacidad').value =  response.capacidad +" personas";
							if(response.disponibilidad == 0)
								document.getElementById('disponibilidadMapa').value = "Sin disponibilidad";
							else if (response.disponibilidad == 1)
								document.getElementById('disponibilidadMapa').value = response.disponibilidad + " persona";
							else
								document.getElementById('disponibilidadMapa').value = response.disponibilidad + " personas";
							saludo(response);
						})
						.fail(function(jqXHR, textStatus, errorThrown) {
							 console.log("Request: " + JSON.stringify(jqXHR));
							 console.log("Request: " + textStatus.toUpperCase());
							console.log("Request: " + jqXHR.statusText);
						
						});

					
					var getParadasURL = "${getParadasURL}";
					$.ajax({
							type        : "POST",
							url         : getParadasURL,
							cache       : false,
							async       : false,
							dataType    : "json",	
							data        : {
								"<portlet:namespace />rutaId" : rutaId
							}
						})
						.done(function(response) {
						//	console.log(response);
							createElements(response);
								
						})
						.fail(function(jqXHR, textStatus, errorThrown) {
							 console.log("Request: " + JSON.stringify(jqXHR));
							 console.log("Request: " + textStatus.toUpperCase());
							console.log("Request: " + jqXHR.statusText);
						
						});

				}

				function createElements(paradas){
						//colocando el mapa de la ruta
					var mapa = document.getElementById('mapaRuta');
					mapa.src = paradas[0].urlMapa;
					
					var parent = document.getElementById('parent');
					
					while (parent.firstChild) {
						parent.removeChild(parent.firstChild);
					}
					
					var divYear = document.createElement('DIV');
					divYear.setAttribute('class','timeline year mb-50');
					parent.appendChild(divYear);
					
					var divInnerYear = document.createElement('DIV');
					divInnerYear.setAttribute('class','timeline-year');
					divYear.appendChild(divInnerYear);
					var pR = document.createElement('P');
					divInnerYear.appendChild(pR);
					var pText = document.createTextNode('Ruta ' + paradas[0].nombreRuta);
					
					pR.appendChild(pText);
					pR.style.fontSize='15px';
					pR.style.width='200px';
					for(var i = 0; i < paradas.length;i++){
					
					var div = document.createElement('DIV');
					div.setAttribute('class','timeline');
					parent.appendChild(div);
					var divT = document.createElement('DIV');
					divT.setAttribute('class','timeline-content');
					div.appendChild(divT);
					var p = document.createElement('P');
					p.setAttribute('class','timeline-fecha');
					var text = document.createTextNode(paradas[i].horarioParada); 
					p.appendChild(text);
					divT.appendChild(p);
					var h3 = document.createElement('H3');
					h3.setAttribute('class','titulon2');
					h3.innerText=paradas[i].nombreParada;
					divT.appendChild(h3);
					var parrafo = document.createElement('P');
					parrafo.setAttribute('class','description');
					parrafo.innerText=paradas[i].descripcionParada;
					divT.appendChild(parrafo);
					var img = document.createElement('IMG');
					img.setAttribute('src',paradas[i].urlImage);
					divT.appendChild(img);
					}
					
				}	
		
				function getParadas(rutaId){
					
					var getParadasSolicitudURL = "${getParadasSolicitudURL}";
					$.ajax({
							type        : "POST",
							url         : getParadasSolicitudURL,
							cache       : false,
							async       : false,
							dataType    : "json",	
							data        : {
								"<portlet:namespace />rutaId" : rutaId
							}
						})
						.done(function(response) {
						//	console.log(response);
							
							var select = document.getElementById('paradasRuta');			
								
							while (select.firstChild) {
								select.removeChild(select.firstChild);
								}
									
							for(var i = 0; i < response.length;i++){
								
								var option = document.createElement("option");
								console.log("Hola berni");
								 console.log(response[i]);
								 option.value = response[i].idParada;
								 console.log(option.value);
								 option.text = response[i].nombreParada;
								 select.appendChild(option);
								}
							
						})
						.fail(function(jqXHR, textStatus, errorThrown) {
							 console.log("Request: " + JSON.stringify(jqXHR));
							 console.log("Request: " + textStatus.toUpperCase());
							console.log("Request: " + jqXHR.statusText);
						
						});
				}
				
						
				
				
		
	var getRutasURL = "${getRutasURL}";
	$.ajax({
			type        : "POST",
			url         : getRutasURL,
			cache       : false,
			async       : false,
			dataType    : "json",	
			data        : {}
		})
		.done(function(response) {
			console.log(response);
			for(var i = 0; i < response.length;i++){
			var select = document.getElementById('exampleFormControlSelect1');			
			var selectSolicitud = document.getElementById('rutasSolicitud');			
			
			var option = document.createElement("option");
			var optionSolicitud = document.createElement("option");
			
			 option.value = response[i].id;
			 option.text = response[i].nombre;
			 select.appendChild(option);
			 
			 optionSolicitud.value = response[i].id;
			 optionSolicitud.text = response[i].nombre;
			 selectSolicitud.appendChild(optionSolicitud);
			
			 if(i == 0)
				 {document.getElementById('capacidad').value =  response[i].capacidad +" personas";
				 getCapacidadDisponibilidad(response[i].id);
				 getParadas(response[i].id);
				 }
			}
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			 console.log("Request: " + JSON.stringify(jqXHR));
			 console.log("Request: " + textStatus.toUpperCase());
			console.log("Request: " + jqXHR.statusText);
		
		});
    

	var date = new Date();

	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();

	var fecha = "";
	if(month < 10){
		fecha = day +'-0'+month+'-'+year;
	}else{
		fecha = day +'-'+month+'-'+year;
		}
	console.log('FECHA::' + fecha);
	document.getElementById('fecha').value=fecha;
	
	function saludo(response){
		console.table(response);
		console.log(response.disponibilidad);
	}
</script>
   