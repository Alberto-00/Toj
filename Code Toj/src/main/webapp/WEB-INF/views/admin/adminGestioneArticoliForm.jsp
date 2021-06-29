<%@ page import="Model.Taglia.Taglia" %>
<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<!DOCTYPE html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="adminStyles" value="libraryAdmin,crmAdmin,adminGestioneArticoli,adminGestioneArticoliForm"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Form"/>
        <jsp:param name="adminScripts" value="crm,adminFormValidateArticoli"/>
    </jsp:include>
</head>
<body>
<%@include file="../partials/admin/dashboardNav.jsp"%>
<div class="col-2">
    <%@include file="../partials/admin/dashboardHeader.jsp"%>
    <%Articolo articolo = (Articolo) request.getAttribute("articolo");%>
    <main class="content">
        <h1>Gestione Articoli</h1><br>
        <form enctype="multipart/form-data" class="input-form" name="input-form" method="post" action="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoliFormModify">
            <h2>Aggiunta Articolo</h2>
            ID      <br><input type="text" name="idArticolo" value="${articolo.IDarticolo}"><br>
            Prezzo  <br><input type="text" name="prezzo" value="${articolo.prezzo}"><br>
            Sesso   <br>
            <c:choose>
                <c:when test="${articolo.sesso eq 'M'}">
                    <input type="radio"name="sesso" checked value="M">M<input type="radio"name="sesso" value="F">F<br>
                </c:when>
                <c:otherwise>
                    <input type="radio"name="sesso" checked value="F">F<input type="radio"name="sesso" value="M">M<br>
                </c:otherwise>
            </c:choose>
            Descrizione <br><textarea name="descrizione">${articolo.descrizione}</textarea><br>
            Sconto <br><input type="text" name="sconto" value="${articolo.sconto}"><br>

            Categoria <br>
            <select name="idCategoria">
                <option value="default"><%=articolo.getCategoria().getNome()%></option>
                <c:forEach items="${categorie}" var="categoria">
                    <c:if test="${articolo.categoria.nome != categoria.nome}">
                        <option value="${categoria.id_categoria}">${categoria.nome}</option>
                    </c:if>
                </c:forEach>
            </select>
            <br>
            Nome <br><input type="text" name="nome" value="${articolo.nome}"><br>
            <fieldset>
                <legend>Taglie</legend>
                <%List<Taglia> taglie = (List<Taglia>) request.getAttribute("taglie"); boolean flag = false;
                for (Taglia t1: taglie) {
                    for (Taglia t :articolo.getTaglie()) {
                        if (t.getId_nome().compareTo(t1.getId_nome()) == 0){%>
                            <input type="checkbox" name="taglia" value="<%=t.getId_nome()%>" checked><label><%=t.getId_nome()%></label>
                            <input type="text" name="quantita" value="<%=t.getQuantita()%>" placeholder="Quantità"><br>
                        <% flag = true; break;
                        } else {
                            flag = false;
                        }
                    } if (!flag){%>
                         <input type="checkbox" name="taglia" value="<%=t1.getId_nome()%>" ><label><%=t1.getId_nome()%></label>
                         <input type="text" name="quantita" value="0" placeholder="Quantità"><br>
                <%}
                }%>
            </fieldset>
            Colore<br>
            <select name="colore" multiple>
                <c:forEach items="${colori}" var="colore">
                    <option value="${colore.cod_esadecimale}">${colore.nome}</option>
                </c:forEach>
            </select>
            <br>
            Immagine<br>
            <input type="file" name="path" id="fileToUpload"><br><br>
            <input type="submit">
        </form>
        <br>
        <form name="delete-form" class="delete-form" action="${pageContext.request.contextPath}/adminServlet/adminGestioneArticoliFormDelete" method="post">
            <h2>Eliminazione Articolo</h2>
            Inserire ID<br><input type="text" value="${articolo.IDarticolo}" name="id"><br><br>
            <input type="submit">
        </form>
    </main>
    <%@include file="../partials/admin/dashboardFooter.jsp"%>
</div>
<%@include file="../partials/admin/dashboardLogout.jsp"%>

</body>
</html>
