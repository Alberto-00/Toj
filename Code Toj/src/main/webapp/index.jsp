<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="WEB-INF/views/partials/head.jsp">
        <jsp:param name="customerStyles" value="homePage,carousel"/>
        <jsp:param name="customerScripts" value="homePage,carousel"/>
        <jsp:param name="customerAjaxScripts" value="newArrivals"/>
        <jsp:param name="title" value="T&#x000F8;j | Clothing Store"/>
    </jsp:include>
</head>
<body>
<!--header-->
<%@include file="WEB-INF/views/partials/customer/headerTop.jsp"%>

<!-- immagini "uomo" "donna" centrali -->
<div class="container-top">
    <div class="image image-left" id="overlay-image-woman">
        <a href="${pageContext.request.contextPath}/customers/Women">
            <img src="${pageContext.request.contextPath}/images/woman.jpg" alt="Women">
        </a>
        <div class="banner-text centered">
            <h1>Women's</h1>
            <p>Big Sale Off Final Items. Caught in the moment!</p>
            <a href="./customers/Women">Acquista Ora</a>
        </div>
    </div>
    <div class="image image-right" id="overlay-image-man">
        <a href="${pageContext.request.contextPath}/customers/Men">
            <img src="${pageContext.request.contextPath}/images/boy.jpg" alt="Men">
        </a>
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
        <a id="F" class="hover ajax">Women's</a>
        <a id="M" class="hover ajax">Men's</a>
    </div>
</div>

<!-- nuovi arrivi-->
<div class="container-top">
    <%@include file="WEB-INF/views/partials/customer/carousel.jsp"%>
</div>

<!-- footer-->
<%@include file="WEB-INF/views/partials/customer/footer.jsp"%>
</body>
</html>
