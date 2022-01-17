<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${not empty userSession}">
        <c:if test="${not empty userInfSession.nome}">
            <a href="${pageContext.request.contextPath}/customers/account">Benvenuto ${userInfSession.nome}!</a><hr class="border-hr">
        </c:if>
        <c:if test="${empty userInfSession.nome}">
            <a href="${pageContext.request.contextPath}/customers/account">Benvenuto Utente!</a><hr class="border-hr">
        </c:if>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/customers/sigin">Accedi / Registrati</a><hr class="border-hr">
    </c:otherwise>
</c:choose>