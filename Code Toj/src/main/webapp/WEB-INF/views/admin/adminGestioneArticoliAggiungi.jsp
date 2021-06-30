
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<!DOCTYPE html>
<head>
    <jsp:include page="/WEB-INF/views/partials/admin/head.jsp">
        <jsp:param name="adminStyles" value="crmAdmin,adminGestioneArticoli,adminGestioneArticoliForm"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Form"/>
        <jsp:param name="adminScripts" value="crm,adminFormValidateArticoli"/>
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
            <h1>Aggiunta Articolo</h1>
            <div class="idArticolo">
                <p>ID</p>
                <input type="text" name="idArticolo">
            </div>

            <div class="price">
                <p>Prezzo</p>
                <input type="text" name="prezzo">
            </div>

            <div class="sex">
                <p>Sesso</p>
                <input type="radio"name="sesso" checked value="m"><label>M</label>
                <input type="radio"name="sesso" value="f"><label>F</label>
            </div>

            <div class="descrizione">
                <p>Descrizione</p>
                <textarea name="descrizione"></textarea>
            </div>

            <div class="sconto">
                <p>Sconto</p><input type="text" name="sconto">
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
                <input type="text" name="nome">
            </div>

            <div class="taglia">
                <fieldset>
                    <legend>Taglie</legend>
                    <c:forEach items="${taglie}" var="taglia">
                        <input type="hidden" name="taglia" value="${taglia.id_nome}"><label>${taglia.id_nome}</label>
                        <input type="text" name="quantita" value="0" placeholder="Quantità"><br>
                    </c:forEach>
                </fieldset>
            </div>

            <div class="colore">
                <p>Colore</p>
                <select name="colore" multiple>
                    <c:forEach items="${colori}" var="colore">
                        <option value="${colore.cod_esadecimale}">${colore.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="foto">
                <p>Foto Articolo</p>
                <small>Inserire almeno due foto</small>
                <input type="file" name="path" id="fileToUpload" multiple>
            </div>
            <button class="btn" type="submit">Aggiorna</button>
        </form>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>
</html>

