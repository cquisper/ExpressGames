<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="es_PE"/>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${requestScope.title}</title>
    <jsp:include page="layout/config.jsp"/>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="layout/header.jsp"/>
<!-- OJO OJETE ESTE ES EL CARRCEL NO MODIFICAR EL CSS -->
<div class="container-md">
    <!--Carrusel con categorias-->
    <div class="row">
        <jsp:include page="layout/filtro.jsp"/>
        <!--Carrusel-->
        <div class="col-md-8">
            <div class="w-100 " style="max-height: 30rem;">
                <div id="carouselExampleControls" class="carousel slide " data-bs-ride="carousel" >
                    <div class="carousel-inner">
                        <div class="carousel-item active " style=" height: 30rem;
                  background-size: cover;

                  background-position: center center;

                  ">
                            <img src="assets/image/consolas-carrusel.jpg" class="d-block w-100" alt="..." style=" height: 30rem;
                   background-size: cover;
                   background-position: center center;">

                        </div>
                        <div class="carousel-item" style=" height: 30rem;
                  background-size: cover;
                  background-repeat: no-repeat;
                  background-position: center center;

                  ">
                            <img src="assets/image/10-juegos-mas-vendidos.jpg" class="d-block w-100" alt="..." style=" height: 30rem;
                   background-size: cover;
                   background-position: center center;">

                        </div>
                        <div class="carousel-item" style=" height: 30rem;
                  background-size: cover;

                  background-position: center center;

                  ">
                            <img src="assets/image/con_videojuego.jpg" class="d-block w-100" alt="..." style=" height: 30rem;
                   background-size: cover;
                   background-position: center center;">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!--Novedades-->
    <div class="row">
        <div class="col">
            <h3 class="card-title my-3">
                Novedades
                <hr class="dropdown-divider">
            </h3>
            <div id="carouselNovedades" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="row row-cols-1 row-cols-md-3 g-4 mb-3">
                            <%--Un productos--%>
                            <c:forEach var="novedad" items="${requestScope.novedades1}">
                                <div class="col">
                                    <div class="card h-100 pt-2">
                                        <a href="${pageContext.request.contextPath}/producto?code=${novedad.codigo}" class="text-center">
                                            <img src="${novedad.imagenRuta}" class="card-img-top" alt="${novedad.nombre.replaceAll(" ", "-")}" style="max-width: 250px">
                                        </a>
                                        <div class="card-body">
                                            <h6 class="card-title text-muted mb-3 text-uppercase">
                                                    ${novedad.plataforma.nombre}
                                            </h6>
                                            <p class="card-text">
                                                    ${novedad.nombre}
                                            </p>
                                            <p class="text-danger fw-bold">
                                                <fmt:formatNumber value="${novedad.precio}" type="currency"/>
                                            </p>
                                        </div>
                                        <div class="card-footer text-center">
                                            <a href="${pageContext.request.contextPath}/carrito?code=${novedad.codigo}" class="btn btn-outline-success">
                                                Agregar al carrito
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--Un productos--%>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="row row-cols-1 row-cols-md-3 g-4 mb-3">
                            <%--Un productos--%>
                            <c:forEach var="novedad" items="${requestScope.novedades2}">
                                <div class="col">
                                    <div class="card h-100 pt-2">
                                        <a href="${pageContext.request.contextPath}/producto?code=${novedad.codigo}" class="text-center">
                                            <img src="${novedad.imagenRuta}" class="card-img-top" alt="${novedad.nombre.replaceAll(" ", "-")}" style="max-width: 250px">
                                        </a>
                                        <div class="card-body">
                                            <h6 class="card-title text-muted mb-3 text-uppercase">
                                                    ${novedad.plataforma.nombre}
                                            </h6>
                                            <p class="card-text">
                                                    ${novedad.nombre}
                                            </p>
                                            <p class="text-danger fw-bold">
                                                <fmt:formatNumber value="${novedad.precio}" type="currency"/>
                                            </p>
                                        </div>
                                        <div class="card-footer text-center">
                                            <a href="${pageContext.request.contextPath}/carrito?code=${novedad.codigo}" class="btn btn-outline-success">
                                                Agregar al carrito
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--Un productos--%>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev btn btn-secondary" type="button" data-bs-target="#carouselNovedades" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next btn btn-secondary" type="button" data-bs-target="#carouselNovedades" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </div>

    <!-- Probando esta imagen al medio-->
    <div class="row">
        <div class="col text-center">
            <img src="assets/image/jalavistas-juegos-ps4_1.webp" alt="poppyplaytimechapter2" class="img-fluid mx-auto mb-3" style="height: 16rem;">
        </div>
        <div class="col">
            <img src="assets/image/jalavistas-juegos-ps5.webp" alt="poppyplaytimechapter2" class="img-fluid mx-auto mb-3" class="img-fluid mx-auto mb-3" style="height: 16rem;">
        </div>
        <div class="col">
            <img src="assets/image/jalavistas-juegos-nsw_1.webp" alt="poppyplaytimechapter2" class="img-fluid mx-auto mb-3" class="img-fluid mx-auto mb-3" style="height: 16rem;">
        </div>
    </div>

    <!--Promociones-->
    <div class="row">
        <div class="col">
            <h3 class="card-title my-3">
                Promociones
                <hr class="dropdown-divider">
            </h3>
            <div id="carouselConsolas" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="row row-cols-1 row-cols-md-3 g-4 mb-3">
                            <%--Un productos--%>
                            <c:forEach var="promocion" items="${requestScope.promociones1}">
                            <div class="col">
                                <div class="card h-100 pt-2">
                                    <a href="${pageContext.request.contextPath}/producto?code=${promocion.codigo}" class="text-center">
                                        <img src="${promocion.imagenRuta}" class="card-img-top" alt="${promocion.nombre.replaceAll(" ", "-")}" style="max-width: 250px">
                                    </a>
                                    <div class="card-body">
                                        <h6 class="card-title text-muted mb-3 text-uppercase">
                                            ${promocion.plataforma.nombre}
                                        </h6>
                                        <p class="card-text">
                                            ${promocion.nombre}
                                        </p>
                                        <p class="text-danger fw-bold">
                                            <fmt:formatNumber value="${promocion.precio}" type="currency"/>
                                        </p>
                                    </div>
                                    <div class="card-footer text-center">
                                        <a href="${pageContext.request.contextPath}/carrito?code=${promocion.codigo}" class="btn btn-outline-success">
                                            Agregar al carrito
                                        </a>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                            <%--Un productos--%>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="row row-cols-1 row-cols-md-3 g-4 mb-3">
                            <%--Un productos--%>
                            <c:forEach var="promocion" items="${requestScope.promociones2}">
                                <div class="col">
                                    <div class="card h-100 pt-2">
                                        <a href="${pageContext.request.contextPath}/producto?code=${promocion.codigo}" class="text-center">
                                            <img src="${promocion.imagenRuta}" class="card-img-top" alt="${promocion.nombre.replaceAll(" ", "-")}" style="max-width: 250px">
                                        </a>
                                        <div class="card-body">
                                            <h6 class="card-title text-muted mb-3 text-uppercase">
                                                    ${promocion.plataforma.nombre}
                                            </h6>
                                            <p class="card-text">
                                                    ${promocion.nombre}
                                            </p>
                                            <p class="text-danger fw-bold">
                                                <fmt:formatNumber value="${promocion.precio}" type="currency"/>
                                            </p>
                                        </div>
                                        <div class="card-footer text-center">
                                            <a href="${pageContext.request.contextPath}/carrito?code=${promocion.codigo}" class="btn btn-outline-success">
                                                Agregar al carrito
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--Un productos--%>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev btn btn-secondary" type="button" data-bs-target="#carouselConsolas" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next btn btn-secondary" type="button" data-bs-target="#carouselConsolas" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </div>

    <!-- Probando esta imagen al medio-->
    <div class="row">
        <div class="col text-center">
            <img src="assets/image/consolas-mandos.webp" alt="consolas-mandos" class="img-fluid mx-auto mb-3">
        </div>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>
</body>
</html>
