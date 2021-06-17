<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%List<Articolo> articoliWomen = (List<Articolo>) request.getServletContext().getAttribute("articoli");
    List<Articolo> nuoviArriviBySex = (List<Articolo>) request.getAttribute("nuoviArrivi");
    List<Articolo> articoli;

    if(nuoviArriviBySex != null && nuoviArriviBySex.size() > 0)
        articoli = nuoviArriviBySex;
    else
        articoli = articoliWomen;
%>

<div class="owl-slider">
    <div class="carousel owl-carousel">

        <%if(articoli.size() > 0){
            for (int i = 0; i < articoli.size() && i < 10; i++){
                request.setAttribute("pathImgFirst",articoli.get(i).getPaths().get(0).getPathName());
                request.setAttribute("pathImgSecond", articoli.get(i).getPaths().get(1).getPathName());
                request.setAttribute("articolo", articoli.get(i));%>

        <div id="item" class="item">
            <div class="single-product">
                <a id="firstImg<%=i%>" href="${pageContext.request.contextPath}/customers/products?id=${articolo.IDarticolo}&sex=${articolo.sesso}">
                    <img class="image-first image-first<%=i%>" src="${pageContext.request.contextPath}/covers/${pathImgFirst}" alt="${articolo.nome}">
                </a>
                <div class="overlay-product">
                    <a id="secondImg<%=i%>" href="${pageContext.request.contextPath}/customers/products?id=${articolo.IDarticolo}&sex=${articolo.sesso}">
                        <img class="image-second<%=i%>" src="${pageContext.request.contextPath}/covers/${pathImgSecond}" alt="${articolo.nome}">
                    </a>
                </div>
                <div class="quick_button">
                    <a href="#">+ Aggiungi al Carrello</a>
                </div>
                <div class="double_base">
                    <%if (articoli.get(i).getSconto() > 0){%>
                    <div class="product_sale">
                        <span><%="- " + articoli.get(i).getSconto() + '%'%></span>
                    </div>
                    <%}%>
                    <div class="label_product">
                        <span>new</span>
                    </div>
                </div>
            </div>
            <div class="product_content">
                <h3><a id="nameProduct<%=i%>" href="${pageContext.request.contextPath}/customers/products?id=${articolo.IDarticolo}&sex=${articolo.sesso}">${articolo.nome}</a></h3>
                <span id="price<%=i%>" class="current_price"><%="€ " + articoli.get(i).getPrezzo()%></span>
            </div>
        </div>
        <%}%>
        <%}%>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/carousel.js"></script>
