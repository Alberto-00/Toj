<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="adminStyles" value="libraryAdmin,crmAdmin,adminGestioneOrdini"/>
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
                <th>ID</th>
                <th>Data Acquisto</th>
                <th>Pacchetto Regalo</th>
                <th>Data Spedizione</th>
                <th>Descrizione</th>
                <th>Destinatario</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>James</td>
                <td>Matman</td>
                <td>Chief Sandwich Eater</td>
                <td>James</td>
                <td>Matman</td>
                <td>Chief Sandwich Eater</td>
            </tr>
            <tr>
                <td>The</td>
                <td>Tick</td>
                <td>Crimefighter Sorta</td>
                <td>The</td>
                <td>Tick</td>
                <td>Crimefighter Sorta</td>
            </tr>
            </tbody>
        </table>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>