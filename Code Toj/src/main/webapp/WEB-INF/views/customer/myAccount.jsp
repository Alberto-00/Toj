<%@ page import="java.util.List" %>
<%@ page import="Model.Ordine.Ordine" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %>
<%@ page import="Model.Articolo.Articolo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Model.Dati_utente.DatiUtente" %>
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
    <jsp:param name="title" value="Il mio Account"/>
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
            <div class="account-content" id="details-account">
                <h1>Dettagli account</h1>
                <form method="post" action="${pageContext.request.contextPath}/customers/updateAnagraphicalDates" name="formData">
                    <div class="row">
                        <div class="columnAlt-100">
                            <label>Nome</label>
                            <input type="text" name="nome" placeholder="Nome"
                                   value="<c:if test="${not empty userInfSession.nome}">${userInfSession.nome}</c:if>"
                                   autocomplete="off">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-100">
                            <label>Cognome</label>
                            <input type="text" name="cognome" placeholder="Cognome"
                                   value="<c:if test="${not empty userInfSession.cognome}">${userInfSession.cognome}</c:if>"
                                   autocomplete="off">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-50">
                            <label>Telefono</label>
                            <input type="tel" name="telefono" placeholder="es. +39 389 887 2651"
                                   value="<c:if test="${not empty userInfSession.numeroTelefonico}">${userInfSession.numeroTelefonico}</c:if>"
                                   autocomplete="off">
                        </div>
                        <div class="columnAlt-50 padding-right0">
                            <label>Email</label>
                            <input type="email" name="email" placeholder="example@gmail.com" value="${userSession.email}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-100">
                            <label>Password</label>
                            <input type="password" name="password">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-50">
                            <label>Data di nascita</label>
                            <% DatiUtente datiUtente = (DatiUtente) session.getAttribute("userInfSession");
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                if (datiUtente != null && datiUtente.getDataDiNascita() != null){
                                    String  str = formatter.format(datiUtente.getDataDiNascita());%>
                                <input type="date" name="birthday" value="<%=str%>" autocomplete="off">
                                <%} else {%>
                            <input type="date" name="birthday" value="" autocomplete="off">
                            <%}%>
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
                            <%List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
                            if (ordini != null && ordini.size() > 0){
                                for (int i = 0; i < ordini.size(); i++){%>
                            <tr>
                                <td class="order-number">#<%=ordini.get(i).getID_ordine()%></td>
                                <td class="order-date"><%=ordini.get(i).getData_acquisto().toString()%>
                                </td>
                                <%if (ordini.get(i).getData_spedizione().before(new Date()) ||
                                    ordini.get(i).getData_spedizione().compareTo(new Date()) == 0){%>
                                <td class="order-status">Consegnato</td>
                                <%} else {%>
                                <td class="order-status">Non Arrivato</td>
                                <%}%>
                                <td class="order-total">€ <%=ordini.get(i).total()%></td>
                                <td class="order-details"><a class="show" id="show<%=i%>">Apri</a></td>
                            </tr>
                            <%}%>
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                    <%Map<String, List<Articolo>> articoloMap = (Map<String, List<Articolo>>) request.getAttribute("articoli");
                    if (articoloMap != null){ int i = 0;
                        for (Ordine o: ordini){%>
                    <div class="hide" id="hide<%=i%>">
                        <h1>Ordine #<%=o.getID_ordine()%></h1>
                        <div class="row">
                           <%int j = 0; for(Articolo a: articoloMap.get(o.getID_ordine())){%>
                            <div class="column-product">
                                <div class="product-box">
                                    <div class="double-img">
                                        <a href="${pageContext.request.contextPath}/customers/products?id=<%=a.getIDarticolo()%>&sex=<%=a.getSesso()%>" >
                                            <img src="${pageContext.request.contextPath}/covers/<%=a.getPaths().get(1).getPathName()%>" alt="">
                                            <img src="${pageContext.request.contextPath}/covers/<%=a.getPaths().get(0).getPathName()%>" alt="" class="top-image">
                                        </a>
                                    </div>
                                    <div class="product-details">
                                        <a href="${pageContext.request.contextPath}/customers/products?id=<%=a.getIDarticolo()%>&sex=<%=a.getSesso()%>" class="p-name"><%=a.getNome()%></a>
                                        <span class="p-price">€ <%=a.getPrezzoScontato()%></span>
                                        <span class="p-price">Quantità x<%=ordini.get(i).getArticoli().get(j).getQuantita_articolo_in_Ordine()%></span>
                                    </div>
                                </div>
                            </div>
                            <%j++;}%>
                        </div>
                    </div>
                    <%i++; }%>
                    <%}%>
                </div>
            </div>
            <div class="account-content" id="address-account">
                <h1>Indirizzo</h1>
                <form action="${pageContext.request.contextPath}/customers/updateAddessUser" method="post" name="formAddress">
                    <div class="row">
                        <div class="columnAlt-75">
                            <label>Indirizzo</label>
                            <input placeholder="Via e numero civico" type="text" name="indirizzo" value="<c:if test="${not empty userInfSession.via}">${userInfSession.via}</c:if>" autocomplete="off">
                        </div>
                        <div class="columnAlt-20">
                            <label>CAP</label>
                            <input placeholder="CAP" type="text" name="cap" maxlength="5" value="<c:if test="${not empty userInfSession.CAP}">${userInfSession.CAP}</c:if>" autocomplete="off">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-100">
                            <input placeholder="Appartamento, scala, piano etc. (opzionale)" name="appartamento" value="<c:if test="${not empty userInfSession.appartamento}">${userInfSession.appartamento}</c:if>" type="text" autocomplete="off">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-100">
                            <label>Città</label>
                            <input type="text" name="city" placeholder="Città" value="<c:if test="${not empty userInfSession.city}">${userInfSession.city}</c:if>" autocomplete="off">
                        </div>
                    </div>
                    <div class="row">
                        <div class="columnAlt-50">
                            <label>Stato / Paese</label>
                            <input type="text" name="paese" placeholder="es. Italia" value="<c:if test="${not empty userInfSession.paese}">${userInfSession.paese}</c:if>" maxlength="10" autocomplete="off">
                        </div>
                    </div>
                    <button type="submit" name="detailsAccount">Salva</button>
                </form>
            </div>
            <div class="account-content" id="logout-account">
                <h1><a href="${pageContext.request.contextPath}/customers/logout">Logout</a></h1>
            </div>
        </div>
    </div>
</div>

<!-- footer -->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
