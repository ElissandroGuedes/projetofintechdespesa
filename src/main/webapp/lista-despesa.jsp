<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fintech</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <div class="mt-5 ms-5 me-5">

        <div class="card mb-3">
            <div class="card-header">
                MEUS GASTOS
            </div>
            <div class="card-body">
                <h5 class="card-title">Gestão das minhas despesas</h5>
                <p class="card-text">Estabeleça uma meta de gastos por categoria e controle tudo de forma clara e rápida</p>
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Descrição</th>
                        <th class="text-end">valor</th>
                        <th class="text-end">Data da despesa</th>
                        <th class="text-center">categoria</th>
                        <th>Credores</th>
                        <th class="text-center"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${despesas}" var="despesa">
                        <tr>
                            <td>${despesa.descricao}</td>
                            <td>${despesa.valor}</td>
                            <td>
                                <fmt:parseDate value="${despesa.dataDespesa}"
                                pattern="yyyy-MM-dd"
                                var="dtDespesa"/>
                                <fmt:formatDate value="${dtDespesa}" pattern="dd/MM/yyyyy"/>
                            </td>
                            <td>${despesa.categoria}</td>
                            <td>${despesa.credores.nome}</td>
                            <td class="text-center">
                                <c:url value="despesa" var="link">
                                    <c:param name="acao" value="abrir-form-edicao"/>
                                    <c:param name="codigo" value="${despesa.cdDespesa}"/>
                                </c:url>
                                <a href="${link}" class="btn btn-primary">Editar</a>

                                <!--Botão abrir modal -->

                                <button
                                        type="button"
                                        class="btn bg-danger"
                                        data-bs-toggle="modal"
                                        data-bs-target="#excluirModal"
                                        onclick="codigoExcluir.value = ${despesa.cdDespesa}"

                                >Excluir
                                </button>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="despesa?acao=abrir-form-cadastro" class="btn btn-primary">Adicionar Despesa</a>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="excluirModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmar a Exclusão</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h4>Você confirma a exclusão desta despesa?</h4>
                <p><strong>Atenção!</strong>Está é uma ação irreversível.</p>
            </div>
            <div class="modal-footer">

                <form action="despesa" method="post">
                    <input
                        type="hidden"
                        name="acao"
                        value="excluir">
                    <input
                        type="hidden"
                        name="codigoExcluir"
                        id="codigoExcluir">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Não</button>
                    <button type="submit" class="btn btn-danger">Sim</button>
                </form>

            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>