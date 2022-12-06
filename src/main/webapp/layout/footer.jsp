<%@page contentType="text/html;charset=UTF-8"%>
<footer class="footer mt-auto pt-5 mt-3" style="background: #2c2a35">
    <div class="container-md text-light">

        <div class="row">
            <div class="col-sm-4 pe-5 py-3">
                <h5>
                    SOBRE <span class="text-danger">EXPRESS GAME</span>
                </h5>
                <p class="card-text">
                    Somos una micro empresa dedicada a la comercializacion de productos de entretenimiento, de capital 100% peruano, con los objetivos y propositos claros a convertirnos en una marca lider en la industria del entretenimiento brindado un servicio experiencias positivas de nuestros clientes.
                </p>
            </div>
            <div class="col-sm-4 py-3">
                <h5>ENLACES <span class="text-danger">IMPORTANTES</span></h5>
                <ul class="nav flex-column">
                    <li class="nav-item mb-2"><a href="${pageContext.request.contextPath}/express.game" class="nav-link p-0 text-light">Home</a></li>
                    <li class="nav-item mb-2"><a href="${pageContext.request.contextPath}/gaming?categoria=0&search=" class="nav-link p-0 text-light">Productos</a></li>
                    <li class="nav-item mb-2"><a href="${pageContext.request.contextPath}/gaming?cat=1" class="nav-link p-0 text-light">Videojuegos</a></li>
                    <li class="nav-item mb-2"><a href="${pageContext.request.contextPath}/gaming?cat=2" class="nav-link p-0 text-light">Consolas</a></li>
                    <li class="nav-item mb-2"><a href="${pageContext.request.contextPath}/gaming?cat=3" class="nav-link p-0 text-light">Mandos</a></li>
                </ul>
            </div>
            <div class="col-sm-4 pt-3">
                <h5><span class="text-danger">INFORMACIÓN</span></h5>
                <ul class="nav flex-column">
                    <li class="nav-item mb-2"><a href="https://www.youtube.com/watch?v=amDMZ5tSysY&t=358s" target="_blank" class="nav-link p-0 text-light">Integrantes</a></li>
                    <li class="nav-item mb-2"><a href="https://docs.google.com/document/d/1V0oWNJIVZ6tPjEP-atOKJ8Z7IJNCINqV/edit" target="_blank" class="nav-link p-0 text-light">Informe</a></li>
                </ul>
            </div>
        </div>

        <div class="row">
            <div class="d-flex justify-content-between py-4 my-4 border-top">
                <p>&copy; 2021 Company, Inc. Todos los derechos reservados</p>
                <ul class="list-unstyled d-flex">
                    <li class="ms-3"><a class="link-dark" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#twitter"></use></svg></a></li>
                    <li class="ms-3"><a class="link-dark" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#instagram"></use></svg></a></li>
                    <li class="ms-3"><a class="link-dark" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#facebook"></use></svg></a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>
