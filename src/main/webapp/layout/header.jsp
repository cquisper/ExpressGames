<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8"%>
<header class="header">
    <div class="rellenar_header bg-dark mb-4 py-1">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-md">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/express.game">
                    <h3 class="align-middle pt-2">
                        <img src="assets/image/favicons/android-chrome-192x192.png" alt="" width="40" height="40" class="d-inline-block align-text-top">
                        ${applicationScope.titleApp}
                    </h3>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <%--
                        <li class="nav-item dropdown">
                            <a class=" w-100 mt-sm-3 mt-lg-0  nav-link dropdown-toggle text-dark me-2  py-1 border text-center   bg-light "
                               href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                Categorías
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Videojuegos</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Consolas</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Mandos</a></li>
                            </ul>
                        </li>
                        --%>
                    </ul>
                    <!--Busqueda-->
                    <form class="d-flex w-100 mb-sm-3 mb-lg-0" action="${pageContext.request.contextPath}/gaming" method="get">
                        <select name="categoria" class="form-select rounded-0 rounded-start" aria-label="Default select example" style="max-width: 150px">
                            <option ${requestScope.categoria eq "0" or requestScope.categoria eq null ? "selected" : ""} value="0">Categorías</option>
                            <option ${requestScope.categoria eq "1" ? "selected" : ""} value="1">Videojuegos</option>
                            <option ${requestScope.categoria eq "2" ? "selected" : ""} value="2">Consolas</option>
                            <option ${requestScope.categoria eq "3" ? "selected" : ""} value="3">Mandos</option>
                        </select>
                        <input class="form-control rounded-0" name="search" type="search"
                               placeholder="Busca tu producto ideal..." aria-label="Search"
                               value='${not empty pageContext.request.getParameter("search") ? pageContext.request.getParameter("search")  : ""}'>
                        <button class="btn bg-primary rounded-0 rounded-end" type="submit">
                            <i class="fa-solid fa-magnifying-glass text-light"></i>
                        </button>
                    </form>

                    <div class="ps-2 text-light d-inline" style="max-width: 100px">
                      <span class="material-icons-outlined fs-1 align-middle me-1">
                        account_circle
                      </span>
                    </div>
                    <c:if test="${not empty sessionScope.usuario}">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-inline" style="max-width: 100px">
                            <li class="nav-item dropdown align-middle" style="max-width: 100px">
                                <a class="nav-link dropdown-toggle text-light" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    ${sessionScope.usuario.get().username}
                                </a>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                                    <c:if test="${not empty sessionScope.historialCompra}">
                                        <li>
                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/historial.jsp">Historial de Compras</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${sessionScope.usuario.get().rol.nombre eq 'administrador'}">
                                        <li>
                                            <a class="dropdown-item" target="_blank" href="${pageContext.request.contextPath}/express.controller">Express game Controller</a>
                                        </li>
                                    </c:if>
                                    <li>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${empty sessionScope.usuario}">
                        <div class="p-2 text-light d-inline" style="max-width: 210px">
                                <a href="${pageContext.request.contextPath}/login" class="text-light text-center d-flex text-decoration-none" style="min-width: 100px; max-width: 100px">
                                    Iniciar Sesión
                                </a>
                                <a href="${pageContext.request.contextPath}/register" class="text-light d-flex text-decoration-none" style="max-width: 100px">
                                    Registrate
                                </a>
                        </div>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/carro.jsp" class="text-decoration-none text-light px-1 rounded  mb-lg-0 ms-lg-2 mt-sm-1 mt-lg-0 " type="submit">
                        <div class="d-flex justify-content-around align-items-center">
                            <i class=" fa-solid fa-cart-shopping"></i>
                            <p id="cantidad_carrito"><span class=" mt-2 ms-1  badge bg-primary">${venta.cantidadProductos}</span></p>
                            <p class="ms-1 mt-2">Carrito</p>
                        </div>
                    </a>
                </div>
            </div>
        </nav>
    </div>
</header>



