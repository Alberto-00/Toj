<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,checkout"/>
        <jsp:param name="title" value="T&#x000F8;j - checkout"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<!--middle-->
<div class = middle-top>
    <div class ="container-top">
        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        <span>/</span>
        <span>Checkout</span>
    </div>
</div>

<div class="container">
    <div class="checkout_form">
        <div class="row">
            <div class="column-contact">
                <form action="#">
                    <h3>Fatturazione</h3>
                    <div class="row">
                        <div class="column-contact">
                            <label>Nome <span>*</span></label>
                            <input type="text">
                        </div>
                        <div class="column-contact">
                            <label>Cognome  <span>*</span></label>
                            <input type="text">
                        </div>
                        <div class="column-contact2">
                            <label>Nome Compagnia</label>
                            <input type="text">
                        </div>

                        <div class="column-contact2">
                            <label>Indirizzo  <span>*</span></label>
                            <input placeholder="House number and street name" type="text">
                        </div>
                        <div class="column-contact2">
                            <input placeholder="Apartment, suite, unit etc. (optional)" type="text">
                        </div>
                        <div class="column-contact2">
                            <label>Città <span>*</span></label>
                            <input type="text">
                        </div>
                        <div class="column-contact2">
                            <label>Stato / Paese <span>*</span></label>
                            <input type="text">
                        </div>
                        <div class="column-contact">
                            <label>Telefono<span>*</span></label>
                            <input type="number">
                        </div>
                        <div class="column-contact">
                            <label>Email <span>*</span></label>
                            <input type="text">
                        </div>
                        <div class="column-contact">
                            <input id="account" type="checkbox" data-target="createp_account">
                            <label for="account" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">Create an account?</label>

                            <div id="collapseOne" class="collapse one" data-parent="#accordion">
                                <div class="card-body1">
                                    <label> Account password   <span>*</span></label>
                                    <input placeholder="password" type="password">
                                </div>
                            </div>
                        </div>
                        <div class="column-contact">
                            <input id="address" type="checkbox" data-target="createp_account">
                            <label class="righ_0" for="address" data-toggle="collapse" data-target="#collapsetwo" aria-controls="collapseOne">Ship to a different address?</label>

                            <div id="collapsetwo" class="collapse one" data-parent="#accordion">
                                <div class="row">
                                    <div class="col-lg-6 mb-20">
                                        <label>First Name <span>*</span></label>
                                        <input type="text">
                                    </div>
                                    <div class="col-lg-6 mb-20">
                                        <label>Last Name  <span>*</span></label>
                                        <input type="text">
                                    </div>
                                    <div class="col-12 mb-20">
                                        <label>Company Name</label>
                                        <input type="text">
                                    </div>
                                    <div class="col-12 mb-20">
                                        <label>Street address  <span>*</span></label>
                                        <input placeholder="House number and street name" type="text">
                                    </div>
                                    <div class="col-12 mb-20">
                                        <input placeholder="Apartment, suite, unit etc. (optional)" type="text">
                                    </div>
                                    <div class="col-12 mb-20">
                                        <label>Town / City <span>*</span></label>
                                        <input type="text">
                                    </div>
                                    <div class="col-12 mb-20">
                                        <label>State / County <span>*</span></label>
                                        <input type="text">
                                    </div>
                                    <div class="col-lg-6 mb-20">
                                        <label>Phone<span>*</span></label>
                                        <input type="text">

                                    </div>
                                    <div class="col-lg-6">
                                        <label> Email Address   <span>*</span></label>
                                        <input type="text">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="order-notes">
                                <label for="order_note">Order Notes</label>
                                <textarea id="order_note" placeholder="Notes about your order, e.g. special notes for delivery."></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="column-contact">
                <form action="#">
                    <h3>Your order</h3>
                    <div class="order_table table-responsive">
                        <table>
                            <thead>
                            <tr>
                                <th>Product</th>
                                <th>Total</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td> Handbag  fringilla <strong> × 2</strong></td>
                                <td> $165.00</td>
                            </tr>
                            <tr>
                                <td>  Handbag  justo	 <strong> × 2</strong></td>
                                <td> $50.00</td>
                            </tr>
                            <tr>
                                <td>  Handbag elit	<strong> × 2</strong></td>
                                <td> $50.00</td>
                            </tr>
                            <tr>
                                <td> Handbag Rutrum	 <strong> × 1</strong></td>
                                <td> $50.00</td>
                            </tr>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>Cart Subtotal</th>
                                <td>$215.00</td>
                            </tr>
                            <tr>
                                <th>Shipping</th>
                                <td><strong>$5.00</strong></td>
                            </tr>
                            <tr class="order_total">
                                <th>Order Total</th>
                                <td><strong>$220.00</strong></td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                    <div class="payment_method">
                        <div class="panel-default">
                            <input id="payment" name="check_method" type="radio" data-target="createp_account">
                            <label for="payment" data-toggle="collapse" data-target="#method" aria-controls="method">Create an account?</label>

                            <div id="method" class="collapse one" data-parent="#accordion">
                                <div class="card-body1">
                                    <p>Please send a check to Store Name, Store Street, Store Town, Store State / County, Store Postcode.</p>
                                </div>
                            </div>
                        </div>
                        <div class="panel-default">
                            <input id="payment_defult" name="check_method" type="radio" data-target="createp_account">
                            <label for="payment_defult" data-toggle="collapse" data-target="#collapsedefult" aria-controls="collapsedefult">PayPal <img src="assets/img/icon/papyel.png" alt=""></label>

                            <div id="collapsedefult" class="collapse one" data-parent="#accordion">
                                <div class="card-body1">
                                    <p>Pay via PayPal; you can pay with your credit card if you don’t have a PayPal account.</p>
                                </div>
                            </div>
                        </div>
                        <div class="order_button">
                            <button type="submit">Proceed to PayPal</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
