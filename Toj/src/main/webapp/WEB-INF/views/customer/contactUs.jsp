<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,contactUs"/>
        <jsp:param name="customerScripts" value="validationContactUs"/>
        <jsp:param name="title" value="T&#x000F8;j - contact us"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<!--middle-->
<jsp:include page="../partials/customer/titleBanner.jsp">
    <jsp:param name="title" value="Contattaci"/>
</jsp:include>

<div class="container-top">
    <div class="row">
        <div class="column-contact">
            <div class="contact-message">
                <h1>Contattaci</h1>
                <p>
                    Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum.
                    Mirum est notare quam littera gothica, quam nunc putamus parum claram anteposuerit litterarum formas human.
                    qui sequitur mutationem consuetudium lectorum. Mirum est notare quam
                </p>
                <ul>
                    <li>
                        <i class="fa fa-fax"></i>
                        Indirizzo: Via Santa Maria Maggiore, 54, Salerno, Campania
                    </li>
                    <li>
                        <i class="far fa-envelope"></i>
                        Tøj@gmail.com
                    </li>
                    <li>
                        <i class="fa fa-phone"></i>
                        (+39) 338 4546 448
                    </li>
                </ul>
            </div>
        </div>

        <div class="column-contact">
            <div class="contact-message">
                <h1>Scrivici le tue idee</h1>
                <form method="post" name="form-contactUs" action="${pageContext.request.contextPath}/customers/contactUs">
                    <p>
                        <label>Nome</label>
                        <input class="input-text" name="name" placeholder="Nome *" type="text" required autocomplete="off">
                    </p>
                    <p>
                        <label>Email</label>
                        <input name="email" placeholder="Email *" type="email"  required autocomplete="off">
                        <c:if test="${not empty msg}">
                            <small class="errMsg">${msg}</small>
                        </c:if>
                    </p>
                    <p>
                        <label>Oggetto</label>
                        <input class="input-text" name="subject" placeholder="Subject *" type="text"  required autocomplete="off">
                    </p>
                    <div class="contact_textarea">
                        <label>Messaggio</label>
                        <textarea placeholder="Message *" name="message" required autocomplete="off"></textarea>
                    </div>
                    <input class="submit-button-log" type="submit" name="submitContact" value="INVIA">
                </form>
            </div>
        </div>
    </div>
</div>

<div class="google-maps">
    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d24176.884208934775!2d14.763927359219496!3d40.7595937268949!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x133bc5a579fb2a69%3A0x8fdc82da37557976!2sUniversity%20of%20Salerno!5e0!3m2!1sen!2sit!4v1620954266451!5m2!1sen!2sit"
            width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy">
    </iframe>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
