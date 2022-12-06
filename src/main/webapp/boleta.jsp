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
<body class="d-flex flex-column h-100" style="background: #EEEEEE">
    <jsp:include page="layout/header.jsp"/>

    <div class="container-md">

        <div class="card mb-3 border-0 shadow bg-body rounded p-2">
            <div class="card-body">
                <div class="row justify-content-between align-items-center">
                    <div class="col-md">
                        <h5 class="mb-2 mb-md-0">Boleta #AD2029${sessionScope.boleta.id}</h5>
                    </div>
                    <div class="col-auto">
                        <form action="${pageContext.request.contextPath}/boletaVentaElectronica" method="post" target="_blank">
                            <input type="hidden" name="idVenta" value="${sessionScope.boleta.id}">
                            <button class="btn btn-success me-1" type="submit"><span class="fas fa-arrow-down me-1"> </span>Download (.pdf)</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Muestra para la impresion -->

        <!-- tabla boleta -->
        <div class="card mb-3 border-0 shadow bg-body rounded p-2">
            <div class="card-body">
                <!-- cabecera factura -->
                <div class="row align-items-center text-center mb-3">
                    <!-- A adir el logo -> no lo pude descargar -->
                    <div class="col-sm-6 text-sm-start"><img src="assets/image/favicons/android-chrome-512x512.png" alt="invoice" width="150" /></div>
                    <div class="col text-sm-end mt-3 mt-sm-0">
                        <h2 class="mb-3">Boleta de venta electronica</h2>
                        <h5>Express Game</h5>
                        <h5>R.U.C. N° 20500128059</h5>
                    </div>
                    <div class="col-12">
                        <hr />
                    </div>
                </div>
                <div class="row align-items-center mb-3">
                    <div class="col">
                        <h6 class="text-500">Boleta a</h6>
                        <h5>${sessionScope.boleta.cliente.nombre} ${sessionScope.boleta.cliente.apellido}</h5>
                        <c:set var="direccion" value="${sessionScope.boleta.direccionEntrega.split(', ')}"/>
                        <p class="">${direccion[0]}<br />${direccion[2]}, ${direccion[1]}<br />Perú </p>
                        <p>
                            <span class="fw-bold">DNI: </span> ${sessionScope.boleta.cliente.dni} <br>
                            <span class="fw-bold">Email: </span> ${sessionScope.boleta.cliente.usuario.email}
                        </p>
                    </div>
                    <div class="col-sm-auto ms-auto">
                        <div class="table-responsive">
                            <table class="table table-sm table-borderless fs--1">
                                <tbody>
                                <tr>
                                    <th class="text-sm-start">Boleta N :</th>
                                    <td>${sessionScope.boleta.id}</td>
                                </tr>
                                <tr>
                                    <th class="text-sm-start">Fecha de pago:</th>
                                    <td>${sessionScope.boleta.fecha.toString()}</td>
                                </tr>

                                <tr class="alert-success fw-bold">
                                    <th class="text-sm-start">Monto total:</th>
                                    <td><fmt:formatNumber value="${sessionScope.boleta.montoBol}" type="currency"/></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-12">
                        <hr />
                    </div>
                </div>
                <div class="row align-items-center mb-3">
                    <div class="col">
                        <h6 class="text-500">Distribuido por</h6>
                        <h5>${sessionScope.boleta.distribuidor.nombre} ${sessionScope.boleta.distribuidor.apellido}</h5>
                        <p>
                            <span class="fw-bold">DNI: </span> ${sessionScope.boleta.distribuidor.dni} <br>
                            <span class="fw-bold">Telefono: </span> +51 ${sessionScope.boleta.distribuidor.telefono}
                        </p>
                    </div>
                    <div class="col-12">
                        <hr />
                    </div>
                </div>
                <!-- la parte de a tabla de datos en si  -->
                <div class="table-responsive scrollbar mt-4">
                    <table class="table table-striped">
                        <thead class="table-dark">
                        <tr class="bg-primary text-white">
                            <th class="border-0">Productos</th>
                            <th class="border-0 text-center">Cantidad</th>
                            <th class="border-0 text-end">Precio</th>
                            <th class="border-0 text-end">Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- cada producto en si  -->
                        <c:forEach var="detVenta" items="${sessionScope.boleta.detalleVentas}">
                            <tr>
                                <td class="align-middle">
                                    <h6 class="mb-0">${detVenta.producto.nombre}</h6>
                                </td>
                                <td class="align-middle text-center">${detVenta.cantidad}</td>
                                <td class="align-middle text-end"><fmt:formatNumber value="${detVenta.precio}" type="currency"/></td>
                                <td class="align-middle text-end"><fmt:formatNumber value="${detVenta.importeBol}" type="currency"/></td>
                            </tr>
                        </c:forEach>
                        <!-- cada producto en si  -->

                        </tbody>
                    </table>
                </div>
                <div class="row justify-content-end">
                    <div class="col-auto">
                        <table class="table table-sm table-borderless fs--1 text-end">
                            <tr class="fw-bold">
                                <th>Monto total a pagar: </th>
                                <td><fmt:formatNumber value="${sessionScope.boleta.montoBol}" type="currency"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="card-footer bg-light">
                <p class="fs--1 mb-0"><strong>Nota: </strong>Realmente apreciamos su confianza en nuestra empresa, si hay algo ms que podamos hacer, haganoslo saber!</p>
            </div>
        </div>
        <!-- /tabla boleta -->
    </div>
    <!-- Muestra para la impresion -->
    <jsp:include page="layout/footer.jsp"/>
</body>
</html>
