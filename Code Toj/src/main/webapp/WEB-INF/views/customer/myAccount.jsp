<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="styles" value="otherPage"/>
        <jsp:param name="title" value="T&#x000F8;j - my account"/>
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
        <span>My Account</span>
    </div>
</div>



<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
