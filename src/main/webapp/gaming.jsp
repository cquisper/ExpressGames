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
    <section id="lista-productos">
        <div class="container-md">
            <div class="row">
                <jsp:include page="layout/filtro.jsp"/>
                <div class="col">
                    <c:if test="${empty requestScope.productos}">
                        <div class="alert alert-secondary" role="alert">
                            Ups :( lo sentimos, no encontramos resultados para <strong class = 'text-success'>'${pageContext.request.getParameter("search") ne null ? pageContext.request.getParameter("search") : "su busqueda"}'</strong> <br>
                            <small class="text-muted">Tal vez su búsqueda fue demasiado específica, intente buscar con un término más general.</small>
                        </div>
                    </c:if>
                    <div class="row row-cols-1 row-cols-md-3 g-4 mb-3">
                        <c:forEach var="producto" items="${requestScope.productos}">
                            <div class="col">
                                <div class="card h-100 pt-2">
                                    <a href="${pageContext.request.contextPath}/producto?code=${producto.codigo}">
                                        <img src="${producto.imagenRuta}" class="card-img-top" alt="${producto.nombre.replaceAll(" ", "-")}">
                                    </a>
                                    <div class="card-body">
                                        <h6 class="card-title text-muted mb-3 text-uppercase">
                                            ${producto.plataforma.nombre}
                                        </h6>
                                        <p class="card-text">
                                            ${producto.nombre}
                                        </p>
                                        <p class="text-danger fw-bold">
                                            <fmt:formatNumber value="${producto.precio}" type="currency"/>
                                        </p>
                                    </div>
                                    <div class="card-footer text-center">
                                        <a href="${pageContext.request.contextPath}/carrito?code=${producto.codigo}" class="btn btn-outline-success">
                                            Agregar al carrito
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Paginacionnnnnnnn-->
                    <div class="row bg-light mb-4">
                        <div class="col">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination pagination-md pt-3">                          <%-- <= --%>
                                    <li class="page-item ms-2 ${Integer.parseInt(pageContext.request.getParameter("p")) le 1 ? 'disabled' : ''}">
                                        <a class="page-link" href="${pageContext.request.contextPath}/gaming?${requestScope.filter}&p=${Integer.parseInt(pageContext.request.getParameter("p")) - 1 }" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>

                                    <c:forEach var="pag" begin="1" end="${requestScope.pagination}">    <%-- == --%>
                                        <li class="page-item ms-2 ${pageContext.request.getParameter("p") eq pag ? 'active' : ''}">
                                            <a class="page-link" href="${pageContext.request.contextPath}/gaming?${requestScope.filter}&p=${pag}">${pag}</a>
                                        </li>
                                    </c:forEach>
                                    <%--                                                              >= --%>
                                    <li class="page-item ms-2 ${pageContext.request.getParameter("p") ge requestScope.pagination ? 'disabled' : ''}">
                                        <a class="page-link" href="${pageContext.request.contextPath}/gaming?${requestScope.filter}&p=${Integer.parseInt(pageContext.request.getParameter("p")) + 1 }" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="layout/footer.jsp"/>
</body>
</html>
