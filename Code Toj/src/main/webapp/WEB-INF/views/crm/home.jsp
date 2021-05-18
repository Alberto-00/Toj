<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Dashboard"/>
        <jsp:param name="styles" value="crm,dashboard"/>
    </jsp:include>
</head>
<body>
<main class="app">
    <%@include file="../partials/crm/sidebar.jsp"%>
    <aside class="sidebar">
        <nav class="menu grid-y align-center">
            <img src="../images/logo.png" width="100" height="100">
            <a href="#">Gestione Clienti</a>
            <a href="/MostraArticoli">Gestione Prodotti</a>
            <a href="#">Gestione Ordini</a>
            <a href="#">Gestione Categorie</a>
            <a href="#">Gestione Nazioni</a>
            <a href="#">Profilo</a>
            <a href="#">Logout</a>
        </nav>
    </aside>
    <section class="content grid-y">
        <header class="topbar grid-x align-center">
            <label class="field command">
                <input type="text" placeholder="Cerca comandi">
            </label>
            <span class="account">
                Benvenuto amministratore
            </span>
        </header>
    </section>

    <!--script per sidebar-->
    <script>
        const hamburger = document.getElementsByClassName("topbar")[0].firstElementChild;
        hamburger.addEventListener('click', function (){
            const sidebar = document.getElementsByClassName("sidebar")[0];
            const content = document.getElementsByClassName("content")[0];
            sidebar.classList.toggle("collapse");
            content.classList.toggle("full-width");
        });

        //ritorna all'homepage cliccando sul logo
        const homeImg = document.getElementsByClassName("menu")[0].firstElementChild;
        homeImg.addEventListener('click', function (){
            window.location.href="/Toj/crm/dashboard";
        })
    </script>
</main>
</body>
</html>
