<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="es">
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
    <link rel="apple-touch-icon" sizes="180x180" href="../assets/image/favicons/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="../assets/image/favicons/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="../assets/image/favicons/favicon-16x16.png">
    <link rel="manifest" href="../assets/image/favicons/site.webmanifest">
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
    <!---contenedor de la barra vertical-->
    <div class="container" data-layout="container">
        <!--Navbar vertical-->
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
                <div class="card-body position-relative">
                    <div class="row">
                        <div class="col-lg-8">
                            <c:choose>
                                <c:when test="${tipo eq 'cliente'}">
                                    <h5 class="mb-0" data-anchor="data-anchor">${title.contains("Insertar") ? "Insertar" : "Editar"} Cliente</h5>
                                </c:when>
                                <c:when test="${tipo eq 'administrador'}">
                                    <h5 class="mb-0" data-anchor="data-anchor">${title.contains("Insertar") ? "Insertar" : "Editar"} Administrador</h5>
                                </c:when>
                                <c:otherwise>
                                    <h5 class="mb-0" data-anchor="data-anchor">Sin resultados</h5>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="card-body bg-light">
                    <div class="tab-content">
                        <div class="tab-pane preview-tab-pane active" role="tabpanel"
                             aria-labelledby="tab-dom-d4ebf6c5-74b4-4308-8c64-cda718c9b324"
                             id="dom-d4ebf6c5-74b4-4308-8c64-cda718c9b324">
                            <!--a donde invibar la infor-->
                            <form action="${pageContext.request.contextPath}/formulario/${tipo}" class="formulario" id="formulario" method="post">
                                <div class="mb-3">
                                    <label class="form-label" for="basic-form-name">Nombre</label>
                                    <input class="form-control" value="${cliente.nombre}" id="basic-form-name" type="text" placeholder="Nombre" name="nombre">
                                </div>
                                <!--Muestra Error-->
                                <c:if test="${not empty errores.nombre}"><div class="mb-3 text-danger">${errores.nombre}</div></c:if>
                                <!--Pegar lo que me robe-->
                                <div class="mb-3">
                                    <label class="form-label" for="basic-form-apellido">Apellido</label>
                                    <input class="form-control" value="${cliente.apellido}" id="basic-form-apellido" type="text" placeholder="Apellido" name="apellido">
                                </div>
                                <!--Muestra Error-->
                                <c:if test="${not empty errores.apellido}"><div class="mb-3 text-danger">${errores.apellido}</div></c:if>

                                <div class="mb-3">
                                    <label class="form-label" for="basic-form-dni">DNI</label>
                                    <input class="form-control" id="basic-form-dni" type="text" placeholder="DNI" maxlength="8"
                                           value="${cliente.dni}" name="dni">
                                </div>
                                <c:if test="${not empty errores.dni}"><div class="mb-3 text-danger">${errores.dni}</div></c:if>
                                <!--username-->
                                <div class="mb-3">
                                    <label class="form-label" for="basic-form-username">Username</label>
                                    <input class="form-control" id="basic-form-username" type="text" placeholder="Username"
                                           value="${cliente.usuario.username}" name="username">
                                </div>
                                <c:if test="${not empty errores.username}"><div class="mb-3 text-danger">${errores.username}</div></c:if>

                                <!--Email-->

                                <div class="mb-3">
                                    <label class="form-label" for="basic-form-email">E-Mail</label>
                                    <input class="form-control" id="basic-form-email" type="text" placeholder="E-mail"
                                           value="${cliente.usuario.email}" name="email">
                                </div>
                                <c:if test="${not empty errores.email}"><div class="mb-3 text-danger">${errores.email}</div></c:if>
                                <!--Contraseña-->
                                <div class="mb-3">
                                    <label class="form-label" for="basic-form-password">Password</label>
                                    <input class="form-control" id="basic-form-password" type="text" placeholder="Password"
                                           value="${cliente.usuario.password}" name="password">
                                </div>
                                <c:if test="${not empty errores.password}"><div class="mb-3 text-danger">${errores.password}</div></c:if>
                                <div class="mb-3">
                                    <label class="form-label" for="basic-form-rol">Rol</label>
                                    <select class="form-select" id="basic-form-rol"
                                            aria-label="Default select example" name="rol">
                                        <option value="1">Usuario</option>
                                        <option value="2">Admin</option>
                                    </select>
                                </div>
                                <c:if test="${not empty errores.rol}"><div class="mb-3 text-danger">${errores.rol}</div></c:if>
                                <!--boton para enviar al servidor -->
                                <button class="btn btn-primary mt-5" type="submit">Enviar</button>
                                <input type="hidden" name="id-cliente" value="${cliente.id}">
                                <input type="hidden" name="id-usuario" value="${cliente.usuario.id}">
                            </form>
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

