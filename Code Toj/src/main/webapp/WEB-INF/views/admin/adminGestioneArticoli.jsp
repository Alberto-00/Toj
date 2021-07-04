<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="/WEB-INF/views/partials/admin/head.jsp">
        <jsp:param name="adminStyles" value="crmAdmin,table,adminGestioneArticoli"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Dashboard"/>
        <jsp:param name="adminScripts" value="crm"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin/dashboardNav.jsp"%>
<div class="col-2">
    <%@include file="../partials/admin/dashboardHeader.jsp"%>
    <main class="content">
        <a class="addProduct" href="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoliAggiungi">Aggiungi Articolo</a>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Prezzo</th>
                <th>Sesso</th>
                <th>Nome</th>
                <th>Sconto</th>
                <th>Data Inserimento</th>
                <th>Id Categoria</th>
                <th>Taglia</th>
                <th>Colore</th>
                <th>Quantità</th>
                <th>Gestione</th>
            </tr>
            </thead>
            <!--Inizio Tables -->
            <tbody>
            <%List<Articolo> productsList = (List<Articolo>) request.getAttribute("productsList");
                if(productsList.isEmpty()){%>
            <td>Nessun Dato Da mostrare</td>
            <%}
                for (Articolo o: productsList){%>
            <tr>
                <td data-head="ID"><%= o.getIDarticolo()%></td>
                <td data-head="Prezzo"><%= o.getPrezzo()%></td>
                <td data-head="Sesso"><%= o.getSesso()%></td>
                <td data-head="Nome"><%= o.getNome()%></td>
                <td data-head="Sconto"><%= o.getSconto()%></td>
                <td data-head="Data Inserimento"><%= o.getData_inserimento()%></td>
                <td data-head="ID Categoria"><%= o.getCategoria().getNome()%></td>
                <td data-head="Taglia">
                    <%int count = 0;
                        for (int j = 0; j <o.getTaglie().size(); j++){
                            if (o.getTaglie().get(j).getQuantita() > 0){%>
                                <%=o.getTaglie().get(j).getId_nome()%>
                                <%count+=o.getTaglie().get(j).getQuantita();
                            }
                        }%>
                </td>
                <td data-head="Colore">
                    <%for (int j = 0; j <o.getColori().size(); j++){%>
                    <%=o.getColori().get(j).getNome()%>
                    <%}%>
                </td>
                <td data-head="Quantità"><%=count%></td>
                <td data-head="Gestione"><a class="modifyProduct" href="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoliForm?id=<%=o.getIDarticolo()%>">Modifica</a></td>
            </tr>
            <%}%>
            </tbody>
        </table>
        <jsp:include page="/WEB-INF/views/partials/admin/pagination.jsp">
            <jsp:param name="servlet" value="adminGestioneArticoli"/>
        </jsp:include>
        <!-- Fine Tables -->
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>
</body>
</html>