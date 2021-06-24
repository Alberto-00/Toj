<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover">
<title>${param.title}</title>
<meta name="description" content="T&#x000F8;j | Clothing Store">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/icons/logo2.png">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone-no">
<meta name="apple-mobile-web-app-title" content="T&#x000F8;j | Clothing Store">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/icons/logo2.png">
<link rel="apple-touch-startup-image" href="${pageContext.request.contextPath}/icons/logo2.png">
<meta name="theme-color" content="#FFFFFF">
<link href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/customer/library.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">

<c:if test="${not empty param.customerStyles}">
    <c:forTokens items="${param.customerStyles}" delims="," var="customerStyle">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/${customerStyle}.css">
    </c:forTokens>
</c:if>

<c:if test="${not empty param.errorStyles}">
    <c:forTokens items="${param.errorStyles}" delims="," var="errorStyle">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/errors/${errorStyle}.css">
    </c:forTokens>
</c:if>

<c:if test="${not empty param.adminStyles}">
    <c:forTokens items="${param.adminStyles}" delims="," var="adminStyle">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/${adminStyle}.css">
    </c:forTokens>
</c:if>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-validate-plugin.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous" defer></script>
<script src="${pageContext.request.contextPath}/js/customer/library.js" defer></script>
<script src="${pageContext.request.contextPath}/js/customer/ajax/liveSearchBar.js" defer></script>

<c:if test="${not empty param.customerScripts}">
    <c:forTokens items="${param.customerScripts}" delims="," var="customerScript">
        <script src="${pageContext.request.contextPath}/js/customer/${customerScript}.js" defer></script>
    </c:forTokens>
</c:if>

<c:if test="${not empty param.adminScripts}">
    <c:forTokens items="${param.adminScripts}" delims="," var="adminScripts">
        <script src="${pageContext.request.contextPath}/js/admin/${adminScripts}.js" defer></script>
    </c:forTokens>
</c:if>

<c:if test="${not empty param.customerAjaxScripts}">
    <c:forTokens items="${param.customerAjaxScripts}" delims="," var="customerAjaxScript">
        <script src="${pageContext.request.contextPath}/js/customer/ajax/${customerAjaxScript}.js" defer></script>
    </c:forTokens>
</c:if>

