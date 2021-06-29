<%@ page import="java.util.List" %>
<%@ page import="Model.Dati_utente.DatiUtente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="adminStyles" value="libraryAdmin,crmAdmin,adminGestioneClienti"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Dashboard"/>
        <jsp:param name="adminScripts" value="crm"/>
    </jsp:include>
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
                <th>CAP</th>
                <th>Città</th>
                <th>Paese</th>
                <th>Appartamento</th>
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
                <%if(o.getUser().getEmail() != null){%>
                <td data-head="Email"><%= o.getUser().getEmail()%></td>
                <%} else {%>
                <td data-head="Email">** non presente **</td>
                <%}%>
                <%if(o.getNome() != null){%>
                <td data-head="Nome"><%= o.getNome()%></td>
                <%} else {%>
                <td data-head="Nome">** non presente **</td>
                <%}%>
                <%if(o.getCognome() != null){%>
                <td data-head="Cognome"><%= o.getCognome()%></td>
                <%} else {%>
                <td data-head="Cognome">** non presente **</td>
                <%}%>
                <%if(o.getDataDiNascita() != null){%>
                <td data-head="Data Nascita"><%= o.getDataDiNascita()%></td>
                <%} else {%>
                <td data-head="Data Nascita">** non presente **</td>
                <%}%>
                <%if(o.getNumeroTelefonico() != null){%>
                <td data-head="Telefono"><%= o.getNumeroTelefonico()%></td>
                <%} else {%>
                <td data-head="Telefono">** non presente **</td>
                <%}%>
                <%if(o.getVia() != null){%>
                <td data-head="Via"><%=o.getVia()%></td>
                <%} else {%>
                <td data-head="Via">** non presente **</td>
                <%}%>
                <%if(o.getCAP() != null){%>
                <td data-head="CAP"><%=o.getCAP()%></td>
                <%} else {%>
                <td data-head="CAP">** non presente **</td>
                <%}%>
                <%if(o.getCity() != null){%>
                <td data-head="Città"><%=o.getCity()%></td>
                <%} else {%>
                <td data-head="Città">** non presente **</td>
                <%}%>
                <%if(o.getPaese() != null){%>
                <td data-head="Paese"><%= o.getPaese()%></td>
                <%} else {%>
                <td data-head="Paese">** non presente **</td>
                <%}%>
                <%if(o.getAppartamento() != null){%>
                <td data-head="Appartamento"><%= o.getAppartamento()%></td>
                <%} else {%>
                <td data-head="Appartamento">** non presente **</td>
                <%}%>
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