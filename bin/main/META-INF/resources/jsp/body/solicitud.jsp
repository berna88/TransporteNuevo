<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
   
<div class="tab-pane fade" id="pills-seccion1" role="tabpanel" aria-labelledby="pills-seccion1-tab">
	<section class="row justify-content-center">
		<article class="col-10 col-sm-10 col-md-10 col-lg-5 col-xl-5">
			<form id="transporteSolicitud" action="<%= procesaSolicitudURL %>" method="POST">
			  <div class="row">
			    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mb-3">
			    <label>Empleado<span class="amarillo">*</span></label>
			      <input type="text" value="${noEmpleado}" name="<portlet:namespace/>empleado" disabled class="form-control form-control-sm" >
			    </div>
			    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mb-3">
			    	<label>Teléfono (móvil)<span class="amarillo">*</span></label>
			      	<input type="text" value="${mobile}" name="<portlet:namespace/>telefono" disabled class="form-control form-control-sm" >
			    </div>
			    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mb-3">
			    	<label>Fecha<span class="amarillo"></span></label>
			      	<input type="text" name="<portlet:namespace/>fecha" disabled class="form-control form-control-sm" id="fecha">
			    </div>
			    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mb-3">
			    	<label for="exampleFormControlSelect1">Ruta elegida<span class="amarillo">*</span></label>
				    <select name="<portlet:namespace/>rutaElegida" class="form-control form-control-sm" id="rutasSolicitud" onchange="getParadas(this.value)">
				     </select>
			    </div>
			    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mb-5">
			    	<label for="exampleFormControlSelect1">Parada elegida<span class="amarillo">*</span></label>
				    <select name="<portlet:namespace/>paradaElegida" class="form-control form-control-sm" id="paradasRuta">
				     
				    </select>
			    </div>
			    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mb-2">
			    	<label for="exampleFormControlSelect1" class="font-weight-bold">Reglamento de transporte</label>
			  	</div>
			  	<div class="col-sm-12 mb-5">
			        
			        <div class="form-check">
						<label class="terminosVacaciones">
						  <input id="CheckPoliticas" type="radio" name="radio" required="required">
						  <span class="checkmark"></span>
						  He leido y acepto <a href="" class="amarillo">Addendum el reglamento para transporte de empleados de José Cuervo.</a>
						</label>
					</div>
					<div class="form-check">
						<label class="terminosVacaciones">
						  <input id="CheckReglamentos" type="radio" name="radio" required="required">
						  <span class="checkmark"></span>
						  He leido y acepto el reglamento de <a href="" class="amarillo">Transporte general UTEP.</a>
						</label>
					</div>
			    </div>
			    <div class="col-sm-12">
			    	<button type="submit" class="pl-6 pr-6 btn btn-primary border border-0 float-right font-weight-bold" style="background-color: #cbb874;color: black;">Solicitar</button>
			    </div>
			</form>		
		</article>
	</section>
</div>

