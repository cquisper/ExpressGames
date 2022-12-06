<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/sweetalert2.min.css">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/main.css">
    <jsp:include page="layout/config.jsp"/>
    <title>${requestScope.title}</title>
</head>
<body>
<!-- contenedor-->
<div class="contenedor_fondo">
    <div class="contenedor">
        <div class="login-title">
            <h1> <a href="${pageContext.request.contextPath}/express.game">${applicationScope.titleApp}</a> </h1>
        </div>
        <!-- login/sing up -->
        <div class="contenedor_login">
            <nav class="barra_login">
                <%-- Entra a la siguiente seccion si se trata de un login --%>
                <c:if test='${not Boolean.valueOf(dataPaso.get("register"))}'>
                    <button type="button" data-paso="${dataPaso.get("data-paso-1")}" class="seleccionado">Sing in </button>
                    <button type="button" data-paso="${dataPaso.get("data-paso-2")}" class="">Sing Up </button>
                </c:if>
                <%-- Entra a la siguiente seccion si se trata de un login --%>
                <c:if test='${Boolean.valueOf(dataPaso.get("register"))}'>
                    <button type="button" data-paso="${dataPaso.get("data-paso-1")}" class="">Sing in </button>
                    <button type="button" data-paso="${dataPaso.get("data-paso-2")}" class="seleccionado">Sing Up </button>
                </c:if>
            </nav>
            <!-- ingresar -->
            <div id="paso-${dataPaso.get("data-paso-1")}" class="formularios">
                <form action ="${pageContext.request.contextPath}/login" id="ingresar_login" class="form-ingresar"  method ="post">
                    <div class="campo">
                        <label for="usario_email_login">Ingrese su usuario o e-mail</label>
                        <input type="text" name="usario_email" id="usario_email_login">
                    </div>
                    <div class="campo">
                        <label for="contraseña_login">Contraseña</label>
                        <input name="password-login" type="password" id="contraseña_login">
                    </div>
                    <input type="submit" value="ingresar" class="boton boton-login boton-azul">
                </form>
            </div>

            <div id="paso-${dataPaso.get("data-paso-2")}" class="formularios">
                <!-- loguear -->
                <form action ="${pageContext.request.contextPath}/register" id="registro_login" class="form-registro" method ="post">

                    <div class="campo_doble">

                        <div class="campo">
                            <label for="nombre">Nombres</label>
                            <input type="text" name="nombre" id="nombre" placeholder="Tu nombre">
                        </div>

                        <div class="campo">
                            <label for="apellidos">Apellidos</label>
                            <input type="text" name="apellido" id="apellidos" placeholder="Tus Apellidos">
                        </div>
                    </div>
                    <div class="campo_doble">
                        <div class="campo">
                            <label for="dni">DNI</label>
                            <input type="text" name="dni" id="dni" placeholder="Tu DNI">
                        </div>
                    </div>
                    <!-- radiobutons -->
                    <div class="campo">
                        <label for="usuario">Nombre de usuario</label>
                        <input type="text" name="username" id="usuario" placeholder="Tu nombre de usuario">
                    </div>
                    <div class="campo">
                        <label for="e-mail">E-mail:</label>
                        <input name="email" type="email" id="e-mail" placeholder="Tu e-mail">
                    </div>
                    <div class="campo">
                        <label for="password">Contraseña</label>
                        <input name="password-register" type="password" id="password">
                    </div>

                    <div class="campo">
                        <label for="confirmar_password">Confirmar Contraseña</label>
                        <input name="confirmar_password" type="password"  id="confirmar_password">
                    </div>

                    <input type="submit" value="crear_cuenta" class="boton boton-azul">
                </form>
            </div>
        </div>
    </div>
</div>
<script src="js/main.js"></script>
<script src="js/sweetalert2.all.min.js"></script>
</body>
</html>