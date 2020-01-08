<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   

<div class="tab-pane fade active show" id="pills-seccion" role="tabpanel" aria-labelledby="pills-seccion-tab">
			  		<form id="transporte" class="mb-50">
					  <div class="form-row">
					    <div class="col-md-4">
					      <label>Elige una ruta</label>
					      	<select class="form-control input-black" id="exampleFormControlSelect1" onchange="getCapacidadDisponibilidad(this.value)">
							</select>
					    	<small class="amarillo">Ver mapa de la ruta</small>
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

			  