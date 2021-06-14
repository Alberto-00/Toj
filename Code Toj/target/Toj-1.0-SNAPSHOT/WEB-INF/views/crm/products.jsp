<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Dashboard"/>
        <jsp:param name="styles" value="crm,dashboard"/>
    </jsp:include>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp"%>
    <section class="content grid-y">
        <%@include file="../partials/crm/header.jsp"%>
        <div class="body grid-x justify-center">
            <table class="table product-table">
                <caption>Lista prodotti</caption>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Url</th>
                    <th>Nome</th>
                    <th>Prezzo</th>
                    <th>Peso</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td data-head=Id">1</td>
                    <td data-head="Url">url 1 </td>
                    <td data-head="prezzo">mela</td>
                    <td>123</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>url 1 </td>
                    <td>mela</td>
                    <td>123</td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html>
