<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,cart"/>
        <jsp:param name="title" value="T&#x000F8;j - carrello"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<!--middle-->
<jsp:include page="../partials/customer/titleBanner.jsp">
    <jsp:param name="title" value="Carrello"/>
</jsp:include>

<div class="container-top">
    <div class="row">
        <div class="column-contact2">
            <div class="table_desc">
                <div class="cart_page table-responsive">
                    <table>
                        <thead>
                        <tr>
                            <th class="product_remove">Cancella</th>
                            <th class="product_thumb">Immagine</th>
                            <th class="product_name">Prodotto</th>
                            <th class="product-price">Prezzo</th>
                            <th class="product_quantity">Quantità</th>
                            <th class="product_total">Totale</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%for(int i = 0; i < 3; i++){%>
                        <tr>
                            <td class="product_remove"><a href="#"><i class="far fa-trash-alt"></i></a></td>
                            <td class="product_thumb"><a href="#"><img src="${pageContext.request.contextPath}/images/boy.jpg" alt=""></a></td>
                            <td class="product_name"><a href="#" class="hover">Handbag fringilla</a></td>
                            <td class="product-price">€ 65.00</td>
                            <td class="product_quantity"><input min="1" max="100" value="1" placeholder="1" type="number"></td>
                            <td class="product_total">€ 130.00</td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                </div>
                <div class="cart_submit">
                    <button type="submit">Aggiorna carrello</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-top">
    <div class="row">
        <div class="column-contact">
            <div class="coupon_code left">
                <h3>Coupon</h3>
                <div class="coupon_inner">
                    <p>Inserisci il tuo codice coupon se ne possiedi uno.</p>
                    <input placeholder="Codice coupon" type="text">
                    <button type="submit">Applica coupon</button>
                </div>
            </div>
        </div>
        <div class="column-contact">
            <div class="coupon_code right">
                <h3>Totale Carrello</h3>
                <div class="coupon_inner">
                    <div class="cart_subtotal">
                        <p>Subtotal</p>
                        <p class="cart_amount">€ 25.00</p>
                    </div>
                    <div class="cart_subtotal ">
                        <p>Spedizione</p>
                        <p class="cart_amount">€ 55.00</p>
                    </div>
                    <hr class="border3-hr">
                    <div class="cart_subtotal">
                        <p>Totale</p>
                        <p class="cart_amount">€ 215.00</p>
                    </div>
                    <div class="checkout_btn">
                        <a href="${pageContext.request.contextPath}/customers/checkout">Procedi al Checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
