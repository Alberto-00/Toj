<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,myAccount"/>
        <jsp:param name="customerScripts" value="myAccount"/>
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
        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        <span>/</span>
        <span>My Account</span>
    </div>
</div>

<div class="container-top">
    <div class="row">
        <div class="column-contact">
            <div id="dashboard-Account">
                <button class="btn-Account active">Dashboard</button>
                <button class="btn-Account">Dettagli Account</button>
                <button class="btn-Account">Ordini</button>
                <button class="btn-Account">Indirizzi</button>
                <button class="btn-Account">Carte collegate</button>
                <button class="btn-Account">Logout</button>
            </div>
        </div>
    </div>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>
