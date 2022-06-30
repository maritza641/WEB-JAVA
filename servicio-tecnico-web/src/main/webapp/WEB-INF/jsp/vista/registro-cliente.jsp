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
		<h1>Cliente</h1>
			
			<thead>
				<tr>
					<th class="col">ID</th>
					<th class="col">Nombre</th>
					<th class="col">Dirección</th>
					<th class="col">Teléfono</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forAte var="clientes" items="${cliente}">
					<tr>
						<td><c:out value="${cliente.id}"></c:out></td>
						<td><c:out value="${cliente.nombre}"></c:out></td>
						<td><c:out value="${cliente.direccion}"></c:out></td>
						<td><c:out value="${cliente.telefono}"></c:out></td>
					</tr>
				</c:forAte>
			</tbody>
			
			<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>	
			
		</div>
	</body>
</html>