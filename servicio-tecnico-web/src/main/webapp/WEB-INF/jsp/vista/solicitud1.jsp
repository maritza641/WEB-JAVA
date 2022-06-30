<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Solicitud del Cliente</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">	
</head>
<body>
	<h1>Nueva Orden</h1>
	
	<div class="container">
		<h2>Datos del Cliente</h2>
		
		<form method="POST" action="OrdenController?indicador=agregarCliente">
		
			<div class="mb-3">
				<label class="form-label">Nombre:</label>
					<input type="text" class="form-control" id="nombre" name="nombre">
			</div>
			
			<div class="mb-3">
				<label class="form-label">Teléfono:</label>
					<input type="text" class="form-control" id="direccion" name="direccion">
			</div>
			
			<div class="mb-3">
				<label class="form-label">Dirección:</label>
					<input type="text" class="form-control" id="telefono" name="telefono">
			</div>
			
			<button type="submit" class="btn btn-primary">Ir a Electrodoméstico</button>
			
			<a href="${pageContext.request.contextPath}/Index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
			
		</form>
	</div>		
</body>
</html>