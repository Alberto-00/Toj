<%@ page contentType="text/html;charset=UTF-8"%>
<footer>
    <div class="container-top">
        <hr class="border-hr">
        <div class="row">
            <div class="column">
                <h3>Informazioni</h3>
                <a href="${pageContext.request.contextPath}/customers/aboutUs" class="hover">About Us</a>
                <a href="${pageContext.request.contextPath}/customers/privacy" class="hover">Privacy Policy</a>
                <a href="${pageContext.request.contextPath}/customers/termsCondition" class="hover">Termini e condizioni</a>
                <a href="${pageContext.request.contextPath}/customers/contactUs" class="hover">Contattaci</a>
                <a href="${pageContext.request.contextPath}/HomePage" class="hover">Ritorna all'HomePage</a>
            </div>
            <div class="column2">
                <h3>Contattaci</h3>
                <p>Indirizzo: Via Santa Maria Maggiore, 54, Salerno, Campania</p>
                <p>84086, IT</p>
                <p>Numero: (+39) 338 4546 448</p>
                <p>Email: Tøj@gmail.com</p>
                <div class="icon">
                    <a href="https://twitter.com" target="_blank" title="twitter" class="hover"><i class="fab fa-twitter"></i></a>
                    <a href="mailto:Tøj@gmail.com" target="_blank" title="googlePlus" class="hover"><i class="fab fa-google-plus"></i></a>
                    <a href="https://facebook.com" target="_blank" title="facebook" class="hover"><i class="fab fa-facebook"></i></a>
                    <a href="https://youtube.com" target="_blank" title="youtube" class="hover"><i class="fab fa-youtube"></i></a>
                </div>
            </div>
            <div class="column3">
                <h3>Registrati alla Newsletter</h3>
                <p>Qualità eccezionale. Fabbriche etiche. Iscriviti gratis
                    per usufruire  della spedizione e dei resi gratuiti
                    in Europa sul tuo primo ordine.
                </p>
                <div class="subscribe-form">
                    <form action="javascript:void(0)" method="post">
                        <label for="newsletter"></label>
                        <input type="email" id="newsletter" name="newsletter"
                               placeholder="Inserisci la tua email qui...">
                        <div class="popup">
                            <button id="button-newsletter" type="button" onclick="popupFunction()" name="newsletterSubmit" class="button">
                                <span>SUBSCRIBE</span>
                            </button>
                            <span class="popup popuptext" id="myPopup"></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="footer-bottom">
        <div class="container-footer-bottom">
            <div class="row2">
                <div class="column4">
                    <p>© 2021 Made with ❤️ by <span style="font-weight: bold">Tøj | Clothing Store.</span></p>
                </div>
                <div class="column5">
                    <p>Felici acquisti a tutti i nostri clienti. La qualità
                        e precisione è da <span style="font-weight: bold">Tøj | Clothing Store.</span></p>
                </div>
            </div>
        </div>
    </div>

    <!-- freccia che riporta al top della pagina-->
    <a id="scrollUp" href="#"><i class="fas fa-angle-double-up"></i></a>
</footer>
