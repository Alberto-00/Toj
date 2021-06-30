<%@ page import="java.util.List" %>
<%@ page import="Model.Dati_utente.DatiUtente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <jsp:include page="/WEB-INF/views/partials/admin/head.jsp">
        <jsp:param name="adminStyles" value="crmAdmin,table,adminGestioneClienti"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Dashboard"/>
        <jsp:param name="adminScripts" value="crm"/>
    </jsp:include>
</head>

<body>
<%@include file="../partials/admin/dashboardNav.jsp"%>
<div class="col-2">
    <%@include file="../partials/admin/dashboardHeader.jsp"%>
    <main class="content table-responsive">
        <table>
            <thead>
            <tr>
                <th>Email</th>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Data Nascita</th>
                <th>Telefono</th>
                <th>Indirizzo</th>
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
                <td data-head="Email"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getNome() != null){%>
                <td data-head="Nome"><%= o.getNome()%></td>
                <%} else {%>
                <td data-head="Nome"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getCognome() != null){%>
                <td data-head="Cognome"><%= o.getCognome()%></td>
                <%} else {%>
                <td data-head="Cognome"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getDataDiNascita() != null){%>
                <td data-head="Data Nascita"><%= o.getDataDiNascita()%></td>
                <%} else {%>
                <td data-head="Data Nascita"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getNumeroTelefonico() != null){%>
                <td data-head="Telefono"><%= o.getNumeroTelefonico()%></td>
                <%} else {%>
                <td data-head="Telefono"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getVia() != null){%>
                <td data-head="Via"><%=o.getVia()%></td>
                <%} else {%>
                <td data-head="Via"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getCAP() != null){%>
                <td data-head="CAP"><%=o.getCAP()%></td>
                <%} else {%>
                <td data-head="CAP"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getCity() != null){%>
                <td data-head="Città"><%=o.getCity()%></td>
                <%} else {%>
                <td data-head="Città"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getPaese() != null){%>
                <td data-head="Paese"><%= o.getPaese()%></td>
                <%} else {%>
                <td data-head="Paese"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getAppartamento() != null){%>
                <td data-head="Appartamento"><%= o.getAppartamento()%></td>
                <%} else {%>
                <td data-head="Appartamento"><span class="empty">** non presente **</span></td>
                <%}%>
            </tr>
            <%}%>
            </tbody>
        </table>
        <jsp:include page="/WEB-INF/views/partials/admin/pagination.jsp">
            <jsp:param name="servlet" value="adminGestioneClienti"/>
        </jsp:include>
        <!-- Fine Tables -->
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>