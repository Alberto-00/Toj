<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="owl-slider">
    <div class="carousel owl-carousel">
        <%List<Articolo> articoliMen = (List<Articolo>) request.getServletContext().getAttribute("nuoviArriviMen");
            List<Articolo> articoliWomen = (List<Articolo>) request.getServletContext().getAttribute("nuoviArriviWomen");
            String sex = request.getParameter("sex");
            List<Articolo> articoli;

            if(sex != null){
                if(sex.compareToIgnoreCase("F") == 0)
                    articoli = articoliWomen;
                else articoli = articoliMen;
            }
            else articoli = articoliMen;

            if(articoli.size() != 0){
                for (int i = 0; i < articoli.size() && i < 10; i++){
                    request.setAttribute("pathImgFirst",articoli.get(i).getPaths().get(0).getPathName());
                    request.setAttribute("pathImgSecond", articoli.get(i).getPaths().get(1).getPathName());
                    request.setAttribute("articolo", articoli.get(i));
        %>
        <div class="item">
            <div class="single-product">
                <a href="${pageContext.request.contextPath}/customers/products?id=${articolo.IDarticolo}&sex=${articolo.sesso}">
                    <img class="image-first" src="${pageContext.request.contextPath}/covers/${pathImgFirst}" alt="${articolo.nome}">
                </a>
                <div class="overlay-product">
                    <a href="${pageContext.request.contextPath}/customers/products?id=${articolo.IDarticolo}&sex=${articolo.sesso}">
                        <img src="${pageContext.request.contextPath}/covers/${pathImgSecond}" alt="${articolo.nome}">
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
                <h3><a href="#">${nome}</a></h3>
                <span class="current_price">€ <%=articoli.get(i).getPrezzo()%></span>
            </div>
        </div>
        <%}%>
       <%}%>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/carousel.js"></script>
