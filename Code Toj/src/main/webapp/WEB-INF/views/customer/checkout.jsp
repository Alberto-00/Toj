<%@ page import="Model.Articolo.Articolo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,checkout"/>
        <jsp:param name="customerScripts" value="checkout"/>
        <jsp:param name="title" value="T&#x000F8;j - checkout"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<!--middle-->
<jsp:include page="../partials/customer/titleBanner.jsp">
    <jsp:param name="title" value="Checkout"/>
</jsp:include>

<form action="#" method="post">
    <div class="container-top">
        <div class="checkout_form">
            <div class="row">
                <div class="column-contact">
                    <h3>Fatturazione</h3>
                        <%@include file="../partials/customer/formCheckout.jsp"%>
                    <div class="column-contact2">
                        <input id="accountPass" type="checkbox">
                        <label for="accountPass">Vuoi creare un account?</label>
                        <div id="pass" class="hide">
                            <label> Account password <span>*</span></label>
                            <input placeholder="password" type="password" name="passwordAccount">
                        </div>
                    </div>
                    <div class="column-contact2">
                        <input id="address" type="checkbox" >
                        <label for="address" class="label">La spedizione coincide con la fatturazione?</label>
                    </div>
                    <div class="hide2">
                        <%@include file="../partials/customer/formCheckout.jsp"%>
                    </div>
                    <div class="column-contact2">
                        <div class="order-notes">
                            <label for="order_note">Order Notes</label>
                            <textarea id="order_note" placeholder="Notes about your order, e.g. special notes for delivery."></textarea>
                        </div>
                    </div>
                </div>
                <div class="column-contact padding-right0">
                    <h3>Il tuo ordine</h3>
                    <div class="order_table table-responsive">
                        <table>
                            <thead>
                            <tr>
                                <th>Prodotti</th>
                                <th>Totale</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%for (Articolo a: cart.getItems()){%>
                            <tr>
                                <td><p><%=a.getNome()%><strong> × <%=a.getLocalQuantity()%></strong></p></td>
                                <td>€ <%=a.totalPrice()%></td>
                            </tr>
                            <%}%>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>Totale Carrello</th>
                                <% double coupon = 0, subtotal;
                                    if (request.getAttribute("coupon") != null){
                                        subtotal = cart.subTotal() - cart.applyCoupon((double) request.getAttribute("coupon"));
                                    } else {
                                        subtotal = cart.subTotal();
                                    }%>
                                <td><strong>€ <%=subtotal%></strong></td>
                            </tr>
                            <tr>
                                <th>Spedizione</th>
                                <td><strong>€ <%=Cart.getSpedizione()%></strong></td>
                            </tr>
                            <tr class="order_total">
                                <th>Ordine Totale</th>
                                <td><strong>€ <%=cart.total()%></strong></td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>

                    <div class="payment_method">
                        <div class="row">
                            <div class="column-contact2">
                                <input id="paycard" type="checkbox" name="card">
                                <label for="paycard" class="inline"><img src="${pageContext.request.contextPath}/icons/pay.png" alt="paycard"></label>
                            </div>
                        </div>

                        <div class="hide3">
                            <div class="row">
                                <div class="column-contact">
                                    <label>Nome <span>*</span></label>
                                    <input type="text" name="nomeCard" required>
                                </div>
                                <div class="column-contact padding-right0">
                                    <label>Cognome  <span>*</span></label>
                                    <input type="text" name="cognomeCard" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="column-contact">
                                    <label>Numero carta <span>*</span></label>
                                    <input type="text" inputmode="numeric" name="numeroCarta" pattern="[0-9]*" size="16" required>
                                </div>
                                <div class="column-contact3">
                                    <label>CVV <span>*</span></label>
                                    <input type="text" name="cvv" maxlength="4" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="column-contact">
                                    <label>Mese / Anno<span>*</span></label>
                                    <input type="month" name="dataCarta" pattern="[0-9]*" size="16" required>
                                </div>
                            </div>
                        </div>
                        <div class="order_button">
                            <button type="submit">Procedi al Pagamento</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
