<%@page contentType="text/html;charset=UTF-8"%>
<div class="col-md-4">
    <div class="card border-light mb-3" style="max-width: 22rem;">
        <div class="card-header">
            <h4>Videojuegos</h4>
        </div>
        <div class="card-body">
            <div class="accordion accordion-flush" id="accordionFlushExample">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingOne">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                            Categoria
                        </button>
                    </h2>
                    <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                            <nav class="nav flex-column">
                                <a class="nav-link text-secondary" aria-current="page" href="${pageContext.request.contextPath}/gaming?cat=1">
                                    Juegos
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?cat=2">
                                    Consolas
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?cat=3">
                                    Mandos
                                </a>
                            </nav>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingTwo">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                            Marca
                        </button>
                    </h2>
                    <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                            <nav class="nav flex-column">
                                <a class="nav-link text-secondary" aria-current="page" href="${pageContext.request.contextPath}/gaming?plat=2">
                                    PlayStation
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?plat=1">
                                    Nintendo
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?plat=3">
                                    Xbox
                                </a>
                            </nav>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingThree">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                            Género
                        </button>
                    </h2>
                    <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">
                            <nav class="nav flex-column">
                                <a class="nav-link text-secondary" aria-current="page" href="${pageContext.request.contextPath}/gaming?scat=1">
                                    Thriller
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?scat=2">
                                    Deporte
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?scat=3">
                                    Musical
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?scat=4">
                                    Suspenso
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?scat=5">
                                    Acción
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?scat=6">
                                    Aventura
                                </a>
                                <a class="nav-link text-secondary" href="${pageContext.request.contextPath}/gaming?scat=15">
                                    Carrera
                                </a>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="card border-light mb-3" style="max-width: 22rem;">
        <div class="card-header">
            <h4>Precio</h4>
        </div>
        <div class="card-body">
            <div class="accordion accordion-flush" id="accordionFlushExample2">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingOne2">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne2" aria-expanded="false" aria-controls="flush-collapseOne2">
                            Filtro por precio
                        </button>
                    </h2>
                    <div id="flush-collapseOne2" class="accordion-collapse collapse" aria-labelledby="flush-headingOne2" data-bs-parent="#accordionFlushExample2">
                        <div class="accordion-body px-0">
                            <form action="${pageContext.request.contextPath}/gaming" method="get">
                                <div class="row gx-3 gy-2 align-items-center">
                                    <div class="col">
                                        <div class="input-group">
                                            <span class="input-group-text" id="basic-addon1">S/</span>
                                            <input type="text" name="min" value="${pageContext.request.getParameter("min")}" minlength="1" min="0" max="9999" maxlength="4" class="form-control" placeholder="29" aria-describedby="basic-addon1">
                                        </div>
                                    </div>
                                    <div class="col-sm-3 align-middle" style="max-width: 30px">
                                        <p class="text-dark fw-bold align-middle m-0" style="max-width: 30px">a</p>
                                    </div>
                                    <div class="col">
                                        <div class="input-group">
                                            <span class="input-group-text" id="basic-addon2">S/</span>
                                            <input type="text" name="max" value="${pageContext.request.getParameter("max")}" minlength="1" min="0" max="9999" maxlength="4" class="form-control" placeholder="1532" aria-describedby="basic-addon2">
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <div class="input-group">
                                            <button type="submit" class="btn btn-light">
                                                <i class="fa-solid fa-arrow-right-long align-middle fs-4"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>