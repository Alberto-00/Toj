
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<!DOCTYPE html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="adminStyles" value="libraryAdmin,crmAdmin,adminGestioneArticoli,adminGestioneArticoliForm"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Form"/>
        <jsp:param name="adminScripts" value="crm,jquery-validate-plugin,adminFormValidateArticoli"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin/dashboardNav.jsp"%>
<div class="col-2">
    <%@include file="../partials/admin/dashboardHeader.jsp"%>
    <main class="content">
        <h1>Gestione Articoli</h1>
        <br>
        <form enctype="multipart/form-data" class="input-form" name="input-form" method="post" action="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoliFormInsert">
            <h2>Aggiunta Articolo</h2>
            ID      <br><input type="text" name="idArticolo"><br>
            Prezzo  <br><input type="text" name="prezzo"><br>
            Sesso   <br><input type="radio"name="sesso" checked value="m">M <input type="radio"name="sesso" value="f">F<br>
            Descrizione <br><textarea name="descrizione"></textarea><br>
            Sconto <br><input type="text" name="sconto"><br>

            Categoria <br>
            <select name="idCategoria">
                <option value="default" disabled selected>Scegli la categoria</option>
                <c:forEach items="${categorie}" var="categoria">
                    <option value="${categoria.id_categoria}">${categoria.nome}</option>
                </c:forEach>
            </select>
            <br>
            Nome <br><input type="text" name="nome"><br>
            <fieldset>
                <legend>Taglie</legend>
                <c:forEach items="${taglie}" var="taglia">
                    <input type="hidden" name="taglia" value="${taglia.id_nome}"><label>${taglia.id_nome}</label>
                    <input type="text" name="quantita" value="0" placeholder="Quantità"><br>
                </c:forEach>
            </fieldset>
            Colore<br>
            <select name="colore" multiple>
                <c:forEach items="${colori}" var="colore">
                    <option value="${colore.cod_esadecimale}">${colore.nome}</option>
                </c:forEach>
            </select>
            <br>
            Foto Articolo<br>
            <input type="file" name="path" id="fileToUpload" multiple><br>
            <input type="submit">
        </form>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>
</html>

