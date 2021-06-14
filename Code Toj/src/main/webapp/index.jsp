<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="WEB-INF/views/partials/head.jsp">
        <jsp:param name="customerStyles" value="homePage,carousel"/>
        <jsp:param name="customerScripts" value="homePage,carousel"/>
        <jsp:param name="title" value="T&#x000F8;j | Clothing Store"/>
    </jsp:include>
</head>
<body>
<!--header-->
<%@include file="WEB-INF/views/partials/customer/headerTop.jsp"%>

<!-- immagini "uomo" "donna" centrali -->
<div class="container-top">
    <div class="image image-left" id="overlay-image-woman">
        <a href="${pageContext.request.contextPath}/customers/Women"><img src="images/woman.jpg" alt=""></a>
        <div class="banner-text centered">
            <h1>Women's</h1>
            <p>Big Sale Off Final Items. Caught in the moment!</p>
            <a href="./customers/Women">Acquista Ora</a>
        </div>
    </div>
    <div class="image image-right" id="overlay-image-man">
        <a href="${pageContext.request.contextPath}/customers/Men"><img src="images/boy.jpg" alt=""></a>
        <div class="banner-text centered">
            <h1>Men's</h1>
            <p>Big Sale Off Final Items. Caught in the moment!</p>
            <a href="./customers/Men">Acquista Ora</a>
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
    <%@include file="WEB-INF/views/partials/customer/carousel.jsp"%>
       <!--trasformare in import dinamico-->
</div>

<!-- footer-->
<%@include file="WEB-INF/views/partials/customer/footer.jsp"%>
</body>
</html>
