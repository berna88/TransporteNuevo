<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4 class="txt-center">Transporte General</h4>
<div class="container">				
	<form action="#" class="formulario mt-50 mb-50 d-md-none">
		<div class="formulario-select">
			<i class="fas fa-chevron-down"></i>
		  	<select class="custom-select" id="selectSeccion">
				<option value="pills-seccion" data-toggle="tab" href="#pills-seccion" role="tab" aria-controls="pills-seccion" aria-selected="true">Rutas</option>
				<option value="pills-seccion1" data-toggle="tab" href="#pills-seccion1" role="tab" aria-controls="pills-seccion1" aria-selected="false">Solicitud</option>
			</select>
		</div>
	</form>
	<ul class="nav nav-pills nav-pills-submenu justify-content-center mt-50 mb-50 d-none d-md-flex" id="pills-tab" role="tablist">
		<li class="nav-item">
			<a class="nav-link active" id="pills-seccion-tab" data-toggle="tab" href="#pills-seccion" role="tab" aria-controls="pills-seccion" aria-selected="true">Rutas</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="pills-seccion1-tab" data-toggle="tab" href="#pills-seccion1" role="tab" aria-controls="pills-seccion1" aria-selected="false">Solicitud</a>
		</li>
	</ul>
</div>
<div class="tab-content mb-50" id="pills-tabContent">
	<%@ include file="linetime.jsp" %>
	<%@ include file="solicitud.jsp" %>	  	
</div>