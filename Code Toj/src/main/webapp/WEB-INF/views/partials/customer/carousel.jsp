<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="owl-slider">
    <div class="carousel owl-carousel">
        <div class="item">
            <div class="single-product">
                <a id="firstImg" href="${pageContext.request.contextPath}/customers/products?id=">
                    <img class="image-first" src="${pageContext.request.contextPath}/covers/" alt="">
                </a>
                <div class="overlay-product">
                    <a id="secondImg" href="${pageContext.request.contextPath}/customers/products?id=">
                        <img class="image-second" src="${pageContext.request.contextPath}/covers/" alt="">
                    </a>
                </div>
                <div class="quick_button">
                    <a href="#">+ Aggiungi al Carrello</a>
                </div>
                <div class="double_base">
                    <div class="product_sale">
                        <span></span>
                    </div>
                    <div class="label_product">
                        <span>new</span>
                    </div>
                </div>
            </div>
            <div class="product_content">
                <h3><a id="nameProduct" href="${pageContext.request.contextPath}/customers/cart/?id="></a></h3>
                <span class="current_price"></span>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/carousel.js"></script>
