<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Orden de Trabajo</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body>
		<h1>Orden de Trabajo # ${ot.id}</h1>
	 	<div class="container">
		
			<h3>Detalles del Electrodoméstico</h3>
			<div class="mb-3">
			<label class="form-label"> Tipo de Electrodoméstico:</label>
				<input type="text" class="form-control" id="tipoElectro" disbled value="${ot.electrodomestico_id.tipo}">
			</div>
			
			<div class="mb-3">
			<label class="form-label">Problema del Electrodoméstico</label>
				<input type="text" class="form-control" id="problemaElectro" name="problemaElectro" disabled value="{ot.electrodomestico_id.problema}">
			</div>
			
			<div class="mb-3">
			<label class="form-label">Estado de la Orden"</label>
				<input type="text" class="form-control" id="estdoOrden" name="estadoOrden" disabled value="${ot.estado}">
			</div>
			
				<h3>Detalles del Cliente</h3>
					
					<div class="mb-3">	
						<label class="form-label">Nombre del Cliente:"</label>
							<input type="text" class="form-control" id="nombreCliente" name="nombreCliente" disabled value="{ot.electrodomestico_id.cliente_id.nombre}">
					</div>
		
					<div class="mb-3">	
						<label class="form-label">Dirección del Cliente:"</label>
							<input type="text" class="form-control" id="direccionCliente" name="direccionCliente" disabled value="{ot.electrodomestico_id.cliente_id.direccion}">
					</div>
					
					<div class="mb-3">	
						<label class="form-label">Teléfono del Cliente:"</label>
							<input type="text" class="form-control" id="telefonoCliente" name="telefonoCliente" disabled value="{ot.electrodomestico_id.cliente_id.telefono}">
					</div>
			<a href="${pageContext.request.contextPath}/Index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
		</div>
</body>
</html>