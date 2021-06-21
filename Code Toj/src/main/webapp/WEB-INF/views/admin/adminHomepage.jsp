
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!doctype html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="adminStyles" value="libraryAdmin,crmAdmin,adminHomepage"/>
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
            <h2>Clienti Registrati</h2>
            <p>1295</p>
        </div>
        <div class="infobox">
            <h2>Ordini</h2>
            <p>655</p>
        </div>
        <div class="infobox">
            <h2>Articoli</h2>
            <p>9291</p>
        </div>
        <div class="infobox">
            <h2>Clienti Registrati</h2>
            <p>aa</p>
        </div>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>