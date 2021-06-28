<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${not empty userSession}">
        <c:if test="${not empty userInfSession.nome}">
            <a id="hide" href="${pageContext.request.contextPath}/customers/account">Benvenuto ${userInfSession.nome}!</a>
        </c:if>
        <c:if test="${empty userInfSession.nome}">
            <a id="hide" href="${pageContext.request.contextPath}/customers/account">Benvenuto Utente!</a>
        </c:if>
    </c:when>
    <c:otherwise>
        <a id="hide" onclick="openForm()">Accedi / Registrati</a>
    </c:otherwise>
</c:choose>