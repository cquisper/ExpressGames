<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_PE"/>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <jsp:include page="layout/config.jsp"/>
    <title>${requestScope.title}</title>
</head>
<body class="d-flex flex-column h-100">
    <jsp:include page="layout/header.jsp"/>
    <div class="container-md">
        <div class="row">
            <div class="col mb-3">
                <h2>Historial de compras</h2>
            </div>
            <div class="row">
                <h5>Productos</h5>
            </div>
        </div>
        <hr class="dropdown-divider">
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Precio Total</th>
                    <th scope="col">Tipo de Pago</th>
                    <ht scope="col"></ht>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="hisCompra" items="${sessionScope.historialCompra}">
                <tr>
                    <th scope="row"><i class="fa-solid fa-cart-arrow-down fs-3 align-middle"></i></th>
                    <td class="align-middle">${hisCompra.detalleVenta.producto.nombre}</td>
                    <td class="align-middle">${hisCompra.fecha.toString()}</td>
                    <td class="align-middle"><fmt:formatNumber value="${hisCompra.montoBol}" type="currency"/></td>
                    <td class="align-middle">PayPal</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/view/boleta?idVenta=${hisCompra.id}" class="btn btn-outline-dark fw-bold">Boleta</a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <jsp:include page="layout/footer.jsp"/>
</body>
</html>
