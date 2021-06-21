<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="adminStyles" value="libraryAdmin,crmAdmin,adminGestioneArticoli"/>
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
                <th>Prezzo</th>
                <th>Sesso</th>
                <th>Descrizione</th>
                <th>Sconto</th>
                <th>Data Inserimento</th>
                <th>Id Categoria</th>
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
                <td>James</td>
            </tr>
            <tr>
                <td>The</td>
                <td>Tick</td>
                <td>Crimefighter Sorta</td>
                <td>The</td>
                <td>Tick</td>
                <td>Crimefighter Sorta</td>
                <td>The</td>
            </tr>
            </tbody>
        </table>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>