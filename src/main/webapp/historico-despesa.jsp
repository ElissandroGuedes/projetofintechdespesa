<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Historico de Despesa</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<%@include file="header.jsp" %>

<div class="container">
    <div class="mt-5 ms-5 me-5">

        <div class="card">
            <h5 class="card-header text-center">Histórico de Despesas</h5>
            <div class="card-body">
                <form method="post" action="despesa?acao=filtrar">
                    <label>Data inicial:</label>
                    <input type="date" name="inicio">
                    <label>Data final:</label>
                    <input type="date" name="fim">
                    <input class="btn btn-primary" type="submit" value="Filtrar">
                </form>

                <table class="table table-striped table-hover">
                    <tr class="table-primary">
                        <th scope="row">Data</th>
                        <th scope="row">Categoria</th>
                        <th scope="row">Descrição</th>
                        <th scope="row">Valor</th>
                        <th scope="row">Credor</th>
                    </tr>
                    <c:forEach items="${listaDespesas}" var="despesa" >
                        <tr class="table-primary">
                            <td class="table-primary"><fmt:formatDate value="${despesa.dataDespesaAsDate}" pattern="dd/MM/yyyy"/></td>
                            <td class="table-primary">${despesa.categoria}</td>
                            <td class="table-primary">${despesa.descricao}</td>
                            <td class="table-primary">${despesa.valor}</td>
                            <td class="table-primary">${despesa.credores.nome}</td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${empty listaDespesas}">
                    <p class="text-center text-muted mt-3">Nenhuma despesa encontrada no período selecionado.</p>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger mt-3" role="alert">
                            ${errorMessage}
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>


<%@include file="footer.jsp" %>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>