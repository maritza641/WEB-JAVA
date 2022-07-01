<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/offcanvas-navbar/">
		<title>Indice</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>    
	</head>
	<body class="bg-light">
	
    <main>
        
        <c:if test="${codigo == 3}">
	    <div class="alert alert-success" role="alert">
            Nota rectificada.
        </div>
        </c:if>

        <c:if test="${codigo == 2}">
	    <div class="alert alert-success" role="alert">
            Se ingreso ${nota} al módulo.
        </div>
        </c:if>

        <c:if test="${codigo == 1}">
        <div class="alert alert-success" role="alert">
            Estudiante ingreso al registro.
        </div>
        </c:if>

        <c:if test="${codigo == 0}">
        <div class="alert alert-success" role="alert">
            Error! Al ingresar estudiante al registro, intentar nuevamente.
        </div>
        </c:if>

        <div class="my-3 p-3 bg-body rounded shadow-sm">
            <h4 class="border-bottom pb-2 mb-0">Listado de Estudiantes</h4>
                <div class="d-flex text-muted pt-3">
                    <a href="/JavaPeopleController?accion=listar"><b>Estudiantes</b></a>
                </div>
        </div>

        <div class="my-3 p-3 bg-body rounded shadow-sm">
            <h4 class="border-bottom pb-2 mb-0">Registro de Estudiantes</h4>
                <div class="d-flex text-muted pt-3">
                    <a href="/JavaPeopleController?accion=formulario"><b>Registrar Nuevo Estudiante</b></a>
                </div>
        </div>
    </main>
    </body>
</html>