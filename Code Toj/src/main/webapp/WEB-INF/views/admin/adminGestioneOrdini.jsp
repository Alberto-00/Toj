<%@ page import="Model.Ordine.Ordine" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <th>Destinatario</th>
                <th>ID</th>
                <th>Data Acquisto</th>
                <th>Data Spedizione</th>
            </tr>
            </thead>
            <!--Inizio Tables -->
            <tbody>
            <%List<Ordine> newOrdini = (List<Ordine>) request.getAttribute("ordini");
                if(newOrdini.isEmpty()){%>
                <td>Nessun Dato Da mostrare</td>
            <%}
                for (Ordine o: newOrdini){%>
                <tr>
                    <td data-head="Destinatario"><%= o.getUser().getEmail()%></td>
                    <td data-head="ID"><%= o.getID_ordine()%></td>
                    <td data-head="Data Acquisto"><%= o.getData_acquisto()%></td>
                    <td data-head="Data Spedizione"><%= o.getData_spedizione()%></td>
                </tr>
            <%}%>
            </tbody>
        </table>
        <c:forEach var="page" begin="1" end="${pages}">
            <div class="paginator">
                <a id="elem${page}" href="${pageContext.request.contextPath}/adminServlet/adminGestioneOrdini?page=${page}">${page}</a>
            </div>
        </c:forEach>
        <!-- Fine Tables -->
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>

