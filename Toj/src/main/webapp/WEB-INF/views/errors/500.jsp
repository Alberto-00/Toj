<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage"/>
        <jsp:param name="errorStyles" value="errors"/>
        <jsp:param name="title" value="T&#x000F8;j - Error 500"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<div class="container-top">
    <div class="row">
        <div class="column-contact">
            <h1>500</h1>
            <h2>Oops, something went wrong</h2>
            <p>Try to refresh this page or feel free to contact us if the problem persists.</p>
        </div>
        <div class="column-contact center">
            <img src="${pageContext.request.contextPath}/images/opsImg.png" alt="oopsImg">
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
