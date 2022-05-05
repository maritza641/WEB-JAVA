<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Circulo</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/includes/header.jsp" %>
	<div class="container">
	
	<h1>Circulo</h1>

	<form method="GET" action="calculo-area-circulo">
		
		<div class="mb-3">
			<select name="operacion" class="form-select">
  				<option selected>Seleccione un operacion</option>
 			 	<option value="area">Calcular Area</option>
  				<option value="perimetro">Calcular Perimetro</option>
			</select>
		</div>
		<div class="mb-3">
	  		<label for="radio" class="form-label">Radio</label>
	  		<input type="text" class="form-control" id="radio" name="radio">
		</div> 
		<button class="btn btn-primary" type="submit">Calcular</button>
	</form>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>