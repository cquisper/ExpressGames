<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_PE"/>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${requestScope.title}</title>
    <jsp:include page="layout/config.jsp"/>
</head>
<body class="d-flex flex-column h-100">
    <jsp:include page="layout/header.jsp"/>
    <div class="container">
        <div class="row my-3">
            <div class="imagen_producto col">
                <img src="${producto.imagenRuta}" class="img-fluid" alt="${producto.nombre.replaceAll(" ", "-")}">
            </div>
            <div class="col-md-8">
                <div class="contenido_producto w-100 h-100  align-items-center">
                    <h3 class="">${producto.nombre}</h3>
                    <div class="d-flex w-50 justify-content-between my-3">
                        <p class="fs-5 text-success" ><span><i class="fa-solid fa-check"></i> </span> Disponible </p>
                        <p class="fs-5">SKU <span class="text-success sku">${producto.codigo}</span></p>
                    </div>
                    <p class="fs-2 fw-bold">
                        <fmt:formatNumber value="${producto.precio}" type="currency"/>
                    </p>
                    <p class="descripcion" >
                        ${producto.descripcion}
                    </p>
                    <form action="${pageContext.request.contextPath}/carrito" method="post">
                        <div class="d-flex w-75 justify-content-between align-items-center my-4">
                            <div class="contenedor-cantidad w-100">
                                <p class="fs-5 fw-bold">Cantidad</p>
                                <input class="w-50 fw-bold form-control" type="number" name="cantidad" value="1" min="1" max="99">
                                <input type="hidden" name="code" value="${producto.codigo}"/>
                            </div>
                            <button type="submit" class="btn btn-success mt-5" style="width: 45rem;">
                                <i class="fa-solid fa-cart-shopping"></i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div>
            <a class="btn btn-dark mb-2" data-bs-toggle="collapse" aria-expanded="false" aria-controls="collapse-1" href="#collapse-1" role="button" style="width: 100%;">Especificaciones</a>
            <div class="collapse  m-2 py-3 border border-secondary" id="collapse-1">
                <div class="p-2">
                    <c:if test="${producto.especificacion[0].contains('youtube')}">
                        <iframe width="560" height="315" src="${producto.especificacion[0]}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </c:if>
                    <p class="fw-bold ${producto.especificacion[0].contains('youtube') ? "mt-4" : ""}">Información del producto</p>
                    <ul>
                        <c:forEach var="epc" items="${requestScope.producto.especificacion}">
                            <c:if test="${not epc.contains('youtube')}">
                                <li>
                                    ${epc}
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="layout/footer.jsp"/>
</body>
</html>
