<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,menWomen"/>
        <jsp:param name="title" value="T&#x000F8;j - Men"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<div class="container-top">

    <div class="column-filters" style="float: left;">
        hhhhhh
    </div>

    <div class="products" style="float: left">
        <%for(int i = 0; i < 5; i++){%>
        <div class="row">
            <%for(int j = 0; j < 3; j++){%>
            <div class="product-box">
                    <!--add-cart---->

                    <!--img------>
                    <div class="double-img">
                        <a href="#">
                            <img src="${pageContext.request.contextPath}/images/woman.jpg" alt="">
                            <img src="${pageContext.request.contextPath}/images/boy.jpg" alt="" class="top-image">
                        </a>
                        <a href="#" class="add-cart">
                            <i class="fas fa-shopping-cart"></i>
                        </a>
                        <span class="add-sconto">5%</span>
                    </div>

                <!--product-details-------->
                <div class="product-details">
                    <a href="#" class="p-name">Drawstring T-Shirt</a>
                    <span class="p-price">$22.00</span>
                </div>
            </div>
            <%}%>

        </div>
        <%}%>

    </div>

</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
