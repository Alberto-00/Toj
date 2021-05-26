<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="it">
<head>
<jsp:include page="../partials/head.jsp">
    <jsp:param name="customerStyles" value="otherPage,contactUs"/>
    <jsp:param name="errorStyles" value="errors"/>
    <jsp:param name="title" value="T&#x000F8;j - Error 404"/>
</jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<div class="container-top">
    <div class="row">
        <div class="column-contact">
            <h1>404</h1>
            <h2>Oops! - Page Not Be Found</h2>
            <p>Sorry but the page you are looking for does not exist, have been
                removed, name changed or is temporarily unavailable.
            </p>
            <div class="error_form">
                <a href="${pageContext.request.contextPath}/index.jsp">Back to Home Page</a>
            </div>
        </div>
        <div class="column-contact">
            <img src="${pageContext.request.contextPath}/images/opsImg.png" alt="oopsImg">
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
