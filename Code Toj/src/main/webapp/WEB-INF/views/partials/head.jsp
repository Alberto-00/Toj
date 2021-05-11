<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover">
<title>${param.title}</title>
<meta name="description" content="Tøj | Clothing Store">
<link rel="icon" type="image/png" href="images/logo2.png">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone-no">
<meta name="apple-mobile-web-app-title" content="Tøj | Clothing Store">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<link rel="apple-touch-icon" href="images/logo2.png">
<link rel="apple-touch-startup-image" href="images/logo2.png">
<meta name="theme-color" content="#FFFFFF">
<link href="css/reset.css" rel="stylesheet">
<link href="css/library.css" rel="stylesheet">
<c:if test="${not empty param.style}">
    <link rel="stylesheet" href="css/${param.style}">
</c:if>
<script src="js/library.js" defer></script>
<!-- con defer scarichiamo prima tutti i contenuti e poi eseguiamo gli script. Se ci sono più script
verrano eseguiti in ordine. In questo modo gli script sono "non bloccanti".-->
<c:if test="${not empty param.script}">
    <script src="js/${param.script}" defer></script>
</c:if>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>