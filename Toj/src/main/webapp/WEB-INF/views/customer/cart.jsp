<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="Model.Sconto.Sconto" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,cart"/>
        <jsp:param name="customerAjaxScripts" value="cart"/>
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
                            <th class="product_size">Taglia</th>
                            <th class="product_total">Totale</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty cartNotLog}">
                            <% int i = 0; for (Articolo a: cart.getItems()){%>
                            <tr>
                                <td class="product_remove"><a href="${pageContext.request.contextPath}/carts/remove?id=<%=a.getIDarticolo()%>&size=<%=a.getChosenSize()%>"><i class="far fa-trash-alt"></i></a></td>
                                <td class="product_thumb">
                                    <a href="${pageContext.request.contextPath}/customers/products?id=<%=a.getIDarticolo()%>&sex=<%=a.getSesso()%>">
                                        <img src="${pageContext.request.contextPath}/covers/<%=a.getPaths().get(0).getPathName()%>" alt="foto">
                                    </a>
                                </td>
                                <td class="product_name">
                                    <a href="${pageContext.request.contextPath}/customers/products?id=<%=a.getIDarticolo()%>&sex=<%=a.getSesso()%>" class="hover">
                                        <%=a.getNome()%></a>
                                </td>
                                <%DecimalFormat df2 = new DecimalFormat("#.00");
                                String price = df2.format(a.getPrezzoScontato());
                                String total = df2.format(a.totalPrice());%>
                                <td class="product-price">€ <%=price%></td>
                                <td class="product_quantity">
                                    <%if (a.getLocalQuantity() > a.getOneTaglia(a.getChosenSize()).getQuantita()){%>
                                    <input id="<%=i%>" class="update" data="<%=a.getChosenSize()%>" data1="<%=a.getIDarticolo()%>" min="1"
                                           max="<%=a.getOneTaglia(a.getChosenSize()).getQuantita()%>"
                                           value="<%=a.getOneTaglia(a.getChosenSize()).getQuantita()%>"
                                           name="quantity" type="number">
                                    <small></small>
                                    <%} else {%>
                                    <input id="<%=i%>" class="update" data="<%=a.getChosenSize()%>" data1="<%=a.getIDarticolo()%>" min="1"
                                           max="<%=a.getOneTaglia(a.getChosenSize()).getQuantita()%>" value="<%=a.getLocalQuantity()%>"
                                           name="quantity" type="number">
                                    <small></small>
                                    <%}%>
                                </td>
                                <td class="product_size"><%=a.getChosenSize()%></td>
                                <td class="product_total">€ <%=total%></td>
                            </tr>
                            <%i++;}%>
                        </c:if>
                        </tbody>
                    </table>
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
                    <input placeholder="Codice coupon" type="text" name="coupon" id="couponInput" maxlength="7">
                    <button data="${pageContext.request.contextPath}" type="submit" id="coupon">Applica coupon</button>
                    <small class="errMsg"></small>
                </div>
            </div>
        </div>
        <% double total =  0.00, subTotal = 0.00, spedizione = 0.00, coupon = 0;
        if(session.getAttribute("coupon") != null) {
            Sconto sconto = (Sconto) session.getAttribute("coupon");
            coupon = sconto.getSconto();
        }
        if(cart != null && cart.getItems().size() > 0){
            if (coupon > 0)
                subTotal = cart.subTotal() - cart.applyCoupon(coupon);
            else subTotal = cart.subTotal();
            spedizione = Cart.getSpedizione();
            total = subTotal + spedizione;
        }
        %>
        <div class="column-contact">
            <div class="coupon_code right">
                <h3>Totale Carrello</h3>
                <div class="coupon_inner">
                    <div class="cart_subtotal">
                        <p>Subtotal</p>
                        <%DecimalFormat df2 = new DecimalFormat("#.00");
                            String priceSubTotal = df2.format(subTotal);%>
                        <p class="cart_amount">€ <%=priceSubTotal%></p>
                    </div>
                    <div class="cart_subtotal">
                        <p>Spedizione</p>
                        <%String spedition = df2.format(spedizione);%>
                        <p class="cart_amount">€ <%=spedition%></p>
                    </div>
                    <hr class="border3-hr">
                    <div class="cart_subtotal">
                        <p>Totale</p>
                        <%String tot = df2.format(total);%>
                        <p class="cart_amount">€ <%=tot%></p>
                    </div>
                    <div class="checkout_btn">
                        <a href="javascript:void(0)" id="checkout-btn">Procedi al Checkout</a>
                        <small></small>
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
