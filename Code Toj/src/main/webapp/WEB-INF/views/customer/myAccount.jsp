<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,myAccount"/>
        <jsp:param name="customerScripts" value="myAccount"/>
        <jsp:param name="title" value="T&#x000F8;j - my Account"/>
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
        <span>My Account</span>
    </div>
</div>

<div class="container-top">
    <div class="row">

        <div class="column-contact">
            <div id="dashboard-Account">
                <button class="btn-Account active" onclick="showhide('dashboard')">Dashboard</button>
                <button class="btn-Account" onclick="showhide('details-account')">Dettagli Account</button>
                <button class="btn-Account" onclick="showhide('orders-account')">Ordini</button>
                <button class="btn-Account" onclick="showhide('address-account')">Indirizzo</button>
                <button class="btn-Account" onclick="showhide('logout-account')">Logout</button>
            </div>
        </div>

        <div class="columnAlt">

            <div class="Account-things" id="dashboard">
                <h1>Dashboard</h1>
                <p>Benvenuto nel tuo account! Qui potrai controllare tutte le informazioni inerenti al tuo account, nonché tutti gli ordini effettuati.</p>
            </div>

            <div class="Account-things contact-message" id="details-account" style="display: none">
                <h1>Dettagli account</h1>
                <form method="post" action="#">
                    <p>
                        <label>Nome</label>
                        <input class="input-text" name="nome" type="text" >
                    </p>
                    <p>
                        <label>Cognome</label>
                        <input class="input-text" name="cognome" type="text">
                    </p>
                    <p>
                        <label>Email</label>
                        <input name="email" type="email">
                    </p>
                    <p>
                        <label>Password</label>
                        <input name="password" type="password">
                    </p>
                    <p>
                        <label>Data di nascita</label><br>
                        <input name="username" type="date" ><br>
                    </p>
                    <input class="submit-button-reg" type="submit" name="submitContact" value="Modifica">
                </form>
            </div>

            <div class="Account-things" id="orders-account" style="display: none">
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
                                <td class="order-details"><button onclick="hideElement('dettagli')">View</button></td>


                            </tr>
                            <%}%>
                            </tbody>
                        </table>
                        <div id="dettagli" style="display: none"> oioiioi </div>
                    </div>
                </div>
            </div>

            <div class="Account-things" id="address-account" style="display: none">
                <h1>Indirizzo di spedizione</h1>

                        <div class="contact-message">

                            <form method="post" action="#">
                                <p>
                                    <label>Nome e Cognome</label>
                                    <input class="input-text" name="name" placeholder="Nome *" type="text">
                                </p>
                                <p>
                                    <label>Città</label>
                                    <input class="input-text" name="city" placeholder="Città *" type="text">
                                </p>
                                <p>
                                    <label>Indirizzo</label>
                                    <input name="input-text" placeholder="Strada e numero civico" type="text">
                                </p>
                                <input type="submit" name="submitContact" value="Modifica">
                            </form>
                        </div>

                </div>


            <div class="Account-things" id="logout-account" style="display: none">
                <h1>Logout</h1>
            </div>
        </div>

    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
