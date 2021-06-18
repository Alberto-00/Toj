<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,menWomen"/>
        <jsp:param name="customerScripts" value="menWomen"/>
        <jsp:param name="title" value="T&#x000F8;j - ${sex}"/>
    </jsp:include>

</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<%List<Articolo> articoli = (List<Articolo>) request.getAttribute("productsList");
    if(articoli.size() > 0){
    for (int i = 0; i < 18; i++){
        request.setAttribute("pathImgFirst",articoli.get(i).getPaths().get(0).getPathName());
        request.setAttribute("pathImgSecond", articoli.get(i).getPaths().get(1).getPathName());
    }
}%>

<div class="container-top">
    <div class="column-filters" id="filters" style="float: left;">
        <form action="#"><br>
            <div class="filter-contenitor">Prezzo
                <a onclick="hideElement('filter-price')" style="display: block"><i class="fas fa-plus"></i></a>
                <div class="filter-price" id="filter-price">
                    <input type="range" min="0" max="100" value="0" class="slider" id="myRange">
                    <p>Quanto?</p>
                    <p id="quanto"></p><br>
                    <input type="submit" value="Submit" style="width: fit-content">
                </div>
            </div>

            <div class="filter-contenitor">Categoria
                <a onclick="hideElement('filter-category')" style="display: block"><i class="fas fa-plus"></i></a>
                <div class="filter-category" id="filter-category">
                    <input type="checkbox" id="camicie" name="camicie" value="Camicie">
                <label for="camicie"> Camicie</label><br>

                <input type="checkbox" id="cappotti" name="cappotti" value="Cappotti">
                <label for="cappotti"> Cappotti</label><br>

                <input type="checkbox" id="costumi" name="costumi" value="Costumi">
                <label for="costumi"> Costumi</label><br>

                <input type="checkbox" id="felpe" name="felpe" value="Felpe">
                <label for="felpe"> Felpe</label><br>

                <input type="checkbox" id="giacche" name="giacche" value="Giacche">
                <label for="giacche"> Giacche</label><br>

                <input type="checkbox" id="magliette" name="magliette" value="Magliette">
                <label for="magliette"> Magliette</label><br>

                <input type="checkbox" id="polo" name="polo" value="Polo">
                <label for="polo"> Polo</label><br>

                <input type="checkbox" id="shorts" name="shorts" value="Shorts">
                <label for="shorts"> Pantaloni corti</label><br>

                <input type="checkbox" id="long" name="long" value="Trousers">
                <label for="long"> Pantaloni lunghi</label><br>

         </div>
    </div>

    <div class="filter-contenitor">Colori
        <a onclick="hideElement('filter-color')" style="display: block"><i class="fas fa-plus"></i></a>
         <div class="filter-color" id="filter-color">

            <label class="container">
                <input type="checkbox" id="green" name="green" value="Green">
                <span class="checkmark" style="background-color: green"></span>
                Verde
            </label>

            <label class="container">
                <input type="checkbox" id="red" name="red" value="Red">
                <span class="checkmark" style="background-color: red"></span>
                Rosso
            </label>

            <label class="container">
                <input type="checkbox" id="yellow" name="yellow" value="Yellow">
                <span class="checkmark" style="background-color: yellow"></span>
                Giallo
            </label>

            <label class="container">
                <input type="checkbox" id="orange" name="orange" value="Orange">
                <span class="checkmark" style="background-color: #fd7e14"></span>
                Arancione
            </label>

            <label class="container">
                <input type="checkbox" id="purple" name="purple" value="Purple">
                <span class="checkmark" style="background-color: purple"></span>
                Viola
            </label>

            <label class="container">
                <input type="checkbox" id="blue" name="blue" value="Blue">
                <span class="checkmark" style="background-color: blue"></span>
                Blu
            </label>

            <label class="container">
                <input type="checkbox" id="black" name="black" value="Black">
                <span class="checkmark" style="background-color: black"></span>
                Nero
            </label>

            <label class="container">
                <input type="checkbox" id="white" name="white" value="White">
                <span class="checkmark checkmark-wh" style="background-color: white; border: 1px solid black;"></span>
                Bianco
            </label>

            <label class="container">
                <input type="checkbox" id="brown" name="brown" value="Brown">
                <span class="checkmark" style="background-color: saddlebrown"></span>
                Marrone
            </label>

            <label class="container">
                <input type="checkbox" id="grey" name="grey" value="Grey">
                <span class="checkmark" style="background-color: grey"></span>
                Grigio
            </label>

            </div>
    </div>

    <div class="filter-contenitor">Taglia
        <a onclick="hideElement('filter-size')" style="display: block"><i class="fas fa-plus"></i></a>
         <div class="filter-size" id="filter-size">

            <input type="checkbox" id="xs" name="xs" value="XS">
            <label for="xs"> XS</label> &ensp;

            <input type="checkbox" id="s" name="s" value="S">
            <label for="s"> S</label><br>

            <input type="checkbox" id="m" name="m" value="M">
            <label for="m"> M</label> &ensp;&nbsp;

            <input type="checkbox" id="l" name="l" value="L">
            <label for="l"> L</label><br>

            <input type="checkbox" id="xl" name="xl" value="XL">
            <label for="xl"> XL</label> &ensp;

            <input type="checkbox" id="xxl" name="xxl" value="XXL">
            <label for="xxl"> XXL</label><br>

            <input type="checkbox" id="xxxl" name="xxxl" value="XXXL">
            <label for="xxl"> XXXL</label><br>

        </div>
    </div>

        </form>




    </div>

    <button class="chiudi" id="close" onclick="closeButton()">APRI</button>

    <div class="products" style="float: left">

        <div class="row">
            <%for(int i = 0; i < 18; i++){%>
            <div class="product-box">
                 <!--img------>
                    <div class="double-img">
                        <a id="firstImg<%=i%>" href="${pageContext.request.contextPath}/customers/products?id=<%=articoli.get(i).getIDarticolo()%>&sex=<%=articoli.get(i).getSesso()%>">
                            <img src="${pageContext.request.contextPath}/covers/<%=articoli.get(i).getPaths().get(1).getPathName()%>" alt="<%=articoli.get(i).getNome()%>">
                            <img class="top-image" src="${pageContext.request.contextPath}/covers/<%=articoli.get(i).getPaths().get(0).getPathName()%>" alt="<%=articoli.get(i).getNome()%>">
                        </a>
                        <a href="#" class="add-cart">
                            <i class="fas fa-shopping-cart"></i>
                        </a>
                        <%if (articoli.get(i).getSconto() > 0){%>
                            <span class="add-sconto"><%="- " + articoli.get(i).getSconto() + '%'%></span>
                        <%}%>
                    </div>

                <!--product-details-------->
                <div class="product-details">
                    <a class="p-name" id="nameProduct<%=i%>" href="${pageContext.request.contextPath}/customers/products?id=<%=articoli.get(i).getIDarticolo()%>&sex=<%=articoli.get(i).getSesso()%>"><%=articoli.get(i).getNome()%>
                    <br><span class="p-price" id="price<%=i%>"><%="€ " + articoli.get(i).getPrezzo()%></span></a>
                </div>
            </div>
            <%}%>
        </div>

        <div class="row">

            <ul class="paginator">

                <c:forEach var="page" begin="1" end="${pages}">
                    <li>
                        <a href="./productsList?page=${page}&sex=<%=articoli.get(0).getSesso()%>">${page}</a>
                    </li>
                </c:forEach>

            </ul>

        </div>

    </div>

</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
