<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="/WEB-INF/views/partials/admin/head.jsp">
        <jsp:param name="adminStyles" value="crmAdmin,adminHomepage"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Dashboard"/>
        <jsp:param name="adminScripts" value="crm"/>
    </jsp:include>
</head>

<body>
<%@include file="../partials/admin/dashboardNav.jsp"%>
<div class="col-2">
    <%@include file="../partials/admin/dashboardHeader.jsp"%>
    <main class="content">
        <div class="infobox">
            <h2>Clienti</h2>
            <p>${requestScope.customers}</p>
        </div>
        <div class="infobox">
            <h2>Ordini</h2>
            <p>${requestScope.ordini}</p>
        </div>
        <div class="infobox">
            <h2>Articoli</h2>
            <p>${requestScope.articoli}</p>
        </div>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>
</body>
</html>