<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,myAccount"/>
        <jsp:param name="customerScripts" value="myAccount,checkout"/>
        <jsp:param name="title" value="T&#x000F8;j - Account"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<!--middle-->
<jsp:include page="../partials/customer/titleBanner.jsp">
    <jsp:param name="title" value="My Account"/>
</jsp:include>

<div class="container-top">
    <div class="row">
        <div class="column-contact">
            <div id="dashboard-Account">
                <button class="btn-Account active-btn" onclick="showhide('dashboard')">Dashboard</button>
                <button class="btn-Account" onclick="showhide('details-account')">Dettagli Account</button>
                <button class="btn-Account" onclick="showhide('orders-account')">Ordini</button>
                <button class="btn-Account" onclick="showhide('address-account')">Indirizzo</button>
                <button class="btn-Account" onclick="showhide('logout-account')">Logout</button>
            </div>
        </div>
        <div class="columnAlt">
            <div class="account-content" id="dashboard">
                <h1>Dashboard</h1>
                <p>Benvenuto nel tuo account! Qui potrai controllare tutte le informazioni
                    inerenti al tuo account, nonché tutti gli ordini effettuati,gestisci i
                    tuoi indirizzi di spedizione e fatturazione e modifica la tua password
                    e i dettagli dell'account.
                </p>
            </div>
            <div class="account-content" id="details-account" >
                <h1>Dettagli account</h1>
                <form method="post" action="#">
                    <div class="row">
                        <div class="columnAlt-100">
                            <label>Nome</label>
                            <input type="text" name="nome" placeholder="Nome">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-100">
                            <label>Cognome</label>
                            <input type="text" name="cognome" placeholder="Cognome">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-50">
                            <label>Telefono</label>
                            <input type="tel" name="telefono" placeholder="es. 389 887 2651" maxlength="10" required>
                        </div>
                        <div class="columnAlt-50 padding-right0">
                            <label>Email</label>
                            <input type="email" name="email" placeholder="example@gmail.com" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-50">
                            <label>Username</label>
                            <input type="text" name="username" placeholder="" required>
                        </div>
                        <div class="columnAlt-50 padding-right0">
                            <label>Password</label>
                            <input type="password" name="password" placeholder="" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-50">
                            <label>Data di nascita</label>
                            <input type="date" name="birthday">
                        </div>
                    </div>
                    <button type="submit" name="detailsAccount">Salva</button>
                </form>
            </div>
            <div class="account-content" id="orders-account">
               <h1>Storico ordini</h1>
                <div class="table_desc">
                    <div class="account_page table-responsive">
                        <table>
                            <thead>
                            <tr>
                                <th class="order-number">Numero Ordine</th>
                                <th class="order-date">Data</th>
                                <th class="order-status">Stato</th>
                                <th class="order-total">Totale</th>
                                <th class="order-details">Dettagli</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%for(int i = 0; i < 3; i++){%>
                            <tr>
                                <td class="order-number">Numero</td>
                                <td class="order-date">Data</td>
                                <td class="order-status">Stato</td>
                                <td class="order-total">€ 65.00</td>
                                <td class="order-details"><a class="show" id="show<%=i%>">Apri</a></td>
                            </tr>
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="hide">
                        <h1>Ordine #332-2221-2121</h1>
                        <div class="row">
                            <%for(int i = 0; i < 12; i++){%>
                            <div class="column">
                                <div class="product-box">
                                    <div class="double-img">
                                        <a href="#">
                                            <img src="${pageContext.request.contextPath}/images/woman.jpg" alt="">
                                            <img src="${pageContext.request.contextPath}/images/boy.jpg" alt="" class="top-image">
                                        </a>
                                    </div>
                                    <div class="product-details">
                                        <a href="#" class="p-name">Drawstring T-Shirt</a>
                                        <span class="p-price">$22.00</span>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="account-content" id="address-account">
                <h1>Indirizzo</h1>
                <form action="#" method="post">
                    <div class="row">
                        <div class="columnAlt-100">
                            <label>Indirizzo</label>
                            <input placeholder="Via e numero civico" type="text" name="indirizzo">
                        </div>
                        <div class="columnAlt-100">
                            <input placeholder="Appartamento, scala, piano etc. (opzionale)" name="appartamento" type="text">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-100">
                            <label>Città</label>
                            <input type="text" name="city" placeholder="Città">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-50">
                            <label>Stato / Paese</label>
                            <input type="text" name="paese" placeholder="es. Italia" maxlength="10">
                        </div>
                    </div>
                    <button type="submit" name="detailsAccount">Salva</button>
                </form>
            </div>
            <div class="account-content" id="logout-account">
                <h1><a href="${pageContext.request.contextPath}/customers/sigin">Logout</a></h1>
            </div>
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
