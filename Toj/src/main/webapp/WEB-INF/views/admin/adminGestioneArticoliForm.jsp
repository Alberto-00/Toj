<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="/WEB-INF/views/partials/admin/head.jsp">
        <jsp:param name="adminStyles" value="crmAdmin,adminGestioneArticoli,adminGestioneArticoliForm"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Form"/>
        <jsp:param name="adminScripts" value="crm,adminFormValidateArticoli"/>
    </jsp:include>
</head>
<body>
<%Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");%>
<%@include file="../partials/admin/dashboardNav.jsp"%>
<div class="col-2">
    <%@include file="../partials/admin/dashboardHeader.jsp"%>
    <%Articolo articolo = (Articolo) request.getAttribute("articolo");%>
    <main class="content">
        <h1>Gestione Articoli</h1><br>
        <form enctype="multipart/form-data" class="modify-form" name="modify-form" method="post" action="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoliFormModify">
            <h2>Modifica Articolo</h2>

            <div class="idArticolo">
                <input type="hidden" name="idArticolo" value="${articolo.IDarticolo}">
                <p>ID</p>
                <input type="text" placeholder="${articolo.IDarticolo}" disabled autocomplete="off">
                <c:if test="${not empty msg}">
                    <label class="error">${msg}</label>
                </c:if>
            </div>

            <div class="price">
                <p>Prezzo</p>
                <input type="text" name="prezzo" value="${articolo.prezzo}" required autocomplete="off">
            </div>

            <div class="sex">
                <p>Sesso</p>
                <c:choose>
                    <c:when test="${articolo.sesso eq 'M'}">
                        <input type="radio" name="sesso" checked value="M">
                        <label>M</label>
                        <input type="radio" name="sesso" value="F"><label>F</label>
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="sesso" checked value="F"><label>F</label>
                        <input type="radio" name="sesso" value="M"><label>M</label>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="descrizione">
                <p>Descrizione</p>
                <textarea name="descrizione" required autocomplete="off">${articolo.descrizione}</textarea>
            </div>

            <div class="sconto">
                <p>Sconto <small>(sconto in decimale)</small></p>
                <input type="text" max="1" name="sconto" value="${articolo.sconto}" placeholder="es. 0.5 --> 50%" required autocomplete="off">
            </div>

            <div class="categoria">
                <p>Categoria</p>
                <select name="idCategoria">
                    <option value="default" disabled>Scegli la categoria</option>
                    <option value="<%=articolo.getCategoria().getId_categoria()%>" selected><%=articolo.getCategoria().getNome()%></option>
                    <c:forEach items="${categorie}" var="categoria">
                        <c:if test="${articolo.categoria.nome != categoria.nome}">
                            <option value="${categoria.id_categoria}">${categoria.nome}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="nome">
                <p>Nome</p>
                <input type="text" name="nome" value="${articolo.nome}" autocomplete="off" required>
            </div>

            <div class="taglia">
                <fieldset>
                    <legend>Taglie</legend>
                    <c:forEach items="${taglie}" var="taglia">
                        <div class="size">
                            <input type="checkbox" name="taglia" value="${taglia.id_nome}" checked disabled>
                            <label>${taglia.id_nome}</label>
                            <input type="text" name="quantita" value="${taglia.quantita}" placeholder="Quantità" autocomplete="off" required>
                        </div>
                    </c:forEach>
                </fieldset>
            </div>

            <div class="colore">
                <p>Nuovi Colori <small>(Selezione multipla)</small></p>
                <select name="colore" required multiple>
                    <c:forEach items="${colori}" var="colore">
                        <option value="${colore.cod_esadecimale}">${colore.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="coloreArt">
                <fieldset>
                    <legend>Colore Attuale ID: <%=articolo.getIDarticolo()%></legend>
                    <c:forEach items="${articolo.colori}" var="colore">
                        <label>${colore.nome}</label><br>
                    </c:forEach>
                </fieldset>
            </div>

            <div class="foto">
                <p>Aggiungi Foto</p>
                <input type="file" accept="image/*" name="path" id="fileToUpload" multiple>
                <%if (errors != null){
                    for (Map.Entry<String, String> e: errors.entrySet()){
                        if (e.getKey().compareTo("pathModify") == 0){
                %>
                <label class="error"><%=e.getValue()%></label>
                <%}%>
                <%}%>
                <%}%>
            </div>

            <div class="deleteImg">
                <fieldset>
                    <legend>Rimuovi Foto <span style="display: block">** Lasciare al più 2 foto **</span></legend>
                    <c:forEach items="${paths}" var="path">
                        <div class="pathCheck">
                            <input type="checkbox" name="deletePath" value="${path.pathName}">
                            <label>${path.pathName}</label>
                        </div>
                    </c:forEach>
                    <%if (errors != null){
                        for (Map.Entry<String, String> e: errors.entrySet()){
                            if (e.getKey().compareTo("pathRemove") == 0){
                    %>
                    <label class="error"><%=e.getValue()%></label>
                    <%}%>
                    <%}%>
                    <%}%>
                </fieldset>
            </div>
            <button class="btn" type="submit">Aggiorna</button>
        </form>

        <form name="delete-form" class="delete-form" action="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoliFormDelete" method="post">
            <h2>Eliminazione Articolo</h2>
            <div class="idArticolo">
                <p>Inserisci ID</p>
                <input type="hidden" name="id" value="${articolo.IDarticolo}">
                <input type="text" name="idDelete" value="${articolo.IDarticolo}" required autocomplete="off">
                <%if (errors != null){
                    for (Map.Entry<String, String> e: errors.entrySet()){
                        if (e.getKey().compareTo("msgID") == 0){
                %>
                <label id="errorID" class="error"><%=e.getValue()%></label>
                <%}%>
                <%}%>
                <%}%>
            </div>
            <button class="btn" type="submit">Elimina</button>
        </form>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>
</body>
</html>
