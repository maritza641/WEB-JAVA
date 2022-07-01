<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registro de Estudiantes</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>    
	</head>
	<body class="bg-light">
	
		<c:if test="${success == 0}">
		<div class="alert alert-success" role="alert">
		  Elija su Género.
		</div>
		</c:if>
	
		<c:if test="${success == 1}">
		<div class="alert alert-success" role="alert">
		  Elija su Curso.
		</div>
		</c:if>
		
	    <div class="my-3 p-3 bg-body rounded shadow-sm">
	    <h1>Registro de Estudiante</h1>
	    <div class="container">

			<h2>Datos del Estudiante</h2>
	      	<div class="col-mb-4">
	      		<form method="POST" action="/JavaPeopleController?accion=addEstudiante">
	        	
                <div class="mb-2">
	      			<label class="form-label">RUT</label>
					<input required type="text" class="form-control" id="rut" name="rut">
	            </div>

                <div class="mb-2">
                    <label class="form-label">Apellidos</label>
                    <input required type="text" class="form-control" id="apellidos" name="apellidos">
                </div>

                <div class="mb-2">
                    <label class="form-label">Nombres</label>
                    <input required type="text" class="form-control" id="nombres" name="nombres">
                </div>

				<p> Género<br>
					<input type="radio" name="genero" value="femenimo" checked> Fem
					<input type="radio" name="genero" value="masculino" checked> Mas
				</p>

                <div class="mb-2">
                    <label class="form-label">Fono</label>
                    <input required type="text" class="form-control" id="fono" name="fono">
                </div>

				<div class="mb-2">
                    <label class="form-label">Asignatura</label>
                    <select class="form-select" id="asignatura" name="asignatura">		
						<option selected="selected" value="none">Elija una Asignatura</opcion>
					   	<option value="asignatura 1">Asignatura 1</option>
					   	<option value="asignatura 2">Asignatura 2</option>
						<option value="asignatura 3">Asignatura 3</option>
				   </select>
                </div>
				<button type="submit" class="btn btn-success">Registrar</button>
				<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
				</form>
            </div>
	    </div>
        </div>
	</body>
</html>