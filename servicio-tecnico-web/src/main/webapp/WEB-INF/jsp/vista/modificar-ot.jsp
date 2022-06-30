<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Modificar Orden de Trabajo</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body>
		<h1>Modificar Orden de Trabajo</h1>
	 	<div class="container">
		
			<form method="POST" action="OrdenController?accion=finalizarEdit">
				<input type="hidden" class="form-control" id="idot" name="idCliente" value="${ot.id}">
				<input type="hidden" class="form-control" id="idCliente" name="idCliente" value="${ot.electrodomestico_id.cliente_id.id}">
				<input type="hidden" class="form-control" id="idElectrodomestico" name="idElectrodomestico" value="${ot.electrodomestico_id.id}">
								
				<h3>Detalles del Electrodoméstico</h3>
			<div class="mb-3">
			<label class="form-label"> Tipo de Electrodoméstico:</label>
				<input type="text" class="form-control" id="tipoElectro" name="tipoElectro" value="${ot.electrodomestico_id.tipo}">
			</div>
			
			<div class="mb-3">
			<label class="form-label">Problema del Electrodoméstico</label>
				<input type="text" class="form-control" id="problemaElectro" name="problemaElectro" value="{ot.electrodomestico_id.problema}">
			</div>
			
			<div class="mb-3">
			<label class="form-label">Estado de la Orden"</label>
				<select class="form-select" name="estadoOrden" id="estadoOrden">
					
					<c:choose>
						<c:when test="${ot.estado eq 'Pendiente'}">
							<option selected="selected" value="Pendiente">Pendiente</option>
							<option value="En reparacion">En reparacion</option>
							<option value="Reparado">Reparado</option>
							<option value="Orden cerrada">Orden cerrada</option>
							<option value="Sin reparacion">Sin reparacion</option>
						</c:when>
						
						<c:when test="${ot.estado eq 'En reparacion'}">
							<option value="Pendiente">Pendiente</option>
							<option selected="selected" value="En reparacion">En reparacion</option>
							<option value="Reparado">Reparado</option>
							<option value="Orden cerrada">Orden cerrada</option>
							<option value="Sin reparacion">Sin reparacion</option>
						</c:when>
						
						<c:when test="${ot.estado eq 'Reparado'}">
							<option value="Pendiente">Pendiente</option>
							<option value="En reparacion">En reparacion</option>
							<option selected="selected" value="Reparado">Reparado</option>
							<option value="Orden cerrada">Orden cerrada</option>
							<option value="Sin reparacion">Sin reparacion</option>
						</c:when>
						
						<c:when test="${ot.estado eq 'Orden cerrada'}">
							<option value="Pendiente">Pendiente</option>
							<option value="En reparacion">En reparacion</option>
							<option value="Reparado">Reparado</option>
							<option selected="selected" value="Orden cerrada">Orden cerrada</option>
							<option value="Sin reparacion">Sin reparacion</option>
						</c:when>
						
						<c:otherwise>
							<option value="Pendiente">Pendiente</option>
							<option value="En reparacion">En reparacion</option>
							<option value="Reparado">Reparado</option>
							<option value="Orden cerrada">Orden cerrada</option>
							<option selected="selected" value="Sin reparacion">Sin reparacion</option>						
						</c:otherwise>				
					</c:choose>
				</select>
			</div>
			
				<h3>Detalles del Cliente</h3>
					
					<div class=	"form-text">Al cambiar los datos del cliente, se refleja en todas las ordenes"</div>
				
					<div class="mb-3">	
						<label class="form-label">Nombre del Cliente:"</label>
							<input type="text" class="form-control" id="nombreCliente" name="nombreCliente" value="{ot.electrodomestico_id.cliente_id.nombre}">
					</div>
		
					<div class="mb-3">	
						<label class="form-label">Dirección del Cliente:"</label>
							<input type="text" class="form-control" id="direccionCliente" name="direccionCliente" value="{ot.electrodomestico_id.cliente_id.direccion}">
					</div>
					
					<div class="mb-3">	
						<label class="form-label">Teléfono del Cliente:"</label>
							<input type="text" class="form-control" id="telefonoCliente" name="telefonoCliente" value="{ot.electrodomestico_id.cliente_id.telefono}">
					</div>
					
				<button type="submit" class="btn btn-success">Finalizar la Modificación</button>
				
			<a href="${pageContext.request.contextPath}/Index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
		
			</form>
		</div>
</body>
</html>