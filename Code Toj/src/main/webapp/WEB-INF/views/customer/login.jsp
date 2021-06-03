<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,login"/>
        <jsp:param name="title" value="T&#x000F8;j - Login / Register"/>
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
        <span>Login - Register</span>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="column-contact">
            <div class="contact-message">
                <h1>Login</h1>
                <form method="post" action="#">
                    <p>
                        <label>Username o email *</label>
                        <input name="username" type="text">
                    </p>
                    <p>
                        <label>Password *</label>
                        <input name="password" type="password">
                    </p>
                    <div class="login-submit">
                        <a href="#">Hai dimenticato la password?</a>
                        <input class="submit-button-log" type="submit" name="submitContact" value="LOGIN">
                        <label for="remember" class="hover">
                            <input id="remember" type="checkbox" name="remember" value="remember">
                            Remember me
                        </label>
                    </div>
                </form>
            </div>
        </div>

        <div class="column-contact">
            <div class="contact-message">
                <h1>Registrati</h1>
                <form method="post" action="#">
                    <p>
                        <label>Email *</label>
                        <input name="email" type="email">
                    </p>
                    <p>
                        <label>Username *</label>
                        <input class="input-text" name="username" type="text">
                    </p>
                    <p>
                        <label>Password *</label>
                        <input name="password" type="password">
                    </p>
                    <input class="submit-button-reg" type="submit" name="submitContact" value="REGISTRATI">
                </form>
            </div>
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
