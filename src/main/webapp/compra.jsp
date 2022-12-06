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
    <header class="header">
        <div class="rellenar_header bg-dark mb-4 py-1">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-md">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/express.game">
                        <h3 class="align-middle pt-2">
                            <img src="${(cambiarDireccion eq "true") ? "../" : ""}assets/image/favicons/android-chrome-192x192.png" alt="" width="40" height="40" class="d-inline-block align-text-top">
                            ${applicationScope.titleApp}
                        </h3>
                    </a>
                </div>
            </nav>
        </div>
    </header>
    <div class="container mb-3">
        <div class="row">
            <!--La direccion-->
            <div class="col me-3 p-5 shadow bg-body rounded">
                <div class="row row-cols-1">
                    <div class="col">
                        <div class="card mb-3 border-0">
                            <div class="card-body p-0">
                                <h4 class="card-title pb-2">
                                    Tus datos
                                </h4>
                                <p class="card-text">
                                    <i class="fa-solid fa-user"></i>
                                    ${sessionScope.cliente.get().nombre} ${sessionScope.cliente.get().apellido}
                                </p>
                                <p class="card-text">
                                    <i class="fa-solid fa-envelope"></i>
                                    ${sessionScope.cliente.get().usuario.email}
                                </p>
                                <p class="card-text">
                                    <i class="fa-solid fa-id-card"></i>
                                    ${sessionScope.cliente.get().dni}
                                </p>
                                <c:if test="${not empty venta.direccionEntrega}">
                                    <div class="d-flex justify-content-between">
                                        <span>
                                            <i class="fa-solid fa-map-location-dot"></i>
                                            ${venta.direccionEntrega}
                                        </span>
                                        <a href="${pageContext.request.contextPath}/checkout/direccion" class="text-muted"><i class="fa-solid fa-pen pe-1"></i>Cambiar</a>
                                    </div>
                                    <c:set var="direccion" value="${venta.direccionEntrega.split(', ')}"/>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <hr class="dropdown-divider text-dark">
                    <div class="col">
                        <form class="row g-3" action="${pageContext.request.contextPath}/checkout/direccion" method="post">
                            <h4 class="card-title pt-2">
                                <i class="fa-solid fa-location-dot"></i>
                                Ingresa tu dirección
                            </h4>
                            <span class="text-muted m-0">para continuar con su <strong>compra</strong> y poder realizar la <strong>entrega</strong></span>
                            <div class="col-md-6">
                                <label for="inputDepart" class="form-label">Departamento</label>
                                <input type="text" name="departamento" class="form-control" value="${direccion[3]}" id="inputDepart" placeholder="Ingrese un Departamento" ${(empty direccion) || (cambiarDireccion eq "true") ? "" : "disabled"} required>
                            </div>
                            <div class="col-md-6">
                                <label for="inputProvin" class="form-label">Provincia</label>
                                <input type="text" name="provincia" class="form-control" value="${direccion[2]}" id="inputProvin" placeholder="Ingrese una Provincia" ${(empty direccion) || (cambiarDireccion eq "true") ? "" : "disabled"} required>
                            </div>
                            <div class="col-md-6">
                                <label for="inputDist" class="form-label">Distrito</label>
                                <input type="text" name="distrito" class="form-control" value="${direccion[1]}" id="inputDist" placeholder="Ingrese un Distrito" ${(empty direccion) || (cambiarDireccion eq "true") ? "" : "disabled"} required>
                            </div>
                            <div class="col-md-6">
                                <label for="inputCalle" class="form-label">Avenida/ Calle/ Jirón</label>
                                <input type="text" name="calle" class="form-control" value="${direccion[0]}" id="inputCalle" placeholder="Ingrese el nombre de la Calle" ${(empty direccion) || (cambiarDireccion eq "true") ? "" : "disabled"} required>
                            </div>
                            <div class="col-12">
                                <button type="submit" ${(empty direccion) || (cambiarDireccion eq "true") ? "" : "disabled"} class="btn btn-primary">Confirmar dirección</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!--Resumen de la compra-->
            <div class="col-md-4 p-5 shadow bg-body rounded">
                <div class="card mb-3 border-0">
                    <div class="card-body p-0">
                        <div>
                            <h4 class="card-title d-inline-block">Resumen de la orden</h4>
                            <span class="text-muted d-inline-block">(${venta.cantidadProductos} productos)</span>
                        </div>
                        <hr class="dropdown-divider text-dark">
                        <div class="accordion accordion-flush" id="accordionFlushExample">
                            <div class="accordion-item">
                                <div class="accordion-header d-flex justify-content-between text-muted" id="flush-headingOne">
                                    <a class="accordion-button collapsed text-muted bg-white px-0 py-2 border-0" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="true" aria-controls="flush-collapseOne" id="botonDetalle">
                                        Ver detalle
                                    </a>
                                    <script>
                                        const btnDetalle = document.querySelector("#botonDetalle");
                                        btnDetalle.addEventListener("click", cambiarNombre);
                                        function cambiarNombre(){
                                            const expand = btnDetalle.getAttribute("aria-expanded");
                                            btnDetalle.textContent = (expand == "true") ? "Ver menos" : "Ver detalle";
                                        }
                                    </script>
                                </div>
                                <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                    <c:forEach var="detVenta" items="${venta.detalleVentas}">
                                        <!-- Un producto -->
                                        <div class="card border-light border border-2">
                                            <div class="row g-0">
                                                <div class="col-md-4 pt-2" style="max-width: 114px;">
                                                    <img src="${detVenta.producto.imagenRuta}" class="img-fluid rounded-start" alt="${detVenta.producto.nombre.replaceAll(" ", "-")}">
                                                </div>
                                                <div class="col-md">
                                                    <div class="card-body pb-2">
                                                        <div class="row">
                                                            <div class="col">
                                                                <p class="card-title text-muted text-uppercase m-0">
                                                                    <small>${detVenta.producto.plataforma.empresa.nombre}</small>
                                                                </p>

                                                                <p class="card-title text-dark m-0">
                                                                    <small>${detVenta.producto.nombre}</small>
                                                                </p>

                                                                <p class="card-text m-0">
                                                                    <small class="text-muted">${detVenta.cantidad} uni.</small>
                                                                </p>
                                                            </div>
                                                            <div class="col-md-4 pe-0">
                                                                <div class="d-flex flex-column">
                                                                    <div class="col mb-1">
                                                                        <p class="text-danger fw-bold">
                                                                            <small><fmt:formatNumber value="${detVenta.producto.precio}" type="currency"/></small>
                                                                        </p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <hr class="dropdown-divider text-dark">
                        <p class="card-text py-1">
                            <a href="${pageContext.request.contextPath}/carrito" class="text-muted">
                                <i class=" fa-solid fa-cart-shopping me-2"></i>Volver al Carrito
                            </a>
                        </p>
                        <hr class="dropdown-divider text-dark">
                        <p class="card-text">
                        <div class="d-flex justify-content-between">
                            <span>Subtotal:</span>
                            <span><fmt:formatNumber value="${venta.monto}" type="currency"/></span>
                        </div>
                        </p>
                        <hr class="dropdown-divider text-dark">
                        <p class="card-text" style="color: #939393">
                        <div class="d-flex justify-content-between">
                            <span>Costo de envío:</span>
                            <span>S/ 0</span>
                        </div>
                        </p>
                        <hr class="dropdown-divider text-dark">
                        <p class="card-text" style="color: #939393">
                        <div class="d-flex justify-content-between fw-bold">
                            <span>Total:</span>
                            <span><fmt:formatNumber value="${venta.monto}" type="currency"/></span>
                        </div>
                        </p>
                        <hr class="dropdown-divider text-dark">
                        <p class="card-text" style="color: #939393">
                        <form class="d-flex flex-row" action="${pageContext.request.contextPath}/checkout/payment" method="post" id="formva">
                            <button ${(empty direccion) || (cambiarDireccion eq "true") ? "disabled" : ""} type="submit" class="btn flex-fill rounded-pill" style="background: #1FDF64">
                                <strong>CONTINUAR AL PAGO</strong>
                                <i class="fab fa-brands fa-paypal"></i>
                            </button>
                        </form>
                        </p>
                        <c:if test="${(empty direccion) || (cambiarDireccion eq 'true')}">
                            <p><small class="text-danger">*Debe ingresar su dirección para poder continuar con su pago*</small></p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
