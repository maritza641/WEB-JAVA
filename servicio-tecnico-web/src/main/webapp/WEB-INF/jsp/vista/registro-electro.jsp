<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Listado</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
		<h1>Electrodomésticos</h1>
		
			<thead>
				<tr>
					<th class="col">ID</th>
					<th class="col">Tipo</th>
					<th class="col">Problema</th>
					<th class="col">Cliente Asociado</th>
				</tr>
			</thead>
			
		<tbody>
			<c:forEach var="electrodomesticos" items="${electrodomesticos}">
				<tr>
					<td><c:out value="${electrodomesticos.id}"></c:out></td>
					<td><c:out value="${electrodomesticos.tipo}"></c:out></td>
					<td><c:out value="${electrodomesticos.problema}"></c:out></td>
					<td><c:out value="${electrodomesticos.cliente_id.nombre}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>	
		
		<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
		
		</div>
	</body>
</html>