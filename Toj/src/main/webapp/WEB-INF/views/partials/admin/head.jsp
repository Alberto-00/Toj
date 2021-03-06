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
<meta name="theme-color" content="#009578">
<link href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/admin/libraryAdmin.css" rel="stylesheet">

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
<script src="${pageContext.request.contextPath}/js/jquery-validate-plugin.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous" defer></script>

<c:if test="${not empty param.adminScripts}">
    <c:forTokens items="${param.adminScripts}" delims="," var="adminScript">
        <script src="${pageContext.request.contextPath}/js/admin/${adminScript}.js" defer></script>
    </c:forTokens>
</c:if>
