<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="styles" value="otherPage,contactUs"/>
        <jsp:param name="title" value="T&#x000F8;j | Clothing Store"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<!--middle-->
<div class = middle-top>
    <div class ="container-top">
        <a href="../../../index.jsp">Home</a>
        <span>/</span>
        <span>Contattaci</span>
    </div>
</div>

<div class="contact-area">
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
                    <form id="contact-form" method="POST" action="#">
                        <p>
                            <label>Il tuo Nome (richiesto)</label>
                            <input name="name" placeholder="Nome *" type="text">
                        </p>
                        <p>
                            <label>La tua Email (richiesto)</label>
                            <input name="email" placeholder="Email *" type="email">
                        </p>
                        <p>
                            <label>Oggetto</label>
                            <input name="subject" placeholder="Subject *" type="text">
                        </p>
                        <div class="contact_textarea">
                            <label>Il tuo Messaggio</label>
                            <textarea placeholder="Message *" name="message" class="form-control2"></textarea>
                        </div>
                        <input type="submit">
                    </form>
                </div>
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
