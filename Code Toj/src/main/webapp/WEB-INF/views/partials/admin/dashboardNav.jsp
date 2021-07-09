<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<nav class="col-1 grid-y">
    <a href="${pageContext.request.contextPath}/adminServlet/adminHomepage">
        <img id="logo" src="${pageContext.request.contextPath}/icons/logo.png" alt="logo">
    </a>
    <a class="btn" href="${pageContext.request.contextPath}/adminServlet/adminGestioneClienti?page=1">Gestione Clienti</a>
    <a class="btn" href="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoli?page=1">Gestione Articoli</a>
    <a class="btn" href="${pageContext.request.contextPath}/adminServlet/adminGestioneOrdini?page=1">Gestione Ordini</a>
    <a class="btn" id="logout" href="javascript:void(0)">Logout</a>
</nav>