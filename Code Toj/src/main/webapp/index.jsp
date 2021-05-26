<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="WEB-INF/views/partials/head.jsp">
        <jsp:param name="customerStyles" value="homePage"/>
        <jsp:param name="title" value="T&#x000F8;j | Clothing Store"/>
    </jsp:include>
</head>
<body>
<!--header-->
<%@include file="WEB-INF/views/partials/customer/headerTop.jsp"%>

<!-- immagini "uomo" "donna" centrali -->
<div class="container-top">
    <div class="image image-left" id="overlay-image-woman">
        <a href="#"><img src="images/woman.jpg" alt=""></a>
        <div class="banner-text centered">
            <h1>Women's</h1>
            <p>Big Sale Off Final Items. Caught in the moment!</p>
            <a href="#">Acquista Ora</a>
        </div>
    </div>
    <div class="image image-right" id="overlay-image-man">
        <a href="#"><img src="images/boy.jpg" alt=""></a>
        <div class="banner-text centered">
            <h1>Men's</h1>
            <p>Big Sale Off Final Items. Caught in the moment!</p>
            <a href="#">Acquista Ora</a>
        </div>
    </div>
</div>

<!-- banner centrale prima dei nuovi arrivi-->
<div class="container-top">
    <div class="title-middle">
        <h2>Nuovi arrivi</h2>
        <p>Design contemporanei, minimali e moderni per essere sempre al passo con la moda</p><br><br>
        <a href="#" class="hover">Women's</a>
        <a href="#" class="hover">Men's</a>
    </div>
</div>

<!-- nuovi arrivi-->
<div class="container-top">
    <div class="new-arrival" id="menu">
        <!--products----------------------->
        <div class="product-container">
            <%for(int i = 0; i < 8; i++){%>
            <!--product-box-1---------->
            <div class="product-box">
                <!--product-img------------>
                <div class="product-img">
                    <!--add-cart---->
                    <a href="#" class="add-cart">
                        <i class="fas fa-shopping-cart"></i>
                    </a>
                    <span class="add-sconto">5%</span>
                    <!--img------>
                    <div class="double-img">
                        <a href="#">
                            <img src="images/woman.jpg" alt="">
                            <img src="images/boy.jpg" alt="" class="top-image">
                        </a>
                    </div>
                </div>
                <!--product-details-------->
                <div class="product-details">
                    <a href="#" class="p-name">Drawstring T-Shirt</a>
                    <span class="p-price">$22.00</span>
                </div>
            </div>
            <%}%>
        </div>
        <div id="nav">
            <div id="prev"><i class="fas fa-chevron-left"></i></div>
            <div id="next"><i class="fas fa-chevron-right"></i></div>
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="WEB-INF/views/partials/customer/footer.jsp"%>
</body>
</html>
