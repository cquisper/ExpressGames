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
        <div class="row mb-2 pb-2">
            <div class="col-sm">
                <h4 class="text-dark">
                    Carrito de compras (${venta.cantidadProductos} productos)
                    <hr class="dropdown-divider">
                </h4>
            </div>
        </div>
        <div class="row">
            <div class="col-md">                    <%-- <= --%>
                <c:if test="${venta.detalleVentas.size() le 0}">
                    <div class="text-center fs-5">
                        <h4 class="mb-3">Tu carrito está vacío</h4>
                        <i class=" fa-solid fa-cart-shopping fs-1 py-3 mb-3"></i>
                        <p>
                            No tienes ningún producto en tu carrito. Haga clic <a href="${pageContext.request.contextPath}/express.game" class="link-success">aquí</a> para continuar explorando este
                            sorprendente mundo de los videojuegos.
                        </p>
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/carrito" method="post">
                    <c:forEach var="detVenta" items="${venta.detalleVentas}">
                        <!-- Un producto -->
                        <div class="card border-light mb-3 border border-2">
                            <div class="row g-0">
                                <div class="col-md-4 pt-2">
                                    <a href="${pageContext.request.contextPath}/producto?code=${detVenta.producto.codigo}">
                                        <img src="${detVenta.producto.imagenRuta}" class="img-fluid rounded-start" alt="${detVenta.producto.nombre.replaceAll(" ", "-")}">
                                    </a>
                                </div>
                                <div class="col-md">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col">
                                                <p class="card-text text-muted text-uppercase">
                                                    ${detVenta.producto.plataforma.empresa.nombre}
                                                </p>
                                                <p class="card-text">
                                                    <small class="text-muted">SKU: ${detVenta.producto.codigo}</small>
                                                </p>
                                                <p class="card-text">
                                                <h4 class="card-title text-dark">
                                                    ${detVenta.producto.nombre}
                                                </h4>
                                                </p>
                                                <p class="card-text">
                                                    <small class="text-muted">${detVenta.producto.subCategoria.nombre}</small>
                                                </p>
                                            </div>
                                            <div class="col">
                                                <div class="d-flex flex-column">
                                                    <div class="col mb-1">
                                                        <p class="text-danger fw-bold">
                                                            <fmt:formatNumber value="${detVenta.producto.precio}" type="currency"/>
                                                        </p>
                                                    </div>
                                                    <div class="col">
                                                        <p class="text-secondary">
                                                            <i class="fa-solid fa-check"></i>
                                                            Envío a domicilio
                                                        </p>
                                                    </div>
                                                    <div class="col-sm-6 align-self-end pt-3">
                                                        <input class="form-control text-dark fw-bold" type="number" name="cant_${detVenta.producto.codigo}" minlength="1" min="1" max="99"  maxlength="2" value="${detVenta.cantidad}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer bg-transparent">
                                        <div class="d-flex bd-highlight align-bottom">
                                            <i class="fa fa-solid fa-truck align-bottom me-auto pt-1"></i>
                                            <button type="submit" class="link-secondary me-2 ps-2 bg-white border-0 text-decoration-underline">Actualizar</button>
                                            <a href="${pageContext.request.contextPath}/carrito?retirar=true&code=${detVenta.producto.codigo}" class="link-secondary align-middle" style="padding-top: 1px;">Eliminar</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Un producto getMonto-->
                    </c:forEach>
                </form>
            </div>
            <c:if test="${venta.detalleVentas.size() gt 0}">
                <div class="col-md-4">
                    <div class="card mb-3 p-3 bg-dark">
                        <div class="card-body">
                            <h3 class="card-title text-white">Resumen de la orden</h3>
                            <hr class="dropdown-divider text-light">
                            <p class="card-text">
                            <div class="d-flex justify-content-between" style="color: #939393">
                                <span>Subtotal:</span>
                                <span><fmt:formatNumber value="${venta.monto}" type="currency"/></span>
                            </div>
                            </p>
                            <hr class="dropdown-divider text-light">
                            <p class="card-text" style="color: #939393">
                            <div class="d-flex justify-content-between" style="color: #939393">
                                <span>Costo de envío:</span>
                                <span>S/ 0</span>
                            </div>
                            </p>
                            <hr class="dropdown-divider text-light">
                            <p class="card-text" style="color: #939393">
                            <div class="d-flex justify-content-between fw-bold" style="color: #939393">
                                <span>Total:</span>
                                <span><fmt:formatNumber value="${venta.monto}" type="currency"/></span>
                            </div>
                            </p>
                            <hr class="dropdown-divider text-light">
                            <p class="card-text" style="color: #939393">
                            <form class="d-flex flex-row" action="${pageContext.request.contextPath}/checkout/compra" method="post" id="formva">
                                <button type="submit" class="btn flex-fill rounded-pill" style="background: #1FDF64">
                                    <strong>CONTINUAR COMPRA</strong>
                                    <i class="fab fa-brands fa-paypal"></i>
                                </button>
                            </form>
                            </p>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <c:if test="${sessionScope.modalCompra eq 'true'}">
        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Opciones de compra</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col border-end">
                                <div class="card border-0">
                                    <div class="card-body">
                                        <h5 class="card-title mb-3">Realizar el pedido como nuevo cliente</h5>
                                        <p class="card-text">Create una nueva cuenta y realiza tus compras de manera más rapida, sencilla y sin complicaciones.</p>
                                    </div>
                                    <div class="card-footer bg-transparent border-0">
                                        <a href="${pageContext.request.contextPath}/register" class="btn btn-success">Registrate aqui!</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col border-start">
                                <form action="${pageContext.request.contextPath}/login" method="post">
                                    <div class="card border-0">
                                        <div class="card-body">
                                            <h5 class="card-title mb-3">Realizar el pedido usando su cuenta</h5>
                                            <div class="mb-3">
                                                <label for="usernameOrEmail" class="form-label">Nombre de usuario o email</label>
                                                <input type="text" name="usario_email" class="form-control" id="usernameOrEmail" placeholder="ingrese su nombre de usuario o email" required>
                                            </div>
                                            <div class="">
                                                <label for="password" class="form-label">Contraseña</label>
                                                <input type="password" name="password-login" class="form-control" id="password" placeholder="ingrese su contraseña" required>
                                            </div>
                                        </div>
                                        <div class="card-footer bg-transparent border-0">
                                            <button class="btn btn-info" type="submit">Iniciar sesión</button>
                                            <input type="hidden" name="loginModal" value="true">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <%--<button type="button" class="btn btn-primary">Understood</button>--%>
                    </div>
                </div>
            </div>
        </div>
        <!-- Button trigger modal -->
        <button type="button" class="d-none" id="btnModal" data-bs-toggle="modal" data-bs-target="#staticBackdrop"></button>
        <script>
            document.querySelector("#btnModal").click();
        </script>
        <c:set var="modalCompra" scope="session" value="false"/>
    </c:if>

    <jsp:include page="layout/footer.jsp"/>
</body>
</html>
