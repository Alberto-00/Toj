<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,aboutUs"/>
        <jsp:param name="title" value="T&#x000F8;j - about us"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<!--middle-->
<jsp:include page="../partials/customer/titleBanner.jsp">
    <jsp:param name="title" value="About Us"/>
</jsp:include>

<div class="middle-center">
    <div class="container-top">
        <div class="row">
            <div class="column-middle">
                <div class="about-content">
                    <h1>Benvenuto in Tøj Clothing Store!</h1>
                    <p>Tøj è una piattaforma digitale dedicata alla vendita di abbigliamento online, sia maschile che femminile.
                        Il nostro desiderio è quello di permettere ai nostri clienti di acquistare capi esclusivi dal comfort delle loro case.
                        Fondato nel 2019, Tøj parte come una piccola società operativa nella sola Italia per arrivare,
                        nel giro di qualche anno, ad avere clienti in tutta Europa, con progetti di estendersi anche in altre regioni del mondo.
                    </p><br><br>
                    <p>
                        Tøj è orgoglioso di offrire a donne e uomini l'opportunità di indossare le
                        ultime tendenze a prezzi democratici, annoverando ben 500 articoli per ogni stile desiderato dal cliente.
                    </p>
                </div>
            </div>
            <div class="column2-middle">
                <div class="image-center-right">
                    <img src="${pageContext.request.contextPath}/images/clothes.jpg" alt="magazzino">
                </div>
            </div>
        </div>
    </div>
</div>

<div class="middle-center">
    <div class="container-top">
        <div class="row">
            <div class="column2-middle">
                <div class="image-center-left">
                    <img src="${pageContext.request.contextPath}/images/womanShop.jpg" alt="Donna che acquista">
                </div>
            </div>
            <div class="column-middle">
                <div class="about-content padding">
                    <p>
                        Tøj offre una vasta selezione di articoli, da magliette grafiche,
                        a camicette stampate a pantaloni indescrivibili, diventando il luogo d'incontro ideale per gli appassionati di moda
                        a prezzi inaccessibili. Il suo obiettivo finale è quello di offrire i migliori stili in tempi record e a
                        prezzi imbattibili in tutto il mondo (o i primi due).
                        Oggi, Tøj effettua consegne in tutta Europa con magazzini presenti nelle principali capitali.
                    </p><br><br>
                    <p>
                        Il nostro sito web è disponibile in tutte le lingue e per tutti ma a poco serve se non sei europeo;
                        per la lingua, tranqui che c’è Google Traduttore. Tøj continua a crescere, anche per i suoi valori che la produzione interna
                        deve dimostrare un'eccellenza indiscutibile. L'obiettivo di Tøj è quello di offrire articoli di tendenza di alta qualità
                        e un servizio impeccabile.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-top">
    <div class="row">
        <div class="column-aboutUs">
            <div class="square">
                <div class="counter-info-image">
                    <img src="${pageContext.request.contextPath}/icons/user.png" alt="">
                </div>
                <div class="counter-info">
                    <h2>2170</h2>
                    <p>Utenti iscritti</p>
                </div>
            </div>
        </div>

        <div class="column-aboutUs">
            <div class="square">
                <div class="counter-info-image">
                    <img src="${pageContext.request.contextPath}/icons/package.png" alt="">
                </div>
                <div class="counter-info">
                    <h2>8080</h2>
                    <p>Ordini effettuati</p>
                </div>
            </div>
        </div>

        <div class="column-aboutUs">
            <div class="square">
                <div class="counter-info-image">
                    <img src="${pageContext.request.contextPath}/icons/clothes.png" alt="">
                </div>
                <div class="counter-info">
                    <h2>3151</h2>
                    <p>Articoli posseduti</p>
                </div>
            </div>
        </div>

        <div class="column-aboutUs">
            <div class="square">
                <div class="counter-info-image">
                    <img src="${pageContext.request.contextPath}/icons/time.png" alt="">
                </div>
                <div class="counter-info">
                    <h2>2151</h2>
                    <p>Giorni di apertura</p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
