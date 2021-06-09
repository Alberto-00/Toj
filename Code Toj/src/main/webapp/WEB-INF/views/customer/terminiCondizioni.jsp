<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,privacyPolicy"/>
        <jsp:param name="title" value="T&#x000F8;j - Termini e Condizioni"/>
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
        <span>Termini e Condizioni</span>
    </div>
</div>

<div class="container-top">
    <div class="row">
        <div class="column-contact">
            <div class="privacy_content">
                <h2>1. Introduzione</h2>
                <p>Le presenti Condizioni Generali di Vendita (denominate di seguito "Condizioni Generali"),
                    regolano esclusivamente le relazioni contrattuali tra i clienti del sito Tøj.com
                    (denominati di seguito "Clienti") ed il venditore Tøj.com
                    (azienda LRFC S.A.S. DI VINCENZO SCRENCI & C) P.IVA 03644080792. Queste Condizioni Generali
                    sono le sole a poter essere applicate e sostituiscono tutte le altre condizioni, salvo deroghe
                    preliminari, espresse e scritte. Tøj può trovarsi nell'obbligo di modificare alcune delle
                    presenti disposizioni; si consiglia perciò ai Clienti di leggere le Condizioni Generali ad
                    ogni visita del sito Tøj.com. Tali modifiche sono opponibili a partire dalla messa in linea e
                    non sono applicabili ai contratti stipulati anteriormente ad essa. Ogni acquisto sul Sito è
                    sottoposto alle Condizioni Generali di Vendita applicabili alla data dell'ordine. Tøj confida
                    che, una volta validato l'ordine, i Clienti abbiano letto e accettato senza riserve le
                    Condizioni Generali di Vendita. I Clienti si impegnano a rispettare le Condizioni Generali e
                    le Condizioni di Utilizzo dettagliate sullo stesso.
                </p>
            </div>
            <div class="privacy_content">
                <h3>2. Eseguire un ordine</h3>
                <p>Per eseguire un ordine sul Sito i Clienti  è possibile collegarsi :
                    - via Internet sul Sito: www.tøj.com 24 ore su 24 e 7 giorni su 7
                </p>
                <p>Il procedimento d'ordine si compone di quattro (4) tappe successive.
                    Una volta effettuata la scelta e validato il carrello, i Clienti dovranno :<br>
                    - identificarsi<br>
                    - scegliere il modo di consegna e l'indirizzo<br>
                    - accettare le Condizioni Generali di Vendita barrando l'apposita casella nella pagina riassuntiva dell'ordine<br>
                    - scegliere il modo di pagamento.
                </p>
                <p>Una volta selezionato il modo di pagamento, i Clienti dovranno procedere al saldo dell'ordine, che formalizzerà
                    in maniera ferma e definitiva il contratto di vendita che li lega a Tøj.
                </p>
            </div>
            <div class="privacy_content">
                <h3>3. Informazioni sui prodotti</h3>
                <p>Tøj pone particolare attenzione alla messa in linea delle informazioni relative alle caratteristiche essenziali
                    dei prodotti tramite schede tecniche elaborate dai suoi partner e/o fornitori e tramite fotografie
                    illustrative dei prodotti, nel limite della tecnica e nel rispetto dei migliori standard del mercato.
                </p>
            </div>
            <div class="privacy_content">
                <h3>4. Prezzi</h3>
                <p>I prezzi di vendita indicati sul Sito sono espressi in euro (€). Le spese di consegna sono a carico dei Clienti nel caso di
                    spedizione diversa o gratis nel momento in cui viene scelto come mezzo di trasporto Corriere.
                </p>
                <p> Il prezzo di listino è in euro ed è sistematicamente indicato in maniera chiara prima della conferma dell'ordine.</p>
                <p>Tøj si riserva il diritto di modificare i prezzi in ogni momento, ma si impegna in ugual misura ad applicare i prezzi indicati
                    sul Sito al momento dell'effettuazione dell'ordine.
                </p>
                <p>In caso di visualizzazione di un prezzo sbagliato e/o manifestamente irrisorio, per qualsivoglia ragione (bug informatico, errore manuale, errore tecnico…),
                    l'ordine sarà annullato, anche in caso di convalida iniziale.
                </p>
            </div>
            <div class="privacy_content">
                <h3>5. Disponibilità</h3>
                <p>Gli ordini dei Clienti sono soddisfatti nei limiti della quantità di prodotti disponibili.
                    Nel caso in cui il prodotto ordinato non sia disponibile, Tøj si impegna a contattare i Clienti via e-mail entro 10 giorni a partire dalla data dell'ordine
                    per informarli dei tempi necessari per la consegna del prodotto ordinato.
                </p>
                <p>Nel caso in cui solamente alcuni dei prodotti ordinati non siano disponibili, Tøj si impegna a spedire immediatamente i prodotti disponibili ed a prendere a
                    proprio carico le spese di consegna inerenti alla spedizione dei prodotti restanti. Nel caso in cui l'indisponibilità del prodotto sia dovuta ad un arresto
                    definitivo della produzione dello stesso da parte del costruttore, Tøj proporrà al cliente un prodotto di qualità e prezzo equivalenti. In caso di rifiuto da
                    parte del cliente, Tøj procederà al rimborso del prodotto indisponibile.
                </p>
            </div>
            <div class="privacy_content">
                <h3>6. Modalità di consegna</h3>
                <p>I prodotti acquistati sul Sito possono essere consegnati in tutto il Mondo.</p>
                <p>L'ordine sarà consegnato all'indirizzo indicato al momento dell'ordine.</p>
                <p>Dandalo propone una modalità di consegna per il territorio nazionale:</p>
                <p>1) Corriere Express Nexive, tempi di spedizione di 3-5 giorni lavorativi. Gratuita per ordini superiori a 20€, mentre ha un costo di 2,29€ per ordini inferiori.</p>
                <p>Nel caso di spedizioni internazionali, le tempistiche variano dai 7 ai 14 giorni lavorativi. Il costo varia in base al paese presso il quale verrà richiesta la consegna.</p>
                <p>Alla ricezione dell'ordine, Tøj consiglia ai Clienti di verificare che i prodotti consegnati siano conformi all'ordine, e di indicare, nel caso in cui siano danneggiati,
                    le eventuali anomalie sul modulo di consegna sottoforma di riserve manoscritte e controfirmate.
                </p>
                <p>Il corriere effettuerà 2 tentativi di consegna. In caso il 2 tentativo non ha successo, la spedizione verrà restituita a Tøj.</p>
            </div>
            <div class="privacy_content">
                <h3>7. Garanzie dei prodotti</h3>
                <p>I prodotti proposti alla vendita sul Sito sono accompagnati da diversi tipi di garanzie commerciali definite qui di seguito. Si precisa che la sottoscrizione da parte
                    del cliente di tali garanzie commerciali non pregiudica l'applicazione, per qualsiasi articolo venduto da Tøj sul Sito, che questo sia nuovo o usato, della garanzia
                    legale di conformità nel rispetto delle seguenti condizioni legali.
                </p>
                <p>In base alla garanzia legale di conformità, Tøj si impegna a consegnare un ordine conforme al contratto di vendita concluso ed a rispondere per eventuali difetti di
                    conformità riscontrati al momento della consegna.
                </p>
                <p>Per essere conformi al contratto, i prodotti devono:<br>
                    1) corrispondere alla descrizione presente sul Sito al momento dell'effettuazione dell'ordine;<br>
                    2) essere idonei ad ogni uso al quale servono abitualmente prodotti dello stesso tipo o a qualsiasi altro uso speciale cercato dal Cliente, di cui Tøj è stata informata ed a cui ha formalmente acconsentito;<br>
                    3) presentare la qualità e le prestazioni abituali di un prodotto dello stesso tipo, che il consumatore può ragionevolmente aspettarsi, tenuto conto della natura del prodotto e delle caratteristiche descritte sul Sito.
                </p>
                <p>In caso di difetto di conformità acclarato, sarà necessario ripristinare la conformità del prodotto difettoso, senza spese per il Cliente.</p>
                <p>Si ricorda che la presente garanzia legale copre qualsiasi difetto di conformità manifestato entro il termine di 2 anni dalla data di consegna dell'ordine.</p>
                <p>Si ricorda, inoltre, che la garanzia legale potrebbe non applicarsi o applicarsi solo parzialmente nei seguenti casi:<br>
                    - La riparazione di danni indotti da una causa esterna al prodotto (per esempio: incidenti, urti, fulmini, sbalzi di tensione, ossidazione, presenza di sabbia...),<br>
                    - Un errore del Cliente risultante, ad esempio, da un uso o da un'installazione non conformi alle specifiche del costruttore,<br>
                    - Un utilizzo dell'apparecchio che risulti dannoso al suo mantenimento,<br>
                    - Qualsiasi difetto di conformità di cui il cliente fosse a conoscenza al momento di effettuare l'ordine.
                </p>
            </div>
            <div class="privacy_content">
                <h3>8. Protezione dati personali</h3>
                <p>Le informazioni comunicateci sono indispensabili per il trattamento e l'inoltro degli ordini, oltre che per la redazione delle fatture e dei contratti di
                    garanzia; la loro mancanza comporta l'annullamento del tuo ordine. Iscrivendoti sul Sito, t’impegni a fornirci informazioni veritiere ed autentiche.
                    La comunicazione d’informazioni false è contraria alle presenti condizioni generali oltre che alle condizioni d'uso presenti sul Sito.
                </p>
                <p>Si ricorda che, in qualità di cliente, potrai, in qualsiasi momento, richiedere a Dandalo di fornirti informazioni riguardanti i tuoi dati personali e
                    chiederne la modifica, contattando il nostro Servizio Clienti.
                </p>
                <p>In caso in accettazione al momento della creazione del tuo conto sul Sito, Tøj ed i propri partner commerciali possono inviarti informazioni riguardanti
                    operazioni promozionali specifiche e circoscritte. Tali partner sono scelti da Tøj e sono riconosciuti per la qualità dei prodotti e servizi proposti.
                    Ricorda, inoltre,che potrai richiedere, in qualsiasi momento, l’interruzione dell’invio di tali messaggi di posta elettronica. Sappi che, in questo
                    processo, Dandalo è l’unico soggetto a detenere i tuoi dati personali.
                </p>
            </div>
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
