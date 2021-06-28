
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<!DOCTYPE html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="adminStyles" value="libraryAdmin,crmAdmin,adminGestioneArticoli,adminGestioneArticoliForm"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Form"/>
        <jsp:param name="adminScripts" value="crm,jquery-validate-plugin,adminFormValidateArticoli"/>
    </jsp:include>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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


            Categoria <br><input list="idCategorie" id="idCategoria" name="idCategoria" />
            <datalist id="idCategorie" name="idCategorie">
                <option value="1">Cappotti</option>
                <option value="2">Giacche</option>
                <option value="3">Costumi</option>
                <option value="4">Bikini</option>
                <option value="5">Intero</option>
                <option value="6">Felpe</option>
                <option value="7">Camice</option>
                <option value="8">Polo</option>
                <option value="9">T-Shirt</option>
                <option value="10">Top</option>
                <option value="11">Pantaloni Lunghi</option>
                <option value="12">Pantaloni Corti</option>
                <option value="13">Gonna Lunga</option>
                <option value="14">Gonna Corta</option>
            </datalist><br>
            Nome <br><input type="text" name="nome"><br>
            <fieldset>
                <legend>Taglie (Nessuna selezione equivale a tutte)</legend>
                <input type="checkbox" name="taglia" value="L" checked>L
                <input type="checkbox" name="taglia" value="M">M
                <input type="checkbox" name="taglia" value="S">S
                <input type="checkbox" name="taglia" value="XL">XL
                <input type="checkbox" name="taglia" value="XS">XS
                <input type="checkbox" name="taglia" value="XXL">XXL
                <input type="checkbox" name="taglia" value="XXXL">XXXL
            </fieldset>
            Colore<br><input list="colori" id="colore" name="colore" />
            <datalist id="colori" name="colori">
                <option value="#000000">Nero</option>
                <option value="#000080">Blu</option>
                <option value="#FF0000">Rosso</option>
                <option value="#FFFF00">Giallo</option>
                <option value="#FFFFFF">Bianco</option>
            </datalist><br>
            Quantità<br>
            <input type="text" name="quantita"><br>
            Immagine<br>
            <input type="file" name="path" id="fileToUpload"><br><br>
            <input type="submit">
        </form>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>
</html>

