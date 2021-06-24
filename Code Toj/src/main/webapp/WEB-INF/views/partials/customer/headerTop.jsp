<%@ page import="Model.Cart.Cart" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<header>
    <!-- sfondo sidenav -->
    <div id="sidenav-background-color"></div>
    <div id="popup-background-color"></div>

    <!-- header top -->
    <div class="header-top">
        <div class="container-top">
            <span>Consegna gratuita:</span>
            <p>approfittane per fare i tuoi acquisti</p>
            <span class="border">Resi gratuiti * </span>
            <p>Soddisfatti o rimborsati</p>
            <div class="top-right">
                <ul>
                    <li class="dropdown">
                        <a href="javascript:void(0)" class="dropbtn">Account
                            <i class="fas fa-angle-down icon-left"></i>
                        </a>
                        <div class="dropdown-content">
                            <a class="border-content" href="${pageContext.request.contextPath}/customers/account">Il mio Account</a>
                            <a href="${pageContext.request.contextPath}/customers/sigin">Accedi</a>
                        </div>
                    </li>
                    <li class="dropdown border">
                        <a href="${pageContext.request.contextPath}/customers/privacy" class="dropbtn">Privacy Policy</a>
                    </li>
                    <li class="dropdown border">
                        <a href="${pageContext.request.contextPath}/customers/termsCondition">Termini e condizioni</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- popup accedi registrati -->
    <div class="popup-login" id="myForm">
        <div class="login__content">
            <div class="login__forms">
                <form action="${pageContext.request.contextPath}/customers/sigin" method="post" class="login__registre close-icon" id="login-in">
                    <h1 class="login__title">Accedi</h1>
                    <a onclick="closeForm()" class="icon-close-popup-sigIn"><i class="fas fa-times"></i></a>

                    <div class="login__box">
                        <label for="EmailInput">
                            <i class="fas fa-user"></i>
                            <input type="text" id="EmailInput" name="email" placeholder="Email" class="login__input">
                        </label>
                        <small class="errMsg"></small>
                    </div>
                    <div class="login__box">
                        <label for="passwordInput">
                            <i class="fas fa-unlock-alt"></i>
                            <input type="password" id="passwordInput" name="password" placeholder="Password" class="login__input">
                        </label>
                        <small class="errMsg"></small>
                    </div>
                    <a href="${pageContext.request.contextPath}/customers/account" class="login__forgot">Password dimenticata?</a>
                    <input type="submit" class="login__button" value="Accedi" name="submitForm">
                    <span class="login__account">Non hai un Account ?</span>
                    <span class="login__signin" id="sign-up">Registrati</span>
                </form>

                <form action="${pageContext.request.contextPath}/customers/sigUp" method="post" class="login__create none close-icon" id="login-up">
                    <h1 class="login__title">Create Account</h1>
                    <a onclick="closeForm()" class="icon-close-popup-sigUp"><i class="fas fa-times"></i></a>

                    <div class="login__box">
                        <label for="email">
                            <i class="fas fa-at"></i>
                            <input type="email" id="email" name="email" placeholder="Email" class="login__input">
                        </label>
                    </div>

                    <div class="login__box">
                        <label for="passwordOutput">
                            <i class="fas fa-unlock-alt"></i>
                            <input type="password" id="passwordOutput" name="password" placeholder="Password" class="login__input">
                        </label>
                    </div>
                    <input type="submit" class="login__button" value="Registrati" name="submitForm">
                    <span class="login__account">Hai già un Account ?</span>
                    <span class="login__signup" id="sign-in">Accedi</span>
                </form>
            </div>
        </div>
    </div>

    <div class="header-middle">
        <div class="container-top">
            <div class="row">
                <div class="columnLogo">
                    <a href="${pageContext.request.contextPath}/index.jsp">
                        <img src="${pageContext.request.contextPath}/icons/logo.png"
                             alt="" width="100" height="100">
                    </a>
                </div>

                <!--Sidenav start -->
                <div class="columnSidenav">
                    <div class="open-sidenav">
                        <i class="fas fa-bars" onclick="openNav()"></i>
                    </div>
                </div>
                <div id="mySidenav" class="sidenav">
                    <div class="closebtn">
                        <a href="javascript:void(0)" onclick="closeNav()">
                            <i class="fas fa-times"></i>
                        </a>
                    </div>

                    <br><span>Consegna gratuita:</span>
                    <p>approfittane per fare i tuoi acquisti</p><br><br>
                    <span>Resi gratuiti * </span>
                    <p>Soddisfatti o rimborsati</p><br><br>

                    <a href="${pageContext.request.contextPath}/customers/sigin">Accedi / Registrati</a><hr class="border-hr">

                    <form action="${pageContext.request.contextPath}/customers/shop">
                        <label for="search">
                            <div class="search-sidenav">
                                <input class="url" type="hidden" name="page" value="1" data="${pageContext.request.contextPath}">
                                <input type="text" id="search" name="nome_articolo" placeholder="Cerca i prodotti qui...">
                            </div>
                        </label>
                    </form>

                    <div class="sidenav-btn">
                        <a href="${pageContext.request.contextPath}/index.jsp" style="border-bottom: 1px solid #747474;">
                            Home<i class="fas fa-home"></i>
                        </a>

                        <button class="dropdown-btn">Uomo
                            <i class="fas fa-plus"></i>
                        </button>
                        <div class="dropdown-container-sidenav">
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M">Mostra Tutto</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Cappotti">Cappotti</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Giacche">Giacche</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Camicie&nome_categoria=Polo&nome_categoria=T-shirt">Maglie</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Costumi">Costumi</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Pantaloni+lunghi&nome_categoria=Pantaloni+corti">Pantaloni</a>

                        </div>

                        <button class="dropdown-btn">Donna
                            <i class="fas fa-plus"></i>
                        </button>
                        <div class="dropdown-container-sidenav">
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F">Mostra Tutto</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Cappotti">Cappotti</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Giacche">Giacche</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=T-shirt&nome_categoria=Polo&nome_categoria=Top">Magliette</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Bikini&nome_categoria=Intero">Costumi</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Pantaloni+lunghi&nome_categoria=Pantaloni+corti">Pantaloni</a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Gonna+corta&nome_categoria=Gonna+lunga">Gonne</a>
                        </div>

                        <button class="dropdown-btn">Nuovi Arrivi
                            <i class="fas fa-plus"></i>
                        </button>
                        <div class="dropdown-container-sidenav">
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&latest=true">Uomo<i class="fas fa-male"></i></a>
                            <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&latest=true">Donna<i class="fas fa-female"></i></a>
                        </div>

                        <button class="dropdown-btn">Account
                            <i class="fas fa-plus"></i>
                        </button>
                        <div class="dropdown-container-sidenav">
                            <a href="${pageContext.request.contextPath}/customers/account">Area personale</a>
                            <a href="${pageContext.request.contextPath}/customers/sigin">Accedi</a>
                        </div>

                        <a href="${pageContext.request.contextPath}/customers/cart" style="border-bottom: 1px solid #747474;">
                            Carrello<i class="fas fa-cart-arrow-down"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/customers/aboutUs" style="border-bottom: 1px solid #747474;">
                            About Us<i class="far fa-address-card"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/customers/aboutUs" style="border-bottom: 1px solid #747474;">
                            Contattaci<i class="far fa-address-book"></i>
                        </a>
                    </div><br>
                    <p><i class="far fa-envelope"></i>Tøj@gmail.com</p>
                    <div class="social-icon-sidenav">
                        <a href="https://twitter.com" target="_blank" title="twitter"><i class="fab fa-twitter"></i></a>
                        <a href="mailto:Tøj@gmail.com" target="_blank" title="googlePlus"><i class="fab fa-google-plus"></i></a>
                        <a href="https://facebook.com" target="_blank" title="facebook"><i class="fab fa-facebook"></i></a>
                        <a href="https://youtube.com" target="_blank" title="youtube"><i class="fab fa-youtube"></i></a>
                    </div><br>
                </div>
                <!--Sidenav end -->

                <div class="columnSearchBar">
                    <form action="${pageContext.request.contextPath}/customers/shop" autocomplete="off">
                        <div class="form-group">
                            <div class="search">
                                <label for="txt-search"></label>
                                <input class="url" type="hidden" name="page" value="1" data="${pageContext.request.contextPath}">
                                <input type="text" id="txt-search" name="nome_articolo" class="search-input" placeholder="Cerca i prodotti qui...">
                            </div>
                            <button class="btn" type="submit"><i class="fas fa-search"></i></button>
                        </div>
                    </form>
                    <div id="filter-records"></div>
                </div>
                <div class="columnSignIn">
                    <a id="hide" onclick="openForm()">Accedi / Registrati</a>
                </div>
                <div class="columnInfo">
                    <div class="cart-link">
                        <a href="${pageContext.request.contextPath}/customers/cart">
                            <i class="fas fa-shopping-cart icon-right"></i>Carrello
                            <% Cart cart = (Cart) session.getAttribute("cartNotLog"); int sum = 0;
                            if(cart != null){
                                sum = cart.quantity();
                            }%>
                            <span class="num-cart-product"><%=sum%></span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- header bottom -->
    <div class="container-top">
        <div class="nav-bottom" id="nav-bottom">
            <ul>
                <li class="dropdown">
                        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
                </li>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M" class="dropbtn">Uomo<i class="fas fa-angle-down icon-left"></i></a>
                    <div class="dropdown-content dropdown-content-bottom">
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Cappotti">Cappotti</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Giacche">Giacche</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Camicie&nome_categoria=Polo&nome_categoria=T-shirt">Maglie</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Costumi">Costumi</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&nome_categoria=Pantaloni+lunghi&nome_categoria=Pantaloni+corti">Pantaloni</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F" class="dropbtn">Donna<i class="fas fa-angle-down icon-left"></i></a>
                    <div class="dropdown-content dropdown-content-bottom">
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Cappotti">Cappotti</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Giacche">Giacche</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=T-shirt&nome_categoria=Polo&nome_categoria=Top">Magliette</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Bikini&nome_categoria=Intero">Costumi</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Pantaloni+lunghi&nome_categoria=Pantaloni+corti">Pantaloni</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&nome_categoria=Gonna+corta&nome_categoria=Gonna+lunga">Gonne</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Nuovi Arrivi<i class="fas fa-angle-down icon-left"></i></a>
                    <div class="dropdown-content dropdown-content-bottom">
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=M&latest=true">Uomo</a>
                        <a href="${pageContext.request.contextPath}/customers/shop?page=1&Sesso=F&latest=true">Donna</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/customers/aboutUs" class="color-fixed-navBottom">About Us</a>
                </li>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/customers/contactUs" class="color2-fixed-navBottom">Contattaci</a>
                </li>
            </ul>
            <span class="hidden-span">Call Free Support: (+39) 338 4546 448</span>
        </div>
    </div>
</header>