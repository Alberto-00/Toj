<%@ page import="Model.Account.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Dati_utente.DatiUtente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="adminStyles" value="libraryAdmin,crmAdmin,adminGestioneClienti"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Dashboard"/>
        <jsp:param name="adminScripts" value="crm"/>
    </jsp:include>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>

    </script>
</head>

<body>
<%@include file="../partials/admin/dashboardNav.jsp"%>
<div class="col-2">
    <%@include file="../partials/admin/dashboardHeader.jsp"%>
    <main class="content">
        <table>
            <thead>
            <tr>
                <th>Email</th>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Data Nascita</th>
                <th>Telefono</th>
                <th>Via</th>
                <th>N°Civico</th>
                <th>CAP</th>
            </tr>
            </thead>
            <!--Inizio Tables -->
            <tbody>
            <%List<DatiUtente> accounts = (List<DatiUtente>) request.getAttribute("accounts");
                if(accounts.isEmpty()){%>
            <td>Nessun Dato Da mostrare</td>
            <%}
                for (DatiUtente o: accounts){%>
            <tr>
                <td data-head="Email"><%= o.getEmail()%></td>
                <td data-head="Nome"><%= o.getNome()%></td>
                <td data-head="Cognome"><%= o.getCognome()%></td>
                <td data-head="Data Nascita"><%= o.getDataDiNascita()%></td>
                <td data-head="Telefono"><%= o.getNumeroTelefonico()%></td>
                <td data-head="Via"><%= o.getVia()%></td>
                <td data-head="N°Civico"><%= o.getNumeroCivico()%></td>
                <td data-head="CAP"><%= o.getCAP()%></td>
            </tr>
            <%}%>
            </tbody>
        </table>
        <c:forEach var="page" begin="1" end="${pages}">
            <div class="item paginator">
                <a id="elem${page}" href="${pageContext.request.contextPath}/adminServlet/adminGestioneClienti?page=${page}">${page}</a>
            </div>
        </c:forEach>
        <!-- Fine Tables -->
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>