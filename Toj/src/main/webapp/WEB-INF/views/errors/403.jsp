<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage"/>
        <jsp:param name="errorStyles" value="errors,error403"/>
        <jsp:param name="title" value="T&#x000F8;j - Error 403"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<div class="container-top">
    <div class="row">
        <div class="column-contact">
            <h1>403</h1>
            <h2>Access Forbidden</h2>
            <p>You don't have permission to access this area. Turn back NOW!</p>
            <div class="error_form">
                <a href="${pageContext.request.contextPath}/index.jsp">Back to Home Page</a>
            </div>
        </div>
        <div class="column-contactImg center">
            <img src="${pageContext.request.contextPath}/images/forbidden.png" alt="forbidden">
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
