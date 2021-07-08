<%@ page import="java.util.List" %>
<%@ page import="Model.Dati_utente.DatiUtente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
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
                <%if(o.getUser().getEmail() != null && o.getUser().getEmail().compareTo("") != 0){%>
                <td data-head="Email"><%= o.getUser().getEmail()%></td>
                <%} else {%>
                <td data-head="Email"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getNome() != null && o.getNome().compareTo("") != 0){%>
                <td data-head="Nome"><%= o.getNome()%></td>
                <%} else {%>
                <td data-head="Nome"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getCognome() != null && o.getCognome().compareTo("") != 0){%>
                <td data-head="Cognome"><%= o.getCognome()%></td>
                <%} else {%>
                <td data-head="Cognome"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getDataDiNascita() != null){%>
                <td data-head="Data Nascita"><%= o.getDataDiNascita()%></td>
                <%} else {%>
                <td data-head="Data Nascita"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getNumeroTelefonico() != null && o.getNumeroTelefonico().compareTo("") != 0){%>
                <td data-head="Telefono"><%= o.getNumeroTelefonico()%></td>
                <%} else {%>
                <td data-head="Telefono"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getVia() != null && o.getVia().compareTo("") != 0){%>
                <td data-head="Via"><%=o.getVia()%></td>
                <%} else {%>
                <td data-head="Via"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getCAP() != null && o.getCAP().compareTo("") != 0){%>
                <td data-head="CAP"><%=o.getCAP()%></td>
                <%} else {%>
                <td data-head="CAP"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getCity() != null && o.getCity().compareTo("") != 0){%>
                <td data-head="Città"><%=o.getCity()%></td>
                <%} else {%>
                <td data-head="Città"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getPaese() != null && o.getPaese().compareTo("") != 0){%>
                <td data-head="Paese"><%= o.getPaese()%></td>
                <%} else {%>
                <td data-head="Paese"><span class="empty">** non presente **</span></td>
                <%}%>
                <%if(o.getAppartamento() != null  && o.getAppartamento().compareTo("") != 0){%>
                <td data-head="Appartamento"><%= o.getAppartamento()%></td>
                <%} else {%>
                <td data-head="Appartamento"><span class="empty">** non presente **</span></td>
                <%}%>
            </tr>
            <%}%>
            </tbody>
        </table>
        <div class="container-paginator">
            <jsp:include page="/WEB-INF/views/partials/admin/pagination.jsp">
                <jsp:param name="servlet" value="adminGestioneClienti"/>
            </jsp:include>
        </div>
        <!-- Fine Tables -->
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>
</body>
</html>