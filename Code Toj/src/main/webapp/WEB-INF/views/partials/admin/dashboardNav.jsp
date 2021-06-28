<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<nav class="col-1 grid-y">
    <a href="${pageContext.request.contextPath}/adminServlet/adminHomepage">
        <img src="${pageContext.request.contextPath}/icons/logo.png" width="200" height="200"></a>
    <a href="${pageContext.request.contextPath}/adminServlet/adminGestioneClienti?page=1">Gestione Clienti</a>
    <a href="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoli?page=1">Gestione Articoli</a>
    <a href="${pageContext.request.contextPath}/adminServlet/adminGestioneOrdini?page=1">Gestione Ordini</a>
    <!--<a href="${pageContext.request.contextPath}/adminServlet/adminProfilo">Profilo</a>-->
    <a id="logout" href="#">Logout</a>
</nav>