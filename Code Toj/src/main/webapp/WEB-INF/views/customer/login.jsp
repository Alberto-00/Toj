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
<jsp:include page="../partials/customer/titleBanner.jsp">
    <jsp:param name="title" value="Login - Register"/>
</jsp:include>

<div class="container-top">
    <div class="row">
        <div class="column-contact">
            <div class="contact-message">
                <h1>Login</h1>
                <form method="post" action="${pageContext.request.contextPath}/customers/sigin" name="formPopup3">

                    <label>Email *</label>
                    <input name="email" type="email">

                    <label class="block">Password *</label>
                    <input name="password" type="password">
                    <c:if test="${not empty msg}">
                        <small class="errMsg">${msg}</small>
                    </c:if>

                    <div class="login-submit">
                        <a href="mailto:Tøj@gmail.com" target="_blank" title="googlePlus" class="hover">Hai dimenticato la password?</a>
                        <input class="submit-button-log" type="submit" name="submitContact" value="LOGIN">
                    </div>
                </form>
            </div>
        </div>

        <div class="column-contact">
            <div class="contact-message">
                <h1>Registrati</h1>
                <form method="post" action="${pageContext.request.contextPath}/customers/registration" name="formPopup4">

                    <label>Email *</label>
                    <input name="email" type="email">

                    <label class="block">Password *</label>
                    <input name="password" type="password">
                    <c:if test="${not empty msg2}">
                        <small class="errMsg">${msg2}</small>
                    </c:if>

                    <input class="submit-button-log reg block" type="submit" name="submitContact" value="REGISTRATI">
                </form>
            </div>
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
