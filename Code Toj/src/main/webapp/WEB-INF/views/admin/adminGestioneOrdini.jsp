<%@ page import="Model.Ordine.Ordine" %><%@ page import="java.util.List" %>
<%@ page import="Model.Articolo.Articolo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="/WEB-INF/views/partials/admin/head.jsp">
        <jsp:param name="adminStyles" value="crmAdmin,table,adminGestioneOrdini"/>
        <jsp:param name="adminScripts" value="crm,ordini"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Dashboard"/>
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
                <th>Dettagli</th>
            </tr>
            </thead>
            <tbody>
            <%List<Ordine> newOrdini = (List<Ordine>) request.getAttribute("ordini");
            if(newOrdini.isEmpty()){%>
            <td>Nessun Dato Da mostrare</td>
            <%} else {
            for (int i =0; i < newOrdini.size(); i++){%>
            <tr>
                <td data-head="Destinatario"><%= newOrdini.get(i).getUser().getEmail()%></td>
                <td data-head="ID"><%= newOrdini.get(i).getID_ordine()%></td>
                <td data-head="Data Acquisto"><%= newOrdini.get(i).getData_acquisto()%></td>
                <td data-head="Data Spedizione"><%= newOrdini.get(i).getData_spedizione()%></td>
                <td data-head="Dettagli"><a class="show" id="show<%=i%>">Apri</a></td>
            </tr>
            <%}
            }%>
            </tbody>
        </table>
        <%int i = 0;
        for (Ordine o: newOrdini){%>
        <div class="hide" id="hide<%=i%>">
            <div class="products">
                <h3>Ordine #<%=o.getID_ordine()%></h3>
                <%for(Articolo a: o.getArticoli()){%>
                <%=a.getNome()%>
                <span class="p-price">€ <%=a.getPrezzo()%></span>
                <span class="p-price">Quantità x<%=a.getQuantita_articolo_in_Ordine()%></span><br>
                <%}%>
                <span class>Totale: € <%=o.total()%></span>
            </div>
        </div>
        <%i++; }%>
        <div class="container-paginator">
            <jsp:include page="/WEB-INF/views/partials/admin/pagination.jsp">
                <jsp:param name="servlet" value="adminGestioneOrdini"/>
            </jsp:include>
        </div>
        <!-- Fine Tables -->
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>
</body>
</html>