<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Lista</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
		<h1>Ordenes de Trabajo</h1>
			
			<thead>
				<tr>
					<th class="col">ID</th>
					<th class="col">Electrodoméstico</th>
					<th class="col">Estado</th>
					<th class="col">Cliente Asociado</th>
					<th class="col">Fecha Solicitud</th>
					<th class="col">Fecha Ultima Actialización</th>
					<th class="col">Acciones</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="ot" items="${ot}">
					<tr>
						<td><c:out value="${ot.id}"></c:out></td>
						<td><c:out value="${ot.electrodomestico_id.tipo}"></c:out></td>
						<td><c:out value="${ot.estado}"></c:out></td>
						<td><c:out value="${ot.electrodomestico_id.cliente_id.nombre}"></c:out></td>
						<td><c:out value="${ot.fechaSolicitud}"></c:out></td>
						<td><c:out value="${ot.fechaActualizacionOrden}"></c:out></td>
						<td>
						<a href="OrdenController?indicador=editarot & id=${ot.id}">Modificar</a>
						
						<a href="OrdenController?indicador=verot & id=${ot.id}">Ver OT</a>
						</td>		
					</tr>
				</c:forEach>
			</tbody>
			
			<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>	
			
		</div>
	</body>
</html>