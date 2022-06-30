<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu Opciones</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    
</head>	

<body class="bg-light">

    <c:if test="${success == 2}">
        <div class="alert alert-success" role="alert">
            Orden de trabajo modificada, servicio y cliente asociado.
        </div>
    </c:if>


    <c:if test="${success == 1}">
        <div class="alert alert-success" role="alert">
            Orden de trabajo agregada.
        </div>
    </c:if>

    <c:if test="${success == 0}">
        <div class="alert alert-danger" role="alert">
            Orden de trabajo anulada.
        </div>
    </c:if>


    <div class="my-3 p-3 bg-body rounded shadow-sm">
        <h4 class="border-bottom pb-2 mb-0">Listados</h4>
        <div class="d-flex text-muted pt-3">
            <p class="pb-3 mb-0 small 1h-sm border-bottom">
                <a href="OrdenController?indicador=registrarcliente"><b>Clientes</b></a>
            </p>
        </div>
            
        <div class="d-flex text-muted pt-3">
            <p class="pb-3 mb-0 small 1h-sm border-bottom">
                <a href="OrdenController?indicador=registro-electro"><b>Trabajos</b></a>
            </p>
        </div>

        <div class="d-flex text-muted pt-3">
            <p class="pb-3 mb-0 small lh-sm border-bottom">
                <a href="OrdenController?indicador=registrarot"><b>Ordenes de Trabajo</b></a>
            </p>
        </div>
    </div>

    <div class="my-3 p-3 bg-body rounded shadow-sm">
        <h4 class="border-bottom pb-2 mb-0">Formulario Nueva O. T.</h4>
        <div class="d-flex text-muted pt-3">

            <div class="pb-3 mb-0 small lh-sm border-bottom w-100">
                <a href="OrdenController?indicador=Solicitud"><b>Crear Nueva Orden de Trabajo</b></a>

                <div class="d-flex justify-content-between">
                </div>
            </div>
        </div>
    </div>
</body>
</html>