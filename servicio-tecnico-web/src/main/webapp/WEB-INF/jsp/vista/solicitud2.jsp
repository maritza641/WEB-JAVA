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
		<h2>Datos del Electrodoméstico</h2>
		
		<form method="POST" action="OrdenController?indicador=agregarElectrodomestico">
			<input type="hidden" class="form-control" id="id" name="id" value="${cliente.id}">
		
				<div class="mb-3">
					<label class="form-label">Nombre Cliente:</label>
						<input type="text" class="form-control" id="nombre" name="nombre" disabled value="${cliente.nombre}">
				</div>
				
				<div class="mb-3">
					<label class="form-label">Tipo de Electrodomestico:</label>
						<input type="text" class="form-control" id="tipoElectro" name="tipoElectro">
				</div>
				
				<div class="mb-3">
					<label class="form-label">Problema del Electrodoméstico:</label>
						<input type="text" class="form-control" id="problemaElectro" name="problemaElectro">
				</div>
				
				<button type="submit" class="btn btn-primary">Agregar Electrodoméstico</button><br>
		</form>
			
		<form method="POST" action="OrdenController?finalizar">
			<input type="hidden" class="form-control" id="id" name="id" value="${cliente.id}">	
			
			<button type="submit" class="btn btn-primary">Finalizar</button>
				
			<div class="form-text">Antes de finalizar por favor, agregar un Electrodoméstico</div>
			<div class="form-text">Se anula la orden si no agrega al menos un electrodoméstico</div>
		</form>
	</div>		
</body>
</html>