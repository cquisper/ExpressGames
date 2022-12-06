<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_PE"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <script src="https://kit.fontawesome.com/60453d73a9.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,500,600,700%7cPoppins:300,400,500,600,700,800,900&amp;display=swap" rel="stylesheet">
    <link href="../css/OverlayScrollbars.min.css" rel="stylesheet">
    <link href="../css/theme-rtl.min.css" rel="stylesheet" id="style-rtl">
    <link href="../css/theme.min.css" rel="stylesheet" id="style-default">
    <link href="../css/user-rtl.min.css" rel="stylesheet" id="user-style-rtl">
    <link href="../css/user.min.css" rel="stylesheet" id="user-style-default">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Material+Icons+Outlined">
    <link rel="apple-touch-icon" sizes="180x180" href="${(requestScope.cambiarDireccion eq "true") ? "../" : ""}assets/image/favicons/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${(requestScope.cambiarDireccion eq "true") ? "../" : ""}assets/image/favicons/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${(requestScope.cambiarDireccion eq "true") ? "../" : ""}assets/image/favicons/favicon-16x16.png">
    <link rel="manifest" href="${(requestScope.cambiarDireccion eq "true") ? "../" : ""}assets/image/favicons/site.webmanifest">
    <script>
        var isRTL = JSON.parse(localStorage.getItem('isRTL'));
        if (isRTL) {
            var linkDefault = document.getElementById('style-default');
            var userLinkDefault = document.getElementById('user-style-default');
            linkDefault.setAttribute('disabled', true);
            userLinkDefault.setAttribute('disabled', true);
            document.querySelector('html').setAttribute('dir', 'rtl');
        } else {
            var linkRTL = document.getElementById('style-rtl');
            var userLinkRTL = document.getElementById('user-style-rtl');
            linkRTL.setAttribute('disabled', true);
            userLinkRTL.setAttribute('disabled', true);
        }
    </script>
    <title>${requestScope.title}</title>
