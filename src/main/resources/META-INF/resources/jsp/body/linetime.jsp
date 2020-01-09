<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   

<div class="tab-pane fade active show" id="pills-seccion" role="tabpanel" aria-labelledby="pills-seccion-tab">
			  		<form id="transporte" class="mb-50">
					  <div class="form-row">
					    <div class="col-md-4">
					      <label>Elige una ruta</label>
					      	<select class="form-control input-black" id="exampleFormControlSelect1" onchange="getCapacidadDisponibilidad(this.value)">
							</select>
					    	<small id="myBtn" onclick="modal()" class="amarillo">Ver mapa de la ruta</small>
					    	
					    </div>
					    <div class="col-md-4">
					      <label>Capacidad</label>
					      <input type="text" class="form-control input-black" value="" disabled id="capacidad">
					    </div>
					    <div class="col-md-4">
					      <label>Disponibilidad</label>
					      <input type="text" class="form-control input-black" value="" id="disponibilidadMapa" disabled>
					    </div>
					  </div>
					</form>
			  		<!-- Timeline -->
			  		<div class="container">
			  		    <div class="row">
			  		        <div class="col-md-12">
			  		            <div class="main-timeline mb-50" id="parent">
			  		            	<div class="timeline year mb-50">
			  		            	    <div class="timeline-year">
			  		            	    	<p>2019</p>
			  		            	    </div>
			  		            	</div>
			 
			  		            </div>
			  		        </div>
			  		    </div>
			  		</div>
			  	</div>
			  	
<!-- inicio modal -->
<div id="myModal-cuervo-marcas" class="modal-cuervo-marcas">
	<div class="modal-content-cuervo-marcas">
    	<div class="container">
        	<div class="row">
            	<div class="col-md-12">
                	<span class="close-cuervo-marcas">&times;</span>
                    <img alt="" src='<%=request.getContextPath()+"/img/ruta_oceania1.png"%>'>
                </div>
           </div>
       </div>
   </div>
</div>
<!-- Fin modal -->
<script>
function modal(){
	alert("Hola modal");
}
<!-- script modal -->
// Get the modal
var modal = document.getElementById("myModal-cuervo-marcas");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close-cuervo-marcas")[0];

// When the user clicks the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
<!-- fin script modal -->
</script>
			  