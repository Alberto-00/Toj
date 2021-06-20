<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,menWomen,carousel"/>
        <jsp:param name="customerScripts" value="menWomen,pagination"/>
        <jsp:param name="title" value="T&#x000F8;j - ${sex}"/>
    </jsp:include>

</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<!--middle-->
<jsp:include page="../partials/customer/titleBanner.jsp">
    <jsp:param name="title" value="${sex}"/>
</jsp:include>

<%List<Articolo> articoli = (List<Articolo>) request.getAttribute("productsList");
int count = (int) request.getAttribute("count");
%>

<div class="container-top">
    <div class="row">
        <div class="column-filters">
            <form action="#">
                    <!--Filtro prezzo-->
                    <div class="filter-contenitor">
                        <div onclick="hideElement('filter-price')">
                            <h3>Prezzo <i class="fas fa-plus"></i></h3>
                        </div>
                        <div class="filter-price" id="filter-price">
                            <div class="slidecontainer">
                                <input type="range" min="1" max="100" value="0" name="barPrice" id="myRange">
                                <p id="quanto">Quanto?</p>
                                <input class="button" type="submit" value="Invia" style="width: fit-content">
                            </div>
                        </div>
                    </div>

                    <!--Filtro categoria-->
                    <div class="filter-contenitor">
                        <div onclick="hideElement('filter-category')">
                            <h3>Categoria <i class="fas fa-plus"></i></h3>
                        </div>

                        <div class="filter-category" id="filter-category">
                            <%for(int i = 0; i < 10; i++){%>
                            <div class="catalog">
                                <input type="checkbox" id="camicie" name="camicie" value="Camicie">
                                <label for="camicie"> Camicie</label>
                            </div>
                            <%}%>
                        </div>
                    </div>

                    <!--Filtro colori-->
                    <div class="filter-contenitor">
                        <div onclick="hideElement('filter-color')">
                            <h3>Colori <i class="fas fa-plus"></i></h3>
                        </div>

                        <div class="filter-color" id="filter-color">
                            <%for (int i = 0; i < 10; i++){%>
                            <div class="catalog">
                                <input type="checkbox" id="green" name="green" value="Green">
                                <label for="green">Verde</label>
                            </div>
                            <%}%>
                        </div>
                    </div>

                    <!--Filtro Taglia-->
                    <div class="filter-contenitor">
                        <div onclick="hideElement('filter-size')">
                            <h3>Taglia <i class="fas fa-plus"></i></h3>
                        </div>

                        <div class="filter-size" id="filter-size">
                            <%for (int i = 0; i < 10; i++){%>
                            <div class="catalog">
                                <input type="checkbox" id="xs" name="xs" value="XS">
                                <label for="xs"> XS</label> &ensp;
                            </div>
                            <%}%>
                        </div>
                    </div>
                </form>
        </div>
        <div class="column-Art">
            <div class="row">
                    <%for(int i = 0; i < count; i++){%>
                    <div class="column-img">
                        <div class="single-product">
                            <a id="firstImg<%=i%>" href="${pageContext.request.contextPath}/customers/products?id=<%=articoli.get(i).getIDarticolo()%>&sex=<%=articoli.get(i).getSesso()%>">
                                <img src="${pageContext.request.contextPath}/covers/<%=articoli.get(i).getPaths().get(0).getPathName()%>" alt="<%=articoli.get(i).getNome()%>">
                            </a>
                            <div class="overlay-product">
                                <a id="secondImg<%=i%>"  href="${pageContext.request.contextPath}/customers/products?id=<%=articoli.get(i).getIDarticolo()%>&sex=<%=articoli.get(i).getSesso()%>">
                                    <img class="image-second<%=i%>" src="${pageContext.request.contextPath}/covers/<%=articoli.get(i).getPaths().get(1).getPathName()%>" alt="<%=articoli.get(i).getNome()%>">
                                </a>
                            </div>
                            <div class="quick_button">
                                <a id="cartHref<%=i%>"  href="${pageContext.request.contextPath}/customers/products?id=<%=articoli.get(i).getIDarticolo()%>&sex=<%=articoli.get(i).getSesso()%>">+ Aggiungi al Carrello</a>
                            </div>
                        </div>
                        <div class="product_content">
                            <h3><a id="nameProduct<%=i%>" href="${pageContext.request.contextPath}/customers/products?id=<%=articoli.get(i).getIDarticolo()%>&sex=<%=articoli.get(i).getSesso()%>"><%=articoli.get(i).getNome()%></a></h3>
                            <span id="price<%=i%>" class="current_price"><%="€ " + articoli.get(i).getPrezzo()%></span>
                        </div>
                    </div>
                <%}%>
            </div>
            <div class="owl-slider">
                <div class="carousel owl-carousel">
                    <c:forEach var="page" begin="1" end="${pages}">
                        <div class="item paginator">
                            <a id="elem${page}" href="./shop?page=${page}&sex=<%=articoli.get(0).getSesso()%>">${page}</a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" defer>
    $("#elem${numPage}").addClass("active_page");
</script>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
