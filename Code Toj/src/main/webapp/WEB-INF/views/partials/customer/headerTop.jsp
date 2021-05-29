<%@ page contentType="text/html;charset=UTF-8"%>
<header>
    <!-- sfondo sidenav -->
    <div id="sidenav-background-color"></div>

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
                        <a href="javascript:void(0)" class="dropbtn">My Account<i class="fas fa-angle-down icon-left"></i></a>
                        <div class="dropdown-content">
                            <a class="border-content" href="${pageContext.request.contextPath}/accountServlet">My Account</a>
                            <a href="${pageContext.request.contextPath}/loginServlet">Sign in</a>
                        </div>
                    </li>
                    <li class="dropdown border">
                        <a href="javascript:void(0)" class="dropbtn">Language<i class="fas fa-angle-down icon-left"></i></a>
                        <div class="dropdown-content">
                            <a class="border-content" href="#">
                                <img src="${pageContext.request.contextPath}/icons/it.png" class="image-lang" alt="">Ita
                            </a>
                            <a href="#"><img src="${pageContext.request.contextPath}/icons/en.png" class="image-lang" alt="">Eng</a>
                        </div>
                    </li>
                    <li class="dropdown border">
                        <a href="#">Termini e condizioni</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- popup accedi registrati -->
    <div class="popup-login" id="myForm">
        <div class="login__content">
            <div class="login__forms">
                <form action="" class="login__registre close-icon" id="login-in">
                    <h1 class="login__title">Sign In</h1>
                    <a onclick="closeForm()" class="icon-close-popup-sigIn"><i class="fas fa-times"></i></a>

                    <div class="login__box">
                        <label for="usernameInput">
                            <i class="fas fa-user"></i>
                            <input type="text" id="usernameInput" name="usernameInput" placeholder="Username" class="login__input">
                        </label>
                    </div>
                    <div class="login__box">
                        <label for="passwordInput">
                            <i class="fas fa-unlock-alt"></i>
                            <input type="password" id="passwordInput" name="passwordInput" placeholder="Password" class="login__input">
                        </label>
                    </div>
                    <a href="#" class="login__forgot">Forgot password?</a>
                    <a href="#" class="login__button">Sign In</a>
                    <div>
                        <span class="login__account">Don't have an Account ?</span>
                        <span class="login__signin" id="sign-up">Sign Up</span>
                    </div>
                </form>

                <form action="" class="login__create none close-icon" id="login-up">
                    <h1 class="login__title">Create Account</h1>
                    <a onclick="closeForm()" class="icon-close-popup-sigUp"><i class="fas fa-times"></i></a>
                    <div class="login__box">
                        <label for="usernameOutput">
                            <i class="fas fa-user"></i>
                            <input type="text" id="usernameOutput" name="usernameOutput" placeholder="Username" class="login__input">
                        </label>
                    </div>

                    <div class="login__box">
                        <label for="email">
                            <i class="fas fa-at"></i>
                            <input type="email" id="email" name="email" placeholder="Email" class="login__input">
                        </label>
                    </div>

                    <div class="login__box">
                        <label for="passwordOutput">
                            <i class="fas fa-unlock-alt"></i>
                            <input type="password" id="passwordOutput" name="passwordOutput" placeholder="Password" class="login__input">
                        </label>
                    </div>
                    <a href="#" class="login__button">Sign Up</a>
                    <div>
                        <span class="login__account">Already have an Account ?</span>
                        <span class="login__signup" id="sign-in">Sign In</span>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="header-middle">
        <div class="container-top">
            <div class="logo">
                <a href="${pageContext.request.contextPath}/index.jsp">
                    <img src="${pageContext.request.contextPath}/icons/logo.png"
                         alt="" width="100" height="100">
                </a>

                <!--Sidenav start -->
                <div class="open-sidenav">
                    <i class="fas fa-bars" onclick="openNav()"></i>
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

                    <a href="#">Accedi / Registrati</a><hr class="border-hr">

                    <form action="#">
                        <label for="categoria-sidenav">
                            <div class="select-sidenav">
                                <select id="categoria-sidenav" name="categoria-sidenav">
                                    <option value="Tutte le categorie" selected>Tutte le categorie</option>
                                    <option value="Cappotti">Cappotti</option>
                                    <option value="Giacche">Giacche</option>
                                    <option value="Maglie">Maglie</option>
                                    <option value="Costumi">Costumi</option>
                                    <option value="Gonne">Gonne</option>
                                </select>
                            </div>
                        </label>
                        <label for="search-input-sidenav">
                            <div class="search-sidenav">
                                <input type="text" id="search-input-sidenav" name="search-input-sidenav" placeholder="Cerca i prodotti qui...">
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
                            <a href="#">Cappotti</a>
                            <a href="#">Giacche</a>
                            <a href="#">Maglie</a>
                            <a href="#">Costumi</a>
                            <a href="#">Pantaloni</a>
                        </div>

                        <button class="dropdown-btn">Donna
                            <i class="fas fa-plus"></i>
                        </button>
                        <div class="dropdown-container-sidenav">
                            <a href="#">Cappotti</a>
                            <a href="#">Giacche</a>
                            <a href="#">Maglie</a>
                            <a href="#">Costumi</a>
                            <a href="#">Pantaloni</a>
                            <a href="#">Gonne</a>
                        </div>

                        <button class="dropdown-btn">Nuovi Arrivi
                            <i class="fas fa-plus"></i>
                        </button>
                        <div class="dropdown-container-sidenav">
                            <a href="#">Uomo<i class="fas fa-male"></i></a>
                            <a href="#">Donna<i class="fas fa-female"></i></a>
                        </div>

                        <button class="dropdown-btn">Account
                            <i class="fas fa-plus"></i>
                        </button>
                        <div class="dropdown-container-sidenav">
                            <a href="#">Area personale</a>
                            <a href="#">Sign in</a>
                        </div>

                        <button class="dropdown-btn">Language
                            <i class="fas fa-plus"></i>
                        </button>
                        <div class="dropdown-container-sidenav">
                            <a href="#"><img src="${pageContext.request.contextPath}/icons/it.png" alt="ita">Ita</a>
                            <a href="#"><img src="${pageContext.request.contextPath}/icons/en.png" alt="english">Eng</a>
                        </div>

                        <a href="#" style="border-bottom: 1px solid #747474;">
                            Carrello<i class="fas fa-cart-arrow-down"></i>
                        </a>
                        <a href="#" style="border-bottom: 1px solid #747474;">
                            About Us<i class="far fa-address-card"></i>
                        </a>
                        <a href="#" style="border-bottom: 1px solid #747474;">
                            Contattaci<i class="far fa-address-book"></i>
                        </a>
                    </div><br>
                    <p><i class="far fa-envelope"></i>Tøj@gmail.com</p>
                    <div class="social-icon-sidenav">
                        <a href="#" title="twitter"><i class="fab fa-twitter"></i></a>
                        <a href="#" title="googlePlus"><i class="fab fa-google-plus"></i></a>
                        <a href="#" title="facebook"><i class="fab fa-facebook"></i></a>
                        <a href="#" title="youtube"><i class="fab fa-youtube"></i></a>
                    </div><br>
                </div>
                <!--Sidenav end -->

                <div class="top-left">
                    <a id="hide" onclick="openForm()">Accedi / Registrati</a>
                    <div class="cart-link">
                        <a href="#">
                            <i class="fas fa-shopping-cart icon-right"></i>Carrello
                            <span class="num-cart-product">0</span>
                        </a>
                    </div>
                </div>
            </div>
            <form action="#">
                <div class="form-group">
                    <div class="dropdown-middle">
                        <label for="categoria"></label>
                        <div class="custom-select" style="width: 150px;">
                            <select id="categoria" name="categoria">
                                <option value="default option">Tutte le categorie</option>
                                <option value="Tutte le categorie" selected>Tutte le categorie</option>
                                <option value="Cappotti">Cappotti</option>
                                <option value="Giacche">Giacche</option>
                                <option value="Maglie">Maglie</option>
                                <option value="Costumi">Costumi</option>
                                <option value="Gonne">Gonne</option>
                            </select>
                        </div>
                    </div>
                    <div class="search">
                        <label for="search-input"></label>
                        <input type="text" id="search-input" name="search-input" class="search-input" placeholder="Cerca i prodotti qui...">
                    </div>
                    <button class="btn" type="submit"><i class="fas fa-search"></i></button>
                </div>
            </form>
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
                    <a href="#" class="dropbtn">Uomo<i class="fas fa-angle-down icon-left"></i></a>
                    <div class="dropdown-content dropdown-content-bottom">
                        <a href="#">Cappotti</a>
                        <a href="#">Giacche</a>
                        <a href="#">Maglie</a>
                        <a href="#">Costumi</a>
                        <a href="#">Pantaloni</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">Donna<i class="fas fa-angle-down icon-left"></i></a>
                    <div class="dropdown-content dropdown-content-bottom">
                        <a href="#">Cappotti</a>
                        <a href="#">Giacche</a>
                        <a href="#">Magliette</a>
                        <a href="#">Costumi</a>
                        <a href="#">Pantaloni</a>
                        <a href="#">Gonne</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Nuovi Arrivi<i class="fas fa-angle-down icon-left"></i></a>
                    <div class="dropdown-content dropdown-content-bottom">
                        <a href="#">Uomo</a>
                        <a href="#">Donna</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="../../customer/aboutUs.jsp" class="color-fixed-navBottom">About Us</a>
                </li>
                <li class="dropdown">
                    <a href="../../customer/contactUs.jsp" class="color2-fixed-navBottom">Contattaci</a>
                </li>
            </ul>
            <span class="hidden-span">Call Free Support: (+39) 338 4546 448</span>
        </div>
    </div>
</header>