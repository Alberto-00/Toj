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
    <main class="content">
        <h1>Gestione Articoli</h1>
        <br>
        <form enctype="multipart/form-data" class="input-form" name="input-form" method="post" action="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoliFormInsert">
            <h1>Aggiunta Articolo</h1>
            <div class="idArticolo">
                <p>ID <small>(ultimo ID inserito ${maxID})</small></p>
                <input type="text" name="idArticolo" required autocomplete="off">
                <%if (errors != null){
                    for (Map.Entry<String, String> e: errors.entrySet()){
                        if (e.getKey().compareTo("msg") == 0){
                %>
                <label id="errorID" class="error"><%=e.getValue()%></label>
                <%}%>
                <%}%>
                <%}%>
            </div>

            <div class="price">
                <p>Prezzo</p>
                <input type="text" name="prezzo" required autocomplete="off">
            </div>

            <div class="sex">
                <p>Sesso</p>
                <input type="radio"name="sesso" checked value="m"><label>M</label>
                <input type="radio"name="sesso" value="f"><label>F</label>
            </div>

            <div class="descrizione">
                <p>Descrizione</p>
                <textarea name="descrizione" required autocomplete="off"></textarea>
            </div>

            <div class="sconto">
                <p>Sconto<small>(sconto in decimale)</small></p><input type="text" max="1" name="sconto" placeholder="es. 0.5 --> 50%" autocomplete="off" required>
            </div>

            <div class="categoria">
                <p>Categoria</p>
                <select name="idCategoria">
                    <option value="default" disabled selected>Scegli la categoria</option>
                    <c:forEach items="${categorie}" var="categoria">
                        <option value="${categoria.id_categoria}">${categoria.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="nome">
                <p>Nome</p>
                <input type="text" name="nome" required autocomplete="off">
            </div>

            <div class="taglia">
                <fieldset>
                    <legend>Taglie</legend>
                    <c:forEach items="${taglie}" var="taglia">
                        <div class="size">
                            <input type="hidden" name="taglia" value="${taglia.id_nome}">
                            <label>${taglia.id_nome}</label>
                            <input type="text" name="quantita" value="0" placeholder="Quantità" required autocomplete="off">
                        </div>
                    </c:forEach>
                </fieldset>
            </div>

            <div class="colore">
                <p>Colore <small>(Selezione multipla)</small></p>
                <select name="colore" multiple>
                    <c:forEach items="${colori}" var="colore">
                        <option value="${colore.cod_esadecimale}">${colore.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="foto">
                <p>Foto Articolo <small>(Inserire almeno due foto)</small></p>
                <input type="file" accept="image/*" name="path" id="fileToUpload" multiple required>
                <%if (errors != null){
                    for (Map.Entry<String, String> e: errors.entrySet()){
                        if (e.getKey().compareTo("msgPath") == 0){
                %>
                <label class="error"><%=e.getValue()%></label>
                <%} else if (e.getKey().compareTo("pathIsPresent") == 0){%>
                <br><label class="error"><%=e.getValue()%></label>
                <%}%>
                <%}%>
                <%}%>
            </div>
            <button class="btn" type="submit">Crea</button>
        </form>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>
</body>
</html>