</head>
<body>
<!--Estructura inicial para separar -->
<main class="main" id="top">

    <div class="container" data-layout="container">
        <!---inicio de la barra vertical-->
        <script>
            var isFluid = JSON.parse(localStorage.getItem('isFluid'));
            if (isFluid) {
                var container = document.querySelector('[data-layout]');
                container.classList.remove('container');
                container.classList.add('container-fluid');
            }
        </script>
        <nav class="navbar navbar-light navbar-vertical navbar-expand-xl">
            <script>
                var navbarStyle = localStorage.getItem("navbarStyle");
                if (navbarStyle && navbarStyle !== 'transparent') {
                    document.querySelector('.navbar-vertical').classList.add(`navbar-${navbarStyle}`);
                }
            </script>
            <div class="d-flex align-items-center">
                <div class="toggle-icon-wrapper">

                    <button class="btn navbar-toggler-humburger-icon navbar-vertical-toggle" data-bs-toggle="tooltip"
                            data-bs-placement="left" title="" data-bs-original-title="Toggle Navigation"
                            aria-label="Toggle Navigation"><span class="navbar-toggle-icon"><span
                            class="toggle-line"></span></span></button>

                </div>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/express.controller">
                    <div class="d-flex align-items-center py-3">
                        <img class="me-2" src="../assets/image/favicons/android-chrome-512x512.png" alt="" width="40"/>
                        <span class="font-sans-serif" style="font-size: 1.5rem">Controller</span>
                    </div>
                </a>
            </div>
            <div class="navbar-collapse collapse show" id="navbarVerticalCollapse" style="">
                <div class="navbar-vertical-content scrollbar">
                    <ul class="navbar-nav flex-column mb-3" id="navbarVerticalNav">
                        <li class="nav-item">
                            <!-- label -> titulo -->
                            <div class="row navbar-vertical-label-wrapper mt-3 mb-2">
                                <div class="col-auto navbar-vertical-label">
                                    Tablas
                                </div>
                                <div class="col ps-0">
                                    <hr class="mb-0 navbar-vertical-divider">
                                </div>
                            </div>
                            <div class="componentes">
                                <!-- parent pages-->
                                <a class="nav-link dropdown-indicator" href="#tables" role="button" data-bs-toggle="collapse" aria-expanded="false" aria-controls="tables">
                                    <div class="d-flex align-items-center">
                                <span class="nav-link-icon">
                                    <svg class="svg-inline--fa fa-table fa-w-16" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="table" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M464 32H48C21.49 32 0 53.49 0 80v352c0 26.51 21.49 48 48 48h416c26.51 0 48-21.49 48-48V80c0-26.51-21.49-48-48-48zM224 416H64v-96h160v96zm0-160H64v-96h160v96zm224 160H288v-96h160v96zm0-160H288v-96h160v96z"></path>
                                    </svg>
                                    <!-- <span class="fas fa-table"></span> Font Awesome fontawesome.com -->
                                </span>
                                        <span class="nav-link-text ps-1">
                                    Personal
                                </span>
                                    </div>
                                </a>
                                <!-- componente - tbala  -->
                                <ul class="nav collapse false tabla-columna-collapse" id="tables">
                                    <!--! cliente tabla  -->
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/tabla/clientes?idRol=1" aria-expanded="false">
                                            <div class="d-flex align-items-center tabla-cliente-enlace ">
                                        <span class="nav-link-text ps-1">
                                            Clientes
                                        </span>
                                            </div>
                                        </a>
                                    </li>
                                    <!--! administradores  -->
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/tabla/administradores?idRol=2" aria-expanded="false">
                                            <div class="d-flex align-items-center">
                                        <span class="nav-link-text ps-1">
                                            Administradores
                                        </span>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                                <!--Productos-->
                                <a class="nav-link dropdown-indicator" href="#productos" role="button" data-bs-toggle="collapse" aria-expanded="false" aria-controls="tables">
                                    <div class="d-flex align-items-center">
                                <span class="nav-link-icon">
                                    <svg class="svg-inline--fa fa-table fa-w-16" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="table" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M464 32H48C21.49 32 0 53.49 0 80v352c0 26.51 21.49 48 48 48h416c26.51 0 48-21.49 48-48V80c0-26.51-21.49-48-48-48zM224 416H64v-96h160v96zm0-160H64v-96h160v96zm224 160H288v-96h160v96zm0-160H288v-96h160v96z"></path>
                                    </svg>
                                    <!-- <span class="fas fa-table"></span> Font Awesome fontawesome.com -->
                                </span>
                                        <span class="nav-link-text ps-1">
                                    Productos
                                </span>
                                    </div>
                                </a>
                                <!-- Productos - tbala  -->
                                <ul class="nav collapse false tabla-columna-collapse" id="productos">
                                    <!--! Videojuegos  -->
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/tabla/videojuegos?cat=1" aria-expanded="false">
                                            <div class="d-flex align-items-center tabla-cliente-enlace ">
                                        <span class="nav-link-text ps-1">
                                            Videojuegos
                                        </span>
                                            </div>
                                        </a>
                                    </li>
                                    <!--! Consolas  -->
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/tabla/consolas?cat=2" aria-expanded="false">
                                            <div class="d-flex align-items-center">
                                        <span class="nav-link-text ps-1">
                                            Consolas
                                        </span>
                                            </div>
                                        </a>
                                    </li>
                                    <!--! Mandos  -->
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/tabla/mandos?cat=3" aria-expanded="false">
                                            <div class="d-flex align-items-center">
                                        <span class="nav-link-text ps-1">
                                            Mandos
                                        </span>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <%--fin Barra vertical   --%>

        <div class="content">
            <!--inicio barra horizontal-->
            <nav class="navbar navbar-light navbar-glass navbar-top navbar-expand">
                <button class="btn navbar-toggler-humburger-icon navbar-toggler me-1 me-sm-3" type="button"
                        data-bs-toggle="collapse" data-bs-target="#navbarVerticalCollapse"
                        aria-controls="navbarVerticalCollapse"
                        aria-expanded="false" aria-label="Toggle Navigation"><span class="navbar-toggle-icon"><span
                        class="toggle-line"></span></span></button>
                <a class="navbar-brand me-1 me-sm-3" href="${pageContext.request.contextPath}/express.controller">
                    <div class="d-flex align-items-center"><img class="me-2"
                                                                src="../assets/image/favicons/android-chrome-512x512.png"
                                                                alt="" width="40"/><span
                            class="font-sans-serif">Controller</span>
                    </div>
                </a>
                <ul class="navbar-nav navbar-nav-icons ms-auto flex-row align-items-center">
                    <li class="nav-item">
                        <div class="theme-control-toggle fa-icon-wait px-2">
                            <input class="form-check-input ms-0 theme-control-toggle-input" id="themeControlToggle"
                                   type="checkbox"
                                   data-theme-control="theme" value="dark"/>
                            <label class="mb-0 theme-control-toggle-label theme-control-toggle-light"
                                   for="themeControlToggle"
                                   data-bs-toggle="tooltip" data-bs-placement="left" title="Switch to light theme"><span
                                    class="fas fa-sun fs-0"></span></label>
                            <label class="mb-0 theme-control-toggle-label theme-control-toggle-dark"
                                   for="themeControlToggle"
                                   data-bs-toggle="tooltip" data-bs-placement="left" title="Switch to dark theme"><span
                                    class="fas fa-moon fs-0"></span></label>
                        </div>
                    </li>
                    <li class="nav-item dropdown"><a class="nav-link pe-0" id="navbarDropdownUser" href="#"
                                                     role="button"
                                                     data-bs-toggle="dropdown" aria-haspopup="true"
                                                     aria-expanded="false">
                        <div class="w-100">
                            <span class="material-icons-outlined align-middle me-1">
                                account_circle
                            </span>
                            <!--
                            <img class="rounded-circle"
                                 src="../assets/img/chica.jpg"
                                 alt="photo-profile"/>
                                 -->

                        </div>
                    </a>
                        <div class="dropdown-menu dropdown-menu-end py-0" aria-labelledby="navbarDropdownUser">
                            <div class="bg-white dark__bg-1000 rounded-2 py-2">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </nav>
            <!--fin barra horizontal-->

            <!--tabla -->
            <div class="container card mb-3 p-3 mt-3">
                <div class="card-header">
                    <div class="row flex-grow-0">
                        <div class="col-auto align-self-center">
                            <c:choose>
                                <c:when test="${requestScope.tipo eq 'videojuegos'}">
                                <h5 class="mb-0" data-anchor="data-anchor">Videojuegos</h5>
                                    <c:set var="subCategoria" value="Género"/>
                                    <c:set var="tipo" value="videojuego"/>
                                </c:when>
                                <c:when test="${requestScope.tipo eq 'consolas'}">
                                <h5 class="mb-0" data-anchor="data-anchor">Consolas</h5>
                                    <c:set var="subCategoria" value="Marca"/>
                                    <c:set var="tipo" value="consola"/>
                                </c:when>
                                <c:when test="${requestScope.tipo eq 'mandos'}">
                                <h5 class="mb-0" data-anchor="data-anchor">Mandos</h5>
                                    <c:set var="subCategoria" value="Marca"/>
                                    <c:set var="tipo" value="mando"/>
                                </c:when>
                                <c:otherwise>
                                <h5 class="mb-0" data-anchor="data-anchor">Sin resultados</h5>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-auto align-self-center">
                            <a href="${pageContext.request.contextPath}/formulario/${tipo}" class="avatar-button text-github"><i class="fa-solid fa-plus pe-2"></i>Insertar nuevo ${tipo}</a>
                        </div>
                    </div>
                </div>
                <div class="card-body pt-0">
                    <div class="tab-content">
                        <div class="tab-pane preview-tab-pane active" role="tabpanel"
                             aria-labelledby="tab-dom-55d552bf-cdbd-40f9-856d-410188578fda" id="dom-55d552bf-cdbd-40f9-856d-410188578fda">
                            <div id="tableExample2" data-list='{"valueNames":["ID","Nombre","Precio", "Codigo", "SubCategoria", "Categoria", "Plataforma", "Empresa"],"page":10,"pagination":true}'>
                                <div class="table-responsive scrollbar">
                                    <table class="table table-bordered table-striped fs--1 mb-0">
                                        <thead class="bg-200 text-900">
                                        <tr>
                                            <th class="sort" data-sort="id">ID</th>
                                            <th class="sort" data-sort="nombre">Nombre</th>
                                            <th class="sort" data-sort="precio">Precio</th>
                                            <th class="sort" data-sort="codigo">Codigo</th>
                                            <th class="sort" data-sort="subCategoria">${subCategoria}</th>
                                            <th class="sort" data-sort="categoria">Categoria</th>
                                            <th class="sort" data-sort="plataforma">Plataforma</th>
                                            <th class="sort" data-sort="empresa">Empresa</th>
                                            <th class="sort" data-sort="opciones">Opciones</th>
                                        </tr>
                                        </thead>
                                        <tbody class="list">
                                        <!--fila por cada usuario -->
                                        <c:forEach var="producto" items="${requestScope.productos}">
                                            <tr>
                                                <td class="id">${producto.id}</td>
                                                <td class="nombre">${producto.nombre}</td>
                                                <td class="precio"><fmt:formatNumber value="${producto.precio}" type="currency"/></td>
                                                <td class="codigo">${producto.codigo}</td>
                                                <td class="subCategoria">${producto.subCategoria.nombre}</td>
                                                <td class="categoria">${producto.subCategoria.categoria.nombre}</td>
                                                <td class="plataforma">${producto.plataforma.nombre}</td>
                                                <td class="empresa">${producto.plataforma.empresa.nombre}</td>
                                                <!--!opcoiones por usuarios-->
                                                <td class="text-end boton-opbiones">
                                                    <div class="dropdown text-center font-sans-serif position-static">
                                                        <button class="btn btn-link text-600 btn-sm dropdown-toggle btn-reveal" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false"><svg class="svg-inline--fa fa-ellipsis-h fa-w-16 fs--1" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="ellipsis-h" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M328 256c0 39.8-32.2 72-72 72s-72-32.2-72-72 32.2-72 72-72 72 32.2 72 72zm104-72c-39.8 0-72 32.2-72 72s32.2 72 72 72 72-32.2 72-72-32.2-72-72-72zm-352 0c-39.8 0-72 32.2-72 72s32.2 72 72 72 72-32.2 72-72-32.2-72-72-72z"></path></svg><!-- <span class="fas fa-ellipsis-h fs--1"></span> Font Awesome fontawesome.com --></button>
                                                        <div class="dropdown-menu dropdown-menu-end border py-0" style="">
                                                            <div class="bg-white py-2">
                                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/formulario/${tipo}?codEdit=${producto.codigo}">
                                                                    Editar
                                                                </a>
                                                                <a class="dropdown-item text-danger"
                                                                   onclick="return confirm('¿Esta seguro que desea eliminar el producto, la acción es irreversible?')"
                                                                   href="${pageContext.request.contextPath}/eliminar/${tipo}?idDelete=${producto.id}">
                                                                    Eliminar
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="d-flex justify-content-center mt-3">
                                    <button class="btn btn-sm btn-falcon-default me-1" type="button" title="Previous" data-list-pagination="prev"><span class="fas fa-chevron-left"></span></button>
                                    <ul class="pagination mb-0"></ul>
                                    <button class="btn btn-sm btn-falcon-default ms-1" type="button" title="Next" data-list-pagination="next"><span class="fas fa-chevron-right"> </span></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer container-fluid bg-light text-center p-3">
                <div class="row g-0 justify-content-between fs--1 mt-4 mb-3">
                    <div class="col-12 col-sm-auto text-center">
                        <p class="mb-0 text-600">Controller | Express Game <span class="d-none d-sm-inline-block">|
                </span><br class="d-sm-none" /> 2022 &copy; <a href="https://themewagon.com">Express Company</a></p>
                    </div>
                </div>
            </footer>
            <!-- ===============================================-->
            <!--    JavaScripts-->
            <!-- ===============================================-->
            <script src="../js/popper.min.js"></script>
            <script src="../js/bootstrap.min.js"></script>
            <script src="../js/anchor.min.js"></script>
            <script src="../js/is.min.js"></script>
            <script src="../js/echarts.min.js"></script>
            <script src="../js/all.min.js"></script>
            <script src="../js/lodash.min.js"></script>
            <script src="https://polyfill.io/v3/polyfill.min.js?features=window.scroll"></script>
            <script src="../js/list.min.js"></script>
            <script src="../js/theme.js"></script>
        </div>
    </div>
</main>
</body>
</html>