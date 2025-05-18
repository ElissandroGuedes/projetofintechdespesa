<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Projeto Fintech</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="despesa?acao=abrir-form-cadastro">Cadastro</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="despesa?acao=listar">Despesa</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="historico-despesa.jsp">Historico</a>
                </li>
            </ul>


            <c:if test="${empty user}">

                <span class="navbar-text text-bg-danger" style="margin-right: 10px">
                    ${erro}
                </span>

                <form class="row g-3" action="login" method="post">
                    <div class="col-auto">
                        <label for="inputEmail2" class="visually-hidden">Email</label>
                        <input type="text" class="form-control" id="inptEmail2" placeholder="email@examplo.com" name="email">
                    </div>
                    <div class="col-auto">
                        <label for="inputPassword2" class="visually-hidden">Senha</label>
                        <input type="password" class="form-control" id="inputPassword2" placeholder="senha" name="senha">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-3">Entrar</button>
                    </div>
                </form>

            </c:if>
            <c:if test="${not empty user}">
                ${user}
                <a href="login" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
            </c:if>


        </div>
    </div>
</nav>