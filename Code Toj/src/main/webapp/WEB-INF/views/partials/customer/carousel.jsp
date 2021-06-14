<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="owl-slider">
    <div class="carousel owl-carousel">
        <c:forEach items="${nuoviArrivi}" var="newProduct">
            <div class="item">
                <div class="single-product">
                    <a href="#">
                        <img class="image-first" src="${pageContext.request.contextPath}/covers/Cappotti da uomo Tasca Colore unico Ufficio grigio 38 (0).jpg" alt="${newProduct.nome}">
                    </a>
                    <div class="overlay-product">
                        <a href="#"><img src="${pageContext.request.contextPath}/${paths[0][1]}" alt=""></a>
                    </div>
                    <div class="quick_button">
                        <a href="#">+ Aggiungi al Carrello</a>
                    </div>
                    <div class="double_base">
                        <div class="product_sale">
                            <span>-7%</span>
                        </div>
                        <div class="label_product">
                            <span>new</span>
                        </div>
                    </div>
                </div>
                <div class="product_content">
                    <h3><a href="#">JBL Flip 3 Portable</a></h3>
                    <span class="current_price">£60.00</span>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/carousel.js"></script>
