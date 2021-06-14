<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="owl-slider">
    <div class="carousel owl-carousel">
        <%for(int i = 0; i < 8; i++){%>
        <div class="item">
            <div class="single-product">
                <a href="#">
                    <img class="image-first" src="${pageContext.request.contextPath}/images/woman.jpg" alt="">
                </a>
                <div class="overlay-product">
                    <a href="#"><img src="${pageContext.request.contextPath}/images/boy.jpg" alt=""></a>
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
        <%}%>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/carousel.js"></script>
